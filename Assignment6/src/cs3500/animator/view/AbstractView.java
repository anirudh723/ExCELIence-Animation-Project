package cs3500.animator.view;

import java.awt.Dimension;
import java.util.List;

public abstract class AbstractView implements IView {
  protected Appendable in;
  protected Readable out;
  protected int ticksPerSecond;
  protected Dimension canvas;
  protected List<IViewShape> shapes;

  AbstractView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas, List<IViewShape> shapes) {
    if (ap == null) {
      throw new IllegalArgumentException("Appendable is null.");
    }
    this.in = ap;
    if (rd == null) {
      throw new IllegalArgumentException("Readable is null.");
    }
    this.out = rd;
    this.ticksPerSecond = ticksPerSecond;
    if (canvas == null) {
      throw new IllegalArgumentException("Dimensions are null.");
    }
    this.canvas = canvas;
    this.shapes = shapes;
  }

  @Override
  public void tweening() {

  }

  @Override
  public abstract void render();

}
