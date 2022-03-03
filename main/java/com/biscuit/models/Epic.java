package com.biscuit.models;


import java.util.ArrayList;
import java.util.List;


public class Epic {

public transient Project project;

// info
public String name;
public String description;


public List<UserStory> userStories = new ArrayList<>();
public List<Bug> bugs;
public List<Test> tests;

// Completed 0pt 0% ToDo 8pt

public static String[] fields;
public static String[] fieldsAsHeader;

static {
	fields = new String[] { "name", "description" };
	fieldsAsHeader = new String[] { "Name", "Description" };
}

public void addUserStory(UserStory userStory) {
	this.userStories.add(userStory);
}

public void save() {
	project.save();
}

public String toString () {
	StringBuilder epicDetails = new StringBuilder();
	epicDetails.append("Epic Name: "+ name + "\n");
	epicDetails.append("Epic Description: "+ description + "\n");

	if(userStories.size() == 0) {
		epicDetails.append("Epic has no user stories");
	}
	else {
		epicDetails.append("User stories in this epic are: " + "\n");
		for(UserStory userStory: userStories) {
			epicDetails.append(userStory.toString());
		}
	}
	return epicDetails.toString();
	}
}
