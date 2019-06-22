package cs3500.animator.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the functions for a controller.
 */
public interface IController {

  void run();

  List<ArrayList<String>> getShapesAtTick(int tick);
}
