package cs3500.animator.view;

import java.awt.Dimension;
import java.util.List;

public class SVGView extends AbstractView {

  SVGView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
      List<IViewShape> shapes) {
    super(ap, rd, ticksPerSecond, canvas, shapes);
  }

  @Override
  public void render() {

  }
}
