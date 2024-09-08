package demo.cohortapp.repository;

import demo.cohortapp.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Optional<Cohort> findByName(String name);
}
