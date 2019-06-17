package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashMap;

/**
 * Represents a read only version of the model. This is beneficial because the controller or view
 * would not be able to change the model's internal state. They can only grab the necessary
 * information.
 */
public interface IReadOnlyAnimationModel {

  /**
   * Gets the map of shape id's to shapes.
   *
   * @return the LinkedHashMap of shapes.
   */
  LinkedHashMap<String, IAnimatableShapeReadOnly> getShapeMap();

  /**
   * Gets the canvas dimension.
   *
   * @return the dimension of the canvas.
   */
  Dimension getCanvasDimension();

  /**
   * Gets the left most x and top most y.
   *
   * @return the Point of the x and y coordinates.
   */
  Point getTopXY();
}
