package weatherunderground;

/**
 * Exception that is thrown when the desired operation is not included within your plan
 * @author Aaron Vontell
 * @version 0.1
 */
public class PlanException extends Exception {

    private final String defaultErrorText = "The API operation you tried to request was not completed; your API plan" +
            " does not support that feature; see plan features at http://www.wunderground.com/weather/api/d/docs?d=data/index&MR=1";

    public PlanException(String message) {
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
