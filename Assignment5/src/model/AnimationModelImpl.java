package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class AnimationModelImpl implements AnimationModel {
  private final int canvasHeight;
  private final int canvasWidth;
  private ArrayList<AbstractShape> shapes;
  private double tickSpeed;
  private Timer timer;
  private StringBuilder trackedState;

  public AnimationModelImpl(int canvasHeight,
                            int canvasWidth,
                            ArrayList<AbstractShape> shapes,
                            double tickSpeed) {

    if (canvasHeight < 1 || canvasWidth < 1) {
      throw new IllegalArgumentException("Canvas height and width must be greater than 0");
    }

    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
    this.shapes = shapes;

    if (tickSpeed <= 0) {
      throw new IllegalArgumentException("Tick Speed must be greater than 0");
    }

    this.tickSpeed = tickSpeed;
    this.timer = new Timer();
  }

  @Override
  public AnimationModel getAnimState(int tick) {
    return this;
  }

  @Override
  public void placeImage(AbstractShape s, int x, int y) {

  }

  @Override
  public String getAnimState() {
    return null;
  }
}
