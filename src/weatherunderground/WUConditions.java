package weatherunderground;

import org.json.JSONObject;
import resources.Constructors;
import resources.exceptions.ApiException;
import resources.exceptions.AuthRequiredException;
import resources.exceptions.BadRequestException;
import resources.exceptions.DataNotSetException;
import resources.infrastructure.EasyApiComponent;

/**
 * Implementation of current conditions in a given area
 * Returns the current temperature, weather condition, humidity, wind, 'feels like' temperature, barometric pressure,
 * and visibility of a location. More information at http://www.wunderground.com/weather/api/d/docs?d=data/conditions&MR=1
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public class WUConditions implements EasyApiComponent {

    private String url = "";
    private JSONObject result;

    //Variables generated from the download
    private String termsUrl;
    private String obsEpoch;
    private double tempC;
    private double dewPointF;
    private double dewPointC;
    private double tempF;
    private double windKPH;
    private double windMPH;
    private double windDeg;
    private String tempString;
    private String weather;
    private String feelsLike;
    private String precipTodMet;
    private String precipTodString;
    private String precipTodIN;
    private String precipHrIn;
    private String iconUrl;
    private String iconDesc;
    private String imageLink;
    private String imageTitle;
    private String imageUrl;
    private String uv;
    private String stationId;
    private String localEpoch;
    private String locationTZShort;
    private String windDirection;
    private String precipHrMetric;
    private String pressureIN;
    private String localTZLong;
    private double windGustMPH;
    private String windchillString;
    private String windchillF;
    private String windchillC;
    private double windGustKPH;
    private String windString;
    private String requestTime;
    private String visibilityKM;
    private String relativeHumidity;
    private String pressureMB;
    private String observationTime;
    private String feelsLikeF;
    private String feelsLikeC;
    private String historyUrl;
    private String solarRad;
    private String obsElev;
    private String obsCtry;
    private String obsCity;
    private String obsLat;
    private String obsLon;
    private String obsState;
    private String obsFull;
    private String dispZip;
    private String dispElev;
    private String dispCtry;
    private String dispCity;
    private String dispState;
    private String dispFull;
    private String dispLat;
    private String dispLon;
    private String dewPointDesc;
    private String pressureTrend;
    private String forecastUrl;
    private double heatIndexF;
    private double heatIndexC;
    private String obUrl;
    private String visibilityMI;


    /**
     * Create this current conditions object with a given API key
     * @param apiKey The valid API key to gain access to the REST API
     */
    protected WUConditions(String apiKey) {

        url +=  WeatherUnderground.BASE_URL + apiKey + "/conditions/q/";

    }

    /**
     * Set the location to find current conditions (city and state)
     * @param location Place to get conditions - preferred format: (STATE/City)
     * @return The WUConditions object with parameters set
     */
    public WUConditions setParameters(String location){

        url += location + ".json";
        return this;

    }

    /**
     * Downloads and parses all data from the given information
     */
    @Override
    public void downloadData() throws ApiException, BadRequestException, DataNotSetException, AuthRequiredException{

        try {

            result = Constructors.constructJSON(url);
            JSONObject jObj = new JSONObject(result.toString());
            termsUrl = jObj.getJSONObject("response").getString("termsofService");
            jObj = jObj.getJSONObject("current_observation");

            tempC            = jObj.getDouble("temp_c");
            obsEpoch         = jObj.getString("observation_epoch");
            tempF            = jObj.getDouble("temp_f");
            dewPointF        = jObj.getDouble("dewpoint_f");
            dewPointC        = jObj.getDouble("dewpoint_c");
            windKPH          = jObj.getDouble("wind_kph");
            windMPH          = jObj.getDouble("wind_mph");
            windDeg          = jObj.getDouble("wind_degrees");
            tempString       = jObj.getString("temperature_string");
            weather          = jObj.getString("weather");
            feelsLike        = jObj.getString("feelslike_string");
            precipTodMet     = jObj.getString("precip_today_metric");
            precipTodString  = jObj.getString("precip_today_string");
            precipTodIN      = jObj.getString("precip_today_in");
            precipHrIn       = jObj.getString("precip_1hr_in");
            iconUrl          = jObj.getString("icon_url");
            iconDesc         = jObj.getString("icon");
            imageLink        = jObj.getJSONObject("image").getString("link");
            imageTitle       = jObj.getJSONObject("image").getString("title");
            imageUrl         = jObj.getJSONObject("image").getString("url");
            uv               = jObj.getString("UV");
            stationId        = jObj.getString("station_id");
            localEpoch       = jObj.getString("local_epoch");
            locationTZShort  = jObj.getString("local_tz_short");
            windDirection    = jObj.getString("wind_dir");
            precipHrMetric   = jObj.getString("precip_1hr_metric");
            pressureIN       = jObj.getString("pressure_in");
            localTZLong      = jObj.getString("local_tz_long");
            windGustMPH      = jObj.getDouble("wind_gust_mph");
            windchillString  = jObj.getString("windchill_string");
            windchillF       = jObj.getString("windchill_f");
            windchillC       = jObj.getString("windchill_c");
            windGustKPH      = jObj.getDouble("wind_gust_kph");
            windString       = jObj.getString("wind_string");
            requestTime      = jObj.getString("local_time_rfc822");
            visibilityKM     = jObj.getString("visibility_km");
            relativeHumidity = jObj.getString("relative_humidity");
            pressureMB       = jObj.getString("pressure_mb");
            observationTime  = jObj.getString("observation_time");
            feelsLikeF       = jObj.getString("feelslike_f");
            feelsLikeC       = jObj.getString("feelslike_c");
            historyUrl       = jObj.getString("history_url");
            solarRad         = jObj.getString("solarradiation");
            obsElev          = jObj.getJSONObject("observation_location").getString("elevation");
            obsCtry          = jObj.getJSONObject("observation_location").getString("country");
            obsCity          = jObj.getJSONObject("observation_location").getString("city");
            obsLat           = jObj.getJSONObject("observation_location").getString("latitude");
            obsLon           = jObj.getJSONObject("observation_location").getString("longitude");
            obsState         = jObj.getJSONObject("observation_location").getString("state");
            obsFull          = jObj.getJSONObject("observation_location").getString("full");
            dispZip          = jObj.getJSONObject("display_location").getString("zip");
            dispElev         = jObj.getJSONObject("display_location").getString("elevation");
            dispCtry         = jObj.getJSONObject("display_location").getString("country");
            dispCity         = jObj.getJSONObject("display_location").getString("city");
            dispState        = jObj.getJSONObject("display_location").getString("state_name");
            dispFull         = jObj.getJSONObject("display_location").getString("full");
            dispLat          = jObj.getJSONObject("display_location").getString("latitude");
            dispLon          = jObj.getJSONObject("display_location").getString("longitude");
            dewPointDesc     = jObj.getString("dewpoint_string");
            pressureTrend    = jObj.getString("pressure_trend");
            forecastUrl      = jObj.getString("forecast_url");
            heatIndexF       = jObj.getDouble("heat_index_f");
            heatIndexC       = jObj.getDouble("heat_index_c");
            obUrl            = jObj.getString("ob_url");
            visibilityMI     = jObj.getString("visibility_mi");

        }
        catch (Exception e) {
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

    @Override
    public String toString(){
        return getRawData();
    }

    // ---------------------------------------
    // Begin data getters
    // ---------------------------------------


    /**
     * The url to Weather Undergrounds Terms of Service
     * @return the terms URL
     */
    public String getTermsUrl() {
        return termsUrl;
    }

    /**
     * The time at which the current conditions were observed
     * @return string representation at the time of recording
     */
    public String getObservationEpoch() {
        return obsEpoch;
    }

    /**
     * The temperature in degrees Celsius
     * @return temperature (C)
     */
    public double getTemperatureC() {
        return tempC;
    }

    /**
     * The dew point in degrees Fahrenheit
     * @return dew point (F)
     */
    public double getDewPointF() {
        return dewPointF;
    }

    /**
     * The dew point in degrees Celsius
     * @return dew point (C)
     */
    public double getDewPointC() {
        return dewPointC;
    }

    /**
     * The temperature in degrees Fahrenheit
     * @return temperature (F)
     */
    public double getTemperatureF() {
        return tempF;
    }

    /**
     * The wind speed in kilometers per hour
     * @return wind speed (kph)
     */
    public double getWindSpeedKph() {
        return windKPH;
    }

    /**
     * The wind speed in miles per hour
     * @return wind speed (mph)
     */
    public double getWindSpeedMPH() {
        return windMPH;
    }

    /**
     * The direction of the wind in degrees
     * @return wind direction (deg)
     */
    public double getWindDirectionDeg() {
        return windDeg;
    }

    /**
     * A description of the current temperature
     * @return temperature (description)
     */
    public String getTemperatureDesc() {
        return tempString;
    }

    /**
     * A description of the current weather
     * @return weather description
     */
    public String getWeather() {
        return weather;
    }

    /**
     * A description of what the current temperature feels like
     * @return temperature feel description
     */
    public String getFeelsLike() {
        return feelsLike;
    }

    /**
     * Today's precipitation in centimeters
     * @return today's precipitation (cm)
     */
    public String getPrecipTodCM() {
        return precipTodMet;
    }

    /**
     * A description of today's precipitation
     * @return today's precipitation
     */
    public String getPrecipTodString() {
        return precipTodString;
    }

    /**
     * Today's precipitation in inches
     * @return today's precipitation (cm)
     */
    public String getPrecipTodIN() {
        return precipTodIN;
    }

    /**
     * Current precipitation for the hour in inches
     * @return hour's precipitation (in)
     */
    public String getPrecipHrIn() {
        return precipHrIn;
    }

    /**
     * URL of an icon representing today's weather
     * @return icon url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * Description of the weather icon
     * @return weather icon description
     */
    public String getIconDesc() {
        return iconDesc;
    }

    /**
     * A link to the location of the Weather Underground page
     * @return iamge location
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * Title of the Weather underground weather page
     * @return image title
     */
    public String getImageTitle() {
        return imageTitle;
    }

    /**
     * Url to the Weather Underground image
     * @return image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * The current UV index
     * @return uv index
     */
    public String getUVIndex() {
        return uv;
    }

    /**
     * ID of the station that recorded the current conditions
     * @return station id
     */
    public String getStationId() {
        return stationId;
    }

    /**
     * Local time that this request was made
     * @return local time
     */
    public String getLocalEpoch() {
        return localEpoch;
    }

    /**
     * Short representation of the location's time zone
     * @return time zone (short)
     */
    public String getLocationTZShort() {
        return locationTZShort;
    }

    /**
     * String representation of the current wind direction
     * @return description of wind direction
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Current precipitation for the hour in cm
     * @return hour's precipitation (cm)
     */
    public String getPrecipHrMetric() {
        return precipHrMetric;
    }

    /**
     * Current pressure of the location in inches
     * @return pressure (in)
     */
    public String getPressureIN() {
        return pressureIN;
    }

    /**
     * Long representation of the location's time zone
     * @return time zone (long)
     */
    public String getLocalTZLong() {
        return localTZLong;
    }

    /**
     * Current wind gust in miles per hour
     * @return wind gust (mph)
     */
    public double getWindGustMPH() {
        return windGustMPH;
    }

    /**
     * Description of the current wind chill
     * @return wind chill
     */
    public String getWindChillString() {
        return windchillString;
    }

    /**
     * Current wind chill in degrees fahrenheit
     * @return wind chill (F)
     */
    public String getWindChillF() {
        return windchillF;
    }

    /**
     * Current wind chill in degrees Celsius
     * @return wind chill (C)
     */
    public String getWindChillC() {
        return windchillC;
    }

    /**
     * Current wind gust in kilometers per hour
     * @return wind gust (kph)
     */
    public double getWindGustKPH() {
        return windGustKPH;
    }

    /**
     * A description of the wind in the given location
     * @return wind description
     */
    public String getWindDesc() {
        return windString;
    }

    /**
     * The time at which this request was made
     * @return request time in unix time
     */
    public String getRequestTime() {
        return requestTime;
    }

    /**
     * Current visibility in kilometers
     * @return visibility (km)
     */
    public String getVisibilityKM() {
        return visibilityKM;
    }

    /**
     * The relative humidity for the current temperature
     * @return relative humidity (%)
     */
    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    /**
     * Current pressure in millibars
     * @return pressure (mb)
     */
    public String getPressureMB() {
        return pressureMB;
    }

    /**
     * The time at which these conditions were observed by the station
     * @return observation time
     */
    public String getObservationTime() {
        return observationTime;
    }

    /**
     * The feels-like temperature in Fahrenheit
     * @return temperature feel (F)
     */
    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    /**
     * The feels-like temperature in Celsius
     * @return temperature feel (C)
     */
    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    /**
     * Link to historic data for this location
     * @return historic data url
     */
    public String getHistoryUrl() {
        return historyUrl;
    }

    /**
     * Current solar radiation
     * @return solar radiation
     */
    public String getSolarRadiation() {
        return solarRad;
    }

    /**
     * The elevation at which this observation was made
     * @return observation elevation
     */
    public String getObsElevation() {
        return obsElev;
    }

    /**
     * The country in which this observation was made
     * @return observation country
     */
    public String getObsCountry() {
        return obsCtry;
    }

    /**
     * The city in which this observation was made
     * @return observation city
     */
    public String getObsCity() {
        return obsCity;
    }

    /**
     * The latitude at which this observation was made
     * @return observation latitude
     */
    public String getObsLatitude() {
        return obsLat;
    }

    /**
     * The longitude at which this observation was made
     * @return observation longitude
     */
    public String getObsLongitude() {
        return obsLon;
    }

    /**
     * The state in which this observation was made
     * @return observation state
     */
    public String getObsState() {
        return obsState;
    }

    /**
     * A full description of where this observation was made
     * @return observation location description
     */
    public String getObsDescription() {
        return obsFull;
    }

    /**
     * The elevation at which this observation was made
     * @return observation elevation
     */
    public String getDispZip() {
        return dispZip;
    }

    /**
     * The elevation for where this data was displayed
     * @return display elevation
     */
    public String getDispElevation() {
        return dispElev;
    }

    /**
     * The country for where this data was displayed
     * @return display country
     */
    public String getDispCountry() {
        return dispCtry;
    }

    /**
     * The city for where this data was displayed
     * @return display city
     */
    public String getDispCity() {
        return dispCity;
    }

    /**
     * The state for where this data was displayed
     * @return display state
     */
    public String getDispState() {
        return dispState;
    }

    /**
     * The full description for where this data was displayed
     * @return display description
     */
    public String getDispDescription() {
        return dispFull;
    }

    /**
     * The latitude for where this data was displayed
     * @return display latitude
     */
    public String getDispLatitude() {
        return dispLat;
    }

    /**
     * The longitude for where this data was displayed
     * @return display longitude
     */
    public String getDispLongitude() {
        return dispLon;
    }

    /**
     * A description of the current dew point
     * @return dew point description
     */
    public String getDewPointDesc() {
        return dewPointDesc;
    }

    /**
     * The current pressure trend as "+" or "-"
     * @return whether the pressure is going up or down
     */
    public String getPressureTrend() {
        return pressureTrend;
    }

    /**
     * Url to the current forecast at Weather Underground
     * @return forecast url
     */
    public String getForecastUrl() {
        return forecastUrl;
    }

    /**
     * Current heat index in Fahrenheit
     * @return heat index (F)
     */
    public double getHeatIndexF() {
        return heatIndexF;
    }

    /**
     * Current heat index in Celsius
     * @return heat index (C)
     */
    public double getHeatIndexC() {
        return heatIndexC;
    }

    /**
     * URL to information at the observation location
     * @return url to observation location
     */
    public String getObUrl() {
        return obUrl;
    }

    /**
     * Current visibility in miles
     * @return visibility (mi)
     */
    public String getVisibilityMI() {
        return visibilityMI;
    }
}
