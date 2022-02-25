package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StatusSprint {

	PLANNED(0), UNPLANNED(1);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("unplanned", "planned"));


	private StatusSprint(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
