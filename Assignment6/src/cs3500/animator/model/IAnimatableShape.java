package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Represents the general functions for an AnimatableShape.
 */
public interface IAnimatableShape {

  /**
   * Return the type of the shape.
   *
   * @return the String type of the shape.
   */
  String getType();


  /**
   * Returns the a deep copy of the list of motions.
   *
   * @return the list of motions for this Shape.
   */
  List<IMotion> getMotions();

  /**
   * Returns a new Position with the x and y coordinates of this position.
   *
   * @return the new Position.
   */
   Point2D getPosition();

  /**
   * Returns a new Dimension with the width and height elements of this dimension.
   *
   * @return the new Dimension.
   */
   Dimension getDimension();

  /**
   * Outputs the motions for a Shape.
   *
   * @return the String consisting of the Shape's corresponding motions.
   */
//  String outputMotions();

  /**
   * Adds a Motion to a Shape.
   *
   * @param m the Motion to be added.
   * @throws IllegalArgumentException if there is an existing motion at the same tick as the motion
   *                                  trying to be added.
   * @throws IllegalArgumentException if the given motion is null.
   */
  void addMotionInShape(IMotion m) throws IllegalArgumentException;

  /**
   * Removes a Motion from a Shape.
   *
   * @param m the Motion to be removed.
   * @throws IllegalArgumentException if the motion trying to be removed doesn't exist.
   * @throws IllegalArgumentException if the motion to be removed is null.
   */
  void removeMotionInShape(IMotion m) throws IllegalArgumentException;

  /**
   * Ensures that the list of Motions of an Animatable Shape is in order by their ticks.
   *
   * @throws IllegalArgumentException if the motions are not in order by tick.
   */
  void ensureMotionsInOrder() throws IllegalArgumentException;
}
