package com.email.service;

import java.io.File;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import com.email.common.LoggingResponseMessage;
import com.email.common.MessageTypeConst;
import com.email.model.EmailContentAndPassWord;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Validated
public class MainService {

	@Autowired
	EmailSend emailSend;

	@Autowired
	Gson gson;

	@Autowired
	PdfEncryptionService encryptionService;

	public void mainSendEmail(@Valid EmailContentAndPassWord eContent,MultipartFile mulFile) throws IOException, MessagingException {
		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("mainSendEmail method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK)));

		byte[] inFileBytes = mulFile.getBytes();  
        
		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Get file successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		PdfEncryptionService.setOwnerPassword(eContent.getOwnerPassword());
		PdfEncryptionService.setUserPassword(eContent.getUserPassword());
		
		File file = encryptionService.setPassword(inFileBytes);

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Password set successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		emailSend.sendMail(eContent, file);

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Email send successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

	}
}
