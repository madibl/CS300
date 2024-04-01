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
 * This class models a CirclingBug variant of the Bug enemy.
 */
public class CirclingBug extends Bug implements Moveable {
  private float[] circleCenter; // the x,y-coordinates for the center of the circle path
  private static final int POINTS = 200; // constant, number of points ALL CirclingBugs are worth
                                         // 200
  private double radius; // the radius of the circle path this bug moves on
  private double ticks; // keeps track of how long the bug has been moving
  private int[] tintColor; // color used to tint the image when being drawn [Red, Green, Blue]


  /**
   * Creates a new CirclingBug object using the provided parameters. By default the number of ticks
   * for a new CirclingBug should be 0.0. The x,y-coordinates for the initial position of the Bug
   * must be calculated by using the equations given in the write-up for ticks = 0.0.
   * 
   * @param circleX   - the x-coordinate for the center of the circle path
   * @param circleY   - the y-coordinate for the center of the circle path
   * @param radius    - the radius of this CirclingBug's circle path
   * @param tintColor - an array containing the Red,Green, and Blue values for the tint color You
   *                  can assume that this array is ALWAYS length 3.
   */
  public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
    super(circleX, circleY, POINTS);
    this.ticks = 0.0;
    this.radius = radius;
    this.tintColor = tintColor;
    this.circleCenter = new float[] {circleX, circleY};


  }


  /**
   * Draws the image to the screen, tinting it with the tintColor before drawing it. After the image
   * is drawn to the screen the tinting effect will need to done undone by tinting it again with
   * white. (255, 255, 255)
   */
  @Override
  public void draw() {
    if (processing != null) {
      // tint the image
      processing.tint(tintColor[0], tintColor[1], tintColor[2]);
      // Draw the image at the GameActor's coordinates
      super.draw();
      // undo tint effect
      processing.tint(255, 255, 255);
      
    }
  }

  /**
   * Moves this CirclingBug to the next position on its circular path. (See write-up for more
   * details on how to calculate this path.) The Hitbox should move with the CirclingBug. The bug
   * only changes its xy-coordinates if it should move. Ticks will advance by 0.05 whenever this
   * method is called.
   */
  @Override
  public void move() {
    ticks += 0.05;
    float newX = (float) (radius * Math.cos(ticks) + circleCenter[0]);
    float newY = (float) (radius * Math.sin(ticks) + circleCenter[1]);
    // TODO Auto-generated method stub
    if (shouldMove()) {
      this.setX(newX);
      this.setY(newY);
      this.moveHitbox(newX, newY);
    }
  


  }

  /**
   * Reports if the CirclingBug needs to move
   */
  @Override
  public boolean shouldMove() {
    return true;
  }

}
