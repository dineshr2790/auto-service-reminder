
package com.autoservice.ai.customer;

import com.autoservice.ai.vehicle.Vehicle;
import com.autoservice.ai.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository
    ) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public CustomerRegistrationResponse registerCustomer(
            CustomerRegistrationRequest request
    ) {
        String vehicleNumber = request.vehicle()
                .vehicleNumber()
                .trim()
                .toUpperCase(Locale.ROOT);

        if (vehicleRepository.existsByVehicleNumber(vehicleNumber)) {
            throw new DuplicateVehicleException(vehicleNumber);
        }

        Customer customer = new Customer(
                request.name().trim(),
                request.mobileNumber().trim(),
                request.location() == null
                        ? null
                        : request.location().trim(),
                request.reminderConsent()
        );

        Customer savedCustomer = customerRepository.save(customer);

        Vehicle vehicle = new Vehicle(
                savedCustomer,
                vehicleNumber,
                request.vehicle().vehicleType().trim().toUpperCase(Locale.ROOT),
                request.vehicle().vehicleBrand() == null
                        ? null
                        : request.vehicle().vehicleBrand().trim()
        );

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return new CustomerRegistrationResponse(
                savedCustomer.getId(),
                savedVehicle.getId(),
                savedCustomer.getName(),
                savedVehicle.getVehicleNumber(),
                "Customer and vehicle registered successfully"
        );
    }
}