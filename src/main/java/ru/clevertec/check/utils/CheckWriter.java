package ru.clevertec.check.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import ru.clevertec.check.exceptions.InternalServerErrorException;

public class CheckWriter {
	private static final String CHECK_FILE = "./result.csv";

	private CheckWriter() {

	}

	public static void writeToCheck(String order) throws InternalServerErrorException {
		try (PrintWriter pw = new PrintWriter(CHECK_FILE);) {
			pw.println(order);
		} catch (FileNotFoundException e) {
			throw new InternalServerErrorException();
		}
	}

	public static void writeToConsole(String order) {
		System.out.println(order);
	}

	public static void writeErrorToCheck(String message) throws InternalServerErrorException {
		try (PrintWriter pw = new PrintWriter(CHECK_FILE);) {
			pw.println("ERROR");
			pw.println(message);
		} catch (FileNotFoundException e) {
			throw new InternalServerErrorException();
		}
	}
}
