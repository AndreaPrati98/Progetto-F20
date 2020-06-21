package main.model.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.*;
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

@SuppressWarnings("unused")
public class Mail {

	private static final String from = "confpcf20@gmail.com";
	private static final String password = "prendiamocisto30";
	private static final String host = "smtp.gmail.com";
	private String object = "Registrazione avvenuta";
	private String text;
	private String to;
	private String user;

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
			String htmlText = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
					"<!--<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#000000;background-image:url('cid:imagewelcome');background -->\r\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\r\n" + 
					"<head>\r\n" + 
					"<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->\r\n" + 
					"<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\r\n" + 
					"<meta content=\"width=device-width\" name=\"viewport\"/>\r\n" + 
					"<!--[if !mso]><!-->\r\n" + 
					"<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"<title></title>\r\n" + 
					"<!--[if !mso]><!-->\r\n" + 
					"<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\"/>\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"<style type=\"text/css\">\r\n" + 
					"		body {\r\n" + 
					"			margin: 0;\r\n" + 
					"			padding: 0;\r\n" + 
					"		}\r\n" + 
					"\r\n" + 
					"		table,\r\n" + 
					"		td,\r\n" + 
					"		tr {\r\n" + 
					"			vertical-align: top;\r\n" + 
					"			border-collapse: collapse;\r\n" + 
					"		}\r\n" + 
					"\r\n" + 
					"		* {\r\n" + 
					"			line-height: inherit;\r\n" + 
					"		}\r\n" + 
					"\r\n" + 
					"		a[x-apple-data-detectors=true] {\r\n" + 
					"			color: inherit !important;\r\n" + 
					"			text-decoration: none !important;\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"<style id=\"media-query\" type=\"text/css\">\r\n" + 
					"		@media (max-width: 670px) {\r\n" + 
					"\r\n" + 
					"			.block-grid,\r\n" + 
					"			.col {\r\n" + 
					"				min-width: 320px !important;\r\n" + 
					"				max-width: 100% !important;\r\n" + 
					"				display: block !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.block-grid {\r\n" + 
					"				width: 100% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.col {\r\n" + 
					"				width: 100% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.col>div {\r\n" + 
					"				margin: 0 auto;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			img.fullwidth,\r\n" + 
					"			img.fullwidthOnMobile {\r\n" + 
					"				max-width: 100% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col {\r\n" + 
					"				min-width: 0 !important;\r\n" + 
					"				display: table-cell !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack.two-up .col {\r\n" + 
					"				width: 50% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col.num4 {\r\n" + 
					"				width: 33% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col.num8 {\r\n" + 
					"				width: 66% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col.num4 {\r\n" + 
					"				width: 33% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col.num3 {\r\n" + 
					"				width: 25% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col.num6 {\r\n" + 
					"				width: 50% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.no-stack .col.num9 {\r\n" + 
					"				width: 75% !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.video-block {\r\n" + 
					"				max-width: none !important;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.mobile_hide {\r\n" + 
					"				min-height: 0px;\r\n" + 
					"				max-height: 0px;\r\n" + 
					"				max-width: 0px;\r\n" + 
					"				display: none;\r\n" + 
					"				overflow: hidden;\r\n" + 
					"				font-size: 0px;\r\n" + 
					"			}\r\n" + 
					"\r\n" + 
					"			.desktop_hide {\r\n" + 
					"				display: block !important;\r\n" + 
					"				max-height: none !important;\r\n" + 
					"			}\r\n" + 
					"		}\r\n" + 
					"	</style>\r\n" + 
					"</head>\r\n" + 
					"<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #F5F5F5;\">\r\n" + 
					"<!--[if IE]><div class=\"ie-browser\"><![endif]-->\r\n" + 
					"<table bgcolor=\"#F5F5F5\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #F5F5F5; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
					"<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\r\n" + 
					"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color:#F5F5F5\"><![endif]-->\r\n" + 
					"<div style=\"background-color:transparent;\">\r\n" + 
					"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\r\n" + 
					"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n" + 
					"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\r\n" + 
					"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:transparent;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
					"<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n" + 
					"<div style=\"width:100% !important;\">\r\n" + 
					"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
					"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
					"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\r\n" + 
					"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"10\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 10px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
					"<td height=\"10\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" + 
					"</td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" + 
					"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
					"</div>\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<div style=\"background-color:transparent;\">\r\n" + 
					"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #eeeeee;\">\r\n" + 
					"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#eeeeee;\">\r\n" + 
					"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:#eeeeee\"><![endif]-->\r\n" + 
					"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:#eeeeee;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:5px; padding-bottom:60px;\"><![endif]-->\r\n" + 
					"<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n" + 
					"<div style=\"width:100% !important;\">\r\n" + 
					"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
					"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:60px; padding-right: 25px; padding-left: 25px;\">\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 15px; padding-top: 20px; padding-bottom: 0px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\r\n" + 
					"<div style=\"color:#052d3d;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;line-height:1.5;padding-top:20px;padding-right:10px;padding-bottom:0px;padding-left:15px;\">\r\n" + 
					"<div style=\"font-size: 12px; line-height: 1.5; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif; color: #052d3d; mso-line-height-alt: 18px;\">\r\n" + 
					"<p style=\"font-size: 50px; line-height: 1.5; text-align: center; font-family: inherit; word-break: break-word; mso-line-height-alt: 75px; margin: 0;\"><span style=\"font-size: 50px;\"><strong><span style=\"font-size: 50px;\"><span style=\"font-size: 38px;\">WELCOME</span></span></strong></span></p>\r\n" + 
					"<p style=\"font-size: 34px; line-height: 1.5; text-align: center; font-family: inherit; word-break: break-word; mso-line-height-alt: 51px; margin: 0;\"><span style=\"font-size: 34px;\"><strong><span style=\"font-size: 34px;\"><span style=\"color: #2190e3; font-size: 34px;\">"+user+"</span></span></strong></span></p>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
					"<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\r\n" + 
					"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]-->\r\n" + 
					"<div style=\"font-size:1px;line-height:45px\"> </div><img align=\"center\" alt=\"Image\" border=\"0\" class=\"center fixedwidth\" src=\"cid:logo\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 540px; display: block;\" title=\"Image\" width=\"540\"/>\r\n" + 
					"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
					"</div>\r\n" + 
					"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\r\n" + 
					"<div style=\"color:#555555;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n" + 
					"<div style=\"font-size: 12px; line-height: 1.2; color: #555555; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 14px;\">\r\n" + 
					"<p style=\"font-size: 18px; line-height: 1.2; text-align: center; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px; color: #000000;\">Thanks for signing up for our updates. We’ll be sending an occasional email with everything new and good that you’ll probably want to know about: new products, posts, promos, and parties.</span></p>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
					"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
					"</div>\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<div style=\"background-color:transparent;\">\r\n" + 
					"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\r\n" + 
					"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n" + 
					"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\r\n" + 
					"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:transparent;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:20px; padding-bottom:60px;\"><![endif]-->\r\n" + 
					"<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n" + 
					"<div style=\"width:100% !important;\">\r\n" + 
					"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
					"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:20px; padding-bottom:60px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\r\n" + 
					"<div style=\"color:#555555;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n" + 
					"<div style=\"font-size: 12px; line-height: 1.5; color: #555555; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 18px;\">\r\n" + 
					"<p style=\"font-size: 14px; line-height: 1.5; text-align: center; word-break: break-word; mso-line-height-alt: 21px; margin: 0;\">NetShop - Lorem ipsum dolor sit amet hasellus sagittis aliquam luctus. </p>\r\n" + 
					"<p style=\"font-size: 14px; line-height: 1.5; text-align: center; word-break: break-word; mso-line-height-alt: 21px; margin: 0;\">329 California St, San Francisco, CA 94118</p>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
					"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
					"</div>\r\n" + 
					"<!--<![endif]-->\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
					"</td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" + 
					"<!--[if (IE)]></div><![endif]-->\r\n" + 
					"</body>\r\n" + 
					"</html>";
			messageBodyPart.setContent(htmlText, "text/html");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource f1 = new FileDataSource("Website\\IMG\\pc1.jpg");
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
