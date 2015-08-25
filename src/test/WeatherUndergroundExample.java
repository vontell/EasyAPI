package test;

import weatherunderground.WUAlerts;
import weatherunderground.WUAlmanac;
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

        WUAlmanac almanac = weatherObject.createAlmanacObject();
        try {
            almanac.setParameters("CT/Bristol").downloadData();
            System.out.println("Location: " + almanac.getObservationLocation());
            System.out.println("High Record: " + almanac.getRecordHighF() + " F");
            System.out.println("Low Record: " + almanac.getRecordLowF() + " F");
            System.out.println("Record High Year: " + almanac.getRecordHighYear() + " F");
            System.out.println("Record Low Year: " + almanac.getRecordLowYear() + " F");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
