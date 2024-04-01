
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Course Enrollment Driver
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
// https://www.geeksforgeeks.org/multidimensional-arrays-in-java/ (syntax for 2D arrays)
// https://www.w3schools.com/java/java_switch.asp (to figure out how switch cases work)
//
///////////////////////////////////////////////////////////////////////////////


import java.util.Scanner;

/**
 * This class implements the Driver Application for cs300 Fall 2023 p01 Course Enrollment System
 *
 */
public class CourseEnrollmentDriver {

  // welcome, good bye, and syntax error messages
  private static final String WELCOME_MSG = "--- Welcome to the Course Enrollment System! ----";
  private static final String GOOD_BYE_MSG = "---------- BYE! Thanks for using our App! ----------";
  private static final String SYNTAX_ERROR_MSG = "Syntax Error: Please enter a valid command!";
  private static final String NO_COURSE_ENROLLMENT_MSG =
      "Error: Create a new course enrollment first!";


  /**
   * Main method that launches this driver application
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    // run application
    System.out.println(WELCOME_MSG); // display welcome message
    // Create a scanner to read the user inputs
    Scanner scanner = new Scanner(System.in);
    // read and process user command lines
    processUserCommands(scanner);
    scanner.close();// close the scanner
    System.out.println(GOOD_BYE_MSG);// display good bye message
  }


  /**
   * Prints out the menu of this application
   */
  private static void displayMenu() {
    System.out.println("\n================================ MENU ===============================");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <roster_capacity> <waitlist_capacity>] Create a new course enrollment");
    System.out.println("[2 <name>:<wisc_email>:<campus_ID>:boolean(true/false)] Enroll student");
    System.out.println("[3 <name>:<wisc_email>:<campus_ID>:boolean] Add student to waitlist");
    System.out.println("[4 <campus_ID>] Drop the course");
    System.out.println("[5] Print roster");
    System.out.println("[6] Print waitlist");
    System.out.println("[7] Logout and EXIT");
    System.out.println("-----------------------------------------------------------------------");
  }


  /**
   * Reads and processes user command lines
   */
  private static void processUserCommands(Scanner scanner) {

    String promptCommandLine = "ENTER COMMAND: ";
    String command = null; // variable to save the user command line

    // define a roster and a waitlist variables for the course enrollment, not yet initialized
    String[][] roster = null;
    int size = 0; // size of roster

    String[][] waitlist = null; // waitlist of the course

    // read and process user command lines until the user quits the application
    do {

      displayMenu(); // display the main menu
      // read user command line
      System.out.print(promptCommandLine);
      command = scanner.nextLine();
      // blank command
      if (command == null || command.isBlank()) {
        System.out.println(SYNTAX_ERROR_MSG); // syntax error message
        continue; // go to the next iteration
      }

      // Exit the loop if the user command is 7 for Quit
      if (command.charAt(0) == '7') {
        break; // exit the loop
      }

      // Create new course enrollment lists
      if (command.charAt(0) == '1') {
        // [1 <roster_capacity> <waitlist_capacity>] Create a new course enrollment
        String[] parts = command.split(" ");
        if (parts.length != 3) {// syntax error
          System.out.println(SYNTAX_ERROR_MSG);
          continue;
        }
        // valid syntax: create lists
        roster = CourseEnrollment.createEmptyList(Integer.parseInt(parts[1]));
        waitlist = CourseEnrollment.createEmptyList(Integer.parseInt(parts[2]));
        continue;
      }

      else { // any other type of command.
        // Check whether the course enrollment lists were created
        if (roster == null || waitlist == null) {
          System.out.println(NO_COURSE_ENROLLMENT_MSG);
          continue;
        }
        // process the user command line for the other options
        switch (command.charAt(0)) {
          // Enroll student
          case '2': // [2 <name>:<wisc_email>:<campus_ID>:boolean(true/false)] Enroll student
            // The boolean indicates whether the pre-requisites of the course are satisfied or not
            // get commands and put them into an array (split by " " and then by ":")
            String[] input = command.split(" ");
            if (input.length != 2) {
              System.out.println(SYNTAX_ERROR_MSG);
            }
            
            String[] studentRecordForRoster = input[1].split(":");

            if (studentRecordForRoster.length != 4) {
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            // input user input to enrollOneStudent
            size = CourseEnrollment.enrollOneStudent(studentRecordForRoster[0],
                studentRecordForRoster[1], studentRecordForRoster[2],
                Boolean.parseBoolean(studentRecordForRoster[3]), roster, size, waitlist);

            break;

          case '3': // [3 <name>:<wisc_email>:<campus_ID>:boolean] Add student to waitlist

            // split user input with " " and then ":"
            input = command.split(" ");
            if (input.length != 2) {
              System.out.println(SYNTAX_ERROR_MSG);
            }

            String[] studentRecordForWaitlist = input[1].split(":");

            if (studentRecordForWaitlist.length != 4) {
              System.out.println(SYNTAX_ERROR_MSG);
              continue;
            }
            
            // call addWaitlist using user input
            CourseEnrollment.addWaitlist(studentRecordForWaitlist[0], studentRecordForWaitlist[1],
                studentRecordForWaitlist[2], Boolean.parseBoolean(studentRecordForWaitlist[3]),
                waitlist);
            break;

          case '4': // [4 <campus_ID>] Drop the course
            
            // split input with " "
            input = command.split(" ");
            if (input.length != 2) {
              System.out.println(SYNTAX_ERROR_MSG);
            }
            // pass user input to dropCourse
            CourseEnrollment.dropCourse(input[1], roster, size);
            break;

          case '5': // [5] Print roster
            CourseEnrollment.printRoster(roster, size);
            break;

          case '6': // [6] Print waitlist
            CourseEnrollment.printWaitlist(waitlist);
            break;

          default:
            System.out.println(SYNTAX_ERROR_MSG); // Syntax Error

        }
      }

    } while (command.charAt(0) != '7');

  }

}

