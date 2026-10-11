
package com.autoservice.ai.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ServiceRecordRequest(

        @NotNull
        @Positive
        Long vehicleId,

        @NotNull
        LocalDate serviceDate,

        @NotBlank
        @Size(max = 80)
        String oilBrand,

        @Positive
        Integer odometerKm,

        @DecimalMin("0.0")
        BigDecimal amountPaid
) {
}