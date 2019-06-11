package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShape;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

public class TextView extends AbstractView {

  TextView(Appendable in, Readable out, int ticksPerSecond, Dimension canvas,
           LinkedHashMap<String, IAnimatableShapeReadOnly> shapes, IReadOnlyAnimationModel model) {
    super(in, out, ticksPerSecond, canvas, shapes, model);
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
    System.out.println(in.toString());//todo remove
  }

}
