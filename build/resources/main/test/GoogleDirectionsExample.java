package test;

import google.GoogleDirections;

/**
 * Class to test the operations and prove as an example for the GoogleDirections class
 * @author Aaron Vontell
 * @version 0.2
 */
public class GoogleDirectionsExample {

	public static void main(String[] args) {

		//Create a GoogleDirections object with your personal key
		GoogleDirections navObject = new GoogleDirections("AIzaSyBFbX7cknRMJnGtnHU7GOzZ8GIkAc1xwXU");

		//Set the trip of this OpenDirection object
		navObject.setTrip("229 Vassar Street, Cambridge", "375 West Washington Street, Bristol, CT");

		//Download the data, preferably asynchronously
		navObject.downloadData();

		//Retrieve information such as the time to make this trip
		System.out.println("Trip time: " + navObject.getTotalTime() + " seconds");

	}

}
