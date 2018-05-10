package quem.me.ajuda.exceptions;

public class EntityNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message){
        super("Entity not found. " + message);
    }

    public EntityNotFoundException(){
        super("Entity not found. ");
    }
}