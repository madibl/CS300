///////////////////////////////////////////////////////////////////////////////
//
// Title: CS 300 Grade Policies
// Course: CS 300 Fall 2023
//
// Author: Madison Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nathan Han
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains various tester methods for the AssignmentGroup, DropAssignmentGroup, and
 * ScalingAssignmentGroup classes. Runs all tests and prints out if the tests passed or failed.
 */
public class AssignmentGroupTester {


  /**
   * Tests that the addAssignment() method works properly in AssignmentGroup
   * 
   * @return true if the AssignmentGroup contains the added assignment and the number of assignments
   *         matches
   */
  public static boolean testAddOneAssignment() {
    AssignmentGroup testGroup = new AssignmentGroup(0.1);
    SimpleAssignment testAssignment = new SimpleAssignment(10);

    testGroup.addAssignment(testAssignment);

    int expectedNumAssignments = 1;
    int actualNumAssignments = testGroup.getNumAssignments();
    SimpleAssignment actualAssignment = testGroup.getAssignment(0);
    return (expectedNumAssignments == actualNumAssignments
        && actualAssignment.equals(testAssignment));
  }

  /**
   * Verify that the addAssignments() method works properly in AssignmentGroup
   * 
   * @return true if the testGroup assignments and number of assignments matches
   */
  public static boolean testAddManyAssignments() {
    // test addAssignemtns
    AssignmentGroup testGroup = new AssignmentGroup(0.2);
    SimpleAssignment testAssignment1 = new SimpleAssignment(10);
    SimpleAssignment testAssignment2 = new SimpleAssignment(25);

    testGroup.addAssignment(testAssignment1);
    testGroup.addAssignment(testAssignment2);
    int expectedNumAssignments = 2;
    int actualNumAssignments = testGroup.getNumAssignments();

    SimpleAssignment actualAssignment1 = testGroup.getAssignment(0);
    SimpleAssignment actualAssignment2 = testGroup.getAssignment(1);

    return (expectedNumAssignments == actualNumAssignments)
        && (testAssignment1 == actualAssignment1) && (testAssignment2 == actualAssignment2);
  }

  /**
   * Helper method that verifies that the getTotalPossible() method returns the expected value in
   * EACH of the classes which implements the method
   * 
   * @return true if all tests pass
   */
  public static boolean testGetTotal() {
    boolean result = testGroupTotal();
    result &= testDropTotal();
    result &= testScaledTotal();

    return result;
  }


  /**
   * Verify that getTotalPossible() works as expected in AssignmentGroup
   * 
   * @return true if getTotalPossible matches the expected total
   */
  private static boolean testGroupTotal() {
    AssignmentGroup testGroup = new AssignmentGroup(1);
    testGroup.addAssignment(new SimpleAssignment(10));
    testGroup.addAssignment(new SimpleAssignment(20));
    testGroup.addAssignment(new SimpleAssignment(30));

    double expectedPointTotal = 60;
    double actualPointTotal = testGroup.getTotalPossible();

    return expectedPointTotal == actualPointTotal;
  }

  /**
   * Verify that getTotalPossible() works as expected in DropAssignmentGroup
   * 
   * @return true if the getTotalPossible returns the expected total
   */
  private static boolean testDropTotal() {
    // after dropping 2 / 4 assignments
    DropAssignmentGroup testGroup = new DropAssignmentGroup(0.8, 2);
    SimpleAssignment a1 = new SimpleAssignment(10);
    SimpleAssignment a2 = new SimpleAssignment(20);
    SimpleAssignment a3 = new SimpleAssignment(10);
    SimpleAssignment a4 = new SimpleAssignment(20);

    // complete 2 assignments
    testGroup.addAssignment(a1);
    testGroup.addAssignment(a2);
    testGroup.addAssignment(a3);
    testGroup.addAssignment(a4);

    a1.complete(10);
    a2.complete(18);

    double expectedPoints = 30;
    double actualPoints = testGroup.getTotalPossible();
    System.out.println(actualPoints);
    return expectedPoints == actualPoints;
  }

  /**
   * Verify that getTotalPossible() works as expected in ScalingAssignmentGroup
   * 
   * @return true if getTotalPossible matches the expected total
   */
  private static boolean testScaledTotal() {
    ScalingAssignmentGroup test = new ScalingAssignmentGroup(0.1, 0.5);
    test.addAssignment(new SimpleAssignment(10));
    test.addAssignment(new SimpleAssignment(20));
    test.addAssignment(new SimpleAssignment(10));

    double expectedTotal = 20;
    double actualTotal = test.getTotalPossible();
    return expectedTotal == actualTotal;
  }

  /**
   * Verify that the getPoints() method returns the expected value in EACH of the classes which
   * implements the method
   * 
   * @return
   */
  public static boolean testGetPoints() {
    boolean result = testGroupPoints();
    result &= testDropPoints();
    result &= testScaledPoints();

    return result;
  }

  /**
   * Verify that getPoints() works as expected in AssignmentGroup
   *
   * @return true if getPoints on an
   */
  private static boolean testGroupPoints() {
    AssignmentGroup testGroup = new AssignmentGroup(0.2);
    SimpleAssignment testAssignment1 = new SimpleAssignment(10);
    SimpleAssignment testAssignment2 = new SimpleAssignment(25);
    SimpleAssignment testAssignment3 = new SimpleAssignment(25);
    testGroup.addAssignment(testAssignment1);
    testGroup.addAssignment(testAssignment2);
    testGroup.addAssignment(testAssignment3);


    double expectedTotal = 60;
    double actualTotal = testGroup.getTotalPossible();
    return (expectedTotal == actualTotal);
  }

  /**
   * Verify that getPoints() works as expected in DropAssignmentGroup
   *
   * @return true if the getPoints output matches expected output on normal and dropped assignments.
   */
  private static boolean testDropPoints() {

    // test when 2/4 are completed and n=2 is dropped
    {

      DropAssignmentGroup testGroup = new DropAssignmentGroup(0.8, 2);
      SimpleAssignment ass1 = new SimpleAssignment(10);
      SimpleAssignment ass2 = new SimpleAssignment(20);
      SimpleAssignment ass3 = new SimpleAssignment(10);
      SimpleAssignment ass4 = new SimpleAssignment(20);

      testGroup.addAssignment(ass1);
      testGroup.addAssignment(ass2);
      testGroup.addAssignment(ass3);
      testGroup.addAssignment(ass4);

      // complete 2 assignments
      ass1.complete(10);
      ass2.complete(18);
      ass3.complete(0);
      ass4.complete(0);

      double expectedNumPoints = 28;
      // calling getPoints calls dropLowestN and traverses testGroup for u
      double actualNumPoints = testGroup.getPoints();

      return (actualNumPoints == expectedNumPoints);
    }


  }

  /**
   * Verify that getPoints() works as expected in ScalingAssignmentGroup
   *
   * @return true if getPoints works correctly for different assignments as well with different
   *         point values for complete method.
   */
  private static boolean testScaledPoints() {
    boolean test1 = false;
    boolean test2 = false;

    // test with full score
    {
      ScalingAssignmentGroup test = new ScalingAssignmentGroup(0.1, 0.8);
      SimpleAssignment testAssignment1 = new SimpleAssignment(10);
      SimpleAssignment testAssignment2 = new SimpleAssignment(25);
      SimpleAssignment testAssignment3 = new SimpleAssignment(25);
      test.addAssignment(testAssignment1);
      test.addAssignment(testAssignment2);
      test.addAssignment(testAssignment3);
      testAssignment1.complete(10);
      testAssignment2.complete(25);
      testAssignment3.complete(25);

      double expectedPoints = 48;
      double actualPoints = test.getPoints();
      test1 = expectedPoints == actualPoints;
    }

    // test with not full score
    {
      ScalingAssignmentGroup test = new ScalingAssignmentGroup(0.1, 0.8);
      SimpleAssignment testAssignment1 = new SimpleAssignment(10);
      SimpleAssignment testAssignment2 = new SimpleAssignment(25);
      SimpleAssignment testAssignment3 = new SimpleAssignment(25);
      test.addAssignment(testAssignment1);
      test.addAssignment(testAssignment2);
      test.addAssignment(testAssignment3);
      testAssignment1.complete(8);
      testAssignment2.complete(10);
      testAssignment3.complete(20);

      double expectedPoints = 38;
      double actualPoints = test.getPoints();

      test2 = expectedPoints == actualPoints;
    }

    return test1 && test2;


  }

  /**
   * Verify that the isComplete() accessor method works as expected in AssignmentGroup
   *
   * @return true if isComplete successfully returns true when run on an array of SimpleAssignments
   *         that have been completed.
   */
  public static boolean testComplete() {
    DropAssignmentGroup test = new DropAssignmentGroup(0.8, 1);
    SimpleAssignment testAssignment1 = new SimpleAssignment(10);
    SimpleAssignment testAssignment2 = new SimpleAssignment(25);
    SimpleAssignment testAssignment3 = new SimpleAssignment(25);
    test.addAssignment(testAssignment1);
    test.addAssignment(testAssignment2);
    test.addAssignment(testAssignment3);
    testAssignment1.complete(10);
    testAssignment2.complete(25);

    boolean expectedResult = true;
    boolean actualResult = test.isComplete();


    return (expectedResult == actualResult);
  }

  public static void main(String[] args) {
    if (SimpleAssignmentTester.runAllTests()) {
      System.out.println("add one: " + (testAddOneAssignment() ? "PASS" : "fail"));
      System.out.println("add many: " + (testAddManyAssignments() ? "PASS" : "fail"));
      System.out.println("get total: " + (testGetTotal() ? "PASS" : "fail"));
      System.out.println("get points: " + (testGetPoints() ? "PASS" : "fail"));
      System.out.println("complete: " + (testComplete() ? "PASS" : "fail"));
    } else {
      System.out.println("Your SimpleAssignment implementation does NOT pass its tests!\n"
          + "Do NOT continue with AssignmentGro until you have passed all SimpleAssignment tests.");
    }
  }

}
