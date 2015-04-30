package festival_ass1_submitted;

/**
 * An exception indicating an invalid session number.
 */
@SuppressWarnings("serial")
public class InvalidSessionException extends RuntimeException {

	public InvalidSessionException() {
		super();
	}

	public InvalidSessionException(String s) {
		super(s);
	}

}
