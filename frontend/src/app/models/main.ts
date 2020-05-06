/**
 * Class that represents the Main object
 * from the SpringBoot application.
 * 
 * Author: Victor Augusto Alves Catanante
 */
export class Main {
    /**
     * The temperature of the location.
     */
    temp?: Number;
    
    /**
     * The pressure of the location.
     */
    pressure?: Number;
    
    /**
     * The humidity level of the location.
     */
    humidity?: Number;
    
    /**
     * The lowest temperature of that day in that
     * location.
     */
    temp_min?: Number;
    
    /**
     * The highest temperature of that day in that
     * location.
     */
    temp_max?: Number;
    
    /**
     * The music genre for that temperature.
     * VERY CONTROVERSIAL
     */
    genre?: string;
}