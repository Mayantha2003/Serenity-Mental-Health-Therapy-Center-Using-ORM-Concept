package lk.ijse.serenitymentalhealththerapycenter.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "therapy_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"patient", "therapist", "therapyProgram"})
public class TherapySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "session_code", unique = true, length = 20)
    private String sessionCode;

    @Column(name = "session_date", nullable = false)
    private LocalDate sessionDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // SCHEDULED, COMPLETED, CANCELLED, RESCHEDULED, NO_SHOW

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "room_number", length = 20)
    private String roomNumber;

    // Many Sessions -> One Patient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Many Sessions -> One Therapist
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    // Many Sessions -> One Program
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private TherapyProgram therapyProgram;
}