//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Madison B Lin
// Campus ID: 9086068708
// WiscEmail: mblin@wisc.edu
////////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.Iterator;
// No additional imports besides the above are allowed

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit. There are FOUR methods with TODO tags:
//
// 1. The constructor
// 2. hasNext()
// 3. next()
// 4. test()
//
// Additionally, you will need to modify the class declaration to implement the
// appropriate interface.
//
// NO variables outside of any method may be added to this class.
//
// Any additional methods added to this class must be PRIVATE STATIC BOOLEAN
// tester methods only.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE.
//
// BE SURE TO SUBMIT THIS FILE AS UNIT QUIZ 4 **PART 2**, not part 4.
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class is an Iterator that returns all integers between a lower (inclusive) and upper
 * (inclusive) limit in _descending_ order that are powers of 2.
 *
 * For example, if the limits are 3 and 128, then the numbers returned are 128, 64, 32, 16, and 4.
 * Each of these values is a power of two (2). For example, 64 is 2 raised to the power 6 (often
 * written as 2^6). The value 4 is the last returned because is the smallest power of two that is >=
 * the lower limit in this example (which was 3). The value 128 is returned because it is the
 * greatest power of two less than OR EQUAL to the upper limit.
 */
// TODO: this class should implement the Iterator interface, return values of
// type Integer.
public class PowersOfTwoBetweenIterator implements Iterator<Integer> {

  /**
   * The next value to be returned from next().
   */
  private int nextValue;

  /**
   * The (inclusive) lower limit value of our iterator. All values returned from the iterator will
   * be greater than or equal to this value.
   */
  private final int lowerLimit;

  /**
   * Constructs a new PowersOfTwoBetweenIterator with an (inclusive) lower limit of lower, and an
   * (inclusive) upper limit of upper. That is, all values returned by the iterator will be greater
   * than or equal to lower and less than or equal to upper. Moreover, they will all be powers of
   * two (2).
   *
   * @param lower the inclusive lower limit value. Must be <= upper.
   * @param upper the inclusive upper limit value. Must be a power of two.
   * @throws IllegalArgumentException if lower > upper or upper is not a power of two. You can use
   *                                  any exception message.
   */
  public PowersOfTwoBetweenIterator(int lower, int upper) {
    // TODO: check the parameters and maybe throw an exception. You can use
    // isPowerOfTwo() to check if lower is a power of two.
    if (lower > upper)
      throw new IllegalArgumentException("lower cannot be greater than upper limit.");
    if (!(isPowerOfTwo(upper)))
      throw new IllegalArgumentException("Upper is not a power of two.");
    // TODO: initialize fields of this class. The first value we return is
    // the upper bound.
    this.lowerLimit = lower;
    this.nextValue = upper;

  }

  /**
   * PROVIDED: Returns true if the argument is a power of two.
   *
   * @param value an integer to check.
   * @return true if value is a power of two; false otherwise.
   */
  private boolean isPowerOfTwo(int value) {
    // In binary, a power of two will have exactly one bit with value 1. If we
    // zero out all the other bits, we should end up with the same value
    // because all the other bits were 0 anyway.
    return (value & Integer.lowestOneBit(value)) == value;
  }

  /**
   * Returns true if a call to next() will return a value.
   *
   * This method MUST NOT change any of the values of the fields.
   *
   * @return returns true if there is another power of two greater than or equal to lowerLimit that
   *         we have not yet returned (ie, if nextValue is greater than or equal to lowerLimit);
   *         false otherwise.
   */
  public boolean hasNext() {
    if (nextValue >= lowerLimit)
      return true;
    return false;

  }

  /**
   * Returns the powers of two greater than or equal to the lowerLimit and less than or equal to the
   * upper limit in descending order. Then, updates this iterator so that nextValue is the next
   * value we will return (on the subsequent call to next()). If there is no next value, then set
   * nextValue to (lowerLimit - 1).
   *
   * NOTE: be careful not to accidentally skip the first return value!
   *
   * @return the next power of two that we have not returned yet.
   * @throws NoSuchElementException if there are no powers of two left that are greater than or
   *                                equal to the limit. You can use any exception message.
   */
  public Integer next() {
    // TODO: throw NoSuchElementException as needed
    if (!(nextValue >= lowerLimit))
      throw new NoSuchElementException(
          "There are no powers of two left that are greater than or equal to the limit");
    // TODO: keep track of nextValue so we can return it
    int toReturn = nextValue;

    // TODO: update next value using a loop and the provided isPowerOfTwo() method.
    while (nextValue >= lowerLimit) {
      // update next value: next value would be that value divided by two (
      nextValue = nextValue / 2;
      // check to make sure it is
      if (isPowerOfTwo(nextValue)) {
        return toReturn;
      }
    }
    
    // if no next value set nextValue to lowerlimit -1;
    nextValue = lowerLimit - 1;
    return toReturn;
  }

  /**
   * Tester method for our iterator.
   *
   * @return true if the test passes! false otherwise.
   */
  private static boolean test() {
    int[] expected = {1024, 512, 256, 128, 64};

    // TODO: construct an iterator with lower and upper limits: 40 and 1024.
    PowersOfTwoBetweenIterator iterator = new PowersOfTwoBetweenIterator(40,1024);
    
    // TODO: extract five (5) items from the iterator and check that they are
    // the expected numbers. ALSO check that hasNext() returns true. You may
    // use a loop to do this if you wish.
    int i = 0;
    while(iterator.hasNext()) {
      if (i >= expected.length) {
        System.out.println("Failed Check 1");
        return false;
      }
      int next = iterator.next();
      if (next != expected[i]) {
        System.out.println("Failed Check 2");
        return false;
      }
      System.out.println(next);
      i++;
    }
    

    // TODO: check that hasNext() is now false, AND any further call to next()
    // throws a NoSuchElementException.
    if (iterator.hasNext()) {
      System.out.println("Failed Check 3");
      return false;
    }
    try {
      iterator.next();
      System.out.println("Failed Check 4");
      return false;
    } catch (NoSuchElementException e) {
      
    }

    // all tests pass
    return true; 
  }

  public static void main(String[] args) {
    boolean result = test();
    System.out.println("test: " + (result ? "PASS" : "FAIL"));
  }
}
