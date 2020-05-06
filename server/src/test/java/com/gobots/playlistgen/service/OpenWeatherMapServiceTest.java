package com.gobots.playlistgen.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.gobots.playlistgen.service.OpenWeatherMapService;

public class OpenWeatherMapServiceTest {

    /**
     * @implNote This test requires the methods to be public.
     */
    @Test
    public void getWeatherUrlTest() {
        final OpenWeatherMapService service = new OpenWeatherMapService();

        final String city = "http://api.openweathermap.org/data/2.5/weather?appid=" + service.getApiKey()
                + "&units=metric&q=SãoCarlos";

        final String location = "http://api.openweathermap.org/data/2.5/weather?appid=" + service.getApiKey()
                + "&units=metric&lon=0.0&lat=0.0";

        Assertions.assertEquals(service.getWeatherUrl("SãoCarlos"), city);
        Assertions.assertEquals(service.getWeatherUrl(0.0, 0.0), location);
    }

    @Test
    public void getGenreTest() {
        final OpenWeatherMapService service = new OpenWeatherMapService();

        Assertions.assertEquals(service.getGenre(0.0), "classical");
        Assertions.assertEquals(service.getGenre(10.0), "classical");

        Assertions.assertEquals(service.getGenre(10.1), "rock");
        Assertions.assertEquals(service.getGenre(14.9), "rock");

        Assertions.assertEquals(service.getGenre(15.0), "pop");
        Assertions.assertEquals(service.getGenre(29.9), "pop");

        Assertions.assertEquals(service.getGenre(30.0), "party");
        Assertions.assertEquals(service.getGenre(60.0), "party");
    }
}