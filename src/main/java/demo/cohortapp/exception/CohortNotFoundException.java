package demo.cohortapp.exception;

public class CohortNotFoundException extends RuntimeException {
    public CohortNotFoundException(String s) {
        super(s);
    }
}
