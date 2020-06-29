package main.services.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	private static final String from = "confpcf20@gmail.com";
	private static final String password = "prendiamocisto30";
	private static final String host = "smtp.gmail.com";
	private String object = "Registrazione avvenuta";
	private String text;
	@SuppressWarnings("unused")
	private String to;
	@SuppressWarnings("unused")
	private String user;

	/**
	 * we use this method to send an email a user when to a user when signs up on
	 * the site or for other operations
	 * 
	 * @param to
	 * @param user
	 */
	public Mail(String to, String user) {

		this.to = to;
		this.text = "Benvenuto nel nostro sito";
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject(object);
			msg.setText(text);
			msg.setSentDate(new Date());
			MimeMultipart mm = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "";
			try {
				BufferedReader in = new BufferedReader(new FileReader("src/main/webapp/website/mail.html"));
				String str;
				while ((str = in.readLine()) != null) {
					htmlText += str;
				}
				in.close();
			} catch (IOException e) {
			}
			htmlText=htmlText.replace("*UserName*", user);
			messageBodyPart.setContent(htmlText, "text/html");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource f1 = new FileDataSource("Website\\IMG\\ris8mail.jpeg");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<logo>");
			mm.addBodyPart(messageBodyPart);

			// put everything together
			msg.setContent(mm);
			Transport.send(msg);
			System.out.println("fatto");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
