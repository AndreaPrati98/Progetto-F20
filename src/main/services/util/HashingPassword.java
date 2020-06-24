package main.services.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingPassword {
	
	private MessageDigest digest;

	public HashingPassword() {
		
	}
	
	public String getHashPsw(String psw) {
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(psw.getBytes("utf8"));
			psw=String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return psw;
	}

	
}
