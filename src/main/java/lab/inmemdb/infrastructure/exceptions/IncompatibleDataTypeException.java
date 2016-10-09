package lab.inmemdb.infrastructure.exceptions;

public class IncompatibleDataTypeException extends Exception{
    public IncompatibleDataTypeException(String message) {
        super(message);
    }

    public IncompatibleDataTypeException() {
    }
}
