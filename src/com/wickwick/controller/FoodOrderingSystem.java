package com.wickwick.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

import com.wickwick.model.Customer;
import com.wickwick.model.Restaurant;

/**
 * Food Ordering System (side-project)
 *
 * @author Harvey (wickwickthedog) 
 *
 */

public class FoodOrderingSystem {
	private ArrayList<Customer> customerList;
	private ArrayList<Restaurant> restaurantList;
	
	
	public FoodOrderingSystem() {
		
	}
	
	private void processCommand(JSONObject json) {
		switch(json.getString("command")) {
		
		case "customer" :
			/* 
			 * Creating customer and adding
			 * to customerList
			 */
			String customerName = json.getString("name");
            String password = json.getString("password");
            
            if (customerList.contains(new Customer(customerName, password))) 
            	break;
            customerList.add(new Customer(customerName, password));
			break;
		
		case "restaurant":
			/* 
			 * Creating restaurant and adding
			 * to restaurantList
			 */
			String restaurantName = json.getString("name");
            String cuisine = json.getString("cuisine");
            LocalDate opening  = LocalDate.parse(json.getString("opening"));
            LocalDate closing = LocalDate.parse(json.getString("closing"));
            
            if (restaurantList.contains(new Restaurant(restaurantName, cuisine, opening, closing))) 
            	break;
            restaurantList.add(new Restaurant(restaurantName, cuisine, opening, closing));
            break;
            
		}
	}
	
	public static void main(String[] args) {
        FoodOrderingSystem system = new FoodOrderingSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }
}
