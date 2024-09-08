package demo.cohortapp.mapper;

import demo.cohortapp.dto.CohortDto;
import demo.cohortapp.model.Cohort;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CohortMapper {

    CohortDto mapCohortToCohortDto(Cohort cohort);

    Cohort mapCohortDtoToCohort(CohortDto cohortDto);

}
