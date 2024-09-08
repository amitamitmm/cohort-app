package demo.cohortapp.service.impl;

import demo.cohortapp.dto.CohortApplicantDto;
import demo.cohortapp.exception.CohortApplicantNotFoundException;
import demo.cohortapp.exception.CohortNotFoundException;
import demo.cohortapp.mapper.CohortApplicantMapper;
import demo.cohortapp.model.Cohort;
import demo.cohortapp.model.CohortApplicant;
import demo.cohortapp.repository.CohortApplicantRepository;
import demo.cohortapp.repository.CohortRepository;
import demo.cohortapp.request.AddCohortApplicantRequest;
import demo.cohortapp.service.ICohortApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CohortApplicantService implements ICohortApplicantService {

    private final CohortApplicantRepository cohortApplicantRepository;
    private final CohortApplicantMapper cohortApplicantMapper;
    private final CohortRepository cohortRepository;


    @Override
    public CohortApplicantDto findById(Long id) {
        return cohortApplicantRepository.findById(id).map(cohortApplicantMapper::mapCohortApplicantToCohortApplicantDto)
                .orElseThrow(() -> new CohortApplicantNotFoundException("CohortApplicant not found with id " + id));
    }

    @Override
    public List<CohortApplicantDto> findAll() {
        return cohortApplicantRepository.findAll().stream()
                .map(cohortApplicantMapper::mapCohortApplicantToCohortApplicantDto).toList();
    }

    @Override
    public CohortApplicantDto addCohortApplicant(AddCohortApplicantRequest addCohortRequest) {
        CohortApplicant cohortApplicant = cohortApplicantMapper.mapAddCohortRequestToCohortApplicant(addCohortRequest);
        Cohort cohort = cohortRepository.findById(addCohortRequest.getCohortId()).orElseThrow(() ->
                new CohortNotFoundException("Cohort applicant can not be created as given cohortId is invalid : " + addCohortRequest.getCohortId()));
        cohortApplicant.setCohort(cohort);
        CohortApplicant savedCohortApplicant = cohortApplicantRepository.save(cohortApplicant);
        return cohortApplicantMapper.mapCohortApplicantToCohortApplicantDto(savedCohortApplicant);
    }

    @Override
    public void removeCohortApplicant(Long id) {
        CohortApplicant cohortApplicant = cohortApplicantRepository.findById(id).orElseThrow(() ->
                new CohortApplicantNotFoundException("CohortApplicant not found with id " + id));
        cohortApplicantRepository.delete(cohortApplicant);

    }

    @Override
    public void updateCohortApplicant(Long id, CohortApplicant cohortApplicant) {

    }
}
