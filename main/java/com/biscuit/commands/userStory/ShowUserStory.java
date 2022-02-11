package com.biscuit.commands.userStory;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.DateService;

public class ShowUserStory implements Command {

	UserStory us = null;


	public ShowUserStory(UserStory us) {
		super();
		this.us = us;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "Title: " + ColorCodes.RESET + us.title);
		System.out.println(ColorCodes.BLUE + "Description: ");
		System.out.println(ColorCodes.RESET + us.description);
		System.out.println(ColorCodes.BLUE + "State: " + ColorCodes.RESET + us.state);
		System.out.println(ColorCodes.BLUE + "Business value: " + ColorCodes.RESET + us.businessValue);
		System.out.println(ColorCodes.BLUE + "Initiated date: " + ColorCodes.RESET
				+ DateService.getDateAsString(us.initiatedDate));
		System.out.println(
				ColorCodes.BLUE + "Planned date: " + ColorCodes.RESET + DateService.getDateAsString(us.plannedDate));
		System.out.println(ColorCodes.BLUE + "Due date: " + ColorCodes.RESET + DateService.getDateAsString(us.dueDate));
		System.out.println(ColorCodes.BLUE + "Points: " + ColorCodes.RESET + us.points);
		System.out.println();
		
		return true;
	}

}
