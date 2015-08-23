package test;

import weatherunderground.WUAlerts;
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

        WUAlerts alerts = weatherObject.createAlertsObject();
        try {
            alerts.setParameters("CT/Bristol").downloadData();
            System.out.println(alerts.getRawData());
            System.out.println(alerts.getNumAlerts());
        } catch (Exception e) {
            e.getMessage();
        }


    }

}
