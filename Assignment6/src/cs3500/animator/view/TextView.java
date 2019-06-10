package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShape;
import java.awt.Dimension;
import java.util.List;

public class TextView extends AbstractView {

  TextView(Appendable in, Readable out, int ticksPerSecond, Dimension canvas,
      List<IViewShape> shapes) {
    super(in, out, ticksPerSecond, canvas, shapes);
  }

  @Override
  public void render() {
    StringBuilder str = new StringBuilder();
    str.append("canvas " + (int) canvas.getWidth() + (int) canvas.getHeight());
    for (IAnimatableShape shape : shapes.values()) {
      str.append("shape " + shape.getName() + " " + shape.getType() + "\n");
      str.append(shape.outputMotions());
    }
    //in.append(str.substring(0, str.toString().length() - 1));
  }
}
