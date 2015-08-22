package weatherunderground;

import org.json.JSONObject;
import resources.Constructors;
import resources.interfaces.EasyApiComponent;

/**
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
    private String windGustMPH;
    private String windchillString;
    private String windchillF;
    private String windchillC;
    private String windGustKPH;
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
    private String dispWMO;
    private String dispFull;
    private String dispLat;
    private String dispLon;
    private String dewPointDesc;
    private String pressureTrend;
    private String forecastUrl;
    private String heatIndexF;
    private String heatIndexC;
    private String obUrl;
    private String visibilityMI;


    /**
     * Create this current conditions object with a given API key
     * @param apiKey The valid API key to gain access to the REST API
     */
    protected WUConditions(String apiKey) {

        url +=  WeatherUnderground.BASE_URL + apiKey + "/conditions/q/";

    }

    public WUConditions setParameters(String location){

        url += location + ".json";
        return this;

    }

    public void downloadData(){
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
        windGustMPH      = jObj.getString("wind_gust_mph");
        windchillString  = jObj.getString("windchill_string");
        windchillF       = jObj.getString("windchill_f");
        windchillC       = jObj.getString("windchill_c");
        windGustKPH      = jObj.getString("wind_gust_kph");
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
        dispWMO          = jObj.getJSONObject("display_location").getString("wmo");
        dispFull         = jObj.getJSONObject("display_location").getString("full");
        dispLat          = jObj.getJSONObject("display_location").getString("latitude");
        dispLon          = jObj.getJSONObject("display_location").getString("longitude");
        dewPointDesc     = jObj.getString("dewpoint_string");
        pressureTrend    = jObj.getString("pressure_trend");
        forecastUrl      = jObj.getString("forecast_url");
        heatIndexF       = jObj.getString("heat_index_f");
        heatIndexC       = jObj.getString("heat_index_c");
        obUrl            = jObj.getString("ob_url");
        visibilityMI     = jObj.getString("visibility_mi");

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
     * @return A string representation at the time of recording
     */
    public String getObservationEpoch() {
        return obsEpoch;
    }

    public double getTempC() {
        return tempC;
    }

    public double getDewPointF() {
        return dewPointF;
    }

    public double getDewPointC() {
        return dewPointC;
    }

    public double getTempF() {
        return tempF;
    }

    public double getWindKPH() {
        return windKPH;
    }

    public double getWindMPH() {
        return windMPH;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public String getTempString() {
        return tempString;
    }

    public String getWeather() {
        return weather;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getPrecipTodMet() {
        return precipTodMet;
    }

    public String getPrecipTodString() {
        return precipTodString;
    }

    public String getPrecipTodIN() {
        return precipTodIN;
    }

    public String getPrecipHrIn() {
        return precipHrIn;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getIconDesc() {
        return iconDesc;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUv() {
        return uv;
    }

    public String getStationId() {
        return stationId;
    }

    public String getLocalEpoch() {
        return localEpoch;
    }

    public String getLocationTZShort() {
        return locationTZShort;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getPrecipHrMetric() {
        return precipHrMetric;
    }

    public String getPressureIN() {
        return pressureIN;
    }

    public String getLocalTZLong() {
        return localTZLong;
    }

    public String getWindGustMPH() {
        return windGustMPH;
    }

    public String getWindchillString() {
        return windchillString;
    }

    public String getWindchillF() {
        return windchillF;
    }

    public String getWindchillC() {
        return windchillC;
    }

    public String getWindGustKPH() {
        return windGustKPH;
    }

    public String getWindString() {
        return windString;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getVisibilityKM() {
        return visibilityKM;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public String getPressureMB() {
        return pressureMB;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public String getHistoryUrl() {
        return historyUrl;
    }

    public String getSolarRad() {
        return solarRad;
    }

    public String getObsElev() {
        return obsElev;
    }

    public String getObsCtry() {
        return obsCtry;
    }

    public String getObsCity() {
        return obsCity;
    }

    public String getObsLat() {
        return obsLat;
    }

    public String getObsLon() {
        return obsLon;
    }

    public String getObsState() {
        return obsState;
    }

    public String getObsFull() {
        return obsFull;
    }

    public String getDispZip() {
        return dispZip;
    }

    public String getDispElev() {
        return dispElev;
    }

    public String getDispCtry() {
        return dispCtry;
    }

    public String getDispCity() {
        return dispCity;
    }

    public String getDispState() {
        return dispState;
    }

    public String getDispWMO() {
        return dispWMO;
    }

    public String getDispFull() {
        return dispFull;
    }

    public String getDispLat() {
        return dispLat;
    }

    public String getDispLon() {
        return dispLon;
    }

    public String getDewPointDesc() {
        return dewPointDesc;
    }

    public String getPressureTrend() {
        return pressureTrend;
    }

    public String getForecastUrl() {
        return forecastUrl;
    }

    public String getHeatIndexF() {
        return heatIndexF;
    }

    public String getHeatIndexC() {
        return heatIndexC;
    }

    public String getObUrl() {
        return obUrl;
    }

    public String getVisibilityMI() {
        return visibilityMI;
    }
}
