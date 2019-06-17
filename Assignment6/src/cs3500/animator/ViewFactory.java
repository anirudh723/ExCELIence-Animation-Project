package cs3500.animator;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * Factory class that creates an IView based on a view type parameter.
 */
final class ViewFactory {
  /**
   * Factory method that creates the appropriate IView based on input parameters.
   *
   * @param output   the output destination.
   * @param input    the input source.
   * @param viewType the type of view, as a string. "text" for TextView, "visual" for VisualView,
   *                 and "svg" for SVGView.
   * @param speed    the number of ticks per second the animation should play at.
   * @param model    the animation model.
   * @return a new IView.
   * @throws IllegalArgumentException if the given view type does not correspond to an current IView
   *                                  implementation.
   */
  final IView create(Appendable output, Readable input, String viewType, int speed,
                     IReadOnlyAnimationModel model) throws IllegalArgumentException {
    switch (viewType) {
      case "text":
        return new TextView(output, input, speed, model);
      case "svg":
        return new SVGView(output, input, speed, model);
      case "visual":
        return new VisualView(output, input, speed, model);
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }
}
