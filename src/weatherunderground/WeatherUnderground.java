package weatherunderground;

import resources.infrastructure.EasyApiObject;

/**
 * A class that can gather information from the Weather Underground API
 * Documentation at: http://www.wunderground.com/weather/api/d/docs
 * Standard format: http://api.wunderground.com/api/KEY/features/settings/q/query.format
 * @author Aaron Vontell
 * @version 0.1
 */
public class WeatherUnderground extends EasyApiObject {

    private String apiKey;
    protected static String BASE_URL = "http://api.wunderground.com/api/";

    /**
     * Create a Weather Underground API object, which will provide factories to access the
     * API components and services available
     * @param apiKey Your API key generated from the Weather Underground website
     */
    public WeatherUnderground(String apiKey) {

        this.apiKey = apiKey;

    }

    /**
     * Instantiates a new conditions object
     * Can be used to retrieve current conditions in a given location
     * @return WUConditions object for current weather information
     */
    public WUConditions createConditionsObject(){ return new WUConditions(apiKey); }

    /**
     * Instantiates a new alerts object
     * Can be used to retrieve current weather alerts for a given location
     * @return WUAlerts object for current alerts information
     */
    public WUAlerts createAlertsObject(){ return new WUAlerts(apiKey); }

    /**
     * Instantiates a new almanac object
     * Can be used to retrieve record high and low temperatures for a given location
     * @return WUAlmanac object for record temperatures information
     */
    public WUAlmanac createAlmanacObject(){ return new WUAlmanac(apiKey); }

    /**
     * Instantiates a new astronomy object
     * Can be used to retrieve sunset, sunrise, and moon information for a given location
     * @return WUAstronomy object for planet information
     */
    public WUAstronomy createAstronomyObject(){ return new WUAstronomy(apiKey); }

    /**
     * Retrieve the list of all requests available in this API
     * @return The list of requests
     */
    @Override
    public String[] getPossibleRequests(){

        String[] possibleRequests = {

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

        return possibleRequests;

    }

}
