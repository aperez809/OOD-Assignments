package model;

import java.util.HashMap;


/**
 * Represents that model for creating images and moving them
 * around a canvas
 */
public interface AnimationModel {

  //place a image of Shape s on the canvas at a given spot
  void placeImage(AbstractShape s, int x, int y) throws IllegalArgumentException;

  //returns a textual representation of the different attributes of the animation's
  //current state
  String getAnimState();

  AnimationModel getAnimState(int tick);
}
