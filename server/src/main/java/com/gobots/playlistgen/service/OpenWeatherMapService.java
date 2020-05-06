package com.gobots.playlistgen.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import com.gobots.playlistgen.model.WeatherType;
import com.gobots.playlistgen.model.Coordinates;
import com.gobots.playlistgen.model.Weather;
import com.gobots.playlistgen.util.Constants;

/**
 * Service implementation of the WeatherService interface, created to implement
 * business rules concerning the OpenWeatherMap API and their data.
 * 
 * {@link http://api.openweathermap.org/}
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
@Service
public class OpenWeatherMapService implements WeatherService {

    /**
     * OpenWeatherMap API key to perform requests.
     */
    private String apiKey;

    /**
     * Template to perform requests.
     */
    private RestTemplate restTemplate;

    /**
     * Service to retrieve information about City.
     */
    private CityService cityService;

    /**
     * Initializes the class properties.
     */
    public OpenWeatherMapService() {
        setRestTemplate(new RestTemplate());
        setCityService(new CityService());
        setApiKey(loadApiKey());
    }

    /**
     * Parametrizes the URL to retrieve weather information
     * based on the city name.
     * 
     * @param city  The name of the city to be searched for.
     * 
     * @return      A string with the URL.
     */
    public String getWeatherUrl(final String city) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/").scheme(Constants.OWM_PROTOCOL)
                .host(Constants.OWM_BASE_URL).path(Constants.OWM_WEATHER_RESOURCE)
                .queryParam(Constants.OWM_APPID_PARAM, this.apiKey)
                .queryParam(Constants.OWM_UNITS_PARAM, Constants.OWM_CELSIUS_VALUE)
                .queryParam(Constants.OWM_CITY_PARAM, city);

        return builder.build().toString();
    }

    /**
     * Parametrizes the URL to retrieve weather information
     * based on the latitude and longitude of the city.
     * 
     * @param lon   The longitude of the city.
     * @param lat   The latitude of the city.
     * 
     * @return      A string with the URL.
     */
    public String getWeatherUrl(final Double lon, final Double lat) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/").scheme(Constants.OWM_PROTOCOL)
                .host(Constants.OWM_BASE_URL).path(Constants.OWM_WEATHER_RESOURCE)
                .queryParam(Constants.OWM_APPID_PARAM, this.apiKey)
                .queryParam(Constants.OWM_UNITS_PARAM, Constants.OWM_CELSIUS_VALUE)
                .queryParam(Constants.OWM_LONGITUDE_PARAM, lon)
                .queryParam(Constants.OWM_LATITUDE_PARAM, lat);

        return builder.build().toString();
    }

    /**
     * Loads the OpenWeatherMap API key from the file.
     * 
     * @return A string with the apiKey or null if an exception occurs.
     */
    private String loadApiKey() {
        final Path path = Path.of(Constants.OWM_KEY_FILE);
        try {
            final String appId = Files.readString(path, StandardCharsets.US_ASCII);
            return appId;
        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves the music genre according to the temperature.
     * 
     * @param temperature   The temperature of the location.
     * 
     * @return              A string with the music genre.
     */
    public String getGenre(final Double temperature) {
        return WeatherType.getGenreForSuchWeather(temperature);
    }

    /**
     * Retrieves the weather based on the city.
     * 
     * @param city          The name of the city to be searched for.
     * 
     * @return              A Weather object that represents the weather 
     *                      for the given city.
     * 
     * @throws Exception
     */
    @Override
    public Optional<Weather> getWeather(final String city) throws Exception {
        Optional<Weather> weather = Optional.empty();
        try {
            final Boolean cityExists = this.cityService.exists(city);

            // retrieve weather only if city exists.
            if (cityExists) {
                weather = Optional.of(requestWeather(getWeatherUrl(city)));
            }
            
            return weather;
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Retrieves the weather based on the latitude and longitude of the city.
     * 
     * @param longitude     The longitude of the city to be searched for.
     * @param latitude      The latitude of the city to be searched for.
     * 
     * @return              A Weather object that represents the weather 
     *                      for the given location.
     * 
     * @throws Exception
     */
    @Override
    public Optional<Weather> getWeather(final Double longitude, final Double latitude) throws Exception {
        Optional<Weather> weather = Optional.empty();

        try {
            final Coordinates coord = new Coordinates(longitude, latitude);
            final Boolean cityExists = this.cityService.exists(coord);
            
            // retrieve weather only if city exists.
            if (cityExists) {
                weather = Optional.of(requestWeather(getWeatherUrl(longitude, latitude)));
            } 
            
            return weather;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Requests weather according to the given URL.
     * 
     * @param url           The URL for the request.
     * 
     * @return              The Weather object or null if an error occurs.
     * @throws Exception
     */
    final Weather requestWeather(final String url) throws Exception {
        try {
            final ResponseEntity<Weather> response = restTemplate.getForEntity(url, Weather.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                final Weather weather = response.getBody();
                System.out.println("HTTP GET - " + url + " - " + response.toString());
                return weather; 
            }

            System.err.println("HTTP GET - " + url + " - " + response.getStatusCode().getReasonPhrase());
            return null;
        } catch (final Exception e) {
            System.err.println("HTTP GET - " + url);
            throw e;
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
}