package com.iot.devices.entity;

import com.iot.devices.enums.DeviceState;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_status")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatus extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DeviceState status;

}
