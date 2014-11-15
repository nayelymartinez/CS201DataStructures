/**
 * WordFrequencies.java
 * 
 * A Java program that computes the word frequencies in a text.
 *
 * @author: Professor Amy Csizmar Dalal and Nayely Martinez
 * CS 201, Winter 2014
 * date: 19 February 2014
 */

import java.util.*;
import java.io.*;

public class WordFrequencies {
    public static void main(String[] args) {
        // Read in the file names from the command line
        String textFile = args[0];
        String ignoreFile = args[1];

        // Read in the threshold from the command line
        int threshold;
        try {
            threshold = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: threshold specified on command line was not an integer. Using default value of 5.");
            threshold = 5;
        }

        // Other variables we'll need later.
        Scanner textIn = null, ignoreIn = null;
        String line;
        String[] fields;



        // instantiate 2 WordBSTs, one to store the words to ignore and one to store the words and frequencies from the text. ***
        WordBST ignoreBST = new WordBST();
        WordBST frequencyBST = new WordBST();


        // Read in files and populate the trees
        try {
            // Read in and create the binary search tree of words from the "ignore" file
            ignoreIn = new Scanner(new File(ignoreFile));
            while (ignoreIn.hasNextLine()) {
                line = ignoreIn.nextLine();
                // remove all extra spaces from the line
                line = line.trim();
           
                // *** YOU FILL THIS IN: Add the ignored words to the "ignore" BST ***
                ignoreBST.insert(line);
            }

            ignoreIn.close();

            // Read in and create the binary search tree of words from the text file.
            textIn = new Scanner(new File(textFile));
            while (textIn.hasNextLine()) {
                line = textIn.nextLine();
                // split the line on spaces and convert all to lowercase.
                line = line.toLowerCase();
                // convert fields into array of words
                fields = line.split("\\s+");
                // remove punctuation
                for (int i = 0; i < fields.length; i++) {
                    String wordInLine = fields[i].replaceAll("\\W", "");
                    // if it is not in ignore list
                    if (ignoreBST.fetch(wordInLine) != 1) {
                        frequencyBST.insert(wordInLine);
                    }
                }

                
            }

            textIn.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: one of the input files does not exist. Check the file name and try again.");
            e.printStackTrace();
            System.exit(-1);
        } 
        catch (IOException e) {
            System.out.println("An error occurred when reading in one of the input files.");
            e.printStackTrace();
            System.exit(-1);
        }


        System.out.println("\nWords that appear in " + textFile + " at least " + threshold + " times: ");
        // Print out all the words from the text, and their frequencies, in alphabetical order, that occur at least THRESHOLD times. ***
        frequencyBST.printWords(threshold);
        System.out.println("\nThe word 'cried' appeared " + frequencyBST.fetch("cried") + " times.");
        System.out.println("\nThe word 'dear' appeared " + frequencyBST.fetch("dear") + " times.");
    }


}