package lk.ijse.serenitymentalhealththerapycenter.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "therapists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"therapySessions", "therapyPrograms"})
public class Therapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_id")
    private Long therapistId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "specialization", length = 100)
    private String specialization;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "license_number", unique = true, length = 50)
    private String licenseNumber;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "availability", length = 100)
    private String availability;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // One Therapist -> Many Therapy Sessions
    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions = new ArrayList<>();

    // Many Therapists <-> Many Therapy Programs
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "therapist_programs",
            joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<TherapyProgram> therapyPrograms = new ArrayList<>();
}