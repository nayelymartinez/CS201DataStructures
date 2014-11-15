/** 
* A class implementing a binary search tree. 
* The nodes stores a word and the number of times it appears in a text file.
* @author Professor Amy Csizmar Dalal and Nayely Martinez
* date: February 26, 2014
* CS 201 Data Structures, Winter 2014
**/

public class WordBST implements WordBSTInt {
	/**
	Node inner class.
	* Represents a word item and the number of times it has appeared in the text.
	*/
	public class Node {
		String word;
		int nAppeared;

		Node l;
		Node r;

		// Creates a node that takes in a word item and the number of times it appears in the text.
		public Node(String w) {
			word = w;
			nAppeared = 1;
			l = null;
			r = null;
		}
	}

	/**
	Word BST class.
	*/

	// Instance variable root
	private Node root;

	public WordBST() {
		root = null;
	}


	
	/**
     * Adds a word to the binary search tree. If the word already appears in the tree, update its count.
     * @param s The word to add to the tree.
     */
	public boolean insert(String s) {
		root = insert(s, root);
		return true;
	}

	// Helpder insert function. Returns the node that was inserted.
	public Node insert(String s, Node n) {
		// Create new node
		Node toInsert = new Node(s);

		// Special Case: If tree is empty, set the new node as the root.
		if (n == null) {
			n = toInsert;
		}

		// Base Case: If word already in tree, update frequency count and return the parent node, a.k.a. the root of subtree.
		else {
			if (n.word.equals(s)) {
				n.nAppeared++;
				return n;
			}
			// If root is greater than string toInsert, go left.
			else if (n.word.compareTo(s) > 0) {
				n.l = insert(s, n.l);
			}

			// Otherwise, go right
			else {
				n.r = insert(s, n.r);
			}
		}
		return n;
	}



	/**
     * Determine if the specified word is in the tree.
     * @param s The word to find in the tree.
     * @return  The current count for this word, or 0 if the word does not exist in the tree.
     */
	public int fetch(String s) {
		int count = fetch(s, root);
		return count;
	}

	// Helper fetch function. Returns the int frequency count of that node
	public int fetch(String s, Node n) {
		// Base Case: If the tree is empty, return 0 for frequency count
		if (n == null) {
			return 0;
		}
		else {
			// If root is greater than string being searched for, go left.
			if (n.word.compareTo(s) > 0) {
				return fetch(s, n.l);
			}
			// If root is less than string being searched for, go right.
			else if (n.word.compareTo(s) < 0) {
				return fetch(s, n.r);
			}
			// Otherwise return the count at the current node.
			else {
				return n.nAppeared;
			}
		}
	}



	/**
     * Traverses the tree, printing out all words in alphabetical order (and their frequencies) that appear at least the specified number of times.
     * @param thresh    The "threshold value" for the word frequency
     */

	public void printWords(int threshold) {
		printWords(threshold, root);
	}

	// Helper printWords function. Recursive call to traverse through BST. Prints word and frequency count.
    public void printWords(int threshold, Node n) {
    	if (n != null) {
    		// Traverse left
    		printWords(threshold, n.l);

    		// Print frequency count if above count is greater than threshold
    		if (n.nAppeared >= threshold) {
    			System.out.println("Word: " + n.word + " \nFrequency count: " + n.nAppeared);
    		}
    		// Traverse right
    		printWords(threshold, n.r);
    	}
    }
}