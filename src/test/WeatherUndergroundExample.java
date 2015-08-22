package test;

import org.json.JSONObject;
import weatherunderground.WUConditions;
import weatherunderground.WeatherUnderground;

/**
 * Class to test the operations and prove as an example for the WeatherUnderground class
 * @author Aaron Vontell
 * @version 0.2
 */
public class WeatherUndergroundExample {

    public static void main(String[] args){

        final String API_KEY = "3435516581c40ab3";
        WeatherUnderground weatherObject = new WeatherUnderground(API_KEY);
        WUConditions conditions = weatherObject.getConditionsObject();
        conditions.setParameters("Bristol_CT");
        conditions.downloadData();
        System.out.print(conditions.getRawData());

    }

}
