import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Class that provides static variables for conducting
 * HTTP calls and creating its required components
 * @author Aaron Vontell
 * @version 0.2
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

			Log.d("SERVER:" , "" + conn.getResponseCode());

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
			Log.e("POST", e.getMessage());
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

			Log.d("SERVER:" , "" + conn.getResponseCode());

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
			Log.e("POST", e.getMessage());
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

			Log.e("SERVER", "" + conn.getResponseCode());
			Log.e("BODY", conn.getResponseMessage());
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
			Log.e("POST", e.getMessage());
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

			Log.d("SERVER", "" + conn.getResponseCode());
			Log.d("BODY", conn.getResponseMessage());
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
			Log.e("PUT", e.getMessage());
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

			// Read the response
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			Log.d("GET RESP", "" + conn.getResponseCode());

			if(conn.getResponseCode() == 204) {
				return new JSONObject().put("success", "true");
			}

			// Return the result
			return new JSONObject(response.toString());
		}
		catch (Exception e) {
			Log.e("GET", e.toString());
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

			Log.d("GET RESP", "" + conn.getResponseCode());

			if(conn.getResponseCode() == 204) {
				return new JSONObject().put("success", "true");
			}

			// Return the result
			return new JSONObject(response.toString());
		}
		catch (Exception e) {
			Log.e("GET", e.toString());
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

			Log.d("GET RESP", "" + conn.getResponseCode());

			return response.toString();
		}
		catch (Exception e) {
			Log.e("GET", e.toString());
			return "";
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

	}

}
