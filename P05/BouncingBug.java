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

import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * This class models a bouncing variant of type Bug character in game
 */
public class BouncingBug extends Bug implements Moveable {

  private boolean goDown; // keep track if bug is moving up or down
  private boolean goLeft; // keep track if bug is left or right
  private static final int POINTS = 100; // a constant, number of points ALL BouncingBugs are worth,
                                         // 100
  private Random randGen; // a random generator to determing initial directions
  private int[] speedNums = {0, 0}; // number of pixels to move horizontally and vertically,
                                    // formatted [dx, dy]

  /**
   * Creates a new Bouncing Bug object using the given parameters. Randomly determines if the bug
   * will initially move left or right. The same applies to if the bug will up or down.
   * 
   * @param x-,  the x-coordinate for the center of this BouncingBug
   * @param y-,  the y-coordinate for the center of this BouncingBug
   * @param dx-, the number of pixels to move horizontally
   * @param dy-, the number of pixels to move vertically
   */
  public BouncingBug(float x, float y, int dx, int dy) {
    super(x, y, POINTS);
    speedNums[0] = dx;
    speedNums[1] = dy;
  }

  /**
   * Moves this BouncingBug dx pixels left or right (depending on the current horizontal direction)
   * and dy pixels up or down (depending on the current vertical direction). The Bug's Hitbox should
   * also move with the BouncingBug. The bug only changes its xy-coordinates if it should move. If
   * the center of the Bouncing Bug hits an end of the window it will switch directions. Ex. The Bug
   * is going down and left and hits the left side of the screen then the Bug will be going down and
   * right.
   * 
   */
  @Override
  public void move() {

    if (shouldMove()) {
      float newX = getX();
      float newY = getY();

      if (goLeft) {
          newX -= speedNums[0];
          if (newX < 0) {
              newX = 0;
              goLeft = false;
          }
      } else {
          newX += speedNums[0];
          if (newX > GameActor.processing.width) {
              newX = GameActor.processing.width;
              goLeft = true;
          }
      }

      if (goDown) {
          newY += speedNums[1];
          if (newY > GameActor.processing.height) {
              newY = GameActor.processing.height;
              goDown = false;
          }
      } else {
          newY -= speedNums[1];
          if (newY < 0) {
              newY = 0;
              goDown = true;
          }
      }

      setX(newX);
      setY(newY);
      moveHitbox(newX, newY);
  
    }

  }

  /*
   * Reports if the BouncingBug needs to move.
   * 
   * @return true - this bug should always move
   */
  @Override
  public boolean shouldMove() {
    // TODO Auto-generated method stub
    return true;
  }

}
