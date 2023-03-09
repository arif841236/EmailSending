package com.email.controller;

import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.email.common.LoggingResponseMessage;
import com.email.common.MessageTypeConst;
import com.email.common.Response;
import com.email.model.EmailContentAndPassWord;
import com.email.service.MainService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(description = "Email Sender", tags = {"Send Email"})
public class EmailController {

	@Autowired
	MainService emailSend;

	@Autowired
	Gson gson;

	@PostMapping(value = "/email")
	@ApiOperation(notes = "Return success response", value = "Set email data",response = Response.class)
	public ResponseEntity<Response> send(@RequestPart("Fill the email data")String andPassWord
			,@RequestPart("Choose the file") MultipartFile file) 
					throws MessagingException, IOException{

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("send method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		EmailContentAndPassWord fromJson = gson.fromJson(andPassWord, EmailContentAndPassWord.class);

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Get email data successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(fromJson)
				.statusCode(HttpStatus.OK).build()));

		emailSend.mainSendEmail(fromJson,file);

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("send method end.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		return ResponseEntity.ok(Response.builder()
				.message("Successfully send email.")
				.statusCode(HttpStatus.OK.value())
				.build());
	}
}
