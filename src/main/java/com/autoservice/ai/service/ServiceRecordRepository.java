
package com.autoservice.ai.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ServiceRecordRepository
        extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByNextReminderDateLessThanEqual(
            LocalDate date
    );

    @Query("""
    SELECT sr
    FROM ServiceRecord sr
    JOIN FETCH sr.vehicle v
    JOIN FETCH v.customer c
    WHERE sr.nextReminderDate <= :today
    """)
    List<ServiceRecord> findDueRecordsWithCustomer(
            @Param("today") LocalDate today
    );
}