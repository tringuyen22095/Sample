package project.personal.shared.common.config;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.res.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> invalidFields = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

		ErrorResponse res = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value())
				.invalidFields(invalidFields).build();

		return ResponseEntity.badRequest().body(res);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		ErrorResponse res = ErrorResponse.builder().errorCode(ex.getStatus().value()).errorMsg(ex.getMessage()).build();
		return ResponseEntity.status(ex.getStatus()).body(res);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleRegularException(Exception ex, WebRequest request) {
		ErrorResponse res = ErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMsg(ex.getMessage()).build();
		return ResponseEntity.internalServerError().body(res);
	}

}
