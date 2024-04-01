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
import java.util.NoSuchElementException;

/**
 * This class tests methods in the WorkoutBuilder class
 */
public class WorkoutBuilderTester {

  /**
   * Checks for the correctness of the WorkoutBuilder.clear() method
   * 
   * @return true if the method clears the list appropriately
   */
  public static boolean testClear() {
    Exercise.resetIDNumbers();
    WorkoutBuilder test = new WorkoutBuilder();
    test.add(new Exercise(WorkoutType.PRIMARY, "squat"));
    test.add(new Exercise(WorkoutType.WARMUP, "jumping jacks"));
    test.add(new Exercise(WorkoutType.COOLDOWN, "walk"));
    test.clear();
    return test.isEmpty() && test.getWarmupCount() == 0 && test.getPrimaryCount() == 0
        && test.getCooldownCount() == 0;
  }

  /**
   * checks for the correctness of the WorkoutBuilder.add() method
   * 
   * @return true if the method adds Exercises to the list correctly
   */
  public static boolean testAddExercises() {
    Exercise.resetIDNumbers();
    boolean test1 = true;
    boolean test2 = true;


    // add three
    WorkoutBuilder list1 = new WorkoutBuilder();

    Exercise primary = new Exercise(WorkoutType.PRIMARY, "squat");
    Exercise warmup = new Exercise(WorkoutType.WARMUP, "jumping jacks");
    Exercise cooldown = new Exercise(WorkoutType.COOLDOWN, "walk");

    list1.add(primary);
    if ((list1.getPrimaryCount() != 1 || list1.getWarmupCount() != 0
        || list1.getCooldownCount() != 0)) {
      test1 = false;
    }

    list1.add(warmup);
    if ((list1.getPrimaryCount() != 1 || list1.getWarmupCount() != 1
        || list1.getCooldownCount() != 0)) {
      test1 = false;
    }
    list1.add(cooldown);
    if ((list1.getPrimaryCount() != 1 || list1.getWarmupCount() != 1
        || list1.getCooldownCount() != 1)) {
      test1 = false;
    }

    if (!(list1.get(0).equals(warmup) && list1.get(1).equals(primary)
        && list1.get(2).equals(cooldown))) {
      test1 = false;
    }


    // add one to empty list
    WorkoutBuilder list2 = new WorkoutBuilder();
    Exercise cooldown2 = new Exercise(WorkoutType.COOLDOWN, "walk");
    list2.add(cooldown2);

    if (!(list2.get(0).equals(cooldown2)))
      test2 = false;
    if ((list2.getPrimaryCount() != 0 || list2.getWarmupCount() != 0
        || list2.getCooldownCount() != 1)) {
      test2 = false;
    }


    return test1 && test2;
  }

  /**
   * checks for the correctness of BOTH of the WorkoutBuilder.removeExercise() methods
   * 
   * @return true if both removeExercise methods remove the appropriate Exercise correctly
   */
  public static boolean testRemoveExercises() {

    Exercise.resetIDNumbers();
    // System.out.println();
    // System.out.println("TESTING testRemoveExercises");
    boolean test1 = true;
    boolean test2 = true;
    boolean test3 = true;

    // test remove by ID
    {
      WorkoutBuilder list = new WorkoutBuilder();

      Exercise primary = new Exercise(WorkoutType.PRIMARY, "squat");
      Exercise warmup = new Exercise(WorkoutType.WARMUP, "jumping jacks");
      Exercise cooldown = new Exercise(WorkoutType.COOLDOWN, "walk");

      list.add(primary);
      list.add(warmup);
      list.add(cooldown);

      // System.out.println(list);

      // remove jumping jacks
      try {
        list.removeExercise(2);
      } catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
      }
      // test counts are correct
      if ((list.getPrimaryCount() != 1 || list.getWarmupCount() != 0
          || list.getCooldownCount() != 1)) {
        test1 = false;
      }

      if (list.size() != 2) {
        test1 = false;
      }

      // System.out.println(list1.get(0).equals(primary));
      if (!(list.get(0).equals(primary) && list.get(1).equals(cooldown)))
        test1 = false;
    }
    {
      WorkoutBuilder list = new WorkoutBuilder();

      Exercise primary = new Exercise(WorkoutType.PRIMARY, "squat");
      Exercise warmup = new Exercise(WorkoutType.WARMUP, "jumping jacks");
      Exercise cooldown = new Exercise(WorkoutType.COOLDOWN, "walk");

      list.add(primary);

      list.add(warmup);
      list.add(cooldown);

      // System.out.println(list);

      // remove squat and jumping jack
      try {
        list.removeExercise(4);
        list.removeExercise(5);
      } catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
      }
      if ((list.getPrimaryCount() != 0 || list.getWarmupCount() != 0
          || list.getCooldownCount() != 1)) {
        test2 = false;
      }

      if (list.size() != 1) {
        test2 = false;
      }

      // System.out.println(list1.get(0).equals(cooldown));
      if (!(list.get(0).equals(cooldown)))
        test2 = false;


    }
    // test remove by type
    {
      WorkoutBuilder list = new WorkoutBuilder();

      Exercise primary = new Exercise(WorkoutType.PRIMARY, "squat");
      Exercise warmup = new Exercise(WorkoutType.WARMUP, "jumping jacks");
      Exercise cooldown = new Exercise(WorkoutType.COOLDOWN, "walk");

      list.add(primary);
      list.add(warmup);
      list.add(cooldown);

      // System.out.println(list);

      try {
        list.removeExercise(WorkoutType.PRIMARY);
        list.removeExercise(WorkoutType.COOLDOWN);
      } catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
      } catch (IndexOutOfBoundsException e) {
        System.out.println(e.getMessage());
      }

      if ((list.getPrimaryCount() != 0 || list.getWarmupCount() != 1
          || list.getCooldownCount() != 0)) {
        test3 = false;
      }

      if (list.size() != 1) {
        test3 = false;
      }

      // System.out.println(list1.get(0).equals(warmup));
      if (!(list.get(0).equals(warmup)))
        test3 = false;


    }



    return test1 && test2 && test3;
  }

  /**
   * checks for the correctness of the WorkoutBuilder.get() method
   * 
   * @return true if the get method returns the correct Exercise
   */
  public static boolean testGetExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder list1 = new WorkoutBuilder();

    Exercise primary = new Exercise(WorkoutType.PRIMARY, "squat");
    Exercise warmup = new Exercise(WorkoutType.WARMUP, "jumping jacks");
    Exercise cooldown = new Exercise(WorkoutType.COOLDOWN, "walk");

    list1.add(primary);
    list1.add(warmup);
    list1.add(cooldown);

    // System.out.println(list1);
    Exercise actual0 = list1.get(0);
    Exercise expected0 = warmup;
    // String expected0 = "WARMUP: jumping jacks (15)";
    Exercise actual1 = list1.get(1);
    Exercise expected1 = primary;
    // String expected1 = "PRIMARY: squat (14)";

    Exercise actual2 = list1.get(2);
    Exercise expected2 = cooldown;
    // String expected2 = "COOLDOWN: walk (16)";


    return expected0.equals(actual0) && expected1.equals(actual1) && expected2.equals(actual2);
  }

  /**
   * checks for the correctness of the isEmpty method
   * 
   * @return true if the method returns the correct boolean value
   */
  public static boolean testIsEmpty() {
    Exercise.resetIDNumbers();
    boolean test1 = false;
    boolean test2 = false;
    {
      WorkoutBuilder list = new WorkoutBuilder();
      boolean expected = true;
      boolean actual = list.isEmpty();
      if (actual == expected)
        test1 = true;
    }
    {
      WorkoutBuilder list = new WorkoutBuilder();
      list.add(new Exercise(WorkoutType.PRIMARY, "push up"));
      boolean expected = false;
      boolean actual = list.isEmpty();
      if (actual == expected)
        test2 = true;
    }

    return test1 && test2;
  }


  /**
   * a test suite method to run all your test methods
   * @return true if all tests pass
   */
  public static boolean runAllTests() {
    boolean clear = testClear(), add = testAddExercises(), remove = testRemoveExercises(),
        get = testGetExercises();

    System.out.println("test clear: " + (clear ? "pass" : "FAIL"));
    System.out.println("test add: " + (add ? "pass" : "FAIL"));
    System.out.println("test remove: " + (remove ? "pass" : "FAIL"));
    System.out.println("test get: " + (get ? "pass" : "FAIL"));

    // TODO: add calls to any other test methods you write
    boolean empty = testIsEmpty();

    return clear && add && remove && get && empty;
  }



  public static void main(String[] args) {
    // System.out.println();
    // System.out.println("Running tests...");
    runAllTests();
    demo();

  }

  /**
   * Helper method to display the size and the count of different boxes stored in a list of boxes
   * 
   * @param list a reference to an InventoryList object
   * @throws NullPointerException if list is null
   */
  private static void displaySizeCounts(WorkoutBuilder list) {
    System.out.println(
        "  Size: " + list.size() + ", warmupCount: " + list.getWarmupCount() + ", primaryCount: "
            + list.getPrimaryCount() + ", cooldownCount: " + list.getCooldownCount());
  }

  /**
   * Demo method showing how to use the implemented classes in P07 Inventory Storage System
   * 
   * @param args input arguments
   */
  public static void demo() {
    Exercise.resetIDNumbers();
    // Create a new empty WorkoutBuilder object
    WorkoutBuilder list = new WorkoutBuilder();
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add a primary exercise to an empty list
    list.add(new Exercise(WorkoutType.PRIMARY, "5k run")); // adds PRIMARY: 5k run (1)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "stretch")); // adds WARMUP: stretch (2) at the head
                                                           // of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.PRIMARY, "bench press")); // adds PRIMARY: bench press (3)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "upright row")); // adds WARMUP: upright row (4) at
                                                               // the head of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "db bench")); // adds WARMUP: db bench (5) at the head
                                                            // of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add more exercises to list and display its contents
    list.add(new Exercise(WorkoutType.COOLDOWN, "stretch")); // adds COOLDOWN: stretch (6) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups")); // adds COOLDOWN: sit-ups (7) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: sit-ups (7) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.PRIMARY, "deadlift")); // adds PRIMARY: deadlift (8)
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: stretch (6) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.WARMUP); // removes WARMUP: db bench (5)
    System.out.println(list); // prints list's content
    list.removeExercise(3); // removes PRIMARY: bench press (3) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    try {
      list.removeExercise(25); // tries to remove box #25
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // remove all warm-ups
    while (list.getWarmupCount() != 0) {
      list.removeExercise(WorkoutType.WARMUP);
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(1); // removes PRIMARY: 5k run (1) from the list -> empty list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts

    System.out.println();
    list.add(new Exercise(WorkoutType.COOLDOWN, "walk")); // adds COOLDOWN: walk (9) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(8); // removes PRIMARY: deadlift (8) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts

    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: walk (9) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.WARMUP, "pull-up")); // adds WARMUP: pull-up (10) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(10); // removes WARMUP: pull-up (10) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
  }

}
