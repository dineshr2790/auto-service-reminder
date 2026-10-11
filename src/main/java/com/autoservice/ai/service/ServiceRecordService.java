
package com.autoservice.ai.service;

import com.autoservice.ai.vehicle.Vehicle;
import com.autoservice.ai.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceRecordService(
            ServiceRecordRepository serviceRecordRepository,
            VehicleRepository vehicleRepository
    ) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public ServiceRecord recordService(
            Long vehicleId,
            LocalDate serviceDate,
            String oilBrand,
            Integer odometerKm,
            BigDecimal amountPaid
    ) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Vehicle not found: " + vehicleId
                ));

        LocalDate nextReminderDate = serviceDate.plusMonths(3);

        ServiceRecord serviceRecord = new ServiceRecord(
                vehicle,
                serviceDate,
                oilBrand,
                odometerKm,
                amountPaid,
                nextReminderDate
        );

        return serviceRecordRepository.save(serviceRecord);
    }
}