package ru.clevertec.check.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {
        super("BAD REQUEST");
    }

}
