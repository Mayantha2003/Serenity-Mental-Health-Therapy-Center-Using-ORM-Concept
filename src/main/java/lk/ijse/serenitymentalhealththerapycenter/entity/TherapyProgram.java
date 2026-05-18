package lk.ijse.serenitymentalhealththerapycenter.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "therapy_programs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"enrolledPatients", "assignedTherapists", "therapySessions"})
public class TherapyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long programId;

    @Column(name = "program_code", unique = true, nullable = false, length = 20)
    private String programCode;

    @Column(name = "program_name", nullable = false, length = 100)
    private String programName;

    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @Column(name = "fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal fee;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // Many Patients <-> Many Programs (inverse side)
    @ManyToMany(mappedBy = "therapyPrograms", fetch = FetchType.LAZY)
    private List<Patient> enrolledPatients = new ArrayList<>();  // FIXED

    // Many Therapists <-> Many Programs (inverse side)
    @ManyToMany(mappedBy = "therapyPrograms", fetch = FetchType.LAZY)
    private List<Therapist> assignedTherapists = new ArrayList<>();

    // One Program -> Many Sessions
    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions = new ArrayList<>();
}