
package com.autoservice.ai.registration;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrationResponse(
        Long customerId,
        Long vehicleId,
        Long serviceRecordId,
        String name,
        String vehicleNumber,
        LocalDate nextReminderDate,
        BigDecimal amountPaid,
        String message
) {}