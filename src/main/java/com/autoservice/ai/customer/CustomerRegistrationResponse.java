package com.autoservice.ai.customer;

public record CustomerRegistrationResponse(
        Long customerId,
        Long vehicleId,
        String name,
        String vehicleNumber,
        String message
) {}