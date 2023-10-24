package com.example.FinalesProjekt;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Transactional
    public void deleteMeetingsOlderThanFiveDays() {
        LocalDateTime thresholdTime = LocalDateTime.now().minusDays(5);
        List<Meeting> meetingsToDelete = meetingRepository.findByTimeBefore(thresholdTime);
        meetingRepository.deleteAll(meetingsToDelete);
    }
}