package festival_ass1_submitted;

/**
 * An exception indicating an invalid line up.
 */
@SuppressWarnings("serial")
public class InvalidLineUpException extends RuntimeException {

	public InvalidLineUpException() {
		super();
	}

	public InvalidLineUpException(String s) {
		super(s);
	}

}
