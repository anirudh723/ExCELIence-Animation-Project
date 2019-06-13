package cs3500.animator.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * Represents a visual view.
 */
public class VisualView extends JFrame implements IView {
  private DrawingPanel panel;
  private JScrollPane scrollPane;
  private Dimension canvas;
  IController controller;


  public VisualView(Dimension canvas) {
    super();
    this.canvas = canvas;
    this.panel = new DrawingPanel();
    panel.setMinimumSize(this.canvas);//todo calculate this
    panel.setPreferredSize(new Dimension(2000, 2000));//todo calculate this
    scrollPane = new JScrollPane(panel);
    setSize(1000,1000);//todo figure this out
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200, 200);
    add(scrollPane);

    setVisible(true);
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

}
