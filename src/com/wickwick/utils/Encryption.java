package com.wickwick.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public interface Encryption {
	public default Key getSalt() {
		String salt = "wickwickthedog19";
		Key aesKey = new SecretKeySpec(salt.getBytes(), "AES");
		return aesKey;
	}
	
	public default String encyprtPassword(String password) {
		Key aesKey = getSalt();
		String encrypted = null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encryption = cipher.doFinal(password.getBytes());
			encrypted = new String(encryption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypted; 
	}
}


