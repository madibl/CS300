//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Madison Lin
// Campus ID: 908 606 8708
// WiscEmail: mblin@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;
// No other imports besides ArrayList and NSEE are permitted.


////////////////////////////////////////////////////////////////////////////////
// BE CAREFUL!! This file contains TWO classes. You will need to complete the
// implementation of BOTH classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects 
// is optional.
////////////////////////////////////////////////////////////////////////////////

/**
 * This class models the Email data type. It contains information about the email's priority level
 * and sender, as well as its subject line. By default, emails have priority 0, but may go as high
 * as 5; a list of unanswered emails is maintained at the class level.
 * 
 * NOTES:
 * You may NOT add any additional data fields to this class unless specified in the TODO tags.
 * You may NOT add ANY additional methods to this class, regardless of access modifier.
 * There is no tester or main method for this class.
 */
public class Email {
  // TODO
  // 1. Declare a protected instance field of type int named messagePriority
  protected int messagePriority;
  // 2. Declare a private instance field of type String named senderName
  private String senderName;
  // 3. Declare a private instance field of type String named subjectLine
  private String subjectLine;
  // 4. Declare AND INITIALIZE a private static data field which contains an ArrayList of Email 
  //    objects, named unansweredEmail. This field will contain all unanswered Email objects,
  //    but at this point it is empty.
  private static ArrayList<Email> unansweredEmail = new ArrayList<Email>();

  /**
   * Adds an email object to the class unansweredEmail list
   * 
   * @param message the message to add to the list (duplicates are allowed)
   */
  public static void addToInbox(Email message) {
    // TODO
    // 5.  Complete the implementation of this method
    unansweredEmail.add(message);
  }
  
  /**
   * Removes the first instance of the provided email from the unansweredEmail list, if it is 
   * present
   * 
   * @param message the email to remove from the list
   * @throws NoSuchElementException if the provided message is not present in unansweredEmail
   */
  public static void markAsAnswered(Email message) throws NoSuchElementException {
    // TODO
    // 6. Check whether the input Email is present in unansweredEmail and handle appropriately
    // HINT: use ArrayList's .contains() method
    // 7. Remove the first instance of the provided email from unanswered Email
    if (unansweredEmail.contains(message)) {
      unansweredEmail.remove(unansweredEmail.indexOf(message));
    }
    else {
      throw new NoSuchElementException("No email found.");
    }
  }
  
  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)
  
  /**
   * Creates a new Email with the given priority/sender/subject, and adds it to the inbox.
   * If a priority outside of 0-5 is provided, the constructor will set the priority to 0.
   * 
   * @param messagePriority a value between 0 (lowest) and 5 (highest). Defaults to 0 when invalid
   * priorities are provided
   * @param senderName the name of the person who sent this email
   * @param subjectLine the subject of this email
   */
  public Email (int messagePriority, String senderName, String subjectLine) {
    // TODO
    // 8. Check the validity of messagePriority and handle appropriately
    if (messagePriority < 0 || messagePriority > 5) {
      this.messagePriority = 0;
    }
    // 9. Set the instance data fields to the provided input parameters
    else {
      this.messagePriority = messagePriority;
    }
    this.senderName = senderName;
    this.subjectLine = subjectLine;
    
    Email.addToInbox(this); // provided, adds this email object to the inbox
  }
  
  /**
   * Gets the priority level of this email
   * 
   * @return the priority level of this email, a value between 0-5 inclusive
   */
  public int getPriority() {
    // TODO
    // 10. Complete the implementation of this accessor method
    return messagePriority;
  }
  
  /**
   * Determines whether a provided object is equal to this one. To be equal, the object must
   * be of type Email, and have the same priority/sender/subject.
   * 
   * @param o the object to compare with this
   * @return true if o is an Email with the same priority/sender/subject as this, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    // TODO
    // 11. Complete the implementation of this method
    if (o instanceof Email) {
      if (this.messagePriority == ((Email) o).messagePriority && this.senderName == ((Email) o).senderName && this.subjectLine == ((Email) o).subjectLine) {
        return true;
      }
    }
    return false;
  }
  
}

// YOU ARE NOT DONE YET!!

// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

/**
 * This class models the ActionItem data type, representing an email which has a due date
 * 
 * NOTES:
 * You may NOT add any additional data fields to this class.
 * You may NOT add ANY additional methods to this class, regardless of access modifier.
 * There is no tester or main method for this class.
 */
class ActionItem extends Email{ // TODO 12. Set this class to be a subclass of the Email class
  
  // the number of days remaining until this ActionItem is due
  private int daysUntilDue;
  
  /**
   * Creates a new ActionItem email with a starting priority of 1 and the provided sender/subject
   * values.
   * 
   * @param senderName the name of the person who sent this email
   * @param subjectLine the subject of this email
   * @param daysUntilDue the number of days until this ActionItem is due, which must be strictly >0
   * @throws IllegalArgumentException if the provided daysUntilDue are 0 or negative
   */
  public ActionItem(String senderName, String subjectLine, int daysUntilDue)
      throws IllegalArgumentException {
    // TODO
    // 13. Call the constructor of the super class with required arguments.
    super(1, senderName, subjectLine);
    // 14. Check the validity of the daysUntilDue parameter and handle appropriately
    if (daysUntilDue <= 0) {
      throw new IllegalArgumentException("daysUntilDue must be greater than 0.");
    }
    // 15. Set the instance fields to the values passed as input
    this.daysUntilDue = daysUntilDue;
    
    
  }
  
  /**
   * Moves one day forward in time, decreasing the number of days until this ActionItem is due
   * Every day, this ActionItem increases its priority by 1 (until its priority is 5).
   * 
   * If this causes the daysUntilDue to reach 0, this method ALSO removes this email from the
   * unansweredEmail list in the Email class (HINT: how might you pass the current object as an
   * argument in a method call? Also remember that unansweredEmail is a _private_ field!).
   * Do not catch the NoSuchElementException this may cause.
   * 
   * @throws NoSuchElementException if this ActionItem is not present in unansweredEmail
   */
  public void advanceOneDay() throws NoSuchElementException {
    // TODO
    // 16. Modify the daysUntilDue and messagePriority fields according to the description above
    // moves one day forward in time

      if (daysUntilDue < 5 || daysUntilDue > 0) {
        daysUntilDue--;
        if (this.getPriority() < 5) {
          this.messagePriority++;
        }
      }
      // 17. Remove from unansweredEmail if necessary
      if (daysUntilDue == 0) {
          markAsAnswered((Email)this);
      }
    
  }
  
  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope
  
}