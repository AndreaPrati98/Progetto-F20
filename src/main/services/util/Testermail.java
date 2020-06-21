package main.services.util;

public class Testermail {

	public static void main(String[] args) {
		Mail m =new Mail("alessandrocapici.ac@gmail.com", "Alessandro");
		HashingPassword hp= new HashingPassword();
		System.out.println(hp.getHashPsw("prova"));
	}

}
