package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Represents the functionality of any type of View. These functions include rendering it in their
 * specific way, a helper to append to appendable, some getters, etc. Views can be rendered as text,
 * SVG code, visually rendered in a window, and rendered in a window the provides functionality to
 * edit an animation.
 */
public interface IView {

  /**
   * Renders the animations into a view. This is only supported by views that do not have visual
   * elements and do not show the animation in a window.
   *
   * @throws UnsupportedOperationException for views that render the shapes in a window with Java
   *                                       Swing, and therefore require a list of information about
   *                                       the shapes at every interval between keyframes.
   */
  void render() throws UnsupportedOperationException;

  /**
   * Renders the GUI shapes using tweening to calculate the steps in between keyframes.
   *
   * @throws UnsupportedOperationException for views that do not have a GUI rendering, such as
   *                                       {@link TextView} and {@link SVGView}.
   */
  void renderGUIShapes(List<ArrayList<String>> shapesToRender) throws UnsupportedOperationException;

  /**
   * Appends the given strings to the Appendable.
   *
   * @param lines the Strings to be appended.
   * @throws IllegalArgumentException if the given strings cannot be appended to the Appendable.
   */
  void tryAppend(String... lines) throws IllegalArgumentException;


  /**
   * Gets the {@link ViewType} of the view. Can be TEXT, SVG, VISUAL, or EDIT
   *
   * @return the view type of the view.
   */
  ViewType getViewType();

  /**
   * Get the ticks per second of the view.
   *
   * @return the ticks per second of the view.
   */
  int getTicksPerSecond();

  /**
   * Returns the JFrame which will be used to create the visual and editor views.
   *
   * @return the JFrame.
   * @throws UnsupportedOperationException if called to return a frame from an SVGView or TextView
   *                                       since they do not have visual rendering capability and
   *                                       therefore do not incorporate the use of a JFrame.
   */
  JFrame getFrame() throws UnsupportedOperationException;

}
