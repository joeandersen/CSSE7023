package festival_ass1_submitted;

/**
 * An exception indicating an invalid shuttle service.
 */
@SuppressWarnings("serial")
public class InvalidServiceException extends RuntimeException {

	public InvalidServiceException() {
		super();
	}

	public InvalidServiceException(String s) {
		super(s);
	}

}
