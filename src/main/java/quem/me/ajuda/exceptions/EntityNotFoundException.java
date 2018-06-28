package quem.me.ajuda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(){
		super("Entity not found.");
	}
	
	public EntityNotFoundException(String message){
        super("Entity not found: ".concat(message));
    }

}