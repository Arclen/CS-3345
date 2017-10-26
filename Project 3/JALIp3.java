import java.util.*;
import java.io.*;
/*
 CS 3345
 Project 3
 javac 1.8.0_141-2-redhat
 java version "1.8.0_131"
 @author Jacob Liou
 date 11/10/2017
*/
class JALIp3 {

  public static void main(String[] args) {
    UnionFind uni = null;

    // Scanner in = new Scanner(new File(".txt"));	// for testing
	  Scanner in = new Scanner(System.in);									// for submission
    boolean done = false;

    while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			switch(tokens[0]) {
        case "d":
          uni = new UnionFind(Integer.parseInt(tokens[1]));
        break;
        case "u":
          uni.union(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        break;
        case "f":
          System.out.println(uni.find(Integer.parseInt(tokens[1])));
        break;
        case "p":
          uni.printSets();
        break;
        case "s":
          System.out.println("nice");
        break;
        case "m":
          System.out.println("how tf am i gonna do this");
        break;
        case "e":
          done = true;
        break;
      }
    }

  }

  public static class UnionFind {

    int[] contents;

    UnionFind(int n) {
      contents = new int[n];
      for(int i=0; i<n; i++)
        contents[i] = -1;
    }
    void union(int x, int y) {

    }
    int find(int y) {
      return 0;
    }
    int numberOfSets() {
      return 69;
    }
    void printStats() {

    }
    void printSets() {
      for(int i: contents) {
        System.out.print(i + " ");
      }
      System.out.print("\n");
    }
  }
}
