package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents a general shape, which consists of the same characteristics of all shapes, such as
 * color, position, etc.
 */
public abstract class AbstractShape implements IShape {

  protected String type;
  protected Dimension dimension;
  protected Color color;
  protected Point2D position;

  /**
   * Constructs an abstract Shape.
   *
   * @param dimension the width and height of the Shape.
   * @param color the color of the Shape.
   * @param position the position of the Shape.
   * @throws IllegalArgumentException if Dimension is null.
   * @throws IllegalArgumentException if the width or height of a shape is negative or 0.
   * @throws IllegalArgumentException if Position is null.
   */
  protected AbstractShape(Dimension dimension, Color color, Point2D position) {
    this.type = getType();
    if (dimension == null) {
      throw new IllegalArgumentException("Dimension is null.");
    }
    if (dimension.getWidth() <= 0 || dimension.getHeight() <= 0) {
      throw new IllegalArgumentException("Width or Height of a shape cannot be negative or 0.");
    }
    this.dimension = dimension;
    this.color = color;
    if (position == null) {
      throw new IllegalArgumentException("Position is null.");
    }
    this.position = position;
  }

  @Override
  public abstract String getType();

  @Override
  public Point2D getPosition() {
    return new Point2D.Double(this.position.getX(), this.position.getY());
  }

  @Override
  public Dimension getDimension() {
    return new Dimension((int) this.dimension.getWidth(), (int) this.dimension.getHeight());
  }

  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public void assignInitialMotion(Dimension d, Point2D p, Color c) {
    this.position = new Point2D.Double(p.getX(), p.getY());
    this.dimension = new Dimension((int) d.getWidth(), (int) d.getHeight());
    this.color = new Color(c.getRed(), c.getGreen(), c.getBlue());
  }
}
