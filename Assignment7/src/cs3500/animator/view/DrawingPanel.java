package cs3500.animator.view;

/**
 * Represents the drawing panel, which is just used to be able to construct a drawing panel, since
 * the abstract one cannot be constructed. In class, it was said that for drawing panel it would be
 * okay to have no interface, but we decided to at least have an abstract class to prevent complete
 * tight coupling.
 */
public class DrawingPanel extends AbstractDrawingPanel {

  /**
   * Constructs the drawing panel.
   */
  public DrawingPanel() {
    super();
  }
}
