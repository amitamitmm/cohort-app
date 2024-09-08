package demo.cohortapp.exception;

public class CohortAlreadyExistsException extends RuntimeException {
    public CohortAlreadyExistsException(String s) {
        super(s);
    }
}
