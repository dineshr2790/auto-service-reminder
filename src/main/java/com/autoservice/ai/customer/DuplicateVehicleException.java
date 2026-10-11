
package com.autoservice.ai.customer;

public class DuplicateVehicleException extends RuntimeException {

    public DuplicateVehicleException(String vehicleNumber) {
        super("Vehicle is already registered: " + vehicleNumber);
    }
}