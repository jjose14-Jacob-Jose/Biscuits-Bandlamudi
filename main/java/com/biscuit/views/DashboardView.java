/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.ColorCodes;
import com.biscuit.commands.help.DashboardHelp;
import com.biscuit.commands.project.AddProject;
import com.biscuit.commands.project.EditProject;
import com.biscuit.commands.project.RemoveProject;
import com.biscuit.factories.DashboardCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Taiga;
import com.biscuit.models.services.Finder.Projects;

import jline.console.completer.Completer;

public class DashboardView extends View {

	public DashboardView() {
		super(null, "Dashboard");
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(DashboardCompleterFactory.getDashboardCompleters());
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {

		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keyword(words);
		} else if (words.length == 3) {
			return execute3Keyword(words);
		}

		return false;
	}


	private boolean execute3Keyword(String[] words) throws IOException {
		if (words[0].equals("go_to") || words[0].equals("gt")) {
			// "project#1", "users", "contacts", "groups"

			if (words[1].equals("project") || words[1].equals("p")) {
				// check if project name
				Project p = Projects.getProject(words[2]);
				if (p != null) {
					ProjectView pv = new ProjectView(this, p);
					pv.view();
					return true;
				}
				return false;
			}
		} else if (words[1].equals("project") || words[1].equals("p")) {
			if (words[0].equals("edit")) {
				Project p = Projects.getProject(words[2]);
				if (p != null) {
					(new EditProject(reader, p)).execute();
					resetCompleters();
					return true;
				}
				return false;
			} else if (words[0].equals("remove")) {
				Project p = Projects.getProject(words[2]);
				if (p != null) {
					(new RemoveProject(reader, p)).execute();
					resetCompleters();
					return true;
				}
				return false;
			}
		} else if (words[0].equals("get")) {
			if (words[1].equals("project") || words[1].equals("projects") || words[1].equals("p")) {
				if(words[2].equals("by_slug")) {
					reader.setPrompt(ColorCodes.BLUE + "Enter project slug: " + ColorCodes.RESET);
					String slug = reader.readLine();
					new Taiga(reader).getProjectsBySlug(slug);
					return true;
				}
			}
		}
		return false;
	}


	private boolean execute2Keyword(String[] words) throws IOException {
		if (words[0].equals("go_to") || words[0].equals("gt")) {
			// "project#1", "users", "contacts", "groups"

			// check if project name
			Project p = Projects.getProject(words[1]);
			if (p != null) {
				ProjectView pv = new ProjectView(this, p);
				pv.view();
				return true;
			}
			return false;

		} else if (words[0].equals("list")) {
			// projects
			// "filter", "sort"
		} else if (words[1].equals("project") || words[1].equals("p")) {
			if (words[0].equals("add")) {
				(new AddProject(reader)).execute();
				resetCompleters();
				return true;
			}
		} else if (words[0].equals("connect_to")) {
			if (words[1].equals("taiga")) {
				new Taiga(reader).execute();
				return true;
			}
		} else if (words[0].equals("get")) {
			if (words[1].equals("project") || words[1].equals("projects") || words[1].equals("p")) {
				if(Taiga.AUTH_TOKEN != null) {
					String prompt = reader.getPrompt();
					reader.setPrompt(ColorCodes.BLUE + "\nEnter project slug: " + ColorCodes.RESET);
					String slug = reader.readLine();
					new Taiga(reader).getProjectsBySlug(slug);
					reader.setPrompt(prompt);
					reader.println();
					return true;
				}
				else {
					System.out.println("Not Authenticated");
					return false;
				}
			} else if (words[1].equals("user_stories") || words[1].equals("user_story")) {
				System.out.println(1);
				if(Taiga.AUTH_TOKEN != null) {
					System.out.println(2);
					String prompt = reader.getPrompt();
					reader.setPrompt(ColorCodes.BLUE + "\nEnter project id: " + ColorCodes.RESET +"\n (use get projects command to see id)\n");
					String id = reader.readLine();
					new Taiga(reader).getUserstories(id);
					reader.setPrompt(prompt);
					reader.println();
					return true;
				}
				else {
					System.out.println("Not Authenticated");
					return false;
				}
			} else if (words[1].equals("tasks") || words[1].equals("task")) {
				System.out.println(1);
				if(Taiga.AUTH_TOKEN != null) {
					System.out.println(2);
					String prompt = reader.getPrompt();
					reader.setPrompt(ColorCodes.BLUE + "\nEnter user story id: " + ColorCodes.RESET +"\n (use get user_stories command to see id)\n");
					String id = reader.readLine();
					new Taiga(reader).getTasks(id);
					reader.setPrompt(prompt);
					reader.println();
					return true;
				}
				else {
					System.out.println("Not Authenticated");
					return false;
				}
			} 
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("summary")) {
		} else if (words[0].equals("projects") || words[0].equals("p")) {
		} else if (words[0].equals("alerts")) {
		} else if (words[0].equals("check_alert")) {
		} else if (words[0].equals("search")) {
		} else if (words[0].equals("help")) {
			return (new DashboardHelp().execute());
		}

		return false;
	}

}
