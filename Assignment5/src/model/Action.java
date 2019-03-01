package model;

import javafx.util.Pair;

public interface Action {

  //apply Action function to given shape
  void apply(Shape s);

  int getStartTick();

  int getEndTick();
}
