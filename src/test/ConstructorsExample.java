package test;

import resources.Constructors;

/**
 * Class to test the operations and prove as an example for the Constructors class
 * @author Aaron Vontell
 * @version 0.1
 */
public class ConstructorsExample {

	public static void main(String[] args) {
		
		//The base URL
		String BASE_URL = "http://testurl.com/api?";
		
		//Create an array of keys and values
		String[] params = new String[6];
		params[0] = "key";
		params[1] = "TEST_KEY";
		params[2] = "from";
		params[3] = "origin";
		params[4] = "to";
		params[5] = "destination";
		
		//Construct the URL using the 'constructUrl' method
		String finalURL = Constructors.constructUrl(BASE_URL, params);
		System.out.println("Constructed URL: " + finalURL);
		
		//You can also modify or add parameters to an existing URL
		String[] params2 = new String[4];
		params2[0] = "key";
		params2[1] = "TEST_KEY_NUM2";
		params2[2] = "another";
		params2[3] = "value";
		
		//Modify the URL using the 'modifyUrlParam' method
		String newURL = Constructors.modifyUrlParam(finalURL, params2);
		System.out.println("New URL: " + newURL);

	}

}
