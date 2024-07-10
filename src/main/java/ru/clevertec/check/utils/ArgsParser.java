package ru.clevertec.check.utils;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {

	private static String regex = "\\d+";

	private ArgsParser() {

	}

	public static Map<String, String> parseArguments(String[] args) {
		Map<String, String> params = new HashMap<>();
		for (String s : args) {
			String[] str;
			if (s.contains("-")) {
				str = s.split("-");
			} else {
				str = s.split("=");
			}
			String key = str[0];
			String value = str[1];
			if (!params.containsKey(key)) {
				params.put(key, value);
			} else if (key.matches(regex)) {
				int valueFromMap = Integer.parseInt(params.get(key));
				int counter = Integer.valueOf(valueFromMap) + Integer.valueOf(value);
				params.put(key, String.valueOf(counter));
			}
		}
		return params;
	}
}
