package test;

import mapquest.DirectionNode;
import mapquest.OpenDirection;

/**
 * Class to test the operations and prove as an example for the OpenDirection class
 * @author Aaron Vontell
 * @version 0.1
 */
public class OpenDirectionExample {
	
	public static void main(String[] args) {
		
		//Create a OpenDirection object with your personal key
		OpenDirection guideObject = new OpenDirection("YOUR_APP_KEY_HERE");
		
		//Set the trip of this OpenDirection object
		guideObject.setTrip("375 West Washington St, Bristol, CT", "229 Vassar Street, Cambridge, MA");
		
		//Show the bounding box
		System.out.println("Bounding Box:");
		System.out.println("Upper Left Lat: " + guideObject.getUlLatLng()[0]);
		System.out.println("Upper Left Lon: " + guideObject.getUlLatLng()[1]);
		System.out.println("Lower Right Lat: " + guideObject.getLrLatLng()[0]);
		System.out.println("Lower Right Lon: " + guideObject.getLrLatLng()[1]);
		System.out.println();
		
		//Show the fuel
		System.out.println("Fuel used: " + guideObject.getFuelUsed());
		
		//Show the time to make this trip
		System.out.println("Trip time: " + guideObject.getTotalTime());
		
		//Show the total distance of this trip
		System.out.println("Trip distance: " + guideObject.getTotalDistance());
		
		//Show the maneuver points for this trip
		System.out.println("");
		System.out.println("Maneuver Points:");
		for(int i : guideObject.getManeuverPoints()){
			System.out.print(" " + i);
		}
		System.out.println("");
		System.out.println("");
		
		//Show the maneuver nodes
		System.out.println("Directions:");
		for(DirectionNode node : guideObject.getDirectionNodes()){
			System.out.println(node.getIndex() + ") For " + node.getTime() +
							   " seconds, or " + node.getDistance() + " miles, " +
							   node.getHumanDirection() + " which is maneuver type " + 
							   node.getManueverType());
		}
		System.out.println();
		
		//Show the drawing points
		System.out.println("Drawing Points:");
		for(int i = 0; i < guideObject.getDrawLatPoints().length; i++){
			System.out.println(guideObject.getDrawLatPoints()[i] + "\t" + guideObject.getDrawLngPoints()[i]);
		}
		
	}

}
