package com.khizar.codingtask.application.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.khizar.codingtask.application.model.City;

/**
 * @author Khizar
 */
public class Utils {
    /**
     * This method takes a URL for the endpoint for cities data and returns a
     * list of City objects from that data.
     * 
     * @param url
     *            - URL of the Json endpoint
     * @return A list of city objects
     */
    public static List<City> pareseJsonToCities(URL url) {
	ObjectMapper objectMapper = new ObjectMapper();
	List<City> cities = null;
	try {
	    JsonNode node = objectMapper.readValue(url, JsonNode.class);
	    if (node.isArray() && node.size() > 0) {
		cities = new ArrayList<>();
		Iterator<JsonNode> nodeElements = node.elements();
		while (nodeElements.hasNext()) {
		    City city = new City();

		    JsonNode currentCity = nodeElements.next();
		    JsonNode idNode = currentCity.get("_id");
		    Long id = idNode.asLong();
		    city.setId(id);

		    JsonNode nameNode = currentCity.get("name");
		    String name = nameNode != null ? nameNode.asText() :" ";
		    city.setName(name);

		    JsonNode typeNode = currentCity.get("type");
		    String type = typeNode != null ? typeNode.asText() :" ";
		    city.setType(type);

		    JsonNode geoPositionNode = currentCity.get("geo_position");
		    if (geoPositionNode != null) {
			JsonNode latitudeNode = geoPositionNode.get("latitude");
			String latitude = latitudeNode!=null?latitudeNode.asText():" ";
			city.setLatitude(latitude);

			JsonNode longitudeNode = geoPositionNode.get("longitude");
			String longitude = longitudeNode!=null?longitudeNode.asText():" ";
			city.setLongitude(longitude);
		    }

		    cities.add(city);
		}

	    }
	} catch (JsonParseException | JsonMappingException e) {
	    System.out.println("Could not parse Json.");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.out.println("File I/O problems.");
	    e.printStackTrace();
	}
	return cities;
    }

    /**
     * This method takes a list of City objects and filename and create a CSV
     * file with the given name and List of Cities. Each query string with at
     * least one result will have only one CSV file as it would be overwritten
     * if asked for again.
     * 
     * @param cities
     *            - List of cities to be converted to CSV
     * @param filename
     *            Name of the CSV file.
     */
    public static void writeCsvFile(List<City> cities, String filename) {
	CsvMapper mapper = new CsvMapper();
	CsvSchema schema = mapper.schemaFor(City.class).withHeader();
	ObjectWriter writer = mapper.writer(schema);

	try {
	    writer.writeValue(new File(filename + ".csv"), cities); // making separate file for each city
	    System.out.println("The results are saved to " + filename + ".csv");
	} catch (JsonGenerationException | JsonMappingException e) {
	    System.out.println("Could not write CSV file.");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.out.println("Could not write CSV file. File I/O problems.");
	    e.printStackTrace();
	}

    }

}
