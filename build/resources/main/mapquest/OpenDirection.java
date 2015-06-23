package mapquest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import resources.Constructors;

/**
 * A class that can gather information from the MapQuest Directions API
 * Documentation at: http://developer.mapquest.com/web/products/dev-services/directions-ws
 * @author Aaron Vontell
 * @version 0.3
 */
public class OpenDirection {
	
	private String apiKey;
	private String BASE_URL = "http://open.mapquestapi.com/directions/v2/route?";
	private String jsonContent;
	private String finalURL;
	
	//Stored Information
	private DirectionNode[] directionNodes;
	private double[] ulLatLng;
	private double[] lrLatLng;
	private double fuelUsed;
	private int totalTime;
	private double totalDistance;
	private int[] maneuverPoints;
	private double[] drawLatPoints;
	private double[] drawLngPoints;
	private int currentNode;

	// When parsing, throw or indicate error rather than setting default

	/**
	 * Creates an object that can access the Open Direction API V2
	 * @param key Your API key for the Open Direction API
	 */
	public OpenDirection(String key){
		
		apiKey = key;
		
	}
	
	/**
	 * Sets the trip using string representations of addresses
	 * @param origin The string representation/address of your origin
	 * @param destination The string representation/address of your destination
	 */
	public void setTrip(String origin, String destination){
		
		//start by constructing the URL
		String[] params = new String[10];
		params[0] = "key";
		params[1] = apiKey;
		params[2] = "from";
		params[3] = origin.replace(" ", "+");
		params[4] = "to";
		params[5] = destination.replace(" ", "+");
		params[6] = "shapeFormat";
		params[7] = "raw";
		params[8] = "generalize";
		params[9] = "0";
		
		finalURL = Constructors.constructUrl(BASE_URL, params);
		
	}
	
	/**
	 * Sets the trip using origin and destination coordinates
	 * @param oriLat The origins latitude
	 * @param oriLon The origins longitude
	 * @param destLat The destinations latitude
	 * @param destLon The destinations longitude
	 */
	public void setTrip(double oriLat, double oriLon, double destLat, double destLon){
		
		//start by constructing the URL
		String[] params = new String[10];
		params[0] = "key";
		params[1] = apiKey;
		params[2] = "from";
		params[3] = Double.toString(oriLat).replace(" ", "+") + "," + Double.toString(oriLon).replace(" ", "+");
		params[4] = "to";
		params[5] = Double.toString(destLat).replace(" ", "+") + "," + Double.toString(destLon).replace(" ", "+");
		
		finalURL = Constructors.constructUrl(BASE_URL, params);
		
	}
	
	/**
	 * Downloads the data from the server
	 * This is the method that should be asynchronously performed
	 */
	public void downloadData(){
		
		//Retrieve the JSON data, and also store the text for later use
		JSONObject jObject = Constructors.constructJSON(finalURL);
		jsonContent = jObject.toString();
				
		//Store the information
		parseData();
		
	}
	
	/**
	 * Parses all trip data
	 */
	private void parseData(){
		
		parseBoundingBox();
		parseFuel();
		parseTime();
		parseDistance();
		parseLegs();
		parseManeuverPoints();
		parseDrawingPoints();
		
	}
	
	/**
	 * Stores the bounding box from the call
	 * If this fails, returns bounding box of all 0 values
	 */
	private void parseBoundingBox(){
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			jObject = jObject.getJSONObject("boundingBox");
			JSONObject ulObject = jObject.getJSONObject("ul");
			JSONObject lrObject = jObject.getJSONObject("lr");
			ulLatLng = new double[2];
			ulLatLng[0] = ulObject.getDouble("lat");
			ulLatLng[1] = ulObject.getDouble("lng");
			lrLatLng = new double[2];
			lrLatLng[0] = lrObject.getDouble("lat");
			lrLatLng[1] = lrObject.getDouble("lng");
		}
		catch (JSONException e){
			System.out.println(e.getMessage());
			ulLatLng = new double[2];
			ulLatLng[0] = 0d;
			ulLatLng[1] = 0d;
			lrLatLng = new double[2];
			lrLatLng[0] = 0d;
			lrLatLng[1] = 0d;
		}
		
	}
	
	/**
	 * Stores the fuel used on this trip
	 * If this fails, set fuel of 0
	 */
	private void parseFuel(){
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			fuelUsed = jObject.getDouble("fuelUsed");
		}
		catch (JSONException e){
			fuelUsed = 0d;
		}
		
	}
	
	/**
	 * Stores the real time to make this trip
	 * If this fails, set a time of 0
	 */
	private void parseTime(){
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			totalTime = jObject.getInt("time");
		}
		catch (JSONException e){
			totalTime = 0;
		}
		
	}

	/**
	 * Stores the total distance of this trip
	 * If this fails, sets a distance of 0
	 */
	private void parseDistance(){
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			totalDistance = jObject.getDouble("distance");
		}
		catch (JSONException e){
			totalDistance = 0d;
		}
		
	}
	
	/**
	 * Will create the DirectionNodes for the trip
	 * If this fails, sets one DirectionNode of human direction "Error"
	 */
	private void parseLegs(){
		
		ArrayList<DirectionNode> directions = new ArrayList<DirectionNode>();
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			JSONArray jArray = jObject.getJSONArray("legs");
			jObject = jArray.getJSONObject(0);
			jArray = jObject.getJSONArray("maneuvers");
			
			for(int i = 0; i < jArray.length(); i++){
				
				jObject = jArray.getJSONObject(i);
					
				int index = jObject.getInt("index");
				int manType = jObject.getInt("turnType");
				String direct = jObject.getString("narrative");
				double dist = jObject.getDouble("distance");
				int time = jObject.getInt("time");

				directions.add(new DirectionNode(manType, index, direct, dist, time));
				
			}
			
		}
		catch (JSONException e){
			directions = new ArrayList<DirectionNode>();
			directions.add(new DirectionNode(0, 0, "Error", 0, 0));
			System.out.println(e.getMessage());
		}
		
		directionNodes = directions.toArray(new DirectionNode[directions.size()]);
		
	}
	
	/**
	 * Parses the drawing points that a maneuver is located at
	 * If this fails, returns an empty array
	 */
	private void parseManeuverPoints(){
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			jObject = jObject.getJSONObject("shape");
			JSONArray jArray = jObject.getJSONArray("maneuverIndexes");
			maneuverPoints = new int[jArray.length()];
			for(int i = 0; i < jArray.length(); i++){
				maneuverPoints[i] = jArray.getInt(i);
			}
		}
		catch (JSONException e){
			maneuverPoints = new int[0];
		}
		
	}
	
	/**
	 * Parses the drawing points for drawing the route
	 * If this fails, set empty arrays
	 */
	private void parseDrawingPoints(){
		
		try{
			JSONObject jObject = new JSONObject(jsonContent);
			jObject = jObject.getJSONObject("route");
			jObject = jObject.getJSONObject("shape");
			JSONArray jArray = jObject.getJSONArray("shapePoints");
			drawLatPoints = new double[jArray.length()/2];
			drawLngPoints = new double[jArray.length()/2];
			for(int i = 0; i < jArray.length()/2; i++){
				drawLatPoints[i] = jArray.getDouble(i*2);
				drawLngPoints[i] = jArray.getDouble(i*2 + 1);
			}
		}
		catch(JSONException e){
			System.out.println(e.getMessage());
			drawLatPoints = new double[0];
			drawLngPoints = new double[0];
			
		}
		
	}
	
	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @return the bASE_URL
	 */
	public String getBASE_URL() {
		return BASE_URL;
	}

	/**
	 * @return the jsonContent
	 */
	public String getJsonContent() {
		return jsonContent;
	}

	/**
	 * @return the directionNodes
	 */
	public DirectionNode[] getDirectionNodes() {
		return directionNodes;
	}

	/**
	 * @return the ulLatLng
	 */
	public double[] getUlLatLng() {
		return ulLatLng;
	}

	/**
	 * @return the lrLatLng
	 */
	public double[] getLrLatLng() {
		return lrLatLng;
	}

	/**
	 * @return the fuelUsed
	 */
	public double getFuelUsed() {
		return fuelUsed;
	}

	/**
	 * @return the total time to complete this trip
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * @return the totalDistance
	 */
	public double getTotalDistance() {
		return totalDistance;
	}

	/**
	 * @return the maneuverPoints
	 */
	public int[] getManeuverPoints() {
		return maneuverPoints;
	}

	/**
	 * @return the drawLatPoints
	 */
	public double[] getDrawLatPoints() {
		return drawLatPoints;
	}

	/**
	 * @return the drawLngPoints
	 */
	public double[] getDrawLngPoints() {
		return drawLngPoints;
	}
	
	/**
	 * Returns the number of drawing points
	 * @return The number of drawing points
	 */
	public int getPointCount(){
		return drawLatPoints.length;
	}
	
	/**
     * @return the current node position
     */
    public int getCurrentNode() { return currentNode;}

    /**
     * Sets the current node
     * @param n The node number to set
     */
    public void setCurrentNode(int n) { currentNode = n;}
	
    /**
     * Method to easily increment the current direction node by one
     */
    public void incrementNode() { currentNode++; }
    
}
