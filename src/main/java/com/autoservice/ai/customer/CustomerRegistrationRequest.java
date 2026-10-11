package com.autoservice.ai.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerRegistrationRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 20)
        String mobileNumber,

        @Size(max = 150)
        String location,


        boolean reminderConsent,

        @NotNull
        @Valid
        VehicleDetails vehicle
) {
    public record VehicleDetails(
            @NotBlank
            @Size(max = 20)
            String vehicleNumber,

            @NotBlank
            @Size(max = 30)
            String vehicleType,

            @Size(max = 80)
            String vehicleBrand
    ) {}
}