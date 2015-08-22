package stackexchange;

import org.json.JSONArray;
import org.json.JSONObject;
import resources.Constructors;

/**
 * A class that can gather information from the Stack Exchange API
 * Also includes the capability to perform authentication
 * Documentation at: https://api.stackexchange.com/
 * @author Aaron Vontell
 * @version 0.1
 */
public class StackExchange {

    private final String BASE_URL = "https://api.stackexchange.com/2.2/";
    private String site;

    /**
     * Creates an object that can access the StackExchange API
     * @param site The site to use, which can be found at https://api.stackexchange.com/docs/sites or from getSitesParameters()
     */
    public StackExchange(String site){

        this.site = site;

    }

    /**
     * Returns an array of api site parameters that can be used to create a StackExchange object
     * This will both download and parse API information
     * @return An array of all api site parameters
     */
    public static String[] getSiteParameters(){

        String siteURL = "https://api.stackexchange.com/2.2/sites";
        JSONObject jObject = Constructors.constructJSON(siteURL);
        JSONArray jArray = jObject.getJSONArray("items");
        //System.out.println(jObject.toString());
        int numSites = jArray.length();
        String[] sites = new String[numSites];

        for(int i = 0; i < numSites; i++){
            JSONObject siteObject = jArray.getJSONObject(i);
            String apiSite = siteObject.getString("api_site_parameter");
            sites[i] = apiSite;
        }

        return sites;

    }

}
