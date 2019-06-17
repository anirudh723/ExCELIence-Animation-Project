package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

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

}
