package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents the general functions regarding Shapes, including getters and a method that assigns a
 * shape's inital state to its first motion.
 */
public interface IShape {

  /**
   * Return the type of the shape.
   *
   * @return the String type of the shape.
   */
  String getType();

  /**
   * Returns a new Position with the x and y coordinates of this position.
   *
   * @return the new Position.
   */
  Point2D getPosition();

  /**
   * Returns a new Dimension with the width and height elements of this dimension.
   *
   * @return the new Dimension.
   */
  Dimension getDimension();


  /**
   * Returns a new Color with the rgb elements of this Color.
   *
   * @return the new Dimension.
   */
  Color getColor();

  /**
   * Assigns the shape to its initial motion.
   */
  void assignInitialMotion(Dimension d, Point2D p, Color c);
}
