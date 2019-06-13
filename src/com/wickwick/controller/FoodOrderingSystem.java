package com.wickwick.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wickwick.model.BankTransfer;
import com.wickwick.model.CardPayment;
import com.wickwick.model.CashPayment;
import com.wickwick.model.Customer;
import com.wickwick.model.Menu;
import com.wickwick.model.Order;
import com.wickwick.model.Restaurant;
import com.wickwick.utils.PaymentMethod;

/**
 * Food Ordering System (side-project)
 *
 * @author Harvey (wickwickthedog) 
 *
 */

public class FoodOrderingSystem {
	private ArrayList<Customer> customerList;
	private ArrayList<Restaurant> restaurantList;
	private ArrayList<Order> orderList;
	
	public FoodOrderingSystem() {
		customerList = new ArrayList<Customer>();
		restaurantList = new ArrayList<Restaurant>();
		orderList = new ArrayList<Order>();
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
            
            Customer customer = getCustomer(customerName);
            if (customer == null) customer = new Customer(customerName, password); 
            
            JSONObject customerResult = addCustomer(customer);
            System.out.println(customerResult.toString(1));
			break;
		
		case "restaurant":
			/* 
			 * Creating restaurant and adding
			 * to restaurantList
			 */
			String restaurantName = json.getString("name");
            String restaurantCuisine = json.getString("cuisine");
            LocalTime opening  = LocalTime.parse(json.getString("opening"));
            LocalTime closing = LocalTime.parse(json.getString("closing"));
            String menuName = json.getString("menu");
            double price = Double.parseDouble(json.getString("price"));
            
            Restaurant restaurant = getRestaurant(restaurantName, restaurantCuisine);
            if (restaurant == null) restaurant = new Restaurant(restaurantName, restaurantCuisine, opening, closing);
            
            Menu menu = getMenu(restaurant, menuName, price);
        	JSONObject restaurantResult = addRestaurant(restaurant, menu);
            System.out.println(restaurantResult.toString(1));
            break;
            
		case "login" :
			/* 
			 * Login, customer have to be Logged in
			 * to make order 
			 */
			String userName = json.getString("name");
            String userPassword = json.getString("password");
            
            Customer loginUser = new Customer(userName, userPassword);
            JSONObject loginResult = loginCustomer(loginUser);
            System.out.println(loginResult.toString(1));
            break;
            
		case "logout" :
			String userName1 = json.getString("name");
			
			Customer logoutUser = getCustomer(userName1);
			JSONObject logoutResult = logoutCustomer(logoutUser);
            System.out.println(logoutResult.toString(1));
			break;
            
		case "order" :
			int orderId1 = Integer.parseInt(json.getString("order"));
			String customerName1 = json.getString("customer");
			String restaurantName1 = json.getString("restaurant");
			String restaurantCuisine1 = json.getString("cuisine");
			String menuName1 = json.getString("menu");
			LocalTime orderTime1  = LocalTime.parse(json.getString("time"));
			
			Customer customerOrder1 = getCustomer(customerName1);
			Restaurant restaurantOrder1 = getRestaurant(restaurantName1, restaurantCuisine1);
			Menu menuOrder1 = getMenu(restaurantOrder1, menuName1);
			
			JSONObject orderResult = addOrder(orderId1, customerOrder1, restaurantOrder1, menuOrder1, orderTime1);
            System.out.println(orderResult.toString(1));
            break;
            
		case "cancel" :
			int orderId = Integer.parseInt(json.getString("order"));
			Order delOrder = getOrder(orderId);
			
			JSONObject cancelResult = cancelOrder(delOrder);
			System.out.println(cancelResult.toString(1));
			break;
		
		case "change" :
			int orderId2 = Integer.parseInt(json.getString("order"));
			String customerName2 = json.getString("customer");
			String restaurantName2 = json.getString("restaurant");
			String restaurantCuisine2 = json.getString("cuisine");
			String menuName2 = json.getString("menu");
			LocalTime orderTime2  = LocalTime.parse(json.getString("time"));
			
			Customer customerOrder2 = getCustomer(customerName2);
			Restaurant restaurantOrder2 = getRestaurant(restaurantName2, restaurantCuisine2);
			Menu menuOrder2 = getMenu(restaurantOrder2, menuName2);
			Order oldOrder = getOrder(orderId2);
			
			JSONObject changeResult = null;
			if (checkOrder(orderId2, customerOrder2, restaurantOrder2, menuOrder2)) {
				orderList.remove(oldOrder);
				changeResult = addOrder(orderId2, customerOrder2, restaurantOrder2, menuOrder2, orderTime2);
			} else {
				changeResult = new JSONObject();
				changeResult.put("status", "Rejected");
				changeResult.put("ERROR_CHANGE", "invalid order change");
			}
			System.out.println(changeResult.toString(1));
			break;
			
		case "deliver" :
			int orderId3 = Integer.parseInt(json.getString("order"));
			
			JSONObject deliverResult = new JSONObject();
			Order toDeliver = getOrder(orderId3);
			if (toDeliver != null) {
				toDeliver.setDeliver(true);
				deliverResult.put("status", "Success");
				deliverResult.put("SYSTEM_DELIVERY", "order [" + toDeliver.getId() + "] is delivering or delivered");
				
			} else {
				deliverResult.put("status", "Rejected");
				deliverResult.put("ERROR_DELIVERY", "invalid order");
			}
			System.out.println(deliverResult.toString(1));
			break;
		
		case "pay" :
			int orderId4 = Integer.parseInt(json.getString("order"));
			String paymentMethod = json.getString("payment");
			double amount = Double.parseDouble(json.getString("amount"));
			
			Order toPay = getOrder(orderId4);
			PaymentMethod pay = getPaymentMethod(paymentMethod);
			JSONObject paymentResult = new JSONObject();
			if(amount < pay.getTotalCharge(toPay.getMenuList())) {
				paymentResult.put("status", "Rejected");
				paymentResult.put("ERROR_$$", "insufficient amount");
			} else if (amount == pay.getTotalCharge(toPay.getMenuList())){
				toPay.setPay(true);
				paymentResult.put("status", "Success");
				paymentResult.put("SYSTEM_PAYMENT", "paid $" + pay.getTotalCharge(toPay.getMenuList()));
			} else {
				toPay.setPay(true);
				paymentResult.put("status", "Success");
				paymentResult.put("SYSTEM_PAYMENT", "paid $" + pay.getTotalCharge(toPay.getMenuList()));
				paymentResult.put("SYSTEM_PAYMENT", "balance $" + (amount - pay.getTotalCharge(toPay.getMenuList())));
			}
			System.out.println(paymentResult.toString(1));
			break;
		
		case "display":
			String things = json.getString("thing");
			
			JSONObject displayResult = new JSONObject();
			JSONArray info = new JSONArray();
			if (things.equals("restaurants")) {
				for (Restaurant r : restaurantList) {
					info.put(r.getName() + " (" + r.getCuisine() + ")");
				}
				displayResult.put("restaurants", info);
			}
			if (things.equals("orders")) {
				for (Order o : orderList) {
					if (o.isPay()) info.put("paid [" + o.getId() + "] ordered by " + o.getCustomerName());
					else info.put("not paid [" + o.getId() + "] ordered by " + o.getCustomerName());
				}
				displayResult.put("orders", info);
			}
			System.out.println(displayResult.toString(1));
			break;
		}
	}
	
	private JSONObject addCustomer(Customer customer) {
        JSONObject result = new JSONObject();
        if (customerList.contains(customer)) {
        	result.put("status", "Rejected");
        	result.put("ERROR_USER", customer.getName() + " exist");
        } else {
        	customerList.add(customer);
        	result.put("status", "Success");
        	result.put("SYSTEM_USER", "customer " + customer.getName() + " added");
        }
        return result;
    }    
	
	private JSONObject addRestaurant(Restaurant restaurant, Menu menu) {
        JSONObject result = new JSONObject();
        if (restaurantList.contains(restaurant)) {
        	if (restaurant.getMenuList().contains(menu)) {
        		result.put("status", "Rejected");
            	result.put("ERROR_MENU", menu.getName() + " exist in " 
            			+ restaurant.getName() + " (" + restaurant.getCuisine() + ")");
        	} else if (!restaurant.getMenuList().contains(menu)) {
        		restaurant.setMenuList(menu);
    			result.put("status", "Success");
            	result.put("SYSTEM_MENU", "menu " + menu.getName() + " " + menu.getPrice() + " added to " 
            			+ restaurant.getName() + " (" + restaurant.getCuisine() + ")");
        	} else {
        		result.put("status", "Rejected");
            	result.put("ERROR_RESTAURANT", restaurant.getName() + " that cooks " 
            			+ restaurant.getCuisine() + " cuisine exist");
        	}
        } else {
        	restaurantList.add(restaurant);
        	restaurant.setMenuList(menu);
        	result.put("status", "Success");
        	result.put("SYSTEM_RESTAURANT", restaurant.getName() + " (" + restaurant.getCuisine() + ") added");
        }
        return result;
    }    
	
	private JSONObject loginCustomer(Customer customer) {
        JSONObject result = new JSONObject();
        if (customerList.contains(customer)) {
        	for (Customer c : customerList) {
        		if (c.equals(customer)) {
        			if (c.isLoggedIn()) {
        				result.put("status", "Rejected");
        	        	result.put("ERROR_LOGIN", c.getName() + " is logged in");
        			} else {
        				c.setLoggedIn(true);
        				result.put("status", "Success");
        	        	result.put("SYSTEM_LOGIN", c.getName());
        			}
        			break;
        		}
        	}
        } else {
        	result.put("status", "Rejected");
        	result.put("ERROR_LOGIN", "Invalid Username or Password");
        }
        return result;
    } 
	
	private JSONObject logoutCustomer(Customer customer) {
		JSONObject result = new JSONObject();
		if (customerList.contains(customer)) {
        	for (Customer c : customerList) {
        		if (c.equals(customer)) {
        			if (c.isLoggedIn()) {
        				c.setLoggedIn(false);
        				result.put("status", "Success");
        	        	result.put("SYSTEM_LOGOUT", c.getName() + " logging out");
        			} else {
        				result.put("status", "Rejected");
        	        	result.put("ERROR_LOGOUT", c.getName() + " is Logged out");
        			}
        			break;
        		}
        	}
        } else {
        	result.put("status", "Rejected");
        	result.put("ERROR_LOGOUT", "Invalid Username");
        }
		return result;
	}
	
	private JSONObject addOrder(int number, Customer customer, Restaurant restaurant, Menu menu, LocalTime orderTime) {
        JSONObject result = new JSONObject();
        if (customer == null || restaurant == null || menu == null) {
        	result.put("status", "Rejected");
        	result.put("ERROR_404", "customer or restaurant or menu doesn't exist");
        } else if (!customer.isLoggedIn()) {
        	result.put("status", "Rejected");
        	result.put("ERROR_USER", customer.getName() + "please login first");
        } else {
        	Order order = getOrder(number, customer, restaurant);
        	if (!order.checkTime(orderTime))  {
        		result.put("status", "Rejected");
            	result.put("ERROR_RESTAURANT", "restaurant is closed"); 
        	} else if (orderList.contains(order)) {
        		if (restaurant.getMenuList().contains(menu) && !order.getMenuList().contains(menu)) {
        			order.setMenuList(menu);
        			result.put("status", "Success");
                	result.put("SYSTEM_UPDATE", menu.getName() + " added to order [" + order.getId() + "]");
        		} else if (!restaurant.getMenuList().contains(menu)) {
        			result.put("status", "Rejected");
                	result.put("ERROR_MENU", menu.getName() + " doesn't exist in " 
                			+ restaurant.getName() + " (" + restaurant.getCuisine() + ")");
        		} else {
        			result.put("status", "Rejected");
                	result.put("ERROR_ORDER", menu.getName() + " already in order [" + order.getId() + "]");
        		}
            } else {
            	orderList.add(order);
            	order.setMenuList(menu);
            	result.put("status", "Success");
            	result.put("order", order.getId());
            	result.put("customer", order.getCustomerName());
            	result.put("SYSTEM_ORDER", "order at " + order.getRestaurantName());
            }
        }
        return result;
    }    
	
	private JSONObject cancelOrder(Order order) {
		JSONObject result = new JSONObject();
		if (order == null) {
			result.put("status", "Rejected");
        	result.put("ERROR_404", "order doesn't exist");
		}else if (!orderList.contains(order)) {
			result.put("status", "Rejected");
        	result.put("ERROR_CANCEL", "order [" + order.getId() + "] doesn't exist");
		} else {
			if (order.isDeliver()) {
				result.put("status", "Rejected");
	        	result.put("ERROR_CANCEL", "order [" + order.getId() + "] is delivered or is OTW");
			} else {
				orderList.remove(order);
				result.put("status", "Success");
	        	result.put("SYSTEM_CANCEL", "order [" + order.getId() + "] is cancelled");
			}
		}
		return result;
	}
	
	private boolean checkOrder(int id, Customer customer, Restaurant restaurant, Menu menu) {
		Order order = getOrder(id);
		if (order == null) return false;
		if (!order.getCustomerName().equals(customer.getName())) return false;
		if (order.isDeliver()) return false;
		if (customer == null || restaurant == null || menu == null) return false;
		if (!customerList.contains(customer) || !customer.isLoggedIn()) return false;
		if (!restaurantList.contains(restaurant)) return false;
		if (!restaurant.getMenuList().contains(menu)) return false;
		return true;
	}
	
	private Customer getCustomer(String name) {
		Customer customer = null;
		for (Customer c : customerList) {
			if (c.getName().equals(name)) {
				customer = c;
				break;
			}
		}
		return customer;
	}
	
	private Restaurant getRestaurant(String name, String cuisine) {
		Restaurant restaurant = null;
		for (Restaurant r : restaurantList) {
			if (r.getName().equals(name) && r.getCuisine().equals(cuisine)) {
				restaurant = r;
				break;
			}
		}
		return restaurant;
	}
	
	private Menu getMenu(Restaurant restaurant, String name) {
		Menu menu = null;
		for (Menu m : restaurant.getMenuList()) {
			if(m.getName().equals(name)) {
				menu = m;
				break;
			}
		}
		return menu;
	}
	
	private Menu getMenu(Restaurant restaurant, String name, double price) {
		Menu menu = new Menu(name, price);
		for (Menu m : restaurant.getMenuList()) {
			if (m.equals(menu)) {
				menu = m;
				break;
			}
		}
		return menu;
	}
	
	private Order getOrder(int id) {
		Order order = null;
		for (Order o : orderList) {
			if (o.getId() == id) {
				order = o;
				break;
			}
		}
		return order;
	}
	
	private Order getOrder(int id, Customer customer, Restaurant restaurant) {
		Order order = new Order(id, customer, restaurant);
		for (Order o : orderList) {
			if (o.equals(order)) {
				order = o;
				break;
			}
		}
		return order;
	}
	
	private PaymentMethod getPaymentMethod(String paymentMethod) {
		PaymentMethod pay = null;
		if (paymentMethod.equals("bank")) pay = new BankTransfer();
		if (paymentMethod.equals("cash")) pay = new CashPayment();
		if (paymentMethod.equals("card")) pay = new CardPayment();
		return pay;
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
