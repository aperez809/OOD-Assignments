package model;

import java.awt.*;

public class AbstractShape implements Shape {
  private int height;
  private int width;
  private Location coords;
  private Color color;

  public AbstractShape(int height, int width, Location coords, Color color) {
    this.height = height;
    this.width = width;
    this.coords = coords;
    this.color = color;
  }

  @Override
  public void changeDims(int newH, int newW) {

  }

  @Override
  public void documentChange() {

  }

  @Override
  public void changeColor(int r, int g, int b) {

  }

  @Override
  public void move(int toX, int toY) {

  }
}




