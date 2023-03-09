package com.email.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {

	private String message;
	private Integer statusCode;
}
