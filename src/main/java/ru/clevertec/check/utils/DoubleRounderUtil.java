package ru.clevertec.check.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DoubleRounderUtil {

	private DoubleRounderUtil() {

	}

	public static double roundDouble(double number) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.##", symbols);
		df.setRoundingMode(RoundingMode.CEILING);
		return Double.valueOf(df.format(number));
	}
}