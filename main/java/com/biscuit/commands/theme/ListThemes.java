package com.biscuit.commands.theme;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.services.Finder.Epics;
import com.biscuit.models.services.Finder.Issues;
import com.biscuit.models.services.Finder.Themes;

public class ListThemes implements Command{
	
	Project project = null;
	public ListThemes(Project p)
	{
		super();
		this.project = p;
	}
	@Override
	public boolean execute() throws IOException {
		// TODO Auto-generated method stub
		List<String> i= Themes.getAllNames(project);
		for(String s:i) {
			System.out.println(s);
		}

		return false;
	}

}