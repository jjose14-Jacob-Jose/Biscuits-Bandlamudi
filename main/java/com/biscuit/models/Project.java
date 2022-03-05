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
	public List<Issue> issue = new ArrayList<>();
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
		issue.add(e);
	}
	
	public void addTheme(Theme e) {
		themes.add(e);
	}


	@Override
	public String toString() {
		return "project name: " + name + "\n\ndescription:-\n" + description + "\n\nGitURL:-\n" + gitURL;
	}

}
