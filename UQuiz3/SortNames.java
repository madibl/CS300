//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Madison Lin
// Campus ID: 908 606 8708
// WiscEmail: mblin@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
// No additional imports besides java.util.Arrays are allowed

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit. There are FOUR methods with TODO tags:
//
// 1. min()
// 2. getMinimum()
// 3. sortNames()
// 4. test()
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
////////////////////////////////////////////////////////////////////////////////

/**
 * This class contains one public utility method - sortNames() - along with
 * several protected helper methods and a simple test method. The public method
 * sorts the names of conference attendees in increasing alphabetical order by
 * family name.
 * 
 * These names are stored in a perfect-size array with NO NULL VALUES and NO
 * REPEATED VALUES, and the sorting operations must be done IN-PLACE.
 * 
 * Be aware that the required tester method only relates to the sorting method;
 * you may add additional tests to verify the functionality of the helper methods
 * but this is NOT required.
 */
public class SortNames {
  
  /**
   * This method returns the smaller of two provided String objects, with respect to the result
   * of the String.compareTo() method. You may assume that s1 and s2 will NEVER be identical.
   * 
   * @param s1 one String to consider
   * @param s2 another String to consider
   * @return the smaller string between s1 and s2 with respect to alphabetical order
   */
  protected static String min(String s1, String s2) {
    // we assume s1 and s2 are NOT null; this does not need to be checked
    if (s1.compareTo(s2) < 0) {
      return s1;
    }
    else {
      return s2;
    }
    // 1. TODO: complete the implementation of this method 
  }

  /**
   * RECURSIVE!
   * 
   * Returns the SMALLEST element of the provided array contents beginning at index and going until
   * the end. For example, with data=[8, 2, 3, 7, 9] and index=2, the return value would be 3.
   * 
   * @param data an array of attendee names
   * @param index the FIRST index of the data partition to consider at this recursive level
   * @return the smallest element in the partition at this recursive level
   */
  protected static String getMinimum(String[] data, int index) {
    // we assume that index is >= 0 and < data.length; this does not need to be checked
    
    // 2. TODO: base case, when the index is the last element of the array
    if (index == data.length-1)
      return data[index];
    
    // 3. TODO: recursive case
    // recursively call getMinimum and set that to a variable that keeps track of the minimum
    String min = getMinimum(data, index+1);
    
    // compare the current to the minimum and return
    // if data[index] is less than min, return that, else return min
//    if (data[index].compareTo(min) < 0)
//      return data[index];
//    else {
//      return min;
//    }
    return (min(data[index], min));
    
    // Try to decompose problem into smaller problems of the same type as follows:
    //   data: [e1, e2, e3, e4, e5, e6]; index: 0
    // This array data can be divided into two partitions:
    //   sub_array1: [e1]; and
    //   sub_array2: [e2, e3, e4, e5, e6], 
    // If we can find the minimum of each partition, we can determine the 
    // minumum of the whole array. The solution to one sub-problem is trivial! 
    // The other sub-problem can be decomposed into smaller sub-problems of the same type.
    
  }
  
  /**
   * PROVIDED: Swaps the provided String objects within an array
   * 
   * @param data array of strings, all of which are unique
   * @param s1 one string to swap
   * @param s2 another string to swap
   * @throws ArrayIndexOutOfBoundsException if s1 or s2 are not present in data
   */
  protected static void swap(String[] data, String s1, String s2) {
    int i = indexOf(s1, data);
    int j = indexOf(s2, data);
    String temp = data[i];
    data[i] = data[j];
    data[j] = temp;   
  }
  
  /**
   * PROVIDED: Returns the index of the first occurrence of a given String
   * 
   * @param d    String to search for
   * @param data reference to an array of String objects
   * @return the index of the first occurrence of d within the array data, or -1 if no
   *         match found.
   */
  private static int indexOf(String d, String[] data) {
    for (int i = 0; i < data.length; i++) {
      if (data[i].equals(d))
        return i;
    }
    return -1;
  }
  
  /**
   * Sorts an array of names stored as "Lastname, Firstname" in alphabetical order using our 
   * recursive getMinimum() method.
   * 
   * This sorting operation MUST be done IN-PLACE, as this method does not have a return value.
   * 
   * @param names an unsorted array of names
   */
  public static void sortNames(String[] names) {
    // this is an in-place sorting algorithm, which MUST use getMinimum() to operate for full credit
    
    // 4. TODO: traverse the names array starting from index 0 (using a loop is fine here)
    for (int i = 0; i < names.length; i++) {
      swap(names, names[i], getMinimum(names, i));
    }
    // This algorithm maintains two partitions in the given array attendees:
    //   1) The part of the array which is already sorted, from index 0 to index i-1.
    //   2) The rest of the array which is unsorted, starting at index i to the end of the array.
    
    // 5. TODO: in each iteration, get the minimum element from the unsorted part of the array and
    //    swap it with the element at index i
  }
  
  /**
   * A short and simple test to verify that your sorting method is working properly
   * @return true if the test passes, otherwise false
   */
  protected static boolean test() {
    String[] names = {"Jensen, Michelle", "Zhang, Yiwei", "Brown, Bo", 
                          "Kacem, Mouna", "Samuelson, Ashley", "Mansi, Mark", 
                          "Klock, Rob", "LeGault, Hobbes"};
    String[] expected = {"Brown, Bo", "Jensen, Michelle", "Kacem, Mouna", 
                         "Klock, Rob", "LeGault, Hobbes", "Mansi, Mark", 
                         "Samuelson, Ashley", "Zhang, Yiwei"};
    
    // 6. TODO: call your sorting algorithm on the unsorted array
    sortNames(names);
    // 7. TODO: compare the result with the expected, sorted array; return true if they are
    //    identical and false otherwise. You may use Arrays.deepEquals() for this test.
    if (!(Arrays.deepEquals(names, expected)))
      return false;
    if (names.length != expected.length)
      return false;
    return true; 
  }

  public static void main(String[] args) {
    System.out.println("test(): "+(test()?"PASS":"fail"));
  }

}
