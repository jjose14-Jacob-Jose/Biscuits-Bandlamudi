package com.biscuit.commands.epic;


import java.io.IOException;
import java.util.List;

import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.services.Finder.Epics;
import com.biscuit.models.services.Finder.Issues;

public class ListEpics implements Command{
	
	Project project = null;
	public ListEpics(Project p)
	{
		super();
		this.project = p;
	}
	@Override
	public boolean execute() throws IOException {
		// TODO Auto-generated method stub
		List<String> i= Epics.getAllNames(project);
		for(String s:i) {
			System.out.println(s);
		}

		return false;
	}

}