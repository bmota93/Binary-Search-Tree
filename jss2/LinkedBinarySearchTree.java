/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface 
 * with links.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;
import java.util.*;

import jss2.exceptions.*;
import jss2.*;

public class LinkedBinarySearchTree<T>  implements BinarySearchTreeADT<T>

{
	protected int count;
	public BinaryTreeNode<T> root;
   /**
    * Creates an empty binary search tree.
    */
   public LinkedBinarySearchTree() 
   {
      super();
   }
   
   /**
    * Adds the specified object to the binary search tree in the
    * appropriate position according to its key value.  Note that
    * equal elements are added to the right.
    *
    * @param element  the element to be added to the binary search tree
    */
   public void addElement (T element) 
   {
      BinaryTreeNode<T> temp = new BinaryTreeNode<T> (element);
      Comparable<T> comparableElement = (Comparable<T>)element;
      
      if (isEmpty())      
         root = temp;      
      else 
      {
         BinaryTreeNode<T> current = root;
         boolean added = false;

         while (!added) 
         {        	 
            if (comparableElement.compareTo(current.element) < 0)
            {
               if (current.left == null) 
               {
                  current.left = temp;
                  added = true;
               } 
               else
                  current = current.left;
            }
            else
            {
               if (current.right == null) 
               {
                  current.right = temp;
                  added = true;
               } 
               else
                  current = current.right;
            }
         }
      }
      
      count++;
   }
   
   /**
    * Removes the first element that matches the specified target
    * element from the binary search tree and returns a reference to
    * it.  Throws a ElementNotFoundException if the specified target
    * element is not found in the binary search tree.
    *
    * @param targetElement              the element being sought in the binary 
    *                                   search tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T removeElement (T targetElement)
                          throws ElementNotFoundException 
   {
      T result = null;

      if (!isEmpty())
      {
         if (((Comparable)targetElement).equals(root.element)) 
         {
            result =  root.element;
            root = replacement (root);
            count--;
         }
         else 
         {
            BinaryTreeNode<T> current, parent = root;
            boolean found = false;

            if (((Comparable)targetElement).compareTo(root.element) < 0)
               current = root.left;
            else
               current = root.right;

            while (current != null && !found) 
            {
               if (targetElement.equals(current.element)) 
               {
                  found = true;
                  count--;
                  result =  current.element;
          
                  if (current == parent.left)
                  {
                     parent.left = replacement (current);
                  }
                  else
                  {
                     parent.right = replacement (current);
                  }
               }
               else 
               {
                  parent = current;
         
                  if (((Comparable)targetElement).compareTo(current.element) < 0)
                     current = current.left;
                  else
                     current = current.right;
               }
            } //while
            
            if (!found)
               throw new ElementNotFoundException("binary search tree");
         }
      } // end outer if

      return result;
   }

   /**
    * Removes elements that match the specified target element from 
    * the binary search tree. Throws a ElementNotFoundException if 
    * the sepcified target element is not found in this tree.
    *
    * @param targetElement              the element being sought in the binary \
    *                                   search tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public void removeAllOccurrences (T targetElement)
                                    throws ElementNotFoundException 
   {
      removeElement(targetElement);
      
      try
      {
         while (contains( (T) targetElement))
            removeElement(targetElement);
      }
      
      catch (Exception ElementNotFoundException)
      {
      }
   }

   /**
    * Removes the node with the least value from the binary search
    * tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if this tree is empty. 
    *
    * @return                           a reference to the node with the least 
    *                                   value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMin() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
         throw new EmptyCollectionException ("binary search tree");
      else 
      {
         if (root.left == null) 
         {
            result = root.element;
            root = root.right;
         }
         else 
         {
            BinaryTreeNode<T> parent = root;
            BinaryTreeNode<T> current = root.left;
            while (current.left != null) 
            {
               parent = current;
               current = current.left;
            }
            result =  current.element;
            parent.left = current.right;
         }

         count--;
      }
 
      return result;
   }

   /**
    * Removes the node with the highest value from the binary
    * search tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if this tree is empty. 
    *
    * @return  a reference to the node with the highest value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMax() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary search tree");
      else 
      {
         if (root.right == null) 
         {
            result =  root.element;
            root = root.left;
         }
         else 
         {
            BinaryTreeNode<T> parent = root;
            BinaryTreeNode<T> current = root.right;
            while (current.right != null) 
            {
               parent = current;
               current = current.right;
            }
            
            result =  current.element;
            parent.right = current.left;
         }

         count--;
      }

      return result;
   }


   /**
    * Returns the element with the least value in the binary search
    * tree. It does not remove the node from the binary search tree. 
    * Throws an EmptyBinarySearchTreeException if this tree is empty.
    *
    * @return  the element with the least value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMin() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
         throw new EmptyCollectionException ("binary search tree");
      else 
      {
         BinaryTreeNode<T> current = root;
        
         while (current.left != null)
            current = current.left;
       
         result = current.element;
      }

      return result;
   }

   /**
    * Returns the element with the highest value in the binary
    * search tree.  It does not remove the node from the binary
    * search tree.  Throws an EmptyBinarySearchTreeException if this 
    * tree is empty.
    *
    * @return  the element with the highest value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMax() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary search tree");
      else 
      {
         BinaryTreeNode<T> current = root;
      
         while (current.right != null)
            current = current.right;
         
         result = current.element;
      }
 
      return result;
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in the binary tree.  Throws a NoSuchElementException if
    * the specified target element is not found in this tree.
    *
    * @param targetElement  the element being sough in the binary tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T find (T targetElement) throws ElementNotFoundException 
   {
      BinaryTreeNode<T> current = root; 
      BinaryTreeNode<T> temp = current;
      
      if (!(current.element.equals(targetElement)) && (current.left !=null)&&
            (((Comparable)current.element).compareTo(targetElement) > 0))
         current = findAgain( targetElement, current.left);
      
      else if (!(current.element.equals(targetElement)) && (current.right != null)) 
         current = findAgain( targetElement, current.right);
      
      if (!(current.element.equals(targetElement)))
         throw new ElementNotFoundException ("binary search tree");
      
      return current.element;
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in this tree.  
    *
    * @param targetElement  the element being sought in the tree
    * @param next           the tree node to being searching on
    */
   private BinaryTreeNode<T> findAgain (T targetElement, BinaryTreeNode<T> next) 
   {
      BinaryTreeNode<T> current = next;
      
      if (!(next.element.equals(targetElement)) && (next.left !=null) &&
            (((Comparable)next.element).compareTo(targetElement) > 0))
         next = findAgain( targetElement, next.left);
      
      else if (!(next.element.equals(targetElement)) && (next.right != null))
         next = findAgain( targetElement, next.right);
      
      return next;
   }

   /**
    * Returns a reference to a node that will replace the one
    * specified for removal.  In the case where the removed node has 

    * two children, the inorder successor is used as its replacement.
    *
    * @param node  the node to be removeed
    * @return      a reference to the replacing node
    */
   protected BinaryTreeNode<T> replacement (BinaryTreeNode<T> node) 
   {
      BinaryTreeNode<T> result = null;

      if ((node.left == null)&&(node.right==null))
         result = null;
      
      else if ((node.left != null)&&(node.right==null))
         result = node.left;
      
      else if ((node.left == null)&&(node.right != null))
         result = node.right;
      
      else
      {
         BinaryTreeNode<T> current = node.right;
         BinaryTreeNode<T> parent = node;
         
         while (current.left != null)
         {
            parent = current;
            current = current.left;
         }
         
         if (node.right == current)
            current.left = node.left;
         
         else
         {
            parent.left = current.right;
            current.right = node.right;
            current.left = node.left;
         }
         
         result = current;
      }
      
      return result;
   }

@Override
public T getRoot()
{
	// TODO Auto-generated method stub
	return null;
}

public int recTreeHeight(BinaryTreeNode node)
{
	if (node == null)
    {
        return 0;
    }
    else
    {
        return 1 + Math.max(recTreeHeight(node.left), recTreeHeight(node.right)); //returns only the maximum value of either subtree
    }
}

public int recTreeCount(BinaryTreeNode node)
{	
	if ( node == null )
	{
        return 0; 
	}
     else
     {
        int count = 1; 
        count += recTreeCount(node.left); //the count is added every time the left subtree is gone through                                        
        count += recTreeCount(node.right); //the count is added every time the right subtree is gone through
        return count;
     }
}

@Override
public boolean isEmpty()
{
	// TODO Auto-generated method stub
	return (count == 0);
}
/* new-new-new-new-new-new-new-new-new-new-new-new */
/*          recRevOrderPrint()                     */
/* new-new-new-new-new-new-new-new-new-new-new-new */
/* Print "sideways" the contents of the tree 
   pointed to by TreePtr.  Use indentation proportionate 
   to "level" */
public void recRevOrderPrint(BinaryTreeNode node, int space)
{
	BinaryTreeNode printnode;
	if (node != null)
	{			
		 printnode = node.right;		 
		 recRevOrderPrint(printnode, space = space + 5); //recursive call		 
		 for(int x = 0; x <= space; x++) //used to add space and give perspective to the tree
		 {
			 System.out.print(" ");
		 }
		 System.out.print(node.element); //the printing statement occurs here
		 System.out.println();	
		 printnode = node.left;
		 recRevOrderPrint(printnode, space); //recurssive call again		
	
	}	
	else
	{		
		return;
	}
}


/* new-new-new-new-new-new-new-new-new-new-new-new */
/*            LevOrderPrint()                      */
/* new-new-new-new-new-new-new-new-new-new-new-new */
/* Print the contents of the levels of the tree 
   from top to bottom -- each level on a new line. */
public void LevOrderPrint()
{	 	
	Queue nodes = new LinkedList(); //two queues are created
    Queue results = new LinkedList();
    
    if (isEmpty())
    {
    	return;
    }

    nodes.add(root); //the root is added first

    while (!nodes.isEmpty())
    {
        Iterator<BinaryTreeNode> iter = nodes.iterator(); //iterator is created
        while (iter.hasNext())
        {
        	BinaryTreeNode currentNode = iter.next(); //the element that the iterator call up is set to the current node
            if (currentNode.left != null)
            {
            	results.add(currentNode.left);
            }
            if (currentNode.right != null)
            {
            	results.add(currentNode.right);
            }
            System.out.print(currentNode.element + " ");
        }
        System.out.println();
        nodes = results; //nodes will equal results
        results = new LinkedList<BinaryTreeNode>(); //the results queue is set to a node queue

    }	
	
}

@Override
public int size() 
{
	// TODO Auto-generated method stub
	return 0;
}

@Override
public boolean contains(T targetElement)
{
	// TODO Auto-generated method stub
	return false;
}

/**
 * Performs an inorder traversal on this binary tree by calling an
 * overloaded, recursive inorder method that starts with
 * the root.
 *
 * @return  an in order iterator over this binary tree
 */
public Iterator<T> iteratorInOrder()
{
   ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
   inorder (root, tempList);
   
   return tempList.iterator();
}

/**
 * Performs a recursive inorder traversal.
 *
 * @param node      the node to be used as the root for this traversal
 * @param tempList  the temporary list for use in this traversal
 */
protected void inorder (BinaryTreeNode<T> node, 
                        ArrayUnorderedList<T> tempList) 
{
   if (node != null)
   {
      inorder (node.left, tempList);
      tempList.addToRear(node.element);
      inorder (node.right, tempList);
   }
}

/**
 * Performs an preorder traversal on this binary tree by calling 
 * an overloaded, recursive preorder method that starts with
 * the root.
 *
 * @return  an pre order iterator over this tree
 */
public Iterator<T> iteratorPreOrder() 
{
   ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
   preorder (root, tempList);
   
   return tempList.iterator();
}

/**
 * Performs a recursive preorder traversal.
 *
 * @param node  the node to be used as the root for this traversal
 * @param tempList  the temporary list for use in this traversal
 */
protected void preorder (BinaryTreeNode<T> node, 
                         ArrayUnorderedList<T> tempList) 
{
   if (node != null)
   {
      tempList.addToRear(node.element);
      preorder (node.left, tempList);
      preorder (node.right, tempList);
   }
}

/**
 * Performs an postorder traversal on this binary tree by calling
 * an overloaded, recursive postorder method that starts
 * with the root.
 *
 * @return  a post order iterator over this tree
 */
public Iterator<T> iteratorPostOrder() 
{
   ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
   postorder (root, tempList);
   
   return tempList.iterator();
}

/**
 * Performs a recursive postorder traversal.
 *
 * @param node      the node to be used as the root for this traversal
 * @param tempList  the temporary list for use in this traversal
 */
protected void postorder (BinaryTreeNode<T> node, 
                          ArrayUnorderedList<T> tempList) 
{
   if (node != null)
   {
      postorder (node.left, tempList);
      postorder (node.right, tempList);
      tempList.addToRear(node.element);
   }
}

@Override
public Iterator<T> iteratorLevelOrder()
{
	// TODO Auto-generated method stub
	return null;
}
}

