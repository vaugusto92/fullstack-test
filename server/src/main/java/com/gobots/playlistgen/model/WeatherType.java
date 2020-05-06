package com.gobots.playlistgen.model;

/**
 * Enum that contains the proposed weather ranges
 * and their music genres.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
public enum WeatherType {
    
    FREEZING(Double.MIN_VALUE, 10.0),
    CHILLY(10.0, 15.0),
    MILD(15.0, 30.0),
    HOT(30.0, Double.MAX_VALUE);

    /**
     * Low temperature limit.
     */
    private final Double low;

    /**
     * High temperature limit.
     */
    private final Double high;

    private WeatherType(Double low, Double high) {
        this.low = low;
        this.high = high;
    }

    /**
     * Retrives the music genre for the given temperature.
     * 
     *  - Classical: t <= 10
     *  - Rock: 10 < t < 15
     *  - Pop: 15 <= t < 30
     *  - Party: t >= 30
     * 
     * VERY CONTROVERSIAL
     * 
     * @param temperature   The temperature of the city.
     * 
     * @return              A string that represents the mood.
     */
    public static String getGenreForSuchWeather(final Double temperature) {
        final StringBuilder stringBuilder = new StringBuilder();

        if (temperature == null) {
            return null;
        }

        if (temperature <= FREEZING.getHigh()) {
            stringBuilder.append("classical");
        }

        if (temperature < CHILLY.getHigh() && temperature > CHILLY.getLow()) {
            stringBuilder.append("rock");
        }

        if (temperature < MILD.getHigh() && temperature >= MILD.getLow()) {
            stringBuilder.append("pop");
        }

        if (temperature >= HOT.getLow()) {
            stringBuilder.append("party");
        }

        return stringBuilder.toString();
    }   

    /**
     * Retrives the weather for the given temperature.
     * 
     * @param temperature   The temperature of the city.
     * 
     * @return              A WeatherType object
     */
    public static WeatherType getWeather(final Double temperature) {
        WeatherType weatherType = null;
        
        if (temperature == null) {
            return null;
        }

        if (temperature <= FREEZING.getHigh()) {
            weatherType = FREEZING;
        }

        if (temperature < CHILLY.getHigh() && temperature > CHILLY.getLow()) {
            weatherType = CHILLY;
        }

        if (temperature < MILD.getHigh() && temperature >= MILD.getLow()) {
            weatherType = MILD;
        }

        if (temperature >= HOT.getLow()) {
            weatherType = HOT;
        }

        return weatherType;
    }   

    public Double getLow() {
        return low;
    }

    public Double getHigh() {
        return high;
    }
}