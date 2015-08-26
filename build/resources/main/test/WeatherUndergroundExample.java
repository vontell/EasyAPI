package test;

import weatherunderground.*;

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

        WUAstronomy astronomy = weatherObject.createAstronomyObject();
        try {
            astronomy.setParameters("CT/Bristol").downloadData();
            System.out.println("Percent Illuminated: " + astronomy.getPercentIlluminated());
            System.out.println("Moon Cycle Progress: " + astronomy.getAgeOfMoon());
            System.out.println("Request Time: " + astronomy.getRequestTimeHour() + ":" + astronomy.getRequestTimeMinute());
            System.out.println("Sunrise Time: " + astronomy.getSunriseHour() + ":" + astronomy.getSunriseMinute());
            System.out.println("Sunset Time: " + astronomy.getSunsetHour() + ":" + astronomy.getSunsetMinute());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
