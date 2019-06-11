package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShape;
import cs3500.animator.model.IAnimatableShapeReadOnly;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

public class TextView extends AbstractView {

  TextView(Appendable in, Readable out, int ticksPerSecond, Dimension canvas,
           LinkedHashMap<String, IAnimatableShapeReadOnly> shapes) {
    super(in, out, ticksPerSecond, canvas, shapes);
  }

  @Override
  public void render() {
    StringBuilder str = new StringBuilder();
    for(String key : shapes.keySet()) {
      str.append("shape " + key);
      str.append(" " + this.shapes.get(key).getType() + "\n");
      str.append(this.shapes.get(key).outputMotions(key)+"\n");
    }
    tryAppend(str.substring(0, str.toString().length() - 1));
  }
}
