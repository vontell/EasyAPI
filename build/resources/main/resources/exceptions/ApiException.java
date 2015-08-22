package resources.exceptions;

/**
 * Exception thrown when the API returns an unexpected error
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public class ApiException extends Exception{

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ApiException(String message) {
        super(message);
    }
}
