package com.biscuit.commands.project;

import java.io.IOException;

import com.biscuit.commands.Command;
import com.biscuit.models.Project;

public class ShowProject implements Command {

	Project p = null;


	public ShowProject(Project p) {
		super();
		this.p = p;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println( "Title: " + p.name);
		System.out.println("Description: \n"+ p.description);
		System.out.println("Git URL: \n"+ p.gitURL);

		return true;
	}

}
