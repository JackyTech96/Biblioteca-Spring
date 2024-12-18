package it.objectmethod.Biblioteca.excepction;

import it.objectmethod.Biblioteca.excepction.body.ErrorBody;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorBody> handleElementNotFoundException(ElementNotFoundException ex) {
        ErrorBody errorBody = ErrorBody.builder()
                .message(ElementNotFoundException.class.getSimpleName())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ErrorBody> handleOperationNotAllowedException(OperationNotAllowedException ex) {
        ErrorBody errorBody = ErrorBody.builder()
                .message(OperationNotAllowedException.class.getSimpleName())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> handleConstraintViolationException(MethodArgumentNotValidException ex) {

        String errorMessage = "Errore di validazione dati";

        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
        ErrorBody errorBody = ErrorBody.builder()
                .message(ConstraintViolationException.class.getSimpleName())
                .description(errorMessage)
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errors(fieldErrors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorBody> handleLoginFailedException(LoginFailedException ex) {
        ErrorBody errorBody = ErrorBody.builder()
                .message(LoginFailedException.class.getSimpleName())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
    }
}
