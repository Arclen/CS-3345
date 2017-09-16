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
	  Totem tot = new Totem();

		boolean done = false;
		boolean debug = false;
		while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			switch(tokens[0]) {
				case "START":
					for(int i=3; i <= Integer.parseInt(tokens[1])+2; i++) {
						// System.out.print(tokens[i-1] + " ");
						tot.insert(Integer.parseInt(tokens[i-2]), i-3);
					}
					System.out.println("\nRace order: ");
					if(Integer.parseInt(tokens[2]) == 1)
						debug = true;
				break;
				case "DROPOUT":
					tot.remove(Integer.parseInt(tokens[1]));
				break;
				case "OVERTAKE":
					tot.swap(Integer.parseInt(tokens[1]));
				break;
				case "PITSTOP":
				break;
				case "PITRETURN":
				break;
				case "CRASH":
				break;
				case "END":
					done = true;
				break;
			}
			if(debug) {
				System.out.print(tokens[0] + " ");
				tot.contents();
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

			public Totem() {
				tail = new Node(-2, null);
				head = new Node(-1, tail);
				size = 1;
			}

			void insertAtTail(int ID) {
					size++;
					if(head != null) {
						Node ref = head;
						if(ref.getNext() == tail) {
							ref.putNext(new Node(ID, tail));
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

			void delete(int ID) {
				if(head!=null) {
					if(head.getID()==ID) // the first Node contains ky
						head = head.getNext();
					else {
						Node x = head;
						while(x.getNext()!=null) {
							if(x.getNext().getID()==ID)
								x.putNext(x.getNext().getNext());
						}
					} // end of else
				}
			} // end of delete


			void swap(int ID) {
				Node ref = head;
				if(head != null) {
					if(ref.getNext().getID() == ID) {
						Node temp = ref;
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

			void contents() {
				Node ref = head;
				// for(int i=0; i<size; i++) {
				// 	System.out.print(ref.getNext().getID()+ " ");
				// 	ref = ref.getNext();
				// }
				while(ref.getNext() != null) {
					System.out.print(ref.getNext().getID()+ " ");
					ref = ref.getNext();
				}
				System.out.print("\n");
			}
	  }
}
