package demo.cohortapp.repository;

import demo.cohortapp.model.CohortApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortApplicantRepository extends JpaRepository<CohortApplicant, Long> {
}
