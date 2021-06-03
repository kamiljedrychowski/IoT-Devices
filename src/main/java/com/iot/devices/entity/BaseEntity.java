package com.iot.devices.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    protected Long id;

    @Column(name = "creation_time")
    protected LocalDateTime creationTime;

    @Column(name = "modification_time")
    protected LocalDateTime modificationTime;

    @PrePersist
    protected void beforeInsert() {
        if(this.creationTime == null) {
            this.creationTime = LocalDateTime.now(ZoneOffset.UTC);
        }

        if(this.modificationTime == null) {
            this.modificationTime = LocalDateTime.now(ZoneOffset.UTC);
        }
    }

    @PreUpdate
    protected void beforeUpdate() {
        this.modificationTime = LocalDateTime.now(ZoneOffset.UTC);
    }
}
