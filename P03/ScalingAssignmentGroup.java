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

/**
 * A wrapper class for an AssignmentGroup object, this class models a collection of scores which are
 * worth a given percentage of a student's grade. When calculating the point values for this type of
 * assignment group, points are scaled to a given percentage of possible points, and everything over
 * that cap is considered full credit. 
 * 
 */
public class ScalingAssignmentGroup {
  private AssignmentGroup group;
  public final double PERCENT_OF_TOTAL;
  private double scalingFactor;

  /**
   * Basic constructor, initializes an assignment group which is worth the given percentage of a
   * student's grade which will be scaled by the given scaling factor. This method also creates the
   * AssignmentGroup that this object will use to store its assignments.
   * 
   * @param percent - the percent of the total grade that this assignment group represents, assumed
   *                to be between 0 and 1.
   * @param scale   - the factor by which the points of this assignment group should be scaled,
   *                assumed to be between 0 and 1.
   */
  public ScalingAssignmentGroup(double percent, double scale) {
    group = new AssignmentGroup(percent);
    PERCENT_OF_TOTAL = percent;
    scalingFactor = scale;
  }

  /**
   * Adds a single assignment object to this ScalingAssignmentGroup
   * 
   * @param assignment - the SimpleAssignment to add
   */

  public void addAssignment(SimpleAssignment assignment) {
    group.addAssignment(assignment);
  }

  /**
   * Adds a batch of assignments to this ScalingAssignmentGroup in the order they appear
   * 
   * @param assignmentBatch - a perfect-size array of SimpleAssignments to add; you may assume there
   *                        are no null values present in this array
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    for (SimpleAssignment assignment : assignmentBatch) {
      group.addAssignment(assignment);
    }

  }

  /**
   * Retrieves an assignment at the given index in the ScalingAssignmentGroup
   * 
   * @param i - the index of the assignment to access (0-based)
   * @return the assignment at the given index, or null if the index is out of bounds
   */

  public SimpleAssignment getAssignment(int i) {
    return group.getAssignment(i);
  }

  /**
   * Accesses the number of assignments currently stored in this ScalingAssignmentGroup
   * 
   * @return the number of assignments present in this ScalingAssignmentGroup
   */

  public int getNumAssignments() {
    return group.getNumAssignments();
  }

  /**
   * Retrieves the total earned points of the assignments in this group, and returns either the
   * actual number of earned points or, if the total is higher than the scaled points possible,
   * returns the maximum number of points possible.
   * 
   * That is, if the reported earned points are 90 and the total number of points possible is 100,
   * but the scalingFactor is .8, this method would return 80 (or .8*100).
   * 
   * @return the number of earned points across all assignments in this group, which cannot exceed
   *         the number of possible points when accounting for the scalingFactor
   */
  public double getPoints() {
    double totalEarnedPoints = 0;
    double scaledPointsPossible = scalingFactor * group.getTotalPossible();
    for (int i = 0; i < group.getNumAssignments(); i++) {
      totalEarnedPoints += group.getAssignment(i).getPoints();
      // System.out.println("Points: " + totalEarnedPoints);
      if (totalEarnedPoints > scaledPointsPossible) {
        return scaledPointsPossible;
      }
    }
    return totalEarnedPoints;
  }

  /**
   * Retrieves the total number of possible points of the assignments in this group, multiplied by
   * this ScalingAssignmentGroup's scalingFactor value. That is, if there are 100 points possible
   * with a scalingFactor of .8, this method would return the value 80.
   * 
   * @return the total number of points possible, scaled by the scalingFactor
   */
  public double getTotalPossible() {
    int sum = 0;
    for (int i = 0; i < group.getNumAssignments(); i++) {
      sum += group.getAssignment(i).POINTS_POSSIBLE;
    }
    return (sum * scalingFactor);
  }

  /**
   * Reports whether all assignments in this group are completed
   * 
   * @return true if all assignments in this group are completed, false otherwise
   */
  public boolean isComplete() {
    boolean completed = false;
    for (int i = 0; i < group.getNumAssignments(); i++) {
      completed = group.getAssignment(i).isComplete();
      if (completed == false) {
        return completed;
      }
    }
    return completed;
  }

  /**
   * Creates a String representation of this ScalingAssignmentGroup. This is given as a percent of
   * possible points, calculated as getPoints()/getTotalPossible(). For any total earned point value
   * over the ceiling percent of total possible points, this will be 1.
   * 
   * @return a String containing the percent of points earned for this ScalingAssignmentGroup,
   *         scaled by the given scalingFactor
   */
  public String toString() {
    
    double score = (double) getPoints()/getTotalPossible();
    /* Multiple score by 100 to get two decimal places, cast to int to get rid of remaining decimal
     * points, cast back to double so the last two digits can be turned into decimal points, and 
     * divide by 100 to round number to the hundredth place.
     */
    return ((double)((int) (score*10000))/100+"%");
  }

}
