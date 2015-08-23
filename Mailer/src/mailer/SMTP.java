package mailer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.io.InputStream;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * SMTP class contains smtp configuration attributes
 * Constructor reads smtp attributes from configuration file
 */
public class SMTP {
	
	private String smtpUser;
	private String smtpPassword;
	private String smtpHost;
	private String smtpPort;
	
	SMTP(){
		try{
			InputStream fis = getClass().getResourceAsStream("/configuration.txt");
            ResourceBundle rb=  new PropertyResourceBundle(fis);
			
			smtpUser = rb.getString("smtpUser");
			smtpPassword = rb.getString("smtpPassword");
			smtpHost = rb.getString("smtpHost");
			smtpPort = rb.getString("smtpPort");
			
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}

	public void SendMail(String from, String to, String Subject, String MessageContent) {
		/**  
		 * SendMail: To send mail 
		 * @param String from emailid
		 * @param String to emailid
		 * @param String Subject of mail
		 * @param String MessageContent content of mail
		 * @throws All Exceptions 
		 * Example  SendMail(from, to, Subject, MessageContent);
		 */
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.port", smtpPort);
			props.put("mail.smtp.socketFactory.port", smtpPort);
			props.put("mail.transport.protocol", "smtp");
			Session session = null;
			session = Session.getInstance(props,
			new javax.mail.Authenticator() {				
				
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(
							smtpUser, smtpPassword
							);
				}

			});
			// Create a default MimeMessage object.

			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.

			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.

			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

			// Set Subject: header field

			message.setSubject(Subject);

			String mailHTML = MessageContent ;

			// Send the actual HTML message, as big as you like
			message.setContent(mailHTML, "text/html");
			Transport.send(message);

			return;

		}
		catch (MessagingException mex) {

			mex.printStackTrace();

			return;

		}
		catch(Exception e){
			System.out.println("Message not Sent....");
			return;
		}
	}
	
}