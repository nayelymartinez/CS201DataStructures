// This class defines a Stack that holds string objects.

import java.util.Scanner;

public class DLLStack implements StackInt {
	// Instance variables
	private StringDLList data;
	private String head;
	private String tail;
	
	// Constructor
	public DLLStack() {
		data = new StringDLList();
	}
	

	// Is the stack empty? Returns true if empty, false otherwise.
	public boolean isEmpty() {
		if (data.getHead() == data.getTail()) {
			return true;
		}
		return false;
	}


	// Clears all contents in the stack by replacing the current stack 'data' with a new stack 'data'.
	// Thank you to Daniel Alabi for suggesting the one-liner 'data' replacement instead of popping each string from the stack.
	public void clear() {
		data = new StringDLList();
	}


	// Pushes a string into the top of the stack and returns true once finished. No checks since there is no size limit in linked lists.
	public boolean push(String s) {
		String str = s;
		boolean successPush = data.insertAtHead(str);
		if (successPush) {
			return true;
		}
		else {
			return false;
		}
	}


	// Removes the string at the top of the stack and returns the string deleted or an error message.
	public String pop() {
		

		if (data.getHead() != data.getTail()) {
			String strDeleted = data.deleteHead();		// What happens if strDeleted returns null (if head and tail are empty)?
			return strDeleted;
		}
		else {
			return null;
		}
	}


	// Returns the value at the top of the stack without removing it from the stack.
	public String peek() {
		if (data.getHead() != data.getTail()) {
			return data.fetchHead();
		}
		else {
			return null;
		}
	}




	public static void main(String[] args) {
		DLLStack strStack = new DLLStack();

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Type in your HTML code here:");
		String input = keyboard.next();


		// **** METHOD CHECK ****

		// Test push method.
		System.out.println("Did the call push go through? \n" + strStack.push(input) + "\nPress Enter to continue.");
		keyboard.nextLine();		// Enter to continue thanks to: http://codewithdesign.com/2010/01/17/create-a-press-enter-to-continue-with-java/


		// *** If stack NOT empty: ***

		// isEmpty
		System.out.println("Is the stack empty? (Should print false)\n" + strStack.isEmpty());
		keyboard.nextLine();

		// Peek
		System.out.println("The string on the top of the stack is: \n" + strStack.peek());
		keyboard.nextLine();


		// Pop
		System.out.println("You popped the following string from the stack: \n" + strStack.pop());
		keyboard.nextLine();

		// Clear
		strStack.clear();
		System.out.println("The clear method does not return anything. Press Enter to continue.");
		keyboard.nextLine();


		
		// *** If stack IS empty: ***

		// isEmpty
		System.out.println("Is the stack empty? (Should print true)" + strStack.isEmpty());
		keyboard.nextLine();

		// Peek
		System.out.println("The next string in the stack is: \n" + strStack.peek());
		keyboard.nextLine();
		

		// Pop
		System.out.println("You popped the following string from the stack: \n" + strStack.pop());
		keyboard.nextLine();

		// Clear
		strStack.clear();
		System.out.println("The clear method does not return anything. Press Enter to continue.");
		keyboard.nextLine();	
	}	
	
}