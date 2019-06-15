package cs3500.animator;

import java.awt.*;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

public class ViewFactory {
  /*
  static method in factory class that takes a parameter, returns an interface.
  for when you have multiple classes that implement the same interface,
  you want to take an input and decide which one to make.
   */
  public static IView create(Readable input, Appendable output, String viewType, int speed) {


    switch (viewType) {
      case "text":
        return new TextView(input, output, speed, dimension, model);
      case "svg":
        return new SVGView(input, output, speed, dimension, model);
      case "visual":
        return new VisualView(input, output, speed, dimension, model);
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }
}
