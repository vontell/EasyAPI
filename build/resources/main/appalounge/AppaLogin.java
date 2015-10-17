package appalounge;

import org.json.JSONArray;
import org.json.JSONObject;
import resources.Constructors;
import resources.exceptions.*;
import resources.infrastructure.EasyApiComponent;

/**
 * Gives login authentication for the APPA Lounge API
 * @author Aaron Vontell
 * @date 10/16/2015
 * @version 0.1
 */
public class AppaLogin implements EasyApiComponent {

    private String url = "";
    private String result;
    private String key;

    private String username;
    private String password;

    /**
     * Create a login object
     */
    public AppaLogin() {

        url = AppaLounge.BASE_URL + "login/";

    }

    /**
     * Sets the username and password to use in the request
     * @param username The username to log in with
     * @param password The corresponding password
     */
    public void setParameters(String username, String password){

        this.username = username;
        this.password = password;

    }

    @Override
    public void downloadData() throws ApiException, AuthRequiredException, BadConnectionException, BadRequestException, DataNotSetException {

        String request = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        System.out.println(url);
        JSONObject response = Constructors.postData(url, request);
        if(response.has("key")){
            result = response.toString();
            key = response.getString("key");
        }
        else{
            throw new ApiException(response.getString("error"));
        }

    }

    public String getKey(){
        if(key != null){
            return key;
        } else {
            return "";
        }
    }

    @Override
    public String getRawData() {
        return result;
    }
}
