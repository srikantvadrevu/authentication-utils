package com.srikant.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// MD5 is susceptible to brute-force and dictionary attacks.
// MD5 is also not collision resistant which means that different passwords can eventually result in the same hash.
// Consider adding some salt for security.
public class MD5Hashing {

	public static void main(String[] args) {
		String passwordToHash = "password";
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();
			// bytes[] has bytes in decimal format; should convert it to hexadecimal format.
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
	}

}
