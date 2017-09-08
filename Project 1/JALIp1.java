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
	  Totem marioKart = new Totem();
    marioKart.insertAtTail(21);
    System.out.println("Welcome to your reckoning baybeeeeeee");
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

	void insertAtTail(int ID) {
	    Node ins = new Node(ID, head);
	    while(ins.getNext() != tail) {
		if(ins.getNext().getNext() == tail)
		    ins.getNext().putNext(ins);
		ins.putNext(ins.getNext());
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

	    }
	}
    }
}
