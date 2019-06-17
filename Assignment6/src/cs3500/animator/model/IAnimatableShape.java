package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
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
   * Return the color of the shape.
   *
   * @return the String type of the shape.
   */
  Color getColor();


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
   * Adds a Motion to a Shape. If the size is 0, it just adds it to the list. If the size is 1, it
   * compares the given motion with the one motion in the list and decides where to put it. If the
   * size is more than 1, it runs a loop and decides where to put it. It does one final check to see
   * if the last motion's tick is less than the given one, so it puts it at the end. Ensures/puts
   * the shape's initial state at the first motion's state.
   *
   * @param m the Motion to be added.
   * @throws IllegalArgumentException if the given motion is null.
   * @throws IllegalArgumentException if there is an existing motion at the same tick as the motion
   *                                  trying to be added.
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
