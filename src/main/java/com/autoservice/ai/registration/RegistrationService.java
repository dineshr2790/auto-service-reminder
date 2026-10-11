
package com.autoservice.ai.registration;

import com.autoservice.ai.customer.Customer;
import com.autoservice.ai.customer.CustomerRepository;
import com.autoservice.ai.service.ServiceRecord;
import com.autoservice.ai.service.ServiceRecordRepository;
import com.autoservice.ai.vehicle.Vehicle;
import com.autoservice.ai.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class RegistrationService {

    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final ServiceRecordRepository serviceRecordRepository;

    public RegistrationService(
            CustomerRepository customerRepository,
            VehicleRepository vehicleRepository,
            ServiceRecordRepository serviceRecordRepository
    ) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {

        // 1. Create customer.
        Customer customer = new Customer(
                request.name().trim(),
                request.mobileNumber().trim(),
                request.location(),
                request.reminderConsent()
        );

        Customer savedCustomer = customerRepository.save(customer);

        // 2. Create vehicle associated with that customer.
        Vehicle vehicle = new Vehicle(
                savedCustomer,
                request.vehicle().vehicleNumber().trim().toUpperCase(),
                request.vehicle().vehicleType(),
                request.vehicle().vehicleBrand()
        );

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        // 3. Create the oil service record.
        LocalDate nextReminderDate =
                request.serviceDate().plusMonths(3);

        ServiceRecord serviceRecord = new ServiceRecord(
                savedVehicle,
                request.serviceDate(),
                request.oilBrand().trim(),
                request.odometerKm(),
                request.amountPaid(),
                nextReminderDate
        );

        ServiceRecord savedServiceRecord =
                serviceRecordRepository.save(serviceRecord);

        // Return only the information the client needs.
        return new RegistrationResponse(
                savedCustomer.getId(),
                savedVehicle.getId(),
                savedServiceRecord.getId(),
                savedCustomer.getName(),
                savedVehicle.getVehicleNumber(),
                savedServiceRecord.getNextReminderDate(),
                savedServiceRecord.getAmountPaid(),
                "Customer, vehicle and oil purchase registered successfully."
        );
    }
}