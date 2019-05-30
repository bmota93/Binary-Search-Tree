/*=================================================================================

Author:          Brandon Mota

Main driver class for the Binary Search Tree

This driver processes inputs like this -- implementing a keyed
list with a binary search tree:

emptyTest
insert every
insert boy
count
height
insert good
insert eats
count
height
insert breakfast
count
height
treePrint
levelPrint
emptyTest
delete every
delete fine
delete boy
treePrint
levelPrint
stop

=================================================================================*/

import java.util.*;

import jss2.*;
import jss2.exceptions.*;

//typedef int listClass ;

public class driver {	
	
/* ================================================== */
	public static void main(String[] args) {
		
		
   LinkedBinarySearchTree<String> tree = new LinkedBinarySearchTree<String>();
   String command ;

do
  { 
     System.out.println("#######################################################");
     System.out.print("Enter a command. Enter '?' for a list of available commands: ");
     Scanner input = new Scanner(System.in);
     command = input.nextLine();
     System.out.print("You entered: ");
     System.out.println (command)  ;
      switch (command.charAt(0))
      {
        case 'c': DoCountInfo(tree) ;
                  break ;
        case 'd': DoDeletion(tree, command) ;
                  break ;
        case 'e': DoEmptyTest(tree) ;
                  break ;
        case 'h': DoHeightInfo(tree) ;
                  break ;
        case 'i': DoInsertion(tree, command) ;
                  break ;
        case 'l': DoLevelPrint(tree) ;
                  break ;
        case 's': DoStop() ;
                  break ;
        case 't': DoTreePrint(tree) ;
                  break ;
        case '?': DoHelp() ;
        break ;
      }
   } while (command.charAt(0) != 's') ;
System.out.println ("#######################################################");
       


}

/* ================================================== */
public static void DoEmptyTest(LinkedBinarySearchTree tree) 
{
	
	if(tree.isEmpty())
	{
		System.out.println("The tree is empty.");
	}
	else
	{
		System.out.println("The tree is not empty.");
	}
}
/* ================================================== */
public static void DoInsertion(LinkedBinarySearchTree tree, String command) 
{
	
	String[] parts = command.split(" "); //strings are split, so only the one part is added and not the command
	String operator = parts[0]; //the command
	String addition = parts[1]; //part wanting to be added
		
	tree.addElement(addition);
	System.out.println("\"" + addition + "\"" + " has been placed in the tree.") ;
}
/* ================================================== */
public static void DoCountInfo(LinkedBinarySearchTree tree) 
{
	System.out.print("The tree contains " + tree.recTreeCount(tree.root) + " elements.\n");
}
/* ================================================== */
public static void DoHeightInfo(LinkedBinarySearchTree tree) 
{
	System.out.println("The height of the tree is: " + tree.recTreeHeight(tree.root));
}
/* ================================================== */
public static void DoDeletion(LinkedBinarySearchTree tree, String command) 
{
	String[] parts = command.split(" "); //strings are split, so only the one part is added and not the command
	String operator = parts[0]; //the command
	String deleter = parts[1]; //part wanting to be added
	try
	{
	tree.removeElement(deleter);
	System.out.println("\"" + deleter + "\"" + " has been removed from the tree.") ;
	}
	catch (ElementNotFoundException e) //if the element is not found it is caught
	{
		System.out.println("\"" + deleter + "\"" + " does not exist in the tree!");
	}
	
}
/* ================================================== */
public static void DoTreePrint(LinkedBinarySearchTree tree)
{		
	System.out.println("The tree looks like this:\n");
       tree.recRevOrderPrint(tree.root, 0);
       System.out.println(""); 
}
/* ================================================== */
public static void DoLevelPrint(LinkedBinarySearchTree tree)
{
	System.out.println("The tree looks like this:\n");
	tree.LevOrderPrint();
}
/* ================================================== */
public static void DoStop() 
{
       System.out.println("All done. Goodbye!");
       return;
}


/* ================================================== */
public static void DoHelp() 
{
       System.out.println("These are all the available commands:");       
       System.out.println("insert <name> - insert the name typed into the tree");
       System.out.println("delete <name> - deletes the item from the tree");
       System.out.println("count - count the number of elements in the tree");
       System.out.println("height - returns the height of the tree");
       System.out.println("treePrint - prints the tree of all current elements in a psudo tree format");
       System.out.println("levelPrint - prints the tree in a stacked level format");
       System.out.println("emptyTest - verifies if there are items in the tree");
       System.out.println("stop - terminates the program");
       
       return;
}

}

/* ================================================== */




