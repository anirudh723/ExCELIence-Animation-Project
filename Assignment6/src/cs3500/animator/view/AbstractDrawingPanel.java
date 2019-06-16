package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents a drawing panel, which will extend the functionality of the JPanel.
 * Consists of the 2D list of information about the shapes at all the ticks, so it can be used
 * to draw the animation of the shapes.
 */
public abstract class AbstractDrawingPanel extends JPanel {

  protected List<ArrayList<String>> shapes = new ArrayList<>();

  /**
   * Constructs the drawing panel, which just calls the JPanel constructor.
   */
  public AbstractDrawingPanel() {
    super();
  }

  /**
   * Draw method just initializes the 2D list of shape info, and calls the repaint method to update
   * and paint the components again.
   *
   * @param shapesToDraw the 2D list of shape information, used to make the real shapes in the
   *     animation.
   */
  void draw(List<ArrayList<String>> shapesToDraw) {
    this.shapes = shapesToDraw;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!shapes.isEmpty()) {
      for (ArrayList<String> data : shapes) {
        if (data.get(data.size() - 1).contains("rect")) {
          g.setColor(new Color(
                  Integer.parseInt(data.get(4)),
                  Integer.parseInt(data.get(5)),
                  Integer.parseInt(data.get(6))));
          g.fillRect(
                  (int) (Math.round(Double.parseDouble(data.get(0)))),
                  (int) (Math.round(Double.parseDouble(data.get(1)))),
                  (int) (Math.round(Double.parseDouble(data.get(2)))),
                  (int) (Math.round(Double.parseDouble(data.get(3)))));
        } else if (data.get(data.size() - 1).contains("ellipse")) {
          g.setColor(new Color(
                  Integer.parseInt(data.get(4)),
                  Integer.parseInt(data.get(5)),
                  Integer.parseInt(data.get(6))));
          g.fillOval(
                  (int) (Math.round(Double.parseDouble(data.get(0)))),
                  (int) (Math.round(Double.parseDouble(data.get(1)))),
                  (int) (Math.round(Double.parseDouble(data.get(2)))),
                  (int) (Math.round(Double.parseDouble(data.get(3)))));
        }
      }
    } else {
      g.clearRect(0, 0, 2560, 2560);
    }
  }
}
