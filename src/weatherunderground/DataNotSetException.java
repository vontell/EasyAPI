package weatherunderground;

/**
 * Exception that is thrown when the operation requested does not include the desired data
 * @author Aaron Vontell
 * @version 0.1
 */
public class DataNotSetException extends Exception {

    private final String defaultErrorText = "An API request was made when the required information was not set";

    public DataNotSetException(String message) {
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
