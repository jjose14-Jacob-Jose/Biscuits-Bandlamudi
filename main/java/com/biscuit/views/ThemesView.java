package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.epic.AddEpic;
import com.biscuit.commands.help.SprintsHelp;
import com.biscuit.commands.sprint.AddSprint;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.commands.theme.AddTheme;
import com.biscuit.factories.EpicsCompleterFactory;
import com.biscuit.factories.SprintsCompleterFactory;
import com.biscuit.factories.ThemesCompleterFactory;
import com.biscuit.models.Epic;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.Theme;
import com.biscuit.models.services.Finder.Epics;
import com.biscuit.models.services.Finder.Sprints;
import com.biscuit.models.services.Finder.Themes;

import jline.console.completer.Completer;

public class ThemesView extends View {

	Project project = null;


	public ThemesView(View previousView, Project p) {
		super(previousView, "themes");
		this.project = p;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ThemesCompleterFactory.getThemesCompleters(project));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}




	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("theme")) {
				(new AddTheme(reader, project)).execute();
				resetCompleters();

				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (Themes.getAllNames(project).contains(words[1])) {
				Theme s = Themes.find(project, words[1]);
				if (s == null) {
					return false;
				}

				// s.project = project;

				ThemeView sv = new ThemeView(this, s);
				sv.view();
				return true;
			}
		}

		return false;
	}
}

