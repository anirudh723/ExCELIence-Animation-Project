package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

public interface IMotion {

  /**
   * Gets the tick of this Motion.
   *
   * @return the tick of this Motion.
   */
  public int getTick();

  /**
   * Returns a new Position with the x and y coordinates of this position.
   *
   * @return the new Position.
   */
  public Point2D getPosition();

  /**
   * Returns a new Dimension with the width and height elements of this dimension.
   *
   * @return the new Dimension.
   */
  public Dimension getDimension();

  /**
   * Returns a new Color with the red, green, and blue components of this color.
   *
   * @return the new Color.
   */
  public Color getColor();


  /**
   * Builds a string of this Motion.
   *
   * @return this resulting Motion in one String.
   */
  public String writeMotion();

}
