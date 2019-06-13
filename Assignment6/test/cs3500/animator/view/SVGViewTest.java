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
//  IMotion motion1;
//  IMotion motion2;
//  IMotion motion3;
//  IMotion motion4;

  IMotion motionR1;
  IMotion motionR2;
  IMotion motionR3;
  IMotion motionR4;
  IMotion motionR5;
  IMotion motionR6;

  IMotion motionE1;
  IMotion motionE2;
  IMotion motionE3;
  IMotion motionE4;
  IMotion motionE5;
  IMotion motionE6;

  IShape shape1;
  IShape shape2;
  ArrayList<IMotion> motions1;
  ArrayList<IMotion> motions2;
  IAnimatableShape ashape1;
  IAnimatableShape ashape2;
  IAnimatableShapeReadOnly readOnlyAShape1;
  IAnimatableShapeReadOnly readOnlyAShape2;
  AnimationModel model;
  IReadOnlyAnimationModel readOnlyModel;

  void reset() {
    ap = new StringBuilder();
    rd = new InputStreamReader(System.in);
    canvas = new Dimension(500, 500);
    readOnlyShapes = new LinkedHashMap<String, IAnimatableShapeReadOnly>();
    shapes = new LinkedHashMap<String, IAnimatableShape>();
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

    motions1 = new ArrayList<>(Arrays.asList(motionR1, motionR2, motionR3, motionR4, motionR5, motionR6));
    motions2 = new ArrayList<>(Arrays.asList(motionE1, motionE2, motionE3, motionE4, motionE5, motionE6));

    ashape1 = new AnimatableShape(shape1, motions1);
    ashape2 = new AnimatableShape(shape2, motions2);

    readOnlyAShape1 = new AnimatableShapeReadOnly(ashape1);
    readOnlyAShape2 = new AnimatableShapeReadOnly(ashape2);

    shapes.put("R", ashape1);
    shapes.put("E", ashape2);

    model = AnimationModelImpl.builder().setWidth(200).setHeight(200).setX(50).setY(50).declareShape(
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