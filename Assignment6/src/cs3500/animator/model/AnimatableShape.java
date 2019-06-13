package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Animatable Shape.
 */
public class AnimatableShape implements IAnimatableShape {

  private IShape shape;
  private ArrayList<IMotion> motions;

  /**
   * Constructs an Animatable shape.
   *
   * @param shape   the Shape.
   * @param motions the list of Motions that correspond with the given Shape.
   * @throws IllegalArgumentException if the given Shape is null.
   * @throws IllegalArgumentException if the given list of Motions is null.
   * @throws IllegalArgumentException if the list of motions are not in order by tick.
   */
  public AnimatableShape(IShape shape, ArrayList<IMotion> motions) {
    if (shape == null) {
      throw new IllegalArgumentException("Given Shape is null.");
    }
    this.shape = shape;
    if (motions == null) {
      throw new IllegalArgumentException("Given list of Motions is null.");
    }
    this.motions = motions;
    this.ensureMotionsInOrder();
  }

  @Override
  public String getType() {
    return this.shape.getType();
  }


  @Override
  public Color getColor() {
    return this.shape.getColor();
  }


  @Override
  public List<IMotion> getMotions() {
    ArrayList<IMotion> copy = new ArrayList<>();
    for (IMotion m : this.motions) {
      copy.add(new Motion(m.getTick(), m.getPosition(), m.getDimension(), m.getColor()));
    }
    return copy;
  }

  @Override
  public Point2D getPosition() {
    return this.shape.getPosition();
  }

  @Override
  public Dimension getDimension() {
    return this.shape.getDimension();
  }

  //todo change this
//  @Override
//  public String outputMotions() {
//    StringBuilder str = new StringBuilder();
//    for (int i = 0; i < motions.size() - 1; i++) {
//      str.append("motion " + shape.getName() + " " + motions.get(i).writeMotion() + "\t\t" + motions
//              .get(i + 1).writeMotion() + "\n");
//    }
//    return str.toString();
//  }

  @Override
  public void addMotionInShape(IMotion m) {
    if (m == null) {
      throw new IllegalArgumentException("Given Motion is null");
    }
    if (motions.size() == 0) {
      motions.add(m);
      putShapeAtInitialMotion();
    }
    else if (motions.size() == 1) {
      if (motions.get(0).getTick() > m.getTick()) {
        motions.add(0, m);
        return;
      } else if (motions.get(0).getTick() < m.getTick()) {
        motions.add(m);
        return;
      } else {
        throw new IllegalArgumentException("Trying to add a Motion where another Motion exists.");
      }
    } else {
      for (int i = 0; i < motions.size() - 1; i++) {
        if (motions.get(i).getTick() == m.getTick()) {
          throw new IllegalArgumentException("Trying to add a Motion where another Motion exists.");
        }
        if (motions.get(i).getTick() < m.getTick() && motions.get(i + 1).getTick() > m.getTick()) {
          motions.add(i + 1, m);
          return;
        }
      }
      motions.add(m);
    }
  }

  private void putShapeAtInitialMotion() {
    this.shape.assignInitialMotion(this.motions.get(0).getDimension(),
            this.motions.get(0).getPosition(), this.motions.get(0).getColor());
  }

  @Override
  public void removeMotionInShape(IMotion m) {
    if (m == null) {
      throw new IllegalArgumentException("Given Motion is null");
    }
    for (int i = 0; i < motions.size(); i++) {
      if (motions.get(i).getTick() == m.getTick()) {
        motions.remove(i);
        return;
      }
    }
    throw new IllegalArgumentException("Trying to remove a Motion that doesn't exist");
  }

  @Override
  public void ensureMotionsInOrder() {
    for (int i = 0; i < motions.size() - 1; i++) {
      if (motions.get(i).getTick() >= motions.get(i + 1).getTick()) {
        throw new IllegalArgumentException("Motions must be in order by their tick.");
      }
    }
  }
}
