package com.email.service;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.email.common.LoggingResponseMessage;
import com.email.common.MessageTypeConst;
import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@NoArgsConstructor
@Data
@Slf4j
public class PdfEncryptionService {

	@Autowired
	Gson gson;

	private static String ownerPassword;
	private static String userPassword;
	private File file;
	private File fileInput;

	public File setPassword(byte[] mFile) throws IOException {
		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("setPassword method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		// step 1. Loading the pdf file

		file = new File("./demo.pdf");
		PDDocument pdd = PDDocument.load(mFile);

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("Get PDDocument successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		// step 2.Creating instance of AccessPermission class
		AccessPermission ap = new AccessPermission();

		// step 3. Creating instance of StandardProtectionPolicy
		StandardProtectionPolicy stpp = new StandardProtectionPolicy(ownerPassword, userPassword, ap);

		// step 4. Setting the length of Encryption key
		stpp.setEncryptionKeyLength(128);

		// step 5. Setting the permission
		stpp.setPermissions(ap);

		// step 6. Protecting the PDF file
		pdd.protect(stpp);

		// step 7. Saving and closing the PDF Document
		pdd.save(file.getAbsolutePath());
		pdd.close();

		log.info(gson.toJson(LoggingResponseMessage.builder()
				.message("PDF Encrypted successfully...")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.OK).build()));

		return file;
	}

	public static String getOwnerPassword() {
		return ownerPassword;
	}

	public static void setOwnerPassword(String ownerPassword) {
		PdfEncryptionService.ownerPassword = ownerPassword;
	}

	public static String getUserPassword() {
		return userPassword;
	}

	public static void setUserPassword(String userPassword) {
		PdfEncryptionService.userPassword = userPassword;
	}
}
