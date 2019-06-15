package cs3500.animator.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * A GUI that draws and plays an animation in a window, using Java Swing.
 */
public class VisualView extends AbstractView implements IView {
  private DrawingPanel panel;
  private JScrollPane scrollPane;
  ViewType type;

  JFrame delegate = new JFrame();

  /**
   * Constructs a Visual View
   * @param ap the file to append to.
   * @param rd the file to read from.
   * @param ticksPerSecond the ticks per second rate of the animation.
   * @param canvas the dimensions of the animation window.
   * @param model
   */
  public VisualView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
                    IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, canvas, model);
    type = ViewType.VISUAL;
    this.panel = new DrawingPanel();

    panel.setMinimumSize(this.canvas);
    panel.setPreferredSize(calculateMaxDimension());
    scrollPane = new JScrollPane(panel);
    delegate.setSize(this.canvas);
    delegate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    delegate.setLocation(250, 250);
    delegate.add(scrollPane);

    delegate.setVisible(true);
  }


  public void renderGUIShapes(List<ArrayList<String>> shapesToDraw) {
    panel.draw(shapesToDraw);

  }

  @Override
  public void render() {
    throw new UnsupportedOperationException("Can not render in this IView implementation");
  }

  @Override
  public void tryAppend(String... lines) throws IllegalArgumentException {

  }

  @Override
  public ViewType getViewType() {
    return type;
  }


  private Dimension calculateMaxDimension() {
    int furthestRight = 0;
    int furthestDown = 0;
    for (IAnimatableShapeReadOnly shape : this.model.getShapeMap().values()) {
      for (IMotion motion : shape.getMotions()) {
        if (motion.getPosition().getX() > furthestRight) {
          furthestRight = (int) (motion.getPosition().getX());
        }
        if (motion.getPosition().getY() > furthestDown) {
          furthestDown = (int) (motion.getPosition().getY());
        }
      }
    }
    System.out.println(furthestRight + " by " + furthestDown);
    return new Dimension(furthestRight, furthestDown);
  }

}
