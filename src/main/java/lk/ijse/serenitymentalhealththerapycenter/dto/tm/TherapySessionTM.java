package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TherapySessionTM {
    private Long sessionId;
    private String sessionCode;
    private String sessionDate;
    private String patientName;
    private String therapistName;
    private String programName;
    private String time;
    private String roomNumber;
    private String status;
}