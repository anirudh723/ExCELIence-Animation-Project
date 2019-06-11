package cs3500.animator.view;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

public abstract class AbstractView implements IView {
  protected Appendable in;
  protected Readable out;
  protected int ticksPerSecond;
  protected Dimension canvas;
  protected LinkedHashMap<String, IAnimatableShapeReadOnly> shapes;
  protected IReadOnlyAnimationModel model;


  AbstractView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
               LinkedHashMap<String, IAnimatableShapeReadOnly> shapes, IReadOnlyAnimationModel model) {
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
    this.model = model;
    this.shapes = model.getShapeMap();
  }

  @Override
  public void tweening() {

  }

  @Override
  public abstract void render();

  @Override
  public void tryAppend(String ... lines) {
    try {
      for (String s : lines) {
        in.append(s);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot append to Appendable.");
    }

  }
}
