package cs3500.animator;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.view.EditorView;
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
                     AnimationModel model) throws IllegalArgumentException {
    IView visualView = new VisualView(output, input, speed, new ReadOnlyAnimationModel(model));
    switch (viewType) {
      case "text":
        return new TextView(output, input, speed, new ReadOnlyAnimationModel(model));
      case "svg":
        return new SVGView(output, input, speed, new ReadOnlyAnimationModel(model));
      case "visual":
        return visualView;
      case "edit":
        return new EditorView(output, input, speed, new ReadOnlyAnimationModel(model), visualView,
            new Controller(model, visualView));
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }
}
