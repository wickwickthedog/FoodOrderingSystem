package com.wickwick.model;

import java.util.ArrayList;

import com.wickwick.utils.PaymentMethod;

public class CashPayment implements PaymentMethod{

	@Override
	public double getTotalCharge(ArrayList<Menu> menuList) {
		// DONE Auto-generated method stub
		double totalCharge = 0;
		for (Menu m : menuList) {
			totalCharge += m.getPrice(); 
		}
		return totalCharge;
	}

}
