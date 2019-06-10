package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;


/**
 * Represents a Rectangle, which is a specific type of Shape.
 */
public class MyRectangle extends AbstractShape {
  /**
   * Constructs a MyRectangle.
   *
   * @param name      the name of the Rectangle.
   * @param dimension the dimension of the Rectangle.
   * @param color     the color of the Rectangle.
   * @param position  the position of the Rectangle.
   * @throws IllegalArgumentException if the width or height of this Rectangle is negative.
   * @throws IllegalArgumentException if the position coordinates of this Rectangle are negative.
   */
  public MyRectangle(String name, Dimension dimension, Color color, Point2D position) {
    super(name, dimension, color, position);
  }

  @Override
  public String getType() {
    return "rectangle";
  }
}
