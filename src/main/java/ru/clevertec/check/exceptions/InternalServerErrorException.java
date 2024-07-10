package ru.clevertec.check.exceptions;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException() {
        super("INTERNAL SERVER ERROR");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }
}
