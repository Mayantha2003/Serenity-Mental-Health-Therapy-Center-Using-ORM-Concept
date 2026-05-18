package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentTM {
    private Long paymentId;
    private String invoiceNumber;
    private String patientName;
    private String programName;
    private BigDecimal amount;
    private String paymentDate;
    private String paymentMethod;
    private String status;
}