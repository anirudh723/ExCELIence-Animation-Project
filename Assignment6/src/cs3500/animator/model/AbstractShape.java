package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 * Represents a general shape.
 */
public abstract class AbstractShape implements IShape {

  protected String type; // "rectangle"
  protected Dimension dimension;
  protected Color color;
  protected Point2D position;

  /**
   * Constructs an abstract Shape.
   *
   * @param dimension the width and height of the Shape.
   * @param color     the color of the Shape.
   * @param position  the position of the Shape.
   * @throws IllegalArgumentException if the width or height of a shape is negative.
   * @throws IllegalArgumentException if the coordinates of the Position are negative.
   */
  protected AbstractShape(Dimension dimension, Color color, Point2D position) {
    this.type = getType();
    if (dimension.getWidth() < 0 || dimension.getHeight() < 0) {
      throw new IllegalArgumentException("Width or Height of a shape cannot be negative.");
    }
    this.dimension = dimension;
    this.color = color;
    if (position.getX() < 0 || position.getY() < 0) {
      throw new IllegalArgumentException("coordinates of a position cannot be negative.");
    }
    this.position = position;
  }

  @Override
  public abstract String getType();

}