package com.biscuit.commands.task;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Task;
import com.biscuit.models.services.DateService;

public class ShowTask implements Command {

	Task t = null;


	public ShowTask(Task t) {
		super();
		this.t = t;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "Title: " + ColorCodes.RESET + t.title);
		System.out.println(ColorCodes.BLUE + "Description: ");
		System.out.println(ColorCodes.RESET + t.description);
		System.out.println(ColorCodes.BLUE + "State: " + ColorCodes.RESET + t.state);
		System.out.println(
				ColorCodes.BLUE + "Initiated date: " + ColorCodes.RESET + DateService.getDateAsString(t.initiatedDate));
		System.out.println(
				ColorCodes.BLUE + "Planned date: " + ColorCodes.RESET + DateService.getDateAsString(t.plannedDate));
		System.out.println(ColorCodes.BLUE + "Due date: " + ColorCodes.RESET + DateService.getDateAsString(t.dueDate));
		System.out.println(ColorCodes.BLUE + "Estimated time: " + ColorCodes.RESET + t.estimatedTime);
		System.out.println(ColorCodes.BLUE + "Happiness: " + ColorCodes.RESET + t.happiness);
		System.out.println();

		return true;
	}

}
