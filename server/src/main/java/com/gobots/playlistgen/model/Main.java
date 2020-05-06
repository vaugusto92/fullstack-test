package com.gobots.playlistgen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that represents the temperature data of the location on an
 * OpenWeatherMap API request.
 * 
 * @implNote Property names need to be out of convention in order to match the
 *           API object and allow proper manipulation.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
public class Main {

    /**
     * The temperature of the location.
     */
    private Double temp;
    
    /**
     * The pressure of the location.
     */
    private Double pressure;
    
    /**
     * The humidity level of the location.
     */
    private Double humidity;
   
    /**
     * The lowest temperature of that day in that
     * location.
     */
    private Double temp_min;
   
    /**
     * The highest temperature of that day in that
     * location.
     */
    private Double temp_max;
    
    /**
     * The music genre for that temperature.
     * VERY CONTROVERSIAL
     */
    private String genre;

    public Main() {

    }

    public Main(Double temp, Double pressure, Double humidity, Double temp_min, Double temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
        result = prime * result + ((pressure == null) ? 0 : pressure.hashCode());
        result = prime * result + ((temp == null) ? 0 : temp.hashCode());
        result = prime * result + ((temp_max == null) ? 0 : temp_max.hashCode());
        result = prime * result + ((temp_min == null) ? 0 : temp_min.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Main other = (Main) obj;
        if (humidity == null) {
            if (other.humidity != null)
                return false;
        } else if (!humidity.equals(other.humidity))
            return false;
        if (pressure == null) {
            if (other.pressure != null)
                return false;
        } else if (!pressure.equals(other.pressure))
            return false;
        if (temp == null) {
            if (other.temp != null)
                return false;
        } else if (!temp.equals(other.temp))
            return false;
        if (temp_max == null) {
            if (other.temp_max != null)
                return false;
        } else if (!temp_max.equals(other.temp_max))
            return false;
        if (temp_min == null) {
            if (other.temp_min != null)
                return false;
        } else if (!temp_min.equals(other.temp_min))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String jsonStr = null;
        final ObjectMapper mapper = new ObjectMapper();

        try {
            jsonStr = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }
}