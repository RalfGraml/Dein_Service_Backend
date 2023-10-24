package com.example.FinalesProjekt;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class AcceptedMeetingData {

   private long meetingId;


    private String date;
    private String time;
    private String companyName;
    private String userMail;
    private String status;
    private String companyAdress;






}