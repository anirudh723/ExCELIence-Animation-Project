package cs3500.animator.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.IShape;

public interface IController {

  void run();

  List<ArrayList<String>> getShapesAtTick(int tick);
}
