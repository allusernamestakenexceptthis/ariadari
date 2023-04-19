package com.gomilkyway.profile.adari.audit;

import java.util.Date;
import static jakarta.persistence.TemporalType.TIMESTAMP;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;

import lombok.Getter;
import lombok.Setter;

/**
 * AuditAbstract entity parent class for auditing
 * to provides createdBy, modifiedBy, createdDate, modifiedDate
 * to track who and when the entity is created and modified
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditAbstract<T> {

    /**
     * Username of the account that created the record
     * T is currently String
     */
    @CreatedBy
    protected T createdBy;

    /**
     * Username of the account that last modified the record
     * T is currently String
     */
    @LastModifiedBy
    protected T modifiedBy;

    /**
     * Date and time of the record creation
     */
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

    /**
     * Date and time of the last modification
     */
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date modifiedDate;
        
}
