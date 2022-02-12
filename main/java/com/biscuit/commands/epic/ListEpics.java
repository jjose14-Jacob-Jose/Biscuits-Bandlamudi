package com.biscuit.commands.epic;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Epic;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ListEpics implements Command {

	Project project = null;
	Release release = null;
	String title = "";
	boolean isFilter = false;
	boolean isSort = false;
	static boolean isReverse = false;
	private String filterBy;
	private String sortBy;
	private static String lastSortBy = "";


	public ListEpics(Project project, String title) {
		super();
		this.project = project;
		this.title = title;
	}
	
	@Override
	public boolean execute() throws IOException {

		V2_AsciiTable at = new V2_AsciiTable();
		String tableString;

		List<Epic> epics = new ArrayList<>();

		if (project != null) {
			epics.addAll(project.epics);
		}


		at.addRule();
		if (!this.title.isEmpty()) {
			at.addRow(null, this.title).setAlignment(new char[] { 'c', 'c' });
			at.addRule();
		}
		at.addRow("Name", "Description")
				.setAlignment(new char[] { 'l', 'l'});

		if (epics.size() == 0) {
			String message;
			if (!isFilter) {
				message = "There are no epics!";
			} else {
				message = "No results";
			}
			at.addRule();
			at.addRow(null, message);
		} else {
			for (Epic s : epics) {
				at.addRule();

				at.addRow(s.name, s.description).setAlignment(new char[] { 'l', 'l'});
			} // for
		}

		at.addRule();
		at.addRow(null, null, "Total: " + epics.size());
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
		rend.setWidth(new WidthLongestLine());

		RenderedTable rt = rend.render(at);
		tableString = rt.toString();


		System.out.println();
		System.out.println(tableString);

		return false;
	}
}