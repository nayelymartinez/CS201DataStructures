/** Player.java

A class that represents a single player in the game and stores the player's name, scorecard, and die objects.

@author Nayely Edith Martinez
@assistance Daniel Alabi
CS 201, Winter 2014
Date: January 17, 2014

*/
import java.util.*;

public class Player {
    
    /* The instance variables */
    private String name;
    private Scorecard scorecard;
    private Die[] dice; //Daniel
    private boolean option1;                // Declared.
    private boolean option2;
    private boolean option3;
    private boolean option4;
    
    
    
    /* The default constructor */
    public Player(String name) {
        this.name = name;
        this.scorecard = new Scorecard();
        this.dice = new Die[4];
        this.option1 = true;                // Initialized.
        this.option2 = true; 
        this.option3 = true; 
        this.option4 = true; 
        
        // Creates 4 die objects into the Die array
        for (int i = 0; i < 4; i++) {
            this.dice[i] = new Die();
        }
    }
    
    /* Accessors and Mutators */
    public String getName() {
        return this.name;
    }
    
    public Scorecard getScorecard() {
        return this.scorecard;
    }
    
    
    public void turn() {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> results = new ArrayList<Integer>(4);
        
        
        // Prints and adds to ArrayList.
        for (int i = 1; i < 5; i++) {
            System.out.println("Die " + i + " = " + this.dice[i-1].roll() + ".");
            results.add(this.dice[i-1].getShowing());
        }
        
        
        
        
        /* Do-While loop for re-rolling twice. Thank you to Daniel Alabi for suggesting this instead of a regular while loop. */
        int i = 0;
        do {
            System.out.println("\n" + this.getName() + ", would you like to re-roll? Enter 'yes' or 'no': ");
            String answer = input.nextLine().toLowerCase(); // Verify answer is lowercase
            
            if (answer.equals("yes")) {
            
                System.out.println("\nWhich dice would you like to re-roll? \nEnter the die number from 1 to 4 with no spaces in between each number. ");
                String reRoll = input.nextLine();
                
                // Re-rolls the same number of times as the length of the user input.
                for (int j = 0; j < reRoll.length(); j++) {
                    int numToReRoll = Character.getNumericValue(reRoll.charAt(j)) - 1;  /* Thank you to Daniel for suggesting the getNumericValue method. */
                    this.dice[numToReRoll].roll();
                    int realDieNum = numToReRoll + 1;
                    results.set(numToReRoll, this.dice[numToReRoll].getShowing());
                    System.out.println("\nYou have re-rolled " + (i+1) + " time(s)! Now: \nDie " + realDieNum + " = " + this.dice[numToReRoll].getShowing() + ".");
                } 
                
            } 
            
            else {
                break;
            }
            
            i = i+1;
            
        } while (i < 2);
             
        System.out.println("\n* * * * * Your final dice roll looks as follows: * * * * *");
        int finalRoll = 0;
        for (int k = 0; k < 4; k++) {
            finalRoll = this.dice[k].getShowing();
            System.out.println("Die " + (k+1) + " = " + finalRoll);
        }    
        
        
        
        /* Choose category */
        System.out.println("\nWhich category would you like to choose to score? \n If you'd like to choose: \n Two-of-a-kind, enter '1'. \n Three-of-a-kind, enter '2'. \n Four-of-a-kind (Straight), enter '3'. \n Yahtzee, enter '4'.");
        int category = 0;
        
        /* Checks to see if user input is a valid integer choice. Try-catch idea thanks to: http://stackoverflow.com/questions/19129416/checking-if-a-users-input-is-in-range-and-is-an-integer-java */
        boolean check = false;
        while (!check) {
            try {
                category = input.nextInt();
                check = true;
            }
            
            catch (InputMismatchException e) {
                System.out.println("\nThat is not a valid choice. \n Which category would you like to choose to score? \n If you'd like to choose: \n Two-of-a-kind, enter '1'. \n Three-of-a-kind, enter '2'. \n Four-of-a-kind (Yahtzee), enter '3'. \n Straight, enter '4'.");
            }
        }
        
        
        /* Category Validation */
        
        // First, sort the array from smallest to largest number.
        Collections.sort(results);
        
        // Two-of-a-Kind
        whileLoop:                           /* Thanks for the label idea (in order to exit out of all loops) to http://stackoverflow.com/questions/6638321/how-to-exit-two-nested-loops. */
        while (true) {
            if (category == 1) {
                if (this.option1) {
                    for (i = 0; i < 3; i++) {
                        int compare1 = results.get(i);      // Get a Die value in arraylist.
                        int compare2 = results.get(i + 1);  // Get the next Die value in arraylist.
                        if (compare1 == compare2) {
                            int score = compare1 * 2;
                            this.scorecard.updateScorecard(score);
                            this.option1 = false;
                            break whileLoop;
                        }
                    }

                    System.out.println("\nYou did not have a Two-of-a-Kind. You received a score of 0.");
                    break whileLoop;
                }
                else {
                    System.out.println("\nYou have already chosen this category. \n Which category would you like to choose to score? \n If you'd like to choose: \n Two-of-a-kind, enter '1'. \n Three-of-a-kind, enter '2'. \n Four-of-a-kind (Yahtzee), enter '3'. \n Straight, enter '4'.");
                    category = input.nextInt();
                }
            }
            
            // Three-of-a-Kind
            else if (category == 2) {
                if (this.option2) {
                    for (i = 0; i < 2; i++) {
                        int compare1 = results.get(i);      // Get a Die value in arraylist.
                        int compare2 = results.get(i + 2);  // Get the Die value 2 ahead of compare1 in arraylist.
                        if (compare1 == compare2) {
                            int score = compare1 * 3;
                            this.scorecard.updateScorecard(score);
                            option2 = false;
                            break whileLoop;
                        }
                    }
                    System.out.println("\nYou did not have a Three-of-a-Kind. You received a score of 0.");
                    break whileLoop;
                }
                else {
                    System.out.println("\nYou have already chosen this category. \n Which category would you like to choose to score? \n If you'd like to choose: \n Two-of-a-kind, enter '1'. \n Three-of-a-kind, enter '2'. \n Four-of-a-kind (Yahtzee), enter '3'. \n Straight, enter '4'.");
                    category = input.nextInt();
                }
            }

            // Four-of-a-Kind (Yahtzee!)
            else if (category == 3) {
                if (this.option3) {
                    int compare1 = results.get(0);        // Get a Die value in arraylist.
                    int compare2 = results.get(3);        // Get the Die value 3 ahead of compare1 in arraylist.
                    if (compare1 == compare2) {
                        scorecard.updateScorecard(40);
                        option3 = false;
                        break whileLoop;
                    }
                    System.out.println("\nYou did not have a Four-of-a-Kind. You received a score of 0.");
                    break whileLoop;
                }
                else {
                    System.out.println("\nYou have already chosen this category. \n Which category would you like to choose to score? \n If you'd like to choose: \n Two-of-a-kind, enter '1'. \n Three-of-a-kind, enter '2'. \n Four-of-a-kind (Yahtzee), enter '3'. \n Straight, enter '4'.");
                    category = input.nextInt();
                }
            }
            
            
            // Straight
            else if (category == 4) {
                i = 0;
                if (this.option4) {
                    while (i < 3) {
                        int compare1 = results.get(i);      // Get a Die value in arraylist.
                        int compare2 = results.get(i + 1);  // Get the Die value ahead of compare1 in arraylist.
                        int oneValueMore = compare1 + 1;
                        if (compare2 != oneValueMore) {     // Compare if 2nd value = 1st value + 1, if it doesn't. If at any point it doesn't, break.
                            System.out.println("\nYou did not have a Straight. You received a score of 0.");
                            break whileLoop;
                        }
                        else {
                            i++;
                        }
                    }
                    this.scorecard.updateScorecard(25);
                    break whileLoop;
                }
                
                else {
                    System.out.println("\nYou have already chosen this category. \n Which category would you like to choose to score? \n If you'd like to choose: \n Two-of-a-kind, enter '1'. \n Three-of-a-kind, enter '2'. \n Four-of-a-kind (Yahtzee), enter '3'. \n Straight, enter '4'.");
                    category = input.nextInt();
                }
            }

        
        }
    }
    
}