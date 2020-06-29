package main.services.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingPassword {
	
	private MessageDigest digest;
/**
 * classe di supporto (algoritmo specifico che per alleggerire il codice è stato messo in una classe dedicata= 
 * 
 */
	public HashingPassword() {
		
	}
	/**
	 * questo metodo consente di usare la funzione di hash su una password garantendo la privacy dei dati sensibili 
	 * (in questo caso solo della password) dei nostri utenti in particolare noi usiamo una  funzione di hash a 256 bit
	 * @param psw
	 * @return
	 */
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
