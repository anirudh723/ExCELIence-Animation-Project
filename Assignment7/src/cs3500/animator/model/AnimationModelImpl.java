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

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.setX(x);
      this.setY(y);
      this.setWidth(width);
      this.setHeight(height);
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
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

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                      int h1, int r1, int g1, int b1, int t2,
                                                      int x2, int y2, int w2, int h2, int r2,
                                                      int g2, int b2) {
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

    /**
     * Adds an individual keyframe to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @param x    The x-position of the shape
     * @param y    The y-position of the shape
     * @param w    The width of the shape
     * @param h    The height of the shape
     * @param r    The red color-value of the shape
     * @param g    The green color-value of the shape
     * @param b    The blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
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
