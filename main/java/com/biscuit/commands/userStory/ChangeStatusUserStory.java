package com.biscuit.commands.userStory;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;

public class ChangeStatusUserStory implements Command {

	UserStory us = null;
	Status state = null;
	String customStatus = null;

	public ChangeStatusUserStory(UserStory us, Status state) {
		super();
		this.us = us;
		this.state = state;
	}
	
	public ChangeStatusUserStory(UserStory us, String state) {
		super();
		this.us = us;
		this.customStatus = state;
	}


	@Override
	public boolean execute() throws IOException {

		Status oldState = us.state;
		
		if(state != null)
			us.state = state;
		else
			us.customeStatus = customStatus;

		us.save();

		System.out.println("State of user story " + us.title + " has been changed from " + oldState+ " to " );
		System.out.println(state != null ? us.state : us.customeStatus + ColorCodes.RED);

		return true;
	}

}
