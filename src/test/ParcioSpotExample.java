package test;

import java.io.IOException;

import parcio.SpotGetter;
import parcio.SpotPoster;
import resources.Constructors;

/**
 * Class to test the operations and prove as an example for the Parcio Spot classes
 * @author Aaron Vontell
 * @version 0.1
 */
public class ParcioSpotExample {

	public static void main(String[] args) {
		
		//Create a SpotPoster object with your personal key
		SpotPoster spotPost = new SpotPoster("YOUR_APP_KEY_HERE");
								
		//Set the data for the spot to be posted
		spotPost.setData("https://parcio.herokuapp.com/users/1/", "223 N William St, Bristol, CT", "34.65137", "23.56789",
								 1422662400, 10, 1, 0, 0, 0, true);
				
		//Post the data to the server
		try {
			spotPost.postData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//Print the response code
			System.out.println(spotPost.getResponseCode());
		}
		
		
		
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
