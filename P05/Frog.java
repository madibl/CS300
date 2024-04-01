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
 * This class models the Frog character that the player controls in the game.
 */
public class Frog extends GameActor implements Moveable {

  private int health;
  private static final String IMG_PATH = "frog.png";
  private boolean isDragging;
  private float oldMouseX;
  private float oldMouseY;
  private Tongue tongue;

	/**
	* An instantiable class maintains data about a Frog in the Froggie Feeding Frenzie game. They an 
	* be drawn to the screen, dragged around by the mouse, and attack Bugs with its Tongue.
	*
	* @param x - , the x-coordinate for the center of the Frog and starting point of the tongue
	* @param y - , the y-coordinate for the center of the Frog and starting point of the tongue
	* @param health - , the initial health of this Frog
	* @throws IllegalArgumentException - with a descriptive message if health is less than 1
	*/
  public Frog(float x, float y, int health) {
    super(x, y, IMG_PATH);
    if (health < 1) {
      throw new IllegalArgumentException("Health must be greater than 1");
    }
    this.health = health;
    isDragging = false;
    this.tongue = new Tongue(x, y);
  }

  /**
   * Draws the image of the Frog to the screen. If the Frog's tongue is active: (1)draw the tongue
   * and (2) extend the tongue by moving it's x-coordinate to the Frog's x-coordinate and up 2
   * pixels.
   * 
   */
  @Override
public void draw() {
  if (processing != null) {
    // Draw the frog image
    processing.image(image, getX(), getY());

    if (tongue.isActive()) {
      // Calculate the endpoint of the tongue starting from the top of the Frog
      float tongueEndX = this.getX();
      float tongueEndY = this.getY() - this.image.height / 2 - 2; // Adjust the offset as needed

      // Update the tongue's endpoint
      this.tongue.updateEndPoint(tongueEndX, tongueEndY);

      // Draw the tongue
      this.tongue.draw();
    }
    super.draw();
  }
}


  /**
   * Moves the Frog by dragging it by the mouse, if it should be dragging. (See write-up for more
   * details on implementing the dragging functionality.) The starting point of the tongue and the
   * Hitbox need to move along with the Frog. If the Frog's tongue is NOT active, move the tongue's
   * endpoint along with the Frog as well. The Frog only moves if it should move.
   */
  public void move() {
    if (shouldMove()) {
      float moveX = processing.mouseX - oldMouseX;
      float moveY = processing.mouseY - oldMouseY;

      this.setX(oldMouseX + moveX);
      this.setY(oldMouseY + moveY);

      // Update the Hitbox and tongue's Hitbox
      getHitbox().setPosition(this.getX(), this.getY());
      tongue.getHitbox().setPosition(this.getX(), this.getY());
      tongue.updateStartPoint(this.getX(), this.getY());
      if (!tongue.isActive()) {
          // Update the tongue's endpoint when it's not active
          tongue.updateEndPoint(this.getX(), this.getY() + 2); // Adjust the offset as needed
          
      }
    }

    // Update the previous mouse coordinates
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }


  /**
   * Determines whether or not this Frog has run into a Bug.
   * 
   * @param b - the Bug to check that if it collides with the Frog
   * @return true if the Bug's Hitbox and Frog's Hitbox overlap, false otherwise
   */
  public boolean isHitBy(Bug b) {
    try {
      if (b.getHitbox().doesCollide(this.getTongueHitbox())) {
        return true;
      }
    } catch (IllegalStateException e){
      return false;
    }
    return false;
  }

  /**
   * Gets the Hitbox for this Frog's tongue.
   * 
   * @return this Frog's tongue's hitbox
   * @throws IllegalStateException - if the tongue is currently inactive
   */
  public Hitbox getTongueHitbox() {
    if (!(tongue.isActive())) {
      throw new IllegalStateException("Frog tongue is not active.");
    }
    return tongue.getHitbox();
  }

  /**
   * Gets the current health of the Frog
   * 
   * @return the current health of this Frog
   */
  public int getHealth() {
    return health;
  }


  /**
   * Determines if this frog is dead.
   * 
   * @return true if this Frog's health is 0 or lower, false otherwise
   */
  public boolean isDead() {
    return (health <= 0);
  }


  /**
   * Determines if the mouse is over the Frog's image
   * 
   * @return true, if the mouse is inside the Frog's bounding box of the image, false otherwise
   */
  public boolean isMouseOver() {
    float halfWidth = image.width / 2;
    float halfHeight = image.height / 2;
    
    // Check if the mouse coordinates are within the image's bounding box.
    if (processing.mouseX >= this.getX() - halfWidth &&
        processing.mouseX <= this.getX() + halfWidth &&
        processing.mouseY >= this.getY() - halfHeight &&
        processing.mouseY <= this.getY() + halfHeight) {
//        System.out.println("true");
        return true;
    }
//    System.out.println("false");
    return false;
  }

  /**
   * Decreases the health of this Frog by 1.
   */
  public void loseHealth() {
    this.health--;
  }

  /**
   * Changes this Frog so it is now being dragged. This method should only be called externally when
   * the mouse is over this frog and has been clicked.
   */
  public void mousePressed() {
    if (isMouseOver()) {
      isDragging = true;
    } else {
      isDragging = false;
    }
//    System.out.println("Mouse Pressed: " + isDragging);
//    System.out.println("isMouseOVer " + isMouseOver());
  }

  /**
   * Changes this Frog so it is no longer being dragged. This method should only be called
   * externally when the mouse has been released.
   */
  public void mouseReleased() {
    isDragging = false;
  }

  /**
   * Reports if the Frog needs to move on the screen.
   * 
   * @return true if the Frog is being dragged, false otherwise
   */
  public boolean shouldMove() {
    return (isDragging);
  }

  /**
   * Starts an attack by resetting the tongue to it's default state and activating the tongue.
   */
  public void startAttack() {
    tongue.reset();
    tongue.activate();
  }

  /**
   * Stops the attack by deactivating the tongue.
   */
  public void stopAttack() {
    tongue.deactivate();
  }

  /**
   * Reports if this Frog's tongue has hit the top of the screen.
   * 
   * @return true if the tongue has hit the top of the screen, false otherwise
   */
  public boolean tongueHitBoundary() {
    return tongue.hitScreenBoundary();
  }

}
