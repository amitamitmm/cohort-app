package demo.cohortapp.dto;

import demo.cohortapp.constant.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CohortDto {

    @NotEmpty(message = ValidationMessage.COHORT_NAME_MUST_NOT_BE_EMPTY)
    private String name;

    @NotEmpty(message = ValidationMessage.COHORT_DESC_MUST_NOT_BE_EMPTY)
    private String description;

    @NotNull(message = ValidationMessage.COHORT_START_DATE_MUST_NOT_BE_NULL)
    private LocalDateTime startDate;

    @NotNull(message = ValidationMessage.COHORT_END_DATE_MUST_NOT_BE_NULL)
    private LocalDateTime endDate;
}
