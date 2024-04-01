//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: The Anti-Scalper Agenda
// Course: CS 300 Fall 2023
//
// Author: Madison Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * A series of static tester methods to check the correctness of the TicketQueue and the
 * TicketQueueIterator
 */
public class TicketQueueTester {

  /**
   * Checks the correctness of the TicketQueue's constructor, including case(s) where it should
   * throw exceptions. Also checks the correctness of isEmpty(), isFull(), size(), capacity(), and
   * toString() on a newly created TicketQueue.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testConstructor() {
    // case where throw exceptions
    {
      try {
        TicketQueue test = new TicketQueue(0);
        return false;
      } catch (IllegalArgumentException e) {
        // System.out.println("Exception caught.");
      }
    }
    // normal case
    {
      TicketQueue test = new TicketQueue(3);
      // test isEmpty
      if (test.isEmpty() != true) {
        return false;
      }
      // test isFull
      if (test.isFull() != false) {
        return false;
      }
      // test size
      if (test.size() != 0) {
        return false;
      }
      // test capacity
      if (test.capacity() != 3) {
        return false;
      }
      // test toString
      if (test.toString() != "") {
        return false;
      }
    }

    return true;

  }

  /**
   * Checks the correctness of the TicketQueue's peek() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testPeek() {
    // test case with exception
    {
      TicketQueue test = new TicketQueue(2);
      try {
        test.peek();
        return false;
      } catch (NoSuchElementException e) {
        // System.out.println("Exception caught.");
      }

    }
    // test case with one
    {
      TicketQueue test = new TicketQueue(4);
      TicketSiteUser testUser = new TicketSiteUser("Madi", "potato", "1234567890123456");
      testUser.login("Madi", "potato");
      test.enqueue(testUser);
      // System.out.println(test);

      if (test.peek() != testUser || test.size() != 1)
        return false;
    }

    // test case with multiple
    {
      TicketQueue test = new TicketQueue(4);
      TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
      TicketSiteUser testUser2 = new TicketSiteUser("Bob", "iwantchicken", "0987654321123456");
      testUser1.login("Madi", "potato");
      testUser2.login("Bob", "iwantchicken");
      test.enqueue(testUser1);
      test.enqueue(testUser2);

      // System.out.println(test);
      if (test.peek() != testUser1 || test.size() != 2)
        return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the TicketQueue's enqueue() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testEnqueue() {
    // case that throws exception
    {
      TicketQueue test = new TicketQueue(2);
      TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
      TicketSiteUser testUser2 = new TicketSiteUser("Bob", "iwantchicken", "0987654321123456");
      TicketSiteUser testUser3 = new TicketSiteUser("Eric", "ericcuh", "0987654321098765");
      testUser1.login("Madi", "potato");
      testUser2.login("Bob", "iwantchicken");
      testUser3.login("Eric", "ericcuh");
      try {
        test.enqueue(testUser1);
        test.enqueue(testUser2);
        test.enqueue(testUser3); // should throw exception after this one
        return false;
      } catch (IllegalStateException e) {
        // System.out.println("Exception Caught: " + e.getMessage());
      }
    }

    // case starting with empty list
    {
      TicketQueue test = new TicketQueue(2);
      TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
      testUser1.login("Madi", "potato");

      test.enqueue(testUser1);
      // System.out.println(test);
      if (test.size() != 1 || test.isEmpty() || test.peek() != testUser1)
        return false;

    }
    // case with not empty list
    {
      TicketQueue test = new TicketQueue(2);
      TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
      TicketSiteUser testUser2 = new TicketSiteUser("Bob", "iwantchicken", "0987654321123456");
      testUser1.login("Madi", "potato");
      testUser2.login("Bob", "iwantchicken");

      test.enqueue(testUser1);
      test.enqueue(testUser2);
      // System.out.println(test);
      if (test.size() != 2 || test.isEmpty() || test.peek() != testUser1)
        return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the TicketQueue's dequeue() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testDequeue() {
    // case that throws exception (empty list)
    {
      TicketQueue test = new TicketQueue(2);

      try {
        test.dequeue();
        return false;
      } catch (NoSuchElementException e) {
        // System.out.println("Exception Caught: " + e.getMessage());
      }
    }
    // normal case
    {
      TicketQueue test = new TicketQueue(3);
      TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
      TicketSiteUser testUser2 = new TicketSiteUser("Bob", "iwantchicken", "0987654321123456");
      TicketSiteUser testUser3 = new TicketSiteUser("Eric", "ericcuh", "0987654321098765");
      testUser1.login("Madi", "potato");
      testUser2.login("Bob", "iwantchicken");
      testUser3.login("Eric", "ericcuh");

      test.enqueue(testUser1);
      test.enqueue(testUser2);
      test.enqueue(testUser3);

      TicketSiteUser removed = test.dequeue();
      if (test.size() != 2 || test.isFull() || removed != testUser1)
        return false;
    }

    // case with one
    {
      TicketQueue test = new TicketQueue(3);
      TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
      testUser1.login("Madi", "potato");
      test.enqueue(testUser1);

      TicketSiteUser removed = test.dequeue();
      if (test.size() != 0 || !test.isEmpty() || removed != testUser1)
        return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the TicketQueueIterator method(s) and iterating through a
   * TicketQueue. See write-up for more details on how to write this test. You DO NOT need to test
   * if the TicketQueueIterator constructor throws an exception when the queue parameter is null.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testIterator() {

    TicketQueue test = new TicketQueue(4);
    TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
    TicketSiteUser testUser2 = new TicketSiteUser("Bob", "iwantchicken", "0987654321123456");
    TicketSiteUser testUser3 = new TicketSiteUser("Eric", "ericcuh", "0987654321098765");
    testUser1.login("Madi", "potato");
    testUser2.login("Bob", "iwantchicken");
    testUser3.login("Eric", "ericcuh");
    test.enqueue(testUser1);
    test.enqueue(testUser2);
    test.enqueue(testUser3);

    // array of users that next should return
    TicketSiteUser[] nextArray = new TicketSiteUser[] {testUser1, testUser2, testUser3};
    int loopSize = 0;
    for (TicketSiteUser user : test) {
      if (user != nextArray[loopSize]) {
        return false;
      }
      loopSize++;
    }
    if (loopSize != test.size())
      return false;

    // test that the original array wasn't modified
    if (test.size() != 3)
      return false;

    if (!(test.toString().equals("Madi: *\nBob: *\nEric: *\n"))) {
      return false;
    }

    return true;
  }

  // making this test to debug
  public static boolean testDeepCopy() {
    TicketQueue test = new TicketQueue(3);
    TicketSiteUser testUser1 = new TicketSiteUser("Madi", "potato", "1234567890123456");
    TicketSiteUser testUser2 = new TicketSiteUser("Bob", "iwantchicken", "0987654321123456");
    TicketSiteUser testUser3 = new TicketSiteUser("Eric", "ericcuh", "0987654321098765");
    testUser1.login("Madi", "potato");
    testUser2.login("Bob", "iwantchicken");
    testUser3.login("Eric", "ericcuh");

    test.enqueue(testUser1);
    test.enqueue(testUser2);
    test.enqueue(testUser3);

    // System.out.println(test.deepCopy());
    if (test.deepCopy().toString().equals(test.toString()))
      return true;
    return false;
  }

  /**
   * Runs all the tests
   * 
   * @return true if all test cases pass, false otherwise
   */
  private static boolean runAllTests() {
    return testConstructor() && testPeek() && testEnqueue() && testDequeue() && testDeepCopy()
        && testIterator();
  }


  public static void main(String[] args) {

    System.out.println("testConstructor: " + (testConstructor() ? ("pass") : ("fail")));
    System.out.println("testPeek: " + (testPeek() ? ("pass") : ("fail")));
    System.out.println("testEnqueue: " + (testEnqueue() ? ("pass") : ("fail")));
    System.out.println("testDequeue: " + (testDequeue() ? ("pass") : ("fail")));
    System.out.println("testDeepCopy: " + (testDeepCopy() ? ("pass") : ("fail")));
    System.out.println("testIterator: " + (testIterator() ? ("pass") : ("fail")));
    System.out.println("ALL TESTS: " + (runAllTests() ? ("PASS") : ("FAIL")));
  }

}
