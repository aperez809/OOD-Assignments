package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * Represents that cs3500.animator.model for creating images and moving them
 * around a canvas.
 */
public interface AnimationModel {

  //place a image of Shape s on the canvas at a given spot
  void addShape(Shape s);

  void addAction(String name, IAction a);

  //returns a textual representation of the different attributes of the animation's
  //current state
  String getAnimState();

  ArrayList<IAction> getShapeActions(Shape s);

  ArrayList<Shape> getShapes();

  TreeMap<Shape, ArrayList<IAction>> getScript();

  void startAnim();

  void setMaxX(int x);

  void setMaxY(int y);

  void setWidth(int width);

  void setHeight(int height);

  String getNextShapeName(String shapeType);

  int getWidth();

  int getHeight();

  int getMaxX();

  int getMaxY();
}

