package com.biscuit.commands.issue;



import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.Epic;
import com.biscuit.models.Issue;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.DateService;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class EditIssue implements Command {
	ConsoleReader reader = null;
	Issue i = new Issue();


	public EditIssue(ConsoleReader reader, Issue i) {
		super();
		this.reader = reader;
		this.i = i;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();

		setName();
		setDescription();

		reader.setPrompt(prompt);

		i.save();

		return true;
	}


	

	


	

	

	private void setDescription() throws IOException {
		StringBuilder description = new StringBuilder();
		String line;
		String prompt = ColorCodes.BLUE + "description: " + ColorCodes.YELLOW + "(\\q to end writing) "
				+ ColorCodes.RESET;
		String preload = i.description.replace("\n", "<newline>").replace("!", "<exclamation-mark>");

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		i.description = description.toString().replace("<newline>", "\n").replace("<exclamation-mark>", "!");
	}


	private void setName() throws IOException {
		String prompt = ColorCodes.BLUE + "name: " + ColorCodes.RESET;
		String preload = i.name;

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		i.name = reader.readLine();
	}


}

