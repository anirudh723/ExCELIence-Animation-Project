package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Represents an Implementation of an Animation Model. Has a map of shapes along with the left most
 * x, top most y, width, and height bounding box coordinates.
 */
public final class AnimationModelImpl extends AbstractAnimationModel {

  /**
   * Constructs an implementation of an Animation Model.
   *
   * @param shapes    the map of shapes of the model.
   * @param leftMostX left most x coordinate value.
   * @param topMostY  top most y coordinate value.
   * @param width     the width of the bounding box.
   * @param height    the height of the bounding box.
   * @throws IllegalArgumentException if the LinkedHashMap of shapes is null.
   * @throws IllegalArgumentException if the width is negative or 0.
   * @throws IllegalArgumentException if the height is negative or 0.
   */
  private AnimationModelImpl(LinkedHashMap<String, IAnimatableShape> shapes, int leftMostX,
                             int topMostY, int width, int height) {
    super(shapes, leftMostX, topMostY, width, height);
  }


  /**
   * Constructs a builder for configuring and then creating an animation model instance.
   *
   * @return the new builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Represents a Builder class, which implements from the provided Animation Builder class. Used to
   * make our model work to show any of the animations.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private LinkedHashMap<String, IAnimatableShape> shapes;
    private int leftMostX;
    private int topMostY;
    private int width;
    private int height;

    /**
     * Constructs a Builder with default values.
     */
    public Builder() {
      this.shapes = new LinkedHashMap<>();
      this.leftMostX = 20;
      this.topMostY = 20;
      this.width = 100;
      this.height = 100;
    }

    /**
     * Setter for the map of shapes.
     *
     * @param shapes the map of Shapes.
     * @return the updated Builder.
     */
    public Builder setShapes(LinkedHashMap<String, IAnimatableShape> shapes) {
      this.shapes = shapes;
      return this;
    }

    /**
     * Setter for the left most x coordinate.
     *
     * @param x the left most x.
     * @return the updated Builder.
     */
    public Builder setX(int x) {
      this.leftMostX = x;
      return this;
    }

    /**
     * Setter for the top most y coordinate.
     *
     * @param y the top most y.
     * @return the updated Builder.
     */
    public Builder setY(int y) {
      this.topMostY = y;
      return this;
    }

    /**
     * Setter for the bounding box width.
     *
     * @param width the bounding box width.
     * @return the updated Builder.
     */
    public Builder setWidth(int width) {
      this.width = width;
      return this;
    }

    /**
     * Setter for the bounding box height.
     *
     * @param height bounding box height.
     * @return the updated Builder.
     */
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
      if (type.contains("rect")) {
        shapes.put(name, new AnimatableShape(new MyRectangle(), new ArrayList<>()));
      } else if (type.contains("ellipse")) {
        shapes.put(name, new AnimatableShape(new MyEllipse(), new ArrayList<>()));
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      IMotion m = new Motion(t1, new Point2D.Double(x1, y1), new Dimension(w1, h1),
              new Color(r1, g1, b1));
      IMotion m2 = new Motion(t2, new Point2D.Double(x2, y2), new Dimension(w2, h2),
              new Color(r2, g2, b2));
      if (!shapes.containsKey(name)) {
        throw new IllegalArgumentException("Trying to add a motion to a shape that doesn't exist.");
      }
      shapes.get(name).addMotionInShape(m);
      shapes.get(name).addMotionInShape(m2);
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

    /**
     * Returns an instance of the animation model, with the specific fields that were built inside
     * this Builder's setters.
     *
     * @return the animation model implementation.
     */
    public AnimationModel build() {
      return new AnimationModelImpl(this.shapes, this.leftMostX, this.topMostY, this.width,
              this.height);
    }
  }
}
