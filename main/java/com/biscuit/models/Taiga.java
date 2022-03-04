package com.biscuit.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.services.MaskPassword;

import jline.console.ConsoleReader;

public class Taiga implements Command{
	public static String AUTH_TOKEN;
	public 
	ConsoleReader reader = null;
	
	public Taiga(ConsoleReader reader) {
		super();
		this.reader = reader;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();
		
		reader.setPrompt(ColorCodes.BLUE + "Username/email: " + ColorCodes.RESET);
		String username = reader.readLine();

		String password = MaskPassword.readPassword("Enter password:");
		
		//reader.setPrompt();
		reader.println(""+ColorCodes.RESET);
		//System.out.println(username+"\t"+password);
		
		reader.setPrompt(prompt);
		
		try {
			authenticate(username, password);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		reader.println();
		if(AUTH_TOKEN != null)
			reader.println(ColorCodes.GREEN + "Connected to Taiga " + ColorCodes.RESET);
		
		return false;
	}

	@SuppressWarnings("rawtypes")
	public void authenticate(String username, String password) throws URISyntaxException, ClientProtocolException, IOException {
	
	    URL url = new URL("https://api.taiga.io/api/v1/auth");
	    Map<String, String> params = new LinkedHashMap<String, String>();
	    params.put("username", username);
	    params.put("password", password);
	    params.put("type", "normal");
	    StringBuilder postData = new StringBuilder();
	    for (Map.Entry param : params.entrySet()) {
	        if (postData.length() != 0) postData.append('&');
	        postData.append(URLEncoder.encode((String) param.getKey(), "UTF-8"));
	        postData.append('=');
	        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	    }
	    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	    conn.setDoOutput(true);
	    conn.getOutputStream().write(postDataBytes);
	    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    StringBuilder sb = new StringBuilder();
	    for (int c; (c = in.read()) >= 0;)
	        sb.append((char)c);
	    String response = sb.toString();
	    System.out.println(response);
	    JSONObject myResponse = new JSONObject(response.toString());
	    AUTH_TOKEN = myResponse.getString("auth_token");
		    //System.out.println(AUTH_TOKEN);
	}
	
	public void getProjectsBySlug(String slug) {
		URL proURL;
		try {
			proURL = new URL("https://api.taiga.io/api/v1/projects/by_slug?slug=" + slug);
			
		    HttpURLConnection proConn = (HttpURLConnection)proURL.openConnection();
		    proConn.setRequestMethod("GET");
		    proConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    proConn.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);
		    proConn.setDoOutput(true);
		    
		    System.out.println("Retrieving details by Slug "+ slug);
		    Reader proIin = new BufferedReader(new InputStreamReader(proConn.getInputStream(), "UTF-8"));
		    StringBuilder sb1 = new StringBuilder();
		    for (int c; (c = proIin.read()) >= 0;)
		        sb1.append((char)c);
		    String response1 = sb1.toString();
		    displayProjectDetails(new JSONObject(response1.toString()));
		    
		  //  System.out.println(response1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getProjects() throws ProtocolException {
	 	URL proURL;
		try {
			proURL = new URL("https://api.taiga.io/api/v1/projects");
		
		    HttpURLConnection proConn = (HttpURLConnection)proURL.openConnection();
		    proConn.setRequestMethod("GET");
		    proConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    proConn.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);
		    proConn.setDoOutput(true);
		
		    Reader proIin = new BufferedReader(new InputStreamReader(proConn.getInputStream(), "UTF-8"));
		    StringBuilder sb1 = new StringBuilder();
		    for (int c; (c = proIin.read()) >= 0;)
		        sb1.append((char)c);
		    String response1 = sb1.toString();
		    System.out.println(response1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayProjectDetails(JSONObject proJson) {
		System.out.println("Project ID: "+ proJson.getDouble("id") + "\n");
		System.out.println("Project Name: "+proJson.getString("name") + "\n");
		System.out.println("Project Description: "+proJson.getString("description") + "\n");
		
		JSONObject getOwnerObj = proJson.getJSONObject("owner");
		System.out.println("Owner of Project: " + getOwnerObj.get("full_name_display") + "\n");
		
		JSONArray getMembersObj = proJson.getJSONArray("members");
		ArrayList<JSONObject> membersArray = new ArrayList<JSONObject>();
		
		for (int i=0;i<getMembersObj.length(); i++) 
			membersArray.add(getMembersObj.getJSONObject(i));
		
		System.out.println("Members included in this prject:");
		
		for(JSONObject member: membersArray)
			System.out.println(member.get("full_name") + "-" + member.getString("role_name"));
		
		JSONArray getMilestonesObj = proJson.getJSONArray("milestones");
		System.out.println("\nMilestones in project are: ");
		for (int i=0;i<getMilestonesObj.length(); i++) {
			JSONObject milestone = (JSONObject) getMilestonesObj.getJSONObject(i);
			System.out.println(milestone.getString("name") + "-" + milestone.getDouble("id"));
		}
	}
	
	public void getUserstories(String proId) {
		URL proURL;
		try {
			proURL = new URL("https://api.taiga.io/api/v1/userstories?project=" + proId);
			
		    HttpURLConnection proConn = (HttpURLConnection)proURL.openConnection();
		    proConn.setRequestMethod("GET");
		    proConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    proConn.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);
		    proConn.setDoOutput(true);
		    
		    System.out.println("Retrieving User stories by Project Id "+ proId + "\n");
		    Reader proIin = new BufferedReader(new InputStreamReader(proConn.getInputStream(), "UTF-8"));
		    StringBuilder sb1 = new StringBuilder();
		    for (int c; (c = proIin.read()) >= 0;)
		        sb1.append((char)c);
		    String response1 = sb1.toString();
		    displayUserStoryDetails(new JSONArray(response1.toString()));
		    
		  //  System.out.println(response1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayUserStoryDetails(JSONArray milestoneObj) {
		for (int i=0;i<milestoneObj.length(); i++) {
			JSONObject milestone = (JSONObject) milestoneObj.getJSONObject(i);
			System.out.println(milestone.getDouble("id") +" - " + milestone.getString("subject") + "\n");
		}
	}
	
}

