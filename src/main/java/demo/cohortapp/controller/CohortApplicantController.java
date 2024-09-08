package demo.cohortapp.controller;

import demo.cohortapp.constant.ValidationMessage;
import demo.cohortapp.dto.CohortApplicantDto;
import demo.cohortapp.request.AddCohortApplicantRequest;
import demo.cohortapp.response.ApiResponse;
import demo.cohortapp.service.ICohortApplicantService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cohort-applicants")
public class CohortApplicantController {

    private final ICohortApplicantService cohortApplicantService;

    @GetMapping("/{applicantId}")
    public ResponseEntity<ApiResponse<CohortApplicantDto>> findById(
            @PathVariable @NotNull(message = ValidationMessage.COHORT_APPLICANT_ID_MUST_NOT_NULL) Long applicantId){
        return ApiResponse.success(HttpStatus.OK, cohortApplicantService.findById(applicantId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CohortApplicantDto>>> findAll(){
        return ApiResponse.success(HttpStatus.OK, cohortApplicantService.findAll());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CohortApplicantDto>> addCohortApplicant(
            @RequestBody @Validated AddCohortApplicantRequest addCohortRequest){
        return ApiResponse.success(HttpStatus.OK, cohortApplicantService.addCohortApplicant(addCohortRequest));
    }

}
