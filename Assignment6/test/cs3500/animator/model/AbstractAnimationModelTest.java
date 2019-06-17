package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * Represents the tests for an abstract Animation Model.
 */
public class AbstractAnimationModelTest {

  private AnimationModel model;

  private LinkedHashMap<String, IAnimatableShape> shapes;
  private ArrayList<IMotion> rectMotions = new ArrayList<>();
  private ArrayList<IMotion> ellipseMotions = new ArrayList<>();

  private IShape rectangle;
  private IShape ellipse;

  private IAnimatableShape animatableRect;

  private IMotion motion1;
  private IMotion motion2;
  private IMotion motion3;
  private IMotion motion4;
  private IMotion motion5;
  private IMotion motion6;
  private IMotion motion7;

  /**
   * resets the rectangle variables to certain data to use for specific tests.
   */
  void resetRectangle() {
    motion1 = new Motion(1, new Point(200, 200),
            new Dimension(50, 100), Color.red);
    motion2 = new Motion(10, new Point(200, 200),
            new Dimension(50, 100), Color.red);
    motion3 = new Motion(50, new Point(300, 300),
            new Dimension(50, 100), Color.red);
    motion4 = new Motion(51, new Point(300, 300),
            new Dimension(50, 100), Color.red);
    motion5 = new Motion(70, new Point(300, 300),
            new Dimension(25, 100), Color.red);
    motion6 = new Motion(100, new Point(200, 200),
            new Dimension(25, 100), Color.red);

    rectMotions.add(motion1);
    rectMotions.add(motion2);
    rectMotions.add(motion3);
    rectMotions.add(motion4);
    rectMotions.add(motion5);
    rectMotions.add(motion6);

    rectangle = new MyRectangle(new Dimension(200, 200),
            Color.RED, new Point(200, 200));
    animatableRect = new AnimatableShape(rectangle, rectMotions);

    shapes = new LinkedHashMap<>();
    model = AnimationModelImpl.builder().setShapes(shapes).build();
    shapes.put("R", this.animatableRect);


  }

  /**
   * resets the ellipse variables to certain data to use for specific tests.
   */
  void resetEllipse() {
    motion1 = new Motion(6, new Point(440, 70),
            new Dimension(120, 60), Color.BLUE);
    motion2 = new Motion(20, new Point(440, 70),
            new Dimension(120, 60), Color.BLUE);
    motion3 = new Motion(50, new Point(440, 250),
            new Dimension(120, 60), Color.BLUE);
    motion4 = new Motion(70, new Point(440, 370),
            new Dimension(120, 60), new Color(0, 170, 85));
    motion5 = new Motion(80, new Point(440, 370),
            new Dimension(120, 60), Color.GREEN);
    motion6 = new Motion(100, new Point(440, 370),
            new Dimension(120, 60), Color.GREEN);

    ellipseMotions.add(motion1);
    ellipseMotions.add(motion2);
    ellipseMotions.add(motion3);
    ellipseMotions.add(motion4);
    ellipseMotions.add(motion5);
    ellipseMotions.add(motion6);

    ellipse = new MyEllipse(new Dimension(120, 60),
            Color.BLUE, new Point(440, 70));
    IAnimatableShape animatableEllipse = new AnimatableShape(ellipse, ellipseMotions);

    shapes = new LinkedHashMap<>();
    shapes.put("E", animatableEllipse);

    model = AnimationModelImpl.builder().setShapes(this.shapes).build();
  }

  /**
   * Creates an animation model with no shapes.
   */
  @Test
  public void createEmptyAnimation() {
    shapes = new LinkedHashMap<>();
    model = AnimationModelImpl.builder().setShapes(shapes).build();
    assertEquals(shapes.size(), 0);
  }


  /**
   * Tests the adding of a shape.
   */
  @Test
  public void testAddShape() {
    ellipse = new MyEllipse(new Dimension(30, 30),
            new Color(255, 255, 0), new Point(350, 200));
    resetRectangle();
    assertEquals(1, shapes.size());
    model.addShape("L", "rect");
    assertEquals(2, shapes.size());
  }

  /**
   * Tests the removing of a shape.
   */
  @Test
  public void testRemoveShape() {
    resetRectangle();
    assertEquals(shapes.size(), 1);
    model.removeShape("R");
    assertEquals(shapes.size(), 0);
  }

  /**
   * Tests the adding of a motion at the front of a shape's list of motions.
   */
  @Test
  public void testAddMotionAtFront() {
    resetRectangle();
    motion7 = new Motion(101, new Point(200, 200),
            new Dimension(25, 100), Color.RED);
    assertEquals(shapes.get("R").getMotions().size(), 6);
    model.addMotion("R", motion7);
    assertEquals(shapes.get("R").getMotions().size(), 7);
    assertEquals(shapes.get("R").getMotions().get(6).getTick(), motion7.getTick());
  }

  /**
   * Tests the adding of a motion at the end of a shape's list of motions.
   */
  @Test
  public void testAddMotionAtEnd() {
    resetRectangle();
    motion7 = new Motion(101, new Point(200, 200),
            new Dimension(25, 100), Color.RED);
    assertEquals(this.shapes.get("R").getMotions().size(), 6);
    model.addMotion("R", motion7);
    assertEquals(shapes.get("R").getMotions().size(), 7);
    assertEquals(shapes.get("R").getMotions().get(6).getTick(), motion7.getTick());
  }

  /**
   * Tests the adding of a motion in between a shape's list of motions.
   */
  @Test
  public void testAddMotionInBetween() {
    resetRectangle();
    this.motion7 = new Motion(56, new Point(205, 200),
            new Dimension(25, 100), Color.RED);
    assertEquals(this.shapes.get("R").getMotions().size(), 6);
    this.model.addMotion("R", this.motion7);
    assertEquals(shapes.get("R").getMotions().size(), 7);
    assertEquals(shapes.get("R").getMotions().get(3).getTick(), motion4.getTick());
    assertEquals(shapes.get("R").getMotions().get(4).getTick(), motion7.getTick());
    assertEquals(shapes.get("R").getMotions().get(5).getTick(), motion5.getTick());
  }

  /**
   * Tests the removing of a motion at the beginning of a shape's list of motions.
   */
  @Test
  public void testRemoveMotionInFront() {
    resetRectangle();
    assertEquals(shapes.get("R").getMotions().size(), 6);
    model.removeMotion("R", motion1);
    assertEquals(shapes.get("R").getMotions().size(), 5);
  }

  /**
   * Tests the removing of a motion at the end of a shape's list of motions. Checks the new size of
   * the list, and where the motion before the removed motions is now in the list.
   */
  @Test
  public void testRemoveMotionAtEnd() {
    resetRectangle();
    assertEquals(shapes.get("R").getMotions().size(), 6);
    model.removeMotion("R", motion6);
    assertEquals(shapes.get("R").getMotions().size(), 5);
    assertEquals(shapes.get("R").getMotions().get(4).getTick(), motion5.getTick());
  }

  /**
   * Tests the removing of a motion in between a shape's list of motions. Checks the new size of the
   * list, and where the motions before and after the removed motion are in the list.
   */
  @Test
  public void testRemoveMotionInBetween() {
    resetRectangle();
    assertEquals(shapes.get("R").getMotions().size(), 6);
    assertEquals(shapes.get("R").getMotions().get(2).getTick(), this.motion3.getTick());
    model.removeMotion("R", motion3);
    assertEquals(shapes.get("R").getMotions().size(), 5);
    assertEquals(shapes.get("R").getMotions().get(1).getTick(), motion2.getTick());
    assertEquals(shapes.get("R").getMotions().get(2).getTick(), motion4.getTick());
  }


  /**
   * Tests removing a motion from a shape that does not exist. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFromNonExistentShape() {
    resetRectangle();
    rectangle = new MyRectangle(new Dimension(200, 200),
            Color.RED, new Point(200, 200));
    animatableRect = new AnimatableShape(rectangle, rectMotions);

    shapes = new LinkedHashMap<>();
    shapes.put("R", this.animatableRect);

    model = AnimationModelImpl.builder().setShapes(this.shapes).build();
    model.removeMotion("T", motion3);
  }

  /**
   * Tests removing a motion from a shape that doesn't have that motion. Expect
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFromShapeThatDoesNotHaveMotion() {
    resetRectangle();
    motion7 = new Motion(6, new Point(440, 70),
            new Dimension(120, 60), Color.BLUE);
    model.removeMotion("R", motion7);
  }

  /**
   * Tests removing a motion from a shape that has no motions. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFromShapeWithNoMotions() {
    resetRectangle();
    this.model.addShape("R", "rect");
    this.model.removeMotion("R", motion1);
  }


  /**
   * Tests adding a motion to a shape that does not exist. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionToNonExistentShape() {
    resetRectangle();
    model.addMotion("Y", motion3);
  }

  /**
   * Tests adding a motion to a shape that already has the motion. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionToShapeWithSameMotionAlready() {
    resetRectangle();
    model.addMotion("R", motion3);
  }
}

