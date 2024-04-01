//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: LAST ONE BEST ONE: P10 BOARDING SYSTEM
// Course: CS 300 Fall 2023
//
// Author: Madison B Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority boarding queue storing elements of type
 * Passenger. This class guarantees the min-heap invariant, so that:
 * 
 * The Passenger at the root should be the smallest Passenger in the queue, which corresponds to the
 * passenger having the highest priority.
 * 
 * Children always are greater than their parent.
 * 
 * The Passenger at the root of this min-heap priority queue must be dequeued (board the airplane)
 * next, meaning removed and returned by the dequeue method.
 * 
 * The BoardingQueue.peekBest() must return without removing the Passenger at the root of this
 * min-heap queue, if the queue is not empty.
 * 
 * We rely on the Passenger.compareTo() method to compare Passengers.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class BoardingQueue implements PriorityQueueADT<Passenger> {
  // This BoardingQueue stores elements of type Passenger, ONLY.

  // oversize array
  private Passenger[] heap;// array min-heap of Passengers representing this priority queue
  private int size; // size of this priority queue
  // The heap is an oversize array, meaning that the following should be ensured:
  // heap[i] == null for all valid indexes and size == 0 when the heap is EMPTY
  // If the heap is NOT empty:
  // heap[i] != null when i >= 0 && i < size
  // heap[i] == null when i >= size && i < heap.length

  /**
   * Constructs an empty BoardingQueue with the given capacity
   * 
   * @param capacity Capacity of this boarding queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer (greater than zero)
   */
  public BoardingQueue(int capacity) {
    if (!(capacity > 0))
      throw new IllegalArgumentException("Capacity must be a positive integer.");
    this.size = 0;
    this.heap = new Passenger[capacity];

  }


  /**
   * Returns the capacity of this BoardingQueue
   * 
   * @return the capacity of this BoardingQueue
   */
  public int capacity() {
    return heap.length;
  }



  /**
   * Removes all the elements from this Boarding Queue
   */
  public void clear() {
    // set to null and set size to 0 clears everything
    heap = null;
    size = 0;
  }


  /**
   * Checks whether this BoardingQueue is full
   * 
   * @return true if this boarding queue is full
   */
  public boolean isFull() {
    return size >= capacity();
  }

  /**
   * Returns a deep copy of the array-heap of this BoardingQueue. This method can be used for
   * testing purposes.
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the Passengers in this queue
   * @throws a NullPointerException if the heap array of this BoardingQueue is null
   */
  public Passenger[] toArray() {
    return Arrays.copyOf(this.heap, this.heap.length);
  }


  /**
   * Returns a deep copy of this BoardingQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * Passengers. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this BoardingQueue. The returned new boarding queue (the deep copy) has
   *         the same length and size as this queue.
   */
  public BoardingQueue deepCopy() {
    BoardingQueue copy = new BoardingQueue(this.capacity());
    for (Passenger p : heap) {
      copy.enqueue(p);
    }
    return copy; // default return statement
  }

  /**
   * Returns a String representing this boarding queue, where each Passenger in the queue is listed
   * on a separate line, in order from smallest to greatest, meaning in their boarding order.
   * 
   * @return a String representing this boarding queue, and an empty String "" if this queue is
   *         empty.
   */
  @Override
  public String toString() {
    String s = "";
    BoardingQueue deepCopy = this.deepCopy();
    while (!deepCopy.isEmpty()) {
      s += deepCopy.dequeue().toString() + "\n";
    }
    return s.trim();

  }

  /**
   * Restores the min-heap of the priority queue by percolating its root down the tree. If the
   * element at the given index does not violate the min-heap ordering property (it is smaller than
   * its smallest child), then this method does not modify the heap. Otherwise, if there is a heap
   * violation, then swap the element with the correct child and continue percolating the element
   * down the heap.
   * 
   * We assume that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate downwards
   */
  protected void percolateDown(int index) {

    // left child: i * 2 + 1
    // right child: i *2 + 2
    // parent: i -1 / 2
    int left = (index * 2) + 1;
    int right = (index * 2) + 2;

    // base case: leaf node
    if (left >= size && right >= size) {
      return;
    }

    // if (heap[left] == null)
    // return;

    // base case, if index is less than smallest child
    // if there is a right child, there is definitely a left one
    // find smallest child
    // default left as smallest
    int smallest = left;
    if (right < size && heap[right].compareTo(heap[left]) < 0)
      // right is smallest
      smallest = right;



    // check if element at given index is not smaller than its smallest child
    if (heap[smallest].compareTo(heap[index]) < 0) {
      // swap down on left side (recursively)
      Passenger temp = heap[smallest];
      heap[smallest] = heap[index];
      heap[index] = temp;

      percolateDown(smallest);
    }

  }

  /**
   * Restores the min-heap invariant of this priority queue by percolating a leaf up the heap. If
   * the element at the given index does not violate the min-heap invariant (it is greater than its
   * parent), then this method does not modify the heap. Otherwise, if there is a heap violation,
   * swap the element with its parent and continue percolating the element up the heap. We assume
   * that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate upwards
   */
  protected void percolateUp(int index) {
    // We recommend implementing a recursive version of this method to get more practice on
    // recursive thinking

    int parent = (index - 1) / 2;

    // base case: if parent is null (at root)
    if (parent < 0)
      return;
    // base case: in correct order (parent is less than current)
    if (heap[parent].compareTo(heap[index]) <= 0)
      return;

    // recursive case: if not in right place keep swapping up
    if (heap[parent].compareTo(heap[index]) > 0) {
      Passenger temp = heap[parent];
      heap[parent] = heap[index];
      heap[index] = temp;
      percolateUp(parent);
    }

  }


  /**
   * checks if the priority queue is empty
   * 
   * @return true if the priority queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0 || heap == null)
      return true;
    return false;
  }

  /**
   * Returns the number of elements in this priority queue
   * 
   * @return the number of elements in this priority queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns without removing the element with the highest priority
   * 
   * @return the element with the highest priority
   * @throws NoSuchElementException if this priority queue is empty
   */
  @Override
  public Passenger peekBest() {
    // if empty, throw exception
    if (isEmpty())
      throw new NoSuchElementException("Queue is Empty.");
    return heap[0];
  }

  /**
   * Inserts a new element e into this priority queue. This method returns false if the queue is
   * already full.
   * 
   * @param e the new element to insert into this priority queue
   * 
   * @return true if the element e was added to this queue, else false
   * @throws NullPointerException if the element e is null
   */
  @Override
  public boolean enqueue(Passenger e) {
    if (e == null) {
      throw new NullPointerException("Passenger is null");
    }
    if (isFull())
      return false;
    // add to end of heap and increment size
    heap[size] = e;

    percolateUp(size);
    size++;

    return true;
  }

  /**
   * Removes and returns the element with the highest priority
   * 
   * @return the removed element
   * @throws NoSuchElementException if this priority queue is empty
   */
  @Override
  public Passenger dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // remove from root of heap (first index), decrement size, and percolate up the lesser value
    Passenger toReturn = heap[0];

    // remove by swapping first and last
    heap[0] = heap[size - 1];
    size--;
    percolateDown(0);

    return toReturn;
  }



}
