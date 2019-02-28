package model;

import java.awt.*;
import java.util.ArrayList;

public interface Shape {

  void execute(ArrayList<Action> actions, int startTick, int endTick);

  void setCoords(Location location);

  void setWidth(int width);

  void setHeight(int height);

  void setColor(Color color);

  StringBuilder getTrackedState();

  Location getCoords();

  Color getColor();

  int getWidth();

  int getHeight();
}
