package com.wickwick.model;

public class Menu {
	private String name;
	private double price;
	
	public Menu(String name, double price) {
		setName(name);
		setPrice(price);
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	private void setPrice(double price) {
		this.price = price;
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
		Menu otherObj = (Menu) obj;
		return this.name.equals(otherObj.name);
	}
}
