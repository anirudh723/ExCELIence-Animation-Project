package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Class that creates and formats the layout and styling of necessary fields for the {@link
 * EditorView}. Has handler for action listeners for fields and buttons that relate to editing in
 * the editor view.
 */
public class ShapeEditorTab extends JPanel {
  IReadOnlyAnimationModel model;
  Features features;
  String[] shapeNames;

  JPanel shapeSelectPanel;
  JPanel keyframePanel;
  JPanel shapeEditorPanel;

  TitledBorder shapeSelectBorder;
  TitledBorder keyframePanelBorder;
  TitledBorder shapeEditorBorder;
  GridBagConstraints ssc;
  GridBagConstraints t;

  JLabel selectShapeLabel;
  JLabel existingShapesLabel;
  JLabel createShapeLabel;
  JLabel newShapeLabel;
  JLabel newShapeTypeLabel;
  JLabel xLabel;
  JLabel yLabel;
  JLabel wLabel;
  JTextField wField;
  JLabel hLabel;
  JLabel tickLabel;
  JLabel rLabel;
  JLabel bLabel;
  JLabel gLabel;

  JTextField newShapeNameField;
  JTextField xField;
  JTextField yField;
  JTextField hField;
  JTextField tickField;
  JTextField rField;
  JTextField bField;
  JTextField gField;

  JComboBox<String> existingShapesDropdown;
  JComboBox<String> shapeTypeDropdown;
  JComboBox<String> keyframesDropdown;

  JButton createShapeButton;
  JButton removeShapeButton;
  JButton editKeyframe;
  JButton deleteKeyframe;
  JButton addKeyFrame;

  boolean removeShapeFlag = false;
  boolean removeKeyframeFlag = false;
  boolean shapeTypeFlag = false;
  boolean shapeNameFlag = false;
  boolean allFieldsFilledFlag = false;

  /**
   * Constructs the ShapeEditorTab with the given controller and model.
   *
   * @param features the given controller.
   * @param model    the given model.
   */
  public ShapeEditorTab(Features features, IReadOnlyAnimationModel model) {
    this.features = features;
    this.model = model;

    setLayout(new GridBagLayout());
    GridBagConstraints setC = new GridBagConstraints();
    setC.fill = GridBagConstraints.HORIZONTAL;
    setC.anchor = GridBagConstraints.PAGE_START;

    Font fNorm = this.getFont();
    Font fPanelTitle = new Font(fNorm.toString(), Font.BOLD, 12);
    Font fTitleBold = new Font(fNorm.toString(), Font.BOLD, 12);
    Font fFieldBold = new Font(fNorm.toString(), Font.BOLD, 11);
    Color titleColor = new Color(0, 65, 255);

    //Start Shape Selection Panel
    shapeSelectPanel = new JPanel();

    shapeSelectBorder = new TitledBorder("SHAPE");
    shapeSelectBorder.setTitleFont(fPanelTitle);
    shapeSelectBorder.setTitleColor(titleColor);
    shapeSelectBorder.setBorder(new LineBorder(new Color(0, 65, 255)));
    shapeSelectPanel.setBorder(shapeSelectBorder);

    shapeSelectPanel.setLayout(new GridBagLayout());
    ssc = new GridBagConstraints();
    ssc.anchor = GridBagConstraints.LINE_START;

    ssc.gridy = 1;
    selectShapeLabel = new JLabel("SELECT");
    selectShapeLabel.setFont(fTitleBold);
    shapeSelectPanel.add(selectShapeLabel, ssc);

    ssc.gridy = 3;
    existingShapesLabel = new JLabel("Existing shapes:");
    existingShapesLabel.setFont(fFieldBold);
    shapeSelectPanel.add(existingShapesLabel, ssc);

    ssc.gridx = 1;
    shapeNames = this.getShapeNames().toArray(new String[0]);
    existingShapesDropdown = new JComboBox<>(shapeNames);
    existingShapesDropdown.setPreferredSize(new Dimension(150, 30));
    shapeSelectPanel.add(existingShapesDropdown, ssc);

    ssc.gridy = 4;
    ssc.gridx = 0;
    ssc.gridwidth = GridBagConstraints.REMAINDER;
    ssc.fill = GridBagConstraints.HORIZONTAL;
    shapeSelectPanel.add(EditorView.separatorFactory(0), ssc);
    ssc.gridwidth = 1;
    ssc.fill = GridBagConstraints.NONE;

    ssc.gridy = 5;
    createShapeLabel = new JLabel("CREATE");
    createShapeLabel.setFont(fTitleBold);
    shapeSelectPanel.add(createShapeLabel, ssc);

    ssc.gridy = 6;
    newShapeLabel = new JLabel("Name:");
    newShapeLabel.setFont(fFieldBold);
    shapeSelectPanel.add(newShapeLabel, ssc);

    ssc.gridx = 1;
    newShapeNameField = new JTextField();
    newShapeNameField.setColumns(10);
    shapeSelectPanel.add(newShapeNameField, ssc);

    ssc.gridy = 7;
    ssc.gridx = 0;
    newShapeTypeLabel = new JLabel("Type:");
    newShapeTypeLabel.setFont(fFieldBold);
    shapeSelectPanel.add(newShapeTypeLabel, ssc);

    ssc.gridx = 1;
    shapeTypeDropdown = new JComboBox<>(new String[]{"rectangle", "ellipse"});
    shapeTypeDropdown.setPreferredSize(new Dimension(150, 30));
    shapeSelectPanel.add(shapeTypeDropdown, ssc);

    ssc.gridy = 8;
    ssc.gridx = 0;
    ssc.gridwidth = GridBagConstraints.REMAINDER;
    ssc.fill = GridBagConstraints.HORIZONTAL;
    shapeSelectPanel.add(EditorView.separatorFactory(0), ssc);
    ssc.gridwidth = 1;
    ssc.fill = GridBagConstraints.NONE;

    ssc.gridy = 9;
    ssc.gridx = 0;
    createShapeButton = new JButton("Create Shape");
    shapeSelectPanel.add(createShapeButton, ssc);

    ssc.gridx = 1;
    removeShapeButton = new JButton("Delete Shape");
    shapeSelectPanel.add(removeShapeButton, ssc);

    add(shapeSelectPanel, setC);
    //End Shape Selection Panel


    //Start Keyframe Panel
    keyframePanel = new JPanel();

    keyframePanelBorder = new TitledBorder("SHAPE'S KEYFRAMES");
    keyframePanelBorder.setTitleFont(fPanelTitle);
    keyframePanelBorder.setTitleColor(titleColor);
    keyframePanelBorder.setBorder(new LineBorder(new Color(0, 65, 255)));
    keyframePanel.setBorder(keyframePanelBorder);
    keyframePanel.setLayout(new GridBagLayout());
    GridBagConstraints kfc = new GridBagConstraints();

    kfc.gridy = 0;
    kfc.gridx = 1;
    keyframesDropdown = new JComboBox<>();
    keyframesDropdown.setPreferredSize(new Dimension(175, 30));
    keyframePanel.add(keyframesDropdown, kfc);

    kfc.gridx = 0;
    deleteKeyframe = new JButton("Delete");
    keyframePanel.add(deleteKeyframe, kfc);

    keyframePanel.setMaximumSize(new Dimension(200, 50));

    setC.gridy = 1;
    add(keyframePanel, setC);
    //End Keyframe Panel


    //Start Shape Editor Panel
    shapeEditorPanel = new JPanel();

    shapeEditorBorder = new TitledBorder("KEYFRAME ADD/EDIT");
    shapeEditorBorder.setTitleFont(fPanelTitle);
    shapeEditorBorder.setTitleColor(titleColor);
    shapeEditorBorder.setBorder(new LineBorder(new Color(0, 65, 255)));
    shapeEditorPanel.setBorder(shapeEditorBorder);

    shapeEditorPanel.setLayout(new GridBagLayout());
    t = new GridBagConstraints();
    t.anchor = GridBagConstraints.LINE_START;

    t.gridy = 0;
    t.gridx = 0;
    tickLabel = new JLabel("tick:");
    tickLabel.setFont(fFieldBold);
    shapeEditorPanel.add(tickLabel, t);

    t.gridx = 1;
    tickField = new JTextField(3);
    shapeEditorPanel.add(tickField, t);

    t.gridy = 1;
    t.gridx = 0;
    t.gridwidth = 8;
    t.fill = GridBagConstraints.HORIZONTAL;
    shapeEditorPanel.add(EditorView.separatorFactory(0), t);
    t.gridwidth = 1;
    t.fill = GridBagConstraints.NONE;

    t.gridy = 2;
    t.gridx = 0;
    t.anchor = GridBagConstraints.LINE_END;
    xLabel = new JLabel("x:");
    xLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    xLabel.setFont(fFieldBold);
    shapeEditorPanel.add(xLabel, t);

    t.gridx = 1;
    xField = new JTextField(3);
    shapeEditorPanel.add(xField, t);

    t.gridx = 2;
    yLabel = new JLabel("y:");
    yLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    yLabel.setFont(fFieldBold);
    shapeEditorPanel.add(yLabel, t);

    t.gridx = 3;
    yField = new JTextField(3);
    shapeEditorPanel.add(yField, t);

    t.gridx = 4;
    wLabel = new JLabel("w:");
    wLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    wLabel.setFont(fFieldBold);
    shapeEditorPanel.add(wLabel, t);

    t.gridx = 5;
    wField = new JTextField(3);
    shapeEditorPanel.add(wField, t);

    t.gridx = 6;
    hLabel = new JLabel("h:");
    hLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    hLabel.setFont(fFieldBold);
    shapeEditorPanel.add(hLabel, t);

    t.gridx = 7;
    hField = new JTextField(3);
    shapeEditorPanel.add(hField, t);

    t.gridy = 3;
    t.gridx = 0;
    t.gridwidth = 8;
    t.fill = GridBagConstraints.HORIZONTAL;
    shapeEditorPanel.add(EditorView.separatorFactory(0), t);
    t.gridwidth = 1;
    t.fill = GridBagConstraints.NONE;

    t.gridy = 4;
    t.gridx = 0;
    rLabel = new JLabel("r:");
    rLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    rLabel.setFont(fFieldBold);
    shapeEditorPanel.add(rLabel, t);

    t.gridx = 1;
    rField = new JTextField(3);
    shapeEditorPanel.add(rField, t);

    t.gridx = 2;
    t.gridheight = 1;
    t.anchor = GridBagConstraints.LINE_START;
    bLabel = new JLabel("b:");
    bLabel.setHorizontalAlignment(SwingConstants.LEFT);
    bLabel.setFont(fFieldBold);
    shapeEditorPanel.add(bLabel, t);

    t.gridx = 3;
    bField = new JTextField(3);
    shapeEditorPanel.add(bField, t);

    t.gridx = 4;
    gLabel = new JLabel("g:");
    gLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    gLabel.setFont(fFieldBold);
    shapeEditorPanel.add(gLabel, t);

    t.gridx = 5;
    gField = new JTextField(3);
    shapeEditorPanel.add(gField, t);

    t.gridy = 6;
    t.gridx = 0;
    t.gridwidth = 8;
    t.fill = GridBagConstraints.HORIZONTAL;
    shapeEditorPanel.add(EditorView.separatorFactory(0), t);


    t.gridy = 7;
    t.gridx = 0;
    t.fill = GridBagConstraints.HORIZONTAL;
    addKeyFrame = new JButton("Add Keyframe");
    shapeEditorPanel.add(addKeyFrame, t);

    t.gridy = 8;
    editKeyframe = new JButton("Edit Keyframe");
    shapeEditorPanel.add(editKeyframe, t);


    setC.gridy = 2;
    add(shapeEditorPanel, setC);
    //End Shape Editor Panel


    //Add action listeners to panel buttons
    existingShapesDropdown.addActionListener(event -> handleShapeDropdown());
    createShapeButton.addActionListener(event -> handleAddShapeButton());
    removeShapeButton.addActionListener(event -> handleRemoveShapeButton());
    addKeyFrame.addActionListener(event -> handleAddKeyframeButton());
    deleteKeyframe.addActionListener(event -> handleRemoveKeyframeButton());
    editKeyframe.addActionListener(event -> handleEditKeyframeButton());

  }

  /**
   * Handler to edit a keyframe. Ensures that all of the keyframe fields are not empty, and that a
   * shape is selected, then edits the keyframe.
   */
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
    }
  }

  /**
   * Handler for the shape combobox. Populates the keyframe dropdown with the selected shape's
   * keyframes.
   */
  private void handleShapeDropdown() {
    removeShapeFlag = true;
    String shapeName = getShapeNameFromShapeDropdown();
    keyframesDropdown.removeAllItems();
    String[] keyframes = this.showKeyFrames(shapeName).toArray(new String[0]);
    for (String keyframe : keyframes) {
      keyframesDropdown.addItem(keyframe);
    }
  }

  /**
   * Handler to add a shape. Ensures that the user has entered a name for the shape, and that a
   * shape with the name does not already exist.
   */
  private void handleAddShapeButton() {
    String nameInField = newShapeNameField.getText();
    boolean alreadyExists = false;
    for (int i = 0; i < existingShapesDropdown.getItemCount(); i++) {
      String[] fullShapeInfo = existingShapesDropdown.getItemAt(i).split(" ");
      fullShapeInfo = Arrays.copyOf(fullShapeInfo, fullShapeInfo.length - 1);
      String shapeNameAtI = this.arrayToString(fullShapeInfo);

      if (shapeNameAtI.equals(nameInField)) {
        alreadyExists = true;
      }
    }

    if (newShapeNameField.getText().length() > 0 && !alreadyExists) {
      String shapeName = newShapeNameField.getText();
      String shapeType = shapeTypeDropdown.getSelectedItem().toString();
      features.addShape(shapeName, shapeType);
      existingShapesDropdown.addItem(shapeName + " " + shapeType);

      newShapeNameField.setText("");
      shapeTypeFlag = false;
      shapeNameFlag = false;
    }
  }

  /**
   * Handler to remove a shape. Ensures that a shape is selected, then removes it from the model.
   */
  private void handleRemoveShapeButton() {
    String shapeName = existingShapesDropdown.getSelectedItem().toString().split(" ")[0];
    features.removeShape(shapeName);
    for (int i = 0; i < existingShapesDropdown.getModel().getSize(); i++) {
      if (existingShapesDropdown.getItemAt(i).equals(existingShapesDropdown.
              getSelectedItem().toString())) {
        existingShapesDropdown.removeItemAt(i);
      }
    }
    removeShapeFlag = false;
  }

  /**
   * Handler to add a keyframe. Ensures that all keyframe fields are not empty, and adds the
   * keyframe to the selected shape.
   */
  private void handleAddKeyframeButton() {
    ensureTextFieldNotEmpty();
    if (allFieldsFilledFlag) {
      String shapeName = getShapeNameFromShapeDropdown();

      features.addKeyFrame(shapeName, Integer.parseInt(tickField.getText()),
              Integer.parseInt(xField.getText()),
              Integer.parseInt(yField.getText()),
              Integer.parseInt(wField.getText()),
              Integer.parseInt(hField.getText()),
              Integer.parseInt(rField.getText()),
              Integer.parseInt(gField.getText()),
              Integer.parseInt(bField.getText()));
      String newString = tickField.getText() + " " + xField.getText()
              + " " + yField.getText() + " " + wField.getText()
              + " " + hField.getText() + " " + rField.getText() + " "
              + gField.getText() + " " + bField.getText();
      keyframesDropdown.addItem(newString);

      ArrayList<JTextField> allFields = new ArrayList<>(Arrays.asList(tickField, xField,
              yField, wField, hField, rField, gField, bField));
      for (JTextField field : allFields) {
        field.setText("");
      }
    }
  }

  /**
   * Handler to remove a keyframe. Ensures that a keyframe and shape are selected, then removes the
   * keyframe.
   */
  private void handleRemoveKeyframeButton() {
    if (ensureDropdownHasSelected(keyframesDropdown)
            && ensureDropdownHasSelected(existingShapesDropdown)) {
      String shapeName = getShapeNameFromShapeDropdown();
      String keyframeStr = keyframesDropdown.getSelectedItem().toString();
      features.removeKeyFrame(shapeName, keyframeStr);
      for (int i = 0; i < keyframesDropdown.getModel().getSize(); i++) {
        if (keyframesDropdown.getItemAt(i).equals(keyframeStr)) {
          keyframesDropdown.removeItemAt(i);
        }
      }
      removeKeyframeFlag = false;
    }
  }

  /**
   * Creates a list of all shapes in the model, with their type.
   *
   * @return a list of all shapes in the model, represented as their name and type:  "name type"
   */
  private ArrayList<String> getShapeNames() {
    ArrayList<String> shapeNames = new ArrayList<>();
    for (String shapeName : model.getShapeMap().keySet()) {
      shapeNames.add(shapeName + " " + model.getShapeMap().get(shapeName).getType());
    }
    return shapeNames;
  }

  /**
   * Given a shape, returns a list of that shape's keyframes, represented out as strings.
   *
   * @param shape the shape to write the motions of.
   * @return a string of the given shape's motions.
   */
  private ArrayList<String> getkeyFrameInfo(IAnimatableShapeReadOnly shape) {
    ArrayList<String> keyFramesInfo = new ArrayList<>();
    if (shape.getMotions().size() != 0) {
      for (IMotion motion : shape.getMotions()) {
        keyFramesInfo.add(motion.writeMotion());
      }
    }
    return keyFramesInfo;
  }

  /**
   * Gets the shape name of the selected item in the shape dropdown.
   *
   * @return the name of the shape.
   */
  private String getShapeNameFromShapeDropdown() {
    String[] fullShapeInfo = existingShapesDropdown.getSelectedItem().toString().split(" ");
    fullShapeInfo = Arrays.copyOf(fullShapeInfo, fullShapeInfo.length - 1);
    String shapeName = this.arrayToString(fullShapeInfo);
    return shapeName;
  }

  /**
   * Ensure that the given ComboBox has something selected.
   */
  private boolean ensureDropdownHasSelected(JComboBox<String> dropdown) {
    return dropdown.getSelectedObjects().length > 0;
  }

  /**
   * Ensure that all text fields that are used to add and edit keyframes are not empty.
   */
  private void ensureTextFieldNotEmpty() {
    ArrayList<JTextField> allFields = new ArrayList<>(Arrays.asList(tickField, xField,
            yField, wField, hField, rField, gField, bField));
    for (JTextField f : allFields) {
      if (f.getText().length() == 0) {
        allFieldsFilledFlag = false;
      } else {
        allFieldsFilledFlag = true;
      }
    }
  }

  /**
   * Convert an array of strings into a string, with each element separated by a space.
   *
   * @param shapeNameList the list of strings to convert.
   * @return the converted string of shape names.
   */
  private String arrayToString(String[] shapeNameList) {
    String newStr = "";
    for (String str : shapeNameList) {
      newStr += str + " ";
    }
    return newStr.substring(0, newStr.length() - 1);
  }


  /**
   * Given the name of a shape, returns a string of that shape's keyframes.
   */
  private ArrayList<String> showKeyFrames(String shapeName) {
    IAnimatableShapeReadOnly shape = model.getShapeMap().get(shapeName);
    return this.getkeyFrameInfo(shape);
  }

}
