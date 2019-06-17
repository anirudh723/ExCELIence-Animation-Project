package cs3500.animator.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.io.InputStreamReader;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyAnimationModel;

/**
 * Represents the tests for the SVG view.
 */
public class SVGViewTest {

  private SVGView svgView;

  private String outputString;

  /**
   * Resets the data to test specific methods.
   */
  private void reset() {
    Appendable ap = new StringBuilder();
    Readable rd = new InputStreamReader(System.in);

    int ticksPerSecond = 5;

    IMotion motionR1 = new Motion(1, new Point(200, 200),
            new Dimension(50, 100), Color.orange);
    IMotion motionR2 = new Motion(10, new Point(200, 200),
            new Dimension(50, 100), Color.orange);
    IMotion motionR3 = new Motion(20, new Point(300, 300),
            new Dimension(50, 100), Color.orange);
    IMotion motionR4 = new Motion(30, new Point(300, 300),
            new Dimension(50, 100), Color.red);
    IMotion motionR5 = new Motion(40, new Point(300, 300),
            new Dimension(25, 100), Color.orange);
    IMotion motionR6 = new Motion(50, new Point(200, 200),
            new Dimension(25, 80), Color.orange);

    IMotion motionE1 = new Motion(1, new Point(300, 300),
            new Dimension(60, 60), Color.orange);
    IMotion motionE2 = new Motion(10, new Point(200, 200),
            new Dimension(60, 60), Color.red);
    IMotion motionE3 = new Motion(20, new Point(400, 400),
            new Dimension(60, 60), Color.blue);
    IMotion motionE4 = new Motion(30, new Point(500, 500),
            new Dimension(60, 60), Color.blue);
    IMotion motionE5 = new Motion(40, new Point(400, 400),
            new Dimension(60, 60), Color.green);
    IMotion motionE6 = new Motion(50, new Point(300, 300),
            new Dimension(60, 60), Color.green);

    AnimationModel model = AnimationModelImpl.builder().setWidth(200).setHeight(200).setX(50).setY(50)
            .declareShape(
                    "R", "rectangle").declareShape("E", "ellipse").build();

    IReadOnlyAnimationModel readOnlyModel = new ReadOnlyAnimationModel(model);

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