/**
 * The main module of the application.
 * 
 * Creates and controls the routes and their
 * components, imports and provides all the
 * user-defined service classes and components.
 * 
 * Also provides the auxiliary components and
 * services:
 * 
 * # FlashMessagesModule
 *  - used to generate error messages under
 *    the navbar.
 * 
 * Author: Victor Augusto Alves Catanante
 */

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { RouterModule, Routes} from '@angular/router'

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { FlashMessagesModule } from 'angular2-flash-messages';
import { WeatherService } from './service/weather.service';
import { HttpClientModule } from '@angular/common/http';
import { RecommendationComponent } from './components/recommendation/recommendation.component';
import { PlaylistService } from './service/playlist.service';

const appRoutes: Routes = [
  {path:'', component: HomeComponent},
  {path:'recommendation', component:RecommendationComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    RecommendationComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    FlashMessagesModule.forRoot(),
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    WeatherService,
    PlaylistService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
