package com.josn;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Inventory {

	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader(".//Data/inventory.json");
		
		// Java Object variable 
		Object obj = jsonparser.parse(reader);
		
		// JSON Object ( after type casting)
		JSONObject inventoryobj = (JSONObject)obj;
		JSONArray array = (JSONArray)inventoryobj.get("inventoryDetails");
		
		for (int i = 0; i < array.size(); i++ )
		{
			JSONObject inventoryDetails = (JSONObject)array.get(i);
			
			String name = (String) inventoryDetails.get("name");
			double weight = (double) inventoryDetails.get("weight");
			double pricePerKg = (double) inventoryDetails.get("pricePerKg");
			String type = (String) inventoryDetails.get("type");
			
			System.out.println("inventoryDetail " +i+ " is ......");
			
			System.out.println("name::" + name);
			System.out.println("weight::" + weight);
			System.out.println("pricePerKg::" + pricePerKg);
			System.out.println("type::" + type);
			
			double value = weight * pricePerKg;
			System.out.println("value for Inventory is::" + value);
		}
		
	}

}
