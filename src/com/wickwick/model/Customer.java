package com.wickwick.model;

import java.util.ArrayList;

import com.wickwick.utils.Encryption;

public class Customer implements Encryption{
	private String name;
	private String password;
	private boolean loggedIn;
	private ArrayList<Order> orderList;
	
	public Customer(String name, String password) {
		setName(name);
		if(encyprtPassword(password) != null) setPassword(encyprtPassword(password));
		else System.out.println("Encryption failed"); // not a good way to debug
		setLoggedIn(false);
		orderList = new ArrayList<Order>();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	private void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the status
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	/**
	 * @param status the status to set
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	/**
	 * @return the orderList
	 */
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	/**
	 * @param order, orderList to add
	 */
	public void setOrderList(Order order) {
		this.orderList.add(order);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// DONE Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Customer otherObj = (Customer) obj;
		return this.name.equals(otherObj.name) 
				&& this.password.equals(otherObj.password);
	}
}
