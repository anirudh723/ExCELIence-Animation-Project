package cs3500.animator.view;

import com.intellij.ui.components.JBScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A GUI that draws and plays an animation in a window, using Java Swing.
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
    JScrollPane scrollPane = new JBScrollPane(panel);
    scrollPane.setSize(this.canvas);
    delegate.setLayout(new BorderLayout());
    delegate.setSize(scrollPane.getWidth(), scrollPane.getHeight());
    delegate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    delegate.setLocationRelativeTo(null);
    delegate.add(scrollPane);
    delegate.pack();
    delegate.setVisible(true);
  }

  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToDraw) {
    panel.draw(shapesToDraw);

  }

  @Override
  public void render() {
    throw new UnsupportedOperationException("Can not render in this IView implementation");
  }

  private Dimension calculateMaxDimension() {
    int furthestX = 0;
    int furthestY = 0;
    int furthestWidth = 0;
    int furthestHeight = 0;
    for (IAnimatableShapeReadOnly shape : this.model.getShapeMap().values()) {
      for (IMotion motion : shape.getMotions()) {
        if (motion.getPosition().getX() > furthestX) {
          furthestX = (int) ((motion.getPosition().getX()));
        }
        if (motion.getPosition().getY() > furthestY) {
          furthestY = (int) ((motion.getPosition().getY()));
        }
        if (motion.getDimension().getWidth() > furthestWidth) {
          furthestWidth = (int) motion.getDimension().getWidth();
        }
        if (motion.getDimension().getHeight() > furthestHeight) {
          furthestHeight = (int) motion.getDimension().getHeight();
        }
      }
    }
    return new Dimension(furthestX + furthestWidth, furthestY + furthestHeight);
  }

  @Override
  public ViewType getViewType() {
    return ViewType.VISUAL;
  }

  @Override
  public JFrame getFrame() {
    return this.delegate;
  }

  @Override
  public DrawingPanel getPanel() {
    return this.panel;
  }

  public IReadOnlyAnimationModel getModel() {
    return this.model;
  }
}
