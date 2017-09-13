import java.util.*;
import java.io.*;
/*
 CS 3345
 Project 1
 Used compiler Java 1.8.0_131
 @author Jacob Liou
 date 8/31/2017
*/

class JALIp1 {

	public static void main(String[] args) throws IOException {
	  //Scanner in = new Scanner(new File("data1.txt"));
	  Scanner in = new Scanner(System.in);
	  Totem marioKart = new Totem(-1);
    marioKart.insertAtTail(21);
    System.out.println(marioKart.getHead().getNext().getID());

		boolean done = false;
		while(!done) {
			System.out.print("DEBUG ");
			String line = in.next();
			String [] tokens = line.split(" ");
			switch(tokens[0]) {
				case "START":

					break;
				case "DROPOUT":

					break;

			}
		}
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

	public Totem(int i) {
		head = new Node(-1, tail);
		tail = new Node(-1, null);
	}

	void insertAtTail(int ID) {
			if(head != null) {
				System.out.println("insertAtTail is working");
				Node ref = head;
				if(ref.getNext() == tail) {
					System.out.println("Ok this should not be triggered");
					ref.putNext(new Node(ID, tail));
				}
			  else {
					while(ref != tail) {
						System.out.print("It thinks that ref is in da middle of da list");
						if(ref.getNext() == tail)
					    ref.putNext(new Node(ID, tail));
						ref.putNext(ref.getNext());
			  	}
				}
			}
	}

	void remove(int ID) {
	    if(head != null) {
		Node looker = head;
		Node prev = null;
		while(looker != null && looker.getID() != ID) {
		    prev = looker;
		    looker = looker.getNext();
		}
		if(looker != null)
		    prev.putNext(looker.getNext());
	    }
	}

	void insert(int ID, int posn) {
	    if(head != null) {
				Node ins = new Node(ID, head.getNext());
				for(int i = 0; i < posn; i++)
					ins.putNext(ins.getNext());
	    }
	}

	void swap(int ID) {
		Node ref = head;
		if(head != null) {
			if(ref.getNext().getID() == ID) {
				Node temp = ref;

			}
		}
	}

	Node getHead() {
		return head;
	}
  }
}
