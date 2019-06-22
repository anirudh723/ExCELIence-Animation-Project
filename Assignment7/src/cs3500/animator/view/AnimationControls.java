package cs3500.animator.view;

import cs3500.animator.controller.Features;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box.Filler;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * initializes and sets the layout and action listeners of the animation playback controls
 * that are displayed in {@link EditorView}.
 */
public class AnimationControls extends JPanel {
  Features features;

  /**
   * Construct the AnimationControls class that initializes and sets the layout of the animation
   * playback controls, as well as adding action listeners to them.
   *
   * @param features the controller.
   */
  public AnimationControls(Features features) {
    this.features = features;
    initControls();
  }

  /**
   * Initialize all animation controls, including buttons that play, pause, restart, resume, speed
   * up, slow down, and loop the animation. Sets the layout and position of the controls.
   */
  private void initControls() {

    Color bg1 = new Color(211, 211, 211);
    setBackground(bg1);
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEtchedBorder());

    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");

    JButton restartButton = new JButton("Restart");
    JButton resumeButton = new JButton("Resume");

    JButton fasterButton = new JButton("Faster");
    JButton slowerButton = new JButton("Slower");

    JRadioButton enableLoop = new JRadioButton("Infinite loop");
    JRadioButton disableLoop = new JRadioButton("Single play");
    enableLoop.setSelected(true);
    ButtonGroup loopGroup = new ButtonGroup();
    loopGroup.add(enableLoop);
    loopGroup.add(disableLoop);

    JPanel playbackControls = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    playbackControls.setBackground(bg1);
    TitledBorder pbcOuter = BorderFactory.createTitledBorder("PLAYBACK CONTROLS");
    pbcOuter.setTitleFont(new Font(getFont().toString(), Font.BOLD, 14));
    pbcOuter.setTitleColor(new Color(0, 65, 255));
    pbcOuter.setTitleJustification(TitledBorder.CENTER);
    pbcOuter.setBorder(new LineBorder(new Color(0, 65, 255)));
    playbackControls.setBorder(pbcOuter);

    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    playbackControls.add(playButton, c);

    c.gridx = 0;
    c.gridy = 1;
    playbackControls.add(pauseButton, c);

    c.gridx = 3;
    c.gridy = 0;
    c.gridheight = GridBagConstraints.VERTICAL;
    c.gridwidth = 1;
    playbackControls.add(EditorView.separatorFactory(1), c);

    c.gridx = 4;
    c.gridy = 0;
    c.gridheight = 1;
    c.gridwidth = 2;
    playbackControls.add(restartButton, c);

    c.gridx = 4;
    c.gridy = 1;
    c.gridheight = 1;
    c.gridwidth = 2;
    playbackControls.add(resumeButton, c);

    c.gridx = 6;
    c.gridy = 0;
    c.gridheight = GridBagConstraints.VERTICAL;
    playbackControls.add(EditorView.separatorFactory(1), c);

    c.gridx = 7;
    c.gridy = 0;
    c.gridwidth = 2;
    c.gridheight = 1;
    playbackControls.add(fasterButton, c);

    c.gridy = 1;
    playbackControls.add(slowerButton, c);

    c.gridx = 108;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = GridBagConstraints.VERTICAL;
    playbackControls.add(EditorView.separatorFactory(1), c);

    c.gridx = 109;
    c.gridy = 0;
    c.gridheight = 1;
    playbackControls.add(enableLoop, c);

    c.gridy = 1;
    playbackControls.add(disableLoop, c);
    add(playbackControls, BorderLayout.LINE_START);

    add(new Filler(new Dimension(0, 15), new Dimension(0, 15),
            new Dimension(0, 15)), BorderLayout.PAGE_END);


    // adding action listeners to playback controls
    playButton.addActionListener(event -> features.start());
    restartButton.addActionListener(event -> features.restart());
    pauseButton.addActionListener(event -> features.pause());
    resumeButton.addActionListener(event -> features.resume());
    enableLoop.addActionListener(event -> features.loop());
    disableLoop.addActionListener(event -> features.loop());
    fasterButton.addActionListener(event -> features.increaseSpeed());
    slowerButton.addActionListener(event -> features.decreaseSpeed());
  }
}
