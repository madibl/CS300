//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    The Bus Stop Tree
// Course:   CS 300 Fall 2023
//
// Author:   Madison Lin
// Email:    mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.time.LocalTime;
import java.util.EmptyStackException;
import java.util.Iterator;

public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    // make two buses with different arrival time
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};

    int[] stopIds2 = {1, 2, 3, 4, 5};
    String[] stopTimes2 = {"06:00", "07:30", "09:00", "10:30", "12:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes2);
    // bus1 is less than bus2 since it is earlier
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    // System.out.println(bus1.getArrivalTime());
    // System.out.println(bus2.getArrivalTime());
    // System.out.println(bus1.compareTo(bus2));

    if (bus1.compareTo(bus2) != -1)
      return false;
    return true;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {

    // test if higher route (number is higher) means a lesser bus
    {
      int[] stopIds1 = {1, 2, 3, 4, 5};
      String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};

      // routes are different objects, but otherwise identical
      BusRoute route1 =
          BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
      BusRoute route2 =
          BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);
      Bus bus2 = new Bus(BusStop.getStop(1), route2);

      // System.out.println(bus1.getArrivalTime());
      // System.out.println(bus2.getArrivalTime());
      // System.out.println(bus1.compareTo(bus2));

      if (bus1.compareTo(bus2) <= 0)
        return false;
    }
    // test if lesser route (lower number) means that bus is greater
    {
      int[] stopIds1 = {1, 2, 3, 4, 5};
      String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};

      BusRoute route1 =
          BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
      BusRoute route2 =
          BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);
      Bus bus2 = new Bus(BusStop.getStop(1), route2);
      //
      // System.out.println(bus1.getArrivalTime());
      // System.out.println(bus2.getArrivalTime());
      // System.out.println(bus1.compareTo(bus2));

      if (bus1.compareTo(bus2) != -1)
        return false;
    }
    return true;

  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    // test if outgoing means a lesser bus
    {
      int[] stopIds1 = {1, 2, 3, 4, 5};
      String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};

      // routes are different objects, but otherwise identical
      BusRoute route1 =
          BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
      BusRoute route2 =
          BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);
      Bus bus2 = new Bus(BusStop.getStop(1), route2);

      // System.out.println(bus1.getArrivalTime());
      // System.out.println(bus2.getArrivalTime());
      // System.out.println(bus1.compareTo(bus2));

      if (bus1.compareTo(bus2) != -1) {
        // System.out.println("check 1");
        return false;
      }
    }
    // test if incoming means that bus is greater
    {
      int[] stopIds1 = {1, 2, 3, 4, 5};
      String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};

      BusRoute route1 =
          BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
      BusRoute route2 =
          BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);
      Bus bus2 = new Bus(BusStop.getStop(1), route2);

      // System.out.println(bus1.getArrivalTime());
      // System.out.println(bus2.getArrivalTime());
      // System.out.println(bus1.compareTo(bus2));

      if (bus1.compareTo(bus2) != 1) {
        System.out.println("check 2");
        return false;

      }
    }
    return true;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    BusStopTree test = new BusStopTree(1);
    return BusStopTree.isValidBST(test.getRoot());

  }


  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    // TODO: Default return value.
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);

    // construct tree
    Node<Bus> node1 = new Node<Bus>(bus1, null, null);
    Node<Bus> node2 = new Node<Bus>(bus2, null, null);
    Node<Bus> node4 = new Node<Bus>(bus4, node1, null);
    Node<Bus> root = new Node<Bus>(bus3, node2, node4);

    return !BusStopTree.isValidBST(root);

  }


  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    // TODO: Default return value.
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);

    // construct tree
    Node<Bus> node1 = new Node<Bus>(bus1, null, null);
    Node<Bus> node2 = new Node<Bus>(bus2, node1, null);
    Node<Bus> node4 = new Node<Bus>(bus4, null, null);
    Node<Bus> root = new Node<Bus>(bus3, node2, node4);


    return BusStopTree.isValidBST(root);
  }


  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    BusStopTree test = new BusStopTree(1);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    if (test.addBus(bus1) && test.size() == 1) {
      // System.out.println(test);
      return true;
    }
    return false;
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    BusStopTree test = new BusStopTree(1);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    if (!(test.addBus(bus2) && test.addBus(bus3) && test.addBus(bus1))) {
      return false;
    }
    // System.out.println(test);
    if (!BusStopTree.isValidBST(test.getRoot())) {
      return false;
    }

    if (!(test.size() == 3))
      return false;
    return true;
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    BusStopTree test = new BusStopTree(1);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    if (!(test.addBus(bus2) && test.addBus(bus3) && test.addBus(bus1))) {
      return false;
    }
    // System.out.println(test);
    if (!BusStopTree.isValidBST(test.getRoot())) {
      return false;
    }

    if (test.addBus(bus3) && test.size() != 3)
      return false;
    return true;
  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    // case thats empty
    {
      BusStopTree test = new BusStopTree(1);
      if (test.contains(bus1))
        return false;
    }
    // case that contains
    {
      BusStopTree test = new BusStopTree(1);
      test.addBus(bus2);
      test.addBus(bus3);
      test.addBus(bus1);
      if (!test.contains(bus3))
        return false;
      if (!test.contains(bus2))
        return false;
      if (!test.contains(bus1))
        return false;
    }
    // case that does not contain
    {
      BusStopTree test = new BusStopTree(1);
      test.addBus(bus2);
      test.addBus(bus1);
      if (test.contains(bus3))
        return false;
    }
    return true;
  }


  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"04:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"04:30", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"05:30", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    {
      BusStopTree test = new BusStopTree(1);
      test.addBus(bus2);
      test.addBus(bus3);
      test.addBus(bus1);

      LocalTime time = LocalTime.of(5, 0);
      Node<Bus> result = test.getFirstNodeAfter(time, test.getRoot());
      System.out.println("RESULT: " + result);
      if (!(result.getValue().getArrivalTime().compareTo(bus3.getArrivalTime()) == 0)) {
        return false;
      }

    }
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"08:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "07:50", "09:00", "11:20", "13:10"};
    String[] stopTimes3 = {"09:00", "09:40", "10:00", "12:00", "14:00"};
    String[] stopTimes4 = {"10:00", "07:00", "09:00", "11:00", "13:00"};

    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);

    BusStopTree test = new BusStopTree(1);
    test.addBus(bus1);
    test.addBus(bus2);
    test.addBus(bus3);
    test.addBus(bus4);

    LocalTime time = LocalTime.of(6, 30);
    Node<Bus> result = test.getFirstNodeAfter(time, test.getRoot());

    if (!(result != null && result.getValue().getArrivalTime().equals(LocalTime.of(7, 0)))) {
      return false;
    }
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"07:30", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:45", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"08:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"08:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"09:30", "07:00", "09:00", "11:00", "13:00"};

    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 5", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes5);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);

    {
      BusStopTree test = new BusStopTree(1);
      test.addBus(bus4);
      test.addBus(bus3);
      test.addBus(bus1);
      test.addBus(bus5);
      test.addBus(bus2);

      System.out.println(test);
      LocalTime time = LocalTime.of(8, 45);
      Node<Bus> result = test.getFirstNodeAfter(time, test.getRoot());
      System.out.println("RESULT: " + result);
      if (!(result != null && result.getValue().getArrivalTime().equals(LocalTime.of(9, 30)))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    // TODO: Default return value.
    return false;

  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    return false;
  }


  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    int[] stopIds1 = {1, 2, 4};
    int[] stopIds2 = {1, 4, 5};
    int[] stopIds3 = {1, 2, 4};
    String[] stopTimes1 = {"08:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "07:50", "09:00", "11:20", "13:10"};
    String[] stopTimes3 = {"09:00", "09:40", "10:00", "12:00", "14:00"};

    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes2);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds3, stopTimes3);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    BusStopTree test = new BusStopTree(1);
    test.addBus(bus1);
    test.addBus(bus2);
    test.addBus(bus3);


    BusForwardIterator iterator = new BusForwardIterator(test.getRoot(), test.getRoot());
    BusFilteredIterator filtered = new BusFilteredIterator(iterator, BusStop.getStop(3));

    try {
      if (filtered.hasNext()) {
        return false;
      }
    } catch (EmptyStackException e) {
      System.out.println("EmptyStackException caught: " + e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {
    BusStopTree test = new BusStopTree(1);
    // Add buses, where the first bus in the iterator should not be filtered out
    test.addBus(
        new Bus(BusStop.getStop(1), BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING,
            new int[] {1, 3, 5}, new String[] {"08:00", "09:00", "10:00"})));
    test.addBus(
        new Bus(BusStop.getStop(1), BusRoute.dummyRoute("Route 2", BusRoute.BusDirection.OUTGOING,
            new int[] {1, 3, 4}, new String[] {"08:30", "09:30", "10:30"})));
    test.addBus(
        new Bus(BusStop.getStop(1), BusRoute.dummyRoute("Route 3", BusRoute.BusDirection.OUTGOING,
            new int[] {1, 2, 4}, new String[] {"09:00", "10:00", "11:00"})));
    BusForwardIterator iterator = new BusForwardIterator(test.getRoot(), test.getRoot());
    BusFilteredIterator filtered = new BusFilteredIterator(iterator, BusStop.getStop(4));
    // check equivalent routes
    boolean check1 =
        filtered.hasNext() && filtered.next().getName().equals("Route 2");
    boolean check2 =
        filtered.hasNext() && filtered.next().getName().equals("Route 3");
    return check1 && check2;
  
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    BusStopTree test = new BusStopTree(1);
    // Add buses, where the first bus in the iterator should not be filtered out
    test.addBus(
        new Bus(BusStop.getStop(1), BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING,
            new int[] {1, 3, 4}, new String[] {"08:00", "09:00", "10:00"})));
    test.addBus(
        new Bus(BusStop.getStop(1), BusRoute.dummyRoute("Route 2", BusRoute.BusDirection.OUTGOING,
            new int[] {1, 3, 4}, new String[] {"08:30", "09:30", "10:30"})));
    test.addBus(
        new Bus(BusStop.getStop(1), BusRoute.dummyRoute("Route 3", BusRoute.BusDirection.OUTGOING,
            new int[] {1, 2, 4}, new String[] {"09:00", "10:00", "11:00"})));
    BusForwardIterator iterator = new BusForwardIterator(test.getRoot(), test.getRoot());
    BusFilteredIterator filtered = new BusFilteredIterator(iterator, BusStop.getStop(4));
    // check equivalent routes
    boolean check2 =
        filtered.hasNext() && filtered.next().getName().equals("Route 1");
    boolean check1 =
        filtered.hasNext() && filtered.next().getName().equals("Route 2");
    boolean check3 =
        filtered.hasNext() && filtered.next().getName().equals("Route 3");
    return check1 && check2 && check3;
  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus" + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
