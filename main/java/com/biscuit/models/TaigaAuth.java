package com.biscuit.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;

import jline.console.ConsoleReader;

public class TaigaAuth implements Command{
	
	Dashboard dashboard = Dashboard.getInstance();
	Project project = new Project();
	ConsoleReader reader = null;
	
	public TaigaAuth(ConsoleReader reader) {
		super();
		this.reader = reader;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();
		
		reader.setPrompt(ColorCodes.BLUE + "Username/email: " + ColorCodes.RESET);
		String username = reader.readLine();
		reader.setPrompt(ColorCodes.BLUE + "\nPassword: " + ColorCodes.BLACK);
		String password = reader.readLine();
		
		//reader.setPrompt();
		reader.println(ColorCodes.RESET);
		System.out.println(username+"\t"+password);
		

		reader.setPrompt(prompt);
		try {
			authenticate(username, password);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		reader.println();
		reader.println(ColorCodes.GREEN + "Connected to Taiga " + ColorCodes.RESET);

		return false;
	}

	@SuppressWarnings("rawtypes")
	public void authenticate(String username, String password) throws URISyntaxException, ClientProtocolException, IOException {
	
		    URL url = new URL("https://api.taiga.io/api/v1/auth");
		    Map<String, String> params = new LinkedHashMap<String, String>();
		    params.put("username", "svodeti@asu.edu");
		    params.put("password", "Mummy@431");
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
		}
}
