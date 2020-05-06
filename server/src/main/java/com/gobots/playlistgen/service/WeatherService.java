package com.gobots.playlistgen.service;

import java.util.Optional;

import com.gobots.playlistgen.model.Weather;

/**
 * Interface for the OpenWeatherMap Service implementation.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
public interface WeatherService {

    public Optional<Weather> getWeather(final String city) throws Exception;
    public Optional<Weather> getWeather(final Double longitude, Double latitude) throws Exception;

}