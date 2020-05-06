package com.gobots.playlistgen.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.hc.core5.http.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gobots.playlistgen.util.Constants;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import org.springframework.stereotype.Service;


/**
 * Service that implements business rules concerning the Spotify API 
 * and their data.
 * 
 * {@link https://developer.spotify.com/}
 * 
 * @author Victor Augusto Alves Catanante
 * @since 1.0
 */
@Service
public class SpotifyService {

    /**
     * Client ID string for authorization.
     */
    private String clientId;

    /**
     * Client secret string for authorization.
     */
    private String clientSecret;

    /**
     * Loads the credentials when the class is created.
     */
    public SpotifyService() {
        loadCredentials();
    }

    /**
     * Searches for the Spotify Client ID and Secret files and
     * reads their data into the class' credentials.
     */
    private void loadCredentials() {
        final Path idPath = Path.of(Constants.SPOTIFY_CLIENT_KEY_FILE);
        final Path secretPath = Path.of(Constants.SPOTIFY_SECRET_KEY_FILE);
        try {
            final String id = Files.readString(idPath, StandardCharsets.US_ASCII);
            final String secret = Files.readString(secretPath, StandardCharsets.US_ASCII);

            setClientId(id);
            setClientSecret(secret);

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds a SpotifyApi object with the credentials and the given
     * access token.
     * 
     * @param accessToken   The String token for requests.
     * 
     * @return              A SpotifyApi object ready for requests.
     */
    private SpotifyApi buildSpotifyApi(final String accessToken) {
        final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret)
                .setAccessToken(accessToken).build();

        return spotifyApi;
    }

    /**
     * Builds a SpotifyApi object with the credentials.
     * 
     * @return A SpotifyApi object ready authentication.
     */
    private SpotifyApi buildSpotifyApi() {
        final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret)
                .build();

        return spotifyApi;
    }

    /**
     * Performs the authentication with the given credentials and
     * obtains an access token.
     * 
     * @return An Optional with the token, or empty if an error occurs.
     */
    public Optional<String> authorizeClientCredentials() {
        try {
            final SpotifyApi spotifyApi = buildSpotifyApi();

            final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            return Optional.of(clientCredentials.getAccessToken());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return Optional.empty();
    }

    /**
     * Retrieves a Recommendation object that represents a playlist,
     * with the given music genre as parameter. 
     * 
     * @param genre     The music genre to be recommended.
     * 
     * @return          An Optional with the recommendation, or empty in an
     *                  error occurs.
     */
    public Optional<Recommendations> getRecommendations(final String genre) {
        try {

            final Optional<String> accessToken = authorizeClientCredentials();

            if (accessToken.isPresent() && !accessToken.isEmpty()) {
                final SpotifyApi spotifyApi = buildSpotifyApi(accessToken.get());

                final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations().limit(10)
                        .seed_genres(genre).build();

                final Recommendations recommendations = getRecommendationsRequest.execute();
                return Optional.of(recommendations);
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return Optional.empty();
    }

    /**
     * Processes the Recommendation Optional and returns a String representation
     * of it as a JSON array.
     * 
     * @param playlist  The Optional Recommendation object with the tracks.
     * 
     * @return          A String with the tracks as a JSON array.
     */
    public String processPlaylist(final Optional<Recommendations> playlist) {

        final ObjectMapper mapper = new ObjectMapper();
        final ArrayNode array = mapper.createArrayNode();

        if (playlist.isPresent() && !playlist.isEmpty()) {
            final TrackSimplified[] tracks = playlist.get().getTracks();

            // iterate over tracks
            for (final TrackSimplified track : tracks) {

                final ArrayList<String> artistNames = new ArrayList<>();
                // iterate over artists
                for (final ArtistSimplified artist : track.getArtists()) {
                    artistNames.add(artist.getName());
                }

                final String artistList = String.join(", ", artistNames);

                final ObjectNode json = mapper.createObjectNode()
                    .put("name", track.getName())
                    .put("artists", artistNames.toString())
                    .put("artistList", artistList);

                array.add(json);
                artistNames.clear();
            }
        }

        return array.toString();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }
}