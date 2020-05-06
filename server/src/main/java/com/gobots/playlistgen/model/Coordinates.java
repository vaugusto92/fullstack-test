package com.gobots.playlistgen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that represents the Coordinates of the location on an OpenWeatherMap
 * API request.
 * 
 * @implNote Property names need to be out of convention in order to match the
 *           API object and allow proper manipulation.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
public class Coordinates {

    /**
     * Longitude information.
     */
    private Double lon;

    /**
     * Latitude information.
     */
    private Double lat;

    public Coordinates() {
    }

    public Coordinates(final Double lon, final Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lon == null) ? 0 : lon.hashCode());
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
        Coordinates other = (Coordinates) obj;
        if (lat == null) {
            if (other.lat != null)
                return false;
        } else if (!lat.equals(other.lat))
            return false;
        if (lon == null) {
            if (other.lon != null)
                return false;
        } else if (!lon.equals(other.lon))
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