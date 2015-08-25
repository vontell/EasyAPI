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
 * Returns an array of the short name description, expiration time and a long text
 * description of severe alerts, if one has been issued for the searched
 * upon location. Only available in the US. More information
 * at http://www.wunderground.com/weather/api/d/docs?d=data/alerts&MR=1
 * @author Aaron Vontell
 * @date 8/22/2015
 * @version 0.1
 */
public class WUAlerts implements EasyApiComponent{

    private String url = "";
    private JSONObject result;

    //Variables generated from the download
    private String[] alertType;
    private String[] description;
    private String[] date;
    private String[] dateEpoch;
    private String[] expires;
    private String[] expiresEpoch;
    private String[] message;
    private String[] phenomena;
    private String[] significance;
    private int numAlerts;

    /**
     * Create this current alerts object with a given API key
     * @param apiKey The valid API key to gain access to the REST API
     */
    protected WUAlerts(String apiKey) {

        url +=  WeatherUnderground.BASE_URL + apiKey + "/alerts/q/";

    }

    /**
     * Set the location to find current alerts (city and state)
     * @param location Place to get alerts - preferred format: (STATE/City)
     * @return The WUAlerts object with parameters set
     */
    public WUAlerts setParameters(String location){

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
            if(jObj.getJSONArray("alerts").length() == 0){
                numAlerts     = 0;
                alertType     = null;
                description   = null;
                date          = null;
                dateEpoch    = null;
                expires       = null;
                expiresEpoch = null;
                message       = null;
                phenomena     = null;
                significance  = null;
            } else {
                JSONArray alerts = jObj.getJSONArray("alerts");
                numAlerts = alerts.length();
                alertType     = new String[numAlerts];
                description   = new String[numAlerts];
                date          = new String[numAlerts];
                dateEpoch    = new String[numAlerts];
                expires       = new String[numAlerts];
                expiresEpoch = new String[numAlerts];
                message       = new String[numAlerts];
                phenomena     = new String[numAlerts];
                significance  = new String[numAlerts];
                for(int i = 0; i < numAlerts; i++){
                    JSONObject alert = alerts.getJSONObject(i);
                    alertType[i]     = alert.getString("type");
                    description[i]   = alert.getString("description");
                    date[i]          = alert.getString("date");
                    dateEpoch[i]    = alert.getString("date_epoch");
                    expires[i]       = alert.getString("expires");
                    expiresEpoch[i] = alert.getString("epoch");
                    message[i]       = alert.getString("message");
                    phenomena[i]     = alert.getString("phenomena");
                    significance[i]  = alert.getString("significance");
                }
            }
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
     * The number of alerts in the given location
     * @return number of alerts
     */
    public int getNumAlerts(){
        return numAlerts;
    }

    /**
     * Get the abbreviated type code of the alert
     * @param index The # alert you wish to observe
     * @return type of alert (abbr. code)
     */
    public String getType(int index){
        return alertType[index];
    }

    /**
     * Get the description of the alert
     * @param index The # alert you wish to observe
     * @return the alert (description)
     */
    public String getDescription(int index){
        return description[index];
    }

    /**
     * The human readable date that the alert was issued
     * @param index The # alert you wish to observe
     * @return time of the alert
     */
    public String getDate(int index){
        return date[index];
    }

    /**
     * The epoch time that the alert was issued
     * @param index The # alert you wish to observe
     * @return epoch of the alert
     */
    public String getDateEpoch(int index){
        return dateEpoch[index];
    }

    /**
     * The human readable time that the alert will expire
     * @param index The # alert you wish to observe
     * @return expiration of the alert
     */
    public String getExpiration(int index){
        return expires[index];
    }

    /**
     * The epoch time that the alert will expire
     * @param index The # alert you wish to observe
     * @return epoch expiration of the alert
     */
    public String getExpirationEpoch(int index){
        return expiresEpoch[index];
    }

    /**
     * A message about the alert
     * @param index The # alert you wish to observe
     * @return message about the alert
     */
    public String getAlertMessage(int index){
        return message[index];
    }

    /**
     * The phenomena that is causing the alert
     * @param index The # alert you wish to observe
     * @return phenomena of the alert
     */
    public String getPhenomena(int index){
        return phenomena[index];
    }

    /**
     * The significance of the alert
     * @param index The # alert you wish to observe
     * @return significance of the alert
     */
    public String getSignificance(int index){
        return significance[index];
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

}
