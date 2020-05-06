# Fullstack Test

## Context

Gobots wants to create a new service to suggest music to its users. The suggestions will depend on the city and the climate where the user is.

## Task

Create a micro-service able to accept RESTful requests receiving as parameter a location and returns a playlist suggestion according to the current temperature. Use any language and framework you want (Kotlin/Java + Spring, Python + Django/Flask etc), however Kotlin + Spring is recommended.

Also create a simple frontend which sends the location to the microservice and shows the music suggestions. Use any language and framework you want (JQuery, ReactJS, Angular etc).

### Observations
 - Location can be represented as city name or lat long coordinates.
 - A playlist can be represented as a list of music names.

## Business rules
* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is between 15 and 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks

## Hints
You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Submission
Fork this project and share it with your interviwer

# Solution - "PlaylistGen"

### Programming Language and Technologies

* Java 14
* Spring
* Maven
* JUnit
* Angular 9

### How to run

First of all, it is necessary to extract the "keys.tar" file into the "server/src/main/resources/keys" folder. The following packages are necessary:

* node.js
* npm
* angular-cli
* maven

For tests, enter the server folder and enter the command:

```
mvn test
```

Still in the server folder, to run the server application:

```
mvn spring-boot:run
```

For the Angular application, open a new terminal, navigate to the "frontend" folder and enter the commands:

```
npm install
```

```
ng serve
```

### API

The service is listening on port 8080 by default.

The endpoints to retrieve weather information are:

* /weather?city=(city name)
* /weather?lon=(longitude)&lat=(latitude)

The endpoints to retrieve playlist information are:

* /credentials
* /playlist?genre=(genre)

### Front-end

The home page offers a drop-down menu to select the search method (either City or Geographic Coordinates). The input data is validated both in the client and server sides. After a successful search, the application navigates to "/recommendations" and presents a table with tracks, as well as some information about the selected location.


### Future work

* Find a way to encrypt the keys;
* Find out which logging framework would be the best;
* Find a way to implement a thread-like way for several requests at the same time;
* Implement unit and integration tests for the service and controller classes.