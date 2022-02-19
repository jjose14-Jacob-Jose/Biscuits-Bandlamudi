package com.biscuit.views;


import java.io.IOException;
import java.util.List;

import com.biscuit.commands.epic.EditEpic;
import com.biscuit.commands.epic.ShowEpic;
import com.biscuit.commands.help.SprintHelp;
import com.biscuit.commands.sprint.ChangeStatusSprint;
import com.biscuit.commands.sprint.EditSprint;
import com.biscuit.commands.sprint.ShowSprint;
import com.biscuit.commands.theme.EditTheme;
import com.biscuit.commands.theme.ShowTheme;
import com.biscuit.commands.userStory.AddUserStoryToEpic;
import com.biscuit.commands.userStory.AddUserStoryToSprint;
import com.biscuit.commands.userStory.AddUserStoryToTheme;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.EpicCompleterFactory;
import com.biscuit.factories.SprintCompleterFactory;
import com.biscuit.factories.ThemeCompleterFactory;
import com.biscuit.models.Epic;
import com.biscuit.models.Sprint;
import com.biscuit.models.Theme;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.Completer;

public class ThemeView extends View {

	Theme theme = null;


	public ThemeView(View previousView, Theme theme) {
		super(previousView, theme.name);
		this.theme = theme;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ThemeCompleterFactory.getThemeCompleters(theme));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		} 

		return false;
	}





	private boolean execute2Keywords(String[] words) throws IOException {

		if (words[0].equals("list")) {
			if (words[1].equals("user_stories")) {
				(new ListUserStories(theme, theme.name + " (User Stories)")).execute();
				return true;
			}
		} else if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToTheme(reader, theme)).execute();
				resetCompleters();

				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (UserStories.getAllNames(theme).contains(words[1])) {
				UserStory us = UserStories.find(theme, words[1]);
				if (us == null) {
					return false;
				}

				UserStroryView usv = new UserStroryView(this, us);
				usv.view();
				return true;
			}
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("show")) {
			(new ShowTheme(theme)).execute();
			return true;
		} else if (words[0].equals("edit")) {
			(new EditTheme(reader, theme)).execute();
			this.name = theme.name;
			updatePromptViews();

			return true;
		} else if (words[0].equals("user_stories")) {
			(new ListUserStories(theme, theme.name + " (User Stories)")).execute();
			return true;
		} else if (words[0].equals("help")) {
			return (new SprintHelp()).execute();
		}
		return false;
	}

}


