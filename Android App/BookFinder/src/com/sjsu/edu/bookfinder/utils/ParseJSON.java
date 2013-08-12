package com.sjsu.edu.bookfinder.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJSON {
	
	private JSONArray jsonArray;
	
	private List<String> titles = new ArrayList<String>();
	private HashMap<String, String> title_condition = new HashMap<String, String>();
	private HashMap<String, String> title_edition = new HashMap<String, String>();
	private HashMap<String, String> title_email = new HashMap<String, String>();
	
	private String bookTitle;
	private String bookCondition;
	private String bookEdition;
	private String email;
	
	public ParseJSON() {}
	
	public ParseJSON(String bookTitle, String bookEdition, String bookCondition, String email) {
		this.bookTitle = bookTitle;
		this.bookEdition = bookEdition;
		this.bookCondition = bookCondition;
		this.email = email;
	}
	
	@SuppressWarnings("unchecked")
	public void readJSON(String url) {
		
		JSONParser parser = new JSONParser();
		 
		try {
	 
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
			Object obj = parser.parse(reader);
	 
			jsonArray = (JSONArray) obj;
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObj = iterator.next();
				String title = jsonObj.get("title").toString();
				titles.add(title);
				title_condition.put(title, jsonObj.get("bookCondition").toString());
				title_edition.put(title, jsonObj.get("edition").toString());
				title_email.put(title, jsonObj.get("email").toString());
			}
	 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject createJSON() {
//		{"bookTitle":"Core Java","bookEdition":"3rd Edition","bookCondition":"Excellent","email":"arknd2gio@gjk.com"}
		
		JSONObject obj = new JSONObject();
		obj.put("bookTitle", bookTitle);
		obj.put("bookEdition", bookEdition);
		obj.put("bookCondition", bookCondition);
		obj.put("email", email);
		
		return obj;
	}
	
	public List<String> getTitles() {
		return Collections.unmodifiableList(titles);
	}
	
	public HashMap<String, String> getBookConditions() {
		return title_condition;
	}
	
	public HashMap<String, String> getBookEditions() {
		return title_edition;
	}
	
	public HashMap<String, String> getBookOwners() {
		return title_email;
	}
}
