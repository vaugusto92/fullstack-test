import { Component, OnInit } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';
import { WeatherService } from '../../service/weather.service';
import { Router } from '@angular/router';
import { PlaylistService } from 'src/app/service/playlist.service';

/**
 * Component that controls the information on the
 * home page.
 * 
 * Author: Victor Augusto Alves Catanante
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  /**
   * Constant that contains the possible input types,
   * either City or Geographic Coordinates.
   */
  inputTypes = [
    { description: 'City', id: 1 },
    { description: 'Geographic Coordinates', id: 2 }
  ];

  /**
   * select city by default as input.
   */
  searchInput = this.inputTypes[0];

  /**
   * The name of the city to be searched for.
   */
  cityName: string;
  
  /**
   * Latitude of the city to be searched for.
   */
  latitude: string;
  
  /**
   * Longitude of the city to be searched for.
   */
  longitude: string;

  constructor(
    private flashMessage: FlashMessagesService,
    private weatherService: WeatherService,
    private playlistService: PlaylistService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  /**
   * Selects the search input type when the
   * dropdown is changed.
   * 
   * @param searchInput   The searchInput object to be selected.
   */
  chooseSearchInput(searchInput) {
    this.searchInput = searchInput;
  }

  /**
   * Submits the information to the playlistService
   * as a simplified form, after validation.
   */
  onSubmit() {

    var valid = false;

    // validate city
    if (this.inputTypes[0] == this.searchInput) {
      valid = this.manageCitySearch(valid);
    }

    // validate location
    if (this.inputTypes[1] == this.searchInput) {
      valid = this.manageCoordinatesSearch(valid);
    }

    // assign information and navigate
    if (valid) {
      this.playlistService.searchBy = this.searchInput['id']
      this.router.navigate(['/recommendation']);
    }
  }

  /**
   * Validates a search by latitude and longitude.
   * 
   * @param valid   Boolean to be filled as true or false
   *                according to the validation.
   */
  manageCoordinatesSearch(valid) {
    const lat = Number(this.latitude);
    const lon = Number(this.longitude);
    
    if (isNaN(lon) || isNaN(lat)) {
      this.flashMessage.show('Please fill in longitude and latitude information.',
      { cssClass: 'alert-danger text-center', timeout: 3000 });
      
    } else {
      this.playlistService.lat = lat;
      this.playlistService.lon = lon;
      valid = true;
    }
    
    return valid;
  }
  
  /**
   * Validates a search by city.
   * 
   * @param valid   Boolean to be filled as true or false
   *                according to the validation.
   */
  manageCitySearch(valid) {
    if (this.cityName == undefined) {
      this.flashMessage.show('Please fill in the city.',
        { cssClass: 'alert-danger text-center', timeout: 3000 });

    } else {
      this.playlistService.cityName = this.cityName;
      valid = true;
    }

    return valid;
  }

  /**
   * Clears the objects of this instance.
   */
  clearState() {
    this.searchInput = this.inputTypes[0];

    this.cityName = undefined;
    this.latitude = undefined;
    this.longitude = undefined;
  }
}
