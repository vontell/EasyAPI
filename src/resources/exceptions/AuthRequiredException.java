package resources.exceptions;

/**
 * Exception thrown when authentication is required to perform the desired operation / API call
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public class AuthRequiredException extends Exception{

    private final String errorText = "The API request you tried to complete requires authentication";

    /**
     * Create the exception with additional returned API messages
     * @param message The extra API return to attach to the default message
     */
    public AuthRequiredException(String message) {
        super(message);
        errorText.concat(": " + message);
    }

    @Override
    public String getMessage() {
        return errorText;
    }
}
