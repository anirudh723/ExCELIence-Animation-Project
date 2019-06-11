package cs3500.animator.view;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;

public class VisualView extends AbstractView {

  VisualView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
             LinkedHashMap<String, IAnimatableShapeReadOnly> shapes) {
    super(ap, rd, ticksPerSecond, canvas, shapes);
  }

  @Override
  public void render() {

  }
}
