package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 * Represents a Motion, which is represented by a tick, position, dimension, and color. Representing
 * it by just one of these fields makes it easier to handle instead of having a start and end.
 */
public class Motion implements IMotion {

  private int tick;
  private Point2D position;
  private Dimension dimension;
  private Color color;

  /**
   * Constructs a Motion.
   *
   * @param tick      the starting tick of the Motion.
   * @param position  the starting position of the Motion.
   * @param dimension the starting width and height of the Motion.
   * @param color     the starting color of the Motion.
   * @throws IllegalArgumentException if start tick is negative.
   * @throws IllegalArgumentException if start width or height is negative or 0.
   */
  public Motion(int tick, Point2D position, Dimension dimension, Color color) {
    if (tick < 0) {
      throw new IllegalArgumentException("Start tick cannot be negative.");
    }
    this.tick = tick;
    this.position = position;
    if (dimension.getWidth() <= 0 || dimension.getHeight() <= 0) {
      throw new IllegalArgumentException("Start width cannot be negative or 0.");
    }
    this.dimension = dimension;
    this.color = color;
  }

  /**
   * Gets the tick of this Motion.
   *
   * @return the tick of this Motion.
   */
  @Override
  public int getTick() {
    return this.tick;
  }

  /**
   * Returns a new Position with the x and y coordinates of this position.
   *
   * @return the new Position.
   */
  @Override
  public Point2D getPosition() {
    return new Point2D.Double(this.position.getX(), this.position.getY());
  }

  /**
   * Returns a new Dimension with the width and height elements of this dimension.
   *
   * @return the new Dimension.
   */
  @Override
  public Dimension getDimension() {
    return new Dimension((int) dimension.getWidth(), (int) dimension.getHeight());
  }

  /**
   * Returns a new Color with the red, green, and blue components of this color.
   *
   * @return the new Color.
   */
  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  /**
   * Builds a string of this Motion.
   *
   * @return this resulting Motion in one String.
   */
  @Override
  public String writeMotion() {
    StringBuilder str = new StringBuilder();
    str.append(tick + " "
            + (int) position.getX() + " "
            + (int) position.getY() + " "
            + (int) dimension.getWidth() + " "
            + (int) dimension.getHeight() + " "
            + color.getRed() + " "
            + color.getGreen() + " "
            + color.getBlue());

    return str.toString();
  }
}
