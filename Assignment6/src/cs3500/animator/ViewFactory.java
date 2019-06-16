package cs3500.animator;

import java.awt.*;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

final class ViewFactory {
  /*
  static method in factory class that takes a parameter, returns an interface.
  for when you have multiple classes that implement the same interface,
  you want to take an input and decide which one to make.
   */
   final IView create(Appendable output, Readable input, String viewType, int speed, IReadOnlyAnimationModel model) {

    switch (viewType) {
      case "text":
        return new TextView(output, input, speed, model.getCanvasDimension(), model);
      case "svg":
        return new SVGView(output, input, speed, model.getCanvasDimension(), model);
      case "visual":
        return new VisualView(output, input, speed,  model.getCanvasDimension(), model);
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }
}
