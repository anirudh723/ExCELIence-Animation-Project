package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.Dimension;
import java.util.LinkedHashMap;

/**
 * Represents an animation as a textual description of the animation that specifies shapes, colors,
 * movement, transformations, and ticks that serve as a unitless measurement for duration.
 * Works on a variety of output sources.
 */
public class TextView extends AbstractView {
  ViewType type;

  /**
   * Constructs a textual view.
   * @param ap the Appendable.
   * @param rd the Readable.
   * @param ticksPerSecond ticks per second.
   * @param canvas the canvas for the view.
   * @param model the read only version of the model.
   * @throws IllegalArgumentException if Appendable is null.
   * @throws IllegalArgumentException if Readable is null.
   * @throws IllegalArgumentException if the ticks per second is negative.
   * @throws IllegalArgumentException if the dimension is null.
   * @throws IllegalArgumentException if the model is null.
   * @throws IllegalArgumentException the shapes are null.
   */
  TextView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
           IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, canvas, model);
    type = ViewType.TEXT;
  }

  @Override
  public void render() {
    StringBuilder str = new StringBuilder();
    str.append("canvas " + this.model.getCanvasDimension().getWidth()
            + " "+ this.model.getCanvasDimension().getHeight() +
            this.model.getTopXY().getX() + " " + this.model.getTopXY().getY() + "\n");
    for(String key : shapes.keySet()) {
      str.append("shape " + key);
      str.append(" " + this.shapes.get(key).getType() + "\n");
      str.append(this.shapes.get(key).outputMotions(key)+"\n");
    }
    tryAppend(str.substring(0, str.toString().length() - 1));
  }

  @Override
  public ViewType getViewType() {
    return type;
  }

}
