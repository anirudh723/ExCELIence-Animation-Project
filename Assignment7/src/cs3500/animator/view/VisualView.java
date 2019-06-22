package cs3500.animator.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IReadOnlyAnimationModel;


import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * A GUI that renders and plays an animation in a window, using Java Swing.
 */
public class VisualView extends AbstractView implements IView {

  private DrawingPanel panel;
  private JFrame delegate = new JFrame();

  /**
   * Constructs a Visual View. Sets the sizes of the frame and other specifications.
   *
   * @param ap             the file to append to.
   * @param rd             the file to read from.
   * @param ticksPerSecond the ticks per second rate of the animation.
   * @param model          the read only version of the model.
   * @throws IllegalArgumentException if Appendable is null.
   * @throws IllegalArgumentException if Readable is null.
   * @throws IllegalArgumentException if the ticks per second is negative.
   * @throws IllegalArgumentException if the dimension is null.
   * @throws IllegalArgumentException if the model is null.
   * @throws IllegalArgumentException the shapes are null.
   */
  public VisualView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, model);
    this.panel = new DrawingPanel();
    panel.setMinimumSize(canvas);
    panel.setPreferredSize(canvas);
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setSize(this.canvas);
    delegate.setLayout(new BorderLayout());
    delegate.setSize(scrollPane.getWidth(), scrollPane.getHeight());
    delegate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    delegate.setLocationRelativeTo(null);
    delegate.add(scrollPane);
    delegate.pack();
  }

  /**
   * Renders the GUI shapes using tweening to calculate the steps in between keyframes.
   *
   * @throws UnsupportedOperationException for views that do not have a GUI rendering, such as
   *                                       {@link TextView} and {@link SVGView}.
   */
  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToDraw) {
    delegate.setVisible(true);
    panel.draw(shapesToDraw);

  }

  /**
   * Renders the animations into a view. This is only supported by views that do not have visual
   * elements and do not show the animation in a window. Not supported by this view.
   *
   * @throws UnsupportedOperationException for views that render the shapes in a window with Java
   *                                       Swing, and therefore require a list of information about
   *                                       the shapes at every interval between keyframes.
   */
  @Override
  public void render() {
    throw new UnsupportedOperationException("Can not render in this IView implementation");
  }

  /**
   * Gets the {@link ViewType} of the view. Can be TEXT, SVG, VISUAL, or EDIT
   *
   * @return the view type of the view.
   */
  @Override
  public ViewType getViewType() {
    return ViewType.VISUAL;
  }

  /**
   * Returns the JFrame which will be used to create the visual and editor views.
   *
   * @return the JFrame.
   * @throws UnsupportedOperationException if called to return a frame from an SVGView or TextView
   *                                       since they do not have visual rendering capability and
   *                                       therefore do not incorporate the use of a JFrame.
   */
  @Override
  public JFrame getFrame() {
    return this.delegate;
  }

}
