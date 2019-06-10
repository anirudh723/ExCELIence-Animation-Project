package cs3500.animator.model;

import java.util.List;

/**
 * Represents a read only version
 */
public interface IAnimatableShapeReadOnly {

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
  List<IMotion> getMotions();
}
