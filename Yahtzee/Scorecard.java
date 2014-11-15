/** Scorecard.java

A class that scores each player's turn based on their selection of which option to score.

@author Nayely Martinez
CS 201, Winter 2014
date: January 13, 2014

*/
import java.util.*;

public class Scorecard {
    /* The instance variables */
	private int total;
	
	/** Default Constructor: It creates the scorecard with 4 options set 1 (1 = option hasn't been used yet). 
	As the Player takes his turns, his choice will change to 0 (0 = option has been used). */
	public Scorecard() {
	    this.total = 0;			// Total for the whole game
	}
	
	// *** ACCESSORS AND MUTATORS GO HERE ***
	
	public int getTotal() {
	    return this.total;
	}
	
    //** Update Scorecard **//
    public void updateScorecard(int score) {
        this.total += score;
    }
}
	
	
	