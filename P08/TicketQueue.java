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

/*
 * 
 */
public class TicketQueue implements Iterable<TicketSiteUser>, QueueADT<TicketSiteUser> {
  // fields
  private LinkedNode<TicketSiteUser> front; // the linked node at the front of the queue
  private LinkedNode<TicketSiteUser> back; // the linked node at the back of the queue
  private int capacity; // the MAXIMUM number of TicketSiteUsers that the queue can hold
  private int size; // the number of TicketSiteUsers in the queue

  /**
   * Creates an empty queue of TicketSiteUsers with the given capacity.
   * 
   * @param capacity - the capacity of this queue
   * @throws IllegalArgumentException - if the capacity is less than 1
   * 
   */
  public TicketQueue(int capacity) throws IllegalArgumentException {
    if (capacity < 1)
      throw new IllegalArgumentException("Error: Capacity cannot be less than 1.");
    this.capacity = capacity;
    this.back = null;
    this.front = null;
    this.size = 0;
  }

  /**
   * Reports the capacity of the queue
   * 
   * @return the MAXIMUM number of TicketSiteUsers this queue can hold
   */
  public int capacity() {
    return this.capacity;
  }

  /**
   * Changes the capacity of the queue to the new capacity. If the capacity is lowered, DO NOT
   * remove any elements. It will be considered full until enough TicketSiteUsers are dequeued by
   * the application.
   * 
   * @param newCapacity
   */
  public void setCapacity(int newCapacity) throws IllegalArgumentException {
    if (newCapacity < 1)
      throw new IllegalArgumentException("Error: the new capacity cannot be less than 1");
    this.capacity = newCapacity;
  }

  /**
   * Creates and returns a deep copy (not the deepest copy) of this TicketQueue
   * 
   * @return a deep copy of this TicketQueue
   */
  public TicketQueue deepCopy() {
    TicketQueue copy = new TicketQueue(this.capacity);

    if (this.front == null)
      return copy;
    // traverse queue, adding each node to the new list
    LinkedNode<TicketSiteUser> temp = this.front;
    while (temp != null) {
      copy.enqueue(temp.getData());
      temp = temp.getNext();
    }
    
    return copy;
  }

  /**
   * Reports whether or not this queue is full.
   * 
   * @return true is the number of TicketSiteUsers is the same or more of the capacity, false
   *         otherwise
   */
  public boolean isFull() {
    return this.size >= this.capacity;
  }


  /**
   * Creates and returns an instance of a TicketQueueIterator for this queue
   * 
   * @return an instance of a TicketQueueIterator
   */
  @Override
  public Iterator<TicketSiteUser> iterator() {
    return new TicketQueueIterator(this);
  }


  /**
   * Adds the given TicketSiteUser to the back of the queue.
   * 
   * @param newObject - element to add at the back (end) of the queue
   */
  @Override
  public void enqueue(TicketSiteUser newObject)
      throws IllegalStateException, IllegalArgumentException {
    // TODO Auto-generated method stub
    if (this.isFull()) 
      throw new IllegalStateException("Queue is full.");
    if (!newObject.canBuyTicket())
      throw new IllegalArgumentException("User is not able to buy ticket.");
    LinkedNode<TicketSiteUser> newElement = new LinkedNode<TicketSiteUser>(newObject);
    
    // if user is first element, set front AND back
    if (this.isEmpty()) {
      front = newElement;
      back = newElement;

    } else {
      back.setNext(newElement);
      back = newElement;
    }
    this.size++;


    

  }

  /**
   * Removes and returns the TicketSiteUser from the front of the queue
   * 
   * @return the TicketSiteUser at the front of the queue
   * @throws NoSuchElementException - if the queue is empty
   */
  @Override
  public TicketSiteUser dequeue() throws NoSuchElementException {
    // TODO Auto-generated method stub
    if (this.isEmpty())
      throw new NoSuchElementException("Queue is empty.");
    TicketSiteUser frontUser = this.front.getData();
    // if list with one element, update back
    if (size == 1) {
      this.back = null;
      this.front = null;
      this.size--;
      return frontUser;
    }
    this.front = front.getNext();
    this.size--;
    return frontUser;
  }

  /**
   * Returns the TicketSiteUser from the front of the queue without removing it.
   * 
   * @return the element at the front of the queue
   * @throws NoSuchElementException - if the queue is empty
   */
  @Override
  public TicketSiteUser peek() throws NoSuchElementException {
    if (this.isEmpty())
      throw new NoSuchElementException("Queue is empty.");
    return this.front.getData();
    
  }

  /**
   * Reports if this queue is empty
   * 
   * @return true if the queue has noTicketSiteUsers in it, false otherwise
   */
  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    
    return size<=0 || this.front == null || this.back == null;
  }

  /**
   * Reports the current size of the queue
   * 
   * @return the number of TicketSiteUsers in the queue
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return this.size;
  }

  /**
   * String representation of TicketQueue
   * @return a string 
   */
  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() +"\n";
      runner = runner.getNext();
    }
    return s;
  }

}
