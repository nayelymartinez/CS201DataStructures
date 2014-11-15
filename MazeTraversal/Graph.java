import java.util.*;

/**
* A Graph class that finds and inserts vertices, adds edges to vertices, and prints the graph by traversing through each vertex.
* This graph implements the GraphInt interface.
* @author Nayely Martinez
* Additional Contributors: Daniel Alabi for his assistance with the breadthFirstTraversal and edge creation logic.
* CS 201, Winter 2014
* date: 9 March 2014
*/

public class Graph implements GraphInt {
	// Instance variables
	private ArrayList<VertexInt> vertices;
	
	// Constructor
	public Graph() {
		vertices = new ArrayList<VertexInt>();
	}

	/**
	 * Returns the current list of vertices
	 */
	public ArrayList<VertexInt> getVertices() {
		return vertices;
	}

	/**
	 * Adds a vertex to the list of vertices
	 * @param row	The x-position of this vertex in the maze
	 * @param col	The y-position of this vertex in the maze	
	 */
	public void insertVertex(int row, int col) {
		vertices.add(new Vertex(row, col));
	}

	/**
	 * Adds an edge between two vertices
	 * @param v1 	The first vertex
	 * @param v2	The second vertex
	 */
	public void insertEdge(VertexInt v1, VertexInt v2) {
		v1.addNeighbor(v2);
		v2.addNeighbor(v1);
	}

 
	/**
	 * Displays the graph contents by printing out each vertex and its neighbor list.
	 */
	public void printGraph() {
		for (int i = 0; i < vertices.size(); i++) {
			Vertex curr = (Vertex)vertices.get(i);
			System.out.println(curr);
			curr.printNeighborList();
		}
	}
	
	/**
	 * Conducts a breadth-first search of a graph, starting at the upper left corner of
	 * the maze (0, 0) and continuing to the bottom right corner of the maze. Prunes all 
     * of the visited nodes that are NOT on the shortest path from (0, 0) to the bottom
     * right corner of the maze.
	 * @return	A string listing the vertices visited, in order, on the path from (0, 0) 
     *          to the end of the maze, or an empty string if no path is found.
	 */
	public String breadthFirstTraversal() {
		// Initialize queue as our data structure
		Queue queue = new LinkedList();
		// Start at (0,0)
		Vertex start = (Vertex)vertices.get(0);
		// Add vertex (0,0) to queue.
		queue.add(start);										
		start.setVisited(true);
		// Set solution initally to empty string
		String solution = "";

		// Initialize and define variables
		Vertex current = start;
		int last = vertices.size() - 1;
		Vertex end = (Vertex)vertices.get(last);

		System.out.println("Start" + start);
		System.out.println("End" + end);

		int x = 0;
		// Run until we reach the end of the maze. Thank you to Daniel for helping with the logic
		while (!current.equals(end)) {
			// If queue is empty AND we haven't reached end of maze, this means run into dead end with no further open neighbors, so return no solution.
			if (!current.equals(end) && queue.isEmpty()) {
				return solution;
			}
			else {
				current = (Vertex)queue.remove();
				// Mark current vertex as visited
				current.setVisited(true);
				// Get edges of current vertex									
				ArrayList<VertexInt> neighborList = current.getNeighbors();	
				// Add each neighbor (edge) to the queue
				for (int i = 0; i < neighborList.size(); i++) {				
					Vertex neighb = (Vertex)neighborList.get(i);
					// If neighbor has not been visited, add to the queue and set current as predecessor of new neighbor
					if (!neighb.isVisited()) {
						int neighborRow = neighb.getRow();
						int neighborCol = neighb.getCol();
						queue.add(neighb);
						neighb.setPredecessor(current);
					}
				}
			}
		}

		
		// Get predecessors
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		while (current != null) {
			// Add current vertex to start of path list.
			path.add(0, current);
			current = (Vertex)current.getPredecessor();
		}

		System.out.println("Size of path: " + path.size());

		// Return path as string (now reversed so prints from (0,0) to bottom right of maze).
		for (int i = 0; i < path.size(); i++) {
			solution = solution + path.get(i) + " ";
		}
		return solution;

	}
}