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

  public static void main(String[] args) throws IOException {
    // Scanner in = new Scanner(new File("d1.txt"));	// for testing
	  Scanner in = new Scanner(System.in);				// for submission

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
        for(int i=0; i<Integer.parseInt(read); i++) {
          nodes.add(new Node(i+1));
        }

      }

      else if(line == 2) {
        start = Integer.parseInt(tokens[0]);
        end = Integer.parseInt(tokens[1]);
      }

      else {
        edges.add(new Edge(nodes.get(Integer.parseInt(tokens[0])-1), nodes.get(Integer.parseInt(tokens[1])-1), Integer.parseInt(tokens[2])));
      }

      line++;
    }


    if(negCycExists(nodes, edges)) {
      System.out.println("Negative Cost Cycle in Graph");
    }
    else {
      SPFA(nodes, edges, nodes.get(start), nodes.get(end));
      longestPath(nodes, edges, nodes.get(start), nodes.get(end));
    }
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
    boolean breaker = false;
    Arrays.fill(cont, false);
    for(Node n: nodes) {
      n.setDist(10000000);
    }
    start.setDist(0);
    q.add(start);
    System.out.print(start.getIndex()-1 + " ");
    cont[0] = true;
    while(q.size() > 0) {
      Node u = q.poll();
      cont[u.getIndex()-1] = false;
      for(Edge e: edges) {
        if(u.getDist() + e.getWeight() < e.getEnd().getDist()) {
          e.getEnd().setDist(u.getDist() + e.getWeight());
          if(e.getEnd() == end || u == end) {
            pl += e.getEnd().getDist();
            for(Node no: q)
              System.out.print(no.getIndex() + " ");
            System.out.print(pl);
            System.out.println();
            breaker = true;
            if(breaker)
              break;
          }
          if(!cont[e.getEnd().getIndex()-1]) {
            q.add(e.getEnd());
            cont[e.getEnd().getIndex()-1] = true;
          }
        }
      }
      if(breaker)
        break;
    }
  }

  public static void longestPath(ArrayList<Node> nodes, ArrayList<Edge> edges, Node start, Node end) {
    Queue<Node> q = new LinkedList<Node>();
    boolean[] cont = new boolean[nodes.size()];
    int pl = 0;
    int counter = 0;
    Arrays.fill(cont, false);
    for(Node n: nodes) {
      n.setDist(10000000);
    }
    start.setDist(0);
    q.add(start);
    System.out.print(start.getIndex()-1 + " ");
    cont[0] = true;
    while(q.size() > 0) {
      Node u = q.poll();
      for(Edge e: edges) {
        if(u.getDist() + e.getWeight() < e.getEnd().getDist()) {
          e.getEnd().setDist(u.getDist() + e.getWeight());
          if(e.getEnd() == end) {
            counter++;
            pl += e.getEnd().getDist();
            if(counter > 1) {
              for(Node no: q)
                System.out.print(no.getIndex() + " ");
              System.out.print(pl);
              System.out.println();
            }
          }
          if(!cont[e.getEnd().getIndex()-1]) {
            q.add(e.getEnd());
            cont[e.getEnd().getIndex()-1] = true;
          }
        }
      }
    }
  }

  public static boolean negCycExists(ArrayList<Node> nodes, ArrayList<Edge> edges) {
    for(Edge e: edges) {
      if(e.getWeight() < 0)
        return true;
    }
    return false;
  }

}
