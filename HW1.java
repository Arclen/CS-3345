public class HW1 {
    public static void main(String[] args) {
      System.out.println("Node test: ");
      System.out.println("Linked list test: ");
    }

    class Node {
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

    class List {      // assume the class does not use a dummy Node
    private Node head;
    List() {           // constructor
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
          Node prev = new Node(head, null);
          while(prev.getNext() != null && prev.getNext().getKey() != ky) {
            
          }
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
      return 1;
    }     
    int sum() {
      return 1;
    }            
    int length() {
      return 1;
    }         

    private int maxElement(Node x) {
      return 1;
    }
    private int sum(Node x) {
      return 1;
    }
    private int length(Node x) {
      return 1;
    }
  }
}
