package xyz.tinyorb.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class gmailApi {
	public int mail(String recipent, String subject, String body)
	{
		int status_code = 1;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		/*
		 * This error text is associated with a call to Session.getDefaultInstance(props, authenticator) where the default instance already has a different authenticator set.
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("tinyorborg@gmail.com","pajisingh");
				}
			});
		*/
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("tinyorborg@gmail.com","pajisingh");
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("tinyorborg@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipent));
			message.setSubject(subject);
			message.setContent(message, "text/html; charset=utf-8");
			message.setText(body);

			Transport.send(message);
			status_code = 0;
			//System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return status_code;
	}
}
