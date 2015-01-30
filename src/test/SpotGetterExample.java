package test;

import java.io.IOException;

import parcio.SpotGetter;
import resources.Constructors;

/**
 * Class to test the operations and prove as an example for the SpotGetter Parcio class
 * @author Aaron Vontell
 * @version 0.1
 */
public class SpotGetterExample {

	public static void main(String[] args) {
		
		//Create a SpotGetter object with your personal key
		SpotGetter spotRet = new SpotGetter("YOUR_APP_KEY_HERE");
				
		//Download the data, preferably asynchronously
		spotRet.downloadData();
		
		//Iterate through a list of the spots, printing information
		int totalSpots = spotRet.getNumSpots();
		for(int index = 0; index < totalSpots; index++){
			System.out.println("----------------------------------------------");
			System.out.println("Location:\t" + spotRet.getAddress(index) + " (" + spotRet.getLat(index) + "," + spotRet.getLon(index) + ")");
			System.out.println("Held by:\t" + spotRet.getUser(index));
			System.out.println("Created:\t" + spotRet.getTimeCreated(index));
			System.out.println("Taken?:\t\t" + spotRet.getIsFilled(index));
			System.out.println("Points:\t\t" + spotRet.getPoints(index));
			System.out.println("Timeout:\t" + spotRet.getTimeout(index));
			System.out.println("Spot Type:\t" + spotRet.getSpotType(index));
			System.out.println("Start Times:\t" + spotRet.getMetTime(index) + ", " + spotRet.getPerTime(index) + ", " + spotRet.getFreTime(index));
		}

	}

}
