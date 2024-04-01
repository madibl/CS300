//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Froggie Feeding Frenzie
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
// Persons:         none
// Online Sources:  none
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a type Bug enemy, which is then extended for the various bug enemies.
 */
public class Bug extends GameActor {
  private static final String IMG_PATH = "bug.png";
  private int points;
  
  /**
   * Creates a new Bug object with the provided information.
   * 
   * @param x - the x-coordinate for the center of this bug
   * @param y - the y-coordinate for the center of this bug
   * @param points - the number of points the Bug is worth
   */
  public Bug(float x, float y, int points){
    super(x, y, IMG_PATH);
    this.points = points;
  }
  
  /** 
  * Get how many points this Bug is worth
  *
  * @return int - the number of points this bug is worth
  */
  public int getPoints(){
    return points;
  }
  
  /**
  * Determines whether or not this bug has been eaten by the Frog.
  * 
  * @param f - the frog that has possibly eaten this bug
  * @return true -  if Bug's hit box overlaps that Frog's Tongue's Hitbox, false if otherwise
  */
  public boolean isEatenBy(Frog f){
    try {
      if (f.getTongueHitbox().doesCollide(getHitbox())) {
        return true;
      }
    } catch (IllegalStateException e){
      return false;
    }
  return false;
  }
}
