package resources;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Class that provides static variables for conducting
 * HTTP calls and creating its required components
 * @author Aaron Vontell
 * @version 0.4
 */
public class Constructors {

	/**
	 * Creates and executes a HTTP POST request using the
	 * application/x-www-form-urlencoded content type
	 * @param url The url to the API endpoint you are hitting
	 * @param data The data to send as a mapping of key - value pairs
	 * @param authorization The bearer token for authenticated access
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

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
	 * Creates and executes a HTTP POST request using the
	 * application/json content type
	 * @param url The url to the API endpoint you are hitting
	 * @param data The data to send as a JSON Object
	 * @param authorization The bearer token for authenticated access
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
	 * Creates and executes a HTTP POST request using the
	 * application/json content type
	 * @param url The url to the API endpoint you are hitting
	 * @param data The data to send as a JSON Object
	 * @param authorization The bearer token for authenticated access
	 * @param xToken the token to put into X-Device-Token
	 * @return The response from the server as a JSON Object
	 */
	public static JSONObject postData(final String url, final JSONObject data,
									  final String authorization, final String xToken){

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
			if(xToken != null) {
				conn.setRequestProperty("X-Device-Token", xToken);
			}

			//Log.e("Whole Thing:", conn.getRequestProperties().toString());

			// Begin writing to the server
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			boolean goodCall = conn.getResponseCode() == 204;

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
			JSONObject jsonResult = new JSONObject(response.toString());
			//Log.e("Good call", "" + goodCall);
			if(goodCall) {
				//Log.d("INSIDE", "um it is 204");
				jsonResult.put("success", true);
			}
			return jsonResult;
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
	 * Creates and executes a HTTP PUT request using the
	 * application/json content type
	 * @param url The url to the API endpoint you are hitting
	 * @param data The data to send as a JSON Object
	 * @param authorization The bearer token for authenticated access
	 * @param xToken the token to put into X-Device-Token
	 * @return The response from the server as a JSON Object
	 */
	public static JSONObject putData(final String url, final JSONObject data,
									 final String authorization, final String xToken){

		HttpURLConnection conn = null;

		try {

			// Construct the POST
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setRequestMethod("PUT");

			String postData = data.toString();

			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));

			if(authorization != null){
				conn.setRequestProperty("Authorization", "Bearer " + authorization);
			}
			if(xToken != null) {
				conn.setRequestProperty("X-Device-Token", xToken);
			}

			//Log.e("Whole Thing:", conn.getRequestProperties().toString());

			// Begin writing to the server
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			boolean goodCall = conn.getResponseCode() == 204;

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
			JSONObject jsonResult = new JSONObject(response.toString());
			//Log.e("Good call", "" + goodCall);
			if(goodCall) {
				//Log.d("INSIDE", "um it is 204");
				jsonResult.put("success", true);
			}
			return jsonResult;
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
	 * @return The response from the server as a JSON Object
	 */
	public static JSONObject getData(final String url, String authorization){

		HttpURLConnection conn = null;

		// Construct the GET
		try {

			// Construct the GET
			URL finalUrl = new URL(url);
			conn = (HttpURLConnection) finalUrl.openConnection();
			conn.setRequestMethod("GET");

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			if(conn.getResponseCode() == 204) {
				return new JSONObject().put("success", "true");
			}

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

	public static JSONObject getData(final String url, String authorization, String xToken){

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
			if(xToken != null) {
				conn.setRequestProperty("X-Device-Token", xToken);
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

			if(conn.getResponseCode() == 204) {
				return new JSONObject().put("success", "true");
			}

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

	public static String getText(final String url, String authorization, String xToken){

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
			if(xToken != null) {
				conn.setRequestProperty("X-Device-Token", xToken);
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

			return response.toString();
		}
		catch (Exception e) {
			return "";
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}

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
