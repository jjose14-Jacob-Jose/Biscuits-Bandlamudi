package com.biscuit.commands.project;

import java.io.IOException;

import com.biscuit.ColorCodes;
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
		System.out.println(ColorCodes.BLUE + "Github url: ");
		System.out.println(ColorCodes.RESET + p.github);
		System.out.println(ColorCodes.BLUE + "Team members: ");
		System.out.println(ColorCodes.RESET + p.team_members);
		System.out.println();

		return true;
	}

}
