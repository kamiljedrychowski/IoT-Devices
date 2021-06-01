package com.iot.devices.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id")
    protected Long id;

    @Column(name = "creation_time")
    protected LocalDateTime creationTime;

    @Column(name = "modification_time")
    protected LocalDateTime modificationTime;
}
