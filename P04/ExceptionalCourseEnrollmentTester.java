//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Course Enrollment System
// Course:   CS 300 Fall 2023
//
// Author:   Madison Lin
// Email:    mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Aarya Gadekar
// Partner Email:   agadekar@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This utility class implements unit tests to check the correctness of methods
 * defined in the
 * ExceptionalCourseEnrollment class of the Exceptional Course Enrollment System
 * program.
 *
 */
public class ExceptionalCourseEnrollmentTester {

  /**
   * Ensures the correctness of the StudentRecord.equals() method.
   * 
   * Defines at least two StudentRecord objects and checks for the following test
   * cases:<BR>
   * (1) StudentRecord.equals() is expected to return true when passed a
   * StudentRecord with the same
   * campusID as the current one. You can compare a student record to itself.<BR>
   * (2) StudentRecord.equals() is expected to return false when passed a
   * StudentRecord with
   * campusID different from the campusID of the current student record. (3)
   * StudentRecord.equals()
   * is expected to return false when passed a String as input (4)
   * StudentRecord.equals() is
   * expected to return false when passed the reference null as input
   * 
   * 
   * @return true if and only if the tester verifies a correct functionality and
   *         false if at least
   *         one bug is detected
   */
  public static boolean studentRecordEqualsTester() {
    // TODO implement this method - DONE
    StudentRecord record1 = null;
    StudentRecord record2 = null;
    try {
      record1 = new StudentRecord("John", "john@wisc.edu", "1234567890", true);
      record2 = new StudentRecord("Jane", "jane@wisc.edu", "7890127890", false);
    } catch (DataFormatException e) {
      e.printStackTrace();
      return false;
    } catch (Exception e) {
      return false; // another exception was thrown
    }
    // check test cases
    // (1) StudentRecord.equals() is expected to return true for same campusID
    boolean testCase1 = record1.equals(record1);

    // (2) StudentRecord.equals() is expected to return false for different campusID
    boolean testCase2 = !record1.equals(record2);

    // (3) StudentRecord.equals() is expected to return false for String input
    boolean testCase3 = !record1.equals("John");

    // (4) StudentRecord.equals() is expected to return false for null input
    boolean testCase4 = !record1.equals(null);

    // return true if all test cases pass
    return testCase1 && testCase2 && testCase3 && testCase4;

    // return false; // default return statement
  }

  /**
   * Ensures the correctness of the constructor of the StudentRecord class when
   * called with VALID
   * input
   * 
   * @return true if and only if the tester verifies a correct functionality and
   *         false if at least
   *         one bug is detected
   */
  public static boolean studentRecordConstructorSuccessful() {
    // TODO implement this method - DONE
    try {
      StudentRecord record = new StudentRecord("John Doe", "john@wisc.edu", "1234567890",
          true);
      return (record != null && record.getName().equals("John Doe") &&
          record.getEmail().equals("john@wisc.edu") &&
          record.getCampusID().equals("1234567890") &&
          record.isPrerequisiteSatisfied());
    } catch (DataFormatException e) {
      e.printStackTrace();
      return false;
    } catch (Exception e) {
      return false; // another exception was thrown
    }
  }

  /**
   * Ensures the correctness of the constructor of the StudentRecord class when
   * called with one
   * INVALID input
   * 
   * @return true if and only if the tester verifies a correct functionality and
   *         false if at least
   *         one bug is detected
   */
  public static boolean studentRecordConstructorUnSuccessful() {
    // TODO implement this method
    try {
      StudentRecord record = new StudentRecord("", "john@wisc.edu", "1234567890", true);
      return false; // constructor did not throw an exception for invalid name
    } catch (DataFormatException e) {
      // expected exception, continue
    } catch (Exception e) {
      return false; // another exception was thrown
    }

    try {
      StudentRecord record = new StudentRecord("John Doe", "johnwisc.edu", "1234567890", true);
      return false; // constructor did not throw an exception for invalid email
    } catch (DataFormatException e) {
      // expected exception, continue
    } catch (Exception e) {
      return false; // another exception was thrown
    }

    try {
      StudentRecord record = new StudentRecord("John Doe", "john@wisc.edu", "123456789", true);
      return false; // constructor did not throw an exception for invalid campusID
    } catch (DataFormatException e) {
      // expected exception, continue
    } catch (Exception e) {
      return false; // another exception was thrown
    }

    return true; // all invalid inputs threw an exception
  }

  /**
   * Ensures the correctness of the searchById() method
   * 
   * Creates an ArrayList which contains at least 2 student records, and defines
   * at least two cases:
   * 
   * (1) successful search<BR>
   * (2) unsuccessful search<BR>
   * 
   * 
   * @throws NoSuchElementException if the search result is not found
   * @return true if and only if the tester verifies a correct functionality and
   *         false if at least
   *         one bug is detected
   */
  public static boolean searchByIdTester() {
    String errMsg = "Bug detected: search did not return the expected result.";
    try {
      // Create an arraylist which contains 3 student records
      ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
      StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
      StudentRecord s2 = new StudentRecord("Joey", "joey@wisc.edu", "1233367890", true);
      StudentRecord s3 = new StudentRecord("NotHere", "no@wisc.edu", "1111167890", true);
      records.add(s1);
      records.add(s2);

      // Finds a student in the arraylist

      StudentRecord r1 = ExceptionalCourseEnrollment.searchById(s1.getCampusID(), records);
      if (r1 != s1) {
        return false;
      }
      // Does'nt find a student not in the array
      try {
        ExceptionalCourseEnrollment.searchById(s3.getCampusID(), records);
        return false; // a NoSuchElementException was not thrown as expected
      } catch (NoSuchElementException e) {
        // check for the error message
        String expectedErrorMessage = "No student record found!";
        if (!e.getMessage().equals(expectedErrorMessage)) {
          System.out
              .println("The NoSuchElementException did not contain the expected error message!");
          return false;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(errMsg);
      return false;
    }

    try {

    } catch (Exception e) {
      return true;
    }
    return true;

  }

  // You are welcome but NOT required to implement additional tester methods at
  // your choice

  /**
   * This tester method checks if the isValidName method in StudentRecord class
   * works as expected.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean isValidNameTester() {
    // Test with a valid name
    if (!StudentRecord.isValidName("John Doe")) {
      return false;
    }
    // Test with a null name
    if (StudentRecord.isValidName(null)) {
      return false;
    }
    // Test with an empty name
    if (StudentRecord.isValidName("")) {
      return false;
    }
    return true;
  }

  /**
   * This tester method checks if the isValidEmail method in StudentRecord class
   * works as expected.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean isValidEmailTester() {
    // Test with a valid email
    if (!StudentRecord.isValidEmail("john@wisc.edu")) {
      return false;
    }
    // Test with a null email
    if (StudentRecord.isValidEmail(null)) {
      return false;
    }
    // Test with an invalid email
    if (StudentRecord.isValidEmail("john@wisc")) {
      return false;
    }
    return true;
  }

  /**
   * This tester method checks if the isValidCampusID method in StudentRecord
   * class works as expected.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean isValidCampusIDTester() {
    // Test with a valid campus ID
    if (!StudentRecord.isValidCampusID("1234567890")) {
      return false;
    }
    // Test with a null campus ID
    if (StudentRecord.isValidCampusID(null)) {
      return false;
    }
    // Test with an invalid campus ID
    if (StudentRecord.isValidCampusID("123456789")) {
      return false;
    }
    return true;
  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    boolean searchTesterOutput = searchByIdTester();
    System.out.println("searchTester: " + (searchTesterOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordEqualsTesterOutput = studentRecordEqualsTester();
    System.out.println(
        "studentRecordEqualsTester: " + (studentRecordEqualsTesterOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordConstructorSuccessfulOutput = studentRecordConstructorSuccessful();
    System.out.println("studentRecordConstructorSuccessful: "
        + (studentRecordConstructorSuccessfulOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordConstructorUnSuccessfulOutput = studentRecordConstructorUnSuccessful();
    System.out.println("studentRecordConstructorUnSuccessful: "
        + (studentRecordConstructorUnSuccessfulOutput ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");

    // additional tests
    boolean isValidNameTesterOutput = isValidNameTester();
    System.out.println(
        "isValidNameTester: " + (isValidNameTesterOutput ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean isValidEmailTesterOutput = isValidEmailTester();
    System.out.println(
        "isValidEmailTester: " + (isValidEmailTesterOutput ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    boolean isValidCampusIDTesterOutput = isValidCampusIDTester();
    System.out.println(
        "isValidCampusIDTester: " + (isValidCampusIDTesterOutput ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");

    return searchTesterOutput && studentRecordEqualsTesterOutput
        && studentRecordConstructorSuccessfulOutput;
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
