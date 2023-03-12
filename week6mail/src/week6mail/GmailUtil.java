/*
	Name: Jennifer Byrne
	Date: March 15, 2020
	Notes: ENTD481 Week 6 Assignment - This is the class GmailUtil that works with MailServlet 
		   to send an email.
 */
// Package statement
package week6mail;

//Import the following Java libraries
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

// Clas declaration
public class GmailUtil {
	
	// sendMail method
	public static void sendMail(String to, String from, 
			String subject, String body) 
			throws MessagingException {
		
		// Creates new properties object
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtps");			// Defines transport protocol: smtps
		properties.put("mail.smtps.host", "smtp.gmail.com");		// Assigns which host to transport to
		properties.put("mail.smtps.port", 465);						// Assigns port number to use
		properties.put("mail.smtps.auth", "true");					// Requires authentication
		properties.put("mail.smtps.quitwait", "false");				// Prevents SSLException
		
		// Creates new session object using the values passed in properties object
		Session session = Session.getDefaultInstance(properties);
		// Prints debugging info about the session into a log file
		session.setDebug(true);
		
		// Try statement
		try {
			// Creates new message object
			Message message = new MimeMessage(session);
			message.setSubject(subject);							// Sets subject as subject passed parameter
			Address fromAddress = new InternetAddress(from);		// Creates new fromAddress object			
			Address toAddress = new InternetAddress(to);			// Creates new toAddress object
			message.setFrom(fromAddress);							// Sets from as fromAddress value
			message.setRecipient(Message.RecipientType.TO, toAddress);	// Sets recipient as toAddress value
			message.setContent(body, "text/html");						// Sets content as body passed parameter
			
			// Creates transport object to send the message object
			Transport transport = session.getTransport();
			transport.connect("jennybyrne.classwork@gmail.com", "APUS2020!");	// Uses username & password
			transport.sendMessage(message, message.getAllRecipients());			// Sends message object
			transport.close();													// Closes transport connection
		}
		// Catch block
		catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
