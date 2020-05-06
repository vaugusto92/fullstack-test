package com.gobots.playlistgen.controller;

import java.util.Optional;

import com.gobots.playlistgen.service.SpotifyService;
import com.wrapper.spotify.model_objects.specification.Recommendations;

import com.gobots.playlistgen.util.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to send requests to the Spotify API endpoints.
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
@RestController
@CrossOrigin(origins = Constants.ANGULAR_URI)
public class SpotifyController {

    /**
     * Spotify Service object.
     */
    private final SpotifyService service;

    public SpotifyController() {
        this.service = new SpotifyService();
    }

    /**
     * Performs the authentication on the Spotify API.
     * 
     * Receives the access token, and if it exists,
     * then the Spotify Service is able to retrieve 
     * the playlist.
     * 
     * @return  A ResponseEntity of String:
     *              - OK, if the token exists and is not empty;
     *              - ACCEPTED, if the token exists but is empty;
     *              - BAD_REQUEST if an error occurs.
     */
    @RequestMapping(value = "/credentials", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCredentials() {
        ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.ACCEPTED);

        try {

            final Optional<String> accessToken = service.authorizeClientCredentials();

            if (accessToken.isPresent() && !accessToken.isEmpty()) {
                response = ResponseEntity.status(HttpStatus.OK).body("Authorized!");
            }

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * Retrieves the playlist recommendation from the Spotify API,
     * with the given music genre.
     * 
     * @param genre     The music genre to be recommended.
     * 
     * @return          A ResponseEntity of String:
     *                      - OK, if the playlist was successfully retrieved;
     *                      - ACCEPTED, if the playlist is empty;
     *                      - BAD_REQUEST if an error occurs.
     */
    @RequestMapping(value = "/playlist", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getPlaylist(@RequestParam(required = false, name = "genre") Optional<String> genre) {
        ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.ACCEPTED);

        try {

            if (genre.isPresent() && !genre.isEmpty()) {
                final Optional<Recommendations> playlist = service.getRecommendations(genre.get());
                final String json = service.processPlaylist(playlist);
                response = ResponseEntity.status(HttpStatus.OK).body(json);
            }

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}