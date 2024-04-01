//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    The Bus Stop Tree
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

public class BusFilteredIterator implements Iterator<Bus> {
  private Iterator<Bus> baseIterator; // the iterator we are filtering
  private BusStop destination; // the destination BusStop we are filtering by
  private Bus next; // the next Bus to be returned, or null if there aren't any more

  /**
   * Construct a new BusFilteredIterator that filters the given iterator to return only Bus-es that
   * stop at the given destination
   * 
   * @param iterator - the iterator that we are filtering.
   * @param destination - the desired destination.
   */
  public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    baseIterator = iterator;
    this.destination = destination;
    next = null;
  }

  /**
   * returns true if there is another Bus (that goes to the destination) in this iterator, or false
   * otherwise
   * 
   * @return true if a call to next() will return another Bus; false otherwise
   */
  @Override
  public boolean hasNext() {
    // skip over next
    if (next == null) {
      advanceToNext();
    }
    return next != null;
  }

  /**
   * Returns the 'next' bus and advances the iterator until the next bus it will return
   * 
   * @return Buses from the iterator baseIterator that go to the destination stop.
   * @throws NoSuchElementException - if called when there is no next Bus.
   */
  @Override
  public Bus next() throws NoSuchElementException {
    // if null, move past it
    if (next == null) {
      advanceToNext();

    }
    // throw exception
    if (next == null)
      throw new NoSuchElementException("No more buses");
    
    // return next after advancing iterator
    Bus toReturn = next;
    next = null;
    return toReturn;
  }

  /**
   * Private helper method that advances this iterator. It will iterate over `this.iterator` until
   * it reaches a Bus that stops at destination. Then, it will store that Bus in `next`.
   */
  private void advanceToNext() {
    // iterate through base iterator
    while(baseIterator.hasNext()){
      Bus bus = baseIterator.next();
      // if it goes to destination, set it as next
      if(bus.goesTo(destination)){
          next = bus;
          break;
      }
    }
  }

}
