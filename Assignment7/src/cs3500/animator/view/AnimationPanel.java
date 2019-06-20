package cs3500.animator.view;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class AnimationPanel extends JScrollPane {
  public AnimationPanel() {
    initAP();
  }

  private void initAP() {
    setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

  }
}
