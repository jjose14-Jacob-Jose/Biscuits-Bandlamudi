package com.biscuit.commands.userStory;



import java.io.IOException;
import java.util.Date;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Epic;
import com.biscuit.models.Sprint;
import com.biscuit.models.Theme;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.BusinessValue;
import com.biscuit.models.enums.Points;
import com.biscuit.models.enums.Status;

import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class AddUserStoryToTheme implements Command {

	ConsoleReader reader = null;
	Theme theme = null;
	UserStory userStory = new UserStory();


	public AddUserStoryToTheme(ConsoleReader reader, Theme theme) {
		super();
		this.reader = reader;
		this.theme = theme;
	}


	public boolean execute() throws IOException {
		StringBuilder description = new StringBuilder();
		String prompt = reader.getPrompt();

		userStory.project = theme.project;
		setTitle();

		setDescription(description);

		reader.setPrompt(prompt);
		theme.addUserStory(userStory);
		
		theme.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "User Story \"" + userStory.title + "\" has been added to theme " + theme.name + "!" + ColorCodes.RESET);

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

		userStory.description = description.toString();
	}


	private void setTitle() throws IOException {
		reader.setPrompt(ColorCodes.BLUE + "title: " + ColorCodes.RESET);
		userStory.title = reader.readLine();
	}

}
