
package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Type {

	BUG(0), QUESTION(1), IMPEDIMENT(2);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("impediment", "question", "bug"));


	private Type(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
