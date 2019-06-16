package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShape;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Represents an animation as a textual description of the animation that specifies shapes, colors,
 * movement, transformations, and ticks that serve as a unitless measurement for duration.
 * Works on a variety of output sources.
 */
public class TextView extends AbstractView {
  private ViewType type;

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
  public TextView(Appendable ap, Readable rd, int ticksPerSecond,
                  Dimension canvas, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, canvas, model);
    type = ViewType.TEXT;
  }

  @Override
  public void render() {
    // Initialize resulting textual description
    StringBuilder resultText = new StringBuilder();
    // Add and format canvas information
    resultText.append("canvas ");
    resultText.append(model.getTopXY().x);
    resultText.append(" ");
    resultText.append(model.getTopXY().y);
    resultText.append(" ");
    resultText.append(model.getCanvasDimension().width);
    resultText.append(" ");
    resultText.append(model.getCanvasDimension().height);
    // shape description:
    for (String k : shapes.keySet()) {
      resultText.append(System.lineSeparator());
      resultText.append("shape");
      resultText.append(" ");
      resultText.append(k);
      resultText.append(" ");
      resultText.append(this.shapes.get(k).getType());
      // movement description:
      List<IMotion> shapesMotions = this.shapes.get(k).getMotions();
      for (IMotion m : shapesMotions) {
        resultText.append(System.lineSeparator());
        resultText.append("motion");
        resultText.append(" ");
        resultText.append(k);
        resultText.append(" ");
        resultText.append(m.writeMotion());
      }
    }
    tryAppend(resultText.toString());
  }

//    StringBuilder str = new StringBuilder();
//    str.append("canvas "
//            + (int)this.model.getCanvasDimension().getWidth() + " "
//            + (int)this.model.getCanvasDimension().getHeight() + " "
//            + (int)this.model.getTopXY().getX() + " "
//            + (int)this.model.getTopXY().getY() + "\n");
//    for(String key : shapes.keySet()) {
//      str.append("shape " + key);
//      str.append(" " + this.shapes.get(key).getType() + "\n");
//      str.append(this.shapes.get(key).outputMotions(key)+"\n");
//    }
//    tryAppend(str.substring(0, str.toString().length() - 1));
//  }

  @Override
  public ViewType getViewType() {
    return type;
  }

}
