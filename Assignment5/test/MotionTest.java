import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionalities regarding Motions.
 */
public class MotionTest {

  private Motion motion1;
  private Motion motion2;
  private Motion motion3;
  private Motion motion4;
  private Motion motion5;
  private Motion motion6;
  private Motion motion7;

  @Before
  public void setUp() {
    motion1 = new Motion(1, new Point(200, 200),
            new Dimension(50, 100), new Color(255, 0, 0));
    motion2 = new Motion(10, new Point(200, 200),
            new Dimension(50, 100), new Color(255, 0, 0));
    motion3 = new Motion(50, new Point(300, 300),
            new Dimension(50, 100), new Color(255, 0, 0));
    motion4 = new Motion(51, new Point(300, 300),
            new Dimension(50, 100), new Color(255, 0, 0));
    motion5 = new Motion(70, new Point(300, 300),
            new Dimension(25, 100), new Color(255, 0, 0));
    motion6 = new Motion(100, new Point(200, 200),
            new Dimension(25, 100), new Color(255, 0, 0));
    motion7 = new Motion(10, new Point(200,200),
        new Dimension(25,100), new Color(255, 50, 50));
  }

  @Test
  public void getTickMotion1() {
    assertEquals(1, motion1.getTick());
  }

  @Test
  public void getTickMotion6() {
    assertEquals(100, motion6.getTick());
  }

  @Test
  public void getPositionMotion2() {
    assertEquals((int) 200, (int) motion2.getPosition().getX());
    assertEquals((int) 200, (int) motion2.getPosition().getY());
  }

  @Test
  public void getPositionMotion3() {
    assertEquals((int) 300, (int) motion3.getPosition().getX());
    assertEquals((int) 300, (int) motion3.getPosition().getY());
  }

  @Test
  public void getDimensionMotion4() {
    assertEquals(new Dimension(50, 100), motion4.getDimension());
  }

  @Test
  public void getDimensionMotion5() {
    assertEquals(new Dimension(25, 100), motion5.getDimension());
  }

  @Test
  public void getColor() {
    assertEquals(new Color(255, 50,50), motion7.getColor());
  }

  @Test
  public void writeMotion1() {
    assertEquals("1 200 200 50 100 255 0 0", motion1.writeMotion());
  }

  @Test
  public void writeMotion6() {
    assertEquals("100 200 200 25 100 255 0 0", motion6.writeMotion());
  }


  /**
   * Tests constructing a motion with a negative tick. Expect IllegalArgumentException.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructMotionNegativeTick() {
    motion1 = new Motion(-10,new Point(200,200),
            new Dimension(25,100), Color.RED);
  }

  /**
   * Tests constructing a motion with a negative Point. Expect IllegalArgumentException.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructMotionNegativePoint() {
    motion2 = new Motion(10,new Point(-200,-200),
            new Dimension(25,100), Color.RED);
  }

  /**
   * Tests constructing a motion with a negative Dimension. Expect IllegalArgumentException.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructMotionNegativeDimension() {
    motion3 = new Motion(10,new Point(200,200),
            new Dimension(-25,-100), Color.RED);
  }

  /**
   * Tests constructing a motion with an invalid Color. Expect IllegalArgumentException.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructMotionInvalidColor() {
    motion4 = new Motion(10,new Point(200,200),
            new Dimension(25,100), new Color(-50, 50, 50));
  }
}
