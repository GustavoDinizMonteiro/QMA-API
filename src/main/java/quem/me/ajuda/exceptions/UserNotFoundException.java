package quem.me.ajuda.exceptions;

public class UserNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super("User does not exist. ");
	}
}