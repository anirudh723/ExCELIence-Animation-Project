package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.*;

public class EditorView extends AbstractView {
  private JFrame delegate;
  private IView visualView;
  private Features features;

  private DrawingPanel visualPanel;
  private JPanel playbackPanel;
  private JPanel editingPanel;

  String[] shapeNames;

  //dropdown of shapes in the model
  private JComboBox<String> shapesDropdown;
  //dropdown of motions in the model, linked to their shape
  private JComboBox<String> keyframesDropdown;
  //field to enter new shape name
  private JTextField shapeNameField;
  //dropdown of shape types, rectangle and ellipse
  private JComboBox<String> shapeTypeDropdown;
  private JLabel speedLabel;


  //text fields for adding a motion
  private JTextField dimensionWidthField;
  private JTextField dimensionHeightField;
  private JTextField positionXField;
  private JTextField positionYField;
  private JTextField redField;
  private JTextField greenField;
  private JTextField blueField;
  private JTextField tickField;

  //buttons to edit
  private JButton addShapeButton;
  private JButton removeShapeButton;
  private JButton addKeyframeButton;
  private JButton removeKeyframeButton;
  private JButton editKeyframeButton;

  //buttons for animation
  private JButton startButton;
  private JButton restartButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton loopButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;

  boolean addShapeFlag = false;
  boolean removeShapeFlag = false;
  boolean addKeyframeFlag = false;
  boolean removeKeyframeFlag = false;
  boolean editKeyframeFlag = false;
  boolean shapeTypeFlag = false;
  boolean shapeNameFlag = false;
  boolean allFieldsFilledFlag = false;

//  boolean tickFieldFlag;
//  boolean xFieldFlag;
//  boolean yFieldFlag;
//  boolean widthFieldFlag;
//  boolean heightFieldFlag;
//  boolean redFieldFlag;
//  boolean greenFieldFlag;
//  boolean blueFieldFlag;


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
    this.playbackPanel = new JPanel();

    shapeNames = getShapeNames().toArray(new String[0]);

    shapeNameField = new JTextField();
    shapeNameField.setPreferredSize(new Dimension(90, 20));
    shapeTypeDropdown = new JComboBox<String>(new String[]{"rectangle", "ellipse"});
    shapesDropdown = new JComboBox<>(shapeNames);
    keyframesDropdown = new JComboBox<String>();
    speedLabel = new JLabel("speed2: " + features.getTicksPerMilisecond());


    addShapeButton = new JButton("Add Shape");
    addShapeButton.setPreferredSize(new Dimension(200, 20));
    addShapeButton.setEnabled(shapeNameFlag && shapeTypeFlag);

    removeShapeButton = new JButton("Remove Shape");
    removeShapeButton.setPreferredSize(new Dimension(200, 20));
    removeShapeButton.setEnabled(removeShapeFlag);

    addKeyframeButton = new JButton("Add KeyFrame");
    addKeyframeButton.setPreferredSize(new Dimension(200, 20));
//    addKeyframeButton.setEnabled(addKeyframeFlag);

    removeKeyframeButton = new JButton("Remove KeyFrame");
    removeKeyframeButton.setPreferredSize(new Dimension(200, 20));
    removeKeyframeButton.setEnabled(removeKeyframeFlag);

    editKeyframeButton = new JButton("Edit KeyFrame");
    editKeyframeButton.setPreferredSize(new Dimension(200, 20));
//    editKeyframeButton.setEnabled(editKeyframeFlag);


    positionXField = new JTextField();
    positionXField.setPreferredSize(new Dimension(40, 20));
    positionYField = new JTextField();
    positionYField.setPreferredSize(new Dimension(40, 20));
    dimensionWidthField = new JTextField();
    dimensionWidthField.setPreferredSize(new Dimension(40, 20));
    dimensionHeightField = new JTextField();
    dimensionHeightField.setPreferredSize(new Dimension(40, 20));
    redField = new JTextField();
    redField.setPreferredSize(new Dimension(55, 20));
    greenField = new JTextField();
    greenField.setPreferredSize(new Dimension(55, 20));
    blueField = new JTextField();
    blueField.setPreferredSize(new Dimension(55, 20));
    tickField = new JTextField();
    tickField.setPreferredSize(new Dimension(55, 20));

    editingPanel = new JPanel();
    this.editingPanel.setLayout(new FlowLayout());
    this.editingPanel.setPreferredSize(new Dimension(250, (int) this.canvas.getHeight() + 200));

    JPanel editButtonsPanel = new JPanel();
    editButtonsPanel.setLayout(new FlowLayout());
    editButtonsPanel.setPreferredSize(new Dimension(250, 150));
    JPanel editFieldsPanel = new JPanel();
    editFieldsPanel.setLayout(new FlowLayout());
    editFieldsPanel.setPreferredSize(new Dimension(250, 150));

    editingPanel.add(shapeTypeDropdown);
    editingPanel.add(shapeNameField);
    editingPanel.add(shapesDropdown);
    editingPanel.add(keyframesDropdown);

    editFieldsPanel.add(new JLabel("x:"));
    editFieldsPanel.add(positionXField);
    editFieldsPanel.add(new JLabel("y:"));
    editFieldsPanel.add(positionYField);
    editFieldsPanel.add(new JLabel("w:"));
    editFieldsPanel.add(dimensionWidthField);
    editFieldsPanel.add(new JLabel("h:"));
    editFieldsPanel.add(dimensionHeightField);
    editFieldsPanel.add(new JLabel("r:"));
    editFieldsPanel.add(redField);
    editFieldsPanel.add(new JLabel("g:"));
    editFieldsPanel.add(greenField);
    editFieldsPanel.add(new JLabel("b:"));
    editFieldsPanel.add(blueField);
    editFieldsPanel.add(new JLabel("tick:"));
    editFieldsPanel.add(tickField);

    editButtonsPanel.add(addShapeButton);
    editButtonsPanel.add(removeShapeButton);
    editButtonsPanel.add(addKeyframeButton);
    editButtonsPanel.add(removeKeyframeButton);
    editButtonsPanel.add(editKeyframeButton);


    editingPanel.add(editFieldsPanel);
    editingPanel.add(editButtonsPanel);


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
    increaseSpeedButton.addActionListener(event -> handleIncreaseSpeedButton());
    decreaseSpeedButton.addActionListener(event -> handleDecreaseSpeedButton());

    shapesDropdown.addActionListener(event -> handleShapeDropdown());
    keyframesDropdown.addActionListener(event -> handleKeyframeDropdown());
    shapeTypeDropdown.addActionListener(event -> handleTypeDropdown());
    addShapeButton.addActionListener(event -> handleAddShapeButton());
    removeShapeButton.addActionListener(event -> handleRemoveShapeButton());
    addKeyframeButton.addActionListener(event -> handleAddKeyframeButton());
    removeKeyframeButton.addActionListener(event -> handleRemoveKeyframeButton());
    editKeyframeButton.addActionListener(event -> handleEditKeyframeButton());
    shapeNameField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        shapeNameFlag = true;
        addShapeButton.setEnabled(true);
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });


    this.playbackPanel.add(startButton);
    this.playbackPanel.add(restartButton);
    this.playbackPanel.add(pauseButton);
    this.playbackPanel.add(resumeButton);
    this.playbackPanel.add(loopButton);
    this.playbackPanel.add(increaseSpeedButton);
    this.playbackPanel.add(decreaseSpeedButton);
    this.playbackPanel.add(speedLabel);

    this.visualPanel = this.visualView.getPanel();
    this.delegate.add(this.visualPanel, BorderLayout.EAST);

    this.delegate.add(this.playbackPanel, BorderLayout.SOUTH);
    this.delegate.add(this.editingPanel, BorderLayout.WEST);
    this.delegate.pack();
    this.delegate.setVisible(true);
  }

  private void handleIncreaseSpeedButton() {
    features.increaseSpeed();
    speedLabel.setText("speed: " + features.getTicksPerMilisecond());

  }

  private void handleDecreaseSpeedButton() {
    features.decreaseSpeed();
    speedLabel.setText("speed2: " + features.getTicksPerMilisecond());
  }

  private void handleEditKeyframeButton() {
    ensureTextFieldNotEmpty();
    if (allFieldsFilledFlag) {
      String selectedKeyframeStr = keyframesDropdown.getSelectedItem().toString();
      keyframesDropdown.removeItem(keyframesDropdown.getSelectedItem());
      System.out.println("removed selected item from dropdown");
      String shapeName = getShapeNameFromShapeDropdown();
      features.removeKeyFrame(shapeName, selectedKeyframeStr);
      System.out.println("removed selected motion from model");
      handleAddKeyframeButton();

//      editKeyframeFlag = false;
//      editKeyframeButton.setEnabled(editKeyframeFlag);
    }
  }

  private void handleShapeDropdown() {
    removeShapeFlag = true;
    removeShapeButton.setEnabled(removeShapeFlag);
    String shapeName = getShapeNameFromShapeDropdown();
    keyframesDropdown.removeAllItems();
    System.out.println("shape dropdown shape name: " + shapeName);
    String[] keyframes = features.getKeyframes(shapeName).toArray(new String[0]);
    System.out.println("shape's motions: " + keyframes.length);
    for (String keyframe : keyframes) {
      keyframesDropdown.addItem(keyframe);
    }
  }


  private void handleKeyframeDropdown() {
    removeKeyframeFlag = true;
    removeKeyframeButton.setEnabled(removeKeyframeFlag);

  }

  private void handleTypeDropdown() {
    shapeTypeFlag = true;
  }

//  private void handleShapeNameTextField(){
//    if(ensureTextFieldNotEmpty(shapeNameField)){
//      shapeTypeFlag = true;
//    }
//  }

  private void handleAddKeyframeButton() {
    ensureTextFieldNotEmpty();
    if (allFieldsFilledFlag) {
      String shapeName = getShapeNameFromShapeDropdown();

      features.addKeyFrame(shapeName, Integer.parseInt(tickField.getText()),
              Integer.parseInt(positionXField.getText()),
              Integer.parseInt(positionYField.getText()),
              Integer.parseInt(dimensionWidthField.getText()),
              Integer.parseInt(dimensionHeightField.getText()),
              Integer.parseInt(redField.getText()),
              Integer.parseInt(greenField.getText()),
              Integer.parseInt(blueField.getText()));
      String newString = tickField.getText() + " " + positionXField.getText()
              + " " + positionYField.getText() + " " + dimensionWidthField.getText()
              + " " + dimensionHeightField.getText() + " " + redField.getText() + " "
              + greenField.getText() + " " + blueField.getText();
      keyframesDropdown.addItem(newString);
//      allFieldsFilledFlag = false;
//      addKeyframeButton.setEnabled(allFieldsFilledFlag);

      ArrayList<JTextField> allFields = new ArrayList<>(Arrays.asList(tickField, positionXField,
              positionYField, dimensionWidthField, dimensionHeightField, redField, greenField, blueField));
      for(JTextField field : allFields){
        field.setText("");
      }

    }
  }

  private void handleRemoveKeyframeButton() {
    String shapeName = getShapeNameFromShapeDropdown();
    String keyframeStr = keyframesDropdown.getSelectedItem().toString();
    features.removeKeyFrame(shapeName, keyframeStr);
    for (int i = 0; i < keyframesDropdown.getModel().getSize(); i++) {
      if (keyframesDropdown.getItemAt(i).equals(keyframeStr)) {
        keyframesDropdown.removeItemAt(i);
      }
    }

    removeKeyframeFlag = false;
    removeKeyframeButton.setEnabled(removeKeyframeFlag);

  }

  private void handleAddShapeButton() {
    String shapeName = shapeNameField.getText();
    String shapeType = shapeTypeDropdown.getSelectedItem().toString();
    features.addShape(shapeName, shapeType);
    shapesDropdown.addItem(shapeName + " " + shapeType);

    shapeNameField.setText("");
    shapeTypeFlag = false;
    shapeNameFlag = false;
    addShapeButton.setEnabled(shapeNameFlag && shapeTypeFlag);
  }

  //todo null pointer if there are no more shapes

  private void handleRemoveShapeButton() {
    String shapeName = shapesDropdown.getSelectedItem().toString().split(" ")[0];
    features.removeShape(shapeName);
    for (int i = 0; i < shapesDropdown.getModel().getSize(); i++) {
      if (shapesDropdown.getItemAt(i).equals(shapesDropdown.getSelectedItem().toString())) {
        shapesDropdown.removeItemAt(i);
      }
    }
    removeShapeFlag = false;
    removeShapeButton.setEnabled(removeShapeFlag);
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
    if (keyFramesInfo.size() != 0) {
      for (IMotion motion : shape.getMotions()) {
        keyFramesInfo.add(motion.writeMotion());
      }
    }
    return keyFramesInfo;
  }

  private String getShapeNameFromShapeDropdown() {
    String[] fullShapeInfo = shapesDropdown.getSelectedItem().toString().split(" ");
    fullShapeInfo = Arrays.copyOf(fullShapeInfo, fullShapeInfo.length - 1);
    String shapeName = features.arrayToString(fullShapeInfo);
    return shapeName;
  }


  private boolean ensureDropdownHasSelected(JComboBox<String> dropdown) {
    return dropdown.getSelectedObjects().length > 0;
  }

  private void ensureTextFieldNotEmpty() {
    System.out.println("here");
    ArrayList<JTextField> allFields = new ArrayList<>(Arrays.asList(tickField, positionXField,
            positionYField, dimensionWidthField, dimensionHeightField, redField, greenField, blueField));
    for (JTextField f : allFields) {
      if (f.getText().length() == 0) {
        allFieldsFilledFlag = false;
      } else {
        allFieldsFilledFlag = true;
      }
    }
  }
}
