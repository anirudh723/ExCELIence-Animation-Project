package cs3500.animator.view;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * Represents a visual view.
 */
public class VisualView extends AbstractView {

  /**
   * Constructs a visual view.
   * @param ap the Appendable.
   * @param rd the Readable.
   * @param ticksPerSecond ticks per second.
   * @param canvas the canvas for the view.
   * @param shapes the map of shapes.
   * @param model the read only version of the model.
   * @throws IllegalArgumentException if Appendable is null.
   * @throws IllegalArgumentException if Readable is null.
   * @throws IllegalArgumentException if the ticks per second is negative.
   * @throws IllegalArgumentException if the dimension is null.
   * @throws IllegalArgumentException if the model is null.
   * @throws IllegalArgumentException the shapes are null.
   */
  VisualView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
             LinkedHashMap<String, IAnimatableShapeReadOnly> shapes, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, canvas, shapes, model);
  }

  @Override
  public void render() {

  }
}
