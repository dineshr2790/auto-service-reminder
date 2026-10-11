
package com.autoservice.ai.registration;

import com.autoservice.ai.customer.CustomerRegistrationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrationRequest(

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
        CustomerRegistrationRequest.VehicleDetails vehicle,

        @NotNull
        LocalDate serviceDate,

        @NotBlank
        @Size(max = 80)
        String oilBrand,

        @PositiveOrZero
        Integer odometerKm,

        @DecimalMin("0.0")
        BigDecimal amountPaid

) {}