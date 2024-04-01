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
// Partner Email:   nhhan@wisc.edu
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
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;

/**
 * This class models a collection of scores which are worth a given percentage of a student's grade.
 * No adjustments are made to these scores.
 */
public class AssignmentGroup {
  // An ArrayList containing the assignments associated with this AssignmentGroup
  private ArrayList<SimpleAssignment> assignments;
  // The percent of the total grade this AssignmentGroup comprises
  public final double PERCENT_OF_TOTAL;

  /**
   * Basic constructor, initializes an assignment group which is worth the given percentage of a
   * student's grade.
   * 
   * @param percent - the percent of the total grade that this assignment group represents, assumed
   *                to be between 0 and 1.
   */
  public AssignmentGroup(double percent) {
    assignments = new ArrayList<SimpleAssignment>();
    PERCENT_OF_TOTAL = percent;

  }

  /**
   * Adds a single assignment object to this AssignmentGroup
   * 
   * @param assignment - the SimpleAssignment to add
   */

  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }

  /**
   * Adds a batch of assignments to this AssignmentGroup in the order they appear
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
   * Retrieves an assignment at the given index in the AssignmentGroup
   * 
   * @param i - the index of the assignment to access (0-based)
   * @return the assignment at the given index, or null if the index is out of bounds
   */

  public SimpleAssignment getAssignment(int i) {
    if (i < 0 || i > assignments.size()-1) {
      return null;
    }
      return assignments.get(i);

  }

  /**
  
   * Accesses the number of assignments currently stored in this AssignmentGroup
   * 
   * @return the number of assignments present in this AssignmentGroup
   */

  public int getNumAssignments() {
    return assignments.size();
  }

  /**
   * Accesses the total number of earned points across all assignments in this AssignmentGroup
   * 
   * @return the sum of all earned points of all assignments in this AssignmentGroup
   */
  public double getPoints() {
    double totalEarnedPoints = 0;
    for (SimpleAssignment assignment : assignments) {
      totalEarnedPoints += assignment.getPoints();
    }
    return totalEarnedPoints;
  }

  /**
   * Accesses the total number of points possible across all assignments in this AssignmentGroup.
   * 
   * @return the sum of all possible points of all assignments in this AssignmentGroup
   */
  public int getTotalPossible() {
    int sum = 0;
    for (SimpleAssignment assignment : assignments) {
      sum += assignment.POINTS_POSSIBLE;
    }
    return sum;
  }

  /**
   * Determines whether all assignments currently in this AssignmentGroup have been completed.
   * 
   * @return true if ALL assignments in this AssignmentGroup have been completed; false otherwise
   */
  public boolean isComplete() {
    boolean completed = false;
    for (SimpleAssignment assignment : assignments) {
      completed = assignment.isComplete();
      if (completed == false) {
        return completed;
      }
    }
    return completed;
  }

  /**
   * Creates a String representation of this AssignmentGroup.
   * 
   * @return a String containing all of the assignments in this AssignmentGroup
   */
  public String toString() {
    // index+1 + ":" + score + "/" + totalPossible + " "
    double roundedPoints;
    double roundedTotal;
    String result = "";
    for (int i = 0; i < assignments.size(); i++) {
      roundedPoints =  (double) ((int)(getAssignment(i).getPoints())*100)/100;
      roundedTotal = (double) ((int)(getTotalPossible()*100))/100;
      result += ((i+1) + ":" + roundedPoints);
      result += "/" + roundedTotal + " ";
    }
    return result;
  }
}
