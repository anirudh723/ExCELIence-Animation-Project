package cs3500.animator.model;

import java.awt.*;
import java.util.LinkedHashMap;

public class ReadOnlyAnimationModel implements IReadOnlyAnimationModel {
  AnimationModel delegate;

  public ReadOnlyAnimationModel(AnimationModel delegate) {
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
