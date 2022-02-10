package com.biscuit.models;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;

import jline.console.ConsoleReader;

public class TaigaAuth implements Command{
	
	Dashboard dashboard = Dashboard.getInstance();
	Project project = new Project();
	ConsoleReader reader = null;
	
	public TaigaAuth(ConsoleReader reader) {
		super();
		this.reader = reader;
	}


	public boolean execute() throws IOException {

		String prompt = reader.getPrompt();
		
		reader.setPrompt(ColorCodes.BLUE + "Username/email: " + ColorCodes.RESET);
		String username = reader.readLine();
		reader.setPrompt(ColorCodes.BLUE + "\nPassword: " + ColorCodes.BLACK);
		String password = reader.readLine();
		
		//reader.setPrompt();
		reader.println(ColorCodes.RESET);
		System.out.println(username+"\t"+password);
		

		reader.setPrompt(prompt);
	
		reader.println();
		reader.println(ColorCodes.GREEN + "Connected to Taiga " + ColorCodes.RESET);

		return false;
	}

}
