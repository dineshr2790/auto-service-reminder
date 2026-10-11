
package com.autoservice.ai.service;

import com.autoservice.ai.vehicle.Vehicle;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "service_records")
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "service_date", nullable = false)
    private LocalDate serviceDate;

    @Column(name = "oil_brand", nullable = false, length = 80)
    private String oilBrand;

    @Column(name = "odometer_km")
    private Integer odometerKm;

    @Column(name = "amount_paid", precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @Column(name = "next_reminder_date", nullable = false)
    private LocalDate nextReminderDate;

    @Column(
            name = "created_at",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private OffsetDateTime createdAt;

    protected ServiceRecord() {
        // Required by JPA
    }

    public ServiceRecord(
            Vehicle vehicle,
            LocalDate serviceDate,
            String oilBrand,
            Integer odometerKm,
            BigDecimal amountPaid,
            LocalDate nextReminderDate
    ) {
        this.vehicle = vehicle;
        this.serviceDate = serviceDate;
        this.oilBrand = oilBrand;
        this.odometerKm = odometerKm;
        this.amountPaid = amountPaid;
        this.nextReminderDate = nextReminderDate;
    }

    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public String getOilBrand() {
        return oilBrand;
    }

    public Integer getOdometerKm() {
        return odometerKm;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public LocalDate getNextReminderDate() {
        return nextReminderDate;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}