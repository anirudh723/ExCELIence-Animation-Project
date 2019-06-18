package cs3500.animator.view;

import cs3500.animator.controller.ICommand;
import cs3500.animator.controller.LoopCommand;
import cs3500.animator.controller.PauseCommand;
import cs3500.animator.controller.RestartCommand;
import cs3500.animator.controller.ResumeCommand;
import cs3500.animator.controller.RewindCommand;
import cs3500.animator.controller.SpeedCommand;
import cs3500.animator.controller.StartCommand;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EditorView extends AbstractView {
  private IView visualView;
  private DrawingPanel visualPanel;
  private DrawingPanel editorPanel;
  private JButton startButton;
  private JButton restartButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton loopButton;
  private JButton speedButton;
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
    if (visualView.getViewType() != ViewType.VISUAL) {
      throw new UnsupportedOperationException("Editor View only supports Visual Views");
    }
    this.visualView = visualView;
    delegate = this.visualView.getFrame();

    this.editorPanel = new DrawingPanel();

    startButton = new JButton("Start");
    restartButton = new JButton("Restart");
    pauseButton = new JButton("Pause");
    resumeButton = new JButton("Resume");
    loopButton = new JButton("Loop");
    speedButton = new JButton("Speed");

    ICommand startCommand = new StartCommand();
    ICommand restartCommand = new RestartCommand();
    ICommand pauseCommand = new PauseCommand();
    ICommand resumeCommand = new ResumeCommand();
    ICommand loopCommand = new LoopCommand();
    ICommand speedCommand = new SpeedCommand();

    startButton.addActionListener(startCommand);
    restartButton.addActionListener(restartCommand);
    pauseButton.addActionListener(pauseCommand);
    resumeButton.addActionListener(resumeCommand);
    loopButton.addActionListener(loopCommand);
    speedButton.addActionListener(speedCommand);

    this.editorPanel.add(startButton);
    this.editorPanel.add(restartButton);
    this.editorPanel.add(pauseButton);
    this.editorPanel.add(resumeButton);
    this.editorPanel.add(loopButton);
    this.editorPanel.add(speedButton);

    this.visualPanel = this.visualView.getPanel();
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

  @Override
  public ViewType getViewType() {
    return ViewType.EDITOR;
  }
}
