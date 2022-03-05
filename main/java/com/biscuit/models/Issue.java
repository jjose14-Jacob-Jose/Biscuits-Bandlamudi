package com.biscuit.models;


import java.util.ArrayList;
import java.util.List;


public class Issue {

public transient Project project;

// info
public String name;
public String description;




// Completed 0pt 0% ToDo 8pt

public static String[] fields;
public static String[] fieldsAsHeader;

static {
	fields = new String[] { "name", "description" };
	fieldsAsHeader = new String[] { "Name", "Description" };
}



public void save() {
	project.save();
}

}
