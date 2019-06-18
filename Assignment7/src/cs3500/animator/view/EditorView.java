package cs3500.animator.view;

import cs3500.animator.controller.ICommand;
import cs3500.animator.controller.RewindCommand;
import cs3500.animator.controller.StartCommand;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EditorView extends AbstractView {
  IView visualView;
  ViewType type;
  DrawingPanel visualPanel;
  DrawingPanel editorPanel;
  JButton startButton;
  JButton rewindButton;
  JButton pauseButton;
  JButton resumeButton;
  JButton loopButton;
  JButton speedButton;
  private JFrame delegate;

  /**
   * Constructs an Editor View.
   *
   * @param visualView the view component of this editor view.
   * @throws UnsupportedOperationException if the given view to build on is not a visual view.
   */
  public EditorView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model,
      IView visualView) {
    super(ap, rd, ticksPerSecond, model);
    type = ViewType.EDITOR;
    if (visualView.getViewType() != ViewType.VISUAL) {
      throw new UnsupportedOperationException("Editor View only supports Visual Views");
    }
    this.visualView = visualView;
    delegate = this.visualView.getFrame();
//    delegate.setLayout(new BorderLayout());
//    delegate.setPreferredSize(new Dimension(1000, 1000));
//    delegate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    delegate.setBackground(Color.red);

    this.editorPanel = new DrawingPanel();
    this.editorPanel.setBackground(Color.yellow);
    //this.editorPanel.setMinimumSize(new Dimension(500, 500));
   // this.editorPanel.setPreferredSize(new Dimension(500, 500));


    ICommand startCommand = new StartCommand();
    ICommand rewindCommand = new RewindCommand();

    startButton = new JButton("Start");
    rewindButton = new JButton("Rewind");
    pauseButton = new JButton("Pause");
    resumeButton = new JButton("Resume");
    loopButton = new JButton("Loop");
    speedButton = new JButton("Speed");

    startButton.addActionListener(startCommand);
    rewindButton.addActionListener(rewindCommand);

    this.editorPanel.add(startButton);
    this.editorPanel.add(rewindButton);
    this.editorPanel.add(pauseButton);
    this.editorPanel.add(resumeButton);
    this.editorPanel.add(loopButton);
    this.editorPanel.add(speedButton);
    this.visualPanel = this.visualView.getPanel();
    this.visualPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    this.delegate.add(this.visualPanel, BorderLayout.CENTER);
    this.delegate.add(editorPanel, BorderLayout.SOUTH);
    this.delegate.pack();
    this.delegate.setVisible(true);
  }

  @Override
  public void render() {
    this.visualView.render();
  }

  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToRender) {
    this.visualView.renderGUIShapes(shapesToRender);
  }
}
