import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

  private LinkedHashMap<Integer, IAnimatableShape> shapes;
  private ArrayList<Motion> rectMotions = new ArrayList<>();
  private ArrayList<Motion> ellipseMotions = new ArrayList<>();

  private IShape rectangle;
  private IShape ellipse;

  private IAnimatableShape animatableRect;
  private IAnimatableShape animatableEllipse;

  private Motion motion1;
  private Motion motion2;
  private Motion motion3;
  private Motion motion4;
  private Motion motion5;
  private Motion motion6;
  private Motion motion7;

  /**
   * resets the variables to certain data to use for specific tests.
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

    rectangle = new MyRectangle("R", new Dimension(200, 200),
            Color.RED, new Point(200,200));
    animatableRect = new AnimatableShape(rectangle, rectMotions);

    shapes = new LinkedHashMap<>();
    shapes.put(1, this.animatableRect);

    model = new AnimationModelImpl(this.shapes);
  }

  void resetEllipse() {
    motion1 = new Motion(6, new Point(440,70),
            new Dimension(120,60), Color.BLUE);
    motion2 = new Motion(20, new Point(440,70),
            new Dimension(120,60), Color.BLUE);
    motion3 = new Motion(50, new Point(440,250),
            new Dimension(120,60), Color.BLUE);
    motion4 = new Motion(70, new Point(440,370),
            new Dimension(120,60), new Color(0, 170, 85));
    motion5 = new Motion(80, new Point(440,370),
            new Dimension(120,60), Color.GREEN);
    motion6 = new Motion(100, new Point(440,370),
            new Dimension(120,60), Color.GREEN);

    ellipseMotions.add(motion1);
    ellipseMotions.add(motion2);
    ellipseMotions.add(motion3);
    ellipseMotions.add(motion4);
    ellipseMotions.add(motion5);
    ellipseMotions.add(motion6);

    ellipse = new MyEllipse("C", new Dimension(120,60),
            Color.BLUE, new Point(440,70));
    animatableEllipse = new AnimatableShape(ellipse, ellipseMotions);

    shapes = new LinkedHashMap<>();
    shapes.put(1, this.animatableEllipse);

    model = new AnimationModelImpl(this.shapes);
  }

  private String rectangleOutput
          = "shape R rectangle\n"
          + "motion R 1 200 200 50 100 255 0 0\t\t10 200 200 50 100 255 0 0\n"
          + "motion R 10 200 200 50 100 255 0 0\t\t50 300 300 50 100 255 0 0\n"
          + "motion R 50 300 300 50 100 255 0 0\t\t51 300 300 50 100 255 0 0\n"
          + "motion R 51 300 300 50 100 255 0 0\t\t70 300 300 25 100 255 0 0\n"
          + "motion R 70 300 300 25 100 255 0 0\t\t100 200 200 25 100 255 0 0";

  private String ellipseOutput
          = "shape C ellipse\n"
          + "motion C 6 440 70 120 60 0 0 255\t\t20 440 70 120 60 0 0 255\n"
          + "motion C 20 440 70 120 60 0 0 255\t\t50 440 250 120 60 0 0 255\n"
          + "motion C 50 440 250 120 60 0 0 255\t\t70 440 370 120 60 0 170 85\n"
          + "motion C 70 440 370 120 60 0 170 85\t\t80 440 370 120 60 0 255 0\n"
          + "motion C 80 440 370 120 60 0 255 0\t\t100 440 370 120 60 0 255 0";

  /**
   * Creates an animation model with no shapes.
   */
  @Test
  public void createEmptyAnimation() {
    shapes = new LinkedHashMap<>();
    model = new AnimationModelImpl(shapes);
    assertEquals(shapes.size(), 0);
  }

  /**
   * Tests the getDescription method for a rectangle.
   */
  @Test
  public void testGetDescription() {
    resetRectangle();
    String rectangleOutput = model.getDescription();
    assertEquals(this.rectangleOutput, rectangleOutput);
  }

  /**
   * Tests the getDescription method for an ellipse.
   */
  @Test
  public void testGetDescription2() {
    resetEllipse();
    ellipse = new MyEllipse("C", new Dimension(120,60),
        Color.BLUE, new Point(440,70));
    animatableEllipse = new AnimatableShape(ellipse, ellipseMotions);

    shapes = new LinkedHashMap<>();
    shapes.put(1, this.animatableEllipse);

    model = new AnimationModelImpl(this.shapes);
    String ellipseOutput = model.getDescription();
    assertEquals(this.ellipseOutput, ellipseOutput);
  }

  /**
   * Tests the adding of a shape.
   */
  @Test
  public void testAddShape() {
    ellipse = new MyEllipse("e2", new Dimension(30, 30),
            new Color(255, 255, 0), new Point(350, 200));
    resetRectangle();
    assertEquals(1, shapes.size());
    model.addShape(6, ellipse);
    assertEquals(2, shapes.size());
    assertEquals(shapes.get(6).getName(), ellipse.getName());
  }

  /**
   * Tests the removing of a shape.
   */
  @Test
  public void testRemoveShape() {
    resetRectangle();
    assertEquals(shapes.size(), 1);
    model.removeShape(1);
    assertEquals(shapes.size(), 0);
  }

  /**
   * Tests the adding of a motion at the front of a shape's list of motions.
   */
  @Test
  public void testAddMotionAtFront() {
    resetRectangle();
    motion7 = new Motion(101,new Point(200,200),
        new Dimension(25,100), Color.RED);
    assertEquals(shapes.get(1).getMotions().size(), 6);
    model.addMotion(1, motion7);
    assertEquals(shapes.get(1).getMotions().size(), 7);
    assertEquals(shapes.get(1).getMotions().get(6).getTick(), motion7.getTick());
  }

  /**
   * Tests the adding of a motion at the end of a shape's list of motions.
   */
  @Test
  public void testAddMotionAtEnd() {
    resetRectangle();
    motion7 = new Motion(101,new Point(200,200),
        new Dimension(25,100), Color.RED);
    assertEquals(shapes.get(1).getMotions().size(), 6);
    model.addMotion(1, motion7);
    assertEquals(shapes.get(1).getMotions().size(), 7);
    assertEquals(shapes.get(1).getMotions().get(6).getTick(), motion7.getTick());
  }

  /**
   * Tests the adding of a motion in between a shape's list of motions.
   */
  @Test
  public void testAddMotionInBetween() {
    resetRectangle();
    this.motion7 = new Motion(56,new Point(205,200),
        new Dimension(25,100), Color.RED);
    assertEquals(this.shapes.get(1).getMotions().size(), 6);
    this.model.addMotion(1, this.motion7);
    assertEquals(shapes.get(1).getMotions().size(), 7);
    assertEquals(shapes.get(1).getMotions().get(3).getTick(), motion4.getTick());
    assertEquals(shapes.get(1).getMotions().get(4).getTick(), motion7.getTick());
    assertEquals(shapes.get(1).getMotions().get(5).getTick(), motion5.getTick());
  }

  /**
   * Tests the removing of a motion at the beginning of a shape's list of motions.
   */
  @Test
  public void testRemoveMotionInFront() {
    resetRectangle();
    assertEquals(shapes.get(1).getMotions().size(), 6);
    model.removeMotion(1, motion1);
    assertEquals(shapes.get(1).getMotions().size(), 5);
  }

  /**
   * Tests the removing of a motion at the end of a shape's list of motions.
   * Checks the new size of the list, and where the motion before the removed motions
   * is now in the list.
   */
  @Test
  public void testRemoveMotionAtEnd() {
    resetRectangle();
    assertEquals(shapes.get(1).getMotions().size(), 6);
    model.removeMotion(1, motion6);
    assertEquals(shapes.get(1).getMotions().size(), 5);
    assertEquals(shapes.get(1).getMotions().get(4).getTick(), motion5.getTick());
  }

  /**
   * Tests the removing of a motion in between a shape's list of motions.
   * Checks the new size of the list, and where the motions before and after the removed motion
   * are in the list.
   */
  @Test
  public void testRemoveMotionInBetween() {
    resetRectangle();
    assertEquals(shapes.get(1).getMotions().size(), 6);
    assertEquals(shapes.get(1).getMotions().get(2).getTick(), this.motion3.getTick());
    model.removeMotion(1, motion3);
    assertEquals(shapes.get(1).getMotions().size(), 5);
    assertEquals(shapes.get(1).getMotions().get(1).getTick(), motion2.getTick());
    assertEquals(shapes.get(1).getMotions().get(2).getTick(), motion4.getTick());
  }


  /**
   * Tests removing a motion from a shape that does not exist. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFromNonExistentShape() {
    resetRectangle();
    rectangle = new MyRectangle("R", new Dimension(200, 200),
        Color.RED, new Point(200,200));
    animatableRect = new AnimatableShape(rectangle, rectMotions);

    shapes = new LinkedHashMap<>();
    shapes.put(1, this.animatableRect);

    model = new AnimationModelImpl(this.shapes);
    model.removeMotion(10, motion3);
  }

  /**
   * Tests removing a motion from a shape that doesn't have that motion.
   * Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFromShapeThatDoesNotHaveMotion() {
    resetRectangle();
    motion7 = new Motion(6, new Point(440,70),
        new Dimension(120,60), Color.BLUE);
    model.removeMotion(10, motion7);
  }

  /**
   * Tests removing a motion from a shape that has no motions. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFromShapeWithNoMotions() {
    resetRectangle();
    MyRectangle rect = new MyRectangle("no motions", new Dimension(200, 200),
            Color.RED, new Point(200,200));
    this.model.addShape(3, rect);
    this.model.removeMotion(3, motion1);
  }


  /**
   * Tests adding a motion to a shape that does not exist. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionToNonExistentShape() {
    resetRectangle();
    model.addMotion(10, motion3);
  }

  /**
   * Tests adding a motion to a shape that already has the motion. Expect IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionToShapeWithSameMotionAlready() {
    resetRectangle();
    model.addMotion(1, motion3);
  }

  /**
   * Tests adding a motion to a shape that has no motions.
   */
  @Test
  public void testAddShapeToShapeWithNoMotions() {
    resetRectangle();
    MyRectangle rect = new MyRectangle("no motions", new Dimension(200, 200),
            Color.RED, new Point(200,200));
    this.model.addShape(3, rect);
    assertTrue(this.shapes.containsKey(3));
    assertEquals(this.shapes.get(3).getName(), rect.getName());
  }

  /**
   * Tests adding a motion to a shape that has no motions.
   */
  @Test
  public void testAddShapeWithNoMotions() {
    resetRectangle();
    MyRectangle rect = new MyRectangle("no motions", new Dimension(200, 200),
            Color.RED, new Point(200,200));
    this.model.addShape(3, rect);
    this.model.addMotion(3, this.motion5);
    assertEquals(this.shapes.get(3).getMotions().get(0).getTick(), this.motion5.getTick());
  }
}

