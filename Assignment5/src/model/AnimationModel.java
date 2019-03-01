package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Represents that model for creating images and moving them
 * around a canvas
 */
public interface AnimationModel {

  //place a image of Shape s on the canvas at a given spot
  void placeImage(Shape s);

  void addAction(Shape s, Action a);

  //returns a textual representation of the different attributes of the animation's
  //current state
  String getAnimState();

  List<Action> getShapeActions(Shape s);

  HashMap<Shape, List<Action>> getAllActions();

  ArrayList<Shape> getShapes();
}

