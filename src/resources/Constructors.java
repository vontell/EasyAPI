package resources;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that provides static variables for conducting
 * HTTP calls and creating its required components
 * @author Aaron Vontell
 * @version 0.3
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
	public static JSONObject constructJSON(String URL, String authorization){
		
		String content = "";
		URL.replace(" ", "%20");

		try{
			
			URL Url = new URL(URL);
			URLConnection conn = Url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}
			
			String line;
			while ((line = bufferedReader.readLine()) != null){
				content += line + "\n";
			}
			bufferedReader.close();
			
			return new JSONObject(content);
			
		} catch(Exception e){
			System.out.println(content);
			System.out.println("Error occurred: " + e);
			return new JSONObject();
		}
		
	}
	
	/**
	 * Makes an HTTP GET call to download the JSON from the given URL
	 * @param URL The URL to connect to
	 * @return A JSONArray containing the HTTP GET results
	 */
	public static JSONArray constructJSONArray(String URL, String authorization){
		
		String content = "";

		try{
			
			URL Url = new URL(URL);
			URLConnection conn = Url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}
			
			String line;
			while ((line = bufferedReader.readLine()) != null){
				content += line + "\n";
			}
			bufferedReader.close();
			
			return new JSONArray(content);
			
		} catch(Exception e){
			System.out.println(content);
			System.out.println("Error occurred: " + e);
			return new JSONArray();
		}
		
	}

	public static JSONObject postData(String url, String data, String authorization){

		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost request = new HttpPost(url);
			StringEntity params = new StringEntity(data);
			params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			request.setEntity(params);
			request.setHeader("Content-Type", "application/json");
			if(authorization != null){
				request.setHeader("Authorization", "Bearer " + authorization);
			}
			//for(Header h : request.getAllHeaders()){System.out.println(h);}
			//System.out.println(data);
			HttpResponse response = httpClient.execute(request);
			//for(Header h : response.getAllHeaders()){System.out.println(h);}
			//System.out.println(response.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			String json = reader.readLine();
			return new JSONObject(json);
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return new JSONObject("{error: There was an error logging in!}");
		} finally {
			httpClient.getConnectionManager().shutdown();
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

	/**
	 * Creates and executes a HTTP POST request using the
	 * application/x-www-form-urlencoded content type
	 * @param url The url to the API endpoint you are hitting
	 * @param data The data to send as a mapping of key - value pairs
	 * @return The response from the server as a JSON Object
	 */
	public static JSONObject postData(final String url, final HashMap<String, String> data, String authorization){

		HttpURLConnection conn = null;

		try {

			// Construct the POST
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");

			String postData = "";
			for(String key : data.keySet()){
				postData += key + "=" + data.get(key) + "&";
			}
			postData = postData.substring(0, postData.length() - 1);

			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
			conn.setRequestProperty("charset","utf-8");

			if(authorization != null){
				conn.setRequestProperty("Authorization", authorization);
			}

			// Begin writing to the server
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			//Log.e("SERVER:" , "" + conn.getResponseCode());

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Return the result
			return new JSONObject(response.toString());
		}
		catch (Exception e) {
			//Log.e("******", "" + e.getMessage());
			return new JSONObject();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}

	/**
	 * Creates and executes a HTTP POST request using the
	 * application/json content type
	 * @param url The url to the API endpoint you are hitting
	 * @param data The data to send as a JSON Object
	 * @return The response from the server as a JSON Object
	 */
	public static JSONObject postData(final String url, final JSONObject data, String authorization){

		HttpURLConnection conn = null;

		try {

			// Construct the POST
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setRequestMethod("POST");

			String postData = data.toString();

			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}

			// Begin writing to the server
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Return the result
			return new JSONObject(response.toString());
		}
		catch (Exception e) {
			return new JSONObject();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}

	/**
	 * Creates and executes an HTTP GET request using
	 * the HttpURLConnection class.
	 * @param url The url to the API endpoint you are hitting
	 * @param authorization The response from the server as a JSON Object
	 * @return
	 */
	public static JSONObject getData(final String url, String authorization){

		HttpURLConnection conn = null;

		// Construct the GET
		try {

			// Construct the GET
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setRequestMethod("GET");

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Return the result
			return new JSONObject(response.toString());
		}
		catch (Exception e) {
			//Log.e("******", e.getMessage());
			return new JSONObject();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}

	/**
	 * Creates and executes an HTTP GET request using
	 * the HttpURLConnection class.
	 * @param url The url to the API endpoint you are hitting
	 * @param authorization The response from the server as a JSON Object
	 * @return
	 */
	public static JSONArray getDataAsArray(final String url, String authorization){

		HttpURLConnection conn = null;

		// Construct the GET
		try {

			// Construct the GET
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setDoInput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				//Log.e("SERVED", inputLine);
				response.append(inputLine);
			}
			in.close();

			//Log.e("CONSTR:", response.toString());

			// Return the result
			return new JSONArray(response.toString());
		}
		catch (Exception e) {

			return new JSONArray();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}

	public static JSONObject deleteData(final String url, final HashMap<String, String> data, String authorization){

		HttpURLConnection conn = null;

		try {

			// Construct the POST
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setDoInput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("DELETE");

			String postData = "";
			for(String key : data.keySet()){
				postData += key + "=" + data.get(key) + "&";
			}
			postData = postData.substring(0, postData.length() - 1);

			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
			conn.setRequestProperty("charset","utf-8");

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}

			// Begin writing to the server
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			//Log.e("SERVER:" , "" + conn.getResponseCode());

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Return the result
			return new JSONObject(response.toString());
		}
		catch (Exception e) {
			return new JSONObject();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}
	
}
