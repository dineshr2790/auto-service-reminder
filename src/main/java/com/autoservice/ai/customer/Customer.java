
package com.autoservice.ai.customer;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "mobile_number", nullable = false, length = 20)
    private String mobileNumber;

    @Column(length = 150)
    private String location;

    @Column(name = "reminder_consent", nullable = false)
    private boolean reminderConsent = false;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected Customer() {
        // Required by JPA
    }

    public Customer(
            String name,
            String mobileNumber,
            String location,
            boolean reminderConsent
    ) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.location = location;
        this.reminderConsent = reminderConsent;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getLocation() {
        return location;
    }

    public boolean isReminderConsent() {
        return reminderConsent;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}