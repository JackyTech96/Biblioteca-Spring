package it.objectmethod.Biblioteca.excepction.body;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Jacksonized
@Data
@NoArgsConstructor
@Builder
public class ErrorBody {

    private String message;
    private String description;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private Map<String, String> errors;

    public ErrorBody(String message, String description, LocalDateTime timestamp, HttpStatus httpStatus) {
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }

    public ErrorBody(String message, String description, LocalDateTime timestamp, HttpStatus httpStatus, Map<String, String> errors) {
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

}
