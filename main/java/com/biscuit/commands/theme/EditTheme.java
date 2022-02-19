package com.biscuit.commands.theme;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.Epic;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.DateService;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class EditTheme implements Command {
	ConsoleReader reader = null;
	Theme e = new Theme();


	public EditTheme(ConsoleReader reader, Theme e) {
		super();
		this.reader = reader;
		this.e = e;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();

		setName();
		setDescription();

		reader.setPrompt(prompt);

		e.save();

		return true;
	}


	

	


	

	

	private void setDescription() throws IOException {
		StringBuilder description = new StringBuilder();
		String line;
		String prompt = ColorCodes.BLUE + "description: " + ColorCodes.YELLOW + "(\\q to end writing) "
				+ ColorCodes.RESET;
		String preload = e.description.replace("\n", "<newline>").replace("!", "<exclamation-mark>");

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		e.description = description.toString().replace("<newline>", "\n").replace("<exclamation-mark>", "!");
	}


	private void setName() throws IOException {
		String prompt = ColorCodes.BLUE + "name: " + ColorCodes.RESET;
		String preload = e.name;

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		e.name = reader.readLine();
	}


}

