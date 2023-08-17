package project.personal.shared.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStorageException extends Exception {

	private static final long serialVersionUID = -6407701862920608525L;

	public FileStorageException(String message) {
		super(message);
		this.setStatus(HttpStatus.BAD_REQUEST);
	}

	public FileStorageException(String message, Throwable ex) {
		super(message, ex);
		this.setStatus(HttpStatus.BAD_REQUEST);
	}

	private HttpStatus status;

}
