
package com.autoservice.ai.service;

import com.autoservice.ai.customer.Customer;
import com.autoservice.ai.vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderProcessor {

    private static final Logger log =
            LoggerFactory.getLogger(ReminderProcessor.class);

    private final ServiceRecordRepository serviceRecordRepository;
    private final ReminderDispatchRepository dispatchRepository;

    public ReminderProcessor(
            ServiceRecordRepository serviceRecordRepository,
            ReminderDispatchRepository dispatchRepository
    ) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.dispatchRepository = dispatchRepository;
    }

    @Transactional
    public int processDueReminders() {
        LocalDate today = LocalDate.now();

        List<ServiceRecord> dueRecords =
                serviceRecordRepository.findDueRecordsWithCustomer(today);

        int processed = 0;

        for (ServiceRecord record : dueRecords) {
            if (dispatchRepository.existsByServiceRecord_Id(record.getId())) {
                continue;
            }

            Vehicle vehicle = record.getVehicle();
            Customer customer = vehicle.getCustomer();

            if (!customer.isReminderConsent()) {
                log.info(
                        "Skipping reminder for serviceRecordId={} because consent is absent",
                        record.getId()
                );
                continue;
            }

            String message = String.format(
                    "Hello %s! Your vehicle %s had an oil service with %s " +
                    "on %s. Your next service reminder date is %s. " +
                    "Please follow your vehicle manufacturer's service recommendations. " +
                    "Contact our shop if you need assistance.",
                    customer.getName(),
                    vehicle.getVehicleNumber(),
                    record.getOilBrand(),
                    record.getServiceDate(),
                    record.getNextReminderDate()
            );

            ReminderDispatch dispatch = new ReminderDispatch(
                    record,
                    "SIMULATED",
                    message
            );

            dispatchRepository.save(dispatch);

            log.info(
                    "SIMULATED REMINDER | serviceRecordId={} | customer={} | message={}",
                    record.getId(),
                    customer.getName(),
                    message
            );

            processed++;
        }

        log.info("Reminder run completed. Simulated reminders: {}", processed);

        return processed;
    }
}