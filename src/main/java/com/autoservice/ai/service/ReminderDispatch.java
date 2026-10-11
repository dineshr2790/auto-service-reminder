
package com.autoservice.ai.service;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reminder_dispatches")
public class ReminderDispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "processed_at", insertable = false, updatable = false)
    private OffsetDateTime processedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected ReminderDispatch() {
    }

    public ReminderDispatch(ServiceRecord serviceRecord,
                           String status,
                           String message) {
        this.serviceRecord = serviceRecord;
        this.status = status;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getProcessedAt() {
        return processedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}