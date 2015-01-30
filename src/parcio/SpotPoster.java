package parcio;

import java.io.IOException;

import org.json.JSONObject;

import resources.Constructors;

/**
 * A class that can post a parking spot to the Parcio Django REST Framework
 * API at: https://parcio.herokuapp.com
 * @author Aaron Vontell
 * @version 0.1
 */
public class SpotPoster {

	private String apiKey;
	private String BASE_URL = "https://parcio.herokuapp.com/parkspot/";
	private String data;
	private String finalURL;
	
	private int responseCode;	// The response from the POST call
	private boolean wasSuccess;	// Indicates that the post was made successfully
	private JSONObject jObj;	// The JSON Object holding the data we wish to change
	
	/**
	 * Creates an object that can post to the Parcio API
	 * @param key Your API key for Parcio
	 */
	public SpotPoster(String key){
		
		apiKey = key;
		
		//Should eventually move to loading
		finalURL = "https://parcio.herokuapp.com/parkspot/";
		
	}
	
	/**
	 * Sets the data that will be posted to the Parcio server
	 * @param user The user that is creating the spot
	 * @param address The address of the spot
	 * @param lat The latitude of the spot (only up to 5 decimals)
	 * @param lon The longitude of the spot (only up to 5 decimals)
	 * @param time_out The UNIX time of when this spot will expire
	 * @param points The point cost of this spot
	 * @param spot_type Use 0 = Metered, 2 = Permit, and 3 = Mixed
	 * @param met_time If mixed, this is when the metered time starts
	 * @param per_time If mixed, this is when the permit time starts
	 * @param free_time If mixed, this is whe the free time starts
	 * @param available Indicates whether or not the spot is actually currently available
	 */
	public void setData(String user, String address, String lat, String lon,
						int time_out, int points, int spot_type, int met_time,
						int per_time, int free_time, boolean available){
		
		jObj = new JSONObject();
		jObj.put("user", user);
		jObj.put("address", address);
		jObj.put("lat", lat);
		jObj.put("lon", lon);
		jObj.put("time_out", time_out);
		jObj.put("points", points);
		jObj.put("spot_type", spot_type);
		jObj.put("met_time", met_time);
		jObj.put("per_time", per_time);
		jObj.put("free_time", free_time);
		jObj.put("available", available);
		
		System.out.println(jObj.toString());
		
	}
	
	/**
	 * This will post the spot to the Parcio server
	 * @throws IOException 
	 */
	public void postData() throws IOException{
		responseCode = Constructors.postData(finalURL, jObj.toString());
		if(responseCode/100 == 2){
			wasSuccess = true;
		}
		else {
			wasSuccess = false;
		}
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @return the wasSuccess
	 */
	public boolean wasSuccess() {
		return wasSuccess;
	}
	
	
	
}
