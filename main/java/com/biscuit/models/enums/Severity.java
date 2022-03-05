package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Severity {

	WISHLIST(0), MINOR(1), NORMAL(2), IMPORTANT(3), CRITICAL(4);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("critical", "important", "normal", "minor", "wishlist"));


	private Severity(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
