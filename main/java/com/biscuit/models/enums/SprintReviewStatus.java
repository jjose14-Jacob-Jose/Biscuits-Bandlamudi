package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SprintReviewStatus {

	COMPLETED(0), NOT_COMPLETED(1);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("not_completed", "completed"));


	private SprintReviewStatus(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
