package com.wickwick.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Restaurant {
	private String name;
	private String cuisine;
	private LocalTime openingHour;
	private LocalTime closingHour;
	private ArrayList<Menu> menuList;
	
	public Restaurant(String name, String cuisine, LocalTime openingHour, LocalTime closingHour) {
		setName(name);
		setCuisine(cuisine);
		setOpeningHour(openingHour);
		setClosingHour(closingHour);
		menuList = new ArrayList<Menu>();
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
	 * @return the cuisine
	 */
	public String getCuisine() {
		return cuisine;
	}
	/**
	 * @param cuisine the cuisine to set
	 */
	private void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	/**
	 * @return the openingHour
	 */
	public LocalTime getOpeningHour() {
		return openingHour;
	}
	/**
	 * @param openingHour the openingHour to set
	 */
	private void setOpeningHour(LocalTime openingHour) {
		this.openingHour = openingHour;
	}
	/**
	 * @return the closingHour
	 */
	public LocalTime getClosingHour() {
		return closingHour;
	}
	/**
	 * @param closingHour the closingHour to set
	 */
	private void setClosingHour(LocalTime closingHour) {
		this.closingHour = closingHour;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// DONE Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Restaurant otherObj = (Restaurant) obj;
		return this.name.equals(otherObj.name) 
				&& this.cuisine.equals(otherObj.cuisine);
	}
}
