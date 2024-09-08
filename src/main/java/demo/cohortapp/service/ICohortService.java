package demo.cohortapp.service;

import demo.cohortapp.dto.CohortDto;
import demo.cohortapp.model.Cohort;

import java.util.List;

public interface ICohortService {
    CohortDto findById(Long id);
    List<CohortDto> findAll();
    CohortDto addCohort(CohortDto cohortDto);
    void updateCohort(Long id, Cohort cohort);
    void deleteCohort(Long id);
}
