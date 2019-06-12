package cs3500.animator.model;

import java.awt.*;
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

  String outputMotions(String name);

  /**
   * Returns the a deep copy of the list of motions.
   *
   * @return the list of motions for this Shape.
   */
  List<IMotion> getMotions();
}
