package cs3500.animator.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

/**
 * Represents the tests regarding the Controller.
 */
public class ControllerTest {

  IController controller;
  IView view;
  AnimationModel model;
  IReadOnlyAnimationModel readModel;
  List<ArrayList<String>> shapesAt10;
  List<ArrayList<String>> shapesAt11;
  List<ArrayList<String>> shapesAt12;

  /**
   * Initializes the data to test specific methods.
   */
  public void init() {
    model = AnimationModelImpl.builder()
            .setWidth(200).setHeight(200).setX(10).setY(10).declareShape(
                    "R", "rectangle").declareShape("E", "ellipse").build();
    model.addMotion("R", new Motion(10, new Point(10, 10),
            new Dimension(20, 20), Color.red));
    model.addMotion("R", new Motion(12, new Point(12, 12),
            new Dimension(20, 20), Color.red));

    model.addMotion("E", new Motion(10, new Point(10, 10),
            new Dimension(20, 20), Color.green));
    model.addMotion("E", new Motion(12, new Point(10, 10),
            new Dimension(22, 22), Color.green));

    shapesAt10 = new ArrayList<>();
    shapesAt10.add(new ArrayList<>(Arrays.asList("10.0", "10.0",
            "20.0", "20.0", "255", "0", "0", "rectangle")));
    shapesAt10.add(new ArrayList<>(Arrays.asList("10.0", "10.0",
            "20.0", "20.0", "0", "255", "0", "ellipse")));

    shapesAt11 = new ArrayList<>();
    shapesAt11.add(new ArrayList<>(Arrays.asList("11.0", "11.0",
            "20.0", "20.0", "255", "0", "0", "rectangle")));
    shapesAt11.add(new ArrayList<>(Arrays.asList("10.0", "10.0",
            "21.0", "21.0", "0", "255", "0", "ellipse")));

    shapesAt12 = new ArrayList<>();
    shapesAt12.add(new ArrayList<>(Arrays.asList("12.0", "12.0",
            "20.0", "20.0", "255", "0", "0", "rectangle")));
    shapesAt12.add(new ArrayList<>(Arrays.asList("10.0", "10.0",
            "22.0", "22.0", "0", "255", "0", "ellipse")));

    readModel = new ReadOnlyAnimationModel(model);
    view = new SVGView(new StringBuilder(), new InputStreamReader(System.in),
            1, readModel);
    controller = new Controller(model, view);
  }

  @Test
  public void getShapesAtTick0() {
    init();
    assertEquals(new ArrayList<String>(), controller.getShapesAtTick(0));
  }

  @Test
  public void getShapesAtTick10() {
    init();
    assertEquals(shapesAt10, controller.getShapesAtTick(10));
  }

  @Test
  public void getShapesAtTick11() {
    init();
    assertEquals(shapesAt11, controller.getShapesAtTick(11));
  }

  @Test
  public void getShapesAtTick12() {
    init();
    assertEquals(shapesAt12, controller.getShapesAtTick(12));
  }

  @Test
  public void getShapesAtTick20() {
    init();
    assertEquals(shapesAt12, controller.getShapesAtTick(20));
  }
}