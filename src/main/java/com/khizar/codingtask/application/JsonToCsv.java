package com.khizar.codingtask.application;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.khizar.codingtask.application.model.City;
import com.khizar.codingtask.application.utils.Utils;

/**
 * @author Khizar
 */
public class JsonToCsv {
    
    private static final String URL_STRING = "http://api.goeuro.com/api/v2/position/suggest/en/";
    
    public static void main(String[] args) {
	URL url = null;
	String query;
	if(args != null && args.length > 0){
	    query = args[0];
	} else {
	    System.out.println("Please enter a city to search as an argument to run the program.");
	    return;
	}
	try {
	    url = new URL(URL_STRING + URLEncoder.encode(query, "UTF-8"));
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    System.out.println("This is embarassing!! We cannot quite make out what city you mean, Please check your spelling and try again");
	    e.printStackTrace();
	}
	
	List<City> cities = Utils.pareseJsonToCities(url);
	if(cities != null && cities.size() > 0){
	    Utils.writeCsvFile(cities, query);
	} else {
	    System.out.println("No such city found.");
	}

	

    }

}
