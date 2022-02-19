package com.biscuit.commands.theme;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.Epic;
import com.biscuit.models.Project;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;
public class AddTheme implements Command {

	ConsoleReader reader = null;
	Project project = null;
	Theme theme = new Theme();


	public AddTheme(ConsoleReader reader, Project project) {
		super();
		this.reader = reader;
		this.project = project;
	}


	public boolean execute() throws IOException {
		StringBuilder description = new StringBuilder();
		String prompt = reader.getPrompt();

		theme.project = project;
		setName();

		setDescription(description);





		reader.setPrompt(prompt);

		project.addTheme(theme);
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "Epic \"" + theme.name + "\" has been added!" + ColorCodes.RESET);

		return false;
	}


	private void setDescription(StringBuilder description) throws IOException {
		String line;
		reader.setPrompt(ColorCodes.BLUE + "\ndescription:\n" + ColorCodes.YELLOW + "(\\q to end writing)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		epic.description = description.toString();
	}


	private void setName() throws IOException {
		reader.setPrompt(ColorCodes.BLUE + "name: " + ColorCodes.RESET);
		epic.name = reader.readLine();
	}

}

