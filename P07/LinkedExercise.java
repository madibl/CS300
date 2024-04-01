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
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a node for use in a singly-linked list. This node can ONLY contain elements of type Exercise.
 */
public class LinkedExercise {
  private Exercise exercise; // The Exercise contained in this linked node, which cannot be replaced
                             // after this node is created
  private LinkedExercise next; // The next linked node in this list


  // constructors
  /**
   * Creates a new node containing the provided exercise data and next node
   * 
   * @param data - the exercise to store in this node
   * @param next - the next node in this list, which MAY be null
   */
  public LinkedExercise(Exercise data, LinkedExercise next) {
    this.exercise = data;
    this.next = next;
  }

  /**
   * Creates a new node containing the provided exercise data with no following node
   * 
   * @param data - the exercise to store in this node
   */
  public LinkedExercise(Exercise data) {
    this.exercise = data;
    this.next = null;
  }

  // methods
  /**
   * Accesses the next linked node in the list, which may be null
   * 
   * @return the reference to the node which follows this one in the list
   */
  public LinkedExercise getNext() {
    return this.next;
  }

  /**
   * Changes the node which follows this one to be the provided value, which may be null
   * 
   * @param node - the reference to set as the next node in the list
   * 
   */
  public void setNext(LinkedExercise node) {
    this.next = node;
  }

  /**
   * Accesses the exercise stored in this linked node
   * 
   * @return the Exercise stored in this linked node
   */
  public Exercise getExercise() {
    return this.exercise;
  }

  /**
   * Returns a String representation of this linked exercise. This String will be:
   * exercise.toString() + " -> " // if next field is NOT null exercise.toString() + " -> END" // if
   * next field is null For instance, LinkedExercise ex1 = new LinkedExercise(new
   * Exercise(WorkoutType.PRIMARY, "curl")); LinkedExercise ex2 = new LinkedExercise(new
   * Exercise(WorkoutType.WARMUP, "stretch"), ex1); ex1.toString() returns "PRIMARY: curl (1) ->
   * END" ex2.toString() returns "WARMUP: stretch (2) -> "
   * 
   * @return a String representation of this linked exercise object
   */
  @Override
  public String toString() {
    if (this.next == null)
      return exercise.toString() + " -> END";
    else
      return exercise.toString() + " -> ";
  }

}
