import { Component, OnInit, OnDestroy } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';
import { WeatherService } from 'src/app/service/weather.service';
import { PlaylistService } from 'src/app/service/playlist.service';
import { Router, NavigationEnd } from '@angular/router';
import { Weather } from 'src/app/models/weather';
import { Track } from 'src/app/models/track';

/**
 * Component that controls the information on the
 * playlist page.
 * 
 * Author: Victor Augusto Alves Catanante
 */
@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit, OnDestroy {

  /**
   * Current playlist shown to the user.
   */
  playlist: Track[];

  /**
   * Current weather of the given location.
   */
  weather: Weather;

  constructor(private router: Router,
    private weatherService: WeatherService,
    private playlistService: PlaylistService,
    private flashMessagesService: FlashMessagesService) {

    // clears the state of this component
    // if the user starts a new search
    router.events.subscribe((val) => {
      if (val instanceof NavigationEnd) {
        if (val.url == "/") {
          this.clearState()
        }
      }
    });
  }

  ngOnInit(): void {
    this.retrieveWeather();
  }

  ngOnDestroy(): void {
    this.clearState();
  }

  /**
   * Manages the retrieval of weather information and 
   * also the playlist retrieval.
   */
  retrieveWeather() {
    // if the user choose the city
    if (this.playlistService.searchBy == 1) {
      this.weatherService.retrieveWeatherByCity(this.playlistService.cityName).subscribe(
        // fetch weather and playlist information
        (weather: Weather) => {
          this.weather = weather;
          this.retrievePlaylist(weather);
        },
        // manage errors
        response => {
          if (response.status != 200) {
            this.router.navigate(['/']);
            this.flashMessagesService.show(response.error.text,
              { cssClass: 'alert-danger text-center', timeout: 3000 });
          }
        });
    }

    if (this.playlistService.searchBy == 2) {
      this.weatherService.retrieveWeatherByCoordinates(this.playlistService.lon, this.playlistService.lat).subscribe(
        // fetch weather and playlist information
        (weather: Weather) => {
          this.weather = weather;
          this.retrievePlaylist(weather);
        },
        // manage errors
        response => {
          if (response.status != 200) {
            this.router.navigate(['/']);
            this.flashMessagesService.show(response.error.text,
              { cssClass: 'alert-danger text-center', timeout: 3000 });
          }

        });
    }

    // clear the state of the playlistService objects
    this.playlistService.cityName = undefined;
    this.playlistService.lat = undefined;
    this.playlistService.lon = undefined;
  }

  /**
   * Calls the methods to perform the playlist request.
   * 
   * @param weather   The weather as a parameter for the playlist
   */
  retrievePlaylist(weather) {
    this.playlistService.loadSpotifyCredentials();
    this.playlistService.retrievePlaylist(weather.main.genre).subscribe((playlist: Track[]) => {
      this.playlist = playlist;
    });
  }

  /**
   * Clears the objects of this instance. 
   */
  clearState() {
    this.playlist = undefined;
    this.weather = undefined;
  }
}
