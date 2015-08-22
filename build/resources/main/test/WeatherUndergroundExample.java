package test;

import org.json.JSONObject;
import resources.exceptions.*;
import weatherunderground.WUConditions;
import weatherunderground.WeatherUnderground;

/**
 * Class to test the operations and prove as an example for the WeatherUnderground API
 * @author Aaron Vontell
 * @version 0.2
 */
public class WeatherUndergroundExample {

    public static void main(String[] args){

        // Set your API key and create an API Object
        final String API_KEY = "API_KEY";
        WeatherUnderground weatherObject = new WeatherUnderground(API_KEY);

        //Create a conditions object, to retrieve current conditions
        WUConditions conditions = weatherObject.createConditionsObject();
        //Set the location for the current conditions lookup
        conditions.setParameters("");

        //Download the data (asynchronous preferred), making sure to handle exceptions that may occur
        try {
            conditions.downloadData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Observe data on the current conditions
        String desc = String.format(
                "Today's conditions are %s, with a temperature of %s F, but feels like %s. There is a visibility of " +
                "%s miles, at your observation location of %s.",
                conditions.getWeather(), conditions.getTemperatureF(), conditions.getFeelsLike(),
                conditions.getVisibilityMI(), conditions.getObsDescription());
        System.out.println(desc);

    }

}
