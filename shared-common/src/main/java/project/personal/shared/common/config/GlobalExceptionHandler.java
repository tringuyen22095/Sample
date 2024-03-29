package project.personal.shared.common.config;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.shared.common.model.res.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger _log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> invalidFields = new Hashtable<String, String>();

		ObjectError global = ex.getBindingResult().getGlobalError();
		if (global != null)
			invalidFields.put(global.getObjectName(), global.getDefaultMessage());

		invalidFields.putAll(ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));

		ErrorResponse res = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value())
				.invalidFields(invalidFields).build();

		return ResponseEntity.badRequest().body(res);
	}

	@ExceptionHandler(value = {
			EntityNotFoundException.class,
			FileStorageException.class,
			FileStorageNotFoundException.class
	})
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		_log.error("Execute fail", ex);
		ErrorResponse res = ErrorResponse.builder().errorCode(ex.getStatus().value()).errorMsg(ex.getMessage()).build();
		return ResponseEntity.status(ex.getStatus()).body(res);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleRegularException(Exception ex, WebRequest request) {
		_log.error("Execute fail", ex);
		ErrorResponse res = ErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMsg(ex.getMessage()).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest request) {
		_log.error("Execute fail", ex);
		ErrorResponse res = ErrorResponse.builder().errorCode(ex.status())
				.errorMsg(ex.getMessage()).build();
		return ResponseEntity.status(HttpStatus.valueOf(ex.status())).body(res);
	}
	
	

}
