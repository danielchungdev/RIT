// FILE: Animal.java
// This animal-guessing program illustrates the use of the binary tree node class.
import java.io.*;
import java.text.*;
import java.util.*;
/******************************************************************************
* The Animals Java application illustrates the use of
* the binary tree node class is an animal-guessing game.
*
* Modified for ISTE-222 homework exercise by Michael Floeser
* Based on example by book's author Michael Main
*
******************************************************************************/

public class Animal
{
   /**
   * The main method prints instructions and repeatedly plays the
   * animal-guessing game. As the game is played, the taxonomy tree
   * grows by learning new animals.
   **/
   static  BufferedReader  inBr;
   static  BufferedWriter  outBw;
   static  BTNode          root = null;
   final static String DELIMITER = "|";
   final static Scanner scan = new Scanner(System.in);

   // If Animal.txt exists, use it. If not use the 
   final static String ANIMAL_OUT = "Animal.txt";
   static String ANIMAL_FILE  = 
         (new File(ANIMAL_OUT).exists() ? ANIMAL_OUT:"Animal.in");   // "Animal copy.txt"
    

   public static void main(String[ ] args)
   {
      new Animal();
   }
   public Animal(){   
      BTNode root = null;
      BTNode rootCopy = null;
      
      char ans;
   
      instruct( );
      
      // create the tree we will use
      root = beginningTree( );
      
      do
      {
         play(root);
         System.out.print("\nShall we play again?");
         ans = scan.nextLine().charAt(0);
      } while (Character.toLowerCase(ans) == 'y');
   
      System.out.println("Thanks for teaching me a thing or two.");
   
   ////////////////////////////////////////////////////////////////
   // Program is all done, now write out the animal file for next time
   
      System.out.println("\n\nTreeSize = " + root.treeSize(root) );
   
      try
      {
         outBw = new BufferedWriter( new FileWriter( ANIMAL_OUT ));
         depthPrint( root, 1 );
         outBw.flush();
         outBw.close();
      }
      catch ( Exception e )
      {
         System.out.println("Error with BufferedWriter: "+ e.getMessage() );
      }
   
   
   ////////////////////////////////////////////////////////////////
   // Program is all done, now write out the animal file for next time
   
      BTNode.setOutputFile( outBw );
      
      System.out.println("\n\nInOrder print:");
      root.reset();
      root.inorderPrint();
   
   }  // end of main()


   /**
   * Print instructions for the animal-guessing game.
   **/

   public static void instruct( )
   {
      System.out.println("1. Think of an animal.");
      System.out.println("2. Answer the Questions truthfully");
      System.out.println("3. I will guess what your animal.");
   }  // end of instruct()


   /**
   * Play one round of the animal guessing game.
   * current
   *   a reference to the root node of a binary taxonomy tree that will be
   *   used to play the game.
   *Postcondition:
   *   The method has played one round of the game, and possibly
   *   added new information about a new animal.
   **/
   public static void play(BTNode current)
   {
      while (!current.isLeaf( ))
      {
         System.out.print( (String) current.getData() ) ; 
         if (Character.toLowerCase(scan.nextLine().charAt(0)) == 'y')
            current = current.getLeft( );    
         else
            current = current.getRight( );  
      }
      
      
      System.out.print("My guess is " + current.getData( ) + ". ");
      System.out.print("Am I right?");
      if (Character.toLowerCase(scan.nextLine().charAt(0)) == 'n')
         learn(current); 
      else
         System.out.println("I knew it all along!");
   
   }  // end of play()

   public  static BTNode beginningTree()
   {
      final String DELIMITER = "|";
      BTNode newNode;
      BTNode node;
      String count;      // 1st string from file
      int countNum;      // 'count' as a number from file
      String path;
      String name;
      StringTokenizer inToken;
      String inLine;
   
      try
      {
         inBr  = new BufferedReader( new FileReader( ANIMAL_FILE ));
      
         while (( inLine = inBr.readLine()) != null)
         {
            inToken = new StringTokenizer( inLine, DELIMITER );
            count    = inToken.nextToken().trim();
            countNum = Integer.parseInt( count );
            
            name     = inToken.nextToken().trim();
            
         
          // If this is first, create the root node
            if ( countNum == 1 )
               root = new BTNode( name, (BTNode) null, (BTNode) null );
            else  // Otherwise, find where the node is and place it
            {
               // Create a new node with the text read
               newNode = new BTNode( name, (BTNode) null, (BTNode) null );
            
               // Return the node where this newNode should be attached
               node = placeNode( countNum / 2 );
               
               // If this countNum number was even, attach the newNode to the left 
               if ( countNum%2 == 0 )
                  node.setLeft( newNode );
               else  // if countNum is odd, attach to the right
                  node.setRight( newNode );
            }
         
         } // end of while()
      
         if (inBr != null)
            inBr.close();
      }
      catch ( FileNotFoundException e)
      {
         System.out.println("File not found: " + ANIMAL_FILE);
         System.out.println("Error: "+ e.getMessage() );
      }
      catch ( IOException e)
      {
         System.out.println("Caught IO error: "+ e.getMessage() );
      }
   
      return root;
   }  // end beginning tree


    // ////////////////////////////////////////////////////////////
    // placeNode will find the parent of a node, given its node number
    // in a level order traversal (breadth order) format.

   public static BTNode placeNode( int nodeNum )
   {
      BTNode node;
   
      // find my way down to root putting the nodeNum
      // on the recursive stack as we go.
   
      if ( nodeNum / 2 > 0 )
         node = placeNode( nodeNum / 2 );
      else
         return root;
   
      // came back from finding root, and using nodeNum can find
      // way to right or left
   
      if ( (nodeNum % 2) == 0 ) // left
         return node.getLeft();
      else                      // right
         return node.getRight();
   
   }  // end placeNode ( nodeNum )

   /**
   * Construct a small taxonomy tree with four animals.
   * param - none
   * return
   *   a reference to the root of a taxonomy tree with the animals:
   *   kangaroo, mouse, trout, robin.
   * exception OutOfMemoryError
   *   Indicates that there is insufficient memory to create the tree.
   **/
   public static BTNode beginningTree2( )
   {
      BTNode root;
      BTNode child;
   
   // Question #2
   // Draw the binary tree construct after this method is executed.
   
      final String ROOT_QUESTION = "Are you a mammal?";
      final String LEFT_QUESTION = "Are you bigger than a cat?";
      final String RIGHT_QUESTION = "Do you live underwater?";
      final String ANIMAL1 = "Kangaroo";
      final String ANIMAL2 = "Mouse";
      final String ANIMAL3 = "Trout";
      final String ANIMAL4 = "Robin";
   
      // Create the root node with the question Are you a mammal?
      root = new BTNode(ROOT_QUESTION, null, null);
   
      // Create and attach the left subtree.
      child = new BTNode(LEFT_QUESTION, null, null);
      child.setLeft(new BTNode(ANIMAL1, null, null));
      child.setRight(new BTNode(ANIMAL2, null, null));
      root.setLeft(child);
   
      // Create and attach the right subtree.
      child = new BTNode(RIGHT_QUESTION, null, null);
      child.setLeft(new BTNode(ANIMAL3, null, null));
      child.setRight(new BTNode(ANIMAL4, null, null));
      root.setRight(child);
   
      return root;
   
   }  // end of beginningTree()


   /**
   * Elicits information from the user to improve a binary taxonomy tree.
   * param current
   *   a reference to a leaf node of a binary taxonomy tree
   * Precondition:
   *   current is a reference to a leaf in a binary taxonomy tree
   * Postcondition:
   *   Information has been elicited from the user, and the tree has
   *   been improved.
   * exception OutOfMemoryError
   *   Indicates that there is insufficient memory to add new
   *   information to the tree.
   **/

   public static void learn(BTNode current)
   
   // Precondition: current is a reference to a leaf in a taxonomy tree. This
   // leaf contains a wrong guess that was just made.
   //
   // Postcondition: Information has been elicited from the user, and the tree
   // has been improved.
   
   {
      String guessAnimal;   // The animal that was just guessed
      String correctAnimal; // The animal that the user was thinking of
      String newQuestion;   // A question to distinguish the two animals
   
      // Set Strings for the guessed animal, correct animal and a new question.
      guessAnimal = (String) current.getData( );
   
      System.out.print("I give up. What are you? ");
      correctAnimal = scan.nextLine();
   
      System.out.println("Please type a yes/no question that will distinguish a");
      System.out.println(correctAnimal + " from a " + guessAnimal + ".");
   
      System.out.println("Your question: ");
      newQuestion = scan.nextLine();
   
   	// If there is no ? on the end of the line, add one.
      newQuestion = newQuestion + (newQuestion.trim().endsWith("?") ? "":"?");
   
      // Put the new question in the 'current' node, and add two new children
      // QUESTION #1: Why are we changing the animal to a question?
      current.setData(newQuestion);
   	
      System.out.println("As a " + correctAnimal + ", " + newQuestion);
      System.out.println("Please answer");
   
   // QUESTION #2 - Why do we need to test for 'y' or 'n' in the following?
   // What is the following "if statement" code doing? Add comments here
   // A: Regardless of what the user input it's going to be verified if it's a
   //y   
   
      // QUESTION #3: Add the 'correctAnimal' and 'guessAnimal' to the tree
      
      if ( Character.toLowerCase( scan.nextLine().charAt(0) ) == 'y' )
      {  // COMPLETE #3a: Add BTNodes to the left and right nodes to 'current' for 'y'
         current.setLeft(new BTNode(correctAnimal, null , null));
         current.setRight(new BTNode(guessAnimal, null, null));
      }
      else
      {  // COMPLETE #3b: Add code to the left and right nodes to 'current' for 'n'
         current.setRight(new BTNode(correctAnimal, null , null));
         current.setLeft(new BTNode(guessAnimal, null, null));
      }
   
   }  // end of learn(BTNode current)


   /** Inorder printing to the output file. */
   public static void depthPrint( BTNode node, int num)
   {
      if (node.getLeft() != null){
         depthPrint(node.getLeft(), num * 2);
      }
      String outPut = num + DELIMITER + node.getData();
      System.out.println ( outPut );
      if (node.getRight() != null){
         depthPrint(node.getRight(), num *2 + 1);
      }
      try
      {
         outBw.write( outPut );
         outBw.newLine();
      }
      catch (IOException e)
      {
         System.out.println("Error in writing file " + e.getMessage() );
      }
      
      // COMPLETE #4: Recursively call this method with the left and right nodes.
      // 2nd parameter is for printing the left 'num*2' and right 'num*2+1' numbers
      // these numbers are used for reloading the tree.
      // Check the left and right nodes are not null before calling depthPrint
   
   // if there is a left node, depthPrint the left node and the counter for the left
   
   // if there is a right node, depthPrint the right node and the counter for the right
   
   
   }
}