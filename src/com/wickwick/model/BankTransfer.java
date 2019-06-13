package com.wickwick.model;

import java.util.ArrayList;

import com.wickwick.utils.PaymentMethod;

public class BankTransfer implements PaymentMethod{

	@Override
	public double getTotalCharge(ArrayList<Menu> menuList) {
		// DONE Auto-generated method stub
		double surcharge = 0.01;
		double totalCharge = 0;
		for (Menu m : menuList) {
			totalCharge += m.getPrice(); 
		}
		return totalCharge + totalCharge * surcharge;
	}

}
