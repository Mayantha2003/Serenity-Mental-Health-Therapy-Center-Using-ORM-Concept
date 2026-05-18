package lk.ijse.serenitymentalhealththerapycenter.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"patient", "therapyProgram"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "invoice_number", unique = true, nullable = false, length = 20)
    private String invoiceNumber;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_method", length = 20)
    private String paymentMethod; // CASH, CARD, BANK_TRANSFER, ONLINE

    @Column(name = "status", nullable = false, length = 20)
    private String status; // PENDING, COMPLETED, FAILED, REFUNDED

    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "processed_by", length = 50)
    private String processedBy;

    // Many Payments -> One Patient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Many Payments -> One Program
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;
}