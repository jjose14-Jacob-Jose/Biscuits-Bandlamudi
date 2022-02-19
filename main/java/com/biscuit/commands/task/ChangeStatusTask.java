package com.biscuit.commands.task;

import java.io.IOException;

import com.biscuit.commands.Command;
import com.biscuit.models.Task;
import com.biscuit.models.enums.Status;

public class ChangeStatusTask implements Command {

	Task t = null;
	Status state = null;
	String customStatus = null;


	public ChangeStatusTask(Task t, Status state) {
		super();
		this.t = t;
		this.state = state;
	}
	
	public ChangeStatusTask(Task t, String state) {
		super();
		this.t = t;
		this.customStatus = state;
	}


	@Override
	public boolean execute() throws IOException {

		Status oldState = t.state;
		
		if(state != null)
			t.state = state;
		else
			t.customStatus = customStatus;

		t.save();

		System.out.print( "State of task " + t.title + " has been changed from " + oldState + " to ");
		System.out.println(state != null ? t.state : t.customStatus);

		return true;
	}

}
