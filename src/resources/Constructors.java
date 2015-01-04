package resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;


/**
 * Class that provides static variables for conducting
 * HTTP calls and creating its required components
 * @author Aaron Vontell
 * @version 0.1
 */
public class Constructors {
	
	/**
	 * Creates a URL for the current API
	 * @param base The base API URL
	 * @param pairs Array with pairs of parameters, where the even indexes hold param names, and the odd indexes hold their powers
	 * @return The string of the constructed URL
	 */
	public static String constructUrl(String base, String [] pairs){
		
		String URL = base;
		
		for(int i = 0; i < pairs.length; i += 2){
			
			if(pairs[i] != null){
				String pair = "&" + pairs[i] + "=" + pairs[i+1];
				URL += pair;
			}
			
		}
		
		URL = URL.replace(" ","%20");
		return URL;
		
	}
	
	/**
	 * Makes an HTTP GET call to download the JSON from the given URL
	 * @param URL The URL to connect to
	 * @return A JSONObject containing the HTTP GET results
	 */
	public static JSONObject constructJSON(String URL){
		
		String content = "";

		try{
			
			URL Url = new URL(URL);
			URLConnection conn = Url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			while ((line = bufferedReader.readLine()) != null){
				content += line + "\n";
			}
			bufferedReader.close();
			
		} catch(Exception e){
			System.out.println("Error occured: " + e);
		}
		
		return new JSONObject(content);
		
	}
	
}
