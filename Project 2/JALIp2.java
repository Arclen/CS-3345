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
    Scanner in = new Scanner(new File("d10.txt"));	// for testing
	  // Scanner in = new Scanner(System.in);									// for submission

    BST bst = new BST();
    boolean done = false;
		int numInserts = 0;
		int numDeletes = 0;
		double numSearches = 1.0;
		double numSplays = 1.0;
		int i = -1;
		boolean rFlag = false;
    while(!done) {
			String line = in.nextLine();
			String [] tokens = line.split(" ");
			if(tokens.length == 2)
				i = Integer.parseInt(tokens[1]);
			switch(tokens[0]) {
        case "A":
					numInserts++;
					if(bst.insert(i) && !rFlag) {
						System.out.println("Key " + i + " inserted");
					}
					else
					if(!rFlag)
						System.out.println("Key " + i + " already exists");
        break;
        case "D":
					numDeletes++;
					if(bst.delete(i) && !rFlag) {
						System.out.println("Key " + i + " deleted");
					}
					else
					if(!rFlag)
						System.out.println("Key " + i + " not found");
        break;
        case "F":
					numSearches++;
					if(bst.search(i) && !rFlag) {
						System.out.println("Key " + i + " found");
					}
					else
						if(!rFlag)
							System.out.println("Key " + i + " not found");
        break;
        case "S":
					numSplays++;
					bst.splay(Integer.parseInt(tokens[1]));
        break;
        case "B":
					bst.printTreeBF();
					System.out.println();
        break;
        case "Z":
					System.out.println("The number of keys is " + bst.getNumkeys());
        break;
        case "R":
					rFlag = true;
        break;
        case "E":
          done = true;
        break;
      }
    }
		System.out.println("Number of inserts: " + numInserts);
		System.out.println("Number of deletes: " + numDeletes);
		System.out.println("Number of splays: " + (int)numSplays);
		System.out.println("The total number of nodes probed during searches: " + bst.nodesProbed);
		System.out.print("The average number of nodes probed during searches: ");
		System.out.printf("%.2f", (double)bst.nodesProbed/numSearches);
		System.out.println("\nThe total number of rotations during splays: " + bst.numRotations);
		System.out.print("The average number of rotations during splays: ");
		System.out.printf("%.2f", (double)bst.numRotations/numSplays);
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
		public static int nodesProbed = 0;
		public static int numRotations = 0;

    BST() {
			numkeys = 0;
		}

    void makeEmpty() {
			root = null;
    }

    boolean insert(int key) {
    	boolean inserted = true;
			TreeNode ref = root;
			if(root == null) {
				root = new TreeNode(key);
				ref = null;
			}
			while(ref != null) {
				if(key == ref.key) {
					inserted = false;
					break;
				}
				else if(key < ref.key) {
					if(ref.leftChild == null) {
						ref.setLeftChild(new TreeNode(key));
						break;
					}
					else ref = ref.leftChild;
				}
				else if(key > ref.key) {
					if(ref.rightChild == null) {
						ref.setRightChild(new TreeNode(key));
						break;
					}
					else ref = ref.rightChild;
				}
			}
			return inserted;
    }

    boolean delete(int key) {
			if(remove(key, root) != null)
				return true;
			return false;
    }

		private TreeNode remove(int x, TreeNode t) {
			 if( t == null )
					 return t;   // Item not found; do nothing
			 int compareResult = x - t.key;
			 if( compareResult < 0 )
					 t.leftChild = remove( x, t.leftChild );
			 else if( compareResult > 0 )
					 t.rightChild = remove( x, t.rightChild );
			 else if( t.leftChild != null && t.rightChild != null ) // Two children
			 {
					 t.key = minNode(t.rightChild ).key;
					 t.rightChild = remove( t.key, t.rightChild );
			 }
			 else
					 t = ( t.leftChild != null ) ? t.leftChild : t.rightChild;
			 return t;
	 	}

		private TreeNode maxNode(TreeNode node) {
			if(node != null )
				while( node.rightChild != null )
					node = node.rightChild;
			return node;
		}
		private TreeNode minNode(TreeNode node) {
			if(node != null )
				while( node.leftChild != null )
					node = node.leftChild;
			return node;
		}

    boolean search(int key) {
			boolean found = false;
			TreeNode ref = root;
			while(ref != null) {
				nodesProbed++;
				if(key == ref.getKey()) {
					found = true;
					nodesProbed-=3;
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
			Queue<TreeNode> currentLevel = new LinkedList<>();
			Queue<TreeNode> nextLevel = new LinkedList<>();
			currentLevel.add(root);
			while(!currentLevel.isEmpty()) {
				TreeNode ref = currentLevel.remove();
				if(ref != null) {
					System.out.print(ref.key + " ");
					if(ref.leftChild != null)
						nextLevel.add(ref.leftChild);
					if(ref.rightChild != null)
						nextLevel.add(ref.rightChild);
				}
				if(currentLevel.isEmpty() && (ref.getLeftChild() != null || ref.getRightChild() != null)) {
					System.out.print("\n");
					Queue<TreeNode> temp = new LinkedList<>();
					currentLevel = temp;
					currentLevel = nextLevel;
					nextLevel = temp;
				}
			}

    }

    boolean splay(int key) {
			TreeNode refParent = new TreeNode(-1);
			TreeNode ref = root;
			if(ref == null)
				return false;
			if(ref.key == key) {
				if(ref.getLeftChild() == null && ref.getLeftChild() == null) { // The root has no children
					root = null;
				}
				else if(ref.getLeftChild() != null && ref.getLeftChild() != null) { // The root has two children
					root = minNode(ref.rightChild);
					root.setRightChild(ref.rightChild);
					root.setLeftChild(ref.leftChild);
					key = minNode(ref.rightChild).getKey();
				}
				else {
					if(ref.getLeftChild() != null) { // The root has one child
						root = maxNode(ref.leftChild);
						root.setLeftChild(ref.leftChild);
						key = minNode(ref.rightChild).getKey();
					}
					else {
						root = minNode(ref.rightChild);
						root.setRightChild(ref.rightChild);
						key = minNode(ref.rightChild).getKey();
					}
				}
			}
			while(ref != null) {
				numRotations++;
				if(key == ref.key) {
					break;
				}
				else if(key < ref.key) {
					if(ref.leftChild == null) {
						break;
					}
					else {
						refParent = ref;
						ref = ref.leftChild;
					}
				}
				else if(key > ref.key) {
					if(ref.rightChild == null) {
						break;
					}
					else {
						refParent = ref;
						ref = ref.rightChild;
					}
				}
			}

			return true;
    }

		int getNumkeys() {
			return numkeys;
		}
  }
}
