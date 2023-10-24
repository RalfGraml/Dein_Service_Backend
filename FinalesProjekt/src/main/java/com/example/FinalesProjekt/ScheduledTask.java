package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    @Autowired
    private MeetingService meetingService;

        @Scheduled(cron = "0 0 0 15 * ?") // Diese Konfiguration führt die Methode am 15. Tag jeden Monats um Mitternacht aus.Sie löscht alle Meetings die älter als 5 Tage sind
        public void deleteOldMeetings() {
            meetingService.deleteMeetingsOlderThanFiveDays();
        }

//    @Scheduled(fixedRate = 5000) // Diese Konfiguration führt die Methode alle 5 Sekunden aus.
//    public void printAllFiveSeconds() {
//        System.out.println("Hallo");
//    }

    }