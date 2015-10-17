package appalounge;

import resources.infrastructure.EasyApiObject;

/**
 * A class that can gather information from the APPA Lounge Website
 * @author Aaron Vontell
 * @version 0.1
 */
public class AppaLounge extends EasyApiObject {


    private String apiKey;
    protected static String BASE_URL = "http://appalounge.com/data/";

    /**
     * Create a APPA Lounge API object, which will provide factories to access the
     * API components and services available
     * @param apiKey Your Access Key gained when logging in
     */
    public AppaLounge(String apiKey) {

        this.apiKey = apiKey;

    }

    /**
     * Create a APPA Lounge API object, which will provide factories to access the
     * API components and services available (no key mode)
     */
    public AppaLounge() {

    }

    /**
     * Instantiates a new announcements
     * Can be used to retrieve current announcements from APPA
     * @return WUConditions object for current weather information
     */
    public APPAAnnounce createAnnouncementObject(){ return new APPAAnnounce(); }

    /**
     * Instantiates a new login object
     * Can be used to login in a user and obtain a key back
     * @return
     */
    public AppaLogin createLoginObject(){ return new AppaLogin(); }

    /**
     * Retrieve the list of all requests available in this API
     *
     * @return The list of requests
     */
    @Override
    public String[] getPossibleRequests() {
        return new String[0];
    }
}
