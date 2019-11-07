package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyAnimationModel;


/**
 * Represents an animation as a textual description of the animation that specifies shapes, colors,
 * movement, transformations, and ticks that serve as a unitless measurement for duration. Works on
 * a variety of output sources.
 */
public class TextView extends AbstractView {

  /**
   * Constructs a textual view.
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
  public TextView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, model);
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
  public void render() {
    StringBuilder str = new StringBuilder();
    str.append("canvas "
            + (int) this.model.getTopXY().getX() + " "
            + (int) this.model.getTopXY().getY() + " "
            + (int) this.model.getCanvasDimension().getWidth() + " "
            + (int) this.model.getCanvasDimension().getHeight() + "\n");
    for (String key : shapes.keySet()) {
      str.append("shape " + key + " " + this.shapes.get(key).getType() + "\n");
      str.append(this.shapes.get(key).outputMotions(key));
    }
    tryAppend(str.substring(0, str.toString().length() - 1));
  }


  /**
   * Gets the {@link ViewType} of the view. Can be TEXT, SVG, VISUAL, or EDIT
   *
   * @return the view type of the view.
   */
  @Override
  public ViewType getViewType() {
    return ViewType.TEXT;
  }
}
