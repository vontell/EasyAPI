package weatherunderground;

import resources.RestApiObject;

/**
 * A class that can gather information from the Weather Underground API
 * Documentation at: http://www.wunderground.com/weather/api/d/docs
 * @author Aaron Vontell
 * @version 0.1
 */
public class WeatherUnderground implements RestApiObject{

    private String apiKey;
    private String BASE_URL = "http://api.wunderground.com/api/";
    private String jsonContent;
    private String finalURL;

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

    //Variables used in making queries
    private String locationString = "";
    private double[] coordinates = {0d,0d};

    public WeatherUnderground(String apiKey) {

        this.apiKey = apiKey;
        this.finalURL = BASE_URL + apiKey + "/";

    }

    /**
     * Set the location for weather and geolocation queries (string name)
     * @param location A location represented by name
     */
    public void setLocation(String location){
        locationString = location;
    }

    /**
     * Set the location for weather and geolocation queries (latitude,longitude)
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     */
    public void setLocation(double lat, double lon){
        coordinates[0] = lat;
        coordinates[1] = lon;
    }

    /**
     * Download
     * @param dataRequest
     */
    @Override
    public void downloadData(String dataRequest){

        if(!possibleRequestsContains(dataRequest)){
            //throw new RequestNotFoundException("");
            return;
        }

    }

    /**
     * Find out if a request is available
     * @param request The request to search for
     * @return Does the request exist
     */
    @Override
    public boolean possibleRequestsContains(String request){

        return true;

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
