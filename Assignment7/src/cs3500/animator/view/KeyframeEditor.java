package cs3500.animator.view;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class KeyframeEditor extends JPanel {

  public KeyframeEditor() {

    TitledBorder border = new TitledBorder("KEYFRAME EDITOR");
    border.setTitleFont(new Font(this.getFont().toString(), Font.BOLD, 12));
    setBorder(border);

  }

}
