package demo.cohortapp.controller;

import demo.cohortapp.constant.Constants;
import demo.cohortapp.constant.ValidationMessage;
import demo.cohortapp.dto.CohortDto;
import demo.cohortapp.response.ApiResponse;
import demo.cohortapp.service.ICohortService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cohorts")
public class CohortController {

    private final ICohortService cohortService;

    @GetMapping("/{cohortId}")
    public ResponseEntity<ApiResponse<CohortDto>> findById(
            @PathVariable @NotNull(message = ValidationMessage.COHORT_ID_MUST_NOT_NULL) Long cohortId){
        return ApiResponse.success(HttpStatus.OK, cohortService.findById(cohortId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CohortDto>>> findAll(){
        return ApiResponse.success(HttpStatus.OK, cohortService.findAll());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CohortDto>> addCohort(@RequestBody @Validated CohortDto cohort){
        return ApiResponse.success(HttpStatus.OK, cohortService.addCohort(cohort));
    }

    @DeleteMapping("/{cohortId}")
    public ResponseEntity<ApiResponse<String>> removeCohort(
            @PathVariable @NotNull(message = ValidationMessage.COHORT_ID_MUST_NOT_NULL) Long cohortId){
        cohortService.deleteCohort(cohortId);
        return ApiResponse.success(HttpStatus.OK, Constants.COHORT_DELETED_SUCCESS);

    }

}
