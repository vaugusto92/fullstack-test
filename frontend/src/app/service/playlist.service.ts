import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient, HttpParams } from "@angular/common/http";
import { Track } from '../models/track';

/**
 * Service to control requests to the Spotify
 * endpoints in the SpringBoot application, in order
 * to fetch playlists.
 * 
 * Author: Victor Augusto Alves Catanante
 */
@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  /**
   * Constant with the server's address.
   */
  baseUri: string = "http://localhost:8080";

  /**
   * Stores the name of the city for the request.
   */
  cityName: string;
  
  /**
   * Stores the latitude information for the request.
   */
  lat: Number;
  
  /**
   * Stores the longitude information for the request.
   */
  lon: Number;
  
  /**
   * Stores what will be the parameter for the request.
   */
  searchBy: Number;

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Performs the authentication in the Spotify API.
   * Retrieves an access token to the SpringBoot
   * application.
   * 
   * @returns   An Observable with the response, either
   *            'Authorized!' or null.
   */
  loadSpotifyCredentials(): Observable<String> {
    let url = `${this.baseUri}/credentials`;
    return this.httpClient.get<String>(url);
  }

  /**
   * Retrieves a playlist according to the genre.
   * 
   * @param genre   A string with the music genre to be
   *                recommended.
   * 
   * @returns       An Observable with a list of Tracks.
   */
  retrievePlaylist(genre): Observable<Track[]> {
    let url = `${this.baseUri}/playlist`;

    const params = new HttpParams()
      .set('genre', genre)

    return this.httpClient.get<Track[]>(url, { params: params });
  }

  /**
   * Clears all parameters of the class.
   */
  clearState() {
    this.cityName = undefined;
    this.lat = undefined;
    this.lon = undefined;
    this.searchBy = undefined;
  }
}
