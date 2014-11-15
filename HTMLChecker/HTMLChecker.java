/**
 * HTMLChecker.java
 * A Java program that checks the tags in an HTML document to make sure that they match.
 *
 * @author Professor Amy Csizmar Dalal and Nayely Martinez
 * CS 201, Winter 2014
 * date: 27 January 2014
 */

import java.util.Scanner;
import java.io.*;

public class HTMLChecker {
    public static void main(String[] args) {
        DLLStack htmlStack = new DLLStack();
        Scanner infile;
        String[] fields;
        String line;
        String currentTag;
        boolean allMatched = true;
        int tagIndex;

        try {
            // Open the HTML file to check; file name specified on the command line
            infile = new Scanner(new File(args[0]));

            while (infile.hasNextLine() && allMatched) {
                line = infile.nextLine();
            
                // Split the line at the tag marker. This lets us process multiple tags per line.
                fields = line.split("<");
               
                // Process each tag found in the line, stripping out "extra" data not related to the tag.
                for (int i=0; i<fields.length; i++) {
                    if (fields[i].length() > 0) {
                        // check: is this an opening tag or a closing tag?
                        if (fields[i].charAt(0) == '/') {
                            // closing tag, pop and check after stripping the tag markers
                            currentTag = fields[i].substring(1, fields[i].indexOf('>'));
                            System.out.println("currentTag:\n" + currentTag);       // Replace() method help thanks to: http://stackoverflow.com/questions/4576352/java-remove-all-occurances-of-char-from-string
                            String strPopped = htmlStack.pop();
                            System.out.println("Popped string:\n" + strPopped);
                            // NOTE: If you find a mismatch, break out of the loop immediately.
                            if (!strPopped.equals(currentTag)) {
                                allMatched = false;
                                break;
                            }
                        } 
                        else {
                            // starting tag, push onto the stack
                            currentTag = fields[i].substring(0, fields[i].indexOf('>'));
                            htmlStack.push(currentTag);
                        } 
                    }
                } // end for loop
            } // end while loop

            infile.close();

            if (allMatched) {
                System.out.println("This HTML document is formatted according to spec.");
            } else {
                System.out.println("This HTML document is not formatted according to spec.");
            }

        }

        catch (IOException e) {
            System.out.println("An error occurred when processing file " + args[0] + ". Exiting.");
            System.exit(0);
        }
    }
}