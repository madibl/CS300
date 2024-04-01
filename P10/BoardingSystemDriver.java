//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LAST ONE BEST ONE: P10 BOARDING SYSTEM
// Course:   CS 300 Fall 2023
//
// Author:   Madison B Lin
// Email:    mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class models and implements an airplane boarding system.
 *
 */
public class BoardingSystemDriver {
  // welcome, good bye, and syntax error messages
  private static String preBoardingAnnouncement =
      "==========================================================================\n"
          + "                      AIRPLANE BOARDING SYSTEM \n"
          + "==========================================================================\n\n"
          + "Welcome to our airline company. We are starting boarding in a few minutes.\n"
          + "We invite our passengers to present to the boarding queue.\n"
          + "Please have your boarding pass and identification ready.\n"
          + "Boarding priority depends on your boarding group.\n"
          + "Group A passengers have the highest priority.\n" + "Thank you.";

  private static String postBoardingAnnouncement =
      "Thank you for choosing our airline.\n" + "We wish you a pleasant and enjoyable flight!";
  private static String syntaxErrorMessage = "Syntax Error: Please enter a valid command!";

  // instance fields
  private BoardingQueue boardingQueue; // priority boarding queue storing passengers
  private ArrayList<Passenger> passengersOnBoard; // list of boarded passengers
  private Scanner scanner; // scanner to read user input command lines


  // PROVIDED Constructor
  /**
   * Creates and initializes a new BoardingSystem object
   * 
   * @param capacity capacity of the admission queue
   * @throws IllegalArgumentException if capacity is negative
   * 
   */
  public BoardingSystemDriver(int capacity) {
    if (capacity < 0)
      throw new IllegalArgumentException("Invalid boarding capacity!");
    boardingQueue = new BoardingQueue(capacity);
    passengersOnBoard = new ArrayList<Passenger>();
    scanner = new Scanner(System.in);

  }

  // PROVIDED main method
  /**
   * Main method that launches this driver application
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    // create a new CareAdmissionDriver object and start the application
    new BoardingSystemDriver(20).runApplication();
  }


  // PROVIDED runApplication() method
  /**
   * Runs this application
   */
  public void runApplication() {
    System.out.println(preBoardingAnnouncement); // display welcome message
    // read and process user command lines
    processUserCommands();
    scanner.close();// close the scanner
    System.out.println(postBoardingAnnouncement);// display good bye message
  }


  /**
   * Inserts a given passenger to the boarding queue of this boarding system.
   * 
   * If the enqueue operation fails, the exact following message MUST be displayed to the console:
   * "Boarding queue full!"
   * 
   * @param p a given passenger to enqueue
   */
  protected void enqueuePassenger(Passenger p) {
    // TODO implement this method
    // [HINT] use BoardingQueue.enqueue() method
    if (!boardingQueue.enqueue(p)) {
      System.out.println("Boarding queue full!");
    }


  }

  // dequeuePassenger() -- //TODO
  /**
   * Dequeues the passenger with the highest priority to board the airplane.
   * 
   * If the boarding queue is empty, the exact following error message MUST be displayed to the
   * console: "Empty Boarding Queue!"
   * 
   * Otherwise, dequeue the next passenger to board the airplane.
   * 
   * If the dequeued passenger has checked in, they can board the airplane. The following message
   * MUST be displayed to the console, and the passenger will be added to the passengersOnBoard
   * arraylist. "Boarding" + <string representation of the dequeued passenger>
   * 
   * If the dequeued passed has not checked in, they MUST NOT be added to the passengersOnBoard
   * arraylist and the following message must be displayed. <dequeued passenger's name> + " is not
   * checked in. Skipping."
   */
  protected void dequeuePassenger() {
    // TODO implement this method according to the details provided in its javadocs
    // [HINT] use BoardingQueue.dequeue() to dequeue the passenger to board the airplane next
    // [HINT] use Passenger.isCheckedIn() to check whether the dequeued passenger has already
    // checked in
    if (boardingQueue.isEmpty()) {
      System.out.println("Empty Boarding Queue!");
    }
    Passenger dequeued = boardingQueue.dequeue();
    if (dequeued.isCheckedIn()) {
      System.out.println("Boarding " + dequeued.toString());
      passengersOnBoard.add(dequeued);

    } else {
      System.out.println(dequeued.name() + " is not checked in. Skipping.");
    }


  }
  

  // PROVIDED startBoarding() but commented out
  // TODO comment out the implementation details of startBoarding() method after you implement the
  // BoardingQueue.isEmpty() and BoardingQueue.size() methods
  /**
   * Start the airplane boarding process. The boarding queue should be empty after this method
   * executes
   */
  protected void startBoarding() {
    System.out.println("Start boarding!");

    System.out.println("The boarding queue contains " + this.boardingQueue.size() + "passengers");
    while (!boardingQueue.isEmpty()) {
      this.dequeuePassenger();
    }

    System.out.println("Boarding process complete.");
  }


  // getPassengerToAdd() -- //TODO complete the missing statement
  /**
   * Reads and processes user command line to add a new passenger to the boarding queue.
   * 
   * @param commandLine user command line to enqueue a new Passenger to the BoardingQueue
   * @return the reference to the passenger to be enqueued, or null if invalid command line
   */
  private Passenger getPassengerToAdd(String commandLine) {
    // valid commandLine format to add a new passenger:
    // 1 <name> <group> <isCheckedIn>
    String[] commands = commandLine.trim().split(" "); // split user command
    if (commands.length < 4) {
      System.out.println(syntaxErrorMessage);
      return null;
    }
    try {
      // read name
      String name = commands[1];
      // read boarding group
      Group group = Group.valueOf(commands[2]);
      // read whether the passenger was checked in
      boolean isCheckedIn = Boolean.valueOf(commands[3]);

      // TODO Create a new Passenger given name, boarding group, isCheckedIn values
      // and return it
      return new Passenger(name, group, isCheckedIn); // default return statement. Feel free to change it

    } catch (IllegalArgumentException e) {
      System.out.println(syntaxErrorMessage + " Invalid boarding group! Should be either A/B/C");
      return null; // a null reference is returned if the command is invalid
    }
  }


  // PROVIDED displayMenu()
  /**
   * Prints out the menu of this application
   */
  private void displayMenu() {
    System.out.println("\n==================== MENU ================================");
    System.out.println("Enter one of the following options:");
    System.out.println("[0] Display the main menu");
    System.out.println("[1 <name> <group> <isCheckedIn>] Enqueues a new passenger");
    System.out.println("[2] Peek next passenger to board");
    System.out.println("[3] Start boarding");
    System.out.println("[4] List all passengers in the boarding queue");
    System.out.println("[5] List all boarded passengers");
    System.out.println("[6] Cancel boarding: clear the boarding queue!");
    System.out.println("[7] Logout and EXIT");
    System.out.println("----------------------------------------------------------");
  }

  // TODO complete the missing statements in the processUserCommands() method
  /**
   * Reads and processes user command lines
   */
  private void processUserCommands() {

    // display the main menu
    displayMenu();
    // read user command line
    String promptCommandLine = "ENTER COMMAND: ";
    System.out.print(promptCommandLine);
    String command = scanner.nextLine();

    // read and process user command lines until the user signs out
    while (command.charAt(0) != '7') { // 7 to logout: quit
      try {
        switch (command.charAt(0)) {
          case '0': // display main menu
            displayMenu(); // display the main menu
            break;
          case '1': // [1 <name> <group> <isCheckedIn>] Enqueues a new passenger
            Passenger p = this.getPassengerToAdd(command);
            if (p != null) {
              // TODO enqueue the passenger p.
              // [HINT] call the BoardingSystemDriver.enqueuePassenger() method
              enqueuePassenger(p);
            }
            break;
          case '2': // [2] Peek next passenger to board"
            // TODO print the string representation of the next passenger to be boarded (the
            // passenger at the root of the priority queue)
            System.out.println(boardingQueue.peekBest().toString());


            break;
          case '3': // [3] Start boarding"
            this.startBoarding();
            break;
          case '4': // [4] List all passengers in the boarding queue"
            System.out.println("Passengers in the boarding queue:");
            System.out.println(this.boardingQueue);
            break;
          case '5': // [5] List all boarded passengers
            System.out.println("List of passengers on board:");
            for (Passenger passenger : this.passengersOnBoard) {
              System.out.println(passenger);
            }
            break;
          case '6': // [6] Cancel boarding: clear the boarding queue!
            System.out.println("We regret to inform you that the boarding process for this flight\n"
                + "is cancelled. Please clear the boarding queue!");
            boardingQueue.clear();
            break;
          default:
            System.out.println(syntaxErrorMessage); // Syntax Error

        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      // read next user command line
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
    }
  }

}
