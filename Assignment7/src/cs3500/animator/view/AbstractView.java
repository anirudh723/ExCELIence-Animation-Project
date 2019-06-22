package cs3500.animator.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

import javax.swing.JFrame;

/**
 * Represents an abstracted version of the View. Contains a Read Only model and list of Read Only
 * shapes from the model so that the view can render the shapes as text, in SVG format, or in a
 * visual format on a {@link DrawingPanel}.
 */
public abstract class AbstractView implements IView {
  protected Appendable ap;
  protected Readable rd;
  protected int ticksPerSecond;
  protected Dimension canvas;
  protected LinkedHashMap<String, IAnimatableShapeReadOnly> shapes;
  protected IReadOnlyAnimationModel model;
  protected ViewType type;


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

  /**
   * Renders the animations into a view. This is only supported by views that do not have visual
   * elements and do not show the animation in a window.
   *
   * @throws UnsupportedOperationException for views that render the shapes in a window with Java
   *                                       Swing, and therefore require a list of information about
   *                                       the shapes at every interval between keyframes.
   */
  @Override
  public abstract void render() throws UnsupportedOperationException;


  /**
   * Renders the GUI shapes using tweening to calculate the steps in between keyframes.
   *
   * @throws UnsupportedOperationException for views that do not have a GUI rendering, such as
   *                                       {@link TextView} and {@link SVGView}.
   */
  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToRender)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot render GUI shapes in this IView.");
  }


  /**
   * Appends the given strings to the Appendable.
   *
   * @param lines the Strings to be appended.
   * @throws IllegalArgumentException if the given strings cannot be appended to the Appendable.
   */
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

  /**
   * Get the ticks per second of the view.
   *
   * @return the ticks per second of the view.
   */
  @Override
  public int getTicksPerSecond() {
    return ticksPerSecond;
  }

  /**
   * Gets the {@link ViewType} of the view. Can be TEXT, SVG, VISUAL, or EDIT
   *
   * @return the view type of the view.
   */
  @Override
  public abstract ViewType getViewType();


  /**
   * Returns the JFrame which will be used to create the visual and editor views.
   *
   * @return the JFrame.
   * @throws UnsupportedOperationException if called to return a frame from an SVGView or TextView
   *                                       since they do not have visual rendering capability and
   *                                       therefore do not incorporate the use of a JFrame.
   */
  @Override
  public JFrame getFrame() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot return a frame here.");
  }
}
