package cs3500.animator.model;

import java.util.List;

public class AnimatableReadOnly implements IAnimatableShapeReadOnly {
  AnimatableShape delegate;

  AnimatableReadOnly(AnimatableShape delegate) {
    this.delegate = delegate;
  }

  @Override
  public String getType() {
    return this.delegate.getType();
  }

  @Override
  public String getName() {
    return this.delegate.getName();
  }

  @Override
  public List<IMotion> getMotions() {
    return this.delegate.getMotions();
  }
}
