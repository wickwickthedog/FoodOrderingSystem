package com.wickwick.model;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Order {
	private int id;
	private boolean deliver;
	private Customer customer;
	private Restaurant restaurant;
	private ArrayList<Menu> menuList;
	private boolean pay;
	
	public Order(int id, Customer customer, Restaurant restaurant) {
		setId(id);
		setDeliver(false);
		setCustomer(customer);
		setRestaurant(restaurant);
		setPay(false);
		menuList = new ArrayList<Menu>();
	}
	/**
	 * @return the number
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param number the number to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the deliver
	 */
	public boolean isDeliver() {
		return deliver;
	}
	/**
	 * @param deliver the deliver to set
	 */
	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	private void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
	/**
	 * @param restaurant the restaurant to set
	 */
	private void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	/**
	 * @return the menuList
	 */
	public ArrayList<Menu> getMenuList() {
		return menuList;
	}
	/**
	 * @param menu, menuList to add
	 */
	public void setMenuList(Menu menu) {
		this.menuList.add(menu);
	}
	/**
	 * @return the pay
	 */
	public boolean isPay() {
		return pay;
	}
	/**
	 * @param pay the pay to set
	 */
	public void setPay(boolean pay) {
		this.pay = pay;
	}
	
	public String getCustomerName() {
		return customer.getName();
	}
	
	public String getRestaurantName() {
		return restaurant.getName() + " (" + restaurant.getCuisine() + ")";
	}
	
	public boolean checkTime(LocalTime orderTime) {
		if (orderTime.isBefore(this.restaurant.getOpeningHour()) 
				|| orderTime.equals(this.restaurant.getOpeningHour().plus(29, ChronoUnit.MINUTES))) return false;
		if (orderTime.isAfter(this.restaurant.getClosingHour()) 
				|| orderTime.equals(this.restaurant.getClosingHour().minus(29, ChronoUnit.MINUTES))) return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Order otherObj = (Order) obj;
		return this.id == otherObj.id
				&& this.customer.equals(otherObj.customer) 
				&& this.restaurant.equals(otherObj.restaurant);
	}
}
