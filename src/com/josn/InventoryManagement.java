package com.josn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InventoryManagement {

	public static void main(String[] args) throws IOException, ParseException{

		InventoryManagement inventory = new InventoryManagement();	//	class object
		//	get data from user and write to the file
		inventory.writeData(inventory);	
		//	read data from the file and calculate the inventory
		inventory.readData();	
		
	}

	
	/**
	 * Takes user input and writes data to json objcet and object to file
	 * @param weights 
	 */
	void writeData(Object weights) {
		Scanner scanner = new Scanner(System.in);	//	scanner to get user input
		String[] names = new String[] {"Rice" , "Wheat" , "Pulses"};	//	inventory array
	
		JSONObject finalObject = new JSONObject();	//	json object to write data
		for (String name : names) {	//	getting data for every inventory
			System.out.print("Enter number of types of " + name + " : ");
			
			String count = scanner.next();	//	types of every inventory
			JSONArray array = new JSONArray();	//	array to store types
			
			for(int i = 0; i < array.size(); i++) {	//	 object for every type
				JSONObject jsonObject  = new JSONObject();
				System.out.print("Enter name, weight and price: ");
				jsonObject.put("name", scanner.next());
				jsonObject.put("weight", scanner.nextDouble());
				jsonObject.put("price", scanner.nextDouble());
				array.add(jsonObject);
			}			
			finalObject.put(name,array);
			
			
			
		}
		
		scanner.close();
		PrintWriter printWriter = null;	//	to write data to the file
		try {
			printWriter = new PrintWriter(".//Data/inventoryManagement.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printWriter.write(finalObject.toJSONString());
		printWriter.close();
	}

	/**
	 * reads data from json file and 
	 * prints inventory value of Rice, Pulses and Wheat
	 */
	void readData() {
		JSONArray array = new JSONArray();	//	array to get json data from file
		JSONParser parser = new JSONParser();	//	parser to parse data from file
		JSONObject object;	// json object to store every type of inventory
		
		try {
			object = (JSONObject) parser.parse(new FileReader(".//Data/inventoryManagement.json"));
			array = (JSONArray) object.get("Rice");
			System.out.println("Rice inventory value: " + getValue(array));
			array = (JSONArray) object.get("Pulses");
			System.out.println("Pulses inventory value: " + getValue(array));
			array = (JSONArray) object.get("Wheat");
			System.out.println("Wheat inventory value: " + getValue(array));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  array - json array
	 *  value of given inventory array
	 */
	public double getValue(JSONArray array){
		Iterator<?> iterator = array.iterator();	//	iterator to iterate
		double value = 0;	//	inventory value
		while(iterator.hasNext()) {
			JSONObject obj = (JSONObject)iterator.next();
			double weight = (double)obj.get("weight");
			double price = (double)obj.get("price");
			value = value + weight * price;	//	adding value
		}
		return value;
	}
}
