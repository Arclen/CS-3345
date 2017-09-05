import java.util.*;
import java.io.*;
  /**
  * CS 3345 
  * Project 1
  * Used compiler Java 1.8.0_131
  * @author Jacob Liou
  * 9/1/17
  */

public class HW1 {
    public static void main(String[] args) {
      System.out.println("Node test: ");
      Node test_tail;
      test_tail = null;
      Node test_head = new Node(test_tail, 0);
      Node test_val = new Node(test_tail, 1);  
      test_head.putNext(test_val);
      List list = new List();
      //list.displayList();
      list.insertAtTail(3);
      list.insertAtTail(4);
      list.insertAtTail(5);
      list.insertAtTail(6);
      list.displayList();
    }

    static class Node {
      private Node next;
      private int key;

      Node(Node nxt, int keyValue) {   // constructor
        next = nxt;
        key = keyValue;
      }
      Node getNext() {
        return next;
      }
      int getKey() {
        return key;
      }
      void putNext(Node nxt) {
        next = nxt;
      }
    }

    static class List {      // assume the class does not use a dummy Node
    private Node head;
    List() {           // constructor
    }
    public void displayList() {
      Node n = head;
      System.out.print("List contents: ");
      while(n != null) {
        System.out.print(n.getKey() + " ");
        n = n.getNext();
      }
    }
    boolean exists(int ky) {     // returns true if ky is in the list 
      return true;
    }
    void insertAtHead(int ky) {  // inserts at the beginning of the list

    }
    void insertAtTail(int ky) { // inserts at the end of the list
      if(head == null)
        head = new Node(head, ky);
      else {
        Node temp = head;
        while(temp.getNext() != null) {
          temp = temp.getNext();
          System.out.print("debug");
        }
        temp.putNext(new Node(temp, ky));
      }
    }
    int removeFromHead() { // Returns -1 if the list is empty
      return 1;
    }
    void delete(int ky) { // delete the element or do nothing if ky doesn’t exist
      if(head != null) {
        if(head.getKey() == ky)
          head = head.getNext();  // remove first element
        else {
          Node ref = new Node(head, 0);
          while(ref != null && ref.getKey() != ky) {
            ref = ref.getNext();
          }
          if(ref != null)
            ref.putNext(ref.getNext());
        }
      }
    }
    int removeSmallest() {
      return 1;
    }
    int removeLargest() {
      return 1;
    }
    int maxElement() {
      return maxElement(head);
    }     
    int sum() {
      return sum(head);
    }            
    int length() {
      return length(head);
    }
    public void recursiveDelete(int ky) {
      recursiveDelete(ky, head);
    }         

    private int maxElement(Node x) {
      if(x != null) { // return -1 if list is empty
        if(x.getNext() == null) // end of list
          return x.getKey();
        else {
          int max = maxElement(x.getNext());
          if(max < x.getKey())
            return x.getKey();
          return max;
        }
      }
      return -1;
    }
    private int sum(Node x) {
      if(x != null) // return -1 if list is empty
      {
        if(x.getNext() == null) // end of list
          return x.getKey();
        return x.getKey() + sum(x.getNext());
      }
      return -1;
    }
    private int length(Node x) {
      if(x != null) { // return 0 if list is empty
        if(x.getNext() == null)
          return 1;
        else return 1 + length(x.getNext());
      }
      return 0;
    }
    private Node recursiveDelete(int ky, Node n) {
      return n;
    }

  }
}
