package statics;

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
			String htmlText = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\r\n"
					+ "<head>\r\n"
					+ "<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->\r\n"
					+ "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\r\n"
					+ "<meta content=\"width=device-width\" name=\"viewport\"/>\r\n" + "<!--[if !mso]><!-->\r\n"
					+ "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\r\n" + "<!--<![endif]-->\r\n"
					+ "<title></title>\r\n" + "<!--[if !mso]><!-->\r\n"
					+ "<link href=\"https://fonts.googleapis.com/css?family=Bitter\" rel=\"stylesheet\" type=\"text/css\"/>\r\n"
					+ "<link href=\"https://fonts.googleapis.com/css?family=Droid+Serif\" rel=\"stylesheet\" type=\"text/css\"/>\r\n"
					+ "<link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\" type=\"text/css\"/>\r\n"
					+ "<!--<![endif]-->\r\n" + "<style type=\"text/css\">\r\n" + "		body {\r\n"
					+ "			margin: 0;\r\n" + "			padding: 0;\r\n" + "		}\r\n" + "\r\n"
					+ "		table,\r\n" + "		td,\r\n" + "		tr {\r\n" + "			vertical-align: top;\r\n"
					+ "			border-collapse: collapse;\r\n" + "		}\r\n" + "\r\n" + "		* {\r\n"
					+ "			line-height: inherit;\r\n" + "		}\r\n" + "\r\n"
					+ "		a[x-apple-data-detectors=true] {\r\n" + "			color: inherit !important;\r\n"
					+ "			text-decoration: none !important;\r\n" + "		}\r\n" + "	</style>\r\n"
					+ "<style id=\"media-query\" type=\"text/css\">\r\n" + "		@media (max-width: 670px) {\r\n"
					+ "\r\n" + "			.block-grid,\r\n" + "			.col {\r\n"
					+ "				min-width: 320px !important;\r\n" + "				max-width: 100% !important;\r\n"
					+ "				display: block !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.block-grid {\r\n" + "				width: 100% !important;\r\n" + "			}\r\n"
					+ "\r\n" + "			.col {\r\n" + "				width: 100% !important;\r\n"
					+ "			}\r\n" + "\r\n" + "			.col>div {\r\n" + "				margin: 0 auto;\r\n"
					+ "			}\r\n" + "\r\n" + "			img.fullwidth,\r\n" + "			img.fullwidthOnMobile {\r\n"
					+ "				max-width: 100% !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.no-stack .col {\r\n" + "				min-width: 0 !important;\r\n"
					+ "				display: table-cell !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.no-stack.two-up .col {\r\n" + "				width: 50% !important;\r\n"
					+ "			}\r\n" + "\r\n" + "			.no-stack .col.num4 {\r\n"
					+ "				width: 33% !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.no-stack .col.num8 {\r\n" + "				width: 66% !important;\r\n"
					+ "			}\r\n" + "\r\n" + "			.no-stack .col.num4 {\r\n"
					+ "				width: 33% !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.no-stack .col.num3 {\r\n" + "				width: 25% !important;\r\n"
					+ "			}\r\n" + "\r\n" + "			.no-stack .col.num6 {\r\n"
					+ "				width: 50% !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.no-stack .col.num9 {\r\n" + "				width: 75% !important;\r\n"
					+ "			}\r\n" + "\r\n" + "			.video-block {\r\n"
					+ "				max-width: none !important;\r\n" + "			}\r\n" + "\r\n"
					+ "			.mobile_hide {\r\n" + "				min-height: 0px;\r\n"
					+ "				max-height: 0px;\r\n" + "				max-width: 0px;\r\n"
					+ "				display: none;\r\n" + "				overflow: hidden;\r\n"
					+ "				font-size: 0px;\r\n" + "			}\r\n" + "\r\n" + "			.desktop_hide {\r\n"
					+ "				display: block !important;\r\n" + "				max-height: none !important;\r\n"
					+ "			}\r\n" + "		}\r\n" + "	</style>\r\n" + "</head>\r\n"
					+ "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #FFFFFF;\">\r\n"
					+ "<!--[if IE]><div class=\"ie-browser\"><![endif]-->\r\n"
					+ "<table bgcolor=\"#FFFFFF\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\r\n"
					+ "<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color:#FFFFFF\"><![endif]-->\r\n"
					+ "<div style=\"background-color:#eeeeee;\">\r\n"
					+ "<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #000000;\">\r\n"
					+ "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#000000;background-image:url('cid:imagewelcome');background-position:center top;background-repeat:no-repeat\">\r\n"
					+ "<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#eeeeee;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:#000000\"><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:#000000;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n"
					+ "<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n"
					+ "<div style=\"width:100% !important;\">\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n"
					+ "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n"
					+ "<!--<![endif]-->\r\n"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 60px; padding-right: 60px; padding-bottom: 60px; padding-left: 60px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 15px; padding-right: 15px; padding-bottom: 15px; padding-left: 15px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top: 0px; padding-bottom: 0px; font-family: 'Times New Roman', Georgia, serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:TimesNewRoman, 'Times New Roman', Times, Beskerville, Georgia, serif;line-height:1.2;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; font-family: TimesNewRoman, 'Times New Roman', Times, Beskerville, Georgia, serif; color: #555555; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; font-family: TimesNewRoman, 'Times New Roman', Times, Beskerville, Georgia, serif; mso-line-height-alt: 17px; margin: 0;\"><span style=\"color: #ffffff;\"><span style=\"font-size: 58px;\"><em>Welcome</em></span><strong><span style=\"font-size: 64px;\"><br/></span></strong></span></p>\r\n"
					+ "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; font-family: TimesNewRoman, 'Times New Roman', Times, Beskerville, Georgia, serif; mso-line-height-alt: 17px; margin: 0;\"><span style=\"color: #ffffff;\"><span style=\"font-size: 58px;\"><em>"
					+ user + "</em></span></span></p>\r\n" + "</div>\r\n" + "</div>\r\n"
					+ "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #555555; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 17px; line-height: 1.2; word-break: break-word; text-align: center; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 20px; mso-ansi-font-size: 18px; margin: 0;\"><span style=\"color: #ffffff; font-size: 17px; mso-ansi-font-size: 18px;\">Get Your Configuration</span></p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 60px; padding-right: 60px; padding-bottom: 60px; padding-left: 60px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 60px; padding-right: 60px; padding-bottom: 60px; padding-left: 60px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 25px; padding-right: 25px; padding-bottom: 25px; padding-left: 25px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n" + "</div>\r\n" + "<!--<![endif]-->\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<div style=\"background-color:#eeeeee;\">\r\n"
					+ "<div class=\"block-grid two-up\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #f7f7f7;\">\r\n"
					+ "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#f7f7f7;\">\r\n"
					+ "<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#eeeeee;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:#f7f7f7\"><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"325\" style=\"background-color:#f7f7f7;width:325px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:20px; padding-bottom:0px;\"><![endif]-->\r\n"
					+ "<div class=\"col num6\" style=\"min-width: 320px; max-width: 325px; display: table-cell; vertical-align: top; width: 325px;\">\r\n"
					+ "<div style=\"width:100% !important;\">\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n"
					+ "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:20px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\r\n"
					+ "<!--<![endif]-->\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top: 0px; padding-bottom: 5px; font-family: Arial, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:0px;padding-right:25px;padding-bottom:5px;padding-left:25px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 16px; line-height: 1.2; word-break: break-word; text-align: left; mso-line-height-alt: 19px; margin: 0;\"><span style=\"font-size: 16px;\"><strong>Satana edition</strong></span></p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<div align=\"left\" class=\"img-container left fixedwidth\" style=\"padding-right: 0px;padding-left: 40px;\">\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 40px;\" align=\"left\"><![endif]--><img alt=\"Alternate text\" border=\"0\" class=\"left fixedwidth\" src=\"cid:imagerate\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 130px; display: block;\" title=\"Alternate text\" width=\"130\"/>\r\n"
					+ "<div style=\"font-size:1px;line-height:15px\"> </div>\r\n"
					+ "<!--[if mso]></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 40px; padding-left: 40px; padding-top: 5px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:5px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: left; mso-line-height-alt: 17px; margin: 0;\">Prestazioni infernali</p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top: 15px; padding-bottom: 5px; font-family: Arial, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:15px;padding-right:25px;padding-bottom:5px;padding-left:25px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 20px; line-height: 1.2; word-break: break-word; text-align: left; mso-line-height-alt: 24px; margin: 0;\"><span style=\"color: #339966; font-size: 20px;\"><strong> <span style=\"color: #0079ff;\">  Price: 1249$</span></strong></span></p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (!mso)&(!IE)]><!-->\r\n" + "</div>\r\n" + "<!--<![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"325\" style=\"background-color:#f7f7f7;width:325px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px;\"><![endif]-->\r\n"
					+ "<div class=\"col num6\" style=\"min-width: 320px; max-width: 325px; display: table-cell; vertical-align: top; width: 325px;\">\r\n"
					+ "<div style=\"width:100% !important;\">\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n"
					+ "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\r\n"
					+ "<!--<![endif]-->\r\n"
					+ "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]--><img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\"cid:imagesatana\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 325px; display: block;\" title=\"Alternate text\" width=\"325\"/>\r\n"
					+ "<!--[if mso]></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "<!--[if (!mso)&(!IE)]><!-->\r\n" + "</div>\r\n" + "<!--<![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<div style=\"background-color:#eeeeee;\">\r\n"
					+ "<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n"
					+ "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\r\n"
					+ "<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#eeeeee;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:#ffffff;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px;\"><![endif]-->\r\n"
					+ "<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n"
					+ "<div style=\"width:100% !important;\">\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n"
					+ "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\r\n"
					+ "<!--<![endif]-->\r\n"
					+ "<div align=\"center\" class=\"img-container center autowidth\" style=\"padding-right: 0px;padding-left: 0px;\">\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]--><img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center autowidth\" src=\"cid:imagelgbt\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 650px; display: block;\" title=\"Alternate text\" width=\"650\"/>\r\n"
					+ "<!--[if mso]></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "<!--[if (!mso)&(!IE)]><!-->\r\n" + "</div>\r\n" + "<!--<![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<div style=\"background-color:#eeeeee;\">\r\n"
					+ "<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n"
					+ "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;background-image:;background-position:top left;background-repeat:no-repeat\">\r\n"
					+ "<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#eeeeee;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:#ffffff;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:25px; padding-bottom:5px;background-color:#f7f7f7;\"><![endif]-->\r\n"
					+ "<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n"
					+ "<div style=\"background-color:#f7f7f7;width:100% !important;\">\r\n"
					+ "<!--[if (!mso)&(!IE)]><!-->\r\n"
					+ "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:25px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n"
					+ "<!--<![endif]-->\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top: 0px; padding-bottom: 5px; font-family: Arial, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:0px;padding-right:25px;padding-bottom:5px;padding-left:25px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 16px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 19px; margin: 0;\"><span style=\"font-size: 16px;\"><strong>Lgbt edition</strong></span></p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 25px;\">\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 25px;\" align=\"center\"><![endif]--><img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\"cid:imagerate\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 130px; display: block;\" title=\"Alternate text\" width=\"130\"/>\r\n"
					+ "<div style=\"font-size:1px;line-height:15px\"> </div>\r\n"
					+ "<!--[if mso]></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 40px; padding-left: 40px; padding-top: 5px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:5px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\">Per tutti i gusti.</p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top: 15px; padding-bottom: 5px; font-family: Arial, sans-serif\"><![endif]-->\r\n"
					+ "<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:15px;padding-right:25px;padding-bottom:5px;padding-left:25px;\">\r\n"
					+ "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\r\n"
					+ "<p style=\"font-size: 20px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 24px; margin: 0;\"><span style=\"color: #339966; font-size: 20px;\"><strong> <span style=\"color: #0079ff;\">  Price: 2341 $</span></strong></span></p>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if mso]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (!mso)&(!IE)]><!-->\r\n" + "</div>\r\n" + "<!--<![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<div style=\"background-color:#eeeeee;\">\r\n"
					+ "<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 650px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #201f1f;\">\r\n"
					+ "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#201f1f;\">\r\n"
					+ "<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#eeeeee;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px\"><tr class=\"layout-full-width\" style=\"background-color:#201f1f\"><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color:#201f1f;width:650px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n"
					+ "<div class=\"col num12\" style=\"min-width: 320px; max-width: 650px; display: table-cell; vertical-align: top; width: 650px;\">\r\n"
					+ "<div style=\"width:100% !important;\">\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n"
					+ "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n"
					+ "<!--<![endif]-->\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" class=\"social_icons\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\r\n"
					+ "<tbody>\r\n" + "<tr style=\"vertical-align: top;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\r\n"
					+ "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"social_table\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-tspace: 0; mso-table-rspace: 0; mso-table-bspace: 0; mso-table-lspace: 0;\" valign=\"top\">\r\n"
					+ "<tbody>\r\n"
					+ "<tr align=\"center\" style=\"vertical-align: top; display: inline-block; text-align: center;\" valign=\"top\">\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 1px; padding-left: 1px;\" valign=\"top\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img alt=\"Facebook\" height=\"32\" src=\"cid:imagef\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\" title=\"Facebook\" width=\"32\"/></a></td>\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 1px; padding-left: 1px;\" valign=\"top\"><a href=\"https://twitter.com/\" target=\"_blank\"><img alt=\"Twitter\" height=\"32\" src=\"cid:imaget\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\" title=\"Twitter\" width=\"32\"/></a></td>\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 1px; padding-left: 1px;\" valign=\"top\"><a href=\"https://instagram.com/\" target=\"_blank\"><img alt=\"Instagram\" height=\"32\" src=\"cid:imagei\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\" title=\"Instagram\" width=\"32\"/></a></td>\r\n"
					+ "<td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 1px; padding-left: 1px;\" valign=\"top\"><a href=\"https://www.linkedin.com/\" target=\"_blank\"><img alt=\"LinkedIn\" height=\"32\" src=\"cid:imagel\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\" title=\"LinkedIn\" width=\"32\"/></a></td>\r\n"
					+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
					+ "</table>\r\n" + "<!--[if (!mso)&(!IE)]><!-->\r\n" + "</div>\r\n" + "<!--<![endif]-->\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "</div>\r\n" + "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
					+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
					+ "<!--[if (IE)]></div><![endif]-->\r\n" + "</body>\r\n" + "</html>";
			messageBodyPart.setContent(htmlText, "text/html");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource f1 = new FileDataSource("Website\\IMG\\pc2.jpg");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagelgbt>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\pc3.jpg");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagesatana>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\benvenuto.jpg");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagewelcome>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\rate.png");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagerate>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\linkedin2x.png");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagel>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\instagram2x.png");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagei>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\twitter2x.png");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imaget>");
			mm.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			f1 = new FileDataSource("Website\\IMG\\facebook2x.png");
			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) f1));
			messageBodyPart.addHeader("Content-ID", "<imagef>");
			mm.addBodyPart(messageBodyPart);
			// add it

			// put everything together
			msg.setContent(mm);
			Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
