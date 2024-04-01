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

import java.util.Random;

/**
 * This class models a SimpleAssignment that is used for other AssignmentGroup classes. 
 * Includes functionality for earning points and awarding bonuses.
 */
public class SimpleAssignment {

  private boolean bonusAvailable; // Indicates whether a 5% bonus is offered (and has not yet been
                                  // applied) for this assignment
  private boolean isComplete; // Indicates whether this assignment has been completed or not
  public final int POINTS_POSSIBLE; // The number of points possible on this assignment; must be
                                    // strictly positive
  private double pointsEarned; // The score the student received on this assignment; only set when
                               // the assignment is completed


  // CONSTRUCTORS
  public SimpleAssignment(int points) {
    isComplete = false;
    if (points < 1){
      POINTS_POSSIBLE = 1;
    } else {
      POINTS_POSSIBLE = points;
    }
  }

  public SimpleAssignment(int points, boolean bonus) {
    isComplete = false;
    if (points < 1){
      POINTS_POSSIBLE = 1;
    } else {
      POINTS_POSSIBLE = points;
    }
    bonusAvailable = bonus;
  }

  /**
   * If the assignment has been completed and there is a bonus available, adds 5% of possible points
   * to the earned points total, up to a maximum of the number of possible points.
   */

  public void awardBonus() {
    if (isComplete() && bonusAvailable) {
      pointsEarned += (0.05 * POINTS_POSSIBLE);

      // if student exceeds the max possible points, set points earned to number of possible points
      if (pointsEarned > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE;
      }
      bonusAvailable = false;
    }
  }

  /**
   * Completes the assignment and records the provided score.
   * 
   * @param score - the score to record for this assignment. If less than 0, recorded score is 0. If
   *              greater than the number of points possible, recorded score is the number of points
   *              possible.
   */
  public void complete(double score) {
   
    if (isComplete != true) {
      if (score < 0) {
        pointsEarned = 0;
      } else if (score > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE;
      } else {
        pointsEarned = score;
      }
      isComplete = true;
    }
  }

  /**
  * Creates random assignment scores with a mean of 80% and a standard
  deviation
  * of 15%.
  * @param n the number of assignment scores to create
  * @param maxScore the maximum score value to create
  * @return an array of the SimpleAssignments created under those requirements
  */
  public static SimpleAssignment[] makeRandomAssignments(int n, int maxScore) {
    Random rand = new Random();
    SimpleAssignment[] result = new SimpleAssignment[n];
    double mean = 0.80;
    double std = 0.15;
    for (int i=0; i<n; i++) {
      double pctScore = rand.nextGaussian(mean, std);
      result[i] = new SimpleAssignment(maxScore);
      result[i].complete(pctScore*maxScore);
    }
    return result;
  }

  /**
   * Accesses whether or not the assignment is complete
   * @return the boolean isComplete
   */
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Returns the number of points earned on the assignment.
   * 
   * @return
   */
  public double getPoints() {
    return pointsEarned;
  }

  /**
   * Creates and returns a String representation of this SimpleAssignment.
   */
  public String toString() {
    String result = "";
    String letterGrade = "";
    double percentScore = getPoints() / POINTS_POSSIBLE;

    if (percentScore >= 90.0) {
      letterGrade = "A";
    } else if (percentScore >= 80.0 && percentScore < 90.0) {
      letterGrade = "B";
    } else if (percentScore >= 70.0 && percentScore < 80.0) {
      letterGrade = "C";
    } else if (percentScore >= 60.0 && percentScore < 70.0) {
      letterGrade = "D";
    } else {
      letterGrade = "F";
    }

    result += ("Total percent: " + percentScore + "%, which is a(n) " + letterGrade);

    return result;
  }

}
