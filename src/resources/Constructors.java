package resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
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
	 * @param pairs Array with pairs of parameters, where the even indexes hold param names, and the odd indexes hold their values
	 * @return The string of the constructed URL
	 */
	public static String constructUrl(String base, String[] pairs){
		
		String URL = base;
		
		for(int i = 0; i < pairs.length; i += 2){
			
			if(pairs[i] != null){
				if(i != 0){
					String pair = "&" + pairs[i] + "=" + pairs[i+1];
					URL += pair;
				}
				else {
					String pair = pairs[i] + "=" + pairs[i+1];
					URL += pair;
				}
				
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
			
			return new JSONObject(content);
			
		} catch(Exception e){
			System.out.println(content);
			System.out.println("Error occured: " + e);
			return new JSONObject();
		}
		
	}
	
	/**
	 * Makes an HTTP GET call to download the JSON from the given URL
	 * @param URL The URL to connect to
	 * @return A JSONArray containing the HTTP GET results
	 */
	public static JSONArray constructJSONArray(String URL){
		
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
			
			return new JSONArray(content);
			
		} catch(Exception e){
			System.out.println(content);
			System.out.println("Error occured: " + e);
			return new JSONArray();
		}
		
	}
	
	/**
	 * Adds or changes extra parameter pairs to a URL that has already been created
	 * If the parameter already exists in the URL, the parameter is changed
	 * If the parameter does not exist, it is added onto the URL
	 * @param url The URL to add parameters to
	 * @param pairs Array with pairs of parameters, where the even indexes hold param names, and the odd indexes hold their values
	 * @return The string of the constructed URL
	 */
	public static String modifyUrlParam(String url, String[] pairs){
		
		String URL = url;
		
		for(int i = 0; i < pairs.length; i += 2){
			
			if(pairs[i] != null){
				
				if(URL.contains(pairs[i] + "=")){
					int start = URL.indexOf(pairs[i] + "=");
					int end = URL.indexOf("&", start);
					String toReplace = URL.substring(start, end);
					URL = URL.replace(toReplace, pairs[i] + "=" + pairs[i+1]);
				}
				else{
					//TODO: Make sure this is not the first param being added (don't want the '&')
					String pair = "&" + pairs[i] + "=" + pairs[i+1];
					URL += pair;
				}

			}
			
		}
		
		URL = URL.replace(" ","%20");
		return URL;
		
	}
	
}
