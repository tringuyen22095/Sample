package project.personal.shared.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = -6407701862920608525L;

	public EntityNotFoundException(String message) {
		super(message);
		this.setStatus(HttpStatus.NOT_FOUND);
	}

	private HttpStatus status;

}
