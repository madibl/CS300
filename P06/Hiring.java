//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TA Hirer
// Course:   CS 300 Fall 2023
//
// Author:   Madison Lin
// Email:    mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Aarya Gadekar
// Partner Email:   agadekar@wisc.edu
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
 * A simple program for solving various hiring problems
 */
class Hiring {

  /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a
   * maximum number of tas to hire, return the set of hires made using a greedy strategy that always
   * chooses the candidate that increases hours covered the most.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the list of those currently hired
   * @param hiresLeft  - the maximum number of candidates to hire
   * @return a list of hired candidates
   */
  public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired,
      int hiresLeft) {
    // Base case
    // if there is no more tas to hire, return current list
    if (hiresLeft <= 0)
      return hired;
    // if the initial list is null, return null (no solution)
    if (candidates == null)
      return null;
    
    // Recursive case
    Candidate bestCandidate = candidates.get(0); // variable to store best candidate
    int bestNumCoveredHours = 0; // variable to store the amound of hours that candidate covers
    // loop through and find best candidate
    for (Candidate c : candidates) {
      if (hired.withCandidate(c).numCoveredHours() > bestNumCoveredHours) {
        bestCandidate = c;
        bestNumCoveredHours = hired.withCandidate(c).numCoveredHours();
      }
    }

    // Add the best candidate to the hired list
    hired.add(bestCandidate);
    // Remove the best candidate from the candidates list
    candidates.remove(bestCandidate);
    // Recurse with the remaining candidates, hired list, and hiresLeft
    return greedyHiring(candidates, hired, hiresLeft - 1);
  }

  /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a
   * maximum number of tas to hire, return the set of hires that maximizes number of scheduled
   * hours. In this function, we will ignore pay rates.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the list of those currently hired
   * @param hiresLeft  - the maximum number of candidates to hire
   * @return a list of hired candidates
   */
  public static CandidateList optimalHiring(CandidateList candidates, CandidateList hired,
      int hiresLeft) {
    // Base Cases
    // if there are no more tas to hire, return the current hired list
    if (hiresLeft <= 0)
      return hired;
    // if the initial list is null, return null (no solution)
    if (candidates == null)
      return null;

    // Recursive Case
    // variable to store best permutation
    CandidateList bestList = hired;
    // get permutation of each candidate
    for (int i = 0; i < candidates.size(); i++) {
      // stores recursive call that calls until it reaches amount of hires needed
      CandidateList newList = optimalHiring(candidates.withoutCandidate(candidates.get(i)),
          hired.withCandidate(candidates.get(i)), hiresLeft - 1);
      // if the new permutation covers more hours than the best one, set it as best one
      if (newList.numCoveredHours() > bestList.numCoveredHours()) {
        bestList = newList;
      }
    }
    // return best permutation
    return bestList;
  }

  /**
   * Knapsack dual problem: find the minimum-budget set of hires to achieve a threshold number of
   * hours. That is, given a set of candidates, a set of already-hired candidates, and a minimum
   * number of hours we want covered, what is the cheapest set of candidates we can hire that cover
   * at least that minimum number of hours specified.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the set of candidates already hired
   * @param minHours   - the minimum number of hours we want to cover total
   * @return a list of hired candidates or null if no set of candidates achieves the requested
   *         number of hours
   */
  public static CandidateList minCoverageHiring(CandidateList candidates, CandidateList hired,
      int minHours) {
    // base cases
    // if the numCoveredHours reaches the minHours
    if (hired.numCoveredHours() >= minHours)
      return hired;

    // variable to store best permutatation
    CandidateList bestList = null;
    int bestCost = Integer.MAX_VALUE;

    // recursive case
    // get permutation of each candidate
    for (int i = 0; i < candidates.size(); i++) {
      CandidateList newList = minCoverageHiring(candidates.withoutCandidate(candidates.get(i)),
          hired.withCandidate(candidates.get(i)), minHours);
      //if the cost of permutation is lower than the best case, store as best case to compare
      if (newList != null && newList.totalCost() < bestCost
          && newList.numCoveredHours() >= minHours) {
        bestList = newList;
        bestCost = bestList.totalCost(); 
      }

    }
    // base case 2
    // return even if no solution reached (should return null)
    return bestList;
  }
}