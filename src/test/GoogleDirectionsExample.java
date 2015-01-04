package test;

import google.GoogleDirections;

/**
 * Class to test the operations and prove as an example for the GoogleDirections class
 * @author Aaron Vontell
 * @version 0.1
 */
public class GoogleDirectionsExample {

	public static void main(String[] args) {
		
		//TODO: Make it so it saves the JSON, and then you can refresh and perform operations
		
		//Create a GoogleDirection object with your personal key
		GoogleDirections navObject = new GoogleDirections("YOUR_API_KEY_HERE");
		
		//Use the TimeToLocation method to retrieve the driving time from one location to another
		Long time = navObject.getTimeToLocation("229 Vassar Street, Cambridge", "375 West Washington Street, Bristol, CT");
		
		System.out.println("It will take " + time + " seconds, or " + time/60 + " minutes, to reach your destination!");

	}

}
