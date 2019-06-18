package cs3500.animator.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents an abstracted version of the View.
 */
public abstract class AbstractView implements IView {
  protected Appendable ap;
  protected Readable rd;
  protected int ticksPerSecond;
  protected Dimension canvas;
  protected LinkedHashMap<String, IAnimatableShapeReadOnly> shapes;
  protected IReadOnlyAnimationModel model;
  protected ViewType type = ViewType.EDITOR;


  /**
   * Constructs an Abstract View.
   *
   * @param ap             the Appendable.
   * @param rd             the Readable.
   * @param ticksPerSecond ticks per second.
   * @param model          the read only version of the model.
   * @throws IllegalArgumentException if Appendable is null.
   * @throws IllegalArgumentException if Readable is null.
   * @throws IllegalArgumentException if the ticks per second is negative.
   * @throws IllegalArgumentException if the dimension is null.
   * @throws IllegalArgumentException if the model is null.
   * @throws IllegalArgumentException the shapes are null.
   */
  AbstractView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model) {
    if (ap == null) {
      throw new IllegalArgumentException("Appendable is null.");
    }
    this.ap = ap;
    if (rd == null) {
      throw new IllegalArgumentException("Readable is null.");
    }
    this.rd = rd;
    if (ticksPerSecond < 0) {
      throw new IllegalArgumentException("Ticks per second cannot be negative.");
    }
    this.ticksPerSecond = ticksPerSecond;

    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.model = model;
    this.canvas = new Dimension(
            (int) (this.model.getCanvasDimension().getWidth()
                    + this.model.getTopXY().getX()),
            (int) (this.model.getCanvasDimension().getHeight()
                    + this.model.getTopXY().getY()));

    this.shapes = model.getShapeMap();
  }


  @Override
  public abstract void render();

  @Override
  public void tryAppend(String... lines) throws IllegalArgumentException {
    try {
      for (String s : lines) {
        this.ap.append(s);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot append to Appendable.");
    }
  }

  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToRender) {
    throw new UnsupportedOperationException("Cannot render GUI shapes in this IView.");
  }

  @Override
  public int getTicksPerSecond() {
    return ticksPerSecond;
  }

  @Override
  public ViewType getViewType() {
    return this.type;
  }

  @Override
  public DrawingPanel getPanel() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot return a panel here.");
  }

  @Override
  public JFrame getFrame() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot return a panel here.");
  }
}
