package cs3500.animator.view;

import org.junit.Before;
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

import static org.junit.Assert.*;

public class SVGViewTest {
  SVGView svgView;
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

  void reset(){
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
    readOnlyModel = new ReadOnlyAnimationModel(model);
    readOnlyShapes.put("R", readOnlyAShape);
    readOnlyShapes.put("E", readOnlyAShape2);
    model.addMotion("E", motion1);
    model.addMotion("E", motion2);

    svgView = new SVGView(ap, rd, ticksPerSecond, canvas, readOnlyShapes, readOnlyModel);
  }

  @Test
  public void render() {
    reset();
    assertEquals("", svgView.getOutputAsString());
  }

  @Test
  public void getOutputAsString() {
  }
}