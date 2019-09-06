package framework;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;    
import javax.mail.internet.*;
import javax.mail.internet.MimeMultipart;

public class SendMailSSL{  


	public  void AutomationReport(String fileName,int passStatus,int failStatus,int skipStatus){  

		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class",    
				"javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "888");    

		Session session = Session.getDefaultInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(EnvironmentManager.getEnvironmentProperties("EmailId"),EnvironmentManager.getEnvironmentProperties("EmailPassword"));
			}    
		});    
		try {    
			MimeMessage message = new MimeMessage(session);    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(EnvironmentManager.getEnvironmentProperties("EmailZfManager")));    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(EnvironmentManager.getEnvironmentProperties("EmailZfLead")));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(EnvironmentManager.getEnvironmentProperties("EmailSaskenManager")));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(EnvironmentManager.getEnvironmentProperties("EmailSaskenLead")));

			message.setSubject("Automatation Report generated after execution");     
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			String text = "Hi All,";
			String start=ExtentReport.startTime;
			System.out.println(start);
			String endTime=ExtentReport.getTimeStamp();
			System.out.println(endTime);
			String html = text+"<br><br>"+"Execution Summary Report"+"<br><br>"+"Execution Start Time : "+start+"<br>"+"Execution  End Time : "+endTime+"<br>"+"Suite : "+Hooks.suiteName+"<br><br>";
			String executionPassStatus="Passed tests for suite "+Hooks.suiteName+" is : "+passStatus +"<br><br>";
			String executionfailStatus="Failed tests for suite "+Hooks.suiteName+" is : "+failStatus +"<br><br>";
			String executionskipStatus="Skipped tests for suite "+Hooks.suiteName+" is : "+skipStatus +"<br><br>";
			
			String attachment="Note : PFA"+"<br><br>"+"Thanks <br> AutomationTeam";
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText( text, "utf-8" );
			textPart.setText( executionPassStatus, "utf-8" );
			textPart.setText( executionfailStatus, "utf-8" );
			textPart.setText( executionskipStatus, "utf-8" );
			textPart.setText( attachment, "utf-8" );
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent( html, "text/html; charset=utf-8" );

			multipart.addBodyPart( htmlPart );
			message.setContent( multipart );

			// Unexpected output.
			System.out.println( "HTML = text/html : " + htmlPart.isMimeType( "text/html" ) );
			System.out.println( "HTML Content Type: " + htmlPart.getContentType() );
			message.saveChanges();
			System.out.println( "TEXT = text/plain: " + textPart.isMimeType( "text/plain" ) );
			System.out.println( "HTML = text/html : " + htmlPart.isMimeType( "text/html" ) );
			System.out.println( "HTML Content Type: " + htmlPart.getContentType() );
			System.out.println( "HTML Data Handler: " + htmlPart.getDataHandler().getContentType() );
			String filename = fileName;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);


			//send message  
			Transport.send(message);    
			System.out.println("Automation Report Email sent successfully");    
		} catch (MessagingException e) {
			System.out.println(e.getMessage());}    

	}  

}  
