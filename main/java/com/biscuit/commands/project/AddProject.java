package com.biscuit.commands.project;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Dashboard;

import jline.console.ConsoleReader;

public class AddProject implements Command {

	Dashboard dashboard = Dashboard.getInstance();
	Project project = new Project();
	ConsoleReader reader = null;


	public AddProject(ConsoleReader reader) {
		super();
		this.reader = reader;
	}


	public boolean execute() throws IOException {

		StringBuilder description = new StringBuilder();
		StringBuilder team_members = new StringBuilder();
		String line,new_line,lines;
		boolean yes = false;
		String prompt = reader.getPrompt();
		
		project.backlog.project = project;
		
		reader.setPrompt(ColorCodes.BLUE + "project name: " + ColorCodes.RESET);
		project.name = reader.readLine();
		reader.setPrompt(ColorCodes.BLUE + "\ndescription: " + ColorCodes.YELLOW + "\n(\\q to end writing)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		project.description = description.toString();
		
		reader.setPrompt(ColorCodes.BLUE + " Do you want to add team members" + "? [Y/n] " + ColorCodes.RESET);
		new_line = reader.readLine();

		yes = (new_line.toLowerCase().equals("y"));

		
		if (yes) {
			
			reader.setPrompt(ColorCodes.GREEN + "\nType names: " + ColorCodes.YELLOW + "\n(\\q to end writing)\n" + ColorCodes.RESET);

			while ((lines = reader.readLine()) != null) {
				if (lines.equals("\\q")) {
					break;
				}
				team_members.append(lines).append("\n");
				reader.setPrompt("");
			}
		}
		else {
			System.out.println(ColorCodes.GREEN+"Okay"+ ColorCodes.RESET);
		}
		project.team_members = team_members.toString();
		
		reader.setPrompt(ColorCodes.GREEN + "\ngithub url: " + ColorCodes.RESET);
		
		project.github= reader.readLine();
		
		

		reader.setPrompt(prompt);

		dashboard.addProject(project);

		dashboard.save();
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "Project \"" + project.name + "\" has been added!" + ColorCodes.RESET);

		return false;
	}

}
