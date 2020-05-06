package com.gobots.playlistgen.controller;

import java.util.Optional;

import com.gobots.playlistgen.model.Weather;
import com.gobots.playlistgen.service.OpenWeatherMapService;

import com.gobots.playlistgen.util.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to send requests to the OpenWeatherMap API endpoints.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
@RestController
@CrossOrigin(origins = Constants.ANGULAR_URI)
public class PlaylistController {

    /**
     * OpenWeatherMapService Service object.
     */
    private final OpenWeatherMapService service;

    public PlaylistController() {
        this.service = new OpenWeatherMapService();
    }

    /**
     * Requests the weather from OpenWeatherMap API.
     * 
     * @param cityName      The city name to be searched for.
     * 
     * @param longitude     The longitude of the city to be searched for.
     * @param latitude      The latitude of the city to be searched for.
     * 
     * @return              An Optional with the Weather object, or empty if
     *                      an error occurs.
     */
    @RequestMapping(value = "/weather", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getWeather(@RequestParam(required = false, name = "city") Optional<String> cityName,
            @RequestParam(required = false, name = "lon") Optional<Double> longitude,
            @RequestParam(required = false, name = "lat") Optional<Double> latitude) {

        try {

            Optional<Weather> weather = Optional.empty();

            if (cityName.isPresent() && !cityName.get().isEmpty()) {
                weather = service.getWeather(cityName.get());
            }

            if (longitude.isPresent() && latitude.isPresent()) {
                weather = service.getWeather(longitude.get(), latitude.get());
            }

            if (weather.isPresent() && !weather.isEmpty()) {
                final String genre = service.getGenre(weather.get().getMain().getTemp());
                weather.get().getMain().setGenre(genre);

                return ResponseEntity.status(HttpStatus.OK).body(weather.get().toString());
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("There is no such location or city.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}