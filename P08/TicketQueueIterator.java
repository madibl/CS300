//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    The Anti-Scalper Agenda
// Course:   CS 300 Fall 2023
//
// Author:   Madison Lin
// Email:    mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for a TicketQueue that keeps the original queue intact. This iterator will return
 * elements in the order of the queue from front to back.
 */
public class TicketQueueIterator implements Iterator<TicketSiteUser> {
  private TicketQueue userQueue; // deep copy of a TicketQueue

  /**
   * Constructor for a TicketQueueIterator that sets the data field to be a deep copy of the given
   * queue.
   * 
   * @param queue - the TicketQueue for this iterator to use
   * @throws IllegalArgumentException - if the queue is null
   */
  public TicketQueueIterator(TicketQueue queue) {
    if (queue == null)
      throw new IllegalArgumentException("Queue is null.");
    this.userQueue = queue.deepCopy();
  }

  /**
   * Determines whether or not there is another TicketSiteUser in the queue.
   * 
   * @return true if there are more TicketSiteUsers in the queue, false otherwise
   */
  @Override
  public boolean hasNext() {
    return !userQueue.isEmpty();
  }

  /**
   * Returns the next TicketSiteUser in the queue, based on the order from front to back.
   * @return the next TicketSiteUser in the queue
   * @throws NoSuchElementException - if there are no more TicketSiteUsers in the queue
   */
  @Override
  public TicketSiteUser next() throws NoSuchElementException {
    // TODO Auto-generated method stub
    if (userQueue.isEmpty())
      throw new NoSuchElementException("There are no more TicketSiteUsers in the queue.");
    // get a copy of the queue, dequeue it.
    return userQueue.dequeue();
  }

}
