EasyAPI
=======

A Java-based library that parses RESTful APIs into useful objects.

---
###Recent Changes
> Coming Soon!

---
### Gradle Dependency

Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```Groovy
dependencies {
    compile 'Coming Soon!'
}
```

---
### Examples

#### MapQuest Direction API
The MapQuest Direction implementation of EasyAPI allows easy creation of an object that can gather trip information from set trip points and options.

1) First, simply create an OpenDirection object from your MapQuest AppKey
```Java
//Create a OpenDirection object with your personal key
OpenDirection guideObject = new OpenDirection("YOUR_APP_KEY_HERE");
```
2) We can then set a multitude of options before making the request
```Java
Coming Soon!
```
3) Next, set the trip in two ways (note that this is when the call to the server is made):
```Java
//Set the trip using human text
guideObject.setTrip("Origin Street Address, City, State", "Destination Street Address, City, State");

//Set the trip using origin and destination latitude and longitude
guideObject.setTrip(double oriLat, double oriLon, double destLat, double destLon);
```
4) Once the trip is downloaded from step 3, you can now get a multitude of information:
```Java
//Print the bounding box
System.out.println("Upper Left Lat: " + guideObject.getUlLatLng()[0]);
System.out.println("Upper Left Lon: " + guideObject.getUlLatLng()[1]);
System.out.println("Lower Right Lat: " + guideObject.getLrLatLng()[0]);
System.out.println("Lower Right Lon: " + guideObject.getLrLatLng()[1]);

//Show the fuel used to complete the trip (average, gallons)
System.out.println("Fuel used: " + guideObject.getFuelUsed());

//Show the time to make this trip (seconds)
System.out.println("Trip time: " + guideObject.getTotalTime());

//Show the total distance of this trip (in miles)
System.out.println("Trip distance: " + guideObject.getTotalDistance());

//Print the drawing points (the lat/lng points to draw on a map)
for(int i = 0; i < guideObject.getDrawLatPoints().length; i++){
	System.out.println(guideObject.getDrawLatPoints()[i] + "\t" + guideObject.getDrawLngPoints()[i]);
}

//Print the information from each DirectionNode (the objects that represent maneuver points)
for(DirectionNode node : guideObject.getDirectionNodes()){
	System.out.println(node.getIndex() + ") For " + node.getTime() +
    " seconds, or " + node.getDistance() + " miles, " +
    node.getHumanDirection() + " which is maneuver type " + 
    node.getManueverType());
}

//Show the maneuver points for this trip (these are the drawing point indeces that involve a direction change)
for(int i : guideObject.getManeuverPoints()){
	System.out.print(" " + i);
}
```
