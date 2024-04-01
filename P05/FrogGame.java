//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Froggie Feeding Frenzie
// Course: CS 300 Fall 2023
//
// Author: Madison Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nathan Han
// Partner Email: nhhan@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * This class controls the main FrogGame. Handles setup of game and also printing of game. Also
 * includes implementation for adding bugs and player control.
 */
public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; // array list of the gameActors in the game
  private int score; // the player's current score
  private PImage backgroundImg; // the image to use for the background
  private boolean isGameOver; // keeps track if the game is over, is true if the game is over
  private Random randGen; // random number generator
  private static final int BUG_COUNT = 5; // how many bugs should be on the screen at all times

  public static void main(String[] args) {
    PApplet.main("FrogGame");
  }

  /**
   * Sets the size of the window.
   */
  @Override
  public void settings() {
    // TODO #1 call PApplet's size() method giving it 800 as the width and 600 as the height
    size(800, 600);
  }

  /**
   * Sets up the window and game. Initializes variables and sets processing.
   */
  @Override
  public void setup() {
    // TODO #2 add PApplet method calls from write-up
    this.getSurface().setTitle("Froggie Feeding Frenzie"); // sets title of window
    this.imageMode(PApplet.CENTER); // images when drawn will use x,y as their center
    this.rectMode(PApplet.CENTER); // rectangles when drawn will use x,y as their center
    this.focused = true; // window is "active" upon start up
    this.textAlign(PApplet.CENTER); // text written to screen will have center alignment
    this.textSize(30); // text is 30 pt



    // TODO #3 initialize randGen
    randGen = new Random();

    // TODO #4 load in and save the backgroundImg
    backgroundImg = loadImage("images" + File.separator + "background.jpg");


    // TODO #5 initialize gameActors to an empty ArrayList
    gameActors = new ArrayList<GameActor>();

    // TODO #7 set the processing variable for all classes where necessary (update this as needed)
    Hitbox.setProcessing(this);
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);

    try {
      GameActor test = new GameActor(400, 300, "bug.png");
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }

    // Checkpoint #2
    float randX = randGen.nextFloat() * width;
    float randY = randGen.nextFloat() * height;
    // gameActors.add(new GameActor(randX, randY, "frog.png"));

    // checkpoint # 3 - add bug
    randX = randGen.nextFloat() * width;
    randY = randGen.nextFloat() * height;
    gameActors.add(new Bug(randX, randY, 0));

    // checkpoint #4 - add BouncingBug

    randX = randGen.nextFloat() * width;
    randY = randGen.nextFloat() * height;
    gameActors.add(new BouncingBug(randX, randY, 3, 3));
    // TODO add hitbox for bouncing bug?

    // checkpoint 5:
    randX = randGen.nextFloat() * width;
    randY = randGen.nextFloat() * height;
    int[] color = {0, 153, 204};
    gameActors.add(new CirclingBug(randX, randY, 5, color));

    // checkpoint 7
    randX = randGen.nextFloat() * width;
    randY = randGen.nextFloat() * height;
    gameActors.add(new Frog(randX, randY, 1));
    // System.out.println(randX + ", " + randY);

    // TODO #16 call initGame()
    initGame();

  }

  /**
   * Draws all GameActors in and determines when the game is over.
   */
  @Override
  public void draw() {

    // TODO #6 call PApplet's image() method to draw the backgroundImg at the center of the screen
    image(backgroundImg, 400, 300);

    // Hitbox tester = new Hitbox(width, height, 50, 50);
    // tester.visualizeHitbox();

    // TODO #8 draw every GameActor in the ArrayList to the screen
    if (isGameOver) {
      this.text("GAME OVER", this.width / 2, this.height / 2);
    } else {
      for (GameActor actor : this.gameActors) {
        actor.draw();
        if (actor instanceof Moveable) {
          ((Moveable) actor).move();
        }
        if (actor instanceof Frog) {
          this.text("Health: " + ((Frog) actor).getHealth(), 80, 40);
          this.text("Score: " + score, 240, 40);
        }
      }

    }
    // TODO #9 update the code you wrote for TODO #8 to have all Movable GameActors move

    // TODO #19 run all the game logic checks
    this.runGameLogicChecks();

    // TODO #14 print "Health: " + frog's health at (80,40) and "Score: " + score at (240,40)

    // to the screen
    // (note in the code logic this step to be performed takes place AFTER TODO #19)
    text("Health: " + ((Frog) gameActors.get(getFrog())).getHealth(), 80, 40);

    // TODO #20 update the code you wrote above to do the following:
    // (1) if the game is over, do NONE of the above steps. Instead print "GAME OVER" to
    // the center of the screen.
    // (2) otherwise do the above steps
  }

  /**
   * Creates a bug of a random type and adds it to the list of GameActors.
   */
  private void addNewBug() {

    // TODO #10 implement this method, see below for more details.
    // This creates a bug of a random type and adds it to the list of GameActors.

    // (1) generate a random number in the range [0,4)
    // (2) generate a random x value in the range [0, windowWidth) for the bug
    // (3) generate a random y value in the range [0, windowHeight - 150) for the bug
    // (4) depending on the value generated in step (1)

    // create the following bug and add it to the arraylist
    // 0 -> a new regular Bug at (x,y) that is worth 25 points
    // 1 -> a new BouncingBug at (x,y) that has a dx of 2 and a dy of 5
    // 2 -> a new CirclingBug at (x,y) with a radius of 25 and a random set of RGB values [0,256)
    // 3 -> a new StrongBug at (x,y) with an initial health of 3
    int randBugVariant = (int) (randGen.nextFloat() * 4);
    float randX = randGen.nextFloat() * width;
    float randY = randGen.nextFloat() * height - 150;
    if (randBugVariant == 0) {
      gameActors.add(new Bug(randX, randY, 25));
    } else if (randBugVariant == 1) {
      gameActors.add(new BouncingBug(randX, randY, 2, 5));
    } else if (randBugVariant == 2) {
      int[] color = {randGen.nextInt() * 256, randGen.nextInt() * 256, randGen.nextInt() * 256};
      gameActors.add(new CirclingBug(randX, randY, 25, color));
    } else if (randBugVariant == 3) {
      gameActors.add(new StrongBug(randX, randY, 3));
    }


  }

  /**
   * Checks if the mouse is over Frog and calls mousePressed
   */
  @Override
  public void mousePressed() {
    // TODO #11 if mouse is over the Frog call its mousePressed method
    for (GameActor actor : gameActors) {
      if (actor instanceof Frog) {
        if (((Frog) actor).isMouseOver()) {
          ((Frog) actor).mousePressed();
        }
      }
    }
  }

  /**
   * Calls the mouseReleased method on all GameActors
   */
  @Override
  public void mouseReleased() {
    // TODO #12 call the Frog's mouseReleased method
    for (GameActor actor : gameActors) {
      if (actor instanceof Frog) {
        ((Frog) actor).mouseReleased();

      }
    }
  }

  /**
   * Checks for keypresses and calls the appropriate method.
   */
  @Override
  public void keyPressed() {
    // TODO #13 if the key is a space, have the frog starts attacking
    if (key == 32) {
      // System.out.println("Space Pressed");
      ((Frog) gameActors.get(getFrog())).startAttack();
      // System.out.println("Attack");
    }

    // TODO #17 if the key is a lowercase 'r', reset the game to its initial state
    if (key == 'r') {
      // System.out.println("R Pressed");
      initGame();
    }



  }

  /**
   * Sets the game to its initial state before playing.
   */
  public void initGame() {
    // TODO #15 implement this method, see below for more details. This methods sets the game to
    // its initial state before playing.
    this.score = 0;
    this.isGameOver = false;
    gameActors.clear();
    gameActors = new ArrayList<GameActor>();
    gameActors.add(new Frog(backgroundImg.width / 2, backgroundImg.height - 100, 100));
    for (int i = 0; i < BUG_COUNT; i++) {
      addNewBug();
    }
    // (1) set the score to 0
    // (2) make the game NOT over
    // (3) clear out the arraylist
    // (4) create and add Frog with 100 health to the list. Its x value should be half the
    // width of the screen. Its y value should be the height of the screen minus 100
    // (5) add new bugs (of random varieties) to the list UP TO the BUG_COUNT
  }

  /**
   * This method runs all necessary game logic checks
   */
  private void runGameLogicChecks() {
    // TODO #18 implement this method, see below for details. This method runs all neccessary
    // game logic checks. Feel free to decompose it into smaller helper methods.



    // (1) if the Frog's tongue hits the edge of the screen, then it stops attacking
    if (((Frog) gameActors.get(getFrog())).tongueHitBoundary() == true) {
      ((Frog) gameActors.get(getFrog())).stopAttack();
    }

    // (2) Check every bug to see if it has been hit by the Frog.
    for (int i = 0; i < this.gameActors.size(); i++) {
      GameActor actor = this.gameActors.get(i);
      if (actor instanceof Bug) {
        Bug bug = (Bug) actor;
        for (int j = 0; j < this.gameActors.size(); j++) {
          GameActor actor2 = this.gameActors.get(j);
          if (actor2 instanceof Frog) {
            Frog frog = (Frog) actor2;
            if (bug.isEatenBy(frog)) {
              if (!(bug instanceof StrongBug)) {
                (frog).stopAttack();
                score += (((Bug) (gameActors.get(i))).getPoints());
                gameActors.remove(i);
                this.addNewBug();
              }
              // (b) of a StrongBug is hit do the following
              // (b1) stop the frog's attack
              // (b2) the StrongBug takes damage and loses health
              // (b3) if the StrongBug is dead do steps a1 - a4
              else {
                frog.stopAttack();
                ((StrongBug) bug).loseHealth();
                if (((StrongBug) bug).isDead() == true) {
                  score += (bug).getPoints();
                  gameActors.remove(i);
                  this.addNewBug();
                }
              }
            }
          }
        }

        if (actor instanceof Frog) {
          Frog frog = (Frog) actor;
          for (GameActor actor2 : this.gameActors) {
            if (actor2 instanceof Bug) {
              Bug bug2 = (Bug) actor2;
              if (frog.isHitBy(bug2)) {
                frog.loseHealth();
                if (frog.isDead()) {
                  this.isGameOver = true;
                }
              }
            }
          }
        }
        // (3) check if the frog hits any of the bugs
        // (a) if it hit any of the bugs it takes damage and loses health
        // NOTE: it can be hit my multiple bugs at the same time loses health for each.
        // Ex. is hit by 2 different bugs simultanously then should take 2 damage.
        // (b) if the frog is dead then update the game so it is over
      }
    }
  }

  /**
   * Helper method that retrieves the Frog in gameActors
   * 
   * @return Frog in gameActors
   */
  private int getFrog() {
    for (GameActor actor : gameActors) {
      if (actor instanceof Frog) {
        return gameActors.indexOf(actor);
      }

    }
    return -1;
  }
}
