package com.biscuit.models;


import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.enums.Priority;
import com.biscuit.models.enums.Severity;
import com.biscuit.models.enums.Type;


public class Issue {

public transient Project project;

// info
public String name;
public String description;
public Type type;
public Severity severity;
public Priority priority;




// Completed 0pt 0% ToDo 8pt

public static String[] fields;
public static String[] fieldsAsHeader;

static {
	fields = new String[] { "name", "description","type","severity","priority" };
	fieldsAsHeader = new String[] { "Name", "Description" , "Type", "Severity", "Priority"};
}



public void save() {
	project.save();
}

}
