package demo.cohortapp.service.impl;

import demo.cohortapp.exception.CohortAlreadyExistsException;
import demo.cohortapp.exception.CohortNotFoundException;
import demo.cohortapp.dto.CohortDto;
import demo.cohortapp.mapper.CohortMapper;
import demo.cohortapp.model.Cohort;
import demo.cohortapp.repository.CohortRepository;
import demo.cohortapp.service.ICohortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CohortService implements ICohortService {

    private final CohortRepository cohortRepository;
    private final CohortMapper cohortMapper;

    @Override
    public CohortDto findById(Long id) {
        return cohortRepository.findById(id).map(cohortMapper::mapCohortToCohortDto)
                .orElseThrow(() -> new CohortNotFoundException("Cohort not found with id " + id));

    }

    @Override
    public List<CohortDto> findAll() {
        return cohortRepository.findAll().stream().map(cohortMapper::mapCohortToCohortDto).toList();
    }

    @Override
    public CohortDto addCohort(CohortDto cohort) {
      Optional<Cohort> optionalCohort = cohortRepository.findByName(cohort.getName());
      if(optionalCohort.isPresent()) {
          throw new CohortAlreadyExistsException("Cohort already exists with name " + cohort.getName());
      }
        Cohort cohortEntity = cohortMapper.mapCohortDtoToCohort(cohort);
        Cohort savedCohort = cohortRepository.save(cohortEntity);
      return cohortMapper.mapCohortToCohortDto(savedCohort);
    }

    @Override
    public void updateCohort(Long id, Cohort cohort) {

    }

    @Override
    public void deleteCohort(Long id) {
        Cohort cohort = cohortRepository.findById(id)
                .orElseThrow(() -> new CohortNotFoundException("Cohort can not be deleted as not found with id " + id));
        cohortRepository.delete(cohort);


    }
}
