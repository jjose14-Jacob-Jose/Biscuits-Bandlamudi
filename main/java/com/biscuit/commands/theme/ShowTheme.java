package com.biscuit.commands.theme;
import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Epic;
import com.biscuit.models.Sprint;
import com.biscuit.models.Theme;
import com.biscuit.models.services.DateService;
public class ShowTheme implements Command {
	Theme e = null;


	public ShowTheme(Theme e) {
		super();
		this.e = e;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "Name: " + ColorCodes.RESET + e.name);
		System.out.println(ColorCodes.BLUE + "Description: ");
		System.out.println(ColorCodes.RESET + e.description);


		System.out.println();

		return true;
	}
}

