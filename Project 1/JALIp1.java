import java.util.*;
import java.io.*;
/*
 CS 3345
 Project 1
 javac version "1.8.0_141-2-redhat"
 java version "1.8.0_131"
 @author Jacob Liou
 date 8/31/2017
*/

class JALIp1 {

	public static void main(String[] args) throws IOException{
	  Scanner in = new Scanner(new File("carsd1.txt"));
	  //Scanner in = new Scanner(System.in);
	  Totem marioKart = new Totem(-1);
    marioKart.insertAtTail(21);
		marioKart.insert(21, 0);
		marioKart.insert(44, 1);
		System.out.println(marioKart.getHead().getNext().getID());
		marioKart.insertAtTail(78);
		System.out.println(marioKart.getHead().getNext().getID());
		marioKart.insert(24,0);
		marioKart.remove(24);
		System.out.println(marioKart.getHead().getNext().getID());
		marioKart.insert(55,1);
		marioKart.remove(55);
		System.out.println(marioKart.getHead().getNext().getID());

		boolean done = false;
		while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			switch(tokens[0]) {
				case "START":
					for(int i=2; i <= Integer.parseInt(tokens[1])+2; i++)
						System.out.print(tokens[i] + " ");
					System.out.print("\n");
				break;
				case "DROPOUT":
					System.out.println("DROPOUT is working");
				break;
				case "OVERTAKE":
					System.out.println("OVERTAKE is working");
				break;
				case "PITSTOP":
					System.out.println("PITSTOP is working");
				break;
				case "PITRETURN":
					System.out.println("PITRETURN is working");
				break;
				case "CRASH":
					System.out.println("CRASH is working");
				break;
				case "END":
					System.out.println("END is working");
					done = true;
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
			private int size;

			public Totem(int i) {
				tail = new Node(-2, null);
				head = new Node(-1, tail);
				size = 0;
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
							boolean atTail = false;
							while(!atTail) {
								if(ref.getNext().getNext() == tail) {
									Node temp = ref.getNext();
							    ref.putNext(new Node(ID, tail));
									temp.putNext(ref);
									System.out.println("Assigning new node");
									atTail = true;
								}
								ref = ref.getNext();
					  	}
						}
					}
			}

			// void insertAtTail(int ID) {
			// 	Node ref = new Node(ID, null);
			// 	size++;
			// 	if(head == null)
			// 		head = ref;
			// 	else {
			// 		tail.putNext(ref);
			// 		tail = ref;
			// 	}
			// }

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
				size--;
			}

			void insert(int ID, int posn) {
		    Node ins = new Node(ID, null);
				if(head == null)
					head = ins;
				Node ref = head;
				posn = posn -1;
				for(int i=1; i<size; i++) {
					if(i == posn) {
						Node temp = ref.getNext();
						ref.putNext(ins);
						ins.putNext(temp);
					}
					ref = ref.getNext();
				}
				size++;
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
