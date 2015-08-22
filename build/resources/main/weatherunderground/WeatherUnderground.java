package weatherunderground;

import resources.interfaces.EasyApiObject;

/**
 * A class that can gather information from the Weather Underground API
 * Documentation at: http://www.wunderground.com/weather/api/d/docs
 * Standard format: http://api.wunderground.com/api/KEY/features/settings/q/query.format
 * @author Aaron Vontell
 * @version 0.1
 */
public class WeatherUnderground implements EasyApiObject {

    private String apiKey;
    protected static String BASE_URL = "http://api.wunderground.com/api/";

    /** List of possible data requests **/
    private String[] possibleRequests = {

            "alerts",
            "almanac",
            "astronomy",
            "conditions",
            "currenthurricane",
            "forecast",
            "forecast10day",
            "geolookup",
            "history",
            "hourly",
            "hourly10day",
            "planner",
            "rawtide",
            "tide",
            "webcams",
            "yesterday"

    };

    /**
     * Create a Weather Underground API object, which will provide factories to access the
     * API components and services available
     * @param apiKey Your API key generated from the Weather Underground website
     */
    public WeatherUnderground(String apiKey) {

        this.apiKey = apiKey;

    }

    public WUConditions getConditionsObject(){
        return new WUConditions(apiKey);
    }

    /**
     * Find out if a request is available
     * @param request The request to search for
     * @return Does the request exist
     */
    @Override
    public boolean possibleRequestsContains(String request){

        return false;

    }

    /**
     * Retrieve the list of all requests available in this API
     * @return The list of requests
     */
    @Override
    public String[] getPossibleRequests(){
        return possibleRequests;
    }

}
