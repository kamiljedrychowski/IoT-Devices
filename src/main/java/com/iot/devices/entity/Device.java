package com.iot.devices.entity;

import com.iot.devices.enums.DeviceType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "device")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device extends BaseEntity {

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}
