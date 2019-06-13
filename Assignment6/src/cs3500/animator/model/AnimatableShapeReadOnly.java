package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class AnimatableShapeReadOnly implements IAnimatableShapeReadOnly {
  IAnimatableShape delegate;

  public AnimatableShapeReadOnly(IAnimatableShape delegate) {
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
                + "\t\t" + this.delegate.getMotions().get(i + 1).writeMotion() + "\n");
      }
    }
    return str.toString();
  }
}
