package com.biscuit.models;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class jsonReader {


	public void ReadFile() {

	      JSONParser parser = new JSONParser();
	      try {
	         Object obj = parser.parse(new FileReader("/Users/santoshkaluva/biscuit/sample.json"));
	         JSONObject jsonObject = (JSONObject)obj;
	         String name = (String)jsonObject.get("Name");
	         String description = (String)jsonObject.get("description");
	         JSONArray backlog = (JSONArray)jsonObject.get("backlog");
	         JSONArray releases = (JSONArray)jsonObject.get("releases");
	         JSONArray sprints = (JSONArray)jsonObject.get("sprints");
	         System.out.println("Name: " + name);
	         System.out.println("description: " + description);
	         System.out.println("Subjects:");
	         Iterator iterator1 = backlog.iterator();
	         while (iterator1.hasNext()) {
	            System.out.println(iterator1.next());
	         }
	         Iterator iterator2 = releases.iterator();
	         while (iterator2.hasNext()) {
	            System.out.println(iterator2.next());
	         }
	         Iterator iterator3 = sprints.iterator();
	         while (iterator3.hasNext()) {
	            System.out.println(iterator3.next());
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
	

}



