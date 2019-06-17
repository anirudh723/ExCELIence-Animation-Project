package cs3500.animator.view;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import cs3500.animator.util.AnimationReader;

/**
 * Represents the test for the textual view.
 */
public class TextViewTest {

  private IView textView;
  private Appendable ap;
  private Readable rd;
  private Dimension canvas;
  private LinkedHashMap<String, IAnimatableShapeReadOnly> readOnlyShapes;
  private LinkedHashMap<String, IAnimatableShape> shapes;
  private int ticksPerSecond;
  private IMotion motion1;
  private IMotion motion2;
  private IMotion motion3;
  private IMotion motion4;
  private IShape shape1;
  private IShape shape2;
  private ArrayList<IMotion> motions;
  private IAnimatableShape ashape;
  private IAnimatableShape ashape2;
  private IAnimatableShapeReadOnly readOnlyAShape;
  private IAnimatableShapeReadOnly readOnlyAShape2;
  private AnimationModel model;
  private IReadOnlyAnimationModel readOnlyModel;

  private FileWriter closedWriter;
  private FileWriter openWriter;
  private FileReader emptyCanvas;
  private FileReader emptyShapes;
  private FileReader givenReadable;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private void setFiles() {
    try {
      closedWriter = new FileWriter("test2.txt", true);
      closedWriter.close();
      openWriter = new FileWriter("test2.txt", true);
      emptyCanvas = new FileReader("TTemptyCanvas.txt");
      emptyShapes = new FileReader("TTemptyShapes.txt");
      givenReadable = new FileReader("givenFile.txt");
    } catch (IOException e) {
      System.out.println("setFiles error!");
    }
  }

  static String readFile(String path, Charset encoding) {
    String result = "";
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      result = new String(encoded, encoding);
    } catch (IOException e) {
      System.out.println("read file error!");
    }
    return result;
  }


  void reset() {
    ap = new StringBuilder();
    rd = new InputStreamReader(System.in);
    canvas = new Dimension(500, 500);
    readOnlyShapes = new LinkedHashMap<String, IAnimatableShapeReadOnly>();
    shapes = new LinkedHashMap<String, IAnimatableShape>();
    ticksPerSecond = 2;
    shape1 = new MyRectangle(new Dimension(20, 20),
        new Color(0, 0, 0), new Point(10, 10));
    shape2 = new MyEllipse(new Dimension(20, 20),
        new Color(0, 0, 0), new Point(10, 10));
    motion1 = new Motion(1, new Point(200, 200),
        new Dimension(50, 100), new Color(255, 0, 0));
    motion2 = new Motion(10, new Point(200, 200),
        new Dimension(50, 100), new Color(255, 0, 0));
    motion3 = new Motion(50, new Point(300, 300),
        new Dimension(50, 100), new Color(255, 0, 0));
    motion4 = new Motion(51, new Point(300, 300),
        new Dimension(50, 100), new Color(255, 0, 0));
    motions = new ArrayList<>(Arrays.asList(motion1, motion2, motion3, motion4));
    ashape = new AnimatableShape(shape1, motions);
    ashape2 = new AnimatableShape(shape2, motions);
    readOnlyAShape = new AnimatableShapeReadOnly(ashape);
    readOnlyAShape2 = new AnimatableShapeReadOnly(ashape2);
    shapes.put("R", ashape);
    model = AnimationModelImpl.builder().setWidth(200).setHeight(200).setX(50).setY(50)
        .declareShape(
            "R", "rectangle").declareShape("E", "ellipse").build();
    model.addMotion("R", motion1);
    model.addMotion("R", motion2);
    model.addMotion("R", motion3);
    model.addMotion("R", motion4);
    readOnlyModel = new ReadOnlyAnimationModel(model);
    readOnlyShapes.put("R", readOnlyAShape);
    readOnlyShapes.put("E", readOnlyAShape2);
    model.addMotion("E", motion1);
    model.addMotion("E", motion2);
    model.addMotion("E", motion3);
    model.addMotion("E", motion4);

    textView = new TextView(ap, rd, ticksPerSecond, readOnlyModel);
  }

  /**
   * Ensures TextView constructor throws errors for all possible illegal arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewConstructorExceptions() {
    /*
    ILLEGAL ARGUMENTS:
    1. null Appendable ap
    2. null Readable rd
    3. negative int ticksPerSecond
    4. null Dimension dimension
    5. negative Dimension dimension.width()
    6. negative Dimension dimension.height()
    7. null IReadOnlyModel model
    */
    reset();
    setFiles();
    IView nullAp = new TextView(null, givenReadable, 1, readOnlyModel);
    IView nullRd = new TextView(openWriter, null, 1, readOnlyModel);
    IView negativeTick = new TextView(openWriter, givenReadable, -10, readOnlyModel);
    IView nullDimension = new TextView(openWriter, givenReadable, 1, readOnlyModel);
    IView negativeWidth = new TextView(openWriter, givenReadable, 1, readOnlyModel);
    IView negativeHeight = new TextView(openWriter, givenReadable, 1, readOnlyModel);
    IView nullModel = new TextView(openWriter, givenReadable, 1, null);
  }

  /**
   * Ensures TextView tryAppend(...) throws error when FileWriter is closed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAppend() {
    setFiles();
    IView appendError = new TextView(closedWriter, givenReadable, 1, readOnlyModel);
    appendError.tryAppend("this", "throws", "an", "error");
  }

  /**
   * Ensures TextView render() functionality for all edge cases.
   */
  @Test
  public void render() {
    reset();
    setFiles();

    StringBuilder emptyCanvasOut = new StringBuilder();
    IReadOnlyAnimationModel emptyCanvas
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.emptyCanvas,
        new AnimationModelImpl.Builder()));
    IView emptyRender = new TextView(emptyCanvasOut, this.emptyCanvas,
        1, emptyCanvas);
    emptyRender.render();
    Assert.assertEquals("canvas 200 70 360 360", emptyCanvasOut.toString());

    StringBuilder emptyShapesOut = new StringBuilder();
    IReadOnlyAnimationModel emptyShapes
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.emptyShapes,
        new AnimationModelImpl.Builder()));
    IView emptyShapesRender = new TextView(emptyShapesOut, this.emptyShapes, 1,
        emptyShapes);
    emptyShapesRender.render();
    Assert.assertEquals("canvas 200 70 360 360" + System.lineSeparator()
            + "shape R rectangle" + System.lineSeparator() + "shape C ellipse",
        emptyShapesOut.toString());

    StringBuilder givenOut = new StringBuilder();
    IReadOnlyAnimationModel givenModel
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.givenReadable,
        new AnimationModelImpl.Builder()));
    IView givenView = new TextView(givenOut, this.givenReadable, 1, givenModel);
    givenView.render();
    Assert.assertEquals(readFile("givenFile.txt",
        Charset.defaultCharset()), givenOut.toString());
    System.out.println("file read error");
  }
}