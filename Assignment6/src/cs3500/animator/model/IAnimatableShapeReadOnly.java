package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Represents a read only version
 */
public interface IAnimatableShapeReadOnly {

  /**
   * Return the type of the shape.
   *
   * @return the String type of the shape.
   */
  String getType();

  /**
   * Return the color of the shape.
   *
   * @return the String type of the shape.
   */
  Color getColor();

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
   * Returns the String output of all the motions of the read only animatable shape.
   *
   * @param name the name of the shape.
   * @return the String output of the shape's motions.
   */
  String outputMotions(String name);

  /**
   * Returns the a deep copy of the list of motions.
   *
   * @return the list of motions for this Shape.
   */
  List<IMotion> getMotions();
}
