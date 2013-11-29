package com.example.medicassist.http;

/*
 * Middleware for the construction of URLs. This way its easier to use REST API.
 * For constructing a query to the database.
 * 
 * Author: Lola Priego. me@lolapriego.com
 */

public class UrlBuilder {

	// Every query goesto this URL. URL of the app DB
	public final static String BASE_URL = "https://api.mongolab.com/api/1/databases/medicalapp/collections/";
	
	// Lola Priego API key to access MongoLab services. Not very secure. ToDo: cipher someway
	private final static String URL_API_KEY = "apiKey=aBhzDl3O0OqTCe7BOjsbSZn1u2lmS99P";
	
	// It takes a collection and the parameters and give you the right url.
		public static String paramsToUrl (String [] params, String collection){
			String path = "%22" + params[0] + "%22%3A%20%20%22" + params[1] + "%22";
			for(int i=2; i<params.length; i= i+2){
				path += ",%20%22" + params[i] + "%22%3A%20%20%22" + params[i+1] + "%22";
			}
			return BASE_URL + collection + "?q=%7B" + path + "%7D&" + URL_API_KEY;
		}
		
		// It provide access to one collection
		public static String toUrl(String collection){
			return BASE_URL + collection + "?" + URL_API_KEY;
		}
	
}
