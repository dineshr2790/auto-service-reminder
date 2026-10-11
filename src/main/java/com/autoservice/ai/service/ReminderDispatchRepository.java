
package com.autoservice.ai.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderDispatchRepository
        extends JpaRepository<ReminderDispatch, Long> {

    boolean existsByServiceRecord_Id(Long serviceRecordId);
}