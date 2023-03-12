/*
	Name: Jennifer Byrne
	Date: March 15, 2020
	Notes: ENTD481 Week 6 Assignment - This is the mail servlet to send an email using parameters 
		   passed from index.html.
 */
// Package statement
package week6mail;

// Imports the following Java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Class declaration
public class MailServlet extends HttpServlet {
	// Default serialVersionUID
	private static final long serialVersionUID = 1L;
	// doPost method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String to = request.getParameter("to");				// Sets value of "to"
		String from = request.getParameter("from");			// Sets value of "from"
		String subject = request.getParameter("subject");	// Sets value of "subject"
		String body = request.getParameter("body");			// Sets value of "body"
		String sendMessage = "";							// Sets initial value of sendMessage
		
		// Try statement
		try {
			// Calls sendMail method from class GmailUtil
			GmailUtil.sendMail(to, from, subject, body);
			// Sets new value of sendMessage if it works
			sendMessage = "Email was sent successfully.";
		}
		// Catch block
		catch (Exception e) {
			e.printStackTrace();
			// Sets new value of sendMessage if exception occurs
			sendMessage = "Something went wrong. Email was not sent.";
		}
		finally {
			// System prints statement based on if the email was sent or not
			System.out.println(sendMessage);
		}	
		
	}
	
}
