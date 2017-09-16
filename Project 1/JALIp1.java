import java.util.*;
import java.io.*;
/*
 CS 3345
 Project 1
 javac 1.8.0_141-2-redhat
 java version "1.8.0_131"
 @author Jacob Liou
 date 8/31/2017
*/

class JALIp1 {

	public static void main(String[] args) throws IOException{
	  Scanner in = new Scanner(new File("carsd1.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);									// for submission

	  Totem tot = new Totem();
		boolean debug = false;		// Whether or not to output the car positions after every input
		boolean done = false;			// I made a variable called done and set it to false
															// as opposed to making a variable called notDone
															// and setting it to true because I am a rebel

		while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			switch(tokens[0]) {
				case "START":	// Initialize the linked list
					for(int i=3; i <= Integer.parseInt(tokens[1])+4; i++) {
						tot.insert(Integer.parseInt(tokens[i-2]), i-3);
					}
					if(Integer.parseInt(tokens[2]) == 1)
						debug = true;
				break;
				case "DROPOUT":	// Remove car based on ID
					tot.remove(Integer.parseInt(tokens[1]));
				break;
				case "OVERTAKE":	// A car with certain ID passes the one in front of it
					tot.swap(Integer.parseInt(tokens[1]));
				break;
				case "PITSTOP":	// Car vanishes from the race like the Heart of Gold starship
					tot.remove(Integer.parseInt(tokens[1]));
				break;
				case "PITRETURN":	// Car which left earlier appears out of nowhere like the Heart of Gold starship
					tot.insert(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])+2);
				break;
				case "CRASH":	// Car crashes like the Heart of Gold starship
					for(int i=1; i<tokens.length; i++) {
							 tot.remove(Integer.parseInt(tokens[i]));
					}
				break;
				case "END":
					done = true;
				break;
			}
			if(debug) {
				tot.contents();
			}
		}
		if(!debug)
			tot.contents();
	}
  static class Node {
		private int ID;
		private Node next;

		Node(int ID, Node nxt) {
		    this.ID = ID;
		    next = nxt;
		}

		Node getNext() {
		    return next;
		}

		void putNext(Node nxt) {
		    next = nxt;
		}

		int getID() {
		    return ID;
		}

		void putID(int val) {
		    ID = val;
		}
	}
  static class Totem {
		private Node head, tail;
		private int size;

		public Totem() {
			tail = new Node(-2, null);
			head = new Node(-1, tail);
			size = 1;
		}

		void insertAtTail(int ID) {
				size++;
				if(head != null) { // Make sure the totem isn't null
					Node ref = head;
					if(ref.getNext() == tail) {
						ref.putNext(new Node(ID, tail)); // If the totem is just head and tail, put the node in between them
					}
				  else {
						boolean atTail = false;
						while(!atTail) {
							if(ref.getNext().getNext() == tail) {
								Node temp = ref.getNext();
						    ref.putNext(new Node(ID, tail));
								temp.putNext(ref);
								atTail = true;
							}
							ref = ref.getNext();
				  	}
					}
				}
		}

		void insert(int ID, int posn) {
			Node ins = new Node(ID, null);
			if(head.getNext() == tail) // The totem is empty
				head = ins;
			Node ref = head;
			posn = posn-1;
			for(int i=1; i<size; i++) {
				if(i == posn) {
					Node temp = ref.getNext();	// Hold onto the new Node's future rear neighbor
					ref.putNext(ins);						// Place the new Node behind the reference
					ins.putNext(temp);					// Put temp behind the new Node
				}
				ref = ref.getNext();
			}
			size++;
		}

		void remove(int ID) {
	    if(head != null) {
				Node ref = head;
				Node prev = null;
				while(ref != null && ref.getID() != ID) {	// Iterate through the list until ID is found
				    prev = ref;
				    ref = ref.getNext();
				}
				if(ref != null)
				    prev.putNext(ref.getNext());	// Now prev points to what ref points to, cutting out ref
	    }
			size--;
		}

		void swap(int ID) {
			Node ref = head;
			if(head != null) {
				while(ref.getNext().getNext() != null) {
					if(ref.getNext().getNext().getID() == ID) {	// There are 4 nodes involved in swap
						Node temp1 = ref.getNext();								// 3 refernces must be changed to preserve
						Node temp2 = ref.getNext().getNext();			// order and prevent an infinite loop
						Node temp3 = ref.getNext().getNext().getNext();
						ref.getNext().getNext().putNext(temp1);
						ref.getNext().putNext(temp3);
						ref.putNext(temp2);
					}
					ref = ref.getNext();
				}
			}
		}

		int getSize() {
			return size;
		}

		Node getHead() {
			return head;
		}

		void contents() { // Print out the contents of the list, for debug and the end of the race
			Node ref = head;
			while(ref.getNext() != null) {
				System.out.print(ref.getNext().getID()+ " ");
				ref = ref.getNext();
			}
			System.out.print("\n");
		}
  }
}
