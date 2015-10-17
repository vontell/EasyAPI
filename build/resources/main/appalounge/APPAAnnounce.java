package appalounge;

import org.json.JSONArray;
import org.json.JSONObject;
import resources.Constructors;
import resources.exceptions.ApiException;
import resources.exceptions.AuthRequiredException;
import resources.exceptions.BadRequestException;
import resources.exceptions.DataNotSetException;
import resources.infrastructure.EasyApiComponent;

/**
 * Returns an array of the announcements made on APPA Lounge
 * @author Aaron Vontell
 * @date 10/16/2015
 * @version 0.1
 */
public class APPAAnnounce implements EasyApiComponent{

    private String url = "";
    private JSONArray result;

    private String[] message;
    private String[] creator;
    private String[] created;

    /**
     * Create an announcement object
     */
    protected APPAAnnounce() {

        url = AppaLounge.BASE_URL + "announcements/2/";

    }

    /**
     * Downloads and parses all data from the given information
     */
    @Override
    public void downloadData() throws ApiException, BadRequestException, DataNotSetException, AuthRequiredException {

        try {
            result = Constructors.constructJSONArray(url);
            message = new String[result.length()];
            creator = new String[result.length()];
            created = new String[result.length()];
            for(int i = 0; i < result.length(); i++){
                String mes = result.getJSONObject(i).getString("message");
                String user = result.getJSONObject(i).getString("creator");
                String date = result.getJSONObject(i).getString("created");
                message[i] = mes;
                creator[i] = user;
                created[i] = date;

            }
        } catch (Exception e) {
                throw new ApiException("API Error: " + result.toString());
        }

    }

    /**
     * Get the number of announcements
     * @return announcement amount
     */
    public int getNumAnnouncements(){
        return message.length;
    }

    /**
     * Get the message at a certain index
     * @param i The num message to get
     * @return The message at that position
     */
    public String getMessageAt(int i){
        return message[i];
    }

    /**
     * Get the user at a certain index
     * @param i The num user to get
     * @return The user at that position
     */
    public String getCreatorAt(int i){
        return creator[i];
    }

    /**
     * Get the date at a certain index
     * @param i The num date to get
     * @return The date at that position
     */
    public String getCreatedAt(int i){
        return created[i];
    }

    @Override
    public String getRawData() {
        if(result != null){
            return result.toString();
        } else {
            return "No data";
        }
    }

}
