package cs3500.animator.view;

import cs3500.animator.controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class AnimationControls extends JPanel {
  Features features;
  public AnimationControls(Features features) {
    this.features = features;
    initControls();
  }

  private void initControls() {

    Color bg1 = new Color(211,211,211);
    setBackground(bg1);
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEtchedBorder());

    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");

    JButton restartButton = new JButton("Restart");
    JButton resumeButton = new JButton("Resume");
    //JLabel fromLabel = new JLabel("From:");
    //JTextField restartFrom = new JTextField("0",3);
    //restartFrom.setHorizontalAlignment(SwingConstants.RIGHT);

    JButton fasterButton = new JButton("Faster");
    JButton slowerButton = new JButton("Slower");
//    JSlider ticksPerSec = new JSlider(SwingConstants.HORIZONTAL, 0, 50, 25);
//    ticksPerSec.createStandardLabels(1);
//    ticksPerSec.setMajorTickSpacing(10);
//    ticksPerSec.setMinorTickSpacing(1);
//    ticksPerSec.setPaintTicks(true);
//    ticksPerSec.setPaintLabels(true);

    JRadioButton enableLoop = new JRadioButton("Infinite loop");
    JRadioButton disableLoop = new JRadioButton("Single play");
    enableLoop.setSelected(true);
    ButtonGroup loopGroup = new ButtonGroup();
    loopGroup.add(enableLoop);
    loopGroup.add(disableLoop);

//    JLabel currentTickNum = new JLabel("Current Tick");
//    currentTickNum.setHorizontalAlignment(SwingConstants.CENTER);
//    tickProgress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
//    tickProgress.setStringPainted(true);
//    tickProgress.setString(tickProgress1 + "%");

    JPanel playbackControls = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    playbackControls.setBackground(bg1);
    TitledBorder pbcOuter = BorderFactory.createTitledBorder("PLAYBACK CONTROLS");
    pbcOuter.setTitleFont(new Font(getFont().toString(), Font.BOLD, 14));
    pbcOuter.setTitleColor(new Color(0,65,255));
    pbcOuter.setTitleJustification(TitledBorder.CENTER);
    pbcOuter.setBorder(new LineBorder(new Color(0,65,255)));
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

//    c.gridy = 1;
//    c.gridwidth = 1;
//    playbackControls.add(fromLabel, c);

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

//    c.gridx = 54;
//    c.gridy = 0;
//    c.gridwidth =1;
//    c.gridheight = 2;
//    playbackControls.add(ticksPerSec, c);

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


//    JPanel tickClock = new JPanel(new GridLayout(2, 1));
//    TitledBorder clockBorder = BorderFactory.createTitledBorder("CLOCK");
//    clockBorder.setTitleFont(new Font(getFont().toString(), Font.BOLD, 14));
//    clockBorder.setTitleColor(new Color(0,65,255));
//    clockBorder.setTitleJustification(TitledBorder.CENTER);
//    clockBorder.setBorder(new LineBorder(new Color(0,65,255)));
//    tickClock.setBackground(bg1);
//    tickClock.setBorder(clockBorder);
//    tickClock.add(currentTickNum);
//    tickClock.add(tickProgress);
//    add(tickClock, BorderLayout.LINE_END);

    add(new Filler(new Dimension(0,15), new Dimension(0,15),
        new Dimension(0,15)), BorderLayout.PAGE_END);



    ////////////////////////////////Ani's Work//////////////////////////////////////
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
