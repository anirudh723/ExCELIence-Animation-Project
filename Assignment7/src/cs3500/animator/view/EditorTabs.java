package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IReadOnlyAnimationModel;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

/**
 * Provides a heading for panel by extending JTabbedPane.
 */
public class EditorTabs extends JTabbedPane {
  Features features;
  IReadOnlyAnimationModel model;

  /**
   * Constructs the editor tab for the panel and sets the layout.
   *
   * @param features the controller.
   * @param model    the read only model.
   */
  public EditorTabs(Features features, IReadOnlyAnimationModel model) {
    this.features = features;
    this.model = model;

    JComponent panel1 = new ShapeEditorTab(this.features, this.model);
    addTab("Animation Editor", panel1);
    setMnemonicAt(0, KeyEvent.VK_1);

    setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

  }
}
