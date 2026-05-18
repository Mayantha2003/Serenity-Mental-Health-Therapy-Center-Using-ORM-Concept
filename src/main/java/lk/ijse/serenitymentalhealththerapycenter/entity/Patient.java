package lk.ijse.serenitymentalhealththerapycenter.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"therapyPrograms", "therapySessions", "payments"})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_code", unique = true, nullable = false, length = 20)
    private String patientCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "nic", unique = true, length = 20)
    private String nic;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "medical_history", length = 1000)
    private String medicalHistory;

    @Column(name = "emergency_contact", length = 100)
    private String emergencyContact;

    @Column(name = "emergency_phone", length = 15)
    private String emergencyPhone;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "status", length = 20)
    private String status = "ACTIVE";

    // Many Patients <-> Many Programs (owning side)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "patient_programs",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<TherapyProgram> therapyPrograms = new ArrayList<>();

    // One Patient -> Many Sessions
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions = new ArrayList<>();

    // One Patient -> Many Payments (FIXED: removed extra <)
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();
}