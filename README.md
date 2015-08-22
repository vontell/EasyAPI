EasyAPI [![Build Status](https://travis-ci.org/vontell/EasyAPI.svg?branch=master)](https://travis-ci.org/vontell/EasyAPI)
=======

A Java-based library that parses RESTful APIs into useful objects.
EasyAPI hopes to create easy RESTful API interaction by create a simple object from your API key, and then allowing full API interaction throught that object.

###Currently Supported APIs:
- MapQuest Directions V0.2 (Partial)
- Google Directions (Partial)
- Parcio Parking API (Personal Company Use, Partial)

---
###Recent Changes
> Google Direction 0.2 - Downloading, configuring, and parsing data now separate

> Parcio API V0.1 - Started object for using the Parcio Parking API

> Open Direction 0.3 - Downloading API data is now a seperate step

> Constructors Class - Added a method for posting data which is in JSON format

---
###Usage

####Project Import
If you want use this library, download the project, import it into your workspace, and add the project as a library in your android project settings.

####Gradle Dependency
Alternatively, easily reference the library in your Gradle projects using this dependency in your module's `build.gradle` file:

```Groovy
dependencies {
    compile 'Coming Soon!'
}
```

---
###Examples

#### MapQuest Direction API
The MapQuest Direction implementation of EasyAPI allows easy creation of an object that can gather trip information from set trip points and options. (*See original documentation at https://developer.mapquest.com/products/directions/*)

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
5) Once the trip is downloaded from step 4, you can now get a multitude of information:
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
The Google Direction implementation of EasyAPI allows easy creation of an object that can gather trip information from Google. (*See original documentation at https://developers.google.com/maps/documentation/directions/*)

1) First, simply create an GoogleDirections object from your Google API Key
```Java
//Create a GoogleDirections object with your personal key
GoogleDirections navObject = new GoogleDirections("YOUR_API_KEY_HERE");
```
2) We can then set a multitude of options before making the request
```Java
Coming Soon!
```
3) Next, set the trip in two ways:
```Java
//Set the trip using human text
navObject.setTrip("Origin Street Address, City, State", "Destination Street Address, City, State");

//Set the trip using origin and destination latitude and longitude
navObject.setTrip(double oriLat, double oriLon, double destLat, double destLon);
```
4) Once the trip is set and modified, download the data
```Java
//Download the data, preferably asynchronously
navObject.downloadData();
```
5) Once the trip is downloaded from step 4, you can now get a multitude of information:
```Java
//Show the time to make this trip (seconds)
System.out.println("Trip time: " + navObject.getTotalTime());

More Coming Soon!
```

####Parcio Parking API
The Parcio Parking implementation of EasyAPI allows easy retrieval and posting of parking spots and Parcio users, including the use authentication of users.

#####Getting Spots
1) First, simply create a SpotGetter object from your Parcio API Key
```Java
//Create a SpotGetter object with your personal key
SpotGetter spotRet = new SpotGetter("YOUR_APP_KEY_HERE");
```

2) Next we can download the data for all parkng spots in the database
```Java
//Download the data, preferably asynchronously
spotRet.downloadData();
```

3) The object now holds all of the spots, which we can retrieve. The data is held in a variety of arrays, where a single index can be used across all to retrieve the information for a single spot. Use the getNumSpots() method to retrieve the number of spots.
```Java
//Iterate through a list of the spots, printing information
int totalSpots = spotRet.getNumSpots();
for(int index = 0; index < totalSpots; index++){
	System.out.println("----------------------------------------------");
	System.out.println("Location:\t" + spotRet.getAddress(index) + " (" + spotRet.getLat(index) + "," + 	spotRet.getLon(index) + ")");
	System.out.println("Held by:\t" + spotRet.getUser(index));
	System.out.println("Created:\t" + spotRet.getTimeCreated(index));
	System.out.println("Taken?:\t\t" + spotRet.getIsFilled(index));
	System.out.println("Points:\t\t" + spotRet.getPoints(index));
	System.out.println("Timeout:\t" + spotRet.getTimeout(index));
	System.out.println("Spot Type:\t" + spotRet.getSpotType(index));
	System.out.println("Start Times:\t" + spotRet.getMetTime(index) + ", " + spotRet.getPerTime(index) + ", " + spotRet.getFreTime(index));
}
```

4) For a more restrictive selection, you can use the findNearbySpots method, which takes in a latitude, longitude, and radius. It returns an array of the indeces that corresponds to the spots that are within that radius.
```Java
//Find the indeces corresponding to nearby spots
int[] nearbySpots = spotRet.findNearbySpots(45.23455, 21.47975, 0.5);

//Print the indeces
for(int index : nearbySpots){ System.out.println(index); }
```
---
### Constructors Class
The Constructors class is used to create URLs and JSON. Although no interaction is needed with this class to use the implemented APIs, you can use it as support for other RESTful APIs that will be integrated into EasyAPI.

Please see the ConstructorsExample.java file for a full example.

The static method constructUrl allows for creation of a URL from a base URL and an array of keys and parameters, with the even numbers being keys and odd numbers being their respective values.
```Java
String url = Constructors.constructUrl(BASE_URL, params_array);
```
The static method modifyUrlParam allows for the modification of an existing URL. If the parameter already exists, it is replaced. If not, it is added to the URL.
```Java
String newURL = Constructors.modifyUrlParam(EXISTING_URL, more_params_array);
```
The static method constructJSON takes in a url, downloads the JSON content from server, and returns a JSONObject from the org.json library.
```Java
JSONObject jObject = Constructors.constructJSON(url);
```
