package weatherunderground;

/**
 * Exception that is thrown when the operation requested does not exist
 * @author Aaron Vontell
 * @version 0.1
 */
public class RequestNotFoundException extends Exception{

    private final String defaultErrorText = "The request you tried to make does not exist";

    public RequestNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return defaultErrorText;
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
