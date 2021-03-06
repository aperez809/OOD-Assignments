package model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Represents that model for creating images and moving them
 * around a canvas.
 */
public interface AnimationModel {

  //place a image of Shape s on the canvas at a given spot
  void addShape(Shape s);

  void addAction(Shape s, Action a);

  //returns a textual representation of the different attributes of the animation's
  //current state
  String getAnimState();

  ArrayList<Action> getShapeActions(Shape s);

  ArrayList<Shape> getShapes();

  HashMap<Shape, ArrayList<Action>> getScript();

  void startAnim();
}

