//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: LAST ONE BEST ONE: P10 BOARDING SYSTEM
// Course: CS 300 Fall 2023
//
// Author: Madison B Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {


  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    // [HINT] You can consider at least two Passenger objects, and ensure at least the following:
    // p1.compareTo(p2) < 0, if p1 has a boarding group less than the boarding group of p2.
    // p2.compareTo(p1) > 0
    // where p1, and p2 are references to Passenger objects with different boarding groups.
    // Recall that we defined three boarding groups A, B, and C such that A < B < C.

    Passenger.resetPassengerOrder();
    {
      // create two passengers
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger p2 = new Passenger("eric", Group.B, true);

      if (!(p1.compareTo(p2) < 0))
        return false;

      if (!(p2.compareTo(p1) > 0))
        return false;
    }
    {
      // create two passengers
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger.resetPassengerOrder();
      Passenger p2 = new Passenger("eric", Group.B, true);

      if (!(p1.compareTo(p2) < 0))
        return false;

      if (!(p2.compareTo(p1) > 0))
        return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    // TODO complete the implementation of this tester method
    // [Hint] You can consider at least two Passenger objects having the SAME boarding group, and
    // ensure at least the following:
    // p1.compareTo(p2) < 0, if p1.ARRIVAL_ORDER is less than p2.ARRIVAL_ORDER
    // p2.compareTo(p1) > 0
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("madi", Group.A, true);
    Passenger p2 = new Passenger("eric", Group.A, true);

    // madi arrives before eric
    if (!(p1.compareTo(p2) < 0))
      return false;

    if (!(p2.compareTo(p1) > 0))
      return false;

    return true;

  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    // Even though this case will not be possible in your priority queue, it is required for testing
    // the full functionality of the compareTo() method.
    // [Hint] You can use the resetPassengerOrder() to create equivalent passengers.

    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("madi", Group.A, true);
    Passenger.resetPassengerOrder();
    Passenger p2 = new Passenger("eric", Group.A, true);
    // System.out.println(p1);
    // System.out.println(p2);

    // madi arrives before eric
    if (!(p1.compareTo(p2) == 0)) {
      // System.out.println("COMPARISON: " + p1.compareTo(p2));
      return false;
    }
    return true;
  }

  /**
   * Test the equals method
   * 
   * @return true if equals returns correct value and false if there is a bug
   */
  public static boolean testEquals() {
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("madi", Group.A, true);
    Passenger.resetPassengerOrder();
    Passenger p2 = new Passenger("madi", Group.A, true);


    return p1.equals(p2);
  }


  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    boolean test1 = false;
    boolean test2 = false;
    // test on invalid
    {
      try {
        BoardingQueue test = new BoardingQueue(-1);
        return test1;
      } catch (IllegalArgumentException e) {
        test1 = true;
      }
    }
    // test on valid
    {
      BoardingQueue test = new BoardingQueue(5);
      if (test.size() == 0 && test.isEmpty() && test.capacity() == 5)
        test2 = true;

    }
    return test1 && test2;

  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    BoardingQueue test = new BoardingQueue(5);
    try {
      test.peekBest();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("madi", Group.A, true);
    Passenger p2 = new Passenger("eric", Group.B, true);
    Passenger p3 = new Passenger("sashimi", Group.C, true);
    Passenger p4 = new Passenger("snoopy", Group.A, true);
    Passenger p5 = new Passenger("snoop dog", Group.B, true);

    BoardingQueue test = new BoardingQueue(5);
    test.enqueue(p2);
    test.enqueue(p4);
    test.enqueue(p3);
    test.enqueue(p5);
    test.enqueue(p1);

    // System.out.println(test.toString());
    if (test.peekBest() != p1)
      return false;
    return true;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("madi", Group.A, true);
    BoardingQueue test = new BoardingQueue(5);

    return (test.enqueue(p1) && test.size() == 1 && test.peekBest() == p1);

  }


  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    // non-empty and not full
    {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger p2 = new Passenger("eric", Group.B, true);
      Passenger p3 = new Passenger("sashimi", Group.C, true);
      Passenger p4 = new Passenger("snoopy", Group.A, true);
      Passenger p5 = new Passenger("snoop dog", Group.B, true);

      try {
        Passenger[] expected = {p1, p4, p2, p5, p3};
        BoardingQueue test = new BoardingQueue(6);
        test.enqueue(p2);
        test.enqueue(p4);
        test.enqueue(p3);
        test.enqueue(p5);
        test.enqueue(p1);

        for (int i = 0; i < test.size(); i++) {
          if (test.dequeue() != expected[i])
            return false;
        }
      } catch (Exception e) {
        return false;
      }


    }
    // non-empty and full
    {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger p2 = new Passenger("eric", Group.B, true);
      Passenger p3 = new Passenger("sashimi", Group.C, true);
      Passenger p4 = new Passenger("snoopy", Group.A, true);
      Passenger p5 = new Passenger("snoop dog", Group.B, true);
      Passenger p6 = new Passenger("samuel", Group.C, true);

      try {
        BoardingQueue test = new BoardingQueue(5);
        test.enqueue(p2);
        test.enqueue(p4);
        test.enqueue(p3);
        test.enqueue(p5);
        test.enqueue(p1);
        BoardingQueue copy = test.deepCopy();
        test.enqueue(p6);
        // System.out.println(test);
        // System.out.println("--------------------------");
        // System.out.println(copy);
        if (!(test.toString().equals(copy.toString())))
          return false;
      } catch (Exception e) {
        return false;
      }



    }
    return true; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    BoardingQueue test = new BoardingQueue(5);
    try {
      test.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }


  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("madi", Group.A, true);
    BoardingQueue test = new BoardingQueue(1);
    test.enqueue(p1);

    if (test.dequeue() != p1 && test.size() != 0)
      return false;

    return true; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger each time it is
   *         called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    // [HINT] Try considering calling dequeue from a boarding queue whose size is at least 6.
    // Consider cases where percolate-down recurses on left and right.
    // You can call dequeue multiple times to cover multiple operational cases of percolate down
    // method (for instance percolate down can reach the bottom level of the heap or not)
    {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger p2 = new Passenger("eric", Group.B, true);
      Passenger p3 = new Passenger("sashimi", Group.C, true);
      Passenger p4 = new Passenger("snoopy", Group.A, true);
      Passenger p5 = new Passenger("snoop dog", Group.B, true);
      Passenger p6 = new Passenger("meep", Group.C, true);

      BoardingQueue test = new BoardingQueue(6);

      test.enqueue(p2);
      test.enqueue(p4);
      test.enqueue(p3);
      test.enqueue(p5);
      test.enqueue(p1);
      test.enqueue(p6);

      if (test.dequeue() != p1 || test.size() != 5 || test.peekBest() != p4)
        return false;

      if (test.dequeue() != p4 || test.size() != 4 || test.peekBest() != p2)
        return false;

      if (test.dequeue() != p2 || test.size() != 3 || test.peekBest() != p5)
        return false;

      if (test.dequeue() != p5 || test.size() != 2 || test.peekBest() != p3)
        return false;

      if (test.dequeue() != p3 || test.size() != 1 || test.peekBest() != p6)
        return false;

      if (test.dequeue() != p6 || test.size() != 0 || !test.isEmpty())
        return false;


    }

    // switch up order?
    {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger p2 = new Passenger("eric", Group.A, true);
      Passenger p3 = new Passenger("sashimi", Group.B, true);
      Passenger p4 = new Passenger("snoopy", Group.A, true);
      Passenger p5 = new Passenger("snoop dog", Group.C, true);
      Passenger p6 = new Passenger("meep", Group.C, true);
      Passenger p7 = new Passenger("Johann Sebastian Bach", Group.B, true);

      BoardingQueue test = new BoardingQueue(7);

      test.enqueue(p2);
      test.enqueue(p4);
      test.enqueue(p3);
      test.enqueue(p5);
      test.enqueue(p1);
      test.enqueue(p6);
      test.enqueue(p7);

      // System.out.println(test);
      if (test.dequeue() != p1 || test.size() != 6 || test.peekBest() != p2)
        return false;

      if (test.dequeue() != p2 || test.size() != 5 || test.peekBest() != p4)
        return false;

      if (test.dequeue() != p4 || test.size() != 4 || test.peekBest() != p3)
        return false;

      if (test.dequeue() != p3 || test.size() != 3 || test.peekBest() != p7)
        return false;

      if (test.dequeue() != p7 || test.size() != 2 || test.peekBest() != p5)
        return false;

      if (test.dequeue() != p5 || test.size() != 1 || test.peekBest() != p6)
        return false;

      if (test.dequeue() != p6 || test.size() != 0 || !test.isEmpty())
        return false;

    }


    return true;
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    // empty
    {
      Passenger.resetPassengerOrder();
      BoardingQueue test = new BoardingQueue(6);
      try {
        test.clear();
      } catch (Exception e) {
        return false;
      }
    }

    // non-empty
    {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("madi", Group.A, true);
      Passenger p2 = new Passenger("eric", Group.B, true);
      Passenger p3 = new Passenger("sashimi", Group.C, true);
      Passenger p4 = new Passenger("snoopy", Group.A, true);
      Passenger p5 = new Passenger("snoop dog", Group.B, true);
      Passenger p6 = new Passenger("samuel", Group.C, true);
      BoardingQueue test = new BoardingQueue(6);
      test.enqueue(p2);
      test.enqueue(p4);
      test.enqueue(p3);
      test.enqueue(p5);
      test.enqueue(p1);
      test.enqueue(p6);

      test.clear();
      if (test.size() != 0 && !test.isEmpty() && test != null)
        return false;
    }

    return true;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testEquals: " + (testEquals() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }

}
