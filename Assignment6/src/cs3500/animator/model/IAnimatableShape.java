package cs3500.animator.model;

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
   * Return the name of the shape.
   *
   * @return the String type of the shape.
   */
  String getName();

  /**
   * Returns the a deep copy of the list of motions.
   *
   * @return the list of motions for this Shape.
   */
  List<Motion> getMotions();

  /**
   * Outputs the motions for a Shape.
   *
   * @return the String consisting of the Shape's corresponding motions.
   */
  String outputMotions();

  /**
   * Adds a Motion to a Shape.
   *
   * @param m the Motion to be added.
   * @throws IllegalArgumentException if there is an existing motion at the same tick as the motion
   *                                  trying to be added.
   * @throws IllegalArgumentException if the given motion is null.
   */
  void addMotionInShape(Motion m) throws IllegalArgumentException;

  /**
   * Removes a Motion from a Shape.
   *
   * @param m the Motion to be removed.
   * @throws IllegalArgumentException if the motion trying to be removed doesn't exist.
   * @throws IllegalArgumentException if the motion to be removed is null.
   */
  void removeMotionInShape(Motion m) throws IllegalArgumentException;

  /**
   * Ensures that the list of Motions of an Animatable Shape is in order by their ticks.
   *
   * @throws IllegalArgumentException if the motions are not in order by tick.
   */
  void ensureMotionsInOrder() throws IllegalArgumentException;
}
