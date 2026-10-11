
package com.autoservice.ai.vehicle;

import com.autoservice.ai.customer.Customer;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "vehicle_number", nullable = false, unique = true, length = 20)
    private String vehicleNumber;

    @Column(name = "vehicle_type", nullable = false, length = 30)
    private String vehicleType = "BIKE";

    @Column(name = "vehicle_brand", length = 80)
    private String vehicleBrand;

    @Column(
            name = "created_at",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private OffsetDateTime createdAt;

    protected Vehicle() {
        // Required by JPA
    }

    public Vehicle(
            Customer customer,
            String vehicleNumber,
            String vehicleType,
            String vehicleBrand
    ) {
        this.customer = customer;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleBrand = vehicleBrand;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}