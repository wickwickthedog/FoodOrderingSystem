package com.wickwick.model;

import java.time.LocalDate;

public class Restaurant {
	private String name;
	private String cuisine;
	private LocalDate openingHour;
	private LocalDate closingHour;
	
	public Restaurant(String name, String cuisine, LocalDate openingHour, LocalDate closingHour) {
		setName(name);
		setCuisine(cuisine);
		setOpeningHour(openingHour);
		setClosingHour(closingHour);
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
	public LocalDate getOpeningHour() {
		return openingHour;
	}
	/**
	 * @param openingHour the openingHour to set
	 */
	private void setOpeningHour(LocalDate openingHour) {
		this.openingHour = openingHour;
	}
	/**
	 * @return the closingHour
	 */
	public LocalDate getClosingHour() {
		return closingHour;
	}
	/**
	 * @param closingHour the closingHour to set
	 */
	private void setClosingHour(LocalDate closingHour) {
		this.closingHour = closingHour;
	}
	
	
}
