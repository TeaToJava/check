package ru.clevertec.check.utils;

import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;

import java.util.Map;
import java.util.Set;

public class Validator {
	private static Set<String> commandLineParameters = Set.of(ArgsConstant.BALANCE_DEBIT_CARD,
			ArgsConstant.DISCOUNT_CARD);

	private Validator() {

	}

	public static void validateInput(Map<String, String> params) throws BadRequestException {
		Set<String> paramsSet = params.keySet();
		for (String param : commandLineParameters) {
			if (!paramsSet.contains(param)) {
				throw new BadRequestException();
			}
		}
	}

	public static void validateBalance(double totalSum, double balance) throws NotEnoughMoneyException {
		if (balance <= 0 || totalSum > balance) {
			throw new NotEnoughMoneyException();
		}
	}
}
