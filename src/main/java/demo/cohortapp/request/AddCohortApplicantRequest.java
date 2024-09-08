package demo.cohortapp.request;

import demo.cohortapp.enums.CohortStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCohortApplicantRequest {
    private String firstName;
    private String lastName;
    private String fullName;
    private String countryCode;
    private String phoneNumber;
    private String emailId;
    private CohortStatus status;
    private Long cohortId;
}
