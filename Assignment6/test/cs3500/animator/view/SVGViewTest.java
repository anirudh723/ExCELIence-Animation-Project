package cs3500.animator.view;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.awt.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import cs3500.animator.model.AnimatableShape;
import cs3500.animator.model.AnimatableShapeReadOnly;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IAnimatableShape;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.MyEllipse;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ReadOnlyAnimationModel;

/**
 * Represents the tests for the SVG view.
 */
public class SVGViewTest {

  private SVGView svgView;
  private Appendable ap;
  private Readable rd;
  private Dimension canvas;
  private LinkedHashMap<String, IAnimatableShapeReadOnly> readOnlyShapes;
  private LinkedHashMap<String, IAnimatableShape> shapes;
  private int ticksPerSecond;

  private IMotion motionR1;
  private IMotion motionR2;
  private IMotion motionR3;
  private IMotion motionR4;
  private IMotion motionR5;
  private IMotion motionR6;

  private IMotion motionE1;
  private IMotion motionE2;
  private IMotion motionE3;
  private IMotion motionE4;
  private IMotion motionE5;
  private IMotion motionE6;

  private IShape shape1;
  private IShape shape2;
  private ArrayList<IMotion> motions1;
  private ArrayList<IMotion> motions2;
  private IAnimatableShape ashape1;
  private IAnimatableShape ashape2;
  private IAnimatableShapeReadOnly readOnlyAShape1;
  private IAnimatableShapeReadOnly readOnlyAShape2;
  private AnimationModel model;
  private IReadOnlyAnimationModel readOnlyModel;

  private String outputString;

  /**
   * Resets the data to test specific methods.
   */
  void reset() {
    ap = new StringBuilder();
    rd = new InputStreamReader(System.in);
    canvas = new Dimension(500, 500);
    readOnlyShapes = new LinkedHashMap<>();
    shapes = new LinkedHashMap<>();
    ticksPerSecond = 5;

    shape1 = new MyRectangle(new Dimension(50, 100),
        Color.red, new Point(10, 10));
    shape2 = new MyEllipse(new Dimension(60, 60),
        Color.orange, new Point(10, 10));

    motionR1 = new Motion(1, new Point(200, 200),
        new Dimension(50, 100), Color.orange);
    motionR2 = new Motion(10, new Point(200, 200),
        new Dimension(50, 100), Color.orange);
    motionR3 = new Motion(20, new Point(300, 300),
        new Dimension(50, 100), Color.orange);
    motionR4 = new Motion(30, new Point(300, 300),
        new Dimension(50, 100), Color.red);
    motionR5 = new Motion(40, new Point(300, 300),
        new Dimension(25, 100), Color.orange);
    motionR6 = new Motion(50, new Point(200, 200),
        new Dimension(25, 80), Color.orange);

    motionE1 = new Motion(1, new Point(300, 300),
        new Dimension(60, 60), Color.orange);
    motionE2 = new Motion(10, new Point(200, 200),
        new Dimension(60, 60), Color.red);
    motionE3 = new Motion(20, new Point(400, 400),
        new Dimension(60, 60), Color.blue);
    motionE4 = new Motion(30, new Point(500, 500),
        new Dimension(60, 60), Color.blue);
    motionE5 = new Motion(40, new Point(400, 400),
        new Dimension(60, 60), Color.green);
    motionE6 = new Motion(50, new Point(300, 300),
        new Dimension(60, 60), Color.green);

    motions1 = new ArrayList<>(
        Arrays.asList(motionR1, motionR2, motionR3, motionR4, motionR5, motionR6));
    motions2 = new ArrayList<>(
        Arrays.asList(motionE1, motionE2, motionE3, motionE4, motionE5, motionE6));

    ashape1 = new AnimatableShape(shape1, motions1);
    ashape2 = new AnimatableShape(shape2, motions2);

    readOnlyAShape1 = new AnimatableShapeReadOnly(ashape1);
    readOnlyAShape2 = new AnimatableShapeReadOnly(ashape2);

    shapes.put("R", ashape1);
    shapes.put("E", ashape2);

    model = AnimationModelImpl.builder().setWidth(200).setHeight(200).setX(50).setY(50)
        .declareShape(
            "R", "rectangle").declareShape("E", "ellipse").build();

    readOnlyModel = new ReadOnlyAnimationModel(model);
    readOnlyShapes.put("R", readOnlyAShape1);
    readOnlyShapes.put("E", readOnlyAShape2);

    model.addMotion("R", motionR1);
    model.addMotion("R", motionR2);
    model.addMotion("R", motionR3);
    model.addMotion("R", motionR4);
    model.addMotion("R", motionR5);
    model.addMotion("R", motionR6);

    model.addMotion("E", motionE1);
    model.addMotion("E", motionE2);
    model.addMotion("E", motionE3);
    model.addMotion("E", motionE4);
    model.addMotion("E", motionE5);
    model.addMotion("E", motionE6);

    svgView = new SVGView(ap, rd, ticksPerSecond, readOnlyModel);

    outputString = "<svg width=\"250\" height= \"250\" version=\"1.1\"\n" +
        "     xmlns=\"http://www.w3.org/2000/svg\" "
        + " xmlns:xlink=\"http://www.w3.org/1999/xlink\" >\n" + "<rect id=\"R\" x=\"200\" y=\"200\""
        + " width=\"50\" height=\"100\" fill=\"rgb(255,200,0)\" visibility=\"visible\" />\n"
        + "<ellipse id=\"E\" cx=\"300\" cy=\"300\" rx=\"30\" ry=\"30\" fill=\"rgb(255,200,0)\""
        + " visibility=\"visible\" />\n" + " <animate xlink:href=\"#R\" attributeType=\"xml\""
        + " begin=\"200ms\" dur=\"1800ms\" attributeName=\"x\" from=\"200\" to=\"200\""
        + " fill=\"freeze\" /> \n" + " <animate xlink:href=\"#R\" attributeType=\"xml\" "
        + "begin=\"200ms\" dur=\"1800ms\" attributeName=\"y\" from=\"200\" to=\"200\""
        + " fill=\"freeze\" /> \n" + " <animate xlink:href=\"#R\" attributeType=\"xml\""
        + " begin=\"200ms\" dur=\"1800ms\" attributeName=\"width\" from=\"50\" to=\"50\""
        + " fill=\"freeze\" /> \n" + " <animate xlink:href=\"#R\" attributeType=\"xml\""
        + " begin=\"200ms\" dur=\"1800ms\" attributeName=\"height\" from=\"100\" to=\"100\""
        + " fill=\"freeze\" /> \n" + " <animate xlink:href=\"#R\" attributeType=\"xml\""
        + " begin=\"200ms\" dur=\"1800ms\" attributeName=\"fill\""
        + " values=\"rgb(255,200,0);rgb(255,200,0)\" fill=\"freeze\" /> \n" + " <animate"
        + " xlink:href=\"#R\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\" "
        + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" /> \n" + " <animate "
        + "xlink:href=\"#R\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" /> \n" + " <animate"
        + " xlink:href=\"#R\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"width\" from=\"50\" to=\"50\" fill=\"freeze\" /> \n" + " <animate"
        + " xlink:href=\"#R\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\" "
        + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" /> \n" + " <animate "
        + "xlink:href=\"#R\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\" "
        + "attributeName=\"fill\" values=\"rgb(255,200,0);rgb(255,200,0)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\""
        + " attributeName=\"x\" from=\"300\" to=\"300\" fill=\"freeze\" /> \n" + " <animate "
        + "xlink:href=\"#R\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\" "
        + "attributeName=\"y\" from=\"300\" to=\"300\" fill=\"freeze\" /> \n" + " <animate"
        + " xlink:href=\"#R\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\" "
        + "attributeName=\"width\" from=\"50\" to=\"50\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\""
        + " attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\" "
        + "attributeName=\"fill\" values=\"rgb(255,200,0);rgb(255,0,0)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\" "
        + "attributeName=\"x\" from=\"300\" to=\"300\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\" "
        + "attributeName=\"y\" from=\"300\" to=\"300\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\" "
        + "attributeName=\"fill\" values=\"rgb(255,0,0);rgb(255,200,0)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\" "
        + "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\" "
        + "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\" "
        + "attributeName=\"width\" from=\"25\" to=\"25\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"height\" from=\"100\" to=\"80\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#R\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"fill\" values=\"rgb(255,200,0);rgb(255,200,0)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"200ms\" dur=\"1800ms\" "
        + "attributeName=\"cx\" from=\"300\" to=\"200\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"200ms\" dur=\"1800ms\""
        + " attributeName=\"cy\" from=\"300\" to=\"200\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"200ms\" dur=\"1800ms\""
        + " attributeName=\"width\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"200ms\" dur=\"1800ms\""
        + " attributeName=\"height\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"200ms\" dur=\"1800ms\""
        + " attributeName=\"fill\" values=\"rgb(255,200,0);rgb(255,0,0)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"cx\" from=\"200\" to=\"400\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"cy\" from=\"200\" to=\"400\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"width\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"height\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"2000ms\" dur=\"2000ms\""
        + " attributeName=\"fill\" values=\"rgb(255,0,0);rgb(0,0,255)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\""
        + " attributeName=\"cx\" from=\"400\" to=\"500\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\""
        + " attributeName=\"cy\" from=\"400\" to=\"500\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\""
        + " attributeName=\"width\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\" "
        + "attributeName=\"height\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\""
        + " attributeName=\"fill\" values=\"rgb(0,0,255);rgb(0,0,255)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"cx\" from=\"500\" to=\"400\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"cy\" from=\"500\" to=\"400\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"width\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"height\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"6000ms\" dur=\"2000ms\""
        + " attributeName=\"fill\" values=\"rgb(0,0,255);rgb(0,255,0)\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"cx\" from=\"400\" to=\"300\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"cy\" from=\"400\" to=\"300\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"width\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"height\" from=\"60\" to=\"60\" fill=\"freeze\" /> \n"
        + " <animate xlink:href=\"#E\" attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\""
        + " attributeName=\"fill\" values=\"rgb(0,255,0);rgb(0,255,0)\" fill=\"freeze\" /> \n"
        + "</svg>";
  }

  /**
   * Tests the render method.
   */
  @Test
  public void render() {
    reset();
    assertEquals(outputString, svgView.getOutputAsString());
  }

  /**
   * Tests getting the right output string.
   */
  @Test
  public void getOutputAsString() {
    reset();
    assertEquals(outputString, svgView.getOutputAsString());
  }

  /**
   * Tests getting the correct enum type for this view.
   */
  @Test
  public void getViewType() {
    reset();
    assertEquals(ViewType.SVG, svgView.getViewType());
  }
}