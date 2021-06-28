package com.josn;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount {
	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser jsonparser = new JSONParser();
		
		FileReader reader = new FileReader(".\\Data\\stockDetails.json");
		
		Object object = jsonparser.parse(reader);
		
		JSONObject stockjsonobj = (JSONObject)object;
		
		JSONArray stockarray = (JSONArray)stockjsonobj.get("StockDetails");
		
		for(int i=0; i<stockarray.size(); i++) {
			
			JSONObject StockDetails = (JSONObject) stockarray.get(i);
			
			String stockName = (String) StockDetails.get("stockName");
			double numberOfShare = (double) StockDetails.get("numberOfShare");
			double sharePrice = (double) StockDetails.get("sharePrice");
			
			System.out.println("Stock Details of " + i + " is ");
			System.out.println("Stock Name: " + stockName);
			System.out.println("Number Of Share: " + numberOfShare);
			System.out.println("Share Price: " +sharePrice);

			double totalValue=0;
			double amount = numberOfShare * sharePrice;
			totalValue = amount + totalValue;
			System.out.println("Total Vlaue is:: " + totalValue);
		}
		
	}

}
