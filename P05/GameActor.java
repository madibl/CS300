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
import processing.core.PApplet;
import processing.core.PImage;

public class GameActor {
  private float[] coordinates;
  private Hitbox hitbox;
  protected PImage image;
  protected static PApplet processing;

  public GameActor(float x, float y, String imgPath) {
    this.coordinates = new float[] {x, y};
    
    if (processing != null) {
      this.image = processing.loadImage("images" + File.separator + imgPath);
      // Draw the image at the provided coordinates
      processing.image(image, coordinates[0], coordinates[1]);
    } else {
      throw new IllegalStateException("Processing is null.");
    }
    
    hitbox = new Hitbox(coordinates[0], coordinates[1], 50, 50);
  }

  public static void setProcessing(PApplet processing) {
    GameActor.processing = processing;
  }

  public float getX() {
    return this.coordinates[0];
  }

  public float getY() {
    return this.coordinates[1];
  }

  public void setX(float newX) {
    this.coordinates[0] = newX;
  }

  public void setY(float newY) {
    this.coordinates[1] = newY;
  }

  public Hitbox getHitbox() {
    return this.hitbox;
  }

  public void moveHitbox(float x, float y) {
    hitbox.setPosition(x, y);
  }

  public void draw() {
    if (processing != null) {
      // Draw the image at the GameActor's coordinates
      processing.image(image, coordinates[0], coordinates[1]);
      // Visualize the hitbox for debugging
      hitbox.visualizeHitbox();
    }
  }
}
