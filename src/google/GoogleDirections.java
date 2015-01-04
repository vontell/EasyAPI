package google;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import resources.Constructors;

/**
 * A class that can gather information from the Google Directions API
 * Documentation at: https://developers.google.com/maps/documentation/directions/
 * @author Aaron Vontell
 * @version 0.1
 */
public class GoogleDirections {

	private String apiKey;
	private String BASE_URL = "https://maps.googleapis.com/maps/api/directions/json?";
	
	//Defaults for the api
	//TODO: Implement waypoints and alternatives
	private String mode = "driving";
	private String alternatives = "false";
	private String avoid = null;
	private String language = null;
	
	/**
	 * Creates an object that can access the Google Directions API
	 * @param key Your API key for the Google Directions API
	 */
	public GoogleDirections(String key){
		
		apiKey = key;
		
	}
	
	/**
	 * Retrieves the time it takes to get from one location to another
	 * @param oriLat The origins latitude
	 * @param oriLon The origins longitude
	 * @param destLat The destinations latitude
	 * @param destLon The destinations longitude
	 * @return The time is takes to travel from the origin to the destination
	 */
	public Long getTimeToLocation(double oriLat, double oriLon, double destLat, double destLon) throws JSONException{
		
		//start by constructing the URL
		String[] params = new String[6];
		params[0] = "origin";
		params[1] = Double.toString(oriLat).replace(" ", "+") + "," + Double.toString(oriLon).replace(" ", "+");
		params[2] = "destination";
		params[3] = Double.toString(destLat).replace(" ", "+") + "," + Double.toString(destLon).replace(" ", "+");
		params[4] = "key";
		params[5] = apiKey;
		
		String url = Constructors.constructUrl(BASE_URL, params);
		
		//TODO: Need to wrap this is some sort of time-based stop
		JSONObject jObject = Constructors.constructJSON(url);
		
		//Parses the time out of the JSON content

		JSONArray jArray = jObject.getJSONArray("routes");
		jObject = jArray.getJSONObject(0);
		jArray = jObject.getJSONArray("legs");
		jObject = jArray.getJSONObject(0);
		jObject = jObject.getJSONObject("duration");
		Long time = jObject.getLong("value");
		
		return time;
		
	}
	
	/**
	 * Retrieves the time it takes to get from one location to another
	 * @param origin The string representation/address of your origin
	 * @param destination The string representation/address of your destination
	 * @return The time is takes to travel from the origin to the destination
	 */
	public Long getTimeToLocation(String origin, String destination){
		
		//start by constructing the URL
		String[] params = new String[6];
		params[0] = "origin";
		params[1] = origin.replace(" ", "+") + "," + origin.replace(" ", "+");
		params[2] = "destination";
		params[3] = destination.replace(" ", "+") + "," + destination.replace(" ", "+");
		params[4] = "key";
		params[5] = apiKey;
		
		String url = Constructors.constructUrl(BASE_URL, params);
		
		//TODO: Need to wrap this is some sort of time-based stop
		JSONObject jObject = Constructors.constructJSON(url);
		
		//Parses the time out of the JSON content

		JSONArray jArray = jObject.getJSONArray("routes");
		jObject = jArray.getJSONObject(0);
		jArray = jObject.getJSONArray("legs");
		jObject = jArray.getJSONObject(0);
		jObject = jObject.getJSONObject("duration");
		Long time = jObject.getLong("value");
		
		return time;
		
	}
	
	
	
}
