package quem.me.ajuda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncompatibleUserIDAndTokenException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public IncompatibleUserIDAndTokenException() {
		super("User id and token do not refer to the same user.");
	}
	
	public IncompatibleUserIDAndTokenException(String message) {
		super(message);
	}
}
