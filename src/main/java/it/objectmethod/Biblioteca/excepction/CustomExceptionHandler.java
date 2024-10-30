package it.objectmethod.Biblioteca.excepction;

import it.objectmethod.Biblioteca.excepction.body.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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


}