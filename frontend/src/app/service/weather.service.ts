import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Weather } from '../models/weather';

import { HttpClient, HttpParams } from "@angular/common/http";

/**
 * Service to control requests to the OpenWeatherMap
 * endpoints in the SpringBoot application, in order
 * to fetch weather information.
 * 
 * Author: Victor Augusto Alves Catanante
 */
@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  /**
   * Constant with the server's address.
   */
  baseUri: string = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  /**
   * Retrieves the weather information according to
   * the name of a city.
   * 
   * @param city  A string with the name of the city.
   * 
   * @returns     An Observable of Weather object.
   */
  retrieveWeatherByCity(city): Observable<Weather> {
    let url = `${this.baseUri}/weather`;

    const params = new HttpParams()
      .set('city', city);

    return this.httpClient.get<Weather>(url, { params: params });
  }

  /**
   * Retrieves the weather information according to
   * the lon and lat coordinates.
   * 
   * @param lon   A number that represents the longitude.
   * @param lat   A number that represents the latitude.
   * 
   * @returns     An Observable of Weather object.
   */
  retrieveWeatherByCoordinates(lon, lat): Observable<Weather> {
    let url = `${this.baseUri}/weather`;

    const params = new HttpParams()
      .set('lon', lon)
      .set('lat', lat);

    return this.httpClient.get<Weather>(url, { params: params });
  }
}