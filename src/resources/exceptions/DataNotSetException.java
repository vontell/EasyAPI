package resources.exceptions;

/**
 * Exception thrown when data required for the API call was not set
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public class DataNotSetException extends Exception{

    private String errorText = "The API request you tried to complete requires data that was not given or was given incorrectly";

    /**
     * Create the exception with additional returned API messages
     * @param message The extra API return to attach to the default message
     */
    public DataNotSetException(String message) {
        super(message);
        if(!message.equals("")){errorText = errorText.concat(": " + message);}
    }

    @Override
    public String getMessage() {
        return errorText;
    }
}
