package com.gobots.playlistgen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that represents some response attributes of an OpenWeatherMap API
 * request. Some information provided by the API is not relevant to this
 * application.
 * 
 * @implNote Property names need to be out of convention in order to match the
 *           API object and allow proper manipulation.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
public class Weather {

    /**
     * The id of the weather response.
     */
    private Long id;
    
    /**
     * The name of the city.
     */
    private String name;
   
    /**
     * The code of response.
     */
    private Long cod;

    /**
     * The coordinates of the city.
     */
    private Coordinates coord;

    /**
     * The temperature information of the city.
     */
    private Main main;

    public Weather() {
    }

    public Weather(Long id, String name, Long cod, Coordinates coord, Main main) {
        this.id = id;
        this.name = name;
        this.cod = cod;
        this.coord = coord;
        this.main = main;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cod == null) ? 0 : cod.hashCode());
        result = prime * result + ((coord == null) ? 0 : coord.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((main == null) ? 0 : main.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Weather other = (Weather) obj;
        if (cod == null) {
            if (other.cod != null)
                return false;
        } else if (!cod.equals(other.cod))
            return false;
        if (coord == null) {
            if (other.coord != null)
                return false;
        } else if (!coord.equals(other.coord))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (main == null) {
            if (other.main != null)
                return false;
        } else if (!main.equals(other.main))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
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