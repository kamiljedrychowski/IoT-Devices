package com.iot.devices.service;

import com.iot.devices.dto.DeviceDto;
import com.iot.devices.entity.Device;
import com.iot.devices.entity.User;
import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.enums.DeviceStatus;
import com.iot.devices.enums.UserRole;
import com.iot.devices.exceptions.ObjectNotFoundException;
import com.iot.devices.exceptions.ObjectSavingException;
import com.iot.devices.exceptions.RequiredDataNotFoundException;
import com.iot.devices.exceptions.UnauthorizedAccessException;
import com.iot.devices.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceService {

    private final UserService userService;
    private final DeviceRepository deviceRepository;
    private final BlockingQueue<DeviceCommandMessage> deviceCommandQueue;


    /**
     * Method returns list of all devices for user
     * if privilegedRole all
     * if user only by owner
     *
     * @param requestHeader for Authorization header
     * @return List<DeviceDto>
     * @throws UnauthorizedAccessException Unauthorized access to getAllDevices
     */
    public List<DeviceDto> getAllDevices(HttpServletRequest requestHeader) throws UnauthorizedAccessException {
        User user = userService.getUserByAuthorization(requestHeader);

        if (user == null) {
            log.error("Unauthorized access to getAllDevices since user does not exist");
            throw new UnauthorizedAccessException("Unauthorized access to getAllDevices");
        }

        if (UserRole.getPrivilegedRoles().contains(user.getRole())) {
            return deviceRepository.findAll().stream().map(DeviceDto::fromEntity).collect(Collectors.toList());
        }
        return deviceRepository.findAllByOwner(user).stream().map(DeviceDto::fromEntity).collect(Collectors.toList());
    }

    public DeviceDto changeDeviceStatus(Long deviceId, DeviceStatus newStatus) throws ObjectNotFoundException {
        try {
            return DeviceDto.fromEntity(setDeviceStatusAndSendCommand(deviceId, newStatus));
        } catch (EntityNotFoundException exception) {
            log.error("Device with Id: {} does not exist", deviceId);
            throw new ObjectNotFoundException("Device does not exist");
        }
    }

    private Device setDeviceStatusAndSendCommand(Long deviceId, DeviceStatus newStatus) {
        Device device;
        device = deviceRepository.getById(deviceId);
        device.setStatus(newStatus);
        DeviceCommandMessage deviceCommandMessage = prepareCommandMessage(device, newStatus);
        deviceCommandQueue.add(deviceCommandMessage);
        deviceRepository.save(device);
        return device;
    }

    public DeviceCommandMessage prepareCommandMessage(Device device, DeviceStatus newStatus) {
        return DeviceCommandMessage.builder()
                .timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .deviceUuid(device.getUuid())
                .commandUuid(UUID.randomUUID())
                .command(newStatus.getDeviceCommand())
                .build();
    }

    public DeviceDto addDevice(DeviceDto deviceDto) throws Exception {
        Device device = createDevice(deviceDto);
        try {
            deviceRepository.save(device);
            return DeviceDto.fromEntity(device);
        } catch (Exception exception) {
            log.error("Exception while saving device");
            throw new ObjectSavingException("Exception while saving device - owner login probably does not exist");
        }
    }

    private Device createDevice(DeviceDto deviceDto) throws Exception {
        if (!validateDeviceDto(deviceDto)) {
            log.error("DeviceDto has wrong data: {}", deviceDto);
            throw new RequiredDataNotFoundException("DeviceDto has wrong data");
        }

        User owner = userService.getUserByLogin(deviceDto.getOwnerLogin());

        if(owner == null) {
            log.error("Owner with this login does not exist");
            throw new ObjectNotFoundException("Owner with this login does not exist");
        }

        return Device.builder()
                .name(deviceDto.getName())
                .type(deviceDto.getType())
                .owner(owner)
                .uuid(UUID.randomUUID())
                .status(DeviceStatus.OFF)
                .build();
    }

    public boolean validateDeviceDto(DeviceDto deviceDto) {
        return deviceDto != null && StringUtils.hasText(deviceDto.getName()) && StringUtils.hasText(deviceDto.getOwnerLogin())
                && deviceDto.getType() != null;
    }

    public void deleteDevice(Long id) throws Exception {
        try{
            deviceRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Device with given id: {} does not exist", id);
            throw new ObjectNotFoundException("Device with given id does not exist");
        }
    }
}
