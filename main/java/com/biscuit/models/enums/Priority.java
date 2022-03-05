package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Priority {

	LOW(0), NORMAL(1), HIGH(2);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("high", "normal", "low"));


	private Priority(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
