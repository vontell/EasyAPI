package resources.exceptions;

/**
 * Exception thrown when a bad request is made to the API
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public class BadRequestException extends Exception{

    private final String errorText = "The API request you tried to complete is not available or does not exist";

    /**
     * Create the exception with additional returned API messages
     * @param message The extra API return to attach to the default message
     */
    public BadRequestException(String message) {
        super(message);
        errorText.concat(": " + message);
    }

    @Override
    public String getMessage() {
        return errorText;
    }
}
