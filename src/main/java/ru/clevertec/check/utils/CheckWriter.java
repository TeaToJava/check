package ru.clevertec.check.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import ru.clevertec.check.exceptions.InternalServerErrorException;

public class CheckWriter {
    private static final String ERROR_FILE = "./error_result.csv";

	private CheckWriter() {

	}

	public static void writeToCheck(String fileName, String order) throws InternalServerErrorException {
        try (PrintWriter pw = new PrintWriter(fileName);) {
            pw.println(order);
        } catch (FileNotFoundException e) {
        	throw new InternalServerErrorException();
        }
    }

    public static void writeToConsole(String order) {
        System.out.println(order);
    }

    public static void writeErrorToCheck(String message) throws InternalServerErrorException {
        try (PrintWriter pw = new PrintWriter(ERROR_FILE);) {
            pw.println("ERROR");
            pw.println(message);
        } catch (FileNotFoundException e) {
        	throw new InternalServerErrorException();
        }
    }
}
