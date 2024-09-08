package demo.cohortapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final T data;
    private final ErrorResponse error;

    public ApiResponse(T data) {
        this(data, null);
    }

    public ApiResponse(ErrorResponse error) {
        this(null, error);
    }

    private ApiResponse(T data, ErrorResponse error) {
        this.data = data;
        this.error = error;
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatus status, T data) {
        return ResponseEntity.status(status).body(new ApiResponse<>(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> failure(HttpStatus status, ErrorResponse error) {
        return ResponseEntity.status(status).body(new ApiResponse<>(error));
    }
}

