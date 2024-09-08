package demo.cohortapp.dto;

import demo.cohortapp.enums.CohortStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CohortApplicantDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String countryCode;
    private String phoneNumber;
    private String emailId;
    private CohortStatus status;
    private CohortDto cohortDto;
}
