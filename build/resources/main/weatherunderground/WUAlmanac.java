package weatherunderground;

import org.json.JSONArray;
import org.json.JSONObject;
import resources.Constructors;
import resources.exceptions.ApiException;
import resources.exceptions.AuthRequiredException;
import resources.exceptions.BadRequestException;
import resources.exceptions.DataNotSetException;
import resources.infrastructure.EasyApiComponent;

/**
 * The average high and low temperature going back as far as Weather Underground
 * has data for OR from National Weather Service going back 30 years. More information
 * can be found at http://www.wunderground.com/weather/api/d/docs?d=data/almanac&MR=1
 * @author Aaron Vontell
 * @date 8/24/15
 * @version 0.1
 */
public class WUAlmanac implements EasyApiComponent{

    private String url = "";
    private JSONObject result;

    //Variables generated from the download
    private String airportCode;
    private String averageHighF;
    private String averageHighC;
    private String highestTodayF;
    private String highestTodayC;
    private String averageLowF;
    private String averageLowC;
    private String lowestTodayF;
    private String lowestTodayC;
    private String recordHighYear;
    private String recordLowYear;

    /**
     * Create this almanc object with a given API key
     * @param apiKey The valid API key to gain access to the REST API
     */
    protected WUAlmanac(String apiKey) {

        url +=  WeatherUnderground.BASE_URL + apiKey + "/almanac/q/";

    }

    /**
     * Set the location to find record high and lows (city and state)
     * @param location Place to get records - preferred format: (STATE/City)
     * @return The WUAlmanac object with parameters set
     */
    public WUAlmanac setParameters(String location){

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
            jObj = jObj.getJSONObject("almanac");
            airportCode    = jObj.getString("airport_code");
            averageHighF   = jObj.getJSONObject("temp_high").getJSONObject("normal").getString("F");
            averageHighC   = jObj.getJSONObject("temp_high").getJSONObject("normal").getString("C");
            highestTodayF  = jObj.getJSONObject("temp_high").getJSONObject("record").getString("F");
            highestTodayC  = jObj.getJSONObject("temp_high").getJSONObject("record").getString("C");
            recordHighYear = jObj.getJSONObject("temp_high").getString("recordyear");
            averageLowF    = jObj.getJSONObject("temp_low").getJSONObject("normal").getString("F");
            averageLowC    = jObj.getJSONObject("temp_low").getJSONObject("normal").getString("C");
            lowestTodayF   = jObj.getJSONObject("temp_low").getJSONObject("record").getString("F");
            lowestTodayC   = jObj.getJSONObject("temp_low").getJSONObject("record").getString("C");
            recordLowYear  = jObj.getJSONObject("temp_low").getString("recordyear");

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
     * Get the airport code for the location that this data was recorded at
     * @return observed location
     */
    public String getObservationLocation(){ return airportCode; }

    /**
     * Get the average high temperature for this location on this day in Fahrenheit
     * @return average high temperature (F)
     */
    public String getAverageHighF(){ return averageHighF; }

    /**
     * Get the average high temperature for this locationon this day in Celsius
     * @return average high temperature (C)
     */
    public String getAverageHighC(){ return averageHighC; }

    /**
     * Get the record high temperature for this location on this day in Fahrenheit
     * @return record high temperature (F)
     */
    public String getRecordHighF(){ return highestTodayF; }

    /**
     * Get the record high temperature for this location on this day in Celsius
     * @return record high temperature (C)
     */
    public String getRecordHighC(){ return highestTodayC; }

    /**
     * Get the year that the record high temperature was set for this location on this day
     * @return record high year
     */
    public String getRecordHighYear(){ return recordHighYear; }

    /**
     * Get the average low temperature for this location on this day in Fahrenheit
     * @return average low temperature (F)
     */
    public String getAverageLowF(){ return averageLowF; }

    /**
     * Get the average low temperature for this locationon this day in Celsius
     * @return average low temperature (C)
     */
    public String getAverageLowC(){ return averageLowC; }

    /**
     * Get the record low temperature for this location on this day in Fahrenheit
     * @return record low temperature (F)
     */
    public String getRecordLowF(){ return lowestTodayF; }

    /**
     * Get the record low temperature for this location on this day in Celsius
     * @return record low temperature (C)
     */
    public String getRecordLowC(){ return lowestTodayC; }

    /**
     * Get the year that the record low temperature was set for this location on this day
     * @return record low year
     */
    public String getRecordLowYear(){ return recordLowYear; }

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
