package demo.cohortapp.model;

import demo.cohortapp.enums.CohortStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cohort_applicants")
public class CohortApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String countryCode;
    private String phoneNumber;
    private String emailId;
    @Enumerated(EnumType.STRING)
    private CohortStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cohort_id")
    Cohort cohort;
}
