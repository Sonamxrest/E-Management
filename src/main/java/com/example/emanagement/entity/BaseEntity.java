package com.example.emanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity<PK extends Serializable> extends AbstractPersistable<PK> {

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "dd:MM:yyyy hh:mm:ss")
    private Date createdAt = new Date();
    @LastModifiedDate
    @JsonFormat(pattern = "dd:MM:yyyy hh:mm:ss")
    @Column(name = "last_modified_at")
    private Date lastModifiedAt = new Date();
    @Version
    private int version;

    @CreatedBy
    @Column(name = "created_by_id", updatable = false)
    private Long createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by_id")
    private Long lastModifiedBy;
    @Override
    protected void setId(PK id) {
        super.setId(id);
    }
}
