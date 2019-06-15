package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 * Represents an Ellipse, which is a specific type of Shape.
 */
public class MyEllipse extends AbstractShape {

  /**
   * Constructs a MyEllipse.
   *
   * @param dimension the dimension of the Ellipse.
   * @param color the color of the Ellipse.
   * @param position the position of the Ellipse.
   * @throws IllegalArgumentException if the width or height of this Ellipse is negative.
   * @throws IllegalArgumentException if the position coordinates of this Ellipse are negative.
   */
  public MyEllipse(Dimension dimension, Color color, Point2D position) {
    super(dimension, color, position);
  }

  /**
   * Convenience constructor, sets the ellipse to some default values. Used for the Builder inside
   * the model.
   */
  public MyEllipse() {
    super(new Dimension(100, 100), new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Override
  public String getType() {
    return "ellipse";
  }
}
