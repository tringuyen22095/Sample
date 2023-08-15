package project.personal.shared.common.config;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
		return ResponseEntity.badRequest().body(Collections.singletonMap("errorCode", "01"));
	}

}
