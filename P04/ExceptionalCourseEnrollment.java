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

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;

// Below is a javadoc class header to complete
/**
 * This class is used to enroll students in courses.
 * 
 * @author Madison Lin
 *
 */

public class ExceptionalCourseEnrollment {
  /** Course name */
  private String courseName;
  /** Arraylist storing the records of students enrolled in this course */
  private ArrayList<StudentRecord> roster;
  /** enrollment capacity of this course enrollment */
  private int enrollmentCapacity;
  /**
   * Arraylist storing records of students in the waitlist (not yet enrolled in
   * the course)
   */
  private ArrayList<StudentRecord> waitlist;
  /** waitlist capacity */
  private int waitlistCapacity;

  /**
   * Constructor for ExceptionalCourseEnrollment. Initializes all the fields with
   * the corresponding
   * inputs. The roster and waitlist arraylists must be empty.
   * 
   * @param courseName         the name of the course
   * @param enrollmentCapacity the capacity of the course roster (INCLUSIVE,
   *                           between 15 and 250. That
   *                           is, 15 and 250 are allowed but 14 and 251 arent)
   * @param waitlistCapacity   the capacity of the waitlist (must be GREATER than
   *                           0 and LESS OR
   *                           EQUAL TO than the enrollmentCapacity)
   * @throws IllegalArgumentException with message "Course name must not be blank
   *                                  or empty" if
   *                                  course name is blank or empty
   * @throws IllegalArgumentException with message "Enrollment capacity must be
   *                                  between 15 and 250!"
   *                                  if enrollment capacity is not between 15 and
   *                                  250, inclusive
   * @throws IllegalArgumentException with message "Waitlist capacity must be
   *                                  between 0 and
   *                                  enrollment capacity!" if waitlistCapacity is
   *                                  larger than
   *                                  enrollmentCapacity or less than zero
   */
  public ExceptionalCourseEnrollment(String courseName, int enrollmentCapacity,
      int waitlistCapacity) throws IllegalArgumentException {
    // initiallize fields
    this.courseName = courseName;
    this.enrollmentCapacity = enrollmentCapacity;
    this.waitlistCapacity = waitlistCapacity;
    roster = new ArrayList<StudentRecord>();
    waitlist = new ArrayList<StudentRecord>();

    // throw exceptions
    if (courseName == null || courseName == "") {
      throw new IllegalArgumentException("Course name must not be blank or empty");
    }
    if (enrollmentCapacity >= 250 || enrollmentCapacity <= 0) {
      throw new IllegalArgumentException("Enrollment capacity must be between 0 and 250!");
    }
    if (waitlistCapacity <= 0 || waitlistCapacity > enrollmentCapacity) {
      throw new IllegalArgumentException("Waitlist capacity must be between 0 and enrollment capacity!");
    }

  }

  /**
   * Checks if the roster is full.
   *
   * @return true if the size of the roster is equal to the enrollment capacity,
   *         false otherwise.
   */
  public boolean isRosterFull() {
    // TOOD: Implement this method with respect to the javadocs
    if (roster.size() == enrollmentCapacity)
      return true;
    return false; // default return
  }

  /**
   * Checks if the waitlist is full.
   *
   * @return true if the size of the waitlist is equal to the waitlist capacity,
   *         false otherwise.
   */
  public boolean isWaitlistFull() {
    // TOOD: Implement this method with respect to the javadocs
    if (waitlist.size() == waitlistCapacity)
      return true;
    return false; // default return
  }

  /**
   * Checks if the course enrollment is closed. A course enrollment is considered
   * closed if both the
   * roster and the waitlist are full.
   *
   * @return true if both the roster and the waitlist are full, false otherwise.
   */
  public boolean isCourseEnrollmentClosed() {
    // TOOD: Implement this method with respect to the javadocs
    if (isRosterFull() && isWaitlistFull())
      return true;
    return false; // default return
  }

  /**
   * Getter for course name
   * 
   * @return string the name of the course
   */
  public String getName() {
    // TOOD: Implement this method with respect to the javadocs
    return courseName; // default return
  }

  /**
   * Returns a deep copy (NOT the deepest) of this course enrollment's roster
   * 
   * @return a deep copy of the roster, and null if roster is null
   */
  public ArrayList<StudentRecord> deepCopyRoster() {
    // TOOD: Implement this method with respect to the javadocs
    ArrayList<StudentRecord> copy = new ArrayList<StudentRecord>();
    if (roster != null) {
      for (StudentRecord record : roster) {
        copy.add(record);
      }
      return copy;
    }
    return null; // default return
  }

  /**
   * Returns a deep copy (NOT the deepest) of this course enrollment's waitlist
   * 
   * @return a deep copy of the waitlist, and null if waitlist is null
   */
  public ArrayList<StudentRecord> deepCopyWaitlist() {
    ArrayList<StudentRecord> copy = new ArrayList<StudentRecord>();
    if (waitlist != null) {
      for (StudentRecord record : waitlist) {
        copy.add(record);
      }
      return copy;
    }
    return null; // default return
  }

  /**
   * Expands the enrollment capacity of the course by the increase amount. Does
   * not affect the
   * waitlist at all.
   * 
   * @param increase the non-negative amount to increase the capacity by
   * @throws IllegalArgumentException with message "Increase amount must be
   *                                  greater than zero!" if
   *                                  increase is not larger than zero
   */
  public void expandEnrollmentCapacity(int increase) throws IllegalArgumentException {
    // TOOD: Implement this method with respect to the javadocs
    if (increase <= 0)
      throw new IllegalArgumentException("Increase amount must be greater than zero!");
    enrollmentCapacity += increase;

  }

  // PROVIDED METHOD
  /**
   * Prints the list of all the students in the waitlist of the course, with
   * respect to the
   * following format.
   * 
   * Waitlist capacity: waitlist_capacity<BR>
   * 1. student1's string representation<BR>
   * 2. student2's string representation <BR>
   * 
   * Every entry must be in a newline. Each of the students records is printed in
   * the format:
   * "order. name, email, campusID<BR>
   * 
   * where order starts at 1 for the student stored at index 0, name, email, and
   * campusID represent
   * the name, email address, and campusID of the waitlisted student.
   * 
   * We assume all inputs are valid. If the waitlist is empty, you must print the
   * capacity followed
   * by "The waitlist is empty." on a newline.
   */
  public void printWaitlist() {

    System.out.println("Waitlist capacity: " + this.waitlistCapacity);
    if (this.waitlist.isEmpty()) {
      System.out.println("The waitlist is empty.");
    } else {
      for (int i = 0; i < this.waitlist.size(); i++) {
        String waitlistString = (i + 1) + ". " + this.waitlist.get(i) + "\n";
        System.out.println(waitlistString);
      }
    }

  }

  /**
   * Returns the student record object that has an exact match with campusID in
   * the list passed as
   * input. We assume that campusID values are unique.
   * 
   * @param campusID a string representing the campusID of a student.
   * @param list     an ArrayList of StudentRecords
   * @return StudentRecord record in list with an exact match with campusID.
   * @throws NoSuchElementException with message "No student record found!" if no
   *                                match found in the
   *                                input list or if campusID is NOT valid.
   * 
   */
  public static StudentRecord searchById(String campusID, ArrayList<StudentRecord> list) throws NoSuchElementException {
    // TOOD: Implement this method with respect to the javadocs
    for (StudentRecord record : list) {
      if (record.getCampusID().equals(campusID))
        return record;
    }
    throw new NoSuchElementException("No student record found!");
  }

  /**
   * Appends (adds to the end) the student record to the waitlist if the waitlist
   * has space, the
   * student isn't already on the waitlist, isn't already enrolled in the course,
   * and they meet the
   * preReqs.
   * Prints student.getName() + " was successfully added to the waitlist." if
   * successful
   * Throws exceptions described below.
   * 
   * @param student valid StudentRecord of student to be added
   * @throws IllegalArgumentException if the student is already on the waitlist
   *                                  with message "That
   *                                  student is already on the waitlist!"
   * @throws IllegalArgumentException if the student is already enrolled in the
   *                                  course with message
   *                                  "That student is already enrolled!"
   * @throws IllegalStateException    if the waitlist is full with the message
   *                                  "The waitlist is
   *                                  full!"
   * @throws IllegalStateException    if the student does not have satisfactory
   *                                  prerequisites with
   *                                  message "The prerequisities are not
   *                                  satisfied for that
   *                                  course!"
   */
  public void addWaitlist(StudentRecord student) {
    // TODO complete the implementation of this method
    // Add implementation-level comments to highlight the major algorithmic steps to
    // resolve this
    // method FIRST!
    // You can use ArrayList.contains() method to check whether there is a match
    // with student in
    // roster or waitlist

    // check if student is on waitlist
    if (waitlist.contains(student))
      throw new IllegalArgumentException("That student is already on the waitlist!");
    // check if student is already enrolled
    if (roster.contains(student))
      throw new IllegalArgumentException("That student is already enrolled!");
    // check if waitlist is full
    if (isWaitlistFull())
      throw new IllegalStateException("The waitlist is full!");
    // check if prereqs are satisfied
    if (!student.isPrerequisiteSatisfied())
      throw new IllegalStateException("The prerequisities are not satisfied for that course!");
    // if it passes all the conditions above, add the student
    waitlist.add(student);

  }

  /**
   * Enrolls one student given their StudentRecord. Only enrolls the student if
   * the following<br>
   * conditions are met, otherwise throws an appropriate error described below:
   * <br>
   * - student is not already enrolled in the course <br>
   * - the course has space <br>
   * - the student has satisfied the prerequisities<br>
   * Prints student.getName() + " was successfully enrolled in this class." if the
   * enrollment was
   * successful. <br>
   * Removes the student from the waitlist if they were on it.<br>
   * 
   * @param student StudentRecord for the student to add
   * @throws IllegalStateException with message "That student is already
   *                               enrolled!" is the student
   *                               is already enrolled
   * @throws IllegalStateException with message "The course is full." if the
   *                               course is full. The
   *                               course is considered full when the roster's
   *                               size equals the
   *                               enrollment capacity.
   * @throws IllegalStateException with message "That student has not satisfied
   *                               the prerequisites!"
   *                               if student does not have the appropriate
   *                               prerequisities
   */
  public void enrollOneStudent(StudentRecord student) {
    // TODO Implement this method with respect to the details provided in its
    // javadoc header
    // [Hint] You can use ArrayList.contains() and ArrayList.remove(Object) to
    // implement this method
    // check if student is not already in the course
    if (roster.contains(student))
      throw new IllegalStateException("That student is already enrolled!");
    // check if the course has space
    if (isRosterFull())
      throw new IllegalStateException("The course is full.");
    // check if the student has satisfied the prereqs
    if (!student.isPrerequisiteSatisfied())
      throw new IllegalStateException("That student has not satisfied the prerequisites!");
    // remove student from waitlist and add to roster
    for (StudentRecord record : roster) {
      if (record.getCampusID().equals(student.getCampusID()))
        waitlist.remove(record);
    }
    roster.add(student);
    System.out.println(student.getName() + " was successfully enrolled in this class.");
  }

  /**
   * Removes a student from the roster based on a matching campusID
   * 
   * @param student the student's StudentRecord
   * @throws NoSuchElementException with message "There is no matching student in
   *                                the roster!" if
   *                                the student is not in the roster
   */
  public void dropCourse(StudentRecord student) throws NoSuchElementException {
    // TOOD: Implement this method with respect to the javadocs
    if (!roster.contains(student))
      throw new NoSuchElementException("There is no matching student in the roster!");
    // loop and remove record
    for (StudentRecord record : roster) {
      if (record.getCampusID().equals(student.getCampusID()))
        roster.remove(record);
    }

  }

  // PROVIDED Method
  /**
   * Returns a String representation of this exceptional course enrollment The
   * string should be of
   * the form: <BR>
   * Course Name: courseName<BR>
   * Number of enrolled students: number of enrolled students<BR>
   * 1. name, email, campusID, preReq <BR>
   * 2. name, email, campusID, preReq <BR>
   * ...<BR>
   * 
   * Every entry must be in a newline. Each of the students records is printed in
   * the format:
   * "order. name, email, campusID, preReq"
   * 
   * where order represents index+1 of the student records in roster (orders are
   * in the range
   * 1..size and NOT in the range 0..size-1), name, email, and campusID represent
   * the name, email
   * address, and campusID of the enrolled student.
   * 
   * @return a String representation of this exceptional course enrollment
   */
  // TODO Uncomment the below methods out after declaring the data fields
  @Override
  public String toString() {
    // Provided to students
    String rosterString = "";
    rosterString = rosterString + "Course Name: " + this.courseName + "\n";
    rosterString = rosterString + "Number of enrolled students: " + this.roster.size() + "\n";
    for (int i = 0; i < this.roster.size(); i++) {
      String studentString = this.roster.get(i).toString();
      rosterString = rosterString + (i + 1) + ". " + studentString + "\n";
    }
    return rosterString.trim();
  }

  // PROVIDED METHOD
  /**
   * Returns a string of the roster of the course, with the string representation
   * of each
   * StudentRecord stored in the ArrayList roster in a separate line.
   * 
   * @return String representing the roster to the above specifications
   */
  // TODO Uncomment the below methods out after declaring the data fields
  public String rosterToString() {
    String rosterString = "";
    for (int i = 0; i < this.roster.size(); i++) {
      rosterString += this.roster.get(i) + "\n";
    }
    return rosterString.trim();

  }

  /**
   * Saves the string representation of the roster to a file passed as input. Does
   * this by calling
   * the rosterToString() method and writing the string to a file.
   * 
   * You can use a PrintWriter or a FileWriter to do this.
   * 
   * Catches and prints the message associated with any IOException that might be
   * thrown.
   * 
   * @param file the path of the output file
   */
  public void saveRoster(File file) {
    // TOOD: Implement this method with respect to the javadocs
    try {
      FileWriter output = new FileWriter(file);
      output.write(rosterToString());
      output.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Helper method to parse a line from a loaded roster and convert it to a
   * StudentRecord object.
   * The line represents a String representation of a student. Extra whitespace at
   * the beginning and
   * end of the line should be disregarded.
   * 
   * A String representation of a StudentRecord should be at the following format:
   * <BR>
   * name, email, campusID, preReqValue
   * 
   * Where name represents the name of a student,<BR>
   * email represents the email address of a student,<BR>
   * campusID represents the campus ID of a student,<BR>
   * preReqValue should be parsable to a boolean telling whether the
   * pre-requisites of the course
   * are satisfied.
   * 
   * 
   * @param line a string representing a student from a saved roster
   * @return StudentRecord the StudentRecord generated from that line
   * 
   * @throws DataFormatException if the line is not formatted correctly. A line is
   *                             not correctly
   *                             formatted if it is not at the above format where
   *                             name, email,
   *                             campusID, preReqValue are valid and separated by
   *                             ", ".
   */
  private StudentRecord lineToRecord(String line) throws DataFormatException {
    // TOOD: Implement this method with respect to the javadocs
    StudentRecord newStudent;
    // split string by comma
    String[] data = line.split(", ");
    // get rid of whitespace
    for (int i = 0; i < data.length - 1; i++) {
      data[i].strip();
    }
    String name = data[0];
    String email = data[1];
    String campusID = data[2];
    boolean preReqValue = Boolean.parseBoolean(data[3]);
    newStudent = new StudentRecord(name, email, campusID, preReqValue);

    if (StudentRecord.isValidName(name) && StudentRecord.isValidEmail(email)
        && StudentRecord.isValidCampusID(campusID)) {
      return newStudent;
    }
    // throw exception if format is incorrect
    else {
      throw new DataFormatException("Line is not formatted correctly");
    }
  }

  /**
   * Loads a roster in from a file. The file contains string representations of
   * StudentRecords each
   * in a separate file.
   * 
   * Enrolls each student until the end of the file or the capacity of the roster
   * is reached.
   * 
   * Catches FileNotFoundException and prints the message "Could not find that
   * file!"
   * 
   * @throws IllegalStateException with message "The course capacity would be
   *                               exceeded by loading
   *                               that student!" if the roster size would be
   *                               exceeded after adding
   *                               that student.
   * @param rosterFile file object to read
   */
  public void loadRoster(File rosterFile) throws IllegalStateException, DataFormatException {
    try (Scanner scnr = new Scanner(rosterFile)) {
      while (scnr.hasNextLine()) {
        try {
          if (roster.size() <= enrollmentCapacity) {
            enrollOneStudent(lineToRecord(scnr.nextLine()));
          } else {
            throw new IllegalStateException("The course capacity would be exceeded by loading that student!");
          }
        } catch (IllegalStateException | DataFormatException e) {
          System.out.println("Error enrolling student: " + e.getMessage());
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not find that file!");
    }
  }

}
