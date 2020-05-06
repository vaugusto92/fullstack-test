package com.gobots.playlistgen.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gobots.playlistgen.model.City;
import com.gobots.playlistgen.model.Coordinates;
import com.gobots.playlistgen.repository.CityRepository;
import com.gobots.playlistgen.util.Constants;

import org.springframework.stereotype.Service;

/**
 * Service implementation of the CityRepository interface, created to retrieve
 * information about the available cities in the OpenWeatherMap API before
 * sending any request.
 * 
 * {@link http://bulk.openweathermap.org/sample/}
 * 
 * @implNote This JSON-based implementation was created for testin purposes.
 *           Real MongoDB queries should be used for production.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
@Service
public class CityService implements CityRepository {

    /**
     * List of City objects to store all possibie cities.
     */
    private List<City> cities;

    /**
     * No-parameter constructor. Loads the available cities in the OpenWeatherMap
     * API into the List of City objects.
     */
    public CityService() {
        this.setCities(loadCities());
    }

    /**
     * Reads the JSON array from the cities file and retrieves it as a List of City
     * objects.
     * 
     * @return List<City> A List of City objects, or null if an Expection occurs.
     */
    private List<City> loadCities() {
        try {
            // create object mapper instance
            final ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of cities
            final List<City> cities = Arrays
                    .asList(mapper.readValue(Paths.get(Constants.CITY_JSON_PATH).toFile(), City[].class));

            return cities;
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if the city exists in the database by means of its name.
     * 
     * @param name A String with the name of the city.
     * @return Boolean True if exists and false otherwise.
     */
    public Boolean exists(final String name) {
        return findByName(name).isPresent();
    }

    /**
     * Checks if the city exists in the database by means of a Coordinate object,
     * with its lon and lat data.
     * 
     * @param coord A Coordinate object with the lon and lat data.
     * 
     * @return Boolean True if exists and false otherwise.
     */
    public Boolean exists(final Coordinates coord) {
        return findByCoordinates(coord).isPresent();
    }

    /**
     * Retrieves a city by its name;
     * 
     * @param name The name of the city.
     * 
     * @return Optional<City> An Optional with the City object.
     */
    @Override
    public Optional<City> findByName(final String name) {
        return this.getCities().stream().filter(o -> o.getName().equals(name)).findFirst();
    }

    /**
     * Retrieves a city by its latitude and longitude data.
     * 
     * @param coord The Coordinates object of the city.
     * 
     * @return Optional<City> An Optional with the City object.
     */
    @Override
    public Optional<City> findByCoordinates(final Coordinates coord) {
        return this.getCities().stream().filter(o -> 
            o.getCoord().getLon().equals(coord.getLon()) &&
            o.getCoord().getLat().equals(coord.getLat())).findFirst();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(final List<City> cities) {
        this.cities = cities;
    }
}