package com.gobots.playlistgen.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.gobots.playlistgen.model.WeatherType;

public class WeatherTypeTest {

    @Test
    public void getHotWeatherTest() {
        Assertions.assertEquals(WeatherType.HOT, WeatherType.getWeather(Double.POSITIVE_INFINITY));
        Assertions.assertEquals(WeatherType.HOT, WeatherType.getWeather(Double.MAX_VALUE));
        Assertions.assertEquals(WeatherType.HOT, WeatherType.getWeather(30.1));
        Assertions.assertEquals(WeatherType.HOT, WeatherType.getWeather(30.0));

        Assertions.assertNotEquals(WeatherType.HOT, WeatherType.getWeather(29.999999));
        Assertions.assertNotEquals(WeatherType.HOT, WeatherType.getWeather(29.0));
        Assertions.assertNotEquals(WeatherType.HOT, WeatherType.getWeather(25.0));
        Assertions.assertNotEquals(WeatherType.HOT, WeatherType.getWeather(10.0));
    }

    @Test
    public void getMildWeatherTest() {
        Assertions.assertEquals(WeatherType.MILD, WeatherType.getWeather(29.9));
        Assertions.assertEquals(WeatherType.MILD, WeatherType.getWeather(25.0));
        Assertions.assertEquals(WeatherType.MILD, WeatherType.getWeather(20.0));
        Assertions.assertEquals(WeatherType.MILD, WeatherType.getWeather(15.0));

        Assertions.assertNotEquals(WeatherType.MILD, WeatherType.getWeather(60.0));
        Assertions.assertNotEquals(WeatherType.MILD, WeatherType.getWeather(2.0));
        Assertions.assertNotEquals(WeatherType.MILD, WeatherType.getWeather(14.9));
        Assertions.assertNotEquals(WeatherType.MILD, WeatherType.getWeather(10.0));
    }

    @Test
    public void getChillyWeatherTest() {
        Assertions.assertEquals(WeatherType.CHILLY, WeatherType.getWeather(14.9));
        Assertions.assertEquals(WeatherType.CHILLY, WeatherType.getWeather(13.0));
        Assertions.assertEquals(WeatherType.CHILLY, WeatherType.getWeather(11.0));
        Assertions.assertEquals(WeatherType.CHILLY, WeatherType.getWeather(10.1));

        Assertions.assertNotEquals(WeatherType.CHILLY, WeatherType.getWeather(10.0));
        Assertions.assertNotEquals(WeatherType.CHILLY, WeatherType.getWeather(20.0));
        Assertions.assertNotEquals(WeatherType.CHILLY, WeatherType.getWeather(-0.25));
        Assertions.assertNotEquals(WeatherType.CHILLY, WeatherType.getWeather(2.0));
    }

    @Test
    public void getFrezingWeatherTest() {
        Assertions.assertEquals(WeatherType.FREEZING, WeatherType.getWeather(10.0));
        Assertions.assertEquals(WeatherType.FREEZING, WeatherType.getWeather(5.0));
        Assertions.assertEquals(WeatherType.FREEZING, WeatherType.getWeather(Double.NEGATIVE_INFINITY));
        Assertions.assertEquals(WeatherType.FREEZING, WeatherType.getWeather(Double.MIN_VALUE));

        Assertions.assertNotEquals(WeatherType.FREEZING, WeatherType.getWeather(10.1));
        Assertions.assertNotEquals(WeatherType.FREEZING, WeatherType.getWeather(20.0));
        Assertions.assertNotEquals(WeatherType.FREEZING, WeatherType.getWeather(30.0));
        Assertions.assertNotEquals(WeatherType.FREEZING, WeatherType.getWeather(15.0));
    }

    @Test
    public void getClassicalTest() {
        Assertions.assertEquals("classical", WeatherType.getGenreForSuchWeather(10.0));
        Assertions.assertEquals("classical", WeatherType.getGenreForSuchWeather(5.0));
        Assertions.assertEquals("classical", WeatherType.getGenreForSuchWeather(Double.NEGATIVE_INFINITY));
        Assertions.assertEquals("classical", WeatherType.getGenreForSuchWeather(Double.MIN_VALUE));

        Assertions.assertNotEquals("classical", WeatherType.getGenreForSuchWeather(10.1));
        Assertions.assertNotEquals("classical", WeatherType.getGenreForSuchWeather(20.0));
        Assertions.assertNotEquals("classical", WeatherType.getGenreForSuchWeather(30.0));
        Assertions.assertNotEquals("classical", WeatherType.getGenreForSuchWeather(15.0));
    }

    @Test
    public void getRockTest() {
        Assertions.assertEquals("rock", WeatherType.getGenreForSuchWeather(14.9));
        Assertions.assertEquals("rock", WeatherType.getGenreForSuchWeather(13.0));
        Assertions.assertEquals("rock", WeatherType.getGenreForSuchWeather(11.0));
        Assertions.assertEquals("rock", WeatherType.getGenreForSuchWeather(10.1));

        Assertions.assertNotEquals("rock", WeatherType.getGenreForSuchWeather(10.0));
        Assertions.assertNotEquals("rock", WeatherType.getGenreForSuchWeather(20.0));
        Assertions.assertNotEquals("rock", WeatherType.getGenreForSuchWeather(-0.25));
        Assertions.assertNotEquals("rock", WeatherType.getGenreForSuchWeather(2.0));
    }

    @Test
    public void getPopTest() {
        Assertions.assertEquals("pop", WeatherType.getGenreForSuchWeather(29.9));
        Assertions.assertEquals("pop", WeatherType.getGenreForSuchWeather(25.0));
        Assertions.assertEquals("pop", WeatherType.getGenreForSuchWeather(20.0));
        Assertions.assertEquals("pop", WeatherType.getGenreForSuchWeather(15.0));

        Assertions.assertNotEquals("pop", WeatherType.getGenreForSuchWeather(60.0));
        Assertions.assertNotEquals("pop", WeatherType.getGenreForSuchWeather(2.0));
        Assertions.assertNotEquals("pop", WeatherType.getGenreForSuchWeather(14.9));
        Assertions.assertNotEquals("pop", WeatherType.getGenreForSuchWeather(10.0));
    }

    @Test
    public void getPartyTest() {
        Assertions.assertEquals("party", WeatherType.getGenreForSuchWeather(Double.POSITIVE_INFINITY));
        Assertions.assertEquals("party", WeatherType.getGenreForSuchWeather(Double.MAX_VALUE));
        Assertions.assertEquals("party", WeatherType.getGenreForSuchWeather(30.1));
        Assertions.assertEquals("party", WeatherType.getGenreForSuchWeather(30.0));

        Assertions.assertNotEquals("party", WeatherType.getGenreForSuchWeather(29.999999));
        Assertions.assertNotEquals("party", WeatherType.getGenreForSuchWeather(29.0));
        Assertions.assertNotEquals("party", WeatherType.getGenreForSuchWeather(25.0));
        Assertions.assertNotEquals("party", WeatherType.getGenreForSuchWeather(10.0));
    }
}