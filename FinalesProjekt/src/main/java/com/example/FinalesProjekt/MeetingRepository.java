package com.example.FinalesProjekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByStatus(String status);


    List<Meeting> findByStatusAndUser(String status, long user);

    List<Meeting> findByStatusAndCompany(String status, long companyId);

    Meeting findById(long meetingId);

    List<Meeting> findByCompany(long companyId);


    List<Meeting> findByTimeBefore(LocalDateTime thresholdTime);

    @Modifying
    @Query("DELETE FROM Meeting m WHERE m.time <= :thresholdTime")
    void deleteMeetingsBeforeTime(@Param("thresholdTime") LocalDateTime thresholdTime);
}

