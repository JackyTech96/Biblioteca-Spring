package it.objectmethod.Biblioteca.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse <T> {
    private String message;
    private T data;
}
