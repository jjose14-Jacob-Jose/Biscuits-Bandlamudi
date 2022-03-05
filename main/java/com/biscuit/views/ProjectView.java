/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import com.biscuit.commands.epic.AddEpic;
import com.biscuit.commands.epic.EditEpic;
import com.biscuit.commands.epic.ListEpics;
import com.biscuit.commands.epic.ShowEpic;
import com.biscuit.commands.help.ProjectHelp;
import com.biscuit.commands.issue.AddIssue;
import com.biscuit.commands.issue.EditIssue;
import com.biscuit.commands.issue.ListIssue;
import com.biscuit.commands.issue.ShowIssue;
import com.biscuit.commands.planner.ShowPlan;
import com.biscuit.commands.planner.ShowPlanDetails;
import com.biscuit.commands.project.SaveToFile;
import com.biscuit.commands.project.ShowProject;
import com.biscuit.commands.release.AddRelease;
import com.biscuit.commands.release.ListReleases;
import com.biscuit.commands.sprint.AddSprint;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.commands.task.ListTasks;
import com.biscuit.commands.theme.AddTheme;
import com.biscuit.commands.theme.EditTheme;
import com.biscuit.commands.theme.ListThemes;
import com.biscuit.commands.theme.ShowTheme;
import com.biscuit.commands.userStory.AddUserStoryToBacklog;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.ProjectCompleterFactory;
import com.biscuit.models.Epic;
import com.biscuit.models.Issue;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.Theme;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.Finder.Epics;
import com.biscuit.models.services.Finder.Issues;
import com.biscuit.models.services.Finder.Releases;
import com.biscuit.models.services.Finder.Sprints;
import com.biscuit.models.services.Finder.Themes;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.Completer;

import java.io.IOException;
import java.util.List;

public class ProjectView extends View {

	Project project = null;


	public ProjectView(View previousView, Project p) {
		super(previousView, p.name);
		this.project = p;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ProjectCompleterFactory.getProjectCompleters(project));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		} else if (words.length == 3) {
			return execute3Keywords(words);
		} else if (words.length == 4) {
			return execute4Keywords(words);
		}
		return false;
	}


	private boolean execute4Keywords(String[] words) throws IOException {
		if (words[0].equals("show") || words[0].equals("sh")) {
			if (words[1].equals("backlog") || words[1].equals("b")) { 
				if (words[2].equals("filter") || words[2].equals("f")) {
					(new ListUserStories(project.backlog, "", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort") || words[2].equals("so")) {
					(new ListUserStories(project.backlog, "", false, "", true, words[3])).execute();
					return true;
				}
			}
		} else if (words[0].equals("list") || words[0].equals("l")) {
			if (words[1].equals("releases") || words[1].equals("r")) {
				if (words[2].equals("filter") || words[2].equals("f")) {
					(new ListReleases(project, "Releases", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort") || words[2].equals("so")) {
					(new ListReleases(project, "Releases", false, "", true, words[3])).execute();
					return true;
				}
			} else if (words[1].equals("sprints") || words[1].equals("sp")) {
				if (words[2].equals("filter") || words[2].equals("f")) {
					(new ListSprints(project, "Sprints", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort") || words[2].equals("so")) {
					(new ListSprints(project, "Sprints", false, "", true, words[3])).execute();
					return true;
				}
			} 

			else if (words[1].equals("epics") ) {
				if (words[2].equals("filter") || words[2].equals("f")) {
					(new ListEpics(project , "epics")).execute();
					return true;
				} else if (words[2].equals("sort") || words[2].equals("so")) {
					(new ListEpics(project, "epics")).execute();
					return true;
				}
			}
			else if (words[1].equals("themes")) {
				if (words[2].equals("filter") || words[2].equals("f")) {
					(new ListThemes(project , "themes")).execute();
					return true;
				} else if (words[2].equals("sort") || words[2].equals("so")) {
					(new ListThemes(project, "themes")).execute();
					return true;
				}
			}else if (words[1].equals("user_stories")) {
				if (words[2].equals("filter") || words[2].equals("f")) {
					(new ListUserStories(UserStories.getAll(project), "User Stories (Filtered)", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort") || words[2].equals("so")) {
					(new ListUserStories(UserStories.getAll(project), "All User Stories", false, "", true, words[3])).execute();
					return true;
				}
			}
		}
		return false;
	}


	private boolean execute3Keywords(String[] words) throws IOException {
		if (words[0].equals("go_to")) {
			if (words[1].equals("release")) {
				if (Releases.getAllNames(project).contains(words[2])) {
					Release r = Releases.find(project, words[2]);
					if (r == null) {
						return false;
					}

					// r.project = project;

					ReleaseView rv = new ReleaseView(this, r);
					rv.view();
					return true;
				}
			} else if (words[1].equals("sprint")) {
				if (Sprints.getAllNames(project).contains(words[2])) {
					Sprint s = Sprints.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					SprintView sv = new SprintView(this, s);
					sv.view();
					return true;
				}
			} 
			
			else if (words[1].equals("epic")) {
				if (Epics.getAllNames(project).contains(words[2])) {
					Epic s = Epics.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					EpicView sv = new EpicView(this, s);
					sv.view();
					return true;
				}
				}
			else if (words[1].equals("theme")) {
				if (Themes.getAllNames(project).contains(words[2])) {
					Theme s = Themes.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					ThemeView sv = new ThemeView(this, s);
					sv.view();
					return true;
				}
				}else if (words[1].equals("user_story")) {
				if (UserStories.getAllNames(project).contains(words[2])) {
					UserStory us = UserStories.find(project, words[2]);
					if (us == null) {
						return false;
					}

					UserStroryView usv = new UserStroryView(this, us);
					usv.view();
					return true;
				}
			}
		}
		else if (words[0].equals("show"))
		{
			if (words[1].equals("issue")) {
				if (Issues.getAllNames(project).contains(words[2])) {
					Issue s = Issues.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					(new ShowIssue(s)).execute();
					return true;
				}
				}
			else if (words[1].equals("epic")) {
				if (Epics.getAllNames(project).contains(words[2])) {
					Epic s = Epics.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					(new ShowEpic(s)).execute();
					return true;
				}
				}
			else if (words[1].equals("theme")) {
				if (Themes.getAllNames(project).contains(words[2])) {
					Theme s = Themes.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					(new ShowTheme(s)).execute();
					return true;
				}
				}
			
		}
		else if (words[0].equals("edit"))
		{
			if (words[1].equals("issue")) {
				if (Issues.getAllNames(project).contains(words[2])) {
					Issue s = Issues.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					(new EditIssue(reader, s)).execute();
					return true;
				}
				}
			else if (words[1].equals("theme")) {
				System.out.println("hi");
				if (Themes.getAllNames(project).contains(words[2])) {
					Theme s = Themes.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					(new EditTheme(reader, s)).execute();
					return true;
				}
				}
			else if (words[1].equals("epic")) {
				if (Epics.getAllNames(project).contains(words[2])) {
					Epic s = Epics.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					(new EditEpic(reader, s)).execute();
					return true;
				}
				}
			
			
		}




		

		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToBacklog(reader, project)).execute();
				return true;
			} else if (words[1].equals("release")) {
				(new AddRelease(reader, project)).execute();
				resetCompleters();

				return true;
			} else if (words[1].equals("sprint")) {
				(new AddSprint(reader, project)).execute();
				resetCompleters();

				return true;
			}else if (words[1].equals("epic")) {
				(new AddEpic(reader, project)).execute();
				resetCompleters();

				return true;
			}
			else if (words[1].equals("issue")) {
				(new AddIssue(reader, project)).execute();
				resetCompleters();

				return true;
			}
			else if (words[1].equals("theme")) {
				(new AddTheme(reader, project)).execute();
				resetCompleters();

				return true;
			}


		} else if (words[0].equals("go_to")) {
			if (words[1].equals("backlog")) {
				this.project.backlog.project = this.project;
				BacklogView bv = new BacklogView(this, this.project.backlog);
				bv.view();
				return true;
			} else if (words[1].equals("releases")) {

				ReleasesView rsv = new ReleasesView(this, project);
				rsv.view();

				return true;
			} else if (words[1].equals("sprints")) {

				SprintsView ssv = new SprintsView(this, project);
				ssv.view();

				return true;
			} else if (words[1].equals("epics")) {

				EpicsView esv = new EpicsView(this, project);
				esv.view();

				return true;
			}
			else if (words[1].equals("themes")) {

				ThemesView esv = new ThemesView(this, project);
				esv.view();

				return true;
			}else if (words[1].equals("planner")) {

				PlannerView pv = new PlannerView(this, project);
				pv.view();

				return true;
			}
		} else if (words[0].equals("show")) {
			if (words[1].equals("backlog")) {
				(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
				return true;
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("releases")) {
				(new ListReleases(project, "Releases")).execute();
				return true;
			} else if (words[1].equals("sprints")) {
				(new ListSprints(project, "Sprints")).execute();
				return true;
			} 
			 else if (words[1].equals("epics")) {
					(new ListEpics(project)).execute();
					return true;
					}
			 else if (words[1].equals("issues")) {
					(new ListIssue(project)).execute();
					return true;
					}
			 else if (words[1].equals("themes")) {
					(new ListThemes(project)).execute();
					return true;
					}else if (words[1].equals("user_stories")) {
				(new ListUserStories(UserStories.getAll(project), "All User Stories")).execute();
				return true;
			}
		} else if (words[0].equals("plan")) {
			if (words[1].equals("details")) {
				(new ShowPlanDetails(project)).execute();
				return true;
			}
		}


		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("info")) {
			reader.println(project.toString());
			return true;
		} else if (words[0].equals("backlog")) {
			(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
			return true;
		} else if (words[0].equals("releases")) {
			(new ListReleases(project, "Releases")).execute();
			return true;
		} else if (words[0].equals("sprints")) {

			for (Release r : project.releases) {
				if (!r.sprints.isEmpty()) {
					(new ListSprints(r, "Release: " + r.name + " -> Sprints")).execute();
				}
			}

			(new ListSprints(project, "Unplanned Sprints")).execute();
			return true;
		} else if (words[0].equals("user_stories")) {
			(new ListUserStories(UserStories.getAll(project), "All User Stories")).execute();
			return true;
		} else if (words[0].equals("plan")) {
			(new ShowPlan(project)).execute();
			return true;
		} else if (words[0].equals("tasks")) {
			List<UserStory> userStories = UserStories.getAll(project);
			for (UserStory us : userStories) {
				if (!us.tasks.isEmpty()) {
					(new ListTasks(us, "User Story: " + us.title)).execute();
				}
			}
			return true;
		} else if (words[0].equals("show")) {
			return (new ShowProject(project).execute());
		} else if (words[0].equals("help")) {
			return (new ProjectHelp().execute());
		}else if (words[0].equals("print_to_file")) {
			return (new SaveToFile().saveToTextFile(project));
		}

		return false;
	}

}
