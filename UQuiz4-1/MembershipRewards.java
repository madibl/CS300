////////////////////////////////////////////////////////////////////////////////
// Full Name: Madison B Lin
// Campus ID: 9086068708
// WiscEmail: mblin@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// TODO fill out the file header with your name, ID number, and @wisc.edu email

import java.util.NoSuchElementException;
// No additional imports are allowed.

////////////////////////////////////////////////////////////////////////////////
//
// BE CAREFUL!! This file contains ONE class. You will need to complete the
// implementation of SEVEN methods with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.

// NO variables outside of any method may be added to this class.
//
// Any additional methods added to this class must be PRIVATE
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE.
//
////////////////////////////////////////////////////////////////////////////////


/**
 * This class models the rewards memberships of reward members related to a specific company.
 *
 * A MembershipRewards object stores information related to RewardMembers in a binary search tree.
 * RewardMembers are comparable objects with respect to their unique phone numbers.
 * 
 * A MembershipRewards object allows the user to perform the main following operations:
 * 
 * Subscribe new reward members. Every RewardMember has a name, a unique phone number, and reward
 * points. The RewardMember class is provided in a separate java file.
 * 
 * Search for a specific reward member given their phone number, and also update their reward points
 * if necessary.
 * 
 * Unsubscribe (delete) a specific RewardMember from the tree given their phone number.
 * 
 * There are SEVEN methods to complete in this class: <BR>
 * 1. isEmpty() <BR>
 * 2. size() <BR>
 * 3. subscribeMember() <BR>
 * 4. searchHelper() <BR>
 * 5. updateRewards() <BR>
 * 6. getMin() <BR>
 * 7. unsubscribeMember() <BR>
 * 
 **/
public class MembershipRewards {
  private BSTNode<RewardMember> root; // root of this BST
  private int size; // total number of RewardMembers stored in this tree

  // You do not need to define a constructor for this class. The compiler will generate
  // automatically a no-argument constructor which creates a new empty MembershipRewards tree.

  /**
   * Checks whether this MembershipRewards is empty
   * 
   * @return true if this MembershipRewards tree is empty and false otherwise
   */
  public boolean isEmpty() {
    // empty if root is empty of size is zero
    return (this.root == null || this.size <= 0);
  }

  /**
   * Returns the size of this MembershipRewards
   * 
   * @return the total number of RewardMember objects stored in this MembershipRewards tree.
   */
  public int size() {
    // This method MUST be implemented as a simple getter of the field size
    return this.size;
  }
  // BE SURE TO SAVE your source file before uploading it to gradescope.


  /**
   * Subscribes a new RewardMember to this MembershipRewards
   * 
   * @param newObject to add to this tree
   * @throws IllegalArgumentException with or without error message if a match with newObject is
   *                                  found in this tree.
   */
  public void subscribeMember(RewardMember newObject) {
    // TODO complete the implementation of this method
    if (isEmpty()) {
      this.root = new BSTNode<RewardMember>(newObject);
      
      // TODO -- Add newObject to a non-empty tree

    } else {
      // TODO -- Add newObject to an non-empty tree
      // You must call subscribeMemberHelper to implement this behavior
      // Be sure to not catch any exception here.
      subscribeMemberHelper(newObject, this.root);

    }
    // TODO increment size
    size++;
  }

  /**
   * Recursive helper method to add newObject to the non-empty subtree rooted at current
   * 
   * @param newObject new RewardMember to add
   * @param current   root of a subtree
   * @throws IllegalArgumentException if the subtree rooted at current contains already a match with
   *                                  newObject
   */
  protected static void subscribeMemberHelper(RewardMember newObject,
      BSTNode<RewardMember> current) {
    // DO NOT MAKE ANY CHANGE TO THE IMPLEMENTATION OF THIS METHOD

    int comparison = newObject.compareTo(current.getData());

    if (comparison == 0) // duplicate found
      throw new IllegalArgumentException("This member is already a reward member.");
    if (comparison < 0) { // try to insert newObject to the left subtree
      if (current.getLeft() == null)
        current.setLeft(new BSTNode<RewardMember>(newObject));
      else
        subscribeMemberHelper(newObject, current.getLeft());
    } else { // try to insert newObject to the right subtree
      if (current.getRight() == null)
        current.setRight(new BSTNode<RewardMember>(newObject));
      else
        subscribeMemberHelper(newObject, current.getRight());
    }
  }

  /**
   * Finds a RewardMember given their phone number
   * 
   * @param phoneNumber phone number of a specific RewardMember to search
   * @return the RewardMember whose phone number has a match with the provided one as input
   * @throws NoSuchElementException with or without a descriptive error message if match not found
   */
  public RewardMember search(String phoneNumber) {
    // DO NOT make any changes to the implementation of this method.
    return searchHelper(new RewardMember("some name", phoneNumber), root);
  }

  /**
   * Recursive helper method which search for a specific RewardMember in the BST rooted at current
   * 
   * @param potentialMember the RewardMember to search in the subtree rooted at current
   * @param current         root of a subtree of this BST
   * @return the RewardMember which matches the one passed as input
   * @throws NoSuchElementException with or without a descriptive error message if match with
   *                                potentialMember was not found
   * 
   */
  protected static RewardMember searchHelper(RewardMember potentialMember,
      BSTNode<RewardMember> current) {
    // You DO NOT need to check whether the input potentialMember is null. An automatic
    // NullPointerException
    // will be thrown automatically. Disregard this case.

    // TODO complete the implementation of this method with respect to the provided comments
    // [Hint] RewardMember.compareTo() method compares RewardMember objects with respect to their
    // phone numbers

    // TODO Determine and implement Base case(s)

    if (current == null)
      throw new NoSuchElementException("Tree is empty.");
    // base case: found it
    if (potentialMember.compareTo(current.getData()) == 0)
      return current.getData();
    
    // base case: if hit leaf and its not match
    if (current.getLeft() != null) {
      // TODO Determine and implement Recursive cases
      // recursively search left if less than current
      if (potentialMember.compareTo(current.getData()) < 0) {
        return searchHelper(potentialMember, current.getLeft());
      }
    }
      
    // recursively search right if greater than current
    if (current.getRight() != null) {
      if (potentialMember.compareTo(current.getData()) > 0) {
        return searchHelper(potentialMember, current.getRight());
      }
    }
    
    // if hit a leaf case (right or left is null) throw exception
    throw new NoSuchElementException ("No Match Found");

  }

  // BE SURE TO SAVE your source file before uploading it to gradescope.

  /**
   * Searches for the reward member given their phone number in this BST and updates their reward
   * points
   * 
   * Returns the string representation of the specific RewardMember if match found.
   * 
   * @param phoneNumber the phoneNumber of the potential reward member to find
   * @return the String representation of the reward member after updating their reward points if
   *         found match
   * @throws NoSuchElementException with or without a descriptive error message if match not found
   */
  public String updateRewards(String phoneNumber, int newRewardPoints) {
    // TODO complete the implementation of this method
    if (this.root == null) {
      throw new NoSuchElementException ("No Match Found");
    }
    RewardMember toReturn = search(phoneNumber);
    toReturn.setRewardPoints(newRewardPoints);
    return toReturn.toString();
  }

  /**
   * Returns the smallest RewardMember (with respect to the result of RewardMember.compareTo()
   * method) in the subtree rooted at current
   * 
   * @param current root of a subtree of a binary search tree storing RewardMember objects
   * @return the smallest RewardMember in the subtree rooted at current
   * @throws a NoSuchElementException with or without error message if current is null.
   */
  protected static RewardMember getMin(BSTNode<RewardMember> current) {
    // TODO complete the implementation of this method with respect to its specification
    // Feel free to design a recursive or an iterative implementation of this method

    if (current == null)
      throw new NoSuchElementException("Current is null.");
    // smallest reward member = leftmost rewardMember
    if (current.getLeft() == null)
      return current.getData();
    return getMin(current.getLeft());
  }
  // MAKE SURE TO SAVE your source file before uploading it to gradescope.

  /**
   * Deletes a RewardMember given their phone number from this MembershipRewards BST. This method
   * does nothing if no match found with the input phoneNumber in this tree.
   * 
   * @param phoneNumber of the phone number of the RewardMember to unsubscribe
   */
  public void unsubsribe(String phoneNumber) {
    // DO NOT MAKE ANY CHANGE TO THE IMPLEMENTATION OF THIS METHOD
    RewardMember toUnsubscribe = new RewardMember("some member", phoneNumber);
    try {
      root = unsubscribeHelper(toUnsubscribe, root);
      size--;
    } catch (NoSuchElementException e) {
      System.out.println("No match found!");
    }
  }

  /**
   * Removes a RewardMember from the tree rooted at current if match found with the provided one
   * 
   * @param member  a RewardMember equal to the RewardMember to be removed
   * @param current the root of a subtree of a binary search tree of RewardMembers
   * @return the new root of the subtree after removing the specified RewardMember
   * @throws NoSuchElementException with or without error message if match with RewardMember was not
   *                                found in the tree rooted at current
   */
  protected static BSTNode<RewardMember> unsubscribeHelper(RewardMember member,
      BSTNode<RewardMember> current) {
    // This method will be MANUALLY graded, ONLY.
    // TODO Complete the missing code in this implementation
    if (current == null) { // RewardMember not found
      // TODO Missing code
      throw new NoSuchElementException("Member not found.");
    }
    if (member.compareTo(current.getData()) < 0) {
      // TODO recurse on the left subtree rooted at current.getLeft()
      // Hint: current.setLeft(/* recursive call */);
      unsubscribeHelper(member, current.getLeft());

    } else if (member.compareTo(current.getData()) > 0) {
      // TODO recurse on the right subtree
      // Use the same logic as above
      unsubscribeHelper(member, current.getRight());

    } else { // RewardMember found
      if (current.getLeft() != null && current.getRight() != null) { // current has two children
        // TODO replace the data of current with the data field value of its successor
        // [Hint] Notice that for simplicity the provided BSTNode class defines a setter for the
        // data field.
       
        // successor == rightmost of right tree
        current.setData(getMin(current.getRight()));



        // We have now a duplicate of the successor at node.getData() and in the right subtree.
        // remove the successor from the right subtree
        current.setRight(unsubscribeHelper(current.getData(), current.getRight()));

      } else // current has up to one child
      if (current.getLeft() != null) { // current has one left child
        // TODO Set current to its left child or return left child
        return current.getLeft();
        

      } else { // current has one right child or zero children
        // TODO Set current to its right child or return right child
        return current.getRight();
      }
    }
    return current; // return the new root of this subtree otherwise the changes to current will be
                    // lost. DO NOT remove this return statement.
  }
  // MAKE SURE TO SAVE your source file before uploading it to gradescope.

  /**
   * Returns a String representation of the contents of this MembershipRewards tree, sorted in the
   * increasing order
   * 
   * @return a String representation of this MembershipRewards
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Returns a string representation of the subtree rooted at current
   * 
   * @param current root of a subtree of a BST storing RewardMembers
   * @return a String representation of the subtree rooted at current
   */
  protected static String toStringHelper(BSTNode<RewardMember> current) {
    String s = "";
    if (current != null) {
      // visit left subtree, then the current node, then the right subtree
      s += toStringHelper(current.getLeft()) + "\n" + (current.getData()) + "\n"
          + toStringHelper(current.getRight());
    }
    return s.trim();
  }

  /**
   * Runs a demo of this code
   */
  public static void demo() {
    MembershipRewards memberships = new MembershipRewards();
    memberships.subscribeMember(new RewardMember("c1", "500"));
    memberships.subscribeMember(new RewardMember("c2", "600"));
    memberships.subscribeMember(new RewardMember("c3", "300"));
    memberships.subscribeMember(new RewardMember("c4", "200"));
    memberships.subscribeMember(new RewardMember("c5", "530"));
    memberships.subscribeMember(new RewardMember("c6", "400"));
    System.out.println("List of reward members (" + memberships.size() + ")");
    System.out.println(memberships);

    // Expected Output Printed Out HERE:
    // List of reward members (6)
    // c4(200): 0
    // c3(300): 0
    // c6(400): 0
    // c1(500): 0
    // c5(530): 0
    // c2(600): 0

    // Try to update rewards for phone 400.
    System.out.println("Updating rewards for phone 400: ");
    System.out.println(memberships.updateRewards("400", 50));
    // Expected Output Printed Out HERE:
    // Updating rewards for phone 400:
    // c6(400): 50

    // Try to update rewards for 608-003.
    try {
      System.out.println("Updating rewards for phone 608-003: ");
      System.out.println(memberships.updateRewards("608-003", 100));
    } catch (NoSuchElementException e) {
      System.out.print("No match found.");
    }
    // Expected Output Printed Out HERE:
    // Updating rewards for phone 608-003:
    // No match found.

  }

  /**
   * main method
   * 
   * @param args input arguments
   */
  public static void main(String[] args) {
    demo();
  }

}
