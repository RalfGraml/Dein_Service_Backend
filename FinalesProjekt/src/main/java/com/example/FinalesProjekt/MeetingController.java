package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/apiGetMeeting")
public class MeetingController {

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/acceptMeeting")
    public List<AcceptedMeetingData> getAcceptedMeetings(@RequestParam("userId") long userId) {
        return showMeetings(userId, "acceptet");
    }

    @PostMapping("/openMeeting")
    public List<AcceptedMeetingData> getOpenMeetings(@RequestParam("userId") long userId) {
        return showMeetings(userId, "open");
    }

    @PostMapping("/declineMeeting")
    public List<AcceptedMeetingData> declineMeetings(@RequestParam("userId") long userId) {
        return showMeetings(userId, "declined");
    }

    @PostMapping("/requestMeeting")
    public ResponseEntity<Map<String, Object>> requestMeeting(
            @RequestParam long userId,
            @RequestParam long companyId,
            @RequestParam("meetingDateTime") LocalDateTime meetingDateTime) {

        Map<String, Object> response = new HashMap<>();

        Meeting newMeeting = new Meeting();

        newMeeting.setCompany(companyId);
        newMeeting.setUser(userId);
        newMeeting.setStatus("open");
        newMeeting.setTime(meetingDateTime);
        meetingRepository.save(newMeeting);

        response.put("success", true);
        response.put("message", "Terminanfrage erfolgreich abgeschickt.");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/acceptMeetingCompany")
    public ResponseEntity<Map<String, Object>> acceptCompanyMeeting(@RequestParam("meetingId") long meetingId) {
        Map<String, Object> response = new HashMap<>();

        Meeting meeting = meetingRepository.findById(meetingId);

        if (meeting != null) {
            meeting.setStatus("acceptet");
            meetingRepository.save(meeting);

            response.put("success", true);
            response.put("message", "Meeting akzeptiert.");
        } else {
            response.put("success", false);
            response.put("message", "Meeting nicht gefunden.");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/declineMeetingCompany")
    public ResponseEntity<Map<String, Object>> declineCompanyMeeting(@RequestParam("meetingId") long meetingId) {
        Map<String, Object> response = new HashMap<>();

        Meeting meeting = meetingRepository.findById(meetingId);

        if (meeting != null) {
            meeting.setStatus("declined");
            meetingRepository.save(meeting);

            response.put("success", true);
            response.put("message", "Meeting abgelehnt.");
        } else {
            response.put("success", false);
            response.put("message", "Meeting nicht gefunden.");
        }

        return ResponseEntity.ok(response);
    }


    @PostMapping("/openMeetingCompany")
    public List<AcceptedMeetingData> getOpenCompanyMeetings(@RequestParam("companyId") long companyId) {
        return showCompany(companyId, "open");
    }


    @PostMapping("/showAllOtherMeetings")
    public List<AcceptedMeetingData> getAllCompanyMeetings(@RequestParam("companyId") long companyId) {
        return showAllCompany(companyId);
    }


    private List<AcceptedMeetingData> showMeetings(long userId, String status) {
        List<AcceptedMeetingData> acceptedMeetingData = new ArrayList<>();
        List<Meeting> meetings = meetingRepository.findByStatusAndUser(status, userId);

        for (Meeting meeting : meetings) {
            Long companyId = meeting.getCompany();
            Company company = companyRepository.findById(companyId).orElse(null);
            User user = userRepository.findById(meeting.getUser());

            if (company != null) {
                AcceptedMeetingData newData = new AcceptedMeetingData();

                LocalDateTime dateAndTime = meeting.getTime();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;

                String formattedDate = formatter.format(dateAndTime);
                String formattedTime = timeFormatter.format(dateAndTime);

                newData.setCompanyAdress(company.getPlace());
                newData.setMeetingId(meeting.getId());
                newData.setCompanyName(company.getName());
                newData.setDate(formattedDate);
                newData.setTime(formattedTime);
                newData.setStatus(meeting.getStatus());
                newData.setUserMail(company.getEmail());
                acceptedMeetingData.add(newData); // Füge das neue Data-Objekt zur Liste hinzu
            }
        }
        return acceptedMeetingData;
    }

    private List<AcceptedMeetingData> showCompany(long companyId, String status) {
        List<AcceptedMeetingData> acceptedMeetingData = new ArrayList<>();
        List<Meeting> meetings = meetingRepository.findByStatusAndCompany(status, companyId);


        for (Meeting meeting : meetings) {
            AcceptedMeetingData newData = new AcceptedMeetingData();
            User user = userRepository.findById(meeting.getUser());

            LocalDateTime dateAndTime = meeting.getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;

            String formattedDate = formatter.format(dateAndTime);
            String formattedTime = timeFormatter.format(dateAndTime);


            newData.setMeetingId(meeting.getId());
            newData.setDate(formattedDate);
            newData.setTime(formattedTime);
            newData.setStatus(meeting.getStatus());
            newData.setUserMail(user.getEmail());
            acceptedMeetingData.add(newData);
        }

        return acceptedMeetingData;

    }

    private List<AcceptedMeetingData> showAllCompany(long companyId) {
        List<AcceptedMeetingData> acceptedMeetingData = new ArrayList<>();
        List<Meeting> meetings = meetingRepository.findByCompany(companyId);

        for (Meeting meeting : meetings) {
            User user = userRepository.findById(meeting.getUser());
            AcceptedMeetingData newData = new AcceptedMeetingData();

            LocalDateTime dateAndTime = meeting.getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;

            String formattedDate = formatter.format(dateAndTime);
            String formattedTime = timeFormatter.format(dateAndTime);

            newData.setUserMail(user.getEmail());
            newData.setMeetingId(meeting.getId());
            newData.setDate(formattedDate);
            newData.setTime(formattedTime);
            newData.setStatus(meeting.getStatus());
            acceptedMeetingData.add(newData); // Füge das neue Data-Objekt zur Liste hinzu

        }
        return acceptedMeetingData;
    }
}


