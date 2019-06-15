package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashMap;

/**
 * Represents a read only version of the animation model. Helpful so the controller and view cannot
 * change the internal state of the model.
 */
public class ReadOnlyAnimationModel implements IReadOnlyAnimationModel {

  AnimationModel delegate;

  /**
   * Constructs a read only animation model.
   *
   * @param delegate the real animation model to delegate to.
   * @throws IllegalArgumentException if the given model is null.
   */
  public ReadOnlyAnimationModel(AnimationModel delegate) {
    if (delegate == null) {
      throw new IllegalArgumentException("Given Animation model is null");
    }
    this.delegate = delegate;
  }

  @Override
  public LinkedHashMap<String, IAnimatableShapeReadOnly> getShapeMap() {
    return this.delegate.getShapeMap();
  }

  @Override
  public Dimension getCanvasDimension() {
    return this.delegate.getCanvasDimension();
  }

  @Override
  public Point getTopXY() {
    return this.delegate.getTopXY();
  }
}
