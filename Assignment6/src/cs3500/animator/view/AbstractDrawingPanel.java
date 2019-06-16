package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

abstract class AbstractDrawingPanel extends JPanel {

  private List<ArrayList<String>> shapes = new ArrayList<>();

  public AbstractDrawingPanel() {
    super();
  }

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
