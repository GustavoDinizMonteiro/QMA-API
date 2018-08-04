package quem.me.ajuda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncompatibleUserIDAndTokenException extends RuntimeException{
	private static final String MESSAGE_WIRH_COMPLEMENT = "User id and token do not refer to the same user: ";
	private static final String MESSAGE = "User id and token do not refer to the same user.";
	private static final long serialVersionUID = 1L;

	public IncompatibleUserIDAndTokenException() {
		super(MESSAGE);
	}
	
	public IncompatibleUserIDAndTokenException(String message) {
		super(MESSAGE_WIRH_COMPLEMENT.concat(message));
	}
}
