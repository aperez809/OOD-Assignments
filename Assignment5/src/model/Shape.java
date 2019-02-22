package model;

public interface Shape {

  void changeDims(int newH, int newW);

  void documentChange();

  void changeColor(int r, int g, int b);

  void move(int toX, int toY);
}
