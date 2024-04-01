
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
 * Contains various tester methods for SimpleAssignment. Runs all tests and prints out if the tests
 * are passed or failed.
 */
public class SimpleAssignmentTester {

  /**
   * Tests the constructor and accessor methods of SimpleAssignment
   * 
   * @return true or false if the expected points match the actual points
   */
  public static boolean testAccessors() {
    SimpleAssignment testAssignment = new SimpleAssignment(10);

    double expectedPoints = 0.0;
    double actualPoints = testAssignment.getPoints();

    double expectedPossible = 10.0;
    double actualPossible = testAssignment.POINTS_POSSIBLE;

    boolean resultObj1 = (expectedPoints == actualPoints && expectedPossible == actualPossible);

    SimpleAssignment testAssignment2 = new SimpleAssignment(10);

    testAssignment2.complete(8.0);

    double expectedPoints2 = 8.0;
    double actualPoints2 = testAssignment2.getPoints();

    double expectedPossible2 = 10.0;
    double actualPossible2 = testAssignment2.POINTS_POSSIBLE;

    boolean resultObj2 = (expectedPoints2 == actualPoints2 && expectedPossible2 == actualPossible2);

    return (resultObj1 == true && resultObj2 == true);

  }

  /**
   * This method calls a number of shorter helper methods, which test various cases for a
   * SimpleAssignment with the bonus option available
   * 
   * @return true or false depending on if the tests pass
   */
  // PROVIDED

  // By breaking these out into shorter tests, you can use the debugger to step through this
  // method and quickly determine which of the tests are failing, if any.
  public static boolean testSimpleBonus() {
    boolean result = testAwardBonus();
    result &= testBonusTwice();
    result &= testNoBonus();
    result &= testBonus105();

    return result;
  }

  /**
   * Test that a completed assignment that scores less than 95% has the correct bonus value added to
   * it when awardBonus() is called
   * 
   * @return true if getPoints returns 95.0 and false if otherwise
   */
  private static boolean testAwardBonus() {
    SimpleAssignment testAssignment = new SimpleAssignment(100, true);
    testAssignment.complete(90);
    testAssignment.awardBonus();

    if (testAssignment.getPoints() == 95.0) {
      return true;
    }
    return false;
  }

  /**
   * Verify that calling the awardBonus() method a second time does not modify the earned points
   * result
   * 
   * @return true if getPoints returns 95.0 and false if otherwise
   */

  private static boolean testBonusTwice() {
    SimpleAssignment testAssignment = new SimpleAssignment(100, true);
    testAssignment.complete(90);
    testAssignment.awardBonus();
    testAssignment.awardBonus();

    if (testAssignment.getPoints() == 95.0) {
      // System.out.println(testAssignment.getPoints());
      return true;
    }
    // System.out.println(testAssignment.getPoints());
    return false;
  }

  /**
   * Verify that calling the awardBonus() method on an assignment with NO bonus available does NOT
   * result in a bonus being applied to the earned points result
   * 
   * @return true if getPoints is equal to 90.0; false if otherwise
   */

  private static boolean testNoBonus() {
    SimpleAssignment testAssignment = new SimpleAssignment(100, false);
    testAssignment.complete(90);
    testAssignment.awardBonus();

    if (testAssignment.getPoints() == 90.0) {
      // System.out.println(testAssignment.getPoints());
      return true;

    }
    return false;
  }

  /**
   * Verify that calling the awardBonus() method on an assignment whose earned points are > 95% of
   * possible points does NOT result in a score that exceeds the total possible points
   * 
   * @return true if getPoints returns 100; false if otherwise
   */

  private static boolean testBonus105() {
    SimpleAssignment testAssignment = new SimpleAssignment(100, true);
    testAssignment.complete(97);
    testAssignment.awardBonus();

    if (testAssignment.getPoints() == 100.0) {
      // System.out.println(testAssignment.getPoints());
      return true;
    }
    return false;
  }

  // PROVIDED
  // This method calls a number of shorter helper methods, all of which test error cases
  // in the SimpleAssignment class.
  // By breaking these out into shorter tests, you can use the debugger to step through this
  // method and quickly determine which of the tests are failing, if any.
  /**
   * Helper method that tests multiple error cases in the SimpleAssignment class.
   * 
   * @return
   */
  public static boolean testSimpleErrorCases() {
    boolean result = testBadConstructorInput();
    result &= testBonusIncomplete();
    result &= testBadPointsEarned();
    result &= testCompleteAssignmentTwice();

    return result;
  }

  /**
   * Tests the SimpleAssignment constructor with bad input
   * 
   * @return true if inputing edge cases returns the correct points
   */
  private static boolean testBadConstructorInput() {
    // test with -1
    SimpleAssignment testAssignment = new SimpleAssignment(-1);
    double expectedPoints = 1.0;
    double actualPoints = testAssignment.POINTS_POSSIBLE;

    // test with 0
    SimpleAssignment testAssignment2 = new SimpleAssignment(0);
    double expectedPoints2 = 1.0;
    double actualPoints2 = testAssignment2.POINTS_POSSIBLE;
    return (expectedPoints == actualPoints && expectedPoints2 == actualPoints2);
  }

  /**
   * Test the awardBonus() method on an assignment that has bonus available but is not yet completed
   * 
   * @return true if getPoints returns the expected value after calling awardBonus
   */
  private static boolean testBonusIncomplete() {
    SimpleAssignment testAssignment = new SimpleAssignment(10, true);

    testAssignment.awardBonus();

    double expectedPoints = 0.0;
    double actualPoints = testAssignment.getPoints();

    return expectedPoints == actualPoints;
  }

  /**
   * Test the complete() method with input values outside of the allowed range and make sure that
   * the points returned are what you expect for the given error case
   * 
   * @return true if getPoints returns the expected value with bad input
   */
  private static boolean testBadPointsEarned() {

    // test with less than 0
    SimpleAssignment testAssignment = new SimpleAssignment(10, true);
    testAssignment.complete(0);
    double expectedPoints = 0;
    double actualPoints = testAssignment.getPoints();


    // greater than points possible
    SimpleAssignment testAssignment2 = new SimpleAssignment(10, true);
    testAssignment2.complete(20);
    double expectedPoints2 = 10;
    double actualPoints2 = testAssignment2.getPoints();

    return (expectedPoints == actualPoints) && (expectedPoints2 == actualPoints2);
  }

  /**
   * Test calling complete() twice with different values, and make sure that the earned point value
   * is NOT updated after the assignment has been completed
   * 
   * @return true if getPoints returns the expected value after completing the assignment twice
   */
  private static boolean testCompleteAssignmentTwice() {
    SimpleAssignment testAssignment = new SimpleAssignment(10, true);

    testAssignment.complete(10.0);
    testAssignment.complete(20.0);

    double expectedPoints = 10.0;
    double actualPoints = testAssignment.getPoints();


    return (expectedPoints == actualPoints);
  }

  // PROVIDED
  /**
   * This method reports whether all provided SimpleAssignmentTester methods have passed
   * 
   * @return true if all tests pass
   */
  public static boolean runAllTests() {
    return testAccessors() && testSimpleBonus() && testSimpleErrorCases();
  }

  public static void main(String[] args) {
    System.out.println("basic: " + (testAccessors() ? "PASS" : "fail"));
    System.out.println("bonus: " + (testSimpleBonus() ? "PASS" : "fail"));
    System.out.println("edge cases: " + (testSimpleErrorCases() ? "PASS" : "fail"));
  }

}
