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
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A single bus on a single run of a route, snapshotted when it arrives at the BusStop we care about
 * (the stop the BST is organized around). Buses are Comparable with each other so that we can
 * insert them into a BST.
 */
public class Bus implements Comparable<Bus> {

  /**
   * The route this Bus is traveling on. Note that BusRoute indicates not just a set of stops but
   * the stop times, too. This is a particular run of that route during the day.
   */
  private BusRoute route;

  /**
   * The BusStop that is the BST is organized around.
   */
  private BusStop userStop;

  /**
   * The time that this Bus arrives at `userStop` along `route`.
   */
  private LocalTime arrivalTime;

  /**
   * Construct a new Bus on the given route and inform it of the particular BusStop our BusStopTree
   * is orgnaized around.
   * 
   * @param userStop the stop the BusStopTree is organized around.
   * @param route    the route this Bus is traveling on.
   */
  public Bus(BusStop userStop, BusRoute route) {
    this.userStop = userStop;
    this.route = route;
    this.arrivalTime = this.route.getArrivalTimeAtStop(userStop);
  }

  /**
   * Checks if the bus goes to the specified destination bus stop.
   *
   * @param destination the destination bus stop
   * @return true if the bus goes to the destination, false otherwise
   */
  public boolean goesTo(BusStop destination) {
    LocalTime arrival = this.route.getArrivalTimeAtStop(destination);
    return !(arrival == null || arrival.isBefore(arrivalTime));
  }

  /**
   * Checks if the bus runs on a specific day.
   *
   * @param day the day to check
   * @return true if the bus runs on the given day, false otherwise
   */
  public boolean runsOnDay(LocalDate day) {
    return this.route.runsOnDate(day);
  }

  /**
   * <p>
   * Compares this bus to another bus. Buses are compared by arrivalTime, then routeAndTripName,
   * then direction:
   * </p>
   * 
   * <p>
   * 1. Time the bus arrives at the user stop (earlier times are "less than" later times) <br>
   * 2. routeAndTripName (alphabetical) <br>
   * 3. The direction (outgoing then incoming) <br>
   * </p>
   * 
   * <p>
   * Note that LocalTime, String, and BusRoute.BusDirection all implement the Comparable interface,
   * so you should use their respective compareTo methods to compare the relevant values.
   * </p>
   * 
   * <p>
   * Example: Consider the following Bus-es:<br>
   * 1. OUTGOING D1 arriving at 13:00 <br>
   * 2. OUTGOING D2 arriving at 12:00 <br>
   * 3. OUTGOING D1 arriving at 12:00 <br>
   * 4. INCOMING D2 arriving at 12:00 <br>
   * 5. INCOMING J arriving at 9:00 <br>
   * </p>
   * 
   * <p>
   * The correct ordering of these Bus-es is as follows: <br>
   * 5. INCOMING J arriving at 9:00 (earliest time) <br>
   * 3. OUTGOING D1 arriving at 12:00 (D1 before D2)<br>
   * 2. OUTGOING D2 arriving at 12:00 (OUTGOING before INCOMING)<br>
   * 4. INCOMING D2 arriving at 12:00 <br>
   * 1. OUTGOING D1 arriving at 13:00 (latest time)<br>
   * </p>
   * 
   * @param other the other bus to compare to
   * 
   * @return a negative integer if this bus is "less than" the other bus, a positive integer if this
   *         bus is "greater than" the other bus, or 0 if the buses are equal.
   */
  @Override
  public int compareTo(Bus other) {
    // compare time bus arrives
      // earlier time is less than later time
    int compareArrival = this.getArrivalTime().compareTo(other.getArrivalTime());
    if  (compareArrival != 0)
      return compareArrival;
    // compare route and trip name
      // alphabetical
    int compareRoute = this.getName().compareTo(other.getName());
    if (compareRoute != 0)
      return compareRoute;
    // compare direction
      // outgoing before incoming
    int compareDirection = this.route.getDirection().compareTo(other.route.getDirection());
    if (compareDirection != 0)
      return compareDirection;
    
    return 0; 
  }

  /**
   * Returns the name of the bus.
   *
   * @return the name of the bus
   */
  public String getName() {
    return route.getRouteAndTripName();
  }

  /**
   * Returns the arrival time of the bus.
   *
   * @return the arrival time of the bus
   */
  public LocalTime getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Returns the arrival time at the specified destination bus stop.
   *
   * @param destination the destination bus stop
   * @return the arrival time at the destination
   */
  public LocalTime getArrivalAtDestination(BusStop destination) {
    return route.getArrivalTimeAtStop(destination);
  }

  /**
   * Returns a string representation of the Bus object.
   * 
   * This should be formatted as "[route rootStop arrivalTime]".
   *
   * @return a string representation of the Bus object.
   */
  @Override
  public String toString() {
    return "[" + route.getRouteAndTripName() + " " + arrivalTime + "]";
  }
}
