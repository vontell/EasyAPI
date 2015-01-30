package test;

import java.io.IOException;

import parcio.SpotPoster;

/**
 * Class to test the operations and prove as an example for the SpotPoster Parcio class
 * @author Aaron Vontell
 * @version 0.1
 */
public class SpotPosterExample {

	public static void main(String[] args) {
		
		//Create a SpotPoster object with your personal key
		SpotPoster spotPost = new SpotPoster("YOUR_APP_KEY_HERE");
						
		//Set the data for the spot to be posted
		spotPost.setData("aaronvontell", "375 W William St, Bristol, CT", "34.65137", "23.56789",
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

	}

}
