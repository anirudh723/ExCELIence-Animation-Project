package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests the methods regarding Animatable Shapes.
 */
public class AnimatableShapeTest {
  private IShape rectangle;
  private IShape ellipse;

  private Motion motionR1;
  private Motion motionR2;
  private Motion motionR3;
  private Motion motionR4;
  private Motion motionR5;
  private Motion motionR6;

  private Motion motionE1;
  private Motion motionE2;
  private Motion motionE3;
  private Motion motionE4;
  private Motion motionE5;
  private Motion motionE6;

  private AnimatableShape animatableRectangle;
  private AnimatableShape animatableEllipse;
  private IAnimatableShapeReadOnly iAnimatableShapeReadOnly;
  private IAnimatableShapeReadOnly iAnimatableShapeReadOnly2;


  /**
   * Initialize variables to represent animatable shapes - rectangle, its list of motions, and an
   * ellipse and its list of motions.
   */
  @Before
  public void setUp() {
    rectangle = new MyRectangle(new Dimension(200, 200),
            Color.RED, new Point(200, 200));
    ellipse = new MyEllipse(new Dimension(120, 60),
            Color.BLUE, new Point(440, 70));

    motionR1 = new Motion(1, new Point(200, 200),
            new Dimension(50, 100), Color.red);
    motionR2 = new Motion(10, new Point(200, 200),
            new Dimension(50, 100), Color.red);
    motionR3 = new Motion(50, new Point(300, 300),
            new Dimension(50, 100), Color.red);
    motionR4 = new Motion(51, new Point(300, 300),
            new Dimension(50, 100), Color.red);
    motionR5 = new Motion(70, new Point(300, 300),
            new Dimension(25, 100), Color.red);
    motionR6 = new Motion(100, new Point(200, 200),
            new Dimension(25, 100), Color.red);


    motionE1 = new Motion(6, new Point(440, 70),
            new Dimension(120, 60), Color.BLUE);
    motionE2 = new Motion(20, new Point(440, 70),
            new Dimension(120, 60), Color.BLUE);
    motionE3 = new Motion(50, new Point(440, 250),
            new Dimension(120, 60), Color.BLUE);
    motionE4 = new Motion(70, new Point(440, 370),
            new Dimension(120, 60), new Color(0, 170, 85));
    motionE5 = new Motion(80, new Point(440, 370),
            new Dimension(120, 60), Color.GREEN);
    motionE6 = new Motion(100, new Point(440, 370),
            new Dimension(120, 60), Color.GREEN);

    animatableRectangle = new AnimatableShape(rectangle, new ArrayList<>());
    animatableEllipse = new AnimatableShape(ellipse, new ArrayList<>());
    iAnimatableShapeReadOnly = new AnimatableShapeReadOnly(animatableRectangle);
    iAnimatableShapeReadOnly2 = new AnimatableShapeReadOnly(animatableEllipse);
  }

  /**
   * Test getting an AnimatableShape's type.
   */
  @Test
  public void getTypeRectangle() {
    assertEquals("rectangle", animatableRectangle.getType());
  }

  /**
   * Test getting an AnimatableShape's type.
   */
  @Test
  public void getTypeEllipse() {
    assertEquals("ellipse", animatableEllipse.getType());
  }

  /**
   * Test outputting the motions for an AnimatableShape with no motions.
   */
  @Test
  public void outputMotionsRectangleEmpty() {
    assertEquals("", iAnimatableShapeReadOnly.outputMotions("R"));
  }

  /**
   * Test outputting the motions for an AnimatableShape with no motions.
   */
  @Test
  public void outputMotionsEllipseEmpty() {
    assertEquals("", iAnimatableShapeReadOnly2.outputMotions("E"));
  }


  /**
   * Test outputting a string of the motions for an AnimatableShape with motions.
   */
  @Test
  public void outputMotionsRectangle() {
    setUp();
    rectangle = new MyRectangle(new Dimension(200, 200),
            Color.RED, new Point(200, 200));
    assertEquals(animatableRectangle.getMotions().size(), 0);
    animatableRectangle.addMotionInShape(motionR1);
    animatableRectangle.addMotionInShape(motionR2);
    animatableRectangle.addMotionInShape(motionR3);
    assertEquals(animatableRectangle.getMotions().size(), 3);
    String str = "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n";
    assertEquals(str, iAnimatableShapeReadOnly.outputMotions("R"));
  }

  /**
   * Test outputting a string of the motions for an AnimatableShape with motions.
   */
  @Test
  public void outputMotionsEllipse() {
    setUp();
    ellipse = new MyEllipse(new Dimension(120, 60),
            Color.BLUE, new Point(440, 70));
    assertEquals(animatableEllipse.getMotions().size(), 0);
    animatableEllipse.addMotionInShape(motionE1);
    animatableEllipse.addMotionInShape(motionE2);
    animatableEllipse.addMotionInShape(motionE3);
    assertEquals(animatableEllipse.getMotions().size(), 3);
    String str = "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n" +
            "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n";
    assertEquals(str, iAnimatableShapeReadOnly2.outputMotions("C"));
  }

  /**
   * Test adding motions to an AnimatableShape.
   */
  @Test
  public void addMotionInShapeRectangle() {
    setUp();
    assertEquals(animatableRectangle.getMotions().size(), 0);
    animatableRectangle.addMotionInShape(motionR4);
    animatableRectangle.addMotionInShape(motionR5);
    animatableRectangle.addMotionInShape(motionR6);
    assertEquals(animatableRectangle.getMotions().size(), 3);
    assertEquals(animatableRectangle.getMotions().get(0).getTick(), motionR4.getTick());
    assertEquals(animatableRectangle.getMotions().get(1).getTick(), motionR5.getTick());
    assertEquals(animatableRectangle.getMotions().get(2).getTick(), motionR6.getTick());
  }

  /**
   * Test adding motions to an AnimatableShape.
   */
  @Test
  public void addMotionInShapeEllipse() {
    setUp();
    assertEquals(animatableEllipse.getMotions().size(), 0);
    animatableEllipse.addMotionInShape(motionE4);
    animatableEllipse.addMotionInShape(motionE5);
    animatableEllipse.addMotionInShape(motionE6);
    assertEquals(animatableEllipse.getMotions().size(), 3);
    assertEquals(animatableEllipse.getMotions().get(0).getTick(), motionE4.getTick());
    assertEquals(animatableEllipse.getMotions().get(1).getTick(), motionE5.getTick());
    assertEquals(animatableEllipse.getMotions().get(2).getTick(), motionE6.getTick());
  }

  /**
   * Test removing motions to an AnimatableShape.
   */
  @Test
  public void removeMotionInShapeRectangle() {
    setUp();
    assertEquals(animatableRectangle.getMotions().size(), 0);
    animatableRectangle.addMotionInShape(motionR1);
    animatableRectangle.addMotionInShape(motionR2);
    animatableRectangle.removeMotionInShape(motionR2);
    assertEquals(animatableRectangle.getMotions().size(), 1);
    assertEquals(animatableRectangle.getMotions().get(0).getTick(), motionR1.getTick());
  }

  /**
   * Test removing motions to an AnimatableShape.
   */
  @Test
  public void removeMotionInShapeEllipse() {
    setUp();
    assertEquals(animatableEllipse.getMotions().size(), 0);
    animatableEllipse.addMotionInShape(motionE1);
    animatableEllipse.addMotionInShape(motionE2);
    animatableEllipse.removeMotionInShape(motionE2);
    assertEquals(animatableEllipse.getMotions().size(), 1);
    assertEquals(animatableEllipse.getMotions().get(0).getTick(), motionE1.getTick());
  }

  /**
   * Test getting a list of motions from an AnimatableShape.
   */
  @Test
  public void getMotionsEllipse() {
    setUp();
    animatableEllipse.addMotionInShape(motionE1);
    animatableEllipse.addMotionInShape(motionE2);
    animatableEllipse.addMotionInShape(motionE3);
    ArrayList<Motion> expectedMotions =
            new ArrayList<Motion>(Arrays.asList(motionE1, motionE2, motionE3));
    assertEquals(expectedMotions.get(0).getTick(), animatableEllipse.getMotions().get(0).getTick());
    assertEquals(expectedMotions.get(1).getTick(), animatableEllipse.getMotions().get(1).getTick());
    assertEquals(expectedMotions.get(2).getTick(), animatableEllipse.getMotions().get(2).getTick());
  }

  /**
   * Test getting a list of motions from an AnimatableShape.
   */
  @Test
  public void getMotionsRectangle() {
    setUp();
    animatableRectangle.addMotionInShape(motionR1);
    animatableRectangle.addMotionInShape(motionR2);
    animatableRectangle.addMotionInShape(motionR3);
    ArrayList<Motion> expectedMotions =
            new ArrayList<>(Arrays.asList(motionR1, motionR2, motionR3));
    assertEquals(expectedMotions.get(0).getTick(), motionR1.getTick());
    assertEquals(expectedMotions.get(1).getTick(), motionR2.getTick());
    assertEquals(expectedMotions.get(2).getTick(), motionR3.getTick());
  }

  /**
   * Test getting a list of motions from an AnimatableShape with no motions.
   */
  @Test
  public void getMotionsRectangleEmpty() {
    setUp();
    assertEquals(new ArrayList<Motion>(), animatableRectangle.getMotions());
  }

  /**
   * Test getting a list of motions from an AnimatableShape with no motions.
   */
  @Test
  public void getMotionsEllipseEmpty() {
    setUp();
    assertEquals(new ArrayList<Motion>(), animatableEllipse.getMotions());
  }

  /**
   * Test removing a motion that doesn't exist from an AnimatableShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeNonexistantMotionInShapeRectangle() {
    setUp();
    animatableRectangle.addMotionInShape(motionR1);
    animatableRectangle.removeMotionInShape(motionR2);
  }

  /**
   * Test removing a motion that doesn't exist from an AnimatableShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeNonexistantMotionInShapeEllipse() {
    setUp();
    animatableEllipse.addMotionInShape(motionE1);
    animatableEllipse.removeMotionInShape(motionE2);
  }

  /**
   * Test adding an invalid motion to an AnimatableShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addInvalidMotionInShapeRectangle() {
    setUp();
    animatableRectangle.addMotionInShape(new Motion(-1, new Point(200, 200),
            new Dimension(50, 100), Color.red));

  }

  /**
   * Test adding an invalid motion to an AnimatableShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addInvalidMotionInShapeEllipse() {
    setUp();
    animatableEllipse.addMotionInShape(new Motion(-1, new Point(200, 200),
            new Dimension(50, 100), Color.red));
  }

  /**
   * Tests adding a motion to a shape that already has a motion there.
   */
  @Test
  public void testAddMotionAtSameTimeToAnother() {
    setUp();
    animatableRectangle.addMotionInShape(motionR6);
    List<IMotion> before = animatableRectangle.getMotions();
    animatableRectangle.addMotionInShape(motionE6);
    List<IMotion> after = animatableRectangle.getMotions();
    for (int i = 0; i < before.size(); i++) {
      assertEquals(before.get(i).writeMotion(), after.get(i).writeMotion());
    }
  }
}