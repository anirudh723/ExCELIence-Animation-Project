package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 * Represents the general functions that pertain to a motion. We did not have an interface for a
 * motion before, because there is only one type of motion, and the methods are mostly getters.
 * However, we wanted to make sure we weren't giving concrete classes in our model so we made one.
 */
public interface IMotion {

  /**
   * Gets the tick of this Motion.
   *
   * @return the tick of this Motion.
   */
  int getTick();

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
   * Returns a new Color with the red, green, and blue components of this color.
   *
   * @return the new Color.
   */
  Color getColor();


  /**
   * Builds a string of this Motion.
   *
   * @return this resulting Motion in one String.
   */
  String writeMotion();
}
