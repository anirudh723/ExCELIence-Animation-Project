package cs3500.animator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an abstract Animation Model.
 */
public abstract class AbstractAnimationModel implements AnimationModel {

  private Map<Integer, IAnimatableShape> shapes;

  /**
   * Constructs an AbstractAnimationModel.
   *
   * @param shapes the LinkedHashMap of shapes
   * @throws IllegalArgumentException if the LinkedHashMap of shapes is null.
   */
  public AbstractAnimationModel(LinkedHashMap<Integer, IAnimatableShape> shapes) {
    if (shapes == null) {
      throw new IllegalArgumentException("Map of shapes is null");
    }
    this.shapes = shapes;
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
  public void addShape(int shapeId, IShape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Given shape is null.");
    }
    if (shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException("Trying to add a shape that already exists in map.");
    }
    shapes.put(shapeId, new AnimatableShape(shape, new ArrayList<Motion>()));
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
