package cs3500.animator.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Controller for the Animation. Has the ability to run an animation, and get the state
 * of all shapes in the model at a given tick.
 */
public interface IController {

  /**
   * Runs the controller.
   */
  void run();

  /**
   * Gets information about each shape at the given tick. Uses tweening to calculate the values of a
   * shape's x, y, width, height, and color between keyframes.
   *
   * @param tick the tick to get the shapes at.
   * @return a list that represents all shape's x, y, width, height, and color at the given tick.
   */
  List<ArrayList<String>> getShapesAtTick(int tick);

  /**
   * Gets the current tick of the animation as it's running.
   *
   * @return the current tick.
   */
  int getTick();
}
