/******************************************************************************
* A BTNode provides a node for a binary tree. Each node
* contains a piece of data (which is a reference to an object) and references
* to a left and right child. The references to children may be null to indicate
* that there is no child. The reference stored in a node can also be null.
*
******************************************************************************/
import java.io.*;

public class BTNode
{
   // Invariant of the BTNode class:
   //   1. Each node has one reference to an Object, stored in the instance
   //      variable data.
   //   2. The instance variables left and right are references to the node's
   //      left and right children.
   private Object data;
	
   private BTNode left;
   private BTNode right;
 
 
   private static BufferedWriter outBw;

   private static int counter = 0;
   private static String path = "0";


   /**
   * Initialize a BTNode with a specified initial data and links
   * children. Note that a child link may be the null reference,
   * which indicates that the new node does not have that child.
   * param initialData
   *   the initial data of this new node
   * param initialLeft
   *   a reference to the left child of this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * param initialRight
   *   a reference to the right child of this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * Postcondition:
   *   This node contains the specified data and links to its children.
   **/

   public BTNode(Object initialData, BTNode initialLeft, BTNode initialRight)
   {
      data = initialData;
      left = initialLeft;
      right = initialRight;
   }


   /**
   * Accessor method to get the data from this node.
   * param - none
   * return
   *   the data from this node
   **/

   public Object getData( )
   {
      return data;
   }


   /**
   * Accessor method to get a reference to the left child of this node.
   * param - none
   * return
   *   a reference to the left child of this node (or the null reference if there
   *   is no left child)
   **/

   public BTNode getLeft( )
   {
      return left;
   }


   /**
   * Accessor method to get the data from the leftmost node of the tree below
   * this node.
   * param - none
   * return
   *   the data from the deepest node that can be reached from this node by
   *   following left links.
   **/

   public Object getLeftmostData( )
   {
      if (left == null)
         return data;
      else
         return left.getLeftmostData( );
   }


   /**
   * Accessor method to get the data from the rightmost node of the tree below
   * this node.
   * param - none
   * return
   *   the data from the deepest node that can be reached from this node by
   *   following right links.
   **/

   public Object getRightmostData( )
   {
      if (left == null)
         return data;
      else
         return left.getRightmostData( );
   }


   /**
   * Accessor method to get a reference to the right child of this node.
   * param - none
   * return
   *   a reference to the right child of this node (or the null reference if there
   *   is no right child)
   **/

   public BTNode getRight( )
   {
      return right;
   }


   /**
   * Uses an inorder traversal to print the data from each node at or below
   * this node of the binary tree.
   * param - none
   * Postcondition:
   *   The data of this node and all its descendants have been writeen by
   *   System.out.println( ) using an inorder traversal.
   **/

   public void inorderPrint( )
   {                                // Student complete BTNode #1
      if (left != null){
         left.inorderPrint();
      }
      System.out.println(data);
      if (right != null){
         right.inorderPrint();
      }
   }


   /**
   * Accessor method to determine whether a node is a leaf.
   * param - none
   * return
   *   true (if this node is a leaf) or
   *   false (if this node is not a leaf.
   **/

   public boolean isLeaf( )
   {
      return (left == null) && (right == null);
   }



   /**
   * Remove the leftmost most node of the tree with this node as its root.
   * param - none
   * Postcondition:
   *   The tree starting at this node has had its leftmost node removed (i.e.,
   *   the deepest node that can be reached by following left links). The
   *   return value is a reference to the root of the new (smaller) tree.
   *   This return value could be null if the original tree had only one
   *   node (since that one node has now been removed).
   **/

   public BTNode removeLeftmost( )
   {
      if (left == null)
         return right;
      else
      {
         left = left.removeLeftmost( );
         return this;
      }
   }


   /**
   * Remove the rightmost most node of the tree with this node as its root.
   * param - none
   * Postcondition:
   *   The tree starting at this node has had its rightmost node removed (i.e.,
   *   the deepest node that can be reached by following right links). The
   *   return value is a reference to the root of the new (smaller) tree.
   *   This return value could be null if the original tree had only one
   *   node (since that one node has now been removed).
   **/

   public BTNode removeRightmost( )
   {
      if (right == null)
         return left;
      else
      {
         right = right.removeRightmost( );
         return this;
      }
   }

   /**
   * Modification method to set the data in this node.
   * param newData
   *   the new data to place in this node
   * Postcondition:
   *   The data of this node has been set to newData.
   **/

   public void setData(Object newData)
   {
      data = newData;
   }


   /**
   * Modification method to set the link to the left child of this node.
   * param newLeft
   *   a reference to the node that should appear as the left child of this node
   *  (or the null reference if there is no left child for this node)
   * Postcondition:
   *   The link to the left child of this node has been set to newLeft.
   *   Any other node (that used to be the left child) is no longer connected to
   *   this node.
   **/

   public void setLeft(BTNode newLeft)
   {
      left = newLeft;
   }


   /**
   * Modification method to set the link to the right child of this node.
   * param newLeft
   *   a reference to the node that should appear as the right child of this node
   *  (or the null reference if there is no right child for this node)
   * Postcondition:
   *   The link to the right child of this node has been set to newRight.
   *   Any other node (that used to be the right child) is no longer connected to
   *   this node.
   **/

   public void setRight(BTNode newRight)
   {
      right = newRight;
   }


 
   /**
   * Count the number of nodes in a binary tree.
   * param root
   *   a reference to the root of a binary tree (which may be
   *   an empty tree where source is null)
   * return
   *   the number of nodes in the binary tree
   * Note:
   *   A wrong answer occurs for trees larger than
   *   Long.MAX_VALUE.
   **/

   public static long treeSize(BTNode root)
   {
      if (root == null)
         return 0;
      else
         // ISTE-222: Student #2 count the right and left nodes, recursively.
         return 1 + treeSize(root.left) + treeSize(root.right);
   }


   /**
   * Reset counter and path for the next print session
   **/
   public void reset()
   {
      counter = 0;
      path = "0";
   }

// ==============================================================
   public static void setOutputFile (BufferedWriter outputBw )
   {
      outBw = outputBw;
   }


}