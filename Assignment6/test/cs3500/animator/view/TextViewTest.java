package cs3500.animator.view;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.*;

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

import static org.junit.Assert.*;

public class TextViewTest {

  IView textView;
  Appendable ap;
  Readable rd;
  Dimension canvas;
  LinkedHashMap<String, IAnimatableShapeReadOnly> readOnlyShapes;
  LinkedHashMap<String, IAnimatableShape> shapes;
  int ticksPerSecond;
  IMotion motion1;
  IMotion motion2;
  IMotion motion3;
  IMotion motion4;
  IShape shape1;
  IShape shape2;
  ArrayList<IMotion> motions;
  IAnimatableShape ashape;
  IAnimatableShape ashape2;
  IAnimatableShapeReadOnly readOnlyAShape;
  IAnimatableShapeReadOnly readOnlyAShape2;
  AnimationModel model;
  IReadOnlyAnimationModel readOnlyModel;

  FileWriter closedWriter;
  FileWriter openWriter;
  FileReader readable;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private void setFiles() {
    try {
      closedWriter = new FileWriter("test2.txt", true);
      closedWriter.close();
      openWriter = new FileWriter("test2.txt", true);
      readable = new FileReader("givenFile.txt");
    } catch (IOException e) {
      System.out.println("setFiles errror!");
    }
  }

  private void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  private void restoreStreams() {
    System.setOut(originalOut);
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
    model = AnimationModelImpl.builder().setWidth(200).setHeight(200).setX(50).setY(50).declareShape(
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

    textView = new TextView(ap, rd, ticksPerSecond, canvas, readOnlyModel);
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
    IView nullAp = new TextView(null, readable, 1, new Dimension(1, 1), readOnlyModel);
    IView nullRd = new TextView(openWriter, null, 1, new Dimension(1, 1), readOnlyModel);
    IView negativeTick = new TextView(openWriter, readable, -10, new Dimension(1, 1), readOnlyModel);
    IView nullDimension = new TextView(openWriter, readable, 1, null, readOnlyModel);
    IView negativeWidth = new TextView(openWriter, readable, 1, new Dimension(-3, 1), readOnlyModel);
    IView negativeHeight = new TextView(openWriter, readable, 1, new Dimension(1, -22), readOnlyModel);
    IView nullModel = new TextView(openWriter, readable, 1, new Dimension(1, 1), null);
  }

  /**
   * Ensures TextView tryAppend(...) throws error when FileWriter is closed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAppend() {
    setFiles();
    IView appendError = new TextView(closedWriter, readable, 1, new Dimension(1, 1), readOnlyModel);
    appendError.tryAppend("this", "throws", "an", "error");
  }

  @Test
  public void testTryAppend() {
    setFiles();
    setUpStreams();
    IReadOnlyAnimationModel r = new ReadOnlyAnimationModel(AnimationReader.parseFile(readable, new AnimationModelImpl.Builder()));
    IView successAppend = new TextView(System.out, readable, 1, new Dimension(1, 1), r);
    successAppend.tryAppend("test1, ", "test2, ", "& test3");
    Assert.assertEquals("test1, test2, & test3", outContent.toString());
    restoreStreams();
  }

  @Test
  public void render() {
    reset();
    assertEquals(true, true);
    textView.render();
  }
}