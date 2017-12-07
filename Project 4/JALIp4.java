// version: java 1.8.0_131
// compile command: javac JALIp4.java
import java.util.*;
import java.io.*;
/*
 CS 3345
 Project 4
 javac 1.8.0_141-2-redhat
 java version "1.8.0_131"
 date 12/6/2017
*/
class JALIp4 {

  /*
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  DO NOT FORGET TO SWITCH TO SUBMISSION MODE
  */

  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(new File("d1.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);				// for submission

    ArrayList<Node> nodes = new ArrayList<Node>();
    ArrayList<Edge> edges = new ArrayList<Edge>();
    Queue<Node> q = new LinkedList<Node>();

    int line = 1;
    int start = 0;
    int end = 0;

    while(in.hasNextLine()) {
      String read = in.nextLine();
      String [] tokens = read.split(" ");

      if(line == 1) {
        //System.out.println("The graph will have " + Integer.parseInt(read) + " vertices");
        for(int i=0; i<Integer.parseInt(read); i++) {
          nodes.add(new Node(i+1));
        }

      }

      else if(line == 2) {
        start = Integer.parseInt(tokens[0]);
        end = Integer.parseInt(tokens[1]);
        //System.out.println("The path will start at Node " + nodes.get(start-1).getIndex() + " and end at Node " + nodes.get(end-1).getIndex());
      }

      else {
        edges.add(new Edge(nodes.get(Integer.parseInt(tokens[0])-1), nodes.get(Integer.parseInt(tokens[1])-1), Integer.parseInt(tokens[2])));
      }

      line++;
    }

    // for(Edge e: edges) {
    //   e.printEdge();
    // }

    SPFA(nodes, edges, nodes.get(start), nodes.get(end));
    longestPath(nodes, edges, nodes.get(start), nodes.get(end));
  }


  public static class Node {
    ArrayList<Node> neighbors;
    private int index;
    private int dist;

    public Node(int i) {
      index = i;
    }

    public int getIndex() {
      return index;
    }

    public void setDist(int d) {
      dist = d;
    }

    public int getDist() {
      return dist;
    }
  }

  public static class Edge {
    private Node start;
    private Node end;
    private int weight;

    public Edge(Node s, Node e, int w) {
      start = s;
      end = e;
      weight = w;
    }

    public void printEdge() {
      System.out.println("Starts at " + start.getIndex() + " and end at " + end.getIndex() + " with weight " + weight);
    }

    public Node getStart() {
      return start;
    }

    public Node getEnd() {
      return end;
    }

    public int getWeight() {
      return weight;
    }
  }

  public static void SPFA(ArrayList<Node> nodes, ArrayList<Edge> edges, Node start, Node end) {
    Queue<Node> q = new LinkedList<Node>();
    boolean[] cont = new boolean[nodes.size()];
    int pl = 0;
    Arrays.fill(cont, false);
    for(Node n: nodes) {
      n.setDist(1000000);
    }
    start.setDist(0);
    q.add(start);
    while(q.size() != 0) {
      // System.out.println(q.peek().getIndex() + " ");
      Node u = q.poll();
      for(Edge e: edges) {
        if(e.getStart().getDist() + e.getWeight() < e.getEnd().getDist()) {
          e.getEnd().setDist(e.getStart().getDist() + e.getWeight());
          // if(e.getEnd() == end) {
          //   pl = e.getEnd().getDist();
          //   for(Node no: q)
          //     System.out.print(no.getIndex() + " ");
          //   System.out.println(pl);
          // }
          if(!cont[e.getEnd().getIndex()-1]) {
            q.add(e.getEnd());
            cont[e.getEnd().getIndex()-1] = true;
          }
        }
      }
    }
    System.out.print(end.getDist());
  }

  public static void longestPath(ArrayList<Node> nodes, ArrayList<Edge> edges, Node start, Node end) {
    System.out.println("I'd buy that for a dollah");
  }

}
