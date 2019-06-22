package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IReadOnlyAnimationModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

/**
 * Produced a GUI of the animation, complete with controls to change how the animation is played
 * (changing speed, looping, playing, pausing, etc), as well as the ability for the user to edit the
 * animation by adding and removing shapes, and adding, removing, and editing keyframes. This view
 * uses the animation rendering provided by another view, {@link VisualView}.
 */
public class EditorView extends AbstractView {
  IView delegateView;
  IReadOnlyAnimationModel model;
  Features features;
  JFrame delegate;
  JPanel animationControls;
  JTabbedPane editorTabs;

  /**
   * Constructs an Editor View. Sets the sizes of the frame, ticks per second, the view to get the
   * rendered animation from, and other specifications.
   *
   * @param ap             the file to append to.
   * @param rd             the file to read from.
   * @param ticksPerSecond the ticks per second of the animation
   * @param model          the model the use for this view.
   * @param view           the view to provide the animation rendering for this view.
   * @param features       the controller for this view.
   */
  public EditorView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model,
                    VisualView view, Features features) {
    super(ap, rd, ticksPerSecond, model);
    this.model = model;
    this.features = features;
    this.delegateView = view;
    this.delegate = view.getFrame();
    this.animationControls = new AnimationControls(this.features);
    this.editorTabs = new EditorTabs(this.features, this.model);
    initGUI();
  }

  /**
   * Initialize elements of this view's GUI such as the menu, controls, layout, size, and tabs.
   */
  private void initGUI() {
    delegate.setTitle("ExCELlence Animator");

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);
    delegate.setJMenuBar(menuBar);

    delegate.add(animationControls, BorderLayout.NORTH);
    delegate.add(editorTabs, BorderLayout.WEST);
    delegate.setMinimumSize(new Dimension(1000, 800));
    delegate.setVisible(true);
  }

  /**
   * Factory class to create line separators.
   *
   * @param orientation the orientation of the separator.
   * @return the line separator.
   */
  public static JSeparator separatorFactory(int orientation) {
    JSeparator result = new JSeparator(orientation);
    result.setBackground(Color.gray);
    return result;
  }

  /**
   * Renders the GUI shapes using tweening to calculate the steps in between keyframes.
   *
   * @throws UnsupportedOperationException for views that do not have a GUI rendering, such as
   *                                       {@link TextView} and {@link SVGView}.
   */
  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToDraw) {
    this.delegateView.renderGUIShapes(shapesToDraw);
  }

  /**
   * Renders the animations into a view. This is only supported by views that do not have visual
   * elements and do not show the animation in a window. Not supported for this view.
   *
   * @throws UnsupportedOperationException for views that render the shapes in a window with Java
   *                                       Swing, and therefore require a list of information about
   *                                       the shapes at every interval between keyframes.
   */
  @Override
  public void render() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can not render in this IView implementation");
  }

  /**
   * Gets the {@link ViewType} of the view. Can be TEXT, SVG, VISUAL, or EDIT
   *
   * @return the view type of the view.
   */
  @Override
  public ViewType getViewType() {
    return ViewType.EDITOR;
  }

}
