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

import java.util.Random;
import java.util.ArrayList;

/**
 * A class that tests all of the methods of the Hiring class
 */
public class HiringTesting {
  /**
   * Method that tests if the recursive call produces the right return value in
   * greedyHiring()
   * 
   * @returns true if the expected list matches the list returned by
   *          greedyHiring()
   */
  public static boolean greedyHiringRecursiveTest() {
    boolean allTestsPassed = true;

    // Test 1: Original Test
    {
      CandidateList candidates = new CandidateList();
      Candidate alice = new Candidate(new boolean[] { true, true, true, false, false, true });
      Candidate bob = new Candidate(new boolean[] { true, false, false, true, false, true });
      Candidate carol = new Candidate(new boolean[] { true, false, true, true, true, true });
      candidates.add(alice);
      candidates.add(bob);
      candidates.add(carol);

      CandidateList hired = new CandidateList();
      int hiresLeft = 2;

      CandidateList result = Hiring.greedyHiring(candidates, hired, hiresLeft);

      if (result.size() != 2 ||
          (result.get(0) != alice && result.get(0) != carol) ||
          (result.get(1) != alice && result.get(1) != carol) ||
          (result.get(0) == result.get(1))) {
        System.out.println("Test 1 failed");
        allTestsPassed = false;
      }
    }

    // Test 2: Different candidates, same logic
    {
      CandidateList candidates = new CandidateList();
      Candidate david = new Candidate(new boolean[] { true, true, false, false, true });
      Candidate eve = new Candidate(new boolean[] { true, false, false, true, true });
      Candidate frank = new Candidate(new boolean[] { true, true, true, true, true });
      candidates.add(david);
      candidates.add(eve);
      candidates.add(frank);

      CandidateList hired = new CandidateList();
      int hiresLeft = 2;

      CandidateList result = Hiring.greedyHiring(candidates, hired, hiresLeft);

      if (result.size() != 2 ||
          (result.get(0) != david && result.get(0) != frank) ||
          (result.get(1) != david && result.get(1) != frank) ||
          (result.get(0) == result.get(1))) {
        System.out.println("Test 2 failed");
        allTestsPassed = false;
      }
    }

    return allTestsPassed;
  }

  /**
   * Method that tests the base cases in greedyHiring()
   * 
   * @returns true if the expected list matches the list returned by
   *          greedyHiring()
   */
  public static boolean greedyHiringBaseTest() {
    // Create a list of candidates.
    CandidateList candidates = new CandidateList();

    Candidate alice = new Candidate(new boolean[] { true, true, true, false, false, true });
    Candidate bob = new Candidate(new boolean[] { false, false, false, true, false, true });


    // [0] ###..#
    // [1] ...#.#

    candidates.add(alice);
    candidates.add(bob);


    CandidateList hired = new CandidateList();
    int hiresLeft = 1;

    // Greedy hiring algorithm.
    CandidateList result = Hiring.greedyHiring(candidates, hired, hiresLeft);

    // Check that the result is correct.
    if (result.size() != 1) {
      System.out.println("Greedy hiring algorithm returned the wrong number of candidates.");
      return false;
    }

    if (result.get(0) != alice) {
      System.out.println("Greedy hiring algorithm returned the wrong candidate.");
      return false;
    }

    return true;
  }

  /**
   * Method that tests the base tests in optimalHiring()
   * 
   * @returns true if the expected list matches the list returned by
   *          optimalHiring()
   */
  public static boolean optimalHiringBaseTest() {
    boolean allTestsPassed = true;

    {
      CandidateList candidates = new CandidateList();
      Candidate alice = new Candidate(new boolean[] { true, false, false, true, true, true });
      Candidate bob = new Candidate(new boolean[] { true, true, false, false, true, false });
      Candidate carol = new Candidate(new boolean[] { false, false, true, true, false, true });
      candidates.add(alice);
      candidates.add(bob);
      candidates.add(carol);

      CandidateList hired = new CandidateList();
      int hiresLeft = 0;

      CandidateList result = Hiring.optimalHiring(candidates, hired, hiresLeft);

      if (result.size() != 0) { // should return a null list
        System.out.println("Test 1 failed");
        allTestsPassed = false;
      }
    }

    return allTestsPassed;
  }

  /**
   * Method that tests if the recursive call produces the right return value in
   * optimalHiring()
   * 
   * @returns true if the expected list matches the list returned by
   *          optimalHiring()
   */
  public static boolean optimalHiringRecursiveTest() {
    boolean allTestsPassed = true;

    // Test 1: Original Test
    {
      CandidateList candidates = new CandidateList();
      Candidate alice = new Candidate(new boolean[] { true, false, false, true, true, true });
      Candidate bob = new Candidate(new boolean[] { true, true, false, false, true, false });
      Candidate carol = new Candidate(new boolean[] { false, false, true, true, false, true });
      candidates.add(alice);
      candidates.add(bob);
      candidates.add(carol);

      CandidateList hired = new CandidateList();
      int hiresLeft = 2;

      CandidateList result = Hiring.optimalHiring(candidates, hired, hiresLeft);
      // System.out.println(result);

      if (result.size() != 2 ||
          (result.get(0) != bob && result.get(0) != carol) ||
          (result.get(1) != bob && result.get(1) != carol) ||
          (result.get(0) == result.get(1))) {
        System.out.println("Test 1 failed");
        allTestsPassed = false;
      }
    }

    return allTestsPassed;

  }

  /**
   * Method that tests if the recursive call produces the right return value in
   * optimalHiring(), given random input
   * 
   * @returns true if the expected list matches the list returned by
   *          optimalHiring()
   */
  public static boolean optimalHiringFuzzTest() {

    Random randgen = new Random(42); // set seed

    // generate 150 random candidates
    for (int i = 0; i < 150; i++) {
      int numHours = randgen.nextInt(5) + 1;
      int numCandidates = randgen.nextInt(10) + 1;
      int desiredHires = randgen.nextInt(numCandidates) + 1;

      CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours, numCandidates);

      ArrayList<CandidateList> solutions = HiringTestingUtilities.allOptimalSolutions(candidates, desiredHires);

      CandidateList ourSolution = Hiring.optimalHiring(candidates, new CandidateList(), desiredHires);

      if (HiringTestingUtilities.compareCandidateLists(solutions, ourSolution) == false)
        return false;
    }
    return true;
  }

  /**
   * Method that tests the base cases in minCoverageHiring()
   * 
   * @returns true if the expected list matches the list returned by
   *          minCoverageHiring()
   */
  public static boolean minCoverageHiringBaseTest() {
    boolean allTestsPassed = true;
    CandidateList candidates = new CandidateList();
    Candidate alice = new Candidate(new boolean[] { true, false, false, true, true, true });
    Candidate bob = new Candidate(new boolean[] { true, true, false, false, true, false });
    Candidate carol = new Candidate(new boolean[] { false, false, true, true, false, true });
    candidates.add(alice);
    candidates.add(bob);
    candidates.add(carol);

    CandidateList hired = new CandidateList();
    int minHours = 2;

    CandidateList result = Hiring.optimalHiring(candidates, hired, minHours);
    // System.out.println(result);

    if (result.size() != 2 ||
        (result.get(0) != bob && result.get(0) != carol) ||
        (result.get(1) != bob && result.get(1) != carol) ||
        (result.get(0) == result.get(1))) {
      System.out.println("Test 1 failed");
      allTestsPassed = false;
    }

    return allTestsPassed;
  }

  /**
   * Method that tests if the recursive call produces the right return value in
   * minCoverageHiring()
   * 
   * @returns true if the expected list matches the list returned by
   *          minCoverageHiring()
   */
  public static boolean minCoverageHiringRecursiveTest() {
    boolean allTestsPassed = true;

    // Test 1: Original Test
    {
      CandidateList candidates = new CandidateList();
      Candidate alice = new Candidate(new boolean[] { true, false, false, true, true, true }, 4);
      Candidate bob = new Candidate(new boolean[] { true, true, false, false, true, false }, 2);
      Candidate carol = new Candidate(new boolean[] { false, false, true, true, false, true }, 3);
      candidates.add(alice);
      candidates.add(bob);
      candidates.add(carol);

      CandidateList hired = new CandidateList();
      int minHours = 4;

      CandidateList result = Hiring.minCoverageHiring(candidates, hired, minHours);
      // System.out.println(result);

      if (result.size() != 1 ||
          (result.get(0) != bob && result.get(0) != alice)) {
        System.out.println("Test 1 failed");
        allTestsPassed = false;
      }
    }

    return allTestsPassed;
  }

  /**
   * Method that tests if the recursive call produces the right return value in
   * minCoverageHiring(), given random input
   * 
   * @returns true if the expected list matches the list returned by
   *          minCoverageHiring()
   */
  public static boolean minCoverageHiringFuzzTest() {

    Random randgen = new Random(42); // set seed

    // generate 150 random candidates
    for (int i = 0; i < 150; i++) {
      int numHours = randgen.nextInt(5) + 1;
      int numCandidates = randgen.nextInt(10) + 1;
      int minHours = randgen.nextInt(numHours) + 1;
      int maxSalary = (numCandidates+1) / 2;

      CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours, numCandidates, maxSalary);

      ArrayList<CandidateList> solutions = HiringTestingUtilities.allMinCoverageSolutions(candidates, minHours);
      CandidateList hired = new CandidateList();
      CandidateList ourSolution = Hiring.minCoverageHiring(candidates, hired, minHours);

      if (HiringTestingUtilities.compareCandidateLists(solutions, ourSolution) == false) {

        // DEBUGGING CODE
        // System.out.println("failed test num: " + i);
        // System.out.println("numHours: " + numHours);
        // System.out.println("numCandidates: " + numCandidates);
        // System.out.println("minHours: " + minHours);

        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // conduct all the tests and save them to variables
    boolean greedyHiringBase = greedyHiringBaseTest();
    boolean greedyHiringRecursive = greedyHiringRecursiveTest();
    boolean optimalHiringBase = optimalHiringBaseTest();
    boolean optimalHiringRecursive = optimalHiringRecursiveTest();
    boolean optimalHiringFuzz = optimalHiringFuzzTest();
    boolean minCoverageHiringBase = minCoverageHiringBaseTest();
    boolean minCoverageHiringRecursive = minCoverageHiringRecursiveTest();
    boolean minCoverageHiringFuzz = minCoverageHiringFuzzTest();
    System.out.println("Greedy Hiring Base Test: " + greedyHiringBase);
    System.out.println("Greedy Hiring Recursive Test: " + greedyHiringRecursive);
    System.out.println("Optimal Hiring Base Test: " + optimalHiringBase);
    System.out.println("Optimal Hiring Recursive Test: " + optimalHiringRecursive);
    System.out.println("Optimal Hiring Fuzz Test: " + optimalHiringFuzz);
    System.out.println("Min Coverage Hiring Base Test: " + minCoverageHiringBase);
    System.out.println("Min Coverage Hiring Recursive Test: " + minCoverageHiringRecursive);
    System.out.println("Min Coverage Hiring Fuzz Test: " + minCoverageHiringFuzz);

    System.out.println(greedyHiringBase && greedyHiringRecursive && optimalHiringBase && optimalHiringRecursive
        && optimalHiringFuzz && minCoverageHiringBase && minCoverageHiringRecursive && minCoverageHiringFuzz);

  }
}
