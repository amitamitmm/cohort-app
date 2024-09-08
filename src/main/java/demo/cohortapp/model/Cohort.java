package demo.cohortapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cohorts")
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 1000)
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cohort")
    private List<CohortApplicant> cohortApplicants;
}
