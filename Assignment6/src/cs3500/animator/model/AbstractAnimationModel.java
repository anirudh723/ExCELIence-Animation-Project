package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Represents an abstract Animation Model. It contains a map of shape id's to shapes. Also has four
 * coordinates which can be used to set the bounds when building this model.
 */
public abstract class AbstractAnimationModel implements AnimationModel {

  private LinkedHashMap<String, IAnimatableShape> shapes;
  private int leftMostX;
  private int topMostY;
  private int width;
  private int height;

  /**
   * Constructs an AbstractAnimationModel.
   *
   * @param shapes the LinkedHashMap of shapes.
   * @param leftMostX left most x coordinate value.
   * @param topMostY top most y coordinate value.
   * @param width the width of the bounding box.
   * @param height the height of the bounding box.
   * @throws IllegalArgumentException if the LinkedHashMap of shapes is null.
   * @throws IllegalArgumentException if the width is negative or 0.
   * @throws IllegalArgumentException if the height is negative or 0.
   */
  public AbstractAnimationModel(LinkedHashMap<String, IAnimatableShape> shapes, int leftMostX,
      int topMostY, int width, int height) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("Map of shapes is null");
    }
    this.shapes = shapes;
    this.leftMostX = leftMostX;
    this.topMostY = topMostY;
    if (width <= 0) {
      throw new IllegalArgumentException("Width is negative or 0");
    }
    this.width = width;
    if (height <= 0) {
      throw new IllegalArgumentException("Height is negative or 0");
    }
    this.height = height;
  }

  @Override
  public void addShape(String name, String type) throws IllegalArgumentException {
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("Trying to add a shape that already exists in map.");
    }
    if (type.contains("rect")) {
      shapes.put(name, new AnimatableShape(new MyRectangle(), new ArrayList<>()));
    } else if (type.contains("ellipse")) {
      shapes.put(name, new AnimatableShape(new MyEllipse(), new ArrayList<>()));
    }
  }

  @Override
  public void removeShape(String shapeId) throws IllegalArgumentException {
    if (!shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException("Trying to remove a shape that doesn't exist in map.");
    }
    shapes.remove(shapeId);
  }

  @Override
  public void addMotion(String shapeId, IMotion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Given Motion is null.");
    }
    if (!shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException("Trying to add a motion to a shape that doesn't exist.");
    }
    shapes.get(shapeId).addMotionInShape(m);
  }

  @Override
  public void removeMotion(String shapeId, IMotion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Given Motion is null.");
    }
    if (!shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException(
          "Trying to remove a motion to a shape that doesn't exist.");
    }
    shapes.get(shapeId).removeMotionInShape(m);
  }


  @Override
  public LinkedHashMap<String, IAnimatableShapeReadOnly> getShapeMap() {
    LinkedHashMap<String, IAnimatableShapeReadOnly> readOnlyCopy = new LinkedHashMap<>();
    for (String key : this.shapes.keySet()) {
      readOnlyCopy.put(key, new AnimatableShapeReadOnly(this.shapes.get(key)));
    }
    return readOnlyCopy;
  }

  @Override
  public Dimension getCanvasDimension() {
    return new Dimension(this.width, this.height);
  }

  @Override
  public Point getTopXY() {
    return new Point(this.leftMostX, this.topMostY);
  }
}