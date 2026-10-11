
package com.autoservice.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReminderScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(ReminderScheduler.class);

    private final ReminderProcessor reminderProcessor;

    public ReminderScheduler(ReminderProcessor reminderProcessor) {
        this.reminderProcessor = reminderProcessor;
    }

    @Scheduled(
        cron = "${app.reminders.cron:0 0 9 * * *}",
        zone = "${app.reminders.zone:Asia/Kolkata}"
    )
    public void runScheduledReminders() {
        log.info("Starting scheduled reminder processing");
        reminderProcessor.processDueReminders();
    }
}