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

public class StrongBug extends Bug implements Moveable{
	
	private int currentHealth;
	private final int MAX_HEALTH;
	private static final int POINTS = 500;
	
	/**
	* Creates a new StrongBug object at full health using the provided parameters.
	*
	* @param x - x position of the StrongBug
	* @param y - y position of the StrongBug
	* @param health - Max health of the StrongBug
	* @throws IllegalArgumentExeption with decriptive message if health below 1
	*/
	public StrongBug(float x, float y, int health){
		super(x,y, POINTS);
		if (health <= 0){
			throw new IllegalArgumentException("Health must be above 0");
		}
		MAX_HEALTH = health;
		currentHealth = MAX_HEALTH;
	}
	
	/**
	* Moves this StrongBug 3 units to the right, wrapping around the screen when the center hits the
	* edge of the window. The Hitbox should move with the StrongBug. The x,y-coordinate of only 
	* changes if the StrongBug sshould move.
	*/
	public void move(){
		if (shouldMove()){
			float xPos = this.getX() + 3;
			
			if (xPos >= processing.width){
				xPos = 0;
			}
			moveHitbox(xPos, this.getY());
		}
	}
	
	/**
	* Reports if this StrongBug is dead.
	* 
	* @return true - if currentHealth is 0 or less, false if otherwise
	*/
	public boolean isDead(){
		return (currentHealth <= 0);
	}
	
	/** 
	* Decreases the health of this StrongBug by 1
	*/
	public void loseHealth(){
		currentHealth--;
	}
	
	/**
	* Reports if the StrongBug needs to move.
	*
	* @return true - if the bug is NOT at full health, false if otherwise.
	*/
	public boolean shouldMove(){
		return (currentHealth != MAX_HEALTH);
	}
	
	public boolean isEatenBy(Frog f){
//		boolean eaten = (f.getTongueHitbox().doesCollide(getHitbox()));
//		
//		float newWidth = (float) ( image.width * 0.75);
//		float newHeight = (float) (image.height * 0.75);
//		
//		getHitbox().changeDimensions(newWidth, newHeight);
//		
//		image.resize((int) newWidth, (int) newHeight);
//		
//		
//		return eaten;
      Hitbox tongueHitbox = null;
      try {
          tongueHitbox = f.getTongueHitbox();
      } catch (IllegalStateException e) {
          return false;
      }
      if (this.getHitbox().doesCollide(tongueHitbox)) {
          this.loseHealth();
          float width = this.image.width;
          float height = this.image.height;
          if (width > 1 || height > 1) {
              this.image.resize((int) (this.image.width * 0.75), (int) (this.image.height * 0.75));
              this.getHitbox().changeDimensions((int) (this.image.width * 0.75), (int) (this.image.height * 0.75));
          }
          return true;
      }
      return false;
	}
	
	
}
