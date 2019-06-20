//package cs3500.animator.view;
//
//import cs3500.animator.controller.Features;
//import cs3500.animator.model.IAnimatableShapeReadOnly;
//import cs3500.animator.model.IMotion;
//import cs3500.animator.model.IReadOnlyAnimationModel;
//
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import javax.swing.*;
//
//public class EditorView2 extends AbstractView {
//  private JFrame delegate;
//  private IView visualView;
//  private Features features;
//
//  private DrawingPanel visualPanel;
//  private JPanel playbackPanel;
//  private JPanel editingPanel;
//
//  String[] shapeNames;
//
//  //dropdown of shapes in the model
//  private JComboBox<String> shapesDropdown;
//  //dropdown of motions in the model, linked to their shape
//  private JComboBox<String> keyframesDropdown;
//  //field to enter new shape name
//  private JTextField shapeNameField;
//  //dropdown of shape types, rectangle and ellipse
//  private JComboBox<String> shapeTypeDropdown;
//  private JLabel tickLabel;
//
//  //text fields for adding a motion
//  private JTextField dimensionWidthField;
//  private JTextField dimensionHeightField;
//  private JTextField positionXField;
//  private JTextField positionYField;
//  private JTextField redField;
//  private JTextField greenField;
//  private JTextField blueField;
//  private JTextField tickField;
//
//  //buttons to edit
//  private JButton addShapeButton;
//  private JButton removeShapeButton;
//  private JButton addKeyframeButton;
//  private JButton removeKeyframeButton;
//  private JButton editKeyframeButton;
//
//  //buttons for animation
//  private JButton startButton;
//  private JButton restartButton;
//  private JButton pauseButton;
//  private JButton resumeButton;
//  private JButton loopButton;
//  private JButton increaseSpeedButton;
//  private JButton decreaseSpeedButton;
//
//
//  /**
//   * Constructs an Editor View.
//   *
//   * @param visualView the view component of this editor view.
//   * @throws UnsupportedOperationException if the given view to build on is not a visual view.
//   */
//  public EditorView2(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model,
//                    IView visualView, Features features) {
//    super(ap, rd, ticksPerSecond, model);
//    if (visualView.getViewType() != ViewType.VISUAL) {
//      throw new UnsupportedOperationException("Editor View only supports Visual Views");
//    }
//    this.visualView = visualView;
//    this.features = features;
//    delegate = this.visualView.getFrame();
//    this.playbackPanel = new JPanel();
//
//    shapeNames = getShapeNames().toArray(new String[0]);
//
//    shapeNameField = new JTextField("Shape Name");
//    shapeTypeDropdown = new JComboBox<String>(new String[]{"rectangle", "ellipse"});
//    shapesDropdown = new JComboBox<>(shapeNames);
//    keyframesDropdown = new JComboBox<String>();
////    keyframesDropdown.setFixedCellWidth(200);
//    tickLabel = new JLabel("Tick: " + this.features.getCurrentTick());
//
//    addShapeButton = new JButton("Add Shape");
//    addShapeButton.setBackground(new Color(200, 200, 100));
//    addShapeButton.setOpaque(true);
//    addShapeButton.setPreferredSize(new Dimension(200, 20));
//
//    removeShapeButton = new JButton("Remove Shape");
//    removeShapeButton.setBackground(new Color(200, 200, 100));
//    removeShapeButton.setOpaque(true);
//    removeShapeButton.setPreferredSize(new Dimension(200, 20));
//
//    addKeyframeButton = new JButton("Add KeyFrame");
//    addKeyframeButton.setBackground(new Color(200, 100, 200));
//    addKeyframeButton.setOpaque(true);
//    addKeyframeButton.setPreferredSize(new Dimension(200, 20));
//
//    removeKeyframeButton = new JButton("Remove KeyFrame");
//    removeKeyframeButton.setBackground(new Color(200, 100, 200));
//    removeKeyframeButton.setOpaque(true);
//    removeKeyframeButton.setPreferredSize(new Dimension(200, 20));
//
//    editKeyframeButton = new JButton("Edit KeyFrame");
//    editKeyframeButton.setBackground(new Color(100, 200, 200));
//    editKeyframeButton.setOpaque(true);
//    editKeyframeButton.setPreferredSize(new Dimension(200, 20));
//
//
//    positionXField = new JTextField();
//    positionXField.setPreferredSize(new Dimension(40, 20));
//    positionYField = new JTextField();
//    positionYField.setPreferredSize(new Dimension(40, 20));
//    dimensionWidthField = new JTextField();
//    dimensionWidthField.setPreferredSize(new Dimension(40, 20));
//    dimensionHeightField = new JTextField();
//    dimensionHeightField.setPreferredSize(new Dimension(40, 20));
//    redField = new JTextField();
//    redField.setPreferredSize(new Dimension(55, 20));
//    greenField = new JTextField();
//    greenField.setPreferredSize(new Dimension(55, 20));
//    blueField = new JTextField();
//    blueField.setPreferredSize(new Dimension(55, 20));
//    tickField = new JTextField();
//    tickField.setPreferredSize(new Dimension(55, 20));
//
//    editingPanel = new JPanel();
//    this.editingPanel.setLayout(new FlowLayout());
//    this.editingPanel.setPreferredSize(new Dimension(250, (int) this.canvas.getHeight() + 200));
//
//    JPanel editButtonsPanel = new JPanel();
//    editButtonsPanel.setLayout(new FlowLayout());
//    editButtonsPanel.setPreferredSize(new Dimension(250, 150));
//    JPanel editFieldsPanel = new JPanel();
//    editFieldsPanel.setLayout(new FlowLayout());
//    editFieldsPanel.setPreferredSize(new Dimension(250, 150));
//
//    editingPanel.add(shapeTypeDropdown);
//    editingPanel.add(shapeNameField);
//    editingPanel.add(shapesDropdown);
//    editingPanel.add(keyframesDropdown);
//
//    editFieldsPanel.add(new JLabel("x:"));
//    editFieldsPanel.add(positionXField);
//    editFieldsPanel.add(new JLabel("y:"));
//    editFieldsPanel.add(positionYField);
//    editFieldsPanel.add(new JLabel("w:"));
//    editFieldsPanel.add(dimensionWidthField);
//    editFieldsPanel.add(new JLabel("h:"));
//    editFieldsPanel.add(dimensionHeightField);
//    editFieldsPanel.add(new JLabel("r:"));
//    editFieldsPanel.add(redField);
//    editFieldsPanel.add(new JLabel("g:"));
//    editFieldsPanel.add(greenField);
//    editFieldsPanel.add(new JLabel("b:"));
//    editFieldsPanel.add(blueField);
//    editFieldsPanel.add(new JLabel("tick:"));
//    editFieldsPanel.add(tickField);
//
//    editButtonsPanel.add(addShapeButton);
//    editButtonsPanel.add(removeShapeButton);
//    editButtonsPanel.add(addKeyframeButton);
//    editButtonsPanel.add(removeKeyframeButton);
//    editButtonsPanel.add(editKeyframeButton);
//
//
//    editingPanel.add(editFieldsPanel);
//    editingPanel.add(editButtonsPanel);
//
//
//    startButton = new JButton("Start");
//    restartButton = new JButton("Restart");
//    pauseButton = new JButton("Pause");
//    resumeButton = new JButton("Resume");
//    loopButton = new JButton("Loop");
//    increaseSpeedButton = new JButton("Increase Speed");
//    decreaseSpeedButton = new JButton("Decrease Speed");
//
//    startButton.addActionListener(event -> features.start());
//    restartButton.addActionListener(event -> features.restart());
//    pauseButton.addActionListener(event -> features.pause());
//    resumeButton.addActionListener(event -> features.resume());
//    loopButton.addActionListener(event -> features.loop());
//    increaseSpeedButton.addActionListener(event -> features.increaseSpeed());
//    decreaseSpeedButton.addActionListener(event -> features.decreaseSpeed());
//
//    shapesDropdown.addActionListener(event -> handleShapeDropdown());
//    addShapeButton.addActionListener(event -> handleAddShapeButton());
//    removeShapeButton.addActionListener(event -> handleRemoveShapeButton());
//    addKeyframeButton.addActionListener(event -> handleAddKeyframeButton());
//    removeKeyframeButton.addActionListener(event -> handleRemoveKeyframeButton());
//
//    this.playbackPanel.add(startButton);
//    this.playbackPanel.add(restartButton);
//    this.playbackPanel.add(pauseButton);
//    this.playbackPanel.add(resumeButton);
//    this.playbackPanel.add(loopButton);
//    this.playbackPanel.add(increaseSpeedButton);
//    this.playbackPanel.add(decreaseSpeedButton);
//    this.playbackPanel.add(tickLabel);
//
//    this.visualPanel = this.visualView.getPanel();
//    this.delegate.add(this.visualPanel, BorderLayout.EAST);
//
//    this.delegate.add(this.playbackPanel, BorderLayout.SOUTH);
//    this.delegate.add(this.editingPanel, BorderLayout.WEST);
//    this.delegate.pack();
//    this.delegate.setVisible(true);
//  }
//
//  private void handleShapeDropdown() {
//    String selectedShapeStr = shapesDropdown.getSelectedItem().toString().split(" ")[0];
//    String[] keyframes = features.showKeyframes(selectedShapeStr).toArray(new String[0]);
//    for (String keyframe : keyframes) {
//      keyframesDropdown.addItem(keyframe);
//    }
//  }
//
//  private void handleAddKeyframeButton() {
//    String shapeName = shapesDropdown.getSelectedItem().toString().split(" ")[0];
//    features.addKeyFrame(shapeName, Integer.parseInt(tickField.getText()),
//            Integer.parseInt(positionXField.getText()),
//            Integer.parseInt(positionYField.getText()),
//            Integer.parseInt(dimensionWidthField.getText()),
//            Integer.parseInt(dimensionHeightField.getText()),
//            Integer.parseInt(redField.getText()),
//            Integer.parseInt(greenField.getText()),
//            Integer.parseInt(blueField.getText()));
//    StringBuilder strBuilder = new StringBuilder();
//    strBuilder.append(shapeName + " ");
//    strBuilder.append(tickField + " ");
//    strBuilder.append(positionXField + " ");
//    strBuilder.append(positionYField + " ");
//    strBuilder.append(dimensionWidthField + " ");
//    strBuilder.append(dimensionHeightField + " ");
//    strBuilder.append(redField + " ");
//    strBuilder.append(greenField + " ");
//    strBuilder.append(blueField);
//    keyframesDropdown.addItem(strBuilder.toString());
//  }
//
//  private void handleRemoveKeyframeButton() {
//    String shapeName = shapesDropdown.getSelectedItem().toString().split(" ")[0];
//    String keyframeStr = keyframesDropdown.getSelectedItem().toString();
//    features.removeKeyFrame(shapeName, keyframeStr);
//    for (int i = 0; i < keyframesDropdown.getModel().getSize(); i++) {
//      if (keyframesDropdown.getItemAt(i).equals(keyframeStr)) {
//        keyframesDropdown.removeItemAt(i);
//      }
//    }
//  }
//
//  private void handleAddShapeButton() {
//    String shapeName = shapeNameField.getText();
//    String shapeType = shapeTypeDropdown.getSelectedItem().toString();
//    features.addShape(shapeName, shapeType);
//    features.addKeyFrame(shapeName, 0, 0, 0, 1, 1, 0, 0, 0);
//    shapesDropdown.addItem(shapeName + " " + shapeType);
//  }
//
//  private void handleRemoveShapeButton() {
//    String shapeName = shapesDropdown.getSelectedItem().toString().split(" ")[0];
//    features.removeShape(shapeName);
//    for (int i = 0; i < shapesDropdown.getModel().getSize(); i++) {
//      if (shapesDropdown.getItemAt(i).equals(shapesDropdown.getSelectedItem().toString())) {
//        shapesDropdown.removeItemAt(i);
//      }
//    }
//  }
//
//
//  @Override
//  public void render() {
//    this.visualView.render();
//  }
//
//  @Override
//  public void renderGUIShapes(List<ArrayList<String>> shapesToRender) {
//    this.visualView.renderGUIShapes(shapesToRender);
//  }
//
//  @Override
//  public ViewType getViewType() {
//    return ViewType.EDITOR;
//  }
//
//  private ArrayList<String> getShapeNames() {
//    ArrayList<String> shapeNames = new ArrayList<>();
//    for (String shapeName : model.getShapeMap().keySet()) {
//      shapeNames.add(shapeName + " " + model.getShapeMap().get(shapeName).getType());
//    }
//    return shapeNames;
//  }
//
//  private ArrayList<String> getkeyFrameInfo(IAnimatableShapeReadOnly shape) {
//    ArrayList<String> keyFramesInfo = new ArrayList<>();
//    for (IMotion motion : shape.getMotions()) {
//      keyFramesInfo.add(motion.writeMotion());
//    }
//    return keyFramesInfo;
//  }
//}
