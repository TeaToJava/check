package ru.clevertec.check.exceptions;

public class InternalServerErrorException extends RuntimeException {
	public InternalServerErrorException() {
		super("INTERNAL SERVER ERROR");
	}

}
