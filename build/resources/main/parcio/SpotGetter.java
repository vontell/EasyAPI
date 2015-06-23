package parcio;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import resources.Constructors;

/**
 * A class that can gather parking spot from the Parcio Django REST Framework
 * API at: https://parcio.herokuapp.com
 * @author Aaron Vontell
 * @version 0.1
 */
public class SpotGetter {
	
	private String apiKey;
	private String BASE_URL = "https://parcio.herokuapp.com/parkspot/";
	private String jsonContent;
	private String finalURL;
	
	//Stored arrays of information, each index corresponds to a different spot
	private int numSpots;
	private String[] user;			// The user who has possession of the spot
	private int[] spotType; 		// 1 = Metered , 2 = Permit, 3 = Mixed
	private boolean[] isFilled; 	// Whether the spot is filled or not
	private String[] address; 		// The human address of the spot
	private double[] lat; 			// Latitude of the spot
	private double[] lon; 			// Longitude of the spot
	private long[] ID; 				// The spots ID
	private String[] note; 			// The extra note of the spot
	private int[] timeout; 			// The spots timeout value in seconds since epoch
	private int[] points; 			// The number of points a spot is worth
	private int[] metTime;			// The start time of metered (if mixed)
	private int[] perTime;			// The start time of permit (if mixed)
	private int[] freTime;			// The start time of free (if mixed)
	private String[] timeCreated;	// The time the spot was created
	
	/**
	 * Creates an object that can access the Parcio API
	 * @param key Your API key for Parcio
	 */
	public SpotGetter(String key){
		
		apiKey = key;
		
		//Should eventually move to loading
		finalURL = "https://parcio.herokuapp.com/parkspot/?format=json";
		
	}
	
	/**
	 * Finds the spots within the radius of the given coordinates, and
	 * returns the spot indeces
	 * @param lati The latitude to search near
	 * @param long The longitude to search near
	 * @param radius The radius to search within (in degrees?)
	 * @return The corresponding indeces of the spots that are nearby
	 */
	public int[] findNearbySpots(double lati, double longi, double radius){
		
		ArrayList<Integer> answers = new ArrayList<Integer>();
		
		for(int index = 0; index < numSpots; index++){
			double distance = Math.sqrt(Math.pow(lati - lat[index], 2) + Math.pow(longi - lon[index], 2));
			if(distance < radius){
				answers.add(index);
			}
		}
		
		int[] results = new int[answers.size()];
		for(int i = 0; i < results.length; i++){
			results[i] = answers.get(i);
		}
		
		return results;
		
	}
	
	/**
	 * Downloads the data from the server
	 * This is the method that should be asynchronously performed
	 */
	public void downloadData(){
		
		//Retrieve the JSON data, and also store the text for later use
		JSONArray jObject = Constructors.constructJSONArray(finalURL);
		jsonContent = jObject.toString();
				
		//Store the information
		parseData();
		
	}
	
	//Parses and stores the data
	private void parseData(){
		
		JSONArray jArray = new JSONArray(jsonContent);
		numSpots = jArray.length();
		
		// Create each array
		user = new String[numSpots];
		spotType = new int[numSpots];
		isFilled = new boolean[numSpots];
		address = new String[numSpots];
		lat = new double[numSpots];
		lon = new double[numSpots];
		ID = new long[numSpots];
		note = new String[numSpots];
		timeout = new int[numSpots];
		points = new int[numSpots];
		metTime = new int[numSpots];
		perTime = new int[numSpots];
		freTime = new int[numSpots];
		timeCreated = new String[numSpots];
		
		for(int index = 0; index < numSpots; index++){
			JSONObject jObj = jArray.getJSONObject(index);
			user[index] = jObj.getString("user");
			address[index] = jObj.getString("address");
			lat[index] = jObj.getDouble("lat");
			lon[index] = jObj.getDouble("lon");
			timeout[index] = jObj.getInt("time_out");
			points[index] = jObj.getInt("points");
			spotType[index] = jObj.getInt("spot_type");
			metTime[index] = jObj.getInt("met_time");
			perTime[index] = jObj.getInt("per_time");
			freTime[index] = jObj.getInt("free_time");
			isFilled[index] = jObj.getBoolean("available");
			timeCreated[index] = jObj.getString("time_create");
		}
		
	}

	/**
	 * @return the numSpots
	 */
	public int getNumSpots() {
		return numSpots;
	}

	/**
	 * @return the user
	 */
	public String getUser(int i) {
		return user[i];
	}

	/**
	 * @return the spotType
	 */
	public int getSpotType(int i) {
		return spotType[i];
	}

	/**
	 * @return the isFilled
	 */
	public boolean getIsFilled(int i) {
		return isFilled[i];
	}

	/**
	 * @return the address
	 */
	public String getAddress(int i) {
		return address[i];
	}

	/**
	 * @return the lat
	 */
	public double getLat(int i) {
		return lat[i];
	}

	/**
	 * @return the lon
	 */
	public double getLon(int i) {
		return lon[i];
	}

	/**
	 * @return the iD
	 */
	public long getID(int i) {
		return ID[i];
	}

	/**
	 * @return the note
	 */
	public String getNote(int i) {
		return note[i];
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout(int i) {
		return timeout[i];
	}

	/**
	 * @return the points
	 */
	public int getPoints(int i) {
		return points[i];
	}

	/**
	 * @return the metTime
	 */
	public int getMetTime(int i) {
		return metTime[i];
	}

	/**
	 * @return the perTime
	 */
	public int getPerTime(int i) {
		return perTime[i];
	}

	/**
	 * @return the freTime
	 */
	public int getFreTime(int i) {
		return freTime[i];
	}

	/**
	 * @return the timeCreated
	 */
	public String getTimeCreated(int i) {
		return timeCreated[i];
	}
	
	
	
}
