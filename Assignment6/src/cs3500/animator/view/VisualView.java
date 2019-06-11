package cs3500.animator.view;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

public class VisualView extends AbstractView {

  VisualView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
             LinkedHashMap<String, IAnimatableShapeReadOnly> shapes, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, canvas, shapes, model);
  }

  @Override
  public void render() {

  }
}
