package it.objectmethod.Biblioteca.excepction.body;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Jacksonized
@Data
@NoArgsConstructor
@Builder
public class ErrorBody {

    private String message;
    private String description;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;

    public ErrorBody(String message, String description, LocalDateTime timestamp, HttpStatus httpStatus) {
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }
}
