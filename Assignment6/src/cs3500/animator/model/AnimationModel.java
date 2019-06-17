package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashMap;

/**
 * Represents the functions for an AnimationModel. Can add/remove shapes, add/remove motions, and
 * get certain types of information, which is helpful for the controller.
 */
public interface AnimationModel {

  /**
   * Adds a Shape to the model.
   *
   * @param name the name of the shape which is the key in the map.
   * @param type the type of shape to be added.
   * @throws IllegalArgumentException if a user tries to add a shape that already exists in map.
   */
  void addShape(String name, String type) throws IllegalArgumentException;

  /**
   * Removes a Shape to the model.
   *
   * @param shapeId the shapeId that the given Shape corresponds in the Map.
   * @throws IllegalArgumentException if a user tries to remove a shape that doesn't exist in map.
   */
  void removeShape(String shapeId) throws IllegalArgumentException;

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
  void addMotion(String shapeId, IMotion m) throws IllegalArgumentException;

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
  void removeMotion(String shapeId, IMotion m) throws IllegalArgumentException;
//
//  /**
//   * Returns all the shapes at a certain tick. This is helpful because it can see how the shapes
//   * have mutated after a certain amount of ticks, even between an occurring motion. This method is
//   * not going to be implemented yet as it needs more info.
//   *
//   * @param tick the given tick in the animation.
//   * @return the list of all shapes.
//   * @throws IllegalArgumentException if the given tick is negative
//   */
//  List<IShape> getAllShapesAtTick(int tick) throws IllegalArgumentException;

  /**
   * Gets the map of shapes from the model.
   *
   * @return the LinkedHashMap of shape id's to shapes.
   */
  LinkedHashMap<String, IAnimatableShapeReadOnly> getShapeMap();

  /**
   * Gets the canvas dimensions.
   *
   * @return the dimension of the canvas.
   */
  Dimension getCanvasDimension();

  /**
   * Gets the left most x and top most y.
   *
   * @return a Point with the x and y coordinates.
   */
  Point getTopXY();
}
