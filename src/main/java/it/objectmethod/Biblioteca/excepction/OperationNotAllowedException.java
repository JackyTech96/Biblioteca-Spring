package it.objectmethod.Biblioteca.excepction;

public class OperationNotAllowedException extends RuntimeException {

    public OperationNotAllowedException(String message) {
        super(message);
    }
}
