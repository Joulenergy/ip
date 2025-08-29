package joules;

/**
 * Represents an exception specific to the Joules application.
 * <p>
 * This exception is thrown when an error occurs during the execution
 * of a Joules command or operation that cannot be handled normally.
 * </p>
 */
public class JoulesException extends Exception {
    public JoulesException(String message) {
        super(message);
    }
}
