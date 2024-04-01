//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: There is an Imposter on this Space Station Game
// Course: CS 300 Fall 2023
//
// Author: Madison Lin
// Email: mblin@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Ruiwen - helped with setting up .jar file
// Online Sources: https://www.geeksforgeeks.org/find-two-rectangles-overlap/ - to help with overlap
// method
// https://www.geeksforgeeks.org/how-to-create-array-of-objects-in-java/ - syntax
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class sets up a minigame with Amongii
 */

import java.util.Random;
import java.io.File;
import processing.core.PImage;

public class SpaceStation {
  private static PImage background;
  private static Amogus[] players;
  private static final int NUM_PLAYERS = 8;
  private static int impostorIndex;

  /**
   * Runs the application
   * 
   * @param args array of strings
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Sets up background and Amogus objects
   */
  public static void setup() {
    background = Utility.loadImage("images" + File.separator + "background.jpeg");
    // make the amonguys
    players = new Amogus[NUM_PLAYERS];
    players[0] = new Amogus(Utility.randGen.nextInt(3) + 1);
    impostorIndex = Utility.randGen.nextInt(NUM_PLAYERS);
    // System.out.println("Impostor index: " + impostorIndex);
  }

  /**
   * Loads in background and Amogus sprites, updating them as needed
   */
  public static void draw() {
    Utility.image(background, 600, 500);

    for (int i = 0; i < players.length; i++) {
      if (players[i] != null) {
        // check if the player is impostor
        if (i == impostorIndex) {
          // for all of the other crewmates, check if the imposter overlaps
          for (int j = 0; j < players.length; j++) {
            if (players[j] != null && i != j) {
              if (overlap(players[i], players[j])) {
                players[j].unalive();
              }
            }
          }
        }
        players[i].draw();
      }
    }
  }


  /**
   * Takes user key input and spawns in Amogus objects
   */
  public static void keyPressed() {
    if (Utility.key() == 'a') {
      for (int i = 0; i < players.length; i++) {
        if (players[i] == null) {
          boolean isImpostor;
          if (i == impostorIndex) {
            isImpostor = true;
          } else {
            isImpostor = false;
          }

          players[i] = new Amogus(Utility.randGen.nextInt(3) + 1,
              Utility.randGen.nextInt(Utility.width() + 1),
              Utility.randGen.nextInt(Utility.height() + 1), isImpostor);
          break;

        }
      }
    }
  }

  /**
   * Checks to see if the mouse is over an Amongii
   * 
   * @param player Amogus object that mouse would be over
   * @return true if mouse is over the Amogus object
   */
  public static boolean isMouseOver(Amogus player) {
    // return true when mouse is over any part of one of the Amogus images (not including edges)
    float playerX = player.getX();
    float playerY = player.getY();
    float mouseX = Utility.mouseX();
    float mouseY = Utility.mouseY();
    int width = player.image().width;
    int height = player.image().height;

    // if mouse X is between right and left edge and mouse Y is between top and bottom edge
    if (mouseX >= playerX - width / 2 && mouseX <= playerX + width / 2
        && mouseY >= playerY - height / 2 && mouseY <= playerY + height / 2) {
      return true;
    }
    return false;
  }

  /**
   * Checks if mouse is clicking on an Amongii to allow for dragging
   */
  public static void mousePressed() {
    // loop to check whether mouse is over any of the nonnull objects in players array
    for (int i = 0; i < players.length; i++) {
      if (players[i] != null) {
        if (isMouseOver(players[i])) {
          players[i].startDragging();
        }
      }
    }
  }

  /**
   * Checks to see if mouse is not clicking on an Amongii to stop dragging
   */
  public static void mouseReleased() {
    for (int i = 0; i < players.length; i++) {
      if (players[i] != null) {
        players[i].stopDragging();
      }
    }
  }

  /**
   * Checks to see if the impostor sprite is overlapping with other players
   * 
   * @param impostor Amogus object with the index of impostorIndex that will unalive other players
   * @param crewmate Amogus object
   * @return true if impostor is not overlapping with them
   */
  public static boolean overlap(Amogus impostor, Amogus crewmate) {
    // x & y coords for impostor top left corner
    float impostorLeftX = impostor.getX() - impostor.image().width / 2;
    float impostorLeftY = impostor.getY() - impostor.image().height / 2;

    // x & y coords for impostor bottom right corner
    float impostorBottomX = impostor.getX() + impostor.image().width / 2;
    float impostorBottomY = impostor.getY() + impostor.image().height / 2;

    // x & y coords for crewmate top left corner
    float crewmateLeftX = crewmate.getX() - crewmate.image().width / 2;
    float crewmateLeftY = crewmate.getY() - crewmate.image().height / 2;
    // x & y coords for crewmate bottom right corner
    float crewmateBottomX = crewmate.getX() + crewmate.image().width / 2;
    float crewmateBottomY = crewmate.getY() + crewmate.image().height / 2;

    // If one is on left side of the other
    if (impostorLeftX > crewmateBottomX || crewmateLeftX > impostorBottomX)
      return false;

    // if one is above the other
    if (impostorBottomY < crewmateLeftY || crewmateBottomY < impostorLeftY)
      return false;

    return true;
  }



}
