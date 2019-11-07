package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Represents a read only version of the animatable shape. This is used so the original animatable
 * shapes cannot be mutated from other sources. Uses the actual animatable shape as a delegate
 * instead.
 */
public class AnimatableShapeReadOnly implements IAnimatableShapeReadOnly {

  private IAnimatableShape delegate;

  /**
   * Constructs a read only animatable shape.
   *
   * @param delegate the actual Animatable shape.
   * @throws IllegalArgumentException if the delegate is null.
   */
  public AnimatableShapeReadOnly(IAnimatableShape delegate) {
    if (delegate == null) {
      throw new IllegalArgumentException("Given animatable shape cannot be null");
    }
    this.delegate = delegate;
  }

  @Override
  public String getType() {
    return this.delegate.getType();
  }

  @Override
  public Color getColor() {
    return this.delegate.getColor();
  }

  @Override
  public Point2D getPosition() {
    return this.delegate.getPosition();
  }

  @Override
  public Dimension getDimension() {
    return this.delegate.getDimension();
  }


  @Override
  public List<IMotion> getMotions() {
    return this.delegate.getMotions();
  }

  @Override
  public String outputMotions(String name) {
    StringBuilder str = new StringBuilder();
    if (this.delegate.getMotions().size() == 1) {
      str.append("motion " + name + " " + this.delegate.getMotions().get(0).writeMotion());
    } else {
      for (int i = 0; i < this.delegate.getMotions().size() - 1; i++) {
        str.append("motion " + name + " " + this.delegate.getMotions().get(i).writeMotion()
                + " " + this.delegate.getMotions().get(i + 1).writeMotion() + "\n");
      }
    }
    return str.toString();
  }
}
