//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Madison Lin
// Campus ID: 9086068708
// WiscEmail: mblin@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Random;
// No additional imports are allowed.

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit. There are SEVEN methods with TODO tags:
//
//1. isEmpty()
//2. isFull()
//3. addToBag()
//4. removeFromBag()
//5. embiggen()
//6. testEmptyFull()
//7. testAdd()
//
// NO variables outside of any method may be added to this class.
//
// Any additional methods added to this class must be PRIVATE STATIC BOOLEAN
// tester methods only.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE.
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class contains FIVE (5) utility methods for interacting with an 
 * unordered data structure called a Bag, as well as three tester methods.
 * 
 * We use a perfect-size array to store the items in this Bag, but to reflect
 * the un-ordered nature of the data structure, we can only remove an item from
 * a RANDOM (occupied) location in the Bag.
 * 
 * Be aware that one of the utility methods does not correspond to any provided
 * test. You MAY add a PRIVATE tester method to verify its functionality, but
 * this is NOT required.
 */
public class BagOfHolding {

  private static Random randGen = new Random();

  /**
   * Determines whether the Bag represented by the provided perfect-size array
   * is completely empty
   * 
   * @param bag a perfect-size array representing a Bag data structure
   * @return true if the Bag is empty; false otherwise
   */
  public static boolean isEmpty(String[] bag) {
    // 1. TODO: complete the implementation of this method 
    for (int i = 0; i < bag.length; i++) {
      if (bag[i] != null)
        return false;
    }
    return true; // default return statement, feel free to change.
  }

  /**
   * Determines whether the Bag represented by the provided perfect-size array
   * has no available spaces remaining
   * 
   * @param bag a perfect-size array representing a Bag data structure
   * @return true if the Bag is full; false otherwise
   */
  public static boolean isFull(String[] bag) {
    // 2. TODO: complete the implementation of this method
    for(int i = 0; i< bag.length; i++) {
      if (bag[i] == null) {
        return false;
      }
    }
    return true; // default return statement, feel free to change.
  }

  /**
   * Adds the provided item to the first available index in the provided Bag,
   * if one is available. Duplicate values are permitted.
   * 
   * @param bag a perfect-size array representing a Bag data structure
   * @param item the value to add to the Bag
   * @return true if the item was added successfully; false if the Bag was
   * already full
   */
  public static boolean addToBag(String[] bag, String item) {
    // 3. TODO: if the bag is full, cannot add any more elements
    if (isFull(bag) == true) {
      return false;
    }
    // 4. TODO: if the bag is NOT full, add the item to the first available space
    // traverse array to find first available space
    for (int i = 0; i < bag.length; i++) {
      if (bag[i] == null) {
        bag[i] = item;
        break;
      }
    }
    return true;
  }

  /**
   * Removes and returns an item from a randomly-generated index in the provided 
   * Bag, if one is available.
   * 
   * @param bag a perfect-size array representing a Bag data structure
   * @return the removed item, which should no longer be present in the provided
   * bag, or null if the bag was already empty
   */
  public static String removeFromBag(String[] bag) {
    // 5. TODO: if the bag is empty, cannot remove an element; return null
    if (isEmpty(bag) == true) {
      return null;
    }
    // 6. TODO: using the provided random number generator randGen, generate a
    // random index into the bag
    int randItemIndex = randGen.nextInt(0, bag.length);
    // 7. TODO: if there is no element at that position, keep generating a new 
    // index until you find one that contains an element
    while (bag[randItemIndex] == null) {
       randItemIndex = randGen.nextInt(0, bag.length);
    }
    // 8. TODO: remove that element from the bag and return it
    String removedItem = bag[randItemIndex];
    bag[randItemIndex] = null;
    return removedItem; 
  }

  /**
   * Creates and returns a larger version of the provided Bag, maintaining the
   * current positions of any elements in that Bag. If the expandValue is 0 or negative,
   * no new array is created.
   * 
   * @param bag a perfect-size array representing a Bag data structure
   * @param expandValue the number of elements to add to the bag's capacity
   * @return the new, larger bag OR the bag itself if the expandValue was 0 or negative
   */
  public static String[] embiggen(String[] bag, int expandValue) {
    // 9. TODO: check the validity of the expandValue
    if (expandValue <= 0) {
      return bag;
    }
    // 10. TODO: create a new bag that is expandValue number of spaces larger than the provided one
    String[] expandedBag = new String[bag.length + expandValue];
    // 11. TODO: copy all elements from bag into the new bag and return the new bag
    for (int i = 0; i < bag.length; i ++) {
      expandedBag[i] = bag[i];
    }
    return expandedBag; // default return statement, feel free to change.
  }

  /**
   * Verifies the functionality of isEmpty() and isFull()
   * 
   * @return true if both isEmpty() and isFull() contain no bugs; false otherwise
   */
  public static boolean testEmptyFull() {
    // 12. TODO: create an EMPTY perfect size array of Strings,
    // and make sure isEmpty is true and isFull is false
    String[] emptyTest = new String[4];
    boolean emptyTestEmpty = isEmpty(emptyTest);
    boolean emptyTestFull = isFull(emptyTest);
    
    // 13. TODO: create a FULL perfect size array of Strings,
    // and make sure isEmpty is false and isFull is true
    String[] fullTest = new String[4]; 
    fullTest[0] = "umbrella";
    fullTest[1] = "gum";
    fullTest[2] = "phone";
    fullTest[3] = "wallet";
    
    boolean fullTestEmpty = isEmpty(fullTest);
    boolean fullTestFull = isFull(fullTest);
//    System.out.println("emptyTestEmpty: " + emptyTestEmpty);
//    System.out.println("emptyTestFull: " + emptyTestFull);
//    System.out.println("fullTestEmpty: " + fullTestEmpty);
//    System.out.println("fullTestFull: " + fullTestFull);
    // 14. TODO: if all tests pass, return true
    if (emptyTestEmpty && !emptyTestFull && !fullTestEmpty && fullTestFull)
      return true;
    
    return false; // default return statement, feel free to change.
  }

  /**
   * Verifies the functionality of addToBag()
   * 
   * @return true if addToBag() contains no bugs; false otherwise
   */
  public static boolean testAdd() {
    // 15. TODO: create a NON-FULL perfect size array of Strings with length at least 5
    // and that contains at least one initialized value
    String[] nonFullArray = new String[5];
    nonFullArray[2] = "keys";
    nonFullArray[4] = "wallet";
    
    // 16. TODO: verify that adding a value to it is successful
    boolean added = addToBag(nonFullArray, "gum");
    
    // 17. TODO: verify that the value has been added to the first available
    // element of that array
    boolean firstVal = false;
    if (nonFullArray[0] == "gum") {
      firstVal = true;
    }

    // 18. TODO: if all tests pass, return true
    if (added && firstVal) {
      return true;
    }
    return false;
  }
  
  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) 

  /**
   * PROVIDED TEST
   * Verifies the functionality of the random removeFromBag() method
   * 
   * @return true if removeFromBag() contains no bugs; false otherwise
   */
  public static boolean testRemove() {
    // create a non-full perfect size array
    String[] notFull = {"cloak", null, "rations", "wand"};

    // verify that removed value is not null
    String removed = removeFromBag(notFull);
    if (removed == null) return false;
    
    // verify that removed value is no longer in array
    for (int i=0; i<notFull.length; i++) {
      String s = notFull[i];
      if (s != null && s.equals(removed)) return false;
    }

    // verify that the removed value had been in our original array
    if (!removed.equals("cloak") && !removed.equals("rations") && !removed.equals("wand")) 
      return false;

    return true;
  }

  /**
   * Runs the tester methods against your implementations
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Empty/Full Test: "+((testEmptyFull())?"pass":"fail"));
    System.out.println("Add Value Test: "+((testAdd())?"pass":"fail"));
    System.out.println("Remove Test: "+((testRemove())?"pass":"fail"));
  }

}
