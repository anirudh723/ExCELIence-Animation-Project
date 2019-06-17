package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods regarding Abstract Shapes.
 */
public class AbstractShapeTest {

  private IShape rectangle;
  private IShape ellipse;

  /**
   * Initialize rectangle and ellipse IShapes.
   */
  @Before
  public void setUp() {
    rectangle = new MyRectangle(new Dimension(20, 20),
            new Color(0, 0, 0), new Point(100, 100));
    ellipse = new MyEllipse(new Dimension(20, 20),
            new Color(0, 0, 0), new Point(100, 100));
  }

  /**
   * Test getting a shape's type.
   */
  @Test
  public void getTypeRectangle() {
    assertEquals("rectangle", rectangle.getType());
  }

  /**
   * Test getting a shape's type.
   */
  @Test
  public void getTypeEllipse() {
    assertEquals("ellipse", ellipse.getType());
  }
}
