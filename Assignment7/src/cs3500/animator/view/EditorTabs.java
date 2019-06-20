package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EditorTabs extends JTabbedPane {
  Features features;
  IReadOnlyAnimationModel model;

  public EditorTabs(Features features, IReadOnlyAnimationModel model) {
    this.features = features;
    this.model = model;

    JComponent panel1 = new ShapeEditorTab(this.features, this.model);
    addTab("Animation Editor", panel1);
    setMnemonicAt(0, KeyEvent.VK_1);

    setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    setMinimumSize(new Dimension(275, 600));
    setPreferredSize(new Dimension(275, 600));
    setMaximumSize(new Dimension(275, 600));

  }
}
