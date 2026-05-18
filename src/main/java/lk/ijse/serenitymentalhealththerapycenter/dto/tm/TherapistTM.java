package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TherapistTM {
    private Long therapistId;
    private String fullName;
    private String specialization;
    private String phone;
    private String email;
    private String licenseNumber;
    private String availability;
}