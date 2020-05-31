package statics;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("unused")
public class Mail {

	private static final String from = "confpcf20@gmail.com";
	private static final String password = "prendiamocisto30";
	private static final String host = "smtp.gmail.com";
	private String object = "Registrazione avvenuta";
	private String text;
	private String to;

	public Mail(String to) {

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
			Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private MimeMessage testoHtml() throws AddressException, MessagingException {
		MimeMessage msg = null;
		msg.setFrom(new InternetAddress(from));
		msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		msg.setSubject(object);
		msg.setText(text);
		msg.setSentDate(new Date());
		MimeBodyPart textPart = new MimeBodyPart();
		//textPart.setText("<html><he
		return msg;
	}
	

}
