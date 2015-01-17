EasyAPI
=======

A Java-based library that parses RESTful APIs into useful objects.
EasyAPI hopes to create easy RESTful API interaction by create a simple object from your API key, and then allowing full API interaction throught that object.

###Currently Supported APIs:
- MapQuest Directions V0.2 (Partial)
- Google Directions (Partial)

---
###Recent Changes
> Coming Soon!

---
### Gradle Dependency

Easily reference the library in your Gradle projects using this dependency in your module's `build.gradle` file:

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
3) Next, set the trip in two ways:
```Java
//Set the trip using human text
guideObject.setTrip("Origin Street Address, City, State", "Destination Street Address, City, State");

//Set the trip using origin and destination latitude and longitude
guideObject.setTrip(double oriLat, double oriLon, double destLat, double destLon);
```
4) Once the trip is set and modified, download the data
```Java
//Download the data, preferably asynchronously
guideObject.downloadData();
```
5) Once the trip is downloaded from step 3, you can now get a multitude of information:
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
####Google Direction API
The Google Direction implementation of EasyAPI allows easy creation of an object that can gather trip information from Google.

1) First, simply create a GoogleDirections object from your Google API Key
```Java
//Create a GoogleDirections object with your personal key
GoogleDirections navObject = new GoogleDirections("YOUR_API_KEY_HERE");
```

2) We can now do a variety of operations with this object
```Java
//Use the timeToLocation method to retrieve the driving time from one location to another, in seconds
Long time = navObject.getTimeToLocation("Origin Address", "Destination Address");
```
---
### Constructors Class
The Constructors class is used to create URL and JSON. Although no interaction is needed with this class to use the implemented APIs, you can use it as support for other RESTful APIs that will be integrated into EasyAPI.

The static method constructUrl allows for creation of a URL from a base URL and an array of keys and parameters, with the even numbers being keys and odd numbers being their respective values.
```Java
String url = Constructors.constructUrl(BASE_URL, params_array);
```

The static method constructJSON takes in a url, downloads the JSON content from server, and returns a JSONObject from the org.json library.
```Java
JSONObject jObject = Constructors.constructJSON(url);
```
