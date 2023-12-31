/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.ArrayList;
import java.util.List;

public class Project {

	public String name;
	public String description;
	public String github;
	public String gitURL;
	public String team_members;
	public Backlog backlog = new Backlog();
	public List<Release> releases = new ArrayList<>();
	public List<Sprint> sprints = new ArrayList<>();
	public List<Epic> epics = new ArrayList<>();
	public List<Issue> issues = new ArrayList<>();
	public List<Theme> themes = new ArrayList<>();

	public void save() {
		ModelHelper.save(this, name);
	}


	public void delete() {
		ModelHelper.delete(name);
	}


	static public Project load(String name) {
		return ModelHelper.loadProject(name);
	}


	public void updateChildrenReferences() {

		this.backlog.project = this;

		for (Release r : releases) {
			r.project = this;
			updateSprintReferences(r.sprints);
		}

		updateSprintReferences(sprints);
		updateUserStoryReferences(backlog.userStories);
	}


	private void updateSprintReferences(List<Sprint> sprints) {
		for (Sprint s : sprints) {
			s.project = this;
			updateUserStoryReferences(s.userStories);
		}
	}


	private void updateUserStoryReferences(List<UserStory> userStories) {
		for (UserStory us : userStories) {
			us.project = this;

			for (Task t : us.tasks) {
				t.project = this;
			}
		}
	}


	public void addRelease(Release r) {
		releases.add(r);
	}


	public void addSprint(Sprint s) {
		sprints.add(s);
	}
	public void addEpic(Epic e) {
		epics.add(e);
	}
	public void addIssue(Issue e) {
		issues.add(e);
	}
	
	public void addTheme(Theme e) {
		themes.add(e);
	}


	@Override
	public String toString() {
		StringBuilder projectDetails = new StringBuilder();
		projectDetails.append("\n" + "Project Information" + "\n");
		projectDetails.append("Project Name: " + name + "\n");
		projectDetails.append("Project Description: " + description + "\n");
		projectDetails.append("Project GitHub URL: " + gitURL + "\n");
		projectDetails.append("Project Team Members: " + team_members + "\n");
		projectDetails.append("Project Backlog: " + backlog.toString() + "\n");
		projectDetails.append("Project Releases: " + releases.toString() + "\n");
		projectDetails.append("Project Sprints: " + sprints.toString() + "\n");
		projectDetails.append("Project Epics: " + epics.toString() + "\n");
		projectDetails.append("Project Themes: " + themes.toString() + "\n");
		return  projectDetails.toString();
	}

}
