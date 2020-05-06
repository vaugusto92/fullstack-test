package com.gobots.playlistgen.repository;

import java.util.Optional;

import com.gobots.playlistgen.model.City;
import com.gobots.playlistgen.model.Coordinates;

/**
 * Interface that contains definitions about the Repository for 
 * the City object.
 * 
 * @implNote This interface should be changed to JpaRepository
 * or CrudRepository or any other implementation for production.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
public interface CityRepository {

    Optional<City> findByName(final String name);
    Optional<City> findByCoordinates(final Coordinates coord);

}
