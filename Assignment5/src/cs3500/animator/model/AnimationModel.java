package cs3500.animator.model;

import java.util.List;

/**
 * Represents the functions for an AnimationModel.
 */
public interface AnimationModel {

  /**
   * Creates a text description of the animation.
   *
   * @return the String description of the animation.
   */
  String getDescription();

  /**
   * Adds a Shape to the model.
   *
   * @param shapeId the shapeId that the given Shape corresponds in the Map.
   * @param shape   the given Shape to be added to the Map.
   * @throws IllegalArgumentException if a user tries to add a shape that already exists in map.
   * @throws IllegalArgumentException if the given Shape is null.
   */
  void addShape(int shapeId, IShape shape) throws IllegalArgumentException;

  /**
   * Removes a Shape to the model.
   *
   * @param shapeId the shapeId that the given Shape corresponds in the Map.
   * @throws IllegalArgumentException if a user tries to remove a shape that doesn't exist in map.
   */
  void removeShape(int shapeId) throws IllegalArgumentException;

  /**
   * Adds a Motion to one of the Model's shapes.
   *
   * @param shapeId the shapeId that the given Shape corresponds in the Map.
   * @param m       the Motion to be added.
   * @throws IllegalArgumentException if the given Motion is null.
   * @throws IllegalArgumentException if a user tries to add a Motion to a shape that doesn't
   *                                  exist.
   * @throws IllegalArgumentException if a user tries to add a Motion where another Motion exists.
   */
  void addMotion(int shapeId, Motion m) throws IllegalArgumentException;

  /**
   * Removes a Motion to one of the Model's shapes.
   *
   * @param shapeId the shapeId that the given Shape corresponds in the Map.
   * @param m       the Motion to be removed.
   * @throws IllegalArgumentException if the given Motion is null.
   * @throws IllegalArgumentException if a user tries to remove a Motion to a shape that doesn't
   *                                  exist.
   * @throws IllegalArgumentException if a user tries to remove a Motion that doesn't exist
   */
  void removeMotion(int shapeId, Motion m) throws IllegalArgumentException;

  /**
   * Returns all the shapes at a certain tick. This is helpful because it can see how the shapes
   * have mutated after a certain amount of ticks, even between an occurring motion. This method is
   * not going to be implemented yet as it needs more info.
   *
   * @param tick the given tick in the animation.
   * @return the list of all shapes.
   * @throws IllegalArgumentException if the given tick is negative
   */
  List<IShape> getAllShapesAtTick(int tick) throws IllegalArgumentException;

}
