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

  public static void main(String[] args) throws IOException{
    UnionFind uni = null;

    Scanner in = new Scanner(new File("d1.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);									// for submission
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
          uni.printStats();
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
      if(contents[x] == -1 && contents[y] == -1) { // See if they are both unconnected
        contents[y] = x;
        contents[x]--;
        System.out.println(x + " " + (-1*contents[x]) + " first case");
      }
      else if(contents[x] < -1 || contents[y] < -1) {
        if(contents[x] > contents[y]) // If y has the bigger subtree
        {
          contents[x] = y;
          contents[y]--;
          System.out.println(contents[x] + " " +  (-1*contents[x]) + " second case");
        }
        else // If x has the bigger subtree or it is the same size as y
        {
          contents[y] = x;
          contents[x]--;
          System.out.println(contents[y] + " " + (-1*contents[y]) + " second case");
        }
      }
      else {
        if(contents[y] == -1) {
          contents[y] = contents[x];
          contents[y]--;
          System.out.println(contents[x] + " third case");
        }
        else {
          contents[x] = contents[y];
          contents[x]--;
          System.out.println(contents[y] + " third case");
        }
      }
    }

    int find(int y) {
      int point = 0;
      if(y == contents[point])
        return point;
      return 100;
    }

    int numberOfSets() {
      int num = 0;
      for(int i:contents)
        if(i < 0)
          num++;
      return num;
    }

    void printStats() {
        System.out.printf("Number of sets remaining =  %4d\n", numberOfSets());
        System.out.printf("Mean path length in find = %6.2f\n", 2342.2342342);
    }

    void printSets() {
      for(int i: contents) {
        System.out.print(i + " ");
      }
      System.out.print("\n");
    }

  }
}
