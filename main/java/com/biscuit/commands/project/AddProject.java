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
			
			reader.setPrompt(ColorCodes.GREEN + "\nType name of the member: " + ColorCodes.YELLOW + "\n(\\q to end writing)\n" + ColorCodes.RESET);

			while ((lines = reader.readLine()) != null) {
				team_members.append(lines);
				if (lines.equals("\\q")) {
					break;
				}
				else {
					reader.setPrompt(ColorCodes.BLUE + " Do you want to add role of the member:" + "? [Y/n] " + ColorCodes.RESET);
					line = reader.readLine();

					yes = (line.toLowerCase().equals("y"));
					
					System.out.println(ColorCodes.YELLOW + "There are three roles available as described below:"+ ColorCodes.RESET);
					System.out.println(ColorCodes.GREEN + "1)scrum master(There can be only one scrum master)." + ColorCodes.RESET);
					System.out.println(ColorCodes.GREEN + "2)product owner(There can be only one product owner)." + ColorCodes.RESET);
					System.out.println(ColorCodes.GREEN + "3)developer(As many as you want)." + ColorCodes.RESET);
					if(yes) {
						
						reader.setPrompt(ColorCodes.GREEN + "\nType role: " + ColorCodes.RESET);
						lines=reader.readLine();
						
						if(lines.toLowerCase().equals("scrum master")) {
							team_members.append(" is a Scrum master ");
						}
						else if (lines.toLowerCase().equals("product owner")) {
							team_members.append(" is a Product owner ");
						}
						else if (lines.toLowerCase().equals("developer")) {
							team_members.append(" is a Developer" );
						}
						else
						{
							System.out.println(ColorCodes.GREEN + "Type valid role:" + ColorCodes.RESET);
						}
						//team_members.append(" is a "+lines);
						
					}
					else {
						System.out.println(ColorCodes.GREEN +"Okay you can add it later."+ ColorCodes.RESET);
					}
				}
				team_members.append("\n");
				reader.setPrompt("");
			}
		}
		else {
			System.out.println(ColorCodes.GREEN+"Okay you can add it later."+ ColorCodes.RESET);
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
