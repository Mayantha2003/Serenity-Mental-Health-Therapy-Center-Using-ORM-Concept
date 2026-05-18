package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientTM {
    private Long patientId;
    private String patientCode;
    private String fullName;
    private String nic;
    private String phone;
    private String program;
    private String registrationDate;
    private String status;
}