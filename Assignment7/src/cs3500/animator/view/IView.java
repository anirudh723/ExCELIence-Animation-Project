package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents the functionality of any type of View. These functions include rendering it in their
 * specific way, a helper to append to appendable, some getters, etc.
 */
public interface IView {

  /**
   * Renders the animations into a view.
   */
  void render();

  /**
   * Renders the GUI shapes.
   */
  void renderGUIShapes(List<ArrayList<String>> shapesToRender);

  /**
   * Appends the given lines to the Appendable.
   *
   * @param lines the String's to be appended.
   * @throws IllegalArgumentException if it cannot append to the Appendable.
   */
  void tryAppend(String... lines) throws IllegalArgumentException;


  ViewType getViewType();

  int getTicksPerSecond();

  /**
   * Returns the drawing panel which will be used to create the editor view.
   * @return the drawing panel.
   * @throws UnsupportedOperationException if it's trying to return a panel from a svg or text view
   *     since they don't have panels.
   */
  JFrame getFrame() throws UnsupportedOperationException;

  /**
   * Returns the drawing panel which will be used to create the editor view.
   * @return the drawing panel.
   * @throws UnsupportedOperationException if it's trying to return a panel from a svg or text view
   *     since they don't have panels.
   */
  DrawingPanel getPanel() throws UnsupportedOperationException;
}
