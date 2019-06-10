package cs3500.animator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an abstract Animation Model.
 */
public abstract class AbstractAnimationModel implements AnimationModel {

  private Map<String, IAnimatableShape> shapes;
  private int leftMostX;
  private int topMostY;
  private int width;
  private int height;

  /**
   * Constructs an AbstractAnimationModel.
   *
   * @param shapes the LinkedHashMap of shapes
   * @throws IllegalArgumentException if the LinkedHashMap of shapes is null.
   */
  public AbstractAnimationModel(LinkedHashMap<String, IAnimatableShape> shapes, int leftMostX,
      int topMostY, int width, int height) {
    if (shapes == null) {
      throw new IllegalArgumentException("Map of shapes is null");
    }
    this.shapes = shapes;
    this.leftMostX = leftMostX;
    this.topMostY = topMostY;
    if (width < 0) {
      throw new IllegalArgumentException("Width is negative");
    }
    this.width = width;
    if (height < 0) {
      throw new IllegalArgumentException("Height is negative");
    }
    this.height = height;
  }

  @Override
  public String getDescription() {
    StringBuilder str = new StringBuilder();
    for (IAnimatableShape shape : shapes.values()) {
      str.append("shape " + shape.getName() + " " + shape.getType() + "\n");
      str.append(shape.outputMotions());
    }
    return str.substring(0, str.toString().length() - 1);
  }

  @Override
  public void addShape(String name, String type) throws IllegalArgumentException {
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("Trying to add a shape that already exists in map.");
    }
    if (name.equals("rectangle")) {
      shapes.put(name, new AnimatableShape(new MyRectangle(), new ArrayList<IMotion>()));
    }
    else {
      shapes.put(name, new AnimatableShape(new MyEllipse(), new ArrayList<IMotion>()));
    }
  }

  @Override
  public void removeShape(int shapeId) throws IllegalArgumentException {
    if (!shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException("Trying to remove a shape that doesn't exist in map.");
    }
    shapes.remove(shapeId);
  }

  @Override
  public void addMotion(int shapeId, Motion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Given Motion is null.");
    }
    if (!shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException("Trying to add a motion to a shape that doesn't exist.");
    }
    shapes.get(shapeId).addMotionInShape(m);
  }

  @Override
  public void removeMotion(int shapeId, Motion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Given Motion is null.");
    }
    if (!shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException(
              "Trying to remove a motion to a shape that doesn't exist.");
    }
    shapes.get(shapeId).removeMotionInShape(m);
  }
}
