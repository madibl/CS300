//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Course Enrollment Tester
// Course: CS 300 Fall 2023
//
// Author: Madison Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:
// https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/Boolean.html (to look at the
// methods for the Boolean class)
//
///////////////////////////////////////////////////////////////////////////////


import java.util.Arrays;

/**
 * This utility class implements unit tests to check the correctness of methods defined in the
 * CourseEnrollment class of the Course Enrollment System program.
 *
 */
public class CourseEnrollmentTester {


  /**
   * Ensures the correctness of the createEmptyList() method
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean createEmptyListTester() {
    String errMsg = "Bug detected: createEmptyList did not return the expected array.";
    // Create an empty list String[][] whose capacity is 5
    String[][] actual = CourseEnrollment.createEmptyList(5);
    String[][] expected = new String[5][];
    // same as expected = new String[][]{null, null, null, null, null}

    if (!Arrays.deepEquals(actual, expected)) {
      // Recommended: descriptive error messages to help locating the bug
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }


    // Try a different capacity: create an empty list String[][] whose capacity is 8
    actual = CourseEnrollment.createEmptyList(8);
    expected = new String[8][];
    // same as expected = new String[][]{null, null, null, null, null}

    if (!Arrays.deepEquals(actual, expected)) {
      // descriptive error messages to help locating the bug
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }

    return true; // expected behavior verified, no bugs detected!
  }

  /**
   * Ensures the correctness of the indexOf(String, String[][]) method
   * 
   * Expected behavior to be verified:<BR>
   * (+) Correct index returned for multiple cases (normal and edge cases)<BR>
   * (+) No changes made to the contents of the input list<BR>
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean indexOfPerfectSizeArrayTester() {
    // (1) edge case: match found at index 0
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}};
      int expectedIndex = 0;
      int actualIndex = CourseEnrollment.indexOf("1234567890", testerArray);

      if (actualIndex != expectedIndex) {
        System.out.println("Problem with (1)");
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }

    // (2) edge case: match found at index length-1 considering a full input array
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}};
      int expectedIndex = testerArray.length - 1;
      int actualIndex = CourseEnrollment.indexOf("12233445678", testerArray);
      if (actualIndex != expectedIndex) {
        System.out.println("Problem with (2)");
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }

    // (3) normal case: match found at the middle of the input array
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}};
      int expectedIndex = 2;
      int actualIndex = CourseEnrollment.indexOf("5678901234", testerArray);
      if (actualIndex != expectedIndex) {
        System.out.println("Problem with (3)");
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }

    // (4) normal case: no match found, -1 should be returned
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"}};
      int expectedIndex = -1;
      int actualIndex = CourseEnrollment.indexOf("563948602", testerArray);
      if (actualIndex != expectedIndex) {
        System.out.println("Problem with (4)");
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }
    // if all tests pass, return true
    return true;
  }

  /**
   * Ensures the correctness of the indexOf(String, String[][], int) method
   * 
   * Expected behavior to be verified:<BR>
   * (+) Correct index returned for multiple cases (normal and edge cases)<BR>
   * (+) No changes made to the contents of the input list<BR>
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean indexOfOversizeSizeArrayTester() {
    // (1) edge case: index of 0
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}, null, null, null};
      int size = 4;
      int expectedIndex = 0;
      int actualIndex = CourseEnrollment.indexOf("1234567890", testerArray, size);

      if (actualIndex != expectedIndex) {
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }
    // (2) edge case: index of size-1
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}, null, null, null};
      int size = 4;
      int expectedIndex = size - 1;
      int actualIndex = CourseEnrollment.indexOf("12233445678", testerArray, size);

      if (actualIndex != expectedIndex) {
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }

    // (3) normal case: middle of array
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}, null, null, null};
      int size = 4;
      int expectedIndex = 2;
      int actualIndex = CourseEnrollment.indexOf("5678901234", testerArray, size);

      if (actualIndex != expectedIndex) {
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }

    // (4) normal case: no match found (-1)
    {
      String[][] testerArray = {{"Mouna", "mouna@wisc.edu", "1234567890"},
          {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
          {"Madi", "mblin@wisc.edu", "12233445678"}, null, null, null};
      int size = 4;
      int expectedIndex = -1;
      int actualIndex = CourseEnrollment.indexOf("128343829", testerArray, size);

      if (actualIndex != expectedIndex) {
        System.out.println("Expected: " + expectedIndex);
        System.out.println("Actual: " + actualIndex);
        return false;
      }
    }
    // if all the tests pass, return true
    return true;
  }

  // Helper method to compare actual and expected oversize roster arrays
  /**
   * Helper method defined to help verifying the actual roster and waitlist arrays with respect to
   * the expected ones
   * 
   * @param methodName     name of the method being tested
   * @param actualRoster   actual roster
   * @param expectedRoster expected roster
   * @param actualSize     actual roster size
   * @param expectedSize   expected roster size
   * 
   * @return true if expected behavior satisfied, false if any bug is detected
   */
  private static boolean assessDeepEqualOversizeArraysHelper(String methodName,
      String[][] actualRoster, String[][] expectedRoster, int actualSize, int expectedSize) {
    // error messages
    String errMsgBadSize =
        "Bug detected: Your " + methodName + "() method did not return the expected size.";
    String errMsgBadRoster = "Bug detected: The contents of the roster array was not as expected "
        + "after " + "your " + methodName + "() method returned.";

    // check roster size
    if (actualSize != expectedSize) {
      System.out.println(errMsgBadSize);
      System.out.println("Expected size: " + expectedSize + ". Actual size: " + actualSize);
      return false;
    }

    // compare roster contents
    if (!Arrays.deepEquals(actualRoster, expectedRoster)) {
      System.out.println(errMsgBadRoster);
      System.out.println("Expected Roster: " + Arrays.deepToString(expectedRoster));
      System.out.println("Actual Roster: " + Arrays.deepToString(actualRoster));
      return false;
    }

    return true; // expected behavior satisfied, no bugs detected
  }

  // Helper method to compare actual and expected oversize roster arrays
  /**
   * Helper method defined to help verifying the actual roster and waitlist arrays with respect to
   * the expected ones
   * 
   * @param methodName       name of the method being tested
   * @param actualWaitlist   actual waitlist
   * @param expectedWaitlist expected waitlist
   * 
   * @return true if expected behavior satisfied, false if any bug is detected
   */
  private static boolean assessDeepEqualPerfectSizeArraysHelper(String methodName,
      String[][] actualWaitlist, String[][] expectedWaitlist) {
    // error message
    String errMsgBadWaitlist =
        "Bug detected: The contents of the waitlist array was not as expected after " + "your "
            + methodName + "() method returned";

    // compare waitlist contents
    if (!Arrays.deepEquals(actualWaitlist, expectedWaitlist)) {
      System.out.println(errMsgBadWaitlist);
      System.out.println("Expected Waitlist: " + Arrays.deepToString(expectedWaitlist));
      System.out.println("Actual Waitlist: " + Arrays.deepToString(actualWaitlist));
      return false;
    }
    return true; // expected behavior satisfied, no bugs detected
  }

  /**
   * Ensures the correctness of the enrollOneStudent() method when called to enroll one student
   * record in the course. The course did not reach its capacity and the course pre-requisites are
   * satisfied.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Student record correctly added to the end of the roster array<BR>
   * (+) No changes made to the waitlist array<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean enrollOneStudentTester() {
    // You do not need to make changes to this method
    // create a waitlist array. We can consider a normal case: not-empty and not-full waitlist)
    String[][] actualWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};

    // No changes to the waitlist are expected
    String[][] expectedWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};

    // This method considers three test cases:
    // (1) edge case: adding to an empty roster
    // (2) normal case: adding to the end of a non-empty roster
    // (3) edge case: after adding the student record, the roster is full

    // To avoid code redundancy, we defined a helper method named verifyCorrectBehaviorHelper() to
    // help verifying the expected behavior for each of the above test cases.

    // --------------------------------------------------------------------------
    // (1) edge case Enroll a student considering an empty roster oversize array
    // enroll one student satisfying prerequisites
    // Create an empty roster
    String[][] actualRoster = new String[3][];
    int actualSize = 0;

    // Try to enroll George
    actualSize = CourseEnrollment.enrollOneStudent("George", "george@wisc.edu", "9780563421", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    String[][] expectedRoster =
        new String[][] {{"George", "george@wisc.edu", "9780563421"}, null, null};
    int expectedSize = 1;
    boolean resultCase1 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // --------------------------------------------------------------------------
    // (2) normal case: adding to the end of a non-empty roster
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};
    actualSize = 2;

    // Try to enroll Matt
    actualSize = CourseEnrollment.enrollOneStudent("Matt", "matt@wisc.edu", "9745632180", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    expectedSize = 3;
    boolean resultCase2 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // --------------------------------------------------------------------------
    // (3) edge case: after adding the student record, the roster is full
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    actualSize = 3;

    // Try to enroll Lisa
    actualSize = CourseEnrollment.enrollOneStudent("Lisa", "lisa@wisc.edu", "9784563211", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"},
        {"Lisa", "lisa@wisc.edu", "9784563211"}};
    expectedSize = 4;
    boolean resultCase3 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // Verify that all the above enrollOneStudent() method calls did not make any changes to the
    // contents of the input waitlist
    boolean assessWaitlistContents = assessDeepEqualPerfectSizeArraysHelper("enrollOneStudent",
        actualWaitlist, expectedWaitlist);

    // test passes only if each of the defined test scenarios passes
    return resultCase1 && resultCase2 && resultCase3 && assessWaitlistContents;
  }


  /**
   * Ensures the correctness of the enrollOneStudent() method when called to enroll one student
   * record in the course. The student record is in the waitlist, course pre-requisites are
   * satisfied, and there is room in the roster to enroll the student in the course.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Student record correctly added to the end of the roster array<BR>
   * (+) Matching student correctly record removed off the waitlist<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean enrollOneStudentMoveFromWaitlistTester() {
    // TODO complete the implementation of this method


    String[][] expectedRoster = {{"Mouna", "mouna@wisc.edu", "1234567890"},
        {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
        {"John", "jsmith@wisc.edu", "0987654321"}, null};
    String[][] actualRoster =
        {{"Mouna", "mouna@wisc.edu", "1234567890"}, {"Michelle", "michelle@wisc.edu", "2345678901"},
            {"Mark", "mark@wisc.edu", "5678901234"}, null, null};
    String[][] actualWaitlist =
        {{"Santa", "sclaus@wisc.edu", "9999999999"}, {"John", "jsmith@wisc.edu", "0987654321"},
            {"Bob", "bross@wisc.edu", "1111111111"}, {"Joe", "jbiden@wisc.edu", "2222222222"}};
    String[][] expectedWaitlist = {{"Santa", "sclaus@wisc.edu", "9999999999"}, null,
        {"Bob", "bross@wisc.edu", "1111111111"}, {"Joe", "jbiden@wisc.edu", "2222222222"}};

    int actualSize = CourseEnrollment.enrollOneStudent("John", "jsmith@wisc.edu", "0987654321",
        true, actualRoster, 3, actualWaitlist);
    int expectedSize = 4;

    // check if (1) record added to end of array
    boolean test1 = assessDeepEqualPerfectSizeArraysHelper("enrollOneStudent", actualWaitlist,
        expectedWaitlist);
    // check if (2) student is removed off waitlist
    boolean test2 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);
    // if all tests pass, return true
    return test1 && test2;
  }

  /**
   * Ensures the correctness of the dropCourse() method when called to remove an existing student
   * record from a course enrollment roster of the class.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Matching student record correctly removed off the input roster array. Order of precedence
   * of the student records must be preserved.<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean successfulDropCourseTester() {

    // edge case: remove last person
    String[][] expectedRoster =
        {{"Mouna", "mouna@wisc.edu", "1234567890"}, {"Michelle", "michelle@wisc.edu", "2345678901"},
            {"Mark", "mark@wisc.edu", "5678901234"}, null, null};

    String[][] actualRoster = {{"Mouna", "mouna@wisc.edu", "1234567890"},
        {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
        {"John", "jsmith@wisc.edu", "0987654321"}, null};

    int expectedSize = 3;
    int actualInitialSize = 4;
    int actualSize = CourseEnrollment.dropCourse("0987654321", actualRoster, actualInitialSize);


    // check if roster is correct
    boolean test1 = assessDeepEqualOversizeArraysHelper("dropCourse", actualRoster, expectedRoster,
        actualSize, expectedSize);

    // edge case: dropping first person
    String[][] expectedRoster2 =
        {{"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
            {"John", "jsmith@wisc.edu", "0987654321"}, null, null};

    String[][] actualRoster2 = {{"Mouna", "mouna@wisc.edu", "1234567890"},
        {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
        {"John", "jsmith@wisc.edu", "0987654321"}, null};

    int expectedSize2 = 3;
    int actualInitialSize2 = 4;
    int actualSize2 = CourseEnrollment.dropCourse("1234567890", actualRoster2, actualInitialSize2);

    boolean test2 = assessDeepEqualOversizeArraysHelper("dropCourse", actualRoster2,
        expectedRoster2, actualSize2, expectedSize2);

    // normal case: dropping second person
    String[][] expectedRoster3 =
        {{"Mouna", "mouna@wisc.edu", "1234567890"}, {"Mark", "mark@wisc.edu", "5678901234"},
            {"John", "jsmith@wisc.edu", "0987654321"}, null, null};

    String[][] actualRoster3 = {{"Mouna", "mouna@wisc.edu", "1234567890"},
        {"Michelle", "michelle@wisc.edu", "2345678901"}, {"Mark", "mark@wisc.edu", "5678901234"},
        {"John", "jsmith@wisc.edu", "0987654321"}, null};

    int expectedSize3 = 3;
    int actualInitialSize3 = 4;
    int actualSize3 = CourseEnrollment.dropCourse("2345678901", actualRoster3, actualInitialSize3);

    boolean test3 = assessDeepEqualOversizeArraysHelper("dropCourse", actualRoster3,
        expectedRoster3, actualSize3, expectedSize3);


    // if all tests pass, return true
    return test1 && test2 && test3;
  }

  /**
   * Ensures the correctness of the dropCourse() method when called to remove a non-existing student
   * record from a course enrollment roster of the class.
   * 
   * Expected behavior to be verified:<BR>
   * (+) No changes made to the input roster array: Fail to find a matching student record .<BR>
   * (+) Correct size returned (same size passed as input to the method)
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean unsuccessfulDropCourseTester() {
    // roster that has the expected result
    String[][] expectedRoster =
        {{"Mouna", "mouna@wisc.edu", "1234567890"}, {"Michelle", "michelle@wisc.edu", "2345678901"},
            {"John", "jsmith@wisc.edu", "0987654321"}, null, null};
    // roster that will go through the method, and have a person dropped
    String[][] actualRoster =
        {{"Mouna", "mouna@wisc.edu", "1234567890"}, {"Michelle", "michelle@wisc.edu", "2345678901"},
            {"John", "jsmith@wisc.edu", "0987654321"}, null, null};
    int expectedSize = 3;
    int actualInitialSize = 3;
    
    // test with campusId not in any list
    int actualSize = CourseEnrollment.dropCourse("8271234829", actualRoster, actualInitialSize);

    // check if roster is correct
    if (!Arrays.deepEquals(actualRoster, expectedRoster)) {
      System.out.println("Expected Roster: " + Arrays.deepToString(expectedRoster));
      System.out.println("Actual Roster: " + Arrays.deepToString(actualRoster));
      return false;
    }
    
    // check if size is correct
    if (actualSize != expectedSize) {
      System.out.println("Expected Size: " + expectedSize);
      System.out.println("Actual Size: " + actualSize);
      return false;
    }
    // if all tests pass, return true
    return true;
  }


  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    boolean createEmptyListTesterResult = createEmptyListTester();
    System.out
        .println("createEmptyListTester: " + (createEmptyListTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean indexOfOversizeSizeArrayTesterResult = indexOfOversizeSizeArrayTester();
    System.out.println("indexOfOversizeSizeArrayTester: "
        + (indexOfOversizeSizeArrayTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean indexOfPerfectSizeArrayTesterResult = indexOfPerfectSizeArrayTester();
    System.out.println("indexOfPerfectSizeArrayTester: "
        + (indexOfPerfectSizeArrayTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentTesterResult = enrollOneStudentTester();
    System.out
        .println("enrollOneStudentTester: " + (enrollOneStudentTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentMoveFromWaitlistTesterResult = enrollOneStudentMoveFromWaitlistTester();
    System.out.println("enrollOneStudentMoveFromWaitlistTester: "
        + (enrollOneStudentMoveFromWaitlistTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean successfulDropCourseTesterResult = successfulDropCourseTester();
    System.out.println(
        "successfulDropCourseTester: " + (successfulDropCourseTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean unsuccessfulDropCourseTesterResult = unsuccessfulDropCourseTester();
    System.out.println("unsuccessfulDropCourseTester: "
        + (unsuccessfulDropCourseTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");

    return createEmptyListTesterResult && indexOfOversizeSizeArrayTesterResult
        && indexOfPerfectSizeArrayTesterResult && enrollOneStudentTesterResult
        && enrollOneStudentMoveFromWaitlistTesterResult && successfulDropCourseTesterResult
        && unsuccessfulDropCourseTesterResult;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
