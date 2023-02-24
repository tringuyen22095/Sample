package com.personal.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.personal.model.res.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ERROR_MSG_TEMPLATE = "Error occurred: %s - %s";
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<?> genericException(HttpStatusCodeException ex, WebRequest request) {
		HttpStatus status = ex.getStatusCode();

		logMsgByStatus(ex, status);

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
				status.name(), ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(errorDetails, status);
	}

	private void logMsgByStatus(Exception ex, HttpStatus status) {
		if (status.is4xxClientError()) {
			log.info(String.format(ERROR_MSG_TEMPLATE, ex.getClass().getName(), ex.getMessage()));
		} else {
			log.error(String.format(ERROR_MSG_TEMPLATE, ex.getClass().getName(), ex.getMessage()), ex);
		}
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errList = ex.getBindingResult().getAllErrors().stream().map(item -> {
			DefaultMessageSourceResolvable sourceResolvable = (DefaultMessageSourceResolvable) item.getArguments()[0];
			return String.format("%s.%s: %s", item.getObjectName(), sourceResolvable.getDefaultMessage(),
					item.getDefaultMessage());
		}).collect(Collectors.toList());
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
				status.name(), StringUtils.join(errList, "; "),
				((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(errorDetails, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		logMsgByStatus(ex, status);

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
				status.name(), ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(errorDetails, headers, status);
	}

}
