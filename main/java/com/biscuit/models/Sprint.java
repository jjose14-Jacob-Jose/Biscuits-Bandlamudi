/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biscuit.models.enums.SprintReviewStatus;
import com.biscuit.models.enums.Status;
import com.biscuit.models.enums.StatusSprint;

public class Sprint {

	public transient Project project;

	// info
	public String name;
	public String description;
	public Status state;
	public StatusSprint planning;
	public String planningNote;
	public SprintReviewStatus review;
	public String reviewNote;
	public String sprintGoals;
	public Date startDate;
	public Date dueDate;
	public int assignedEffort;
	public int velocity;
	public String retrospectiveMeetingDetails;

	public List<UserStory> userStories = new ArrayList<>();
	public List<Bug> bugs;
	public List<Test> tests;

	// Completed 0pt 0% ToDo 8pt

	public static String[] fields;
	public static String[] fieldsAsHeader;

	static {
		fields = new String[] { "name", "description", "state", "start_date", "due_date", "assigned_effort", "velocity", "retrospectiveMeetingDetails" };
		fieldsAsHeader = new String[] { "Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort", "Velocity", "Retrospective Meeting" };
	}

	public void addUserStory(UserStory userStory) {
		this.userStories.add(userStory);
	}

	public void save() {
		project.save();
	}

	public String toString () {
		StringBuilder sprintInformation = new StringBuilder();
		sprintInformation.append("Sprint Details " + "\n");
		sprintInformation.append("Sprint Name :" + name + "\n");
		sprintInformation.append("Sprint Description :" + name + "\n");
		sprintInformation.append("Sprint State :" + name + "\n");
		sprintInformation.append("Sprint Start Date :" + name + "\n");
		sprintInformation.append("Sprint Due Date :" + name + "\n");
		sprintInformation.append("Sprint Assigned Effort :" + name + "\n");
		sprintInformation.append("Sprint Velocity :" + name + "\n");
		sprintInformation.append("Sprint Retrospective Meeting Details :" + name + "\n");

		if(userStories.size() == 0)
		{
			sprintInformation.append("Sprint has no user stories");
		}
		else {
			sprintInformation.append("User Stores in this sprint are: " + "\n");
			for (UserStory userStory: userStories) {
				sprintInformation.append(userStory.toString() + "\n");
			}

		}

		return sprintInformation.toString();
	}

}
