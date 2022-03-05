package com.biscuit.commands.issue;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Epic;
import com.biscuit.models.Issue;
import com.biscuit.models.Sprint;
import com.biscuit.models.services.DateService;

public class ShowIssue implements Command {
	Issue e = null;


	public ShowIssue(Issue e) {
		super();
		this.e = e;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "Name: " + ColorCodes.RESET + e.name);
		System.out.println(ColorCodes.BLUE + "Description: ");
		System.out.println(ColorCodes.RESET + e.description);
		System.out.println(ColorCodes.BLUE + "Type: " + ColorCodes.RESET + e.type);
		System.out.println(ColorCodes.BLUE + "Severity: " + ColorCodes.RESET + e.severity);
		System.out.println(ColorCodes.BLUE + "Priority: " + ColorCodes.RESET + e.priority);

		System.out.println();

		return true;
	}
}
