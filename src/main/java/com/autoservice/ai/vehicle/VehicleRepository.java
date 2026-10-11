
package com.autoservice.ai.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {
    boolean existsByVehicleNumber(String vehicleNumber);
}