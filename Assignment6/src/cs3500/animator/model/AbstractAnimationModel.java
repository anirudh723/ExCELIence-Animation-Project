package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Represents an abstract Animation Model.
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

//  @Override
//  public String getDescription() {
//    StringBuilder str = new StringBuilder();
//    for(String key : shapes.keySet()) {
//      str.append("shape " + key);
//        str.append(" " + this.shapes.get(key).getType() + "\n");
//        str.append(this.shapes.get(key).outputMotions()+"\n");
//      }
//    return str.substring(0, str.toString().length() - 1);
//  }

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
  public LinkedHashMap<String,IAnimatableShapeReadOnly> getShapeMap(){
    LinkedHashMap<String,IAnimatableShapeReadOnly> readOnlyCopy = new LinkedHashMap<>();
    for(String key : this.shapes.keySet()){
      readOnlyCopy.put(key, new AnimatableShapeReadOnly(this.shapes.get(key)));
    }
    return readOnlyCopy;
  }

  @Override
  public Dimension getCanvasDimension(){
    return new Dimension(this.width, this.height);
  }

  @Override
  public Point getTopXY(){
    return new Point(this.leftMostX, this.topMostY);
  }
}
