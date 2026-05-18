package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TherapyProgramTM {
    private Long programId;
    private String programCode;
    private String programName;
    private String duration;
    private BigDecimal fee;
    private String therapistName;
    private String description;
}