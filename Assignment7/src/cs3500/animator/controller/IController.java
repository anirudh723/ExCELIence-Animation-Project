package cs3500.animator.controller;

import java.util.ArrayList;
import java.util.List;

public interface IController {

  void run();

  List<ArrayList<String>> getShapesAtTick(int tick);

  int getTick();
}
