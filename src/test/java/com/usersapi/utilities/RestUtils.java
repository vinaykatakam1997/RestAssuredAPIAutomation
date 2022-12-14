package com.usersapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String userName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("morpheus" + generatedString);
	}

	public static String userSal() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}

	public static String userAge() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}
}
