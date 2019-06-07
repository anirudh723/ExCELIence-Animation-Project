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
   * @param name the name of the Ellipse.
   * @param dimension the dimension of the Ellipse.
   * @param color the color of the Ellipse.
   * @param position the position of the Ellipse.
   * @throws IllegalArgumentException if the width or height of this Ellipse is negative.
   * @throws IllegalArgumentException if the position coordinates of this Ellipse are negative.
   */
  public MyEllipse(String name, Dimension dimension, Color color, Point2D position) {
    super(name, dimension, color, position);
  }

  @Override
  public String getType() {
    return "ellipse";
  }
}
