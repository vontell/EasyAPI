package resources.exceptions;

/**
 * Exception thrown when internet connection was lost, or was never established
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public class BadConnectionException extends Exception{

    private String errorText = "Internet connection could not be made";

    /**
     * Create the exception with additional returned API messages
     * @param message The extra API return to attach to the default message
     */
    public BadConnectionException(String message) {
        super(message);
        if(!message.equals("")){errorText = errorText.concat(": " + message);}
    }

    @Override
    public String getMessage() {
        return errorText;
    }
}
