package com.wickwick.model;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Customer {
	private String name;
	private String password;
	
	public Customer(String name, String password) {
		setName(name);
		if(encyprtPassword(name, password) != null) setPassword(encyprtPassword(name, password));
		else System.out.println("Encryption failed");
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
	
	private String encyprtPassword(String name, String password) {
		Key aesKey = new SecretKeySpec(password.getBytes(), "AES");
		String encrypted = null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encryption = cipher.doFinal(name.getBytes());
			encrypted = new String(encryption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypted; 
	}
}
