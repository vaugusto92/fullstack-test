import { Component, OnInit } from '@angular/core';
import { PlaylistService } from 'src/app/service/playlist.service';
import { Router, NavigationEnd } from '@angular/router';

/**
 * Component that controls the navigation bar.
 * In this case, it only contains one button.
 * 
 * Author: Victor Augusto Alves Catanante
 */
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  /**
   * Boolean to control the visibility of the 
   * return button.
   */
  showReturn = true;

  constructor(private router: Router,
    private playlistService: PlaylistService) {

    // shows the button only if the user is
    // in the playlist view route.
    router.events.subscribe((val) => {
      if (val instanceof NavigationEnd) {
        if (val.url == "/recommendation") {
          this.showReturn = false;
        } else {
          this.showReturn = true;
        }
      }
    });
  }

  ngOnInit(): void {
  }

  /**
   * Clears the state of the playlist service object
   * if the user clicked the return button.
   */
  onClickHome() {
    this.playlistService.clearState();
  }
}