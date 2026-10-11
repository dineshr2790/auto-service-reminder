
package com.autoservice.ai.service;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(
            ServiceRecordService serviceRecordService
    ) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRecordResponse recordService(
            @Valid @RequestBody ServiceRecordRequest request
    ) {
        ServiceRecord saved = serviceRecordService.recordService(
                request.vehicleId(),
                request.serviceDate(),
                request.oilBrand(),
                request.odometerKm(),
                request.amountPaid()
        );

        return new ServiceRecordResponse(
                saved.getId(),
                saved.getVehicle().getId(),
                saved.getVehicle().getVehicleNumber(),
                saved.getServiceDate(),
                saved.getOilBrand(),
                saved.getOdometerKm(),
                saved.getAmountPaid(),
                saved.getNextReminderDate()
        );
    }
}