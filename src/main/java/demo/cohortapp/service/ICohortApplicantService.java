package demo.cohortapp.service;

import demo.cohortapp.dto.CohortApplicantDto;
import demo.cohortapp.model.CohortApplicant;
import demo.cohortapp.request.AddCohortApplicantRequest;

import java.util.List;

public interface ICohortApplicantService {

    CohortApplicantDto findById(Long id);
    List<CohortApplicantDto> findAll();
    CohortApplicantDto addCohortApplicant(AddCohortApplicantRequest addCohortRequest);
    void removeCohortApplicant(Long id);
    void updateCohortApplicant(Long id, CohortApplicant cohortApplicant);

}
