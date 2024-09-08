package demo.cohortapp.exception;

import demo.cohortapp.response.ApiResponse;
import demo.cohortapp.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleCohortNotFoundException(HttpServletRequest request, Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getServletPath())
                .build();
        return ApiResponse.failure(HttpStatus.NOT_FOUND, errorResponse);
    }


    @ExceptionHandler(CohortAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleCohortAlreadyExistsException(HttpServletRequest request, Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getServletPath())
                .build();
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message("Validation failed")
                .details(errors)
                .timestamp(LocalDateTime.now())
                .path(request.getServletPath())
                .build();
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, errorResponse);
    }

}
