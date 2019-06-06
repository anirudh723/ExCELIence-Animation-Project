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

  @Before
  public void setUp() {
    rectangle = new MyRectangle("R", new Dimension(20, 20),
            new Color(0, 0, 0), new Point(100, 100));
    ellipse = new MyEllipse("C", new Dimension(20, 20),
            new Color(0, 0, 0), new Point(100, 100));
  }

  @Test
  public void getNameRectangle() {
    assertEquals("R", rectangle.getName());
  }

  @Test
  public void getNameEllipse() {
    assertEquals("C", ellipse.getName());
  }

  @Test
  public void getTypeRectangle() {
    assertEquals("rectangle", rectangle.getType());
  }

  @Test
  public void getTypeEllipse() {
    assertEquals("ellipse", ellipse.getType());
  }
}
