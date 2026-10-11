
package com.autoservice.ai.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ServiceRecordResponse(
        Long id,
        Long vehicleId,
        String vehicleNumber,
        LocalDate serviceDate,
        String oilBrand,
        Integer odometerKm,
        BigDecimal amountPaid,
        LocalDate nextReminderDate
) {
}