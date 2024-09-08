package demo.cohortapp.mapper;

import demo.cohortapp.dto.CohortApplicantDto;
import demo.cohortapp.model.CohortApplicant;
import demo.cohortapp.request.AddCohortApplicantRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CohortApplicantMapper {

    @Mapping(source = "status", target = "status")
    @Mapping(source = "cohort", target = "cohortDto")
    CohortApplicantDto mapCohortApplicantToCohortApplicantDto(CohortApplicant cohortApplicant);


    @Mapping(source = "cohortId", target = "cohort.id")
    @Mapping(source = "status", target = "status")
    CohortApplicant mapAddCohortRequestToCohortApplicant(AddCohortApplicantRequest addCohortRequest);
}
