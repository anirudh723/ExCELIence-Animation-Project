package cs3500.animator.model;

import java.util.List;

public class AnimatableShapeReadOnly implements IAnimatableShapeReadOnly {
  IAnimatableShape delegate;

  AnimatableShapeReadOnly(IAnimatableShape delegate) {
    this.delegate = delegate;
  }

  @Override
  public String getType() {
    return this.delegate.getType();
  }


  @Override
  public List<IMotion> getMotions() {
    return this.delegate.getMotions();
  }

  @Override
  public String outputMotions(String name) {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < this.delegate.getMotions().size() - 1; i++) {
      str.append("motion " + name + " " + this.delegate.getMotions().get(i).writeMotion()
              + "\t\t" + this.delegate.getMotions().get(i + 1).writeMotion() + "\n");
    }
    return str.toString();
  }
}
