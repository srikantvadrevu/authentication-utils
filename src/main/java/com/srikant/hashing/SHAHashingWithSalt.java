package com.srikant.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// Generates a stronger hash than MD5. Collision are still possible but the probability is very low.
// There are 4 types of implementations - SHA-1, SHA-256, SHA-354, SHA-512.
// SHA-512 is the strongest and generates a 512 bit hash.
public class SHAHashingWithSalt {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String passwordToHash = "password";
		byte[] salt = getSalt();
		String securePassword = get_SHA_1_SecurePassword(passwordToHash, salt);
		System.out.println(securePassword);
		String regeneratedPassowrdToVerify = get_SHA_1_SecurePassword(passwordToHash, salt);
		System.out.println(regeneratedPassowrdToVerify);
	}

	private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
}
