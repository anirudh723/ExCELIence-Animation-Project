package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

abstract class AbstractDrawingPanel extends JPanel {

  List<ArrayList<String>> shapes;

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
    if (shapes != null) {
      for (ArrayList<String> data : shapes) {
        if(data.get(data.size()-1).contains("rect")){
          g.setColor(new Color(Integer.parseInt(data.get(4)),
                  Integer.parseInt(data.get(5)),
                  Integer.parseInt(data.get(6))));
          g.fillRect(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)),
                  Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)));
        } else if (data.get(data.size()-1).contains("ellipse")) {
          g.setColor(new Color(Integer.parseInt(data.get(4)),
                  Integer.parseInt(data.get(5)),
                  Integer.parseInt(data.get(6))));
          g.fillOval(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)),
                  Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)));
        }
      }
    }
  }
// x y w h r g b "type"

}
