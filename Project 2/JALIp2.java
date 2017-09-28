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
    Scanner in = new Scanner(new File("d2.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);									// for submission

    System.out.println("Hello");
    BST bst = new BST();
    boolean done = false;

    while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			switch(tokens[0]) {
        case "A":
          bst.insert(Integer.parseInt(tokens[1]));
        break;
        case "D":
          bst.delete(Integer.parseInt(tokens[1]));
        break;
        case "F":
          bst.search(Integer.parseInt(tokens[1]));
        break;
        case "S":

        break;
        case "B":

        break;
        case "Z":

        break;
        case "R":

        break;
        case "E":
          done = true;
        break;
      }
    }
  }

  public class TreeNode {
    int key;
    TreeNode leftChild;
    TreeNode rightChild;

    TreeNode() {

    }
  }

  public static class BST {
    TreeNode root;
    int numkeys;

    BST() {

    }

    void makeEmpty() {

    }

    boolean insert(int key) {
      System.out.println("Key " + key + " inserted");
      return true;
    }

    boolean delete(int key) {
      return false;
    }

    boolean search(int key) {
      return true;
    }

    void printTreeBF(int key) {

    }

    boolean splay(int key) {
      return false;
    }
  }
}
