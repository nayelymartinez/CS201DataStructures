/*
 Game.java

 A class representing a simplified Yahtzee game.

 @author Professor Amy Csizmar Dalal
 @editor Nayely Edith Martinez
 CS 201, Winter 2014
 date: 18 January 2014
 */

import java.util.*;

public class Game {
    private Player player1;
    private Player player2;

    /*
     The constructor instantiates a new game by creating the two players.

     @param p1 The name of the first player
     @param p2 The name of the second player.
     */
    public Game(String p1, String p2) {
        this.player1 = new Player(p1);
        this.player2 = new Player(p2);
    }

    /**
     Plays 4 rounds of the game.
     */
    public void play() {
        for (int i=0; i<4; i++) {
            System.out.println("\n \n* * * * * Player 1 * * * * *");
            System.out.println("\n" + this.player1.getName() + ", it's your turn! \n");
            System.out.println("\n \nYou rolled:");
            this.player1.turn();
            System.out.println("\n \n" + this.player1.getName() + ", your score is: " + this.player1.getScorecard().getTotal());
            
            System.out.println("\n \n* * * * * Player 2 * * * * *");
            System.out.println("\n" + this.player2.getName() + ", it's your turn! \n");
            System.out.println("\n \nYou rolled:");
            this.player2.turn();
            System.out.println("\n \n" + this.player2.getName() + ", your score is: " + this.player2.getScorecard().getTotal());
        }
    }

    /*
     Determines which player won, and prints this info out to the screen.
     */
    public void declareWinner() {
        Scorecard card1, card2;
        int score1, score2;

        card1 = this.player1.getScorecard();
        card2 = this.player2.getScorecard();

        score1 = card1.getTotal();
        score2 = card2.getTotal();

        if (score1 > score2) {
            System.out.println("\n \n* * * * * * FINAL RESULTS * * * * * \n");
            System.out.println(this.player1.getName() + " is the winner with " + score1 + " points!");
            System.out.println(this.player2.getName() + " is the loser with " + score2 + " points. \n");
        } else if (score2 > score1) {
            System.out.println(this.player2.getName() + " is the winner with " + score2 + " points!");
            System.out.println(this.player1.getName() + " is the loser with " + score1 + " points. \n");
        } else {
            System.out.println(this.player1.getName() + " and " + this.player2.getName() + " tied with " + score1 + " points. \n");
        }
    }

    /*
     The main method for the project: starts and plays a game of MiniYahtzee.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name1, name2;
        Game yahtzee;

        // Banner message
        System.out.println(" * * * * * Time to play MiniYahtzee! * * * * *");
        System.out.println();

        // Get names of players
        System.out.print("Player 1, please enter your name: ");
        name1 = input.nextLine();

        System.out.print("Player 2, please enter your name: ");
        name2 = input.nextLine();

        // Play the game!
        yahtzee = new Game(name1, name2);
        yahtzee.play();

        // Declare a winner
        yahtzee.declareWinner();
        
    }

}