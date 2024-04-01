///////////////////////////////////////////////////////////////////////////////
//
// Title:    CS 300 Grade Policies
// Course:   CS 300 Fall 2023
//
// Author:   Madison Lin
// Email:    mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Nathan Han
// Partner Email:   (email address of your programming partner)
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
// Online Sources:  https://www.digitalocean.com/community/tutorials/java-list-remove-methods-
//                  arraylist-remove (syntax to remove a dropped assignment from the ArrayList)
//                  
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class models a collection of scores which are worth a given percentage of a student's grade.
 * When calculating the point values for this type of assignment group, the lowest N scores are 
 * dropped, which is facilitated using a pair of static utility methods.
 */
public class DropAssignmentGroup {
  private ArrayList<SimpleAssignment> assignments;
  private int numDropped;
  public final double PERCENT_OF_TOTAL;


  /**
   * Basic constructor, initializes an assignment group which is worth the given percentage of a
   * student's grade which will be scaled by the given scaling factor. This method also creates the
   * AssignmentGroup that this object will use to store its assignments.
   * 
   * @param percent
   * @param scale
   */
  public DropAssignmentGroup(double percent, int drops) {
    assignments = new ArrayList<SimpleAssignment>();
    PERCENT_OF_TOTAL = percent;
    numDropped = drops;
  }

  /**
   * Adds a single assignment object to this DropAssignmentGroup
   * 
   * @param assignment - the SimpleAssignment to add
   */

  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }

  /**
   * Adds a batch of assignments to this DropAssignmentGroup in the order they appear
   * 
   * @param assignmentBatch - a perfect-size array of SimpleAssignments to add; you may assume there
   *                        are no null values present in this array
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    for (SimpleAssignment assignment : assignmentBatch) {
      assignments.add(assignment);
    }
  }

  /**
   * Without modifying the input ArrayList, creates a NEW ArrayList which contains all but the
   * lowest-scoring N (numDropped) assignments from the input ArrayList. If the input ArrayList
   * contains N (numDropped) or fewer assignments, the returned ArrayList will be empty.
   * 
   * @param assignments - an ArrayList containing all assignments
   * @param n           - the number of assignments to drop
   * @return a COPY of the input ArrayList which contains all but the lowest-scoring n (NOT
   *         numDropped) assignments
   */
  public static ArrayList<SimpleAssignment> dropLowestN(ArrayList<SimpleAssignment> assignments,
      int n) {
      
      //make sure num you want to drop is less than array, returns null if it is greater
        if (n >= assignments.size()) {
        return new ArrayList<SimpleAssignment>();
      }
      ArrayList<SimpleAssignment> result = new ArrayList<SimpleAssignment>();
      
      // make deep copy of assignments to result
      for (int i = 0; i < assignments.size(); i++) {
        result.add(assignments.get(i));
      }

      for (int j = 0; j < n; j++){
        double lowestScore = (double) Integer.MAX_VALUE;
     		int lowestIndex = -1;
        for (int i = 0; i < result.size(); i++) {
          if (result.get(i).getPoints() < lowestScore) {
            lowestScore = result.get(i).getPoints();
            lowestIndex = i;
          }
        }
        if (lowestIndex != -1)
          result.remove(lowestIndex);
      }
      return result;
  }

  /**
   * Finds the index of the lowest scoring assignment in the given ArrayList. In the case of ties,
   * this method should prefer the assignment with the lower index. No other form of tie-breaking
   * (e.g. points possible, completeness, etc) should be implemented.
   * 
   * @param assignments - an ArrayList containing the assignments to analyze
   * @return the index (0-based) of the lowest scoring assignment
   */
  public static int getLowestScoreIndex(ArrayList<SimpleAssignment> assignments) {

    double lowestScore = Integer.MAX_VALUE;
    int lowestIndex = 0;

    for (int i = 0; i < assignments.size(); i++) {
      if (assignments.get(i).getPoints() < lowestScore) {
        lowestScore = assignments.get(i).getPoints();
        lowestIndex = i;
      }
    }

    return lowestIndex;
  }

  /**
   * Retrieves an assignment at the given index in the DropAssignmentGroup, NOT accounting for drops
   * 
   * @param i - the index of the assignment to access (0-based)
   * @return the assignment at the given index, or null if the index is out of bounds
   */

  public SimpleAssignment getAssignment(int i) {
    if (i >= 0 && i < assignments.size()) {
      return assignments.get(i);
    }
    return null; // Index is out of bounds
  }

  /**
   * Accesses the number of assignments currently stored in this DropAssignmentGroup, NOT accounting
   * for drops
   *
   * @return the number of assignments present in this DropAssignmentGroup
   */

  public int getNumAssignments() {
    return assignments.size();
  }

  /**
   * Accesses the total number of earned points across all assignments in this DropAssignmentGroup,
   * after dropping the lowest N (numDropped)
   * 
   * @return Accesses the total number of earned points across all assignments in this
   *         DropAssignmentGroup, after dropping the lowest N (numDropped)
   */
  public double getPoints() {
    double totalEarnedPoints = 0;
    // drop lowest n before, passing in numDropped
    ArrayList<SimpleAssignment> droppedList =
        DropAssignmentGroup.dropLowestN(this.assignments, numDropped);

    for (SimpleAssignment assignment : droppedList) {
      totalEarnedPoints += assignment.getPoints();
    }
    return totalEarnedPoints;
  }

  /**
   * Accesses the total number of points possible across all assignments in this
   * DropAssignmentGroup, after dropping the lowest N (numDropped). Be careful - not all assignments
   * in this group are required to have the same number of points possible.
   * 
   * @return the sum of all possible points of all non-dropped assignments in this
   *         DropAssignmentGroup
   */
  public int getTotalPossible() {
    // drop lowest n first
    ArrayList<SimpleAssignment> droppedList =
        DropAssignmentGroup.dropLowestN(this.assignments, numDropped);

    int sum = 0;
    for (SimpleAssignment assignment : droppedList) {
      sum += assignment.POINTS_POSSIBLE;
    }
    return sum;
  }

  /**
   * Determines whether all assignments currently in this DropAssignmentGroup have been completed,
   * after dropping the lowest N (numDropped).
   * 
   * @return true if ALL non-dropped assignments in this DropAssignmentGroup have been completed;
   *         false otherwise
   */
  public boolean isComplete() {
    boolean completed = false;
    ArrayList<SimpleAssignment> droppedList =
        DropAssignmentGroup.dropLowestN(this.assignments, numDropped);
    for (SimpleAssignment assignment : droppedList) {
      completed = assignment.isComplete();
      if (completed == false) {
        return completed;
      }
    }
    return completed;
  }

  /**
   * Creates a String representation of this DropAssignmentGroup. Each assignment is listed by
   * number (1-based) and its String representation.
   * 
   * @return a String containing all of the non-dropped assignments in this DropAssignmentGroup
   */
  public String toString() {
    double roundedPoints;
    double roundedTotal;
    String result = "";
    for (int i = 0; i < assignments.size(); i++) {
      roundedPoints = ((double) ((int) (getAssignment(i).getPoints()*100))/100);
      roundedTotal = ((double) ((int) (getTotalPossible()*100))/100);
      if (getLowestScoreIndex(assignments) != i) {
        result += ((i+1) + ":" + roundedPoints);
        result += "/" + roundedTotal + " ";
      }
      else {
        result += "x:" + roundedPoints; 
        result += "/" + roundedTotal + " ";
      }
      
    }
    return result;
  }

}
