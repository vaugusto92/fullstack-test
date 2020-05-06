import { Coordinates } from "./coordinates";
import { Main } from "./main";

/**
 * Class that represents the Weather object
 * from the SpringBoot application.
 * 
 * Author: Victor Augusto Alves Catanante
 */
export class Weather {
    /**
     * The id of the weather response.
     */
    id?: Number;

    /**
     * The name of the city.
     */
    name: string;

    /**
     * The code of response.
     */
    cod?: Number;

    /**
     * The coordinates of the city.
     */
    coord: Coordinates;
    
    /**
     * The temperature information of the city.
     */
    main: Main;
}