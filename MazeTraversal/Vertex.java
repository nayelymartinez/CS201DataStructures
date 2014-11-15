import java.util.*;

/**
* A Vertex class that uses adjancency lists to store, access, and add adges to a vertex.
* @author Nayely Martinez
* CS 201, Winter 2014
* date: 9 March 2014
*/

public class Vertex implements VertexInt {
	// Instance Variables
	private boolean visited;
	private Vertex predecessor;
	private int row;
	private int col;
	private ArrayList<VertexInt> neighbors;	// Initializes neighboring vertices list

	// Constructor
	public Vertex(int r, int c) {
		neighbors = new ArrayList<VertexInt>();
		visited = false;
		row = r;
		col = c;
	}


	// Returns whether this vertex has been visited on the latest graph traversal.
	public boolean isVisited() {
		return visited;
	}

	// QUESTION. Why would you reset?
	// Sets whether this vertex has been visited (true) or resets it (false).
	public void setVisited(boolean v) {
		visited = v;
	}

    // Returns the predecessor (the vertex visited prior to this one) to this vertex.
    public VertexInt getPredecessor() {
    	return predecessor;
    }

    // Sets the predecessor for this vertex.
    public void setPredecessor(VertexInt p) {
    	predecessor = (Vertex) p;
    }

	// Returns the x-coordinate of this vertex in the maze.
	public int getRow() {
		return row;
	}

	// Returns the y-coordinate of this vertex in the maze.
	public int getCol() {
		return col;
	}

	// Returns a list of the neighbors of this node.
	public ArrayList<VertexInt> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * Adds a new neighbor to the list of neighbors for this vertex if that neighbor is not already in the list of neighbors.
	 * Thank you to Daniel for suggesting isNeighbor check before adding new neighbors.
	 * @param v	The new neighbor (vertex)
	 */
	public void addNeighbor(VertexInt v) {
		if (!this.isNeighbor(v)) {
			neighbors.add((Vertex) v);
		}
	}
		
	/**
	 * Compares two vertices to see if they are equal. Two vertices are equal if their
	 * row and column values are identical.
	 * @param v		The vertex to which to compare this vertex.
	 * @return 		true if the 2 vertices are equal, false otherwise
	 */
	public boolean equals(VertexInt v) {
		Vertex vToCompare = (Vertex) v;
		// Return true if boths rows and cols are equal
		if (this.getRow() == vToCompare.getRow() && this.getCol() == vToCompare.getCol()) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Returns true if the vertex v is a neighbor of this vertex, and false otherwise.
	 */
	public boolean isNeighbor(VertexInt v) {
		Vertex vIsNeighbor = (Vertex) v;
		if (neighbors.contains(vIsNeighbor)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the vertex as a (row, col) string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String coordinates = "(" + this.getRow() + ", " + this.getCol() + ")";
		return coordinates;
	}

	
	/**
	 * Prints out a list of the neighbors of this node
	 */
	public void printNeighborList() {
		for (int i = 0; i < neighbors.size(); i++) {
			System.out.print(neighbors.get(i) + " ");
		}
		System.out.println("");
	}
}