package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class ThemesHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Themes Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();
		at.addRow("Themes", "List all Themes").setAlignment(new char[] { 'l', 'l' });
		at.addRow("back", "Go back to previous view (Project)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to theme", "Go to a theme view (followed by a theme name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add theme", "Add new theme").setAlignment(new char[] { 'l', 'l' });
	}

}

