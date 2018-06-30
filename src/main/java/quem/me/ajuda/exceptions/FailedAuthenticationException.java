package quem.me.ajuda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public FailedAuthenticationException() {
		super("Bad credentials.");
	}
	
	public FailedAuthenticationException(String message) {
		super(message);
	}
}