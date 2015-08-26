package weatherunderground;

import org.json.JSONObject;
import resources.Constructors;
import resources.exceptions.ApiException;
import resources.exceptions.AuthRequiredException;
import resources.exceptions.BadRequestException;
import resources.exceptions.DataNotSetException;
import resources.infrastructure.EasyApiComponent;

/**
 * Returns the moon phase, sunrise and sunset times.
 * More information can be found at http://www.wunderground.com/weather/api/d/docs?d=data/astronomy
 * @author Aaron Vontell
 * @date 8/26/2015
 * @version 0.1
 */
public class WUAstronomy implements EasyApiComponent{

    private String url = "";
    private JSONObject result;

    //Variables generated from the download
    private String percentIlluminated;
    private String ageOfMoon;
    private String currentTimeHour;
    private String currentTimeMinute;
    private String sunriseHour;
    private String sunriseMinute;
    private String sunsetHour;
    private String sunsetMinute;

    /**
     * Create this astronomy object with a given API key
     * @param apiKey The valid API key to gain access to the REST API
     */
    protected WUAstronomy(String apiKey) {

        url +=  WeatherUnderground.BASE_URL + apiKey + "/astronomy/q/";

    }

    /**
     * Set the location to find moon and sun information at that location (city and state)
     * @param location Place to get information - preferred format: (STATE/City)
     * @return The WUAstronomy object with parameters set
     */
    public WUAstronomy setParameters(String location){

        url += location + ".json";
        return this;

    }

    /**
     * Downloads and parses all data from the given information
     */
    @Override
    public void downloadData() throws ApiException, BadRequestException, DataNotSetException, AuthRequiredException {

        try {
            result = Constructors.constructJSON(url);
            JSONObject jObj = new JSONObject(result.toString());
            jObj = jObj.getJSONObject("moon_phase");
            percentIlluminated = jObj.getString("percentIlluminated");
            ageOfMoon          = jObj.getString("ageOfMoon");
            currentTimeHour    = jObj.getJSONObject("current_time").getString("hour");
            currentTimeMinute  = jObj.getJSONObject("current_time").getString("minute");
            sunriseHour        = jObj.getJSONObject("sunrise").getString("hour");
            sunriseMinute      = jObj.getJSONObject("sunrise").getString("minute");
            sunsetHour         = jObj.getJSONObject("sunset").getString("hour");
            sunsetMinute       = jObj.getJSONObject("sunset").getString("minute");
        } catch (Exception e) {
            if(result.getJSONObject("response").has("error")) {
                switch(result.getJSONObject("response").getJSONObject("error").getString("type")){
                    case "invalidformat":
                        throw new BadRequestException(result.getJSONObject("response").getJSONObject("error").getString("description"));
                    case "querynotfound":
                    case "invalidquery":
                        throw new DataNotSetException(result.getJSONObject("response").getJSONObject("error").getString("description"));
                    case "keynotfound":
                        throw new AuthRequiredException("API key is invalid");
                    default:
                        throw new ApiException(result.toString());
                }

            } else {
                throw new ApiException("API Error: " + result.toString());
            }
        }

    }

    /**
     * Get the percentage of the moon's surface that is illuminated by the sun
     * @return moon's percentage of illumination
     */
    public String getPercentIlluminated(){ return percentIlluminated; }

    /**
     * Get the age of the moon in the current cycle/phase
     * @return age of moon in cycle
     */
    public String getAgeOfMoon(){ return ageOfMoon; }

    /**
     * Get the hour that this request was made
     * @return request hour
     */
    public String getRequestTimeHour(){ return currentTimeHour; }

    /**
     * Get the minute that this request was made
     * @return request minute
     */
    public String getRequestTimeMinute(){ return currentTimeMinute; }

    /**
     * Get the hour for today's sunrise
     * @return sunrise hour
     */
    public String getSunriseHour(){ return sunriseHour; }

    /**
     * Get the minute for today's sunrise
     * @return sunrise minute
     */
    public String getSunriseMinute(){ return sunriseMinute; }

    /**
     * Get the hour for today's sunset
     * @return sunset hour
     */
    public String getSunsetHour(){ return sunsetHour; }

    /**
     * Get the minute for today's sunset
     * @return sunset minute
     */
    public String getSunsetMinute(){ return sunsetMinute; }

    /**
     * Returns the raw data that was downloaded from the API
     * @return A JSONObject representation of what was downloaded
     */
    public String getRawData(){
        if(result != null){
            return result.toString();
        } else {
            return "No data";
        }
    }

}
