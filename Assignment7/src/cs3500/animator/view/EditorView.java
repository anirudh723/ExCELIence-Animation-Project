package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;


import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class EditorView extends AbstractView {
  private JFrame delegate;
  private IView visualView;
  private Features features;

  private DrawingPanel visualPanel;
  private DrawingPanel editorPanel;
  private DrawingPanel shapesPanel;
  private DrawingPanel keyframesPanel;

  String[] shapeNames;

  private JComboBox<String> shapesDropdown;
  private JList<String> keyframesDropdown;

  private JTextField shapeName;
  private JComboBox<String> shapeType;


  private JTextField dimensionWidthField;
  private JTextField dimensionHeightField;
  private JTextField positionXField;
  private JTextField positionYField;
  private JTextField redField;
  private JTextField greenField;
  private JTextField blueField;
  private JTextField tickField;

  private JButton addShapeButton;
  private JButton removeShapeButton;
  private JButton addKeyframeButton;
  private JButton removeKeyframeButton;
  private JButton editKeyframeButton;
  private JButton startButton;
  private JButton restartButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton loopButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;


  /**
   * Constructs an Editor View.
   *
   * @param visualView the view component of this editor view.
   * @throws UnsupportedOperationException if the given view to build on is not a visual view.
   */
  public EditorView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model,
                    IView visualView, Features features) {
    super(ap, rd, ticksPerSecond, model);
    if (visualView.getViewType() != ViewType.VISUAL) {
      throw new UnsupportedOperationException("Editor View only supports Visual Views");
    }
    this.visualView = visualView;
    this.features = features;
    delegate = this.visualView.getFrame();
    this.editorPanel = new DrawingPanel();

    shapeNames = getShapeNames().toArray(new String[0]);

    shapeName = new JTextField("Shape Name");
    shapeType = new JComboBox<String>(new String[]{"rectangle", "ellipse"});
    shapesDropdown = new JComboBox<>(shapeNames);
    keyframesDropdown = new JList<String>();
    keyframesDropdown.setFixedCellWidth(100);

    addShapeButton = new JButton("Add Shape");
    removeShapeButton = new JButton("Remove Shape");
    addKeyframeButton = new JButton("Add KeyFrame");
    removeKeyframeButton = new JButton("Remove KeyFrame");
    editKeyframeButton = new JButton("Edit KeyFrame");


    dimensionWidthField = new JTextField("width");
    dimensionHeightField = new JTextField("height");
    positionXField = new JTextField("x");
    positionYField = new JTextField("y");
    redField = new JTextField("r");
    greenField = new JTextField("g");
    blueField = new JTextField("b");
    tickField = new JTextField("tick");

    shapesPanel = new DrawingPanel();
    shapesPanel.add(shapesDropdown);
    shapesPanel.add(addShapeButton);
    shapesPanel.add(removeShapeButton);
    shapesPanel.add(addKeyframeButton);
    shapesPanel.add(removeKeyframeButton);
    shapesPanel.add(editKeyframeButton);
    shapesPanel.add(keyframesDropdown);


    shapesPanel.add(positionXField);
    shapesPanel.add(positionYField);
    shapesPanel.add(dimensionWidthField);
    shapesPanel.add(dimensionHeightField);
    shapesPanel.add(dimensionWidthField);
    shapesPanel.add(redField);
    shapesPanel.add(greenField);
    shapesPanel.add(blueField);
    shapesPanel.add(tickField);


    //keyframesPanel = new DrawingPanel();


    startButton = new JButton("Start");
    restartButton = new JButton("Restart");
    pauseButton = new JButton("Pause");
    resumeButton = new JButton("Resume");
    loopButton = new JButton("Loop");
    increaseSpeedButton = new JButton("Increase Speed");
    decreaseSpeedButton = new JButton("Decrease Speed");

    startButton.addActionListener(event -> features.start());
    restartButton.addActionListener(event -> features.restart());
    pauseButton.addActionListener(event -> features.pause());
    resumeButton.addActionListener(event -> features.resume());
    loopButton.addActionListener(event -> features.loop());
    increaseSpeedButton.addActionListener(event -> features.increaseSpeed());
    decreaseSpeedButton.addActionListener(event -> features.decreaseSpeed());

    shapesDropdown.addActionListener(event ->
            keyframesDropdown.setListData(features
                    .showKeyframes(shapesDropdown.getItemAt(shapesDropdown
                            .getSelectedIndex())).toArray(new String[0])));
    addShapeButton.addActionListener(event -> features
            .addShape(shapesDropdown.getSelectedItem().toString(), shapeName.getText()));
    removeShapeButton.addActionListener(event -> features
            .removeShape(shapesDropdown.getSelectedItem().toString().split(" ")[0]));

    addKeyframeButton.addActionListener(event -> features
            .addKeyFrame(shapesDropdown.getSelectedItem().toString().split(" ")[0]
                    , Integer.parseInt(tickField.getText())
                    , Integer.parseInt(positionXField.getText())
                    , Integer.parseInt(positionYField.getText())
                    , Integer.parseInt(dimensionWidthField.getText())
                    , Integer.parseInt(dimensionWidthField.getText())
                    , Integer.parseInt(redField.getText())
                    , Integer.parseInt(greenField.getText())
                    , Integer.parseInt(blueField.getText())));

    removeKeyframeButton.addActionListener(event -> features
            .removeKeyFrame(shapesDropdown.getSelectedItem().toString(),
                    keyframesDropdown.getSelectedValue()));

    this.editorPanel.add(startButton);
    this.editorPanel.add(restartButton);
    this.editorPanel.add(pauseButton);
    this.editorPanel.add(resumeButton);
    this.editorPanel.add(loopButton);
    this.editorPanel.add(increaseSpeedButton);
    this.editorPanel.add(decreaseSpeedButton);

    this.visualPanel = this.visualView.getPanel();
    this.delegate.add(this.visualPanel, BorderLayout.EAST);
    this.delegate.add(this.editorPanel, BorderLayout.SOUTH);
    this.delegate.add(this.shapesPanel, BorderLayout.WEST);
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

  private ArrayList<String> getShapeNames() {
    ArrayList<String> shapeNames = new ArrayList<>();
    for (String shapeName : model.getShapeMap().keySet()) {
      shapeNames.add(shapeName + " " + model.getShapeMap().get(shapeName).getType());
    }
    return shapeNames;
  }

  private ArrayList<String> getkeyFrameInfo(IAnimatableShapeReadOnly shape) {
    ArrayList<String> keyFramesInfo = new ArrayList<>();
    for (IMotion motion : shape.getMotions()) {
      keyFramesInfo.add(motion.writeMotion());
    }
    return keyFramesInfo;
  }
}
