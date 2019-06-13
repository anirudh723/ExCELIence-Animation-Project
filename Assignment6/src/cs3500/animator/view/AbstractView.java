package cs3500.animator.view;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * Represents an abstracted version of the View.
 */
public abstract class AbstractView implements IView {
  protected Appendable in;
  protected Readable rd;
  protected int ticksPerSecond;
  protected Dimension canvas;
  protected LinkedHashMap<String, IAnimatableShapeReadOnly> shapes;
  protected IReadOnlyAnimationModel model;


  /**
   * Constructs an Abstract View.
   *
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
  AbstractView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
               LinkedHashMap<String, IAnimatableShapeReadOnly> shapes, IReadOnlyAnimationModel model) {
    if (ap == null) {
      throw new IllegalArgumentException("Appendable is null.");
    }
    this.in = ap;
    if (rd == null) {
      throw new IllegalArgumentException("Readable is null.");
    }
    this.rd = rd;
    if (ticksPerSecond < 0) {
      throw new IllegalArgumentException("Ticks per second cannot be negative.");
    }
    this.ticksPerSecond = ticksPerSecond;
    if (canvas == null) {
      throw new IllegalArgumentException("Dimensions are null.");
    }
    this.canvas = canvas;
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.model = model;
    if (shapes == null) {
      throw new IllegalArgumentException("Map of shapes is null");
    }
    this.shapes = shapes;
    this.shapes = model.getShapeMap();
  }

  @Override
  public void tweening() {

  }

  @Override
  public abstract void render();

  @Override
  public void tryAppend(String ... lines) throws IllegalArgumentException {
    try {
      for (String s : lines) {
        in.append(s);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot append to Appendable.");
    }

  }
}
