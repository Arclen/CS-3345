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
class JALIp2 {

	public static void main(String[] args) throws IOException{
    Scanner in = new Scanner(new File("d4.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);									// for submission

    BST bst = new BST();
    boolean done = false;
		int numInserts = 0;
		int numSplays = 0;
		int i = -1;
    while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			if(tokens.length == 2)
				i = Integer.parseInt(tokens[1]);
			switch(tokens[0]) {
        case "A":
					numInserts++;
					if(bst.insert(i)) {
						System.out.println("Key " + i + " inserted");
					}
					else
						System.out.println("Key " + i + " already exists");
        break;
        case "D":
          bst.delete(Integer.parseInt(tokens[1]));
        break;
        case "F":
					if(bst.search(i)) {
						System.out.println("Key " + i + " found");
					}
					else
						System.out.println("Key " + i + " not found");
        break;
        case "S":
					bst.splay(Integer.parseInt(tokens[1]));
        break;
        case "B":
					bst.printTreeBF();
        break;
        case "Z":
					System.out.println("The number of keys is " + bst.getNumkeys());
        break;
        case "R":
					bst.makeEmpty();
        break;
        case "E":
          done = true;
        break;
      }
    }
		System.out.println("Number of inserts: " + numInserts);
  }

  public static class TreeNode {
    int key;
    TreeNode leftChild;
    TreeNode rightChild;

    TreeNode(int k) {
			key = k;
    }

		void printKey() {
			System.out.println("The key is " + key);
		}

		int getKey() {
			return key;
		}

		TreeNode getLeftChild() {
			return leftChild;
		}

		TreeNode getRightChild() {
			return rightChild;
		}

		void setLeftChild(TreeNode n) {
			leftChild = n;
		}

		void setRightChild(TreeNode n) {
			rightChild = n;
		}
  }

  public static class BST {
    TreeNode root;
    int numkeys;

    BST() {
			numkeys = 0;
		}

    void makeEmpty() {

    }

    boolean insert(int key) {
    	boolean inserted = true;
			TreeNode ref = root;
			if(root == null) {
				root = new TreeNode(key);
				ref = null;
			}
			while(ref != null) {
				if(key == ref.getKey()) {
					inserted = false;
					break;
				}
				else if(key < ref.getKey()) {
					if(ref.getLeftChild() == null) {
						ref.setLeftChild(new TreeNode(key));
						break;
					}
					else ref = ref.getLeftChild();
				}
				else if(key > ref.getKey()) {
					if(ref.getRightChild() == null) {
						ref.setRightChild(new TreeNode(key));
						break;
					}
					else ref = ref.getRightChild();
				}
			}
			return inserted;
    }

    boolean delete(int key) {
      return false;
    }

    boolean search(int key) {
			boolean found = false;
			TreeNode ref = root;
			while(ref != null) {
				if(key == ref.getKey()) {
					found = true;
					break;
				}
				else if(key < ref.getKey()) {
					if(ref.getLeftChild() == null) {
						break;
					}
					else ref = ref.getLeftChild();
				}
				else if(key > ref.getKey()) {
					if(ref.getRightChild() == null) {
						break;
					}
					else ref = ref.getRightChild();
				}
			}
			return found;
    }

    void printTreeBF() {
			System.out.println("The tree is empty");
    }

    boolean splay(int key) {
			System.out.println("key " + key + " splayed");
      return false;
    }

		int getNumkeys() {
			return numkeys;
		}
  }
}
