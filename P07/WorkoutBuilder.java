//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: WorkoutBuilder
// Course: CS 300 Fall 2023
//
// Author: Madison Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: https://pages.cs.wisc.edu/~cs300/readings/Linked-Lists/ - used to help understand
// linked lists better and get an idea on how to implement this one
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class uses a singly-linked list data structure to maintain a list of exercises organized
 * according to their WorkoutType.
 */
public class WorkoutBuilder implements ListADT<Exercise> {
  // use SINGLY LINKED LIST to maintain list of excercises
  // organized by: warm-up, cool-down, or primary excercises
  // each node (excercise) has unique identification number
  private int cooldownCount; // the number of excercises with WorkoutType equal to COOLDOWN in this
                             // WorkoutBuilder list
  private LinkedExercise head; // The node containing the element at index 0 of this singly-linked
                               // list
  private int primaryCount; // The number of exercises with WorkoutType equal to PRIMARY in this
                            // WorkoutBuilder list
  private int size; // The total number of exercises contained in this WorkoutBuilder list

  private LinkedExercise tail; // The node containing the last element of this singly-linked list

  private int warmupCount; // The number of exercises with WorkoutType equal to WARMUP in this
                           // WorkoutBuilder list

  /**
   * Accesses the total number of elements in this WorkoutBuilder list
   * 
   * @return the size of this list
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Accesses the number of warm-up exercises stored in this WorkoutBuilder list
   * 
   * @return the count of exercises with WorkoutType equal to WARMUP
   */
  public int getWarmupCount() {
    return this.warmupCount;
  }

  /**
   * Accesses the number of primary exercises stored in this WorkoutBuilder list
   * 
   * @return the count of exercises with WorkoutType equal to PRIMARY
   */
  public int getPrimaryCount() {
    return this.primaryCount;
  }

  /**
   * Accesses the number of cool-down exercises stored in this WorkoutBuilder list
   * 
   * @return
   */
  public int getCooldownCount() {
    return this.cooldownCount;
  }

  /**
   * Checks whether this WorkoutBuilder list is empty
   * 
   * @return true if this list contains no elements and neither its head nor tail refer to
   *         LinkedExercise objects
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0 && this.head == null && this.tail == null && this.warmupCount == 0
        && this.primaryCount == 0 && this.cooldownCount == 0;
  }

  /**
   * Removes all elements from this list. The list will be empty after this call returns.
   *
   */
  public void clear() {
    // reset size and counts
    size = 0;
    warmupCount = 0;
    primaryCount = 0;
    cooldownCount = 0;
    // reset head and tail
    head = null;
    tail = null;


  }



  /**
   * Finds the index of a given exercise in this WorkoutBuilder list, if it is present. Note that
   * Exercise contains an overridden equals() method for use here. OPTIONAL IMPLEMENTATION
   * CHALLENGE: check as few nodes as possible.
   * 
   * @param findObject - the exercise to search for in this list
   * @return the index of this object in the list if it is present; -1 if it is not
   */
  @Override
  public int indexOf(Exercise findObject) {
    // traverse through this workoutbuilder list? start from head and get next
    LinkedExercise temp = head;
    int index = 0;
    while (index <= size()) {
      if (temp.getExercise().equals(findObject))
        return index;
      temp = temp.getNext();
      index++;
    }

    return -1;
  }

  /**
   * Returns the exercise stored at the given index of this list without removing it.
   * 
   * @param index - position within this list
   * @return the exercise stored at the given index of this list
   * @throws IndexOutOfBoundsException - with a descriptive error message if the index is not valid
   *                                   for the current size of this list
   */
  @Override
  public Exercise get(int index) {
    // TODO Auto-generated method stub
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of range.");
    }
    int count = 0;
    LinkedExercise temp = head;
    // keep flipping through the next node till reach index
    while (count < index && temp != null) {
      temp = temp.getNext();
      count++;
    }
    if (temp == null)
      throw new IndexOutOfBoundsException("Index out of range.");

    return temp.getExercise();



  }

  /**
   * Adds the provided Exercise to the appropriate position in the list for its WorkoutType, and
   * increments the corresponding counter fields: WARMUP: adds to the FRONT (head) of the list
   * PRIMARY: adds after all warm-ups and before any cool-downs; if there are any existing primary
   * exercises, adds before all of those as well COOLDOWN: adds to the END (tail) of the list We
   * recommend implementing private helper methods for each of these cases, but this is not
   * required. Remember to consider the case where you are adding the very first exercise to the
   * list!
   * 
   * @param newObject - the exercise to add to the WorkoutBuilder list
   */
  @Override
  public void add(Exercise newObject) {
    // if first node
    if (this.isEmpty()) {
      LinkedExercise newNode = new LinkedExercise(newObject);
      head = newNode;
      tail = newNode;
      size++;

      // update counters
      if (newObject.getType() == WorkoutType.WARMUP) {
        warmupCount++;
      }
      if (newObject.getType() == WorkoutType.PRIMARY) {
        primaryCount++;
      }
      if (newObject.getType() == WorkoutType.COOLDOWN) {
        cooldownCount++;
      }

    } else {
      LinkedExercise newNode = new LinkedExercise(newObject);
      LinkedExercise current = head;
      // if type warmup, add to front
      if (newObject.getType() == WorkoutType.WARMUP) {
        // create new node that points to original head
        newNode.setNext(head);
        // point head to newNode
        head = newNode;
        // increment count
        warmupCount++;
      }
      // if type primary, add between warmup and cooldown (get counter for them and add at index)
      if (newObject.getType() == WorkoutType.PRIMARY) {

        // if there are warmup nodes
        if (warmupCount > 0) {
          // add after warmup nodes by getting the index

          while (current.getNext() != null
              && current.getNext().getExercise().getType() == WorkoutType.WARMUP) {
            current = current.getNext();
          }
          newNode.setNext(current.getNext());
          current.setNext(newNode);
          primaryCount++;
        }

        // if there are no warmup nodes, but there are cooldown nodes
        else if (cooldownCount > 0) {
          // find the last primary node
          while (current.getNext() != null
              && current.getNext().getExercise().getType() != WorkoutType.COOLDOWN) {
            current = current.getNext();
          }
          newNode.setNext(current.getNext());
          current.setNext(newNode);
          primaryCount++;

        }

        // if there are neither, and only primary nodes
        else {
          // just add to front
          newNode.setNext(head);
          head = newNode;
          primaryCount++;
        }


      }

      // if type cool down, add to end
      if (newObject.getType() == WorkoutType.COOLDOWN) {
        // node still points to null
        // have tail node's next pointer point to new node
        tail.setNext(newNode);
        // move tail pointer to new node
        tail = newNode;

        // increment count
        cooldownCount++;
      }
      size++;

    }
  }

  /**
   * Removes an exercise of the provided type from the list, if one exists, and decrements the
   * corresponding counter fields: WARMUP: removes the FIRST (head) element from the list PRIMARY:
   * removes the FIRST exercise of type PRIMARY from the list COOLDOWN: removes the LAST (tail)
   * element from the list You are encouraged to implement private helper methods for each of these
   * cases as well, but this is not required. Be sure to check that there are any exercises with the
   * given type present in the list, and remember to consider the case where you are removing the
   * very last exercise from the entire list!
   * 
   * @param type - the type of exercise to remove from the list
   * @return the exercise object that has been removed from the list
   * @throws NoSuchElementException - if there are no exercises in the list with the given
   *                                WorkoutType
   */
  public Exercise removeExercise(WorkoutType type) throws NoSuchElementException {


    // case 1 empty list
    if (this.isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    // case 2 remove at head
    if (head != null && head.getExercise().getType() == type) {
      Exercise removed = head.getExercise();
      head = head.getNext();
      updateCounters(type, -1);
      size--;
      return removed;
    }
    // case 3 search through list, remove
    LinkedExercise current = head;
    LinkedExercise previous = null;

    while (current != null && current.getExercise().getType() != type) {
      previous = current;
      current = current.getNext();

    }

    // check if the exercise of type was found
    if (current == null)
      throw new NoSuchElementException("Exercise not found.");

    // remove exercise
    Exercise removed = current.getExercise();
    previous.setNext(current.getNext());
    updateCounters(type, -1);
    size--;

    // if removed exercise was the last one, update tail
    if (current.getNext() == null) {
      tail = current;
    }
    return removed;

  }


  /**
   * Removes the exercise with the provided ID number from the list, if it is present, and adjusts
   * any corresponding counter fields as necessary. This method uses a linear search algorithm.
   * 
   * @param exerciseID - the unique identifier of the exercise to be removed
   * @return the exercise object that has been removed from the list
   * @throws NoSuchElementException - if no exercises in the list match the provided exerciseID
   *                                number
   */
  public Exercise removeExercise(int exerciseID) throws NoSuchElementException {
    // if list is empty, no exercises match - throw exception
    if (head == null) {
      throw new NoSuchElementException("List is empty");
    }

    // Check if the first node contains the exercise to be removed
    if (head.getExercise().getExerciseID() == exerciseID) {
      Exercise removedExercise = head.getExercise();
      head = head.getNext(); // Move head to the next node
      updateCounters(removedExercise.getType(), -1);
      size--;
      // check if we removed last element and update pointers
      if (head == null)
        tail = null;
      return removedExercise;
    }
    //
    LinkedExercise current = head;
    while (current.getNext() != null
        && current.getNext().getExercise().getExerciseID() != exerciseID) {
      current = current.getNext();
    }

    if (current.getNext() == null) {
      throw new NoSuchElementException("Exercise with ID " + exerciseID + " not found");
    }

    // Node with exerciseID found, remove it
    Exercise removedExercise = current.getNext().getExercise();
    current.setNext(current.getNext().getNext());
    updateCounters(removedExercise.getType(), -1);
    size--;

    // check if node being removed is the tail
    if (current.getNext() == null) {
      tail = current; // update tail
    }

    return removedExercise;
  }

  /**
   * Returns a String representation of the contents of this list, as the concatenated String
   * representations of all LinkedExercise nodes in this list. See the sample output at the end of
   * the writeup for examples.
   * 
   * @return return a String representation of the content of this list. If this list is empty, an
   *         empty String ("") will be returned.
   */
  @Override
  public String toString() {
    String result = "";
    LinkedExercise temp = head;
    while (temp != null) {
      result += temp.toString();
      temp = temp.getNext();
    }
    return result;
  }

  // HELPER METHODS
  // remove warmup

  /**
   * This helper method updates the counters depending on what type it is
   * 
   * @param type   - WorkoutType to determine which counter to update
   * @param change - integer that determines how much to change counters by; 1 to increment, -1 to
   *               decrement
   */
  private void updateCounters(WorkoutType type, int change) {
    // Update counters based on the type of exercise
    switch (type) {
      case WARMUP:
        warmupCount += change;
        break;
      case PRIMARY:
        primaryCount += change;
        break;
      case COOLDOWN:
        cooldownCount += change;
        break;
    }
  }
}
