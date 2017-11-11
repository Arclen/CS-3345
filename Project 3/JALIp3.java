import java.util.*;
import java.io.*;
/*
 CS 3345
 Project 3
 javac 1.8.0_141-2-redhat
 java version "1.8.0_131"
 date 11/10/2017
*/
class JALIp3 {

  public static void main(String[] args) throws IOException{
    UnionFind uni = null;

    Scanner in = new Scanner(new File("d1.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);				// for submission
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
          uni = new UnionFind((int) Math.pow(2, ((int) Math.pow(2,Integer.parseInt(tokens[1])))));
          uni.initEdges((int) Math.pow(2, ((int) Math.pow(2,Integer.parseInt(tokens[1])))));
          uni.genEdges();
          uni.printMaze();
        break;
        case "e":
          done = true;
        break;
      }
    }

  }

  public static class UnionFind {

    int[] contents;
    static double[] paths;
    static int tracker;
    static double path;
    int[][] edges;

    UnionFind(int n) {
      tracker = 0;
      path = 0.5;
      paths = new double[999999];
      contents = new int[n];
      for(int i=0; i<n; i++)
        contents[i] = -1;
    }

    void union(int x, int y) {
      if(x == y)
        System.out.println("lmao");
      else if(contents[x] == y) {
        System.out.println(x + " " + (-1*contents[find(y)]));
      }
      else if(contents[y] == x) {
        System.out.println(x + " " + (-1*contents[find(x)]));
      }
      else if(contents[x] == -1 && contents[y] == -1) { // See if they are both unconnected
        contents[y] = find(x);
        contents[x]--;
        System.out.println(x + " " + (-1*contents[x]));
      }
      else {
        if(contents[x] != -1 && contents[y] == -1) {
          contents[y] = find(x);
          contents[find(x)]--;
          System.out.println(find(x) + " " + (-1*contents[find(x)]));
        }
        else if(contents[x] == -1 && contents[y] != -1) {
          contents[x] = find(y);
          contents[find(y)]--;
          System.out.println(find(y) + " " +  (-1*contents[find(y)]));
        }
        else if(contents[find(x)] < -1 || contents[find(y)] < -1) {
          if(contents[find(x)] > contents[find(y)]) // If y has the bigger subtree
          {
            contents[find(y)] += contents[find(x)];
            contents[find(x)] = find(y);
            contents[x] = find(y);
            System.out.println(find(contents[x]) + " " +  (-1*contents[find(x)]));
          }
          else { // If x has the bigger subtree or it is the same size as y
            contents[find(x)] += contents[find(y)];
            contents[find(y)] = find(x);
            contents[y] = find(x);
            System.out.println(find(contents[y]) + " " + (-1*contents[find(y)]));
          }
        }
      }
    }

    int find(int y) {
      if(contents[y] < 0)
      {
        paths[tracker] = path;
        tracker++;
        path = 0;
        return y;
      }
      else {
        path += 1.0;
        return find(contents[y]);
      }
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
        System.out.printf("Mean path length in find = %6.2f\n", avgPath());
    }

    double avgPath() {
      int i=0;
      double tot = 0.0;
      while(paths[i] != 0) {
        tot += paths[i];
        i++;
        System.out.print("Z");
      }
      return tot/i;
    }
    void printSets() {
      for(int i: contents) {
        System.out.print(i + " ");
      }
      System.out.print("\n");
    }

    void initEdges(int dim) {
      edges = new int[dim][dim];
      for(int i=0; i<dim; i++)
        for(int j=0; j<dim; j++)
          edges[i][j] = 0;
    }

    void genEdges() {
       while(numberOfSets() > 1) {
         int n,m;
         n = (int)(Math.random() * ((edges.length - 1))) +1;
         m = (int)(Math.random() * ((edges.length - 1))) +1;
         if(n != m && edges[n][m] == 0) {
           union(n,m);
           edges[n][m] = (int)(Math.random() * 2) +1;
         }
       }
    }

    void printMaze() {
      for(int i=0; i<edges.length; i++) {
        int numEdges = 0;
        System.out.print("\n");
        for(int j=0; j<edges[0].length; j++) {
          if(edges[i][j] != 0)
            numEdges++;
        }
        System.out.print(numEdges + " ");
        for(int j=0; j<edges[0].length; j++) {
          if(edges[i][j] != 0)
            System.out.print(edges[i][j]);
        }
      }
      System.out.print("\n");
    }

  }
}
