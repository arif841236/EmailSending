package com.email.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.email.common.LoggingResponseMessage;
import com.email.common.MessageTypeConst;
import com.email.model.EmailContentAndPassWord;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailSend {

	@Autowired
	Gson gson;
	
	public void sendMail(EmailContentAndPassWord eContent,File file) throws MessagingException, IOException{
		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("sendMail method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));
		
		final String username = "md970824@gmail.com";
		final String password = "upefmkqwteuifsas";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Email properties set successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Email session create successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("md970824@gmail.com"));
			
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(eContent.getTo()));
			
			message.setSubject(eContent.getSubject());

			//3) create MimeBodyPart object and set your message text        
			BodyPart messageBodyPart1 = new MimeBodyPart();     
			messageBodyPart1.setText("This is message body");          

			//4) create new MimeBodyPart object and set DataHandler object to this object        
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
			messageBodyPart2.attachFile(file, "application/pdf", null);

			//5) create Multipart object and add MimeBodyPart objects to this object        
			Multipart multipart = new MimeMultipart();    
			multipart.addBodyPart(messageBodyPart1);     
			multipart.addBodyPart(messageBodyPart2);      

			//6) set the multiplart object to the message object    
			message.setContent(multipart );        

			//7) send message    
			Transport.send(message);      
			log.info(gson.toJson(LoggingResponseMessage.builder()
					.message("Email send successfully.")
					.messageTypeId(MessageTypeConst.SUCCESS)
					.statusCode(HttpStatus.OK).build()));
	}
}
