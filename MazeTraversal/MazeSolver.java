import java.io.*;
import java.util.*;

/**
 * Find the shortest path through a maze using graph traversals.
 * @author Amy Csizmar Dalal and Nayely Martinez
 * CS 201, Winter 2014
 * date: 9 March 2014
 */
public class MazeSolver {

	/**
	 * Reads in the maze from a text file, creates a graph to represent the maze, and then calls
	 * a breadth-first traversal to find the shortest path through the maze.
	 * @param args	The command line argument is the name of the maze file.
	 */
	public static void main(String[] args) {
		// Instance variables
		Graph maze = new Graph();
		Scanner infile = null;
		String line;
		String[] fields;
		
		// The line number is also the current row number in the maze.
		int lineNum = 0;
	
		// Open the file; exit if the file does not exist.
		try {
			infile = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + args[0] + " not found, exiting.");
			System.exit(0);
		}

/*
Create vertices
*/
		/*
		 * Read in each line in the file. Each line represents a row in the maze. Each row
		 * contains information for each column: a 0 in the column means there's an empty
		 * space here (a vertex), while a 1 means there's a wall here (no vertex). Create
		 * a vertex at (lineNum, col) every time you encounter a 0 in the file.
		 */
		while (infile.hasNextLine()) {
			line = infile.nextLine();
			fields = line.split("\\s"); // split on spaces 
			for (int col=0; col < fields.length; col++) {
                // ***add try-catch block?***
				if (Integer.parseInt(fields[col]) == 0) {
					maze.insertVertex(lineNum, col);
				}
			}
			lineNum++;
		}
		
		infile.close();

/*
Create edges
*/
		/*
		 * Once the vertices are determined, figure out the edges. An edge exists
		 * between two vertices if they are adjacent, i.e. if the row and column differ by 0 or 1.
		 */

		// Initialize variables
		Vertex currentVertex;
		Vertex nextNeighbor;
		ArrayList<VertexInt> vertices = maze.getVertices();

		// Go through maze and add neighbors 
		for (int i = 0; i < vertices.size(); i++) {
			currentVertex = (Vertex)vertices.get(i);
			// Get neighbor for each item in adjacency list of current vertex
			for (int j = 0; j < vertices.size(); j++) {			
				nextNeighbor = (Vertex)vertices.get(j);
				// Check if the difference between the two vertices is at most 1 (adjacent). Thank you to Daniel Alabi for this recommendation.
				int check = Math.abs(currentVertex.getRow() - nextNeighbor.getRow()) +
							Math.abs(currentVertex.getCol() - nextNeighbor.getCol());
				// If adjacent, add as mutual neighbors (edges)
				if (check == 1) {
					maze.insertEdge(currentVertex, nextNeighbor);
				}
			}
		}

		// Print out the graph.
		maze.printGraph();
		
		// Solve the maze by breadth-first traversal
		String solution = maze.breadthFirstTraversal();
		if (solution.equals("")) {
			System.out.println("No solution found.");			
		} else {
            System.out.println("Solution found: " + solution);
		}
	}
}
