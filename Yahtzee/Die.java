/*
 Die.java

 A class representing a single die.

 @author Professor Amy Csizmar Dalal
 @editor Nayely Martinez
 CS 201, Winter 2014
 date: 10 January 2014
 */

// This import statement is needed for the random number generator class
import java.util.*;

public class Die {
    private int numSides;
    private int showing;
    private int numTimesRolled;
    private Random rand;

    /**
     This is the "default constructor".
     */
    public Die() {
        this.showing = -1;

        // This is the object we use to generate the random number for the die rolls.
        this.rand = new Random();
    }

    // *** ACCESSORS AND MUTATORS GO HERE ***   
    public int getShowing() {
    	return this.showing;
    }

    /**
     Roll the die.

     @return The value showing on the die face after the roll.
     */
    public int roll() {
       	this.showing = rand.nextInt(6) + 1;
       	return this.showing;
    }
    
}