package com.personal.model.res;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime time;
	public int status;
	public String error;
	public String errorCode;
	public String errorMessage;
	public String path;

	public ErrorDetails(LocalDateTime time, int status, String error, String errorCode, String errorMessage,
			String path) {
		super();
		this.time = time;
		this.status = status;
		this.error = error;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.path = path;
	}
}
