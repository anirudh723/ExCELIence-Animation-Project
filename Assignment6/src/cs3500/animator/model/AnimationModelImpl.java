package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents an Implementation of an Animation Model.
 */
public final class AnimationModelImpl extends AbstractAnimationModel {

  /**
   * Constructs an implementation of an Animation Model.
   *
   * @param shapes the map of shapes of the model.
   * @throws IllegalArgumentException if the LinkedHashMap of shapes is null.
   */
  private AnimationModelImpl(LinkedHashMap<String, IAnimatableShape> shapes, int leftMostX,
      int topMostY, int width, int height) {
    super(shapes, leftMostX, topMostY, width, height);
  }

  @Override
  public List<IShape> getAllShapesAtTick(int tick) {
    return null;
  }


  /**
   * Constructs a builder for configuring and then creating a game model instance. Defaults to a
   * standard game of Connect Four with players named "White" and "Red".
   *
   * @return the new builder
   */
  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private LinkedHashMap<String, IAnimatableShape> shapes;
    private int leftMostX;
    private int topMostY;
    private int width;
    private int height;


    public Builder() {
      this.shapes = new LinkedHashMap<>();
      this.leftMostX = 0;
      this.topMostY = 0;
      this.width = 0;
      this.height = 0;
    }

    public Builder setShapes(LinkedHashMap<String, IAnimatableShape> shapes) {
      this.shapes = shapes;
      return this;
    }


    public AnimationModel build() {
      return new AnimationModelImpl(this.shapes, this.leftMostX, this.topMostY, this.width, this.height);
    }

    public Builder setX(int x) {
      this.leftMostX = x;
      return this;
    }

    public Builder setY(int y) {
      this.topMostY = y;
      return this;
    }

    public Builder setWidth(int width) {
      this.width = width;
      return this;
    }

    public Builder setHeight(int height) {
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.setX(x);
      this.setY(y);
      this.setWidth(width);
      this.setHeight(height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      if (shapes.containsKey(name)) {
        throw new IllegalArgumentException("Trying to add a shape that already exists in map.");
      }
      if (name.equals("rectangle")) {
        shapes.put(name, new AnimatableShape(new MyRectangle(), new ArrayList<IMotion>()));
      }
      else {
        shapes.put(name, new AnimatableShape(new MyEllipse(), new ArrayList<IMotion>()));
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      IMotion m = new Motion(t1, new Point2D.Double(x1, x2), new Dimension(w1, h1), new Color(r1, g1, b1));
      if (!shapes.containsKey(name)) {
        throw new IllegalArgumentException("Trying to add a motion to a shape that doesn't exist.");
      }
      shapes.get(name).addMotionInShape(m);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {
      IMotion m = new Motion(t, new Point2D.Double(x, y), new Dimension(w, h), new Color(r, g, b));
      if (!shapes.containsKey(name)) {
        throw new IllegalArgumentException("Trying to add a motion to a shape that doesn't exist.");
      }
      shapes.get(name).addMotionInShape(m);
      return this;
    }
  }

}
