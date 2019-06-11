package cs3500.animator.model;

import java.awt.*;
import java.util.LinkedHashMap;

public interface IReadOnlyAnimationModel {


  LinkedHashMap<String, IAnimatableShapeReadOnly> getShapeMap();

  Dimension getCanvasDimension();

  Point getTopXY();
}
