
package com.capstone.momomeal.domain;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    public BaseTimeEntity() {
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }
}
