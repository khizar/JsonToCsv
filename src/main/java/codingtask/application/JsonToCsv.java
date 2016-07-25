package codingtask.application;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import codingtask.application.model.City;
import codingtask.application.utils.Utils;

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
	    url = new URL(URL_STRING + query);
	} catch (MalformedURLException e) {
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
