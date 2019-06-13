package com.wickwick.utils;

import java.util.ArrayList;

import com.wickwick.model.Menu;

public interface PaymentMethod {
	abstract double getTotalCharge(ArrayList<Menu> menuList);
}
