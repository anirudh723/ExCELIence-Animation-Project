package cs3500.animator.view;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import cs3500.animator.controller.Features;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ShapeEditorTab extends JPanel {
  IReadOnlyAnimationModel model;
  Features features;
  String[] shapeNames;

  JPanel shapeSelectPanel;
  JPanel keyframePanel;
  JPanel shapeEditorPanel;
  JPanel colorPreviewPanel;

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
  JLabel keyframeListLabel;
  JLabel xLabel;
  JLabel yLabel;
  JLabel wLabel;
  JTextField wField;
  JLabel hLabel;
  JLabel tickLabel;
  JLabel cPrevLabel;
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
  JList<String> keyframeList;
  DefaultListModel<String> keyframeListModel;

  JButton createShapeButton;
  JButton removeShapeButton;
  JButton editKeyframe;
  JButton deleteKeyframe;
  JButton addKeyFrame;

  boolean addShapeFlag = false;
  boolean removeShapeFlag = false;
  boolean addKeyframeFlag = false;
  boolean removeKeyframeFlag = false;
  boolean editKeyframeFlag = false;
  boolean shapeTypeFlag = false;
  boolean shapeNameFlag = false;
  boolean allFieldsFilledFlag = false;

  public ShapeEditorTab(Features features, IReadOnlyAnimationModel model) {
    this.features = features;
    this.model = model;

    setLayout(new GridLayout(3,1));

    Font fNorm = this.getFont();
    Font fPanelTitle = new Font(fNorm.toString(), Font.BOLD, 12);
    Font fTitleBold = new Font(fNorm.toString(), Font.BOLD, 12);
    Font fFieldBold = new Font(fNorm.toString(), Font.BOLD, 11);
    Color titleColor = new Color(0,65,255);

    // START SHAPESELECTIONPANEL
    shapeSelectPanel = new JPanel();

    shapeSelectBorder = new TitledBorder("SHAPE SELECTION");
    shapeSelectBorder.setTitleFont(fPanelTitle);
    shapeSelectBorder.setTitleColor(titleColor);
    shapeSelectBorder.setBorder(new LineBorder(new Color(0,65,255)));
    shapeSelectPanel.setBorder(shapeSelectBorder);

    shapeSelectPanel.setLayout(new GridBagLayout());
    ssc = new GridBagConstraints();

    ssc.gridx = 0;
    ssc.gridy = 0;
    ssc.gridwidth = 2;
    ssc.fill = GridBagConstraints.BOTH;
    shapeSelectPanel.add(EditorView.separatorFactory(0), ssc);


    ssc.gridy = 1;
    selectShapeLabel = new JLabel("SELECT SHAPE");
    selectShapeLabel.setFont(fTitleBold);
    shapeSelectPanel.add(selectShapeLabel, ssc);
    shapeSelectPanel.add(EditorView.separatorFactory(0));


    ssc.gridy = 2;
    ssc.gridwidth = 1;
    existingShapesLabel = new JLabel("Existing shapes:");
    existingShapesLabel.setFont(fFieldBold);
    shapeSelectPanel.add(existingShapesLabel, ssc);

    ssc.gridx = 1;
    shapeNames = this.getShapeNames().toArray(new String[0]);
    existingShapesDropdown = new ComboBox(shapeNames);
    existingShapesDropdown.setPreferredSize(new Dimension(110,30));
    shapeSelectPanel.add(existingShapesDropdown, ssc);


    ssc.gridy = 3;
    ssc.gridx = 0;
    ssc.gridwidth = GridBagConstraints.REMAINDER;
    shapeSelectPanel.add(EditorView.separatorFactory(0), ssc);


    ssc.gridy = 4;
    ssc.gridwidth = 2;
    createShapeLabel = new JLabel("CREATE SHAPE");
    createShapeLabel.setFont(fTitleBold);
    shapeSelectPanel.add(createShapeLabel, ssc);


    ssc.gridy = 5;
    ssc.gridwidth = 1;
    newShapeLabel = new JLabel("Name:");
    newShapeLabel.setFont(fFieldBold);
    shapeSelectPanel.add(newShapeLabel, ssc);

    ssc.gridx = 1;
    newShapeNameField = new JTextField();
    newShapeNameField.setColumns(8);
    shapeSelectPanel.add(newShapeNameField, ssc);


    ssc.gridy = 6;
    ssc.gridx = 0;
    newShapeTypeLabel = new JLabel("Type:");
    newShapeTypeLabel.setFont(fFieldBold);
    shapeSelectPanel.add(newShapeTypeLabel, ssc);

    ssc.gridx = 1;
    shapeTypeDropdown = new ComboBox(new String[]{"rectangle", "ellipse"});
    shapeTypeDropdown.setPreferredSize(new Dimension(110,30));
    shapeSelectPanel.add(shapeTypeDropdown, ssc);

    ssc.gridy = 7;
    ssc.gridx = 0;
    createShapeButton = new JButton("Create Shape");
    shapeSelectPanel.add(createShapeButton, ssc);

    ssc.gridy = 7;
    ssc.gridx = 1;
    removeShapeButton = new JButton("Remove Shape");
    shapeSelectPanel.add(removeShapeButton, ssc);

    ssc.gridy = 12;
    shapeSelectPanel.add(EditorView.separatorFactory(0), ssc);

    add(shapeSelectPanel);
    // END SHAPESELECTIONPANEL




    // START KEYFRAMEPANEL
    keyframePanel = new JPanel();

    keyframePanelBorder = new TitledBorder("SHAPE'S KEYFRAMES");
    keyframePanelBorder.setTitleFont(fPanelTitle);
    keyframePanelBorder.setTitleColor(titleColor);
    keyframePanelBorder.setBorder(new LineBorder(new Color(0,65,255)));
    keyframePanel.setBorder(keyframePanelBorder);
    keyframePanel.setLayout(new FlowLayout());
    GridBagConstraints kfc = new GridBagConstraints();
    kfc.fill = GridBagConstraints.BOTH;

    kfc.gridx = 0;
    kfc.gridy = 0;
    kfc.gridwidth = GridBagConstraints.HORIZONTAL;
    keyframePanel.add(EditorView.separatorFactory(0), kfc);

//    kfc.gridy = 1;
//    kfc.gridwidth = 1;
//    JLabel shapeIdLabel = new JLabel("Selected Shape: ");
//    shapeIdLabel.setFont(fTitleBold);
//    keyframePanel.add(shapeIdLabel, kfc);

//    kfc.gridx = 1;
//    JLabel selectedShapeId = new JLabel("unselected");
//    selectedShapeId.setHorizontalAlignment(SwingConstants.RIGHT);
//    selectedShapeId.setFont(fTitleBold);
//    keyframePanel.add(selectedShapeId, kfc);

    kfc.gridy = 2;
    kfc.gridx = 0;

    kfc.gridwidth = GridBagConstraints.HORIZONTAL;
    keyframePanel.add(EditorView.separatorFactory(0), kfc);

//    kfc.gridy = 3;
//    kfc.gridwidth = 1;
//    keyframeListLabel = new JLabel("Shape's Keyframes: ");
//    keyframeListLabel.setFont(fTitleBold);
//    keyframePanel.add(keyframeListLabel, kfc);

//    kfc.gridy = 4;
//    Component keyframesLabelListSpacer = new Filler(new Dimension(1,5), new Dimension(1,5),new Dimension(1,5));
//    keyframePanel.add(keyframesLabelListSpacer, kfc);

    kfc.gridy = 0;
    kfc.gridx = 0;
    keyframesDropdown = new ComboBox<>();
    keyframesDropdown.setPreferredSize(new Dimension(200, 30));
    keyframePanel.add(keyframesDropdown);

    // Creates the list ...
//    keyframeListModel = new DefaultListModel<>();
//    keyframeList = new JBList<>(keyframeListModel);
//    keyframeList.setVisibleRowCount(-1);
//    keyframeList.setLayoutOrientation(JList.VERTICAL);
//    keyframeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    keyframeList.setListData(new String[]{});
//    // Creates the list scroller
//    JScrollPane keyframeListScrollerPanel = new JBScrollPane(keyframeList);
//    keyframeListScrollerPanel.setMinimumSize(new Dimension(225, 60));
//    keyframeListScrollerPanel.setPreferredSize(new Dimension(225, 60));
//    keyframePanel.add(keyframeListScrollerPanel, kfc);

//
    kfc.gridx = GridBagConstraints.WEST;
    deleteKeyframe = new JButton("Delete Keyframe");
    keyframePanel.add(deleteKeyframe, FlowLayout.CENTER);

//    kfc.gridy = 8;
//    kfc.gridwidth = GridBagConstraints.HORIZONTAL;
//    keyframePanel.add(EditorView.separatorFactory(0), kfc);

    add(keyframePanel);
    //END KEYFRAMEPANEL


    // START KEYFRAMEPANEL
    shapeEditorPanel = new JPanel();

    shapeEditorBorder = new TitledBorder("KEYFRAME ADD/EDIT");
    shapeEditorBorder.setTitleFont(fPanelTitle);
    shapeEditorBorder.setTitleColor(titleColor);
    shapeEditorBorder.setBorder(new LineBorder(new Color(0,65,255)));
    shapeEditorPanel.setBorder(shapeEditorBorder);

    shapeEditorPanel.setLayout(new GridBagLayout());
    t = new GridBagConstraints();
    t.anchor = GridBagConstraints.LINE_START;
    t.gridx = 5;


//    t.gridx = 0;
//    t.gridy = 0;
//    t.gridwidth = GridBagConstraints.REMAINDER;
//    t.fill =  GridBagConstraints.HORIZONTAL;
//    shapeEditorPanel.add(EditorView.separatorFactory(0), t);
//    t.fill =  GridBagConstraints.NONE;


//    t.gridx = 0;
//    t.gridy = 1;
//    t.gridwidth = 2;
//    JLabel changeNameLabel = new JLabel("Name:");
//    changeNameLabel.setFont(fFieldBold);
//    shapeEditorPanel.add(changeNameLabel, t);
//
//    t.gridx = 2;
//    JTextField changeNameField = new JTextField();
//    changeNameField.setColumns(8);
//    shapeEditorPanel.add(changeNameField, t);
//
//
//    t.gridx = 0;
//    t.gridy = 2;
//    JLabel shapeTypeLabel = new JLabel("Shape Type: ");
//    shapeTypeLabel.setFont(fFieldBold);
//    shapeEditorPanel.add(shapeTypeLabel, t);
//
//    t.gridx = 2;
//    JComboBox<String> selectedShapeType = new JComboBox<>();
//    selectedShapeType.setPreferredSize(new Dimension(110,30));
//    shapeEditorPanel.add(selectedShapeType, t);


//    t.gridx = 0;
//    t.gridy = 3;
//    t.gridwidth = GridBagConstraints.REMAINDER;
//    t.fill =  GridBagConstraints.HORIZONTAL;
//    shapeEditorPanel.add(EditorView.separatorFactory(0), t);
//    t.fill =  GridBagConstraints.NONE;

    t.gridy = 0;
    t.gridx = 1;
    //t.gridwidth = 1;
    //t.anchor = GridBagConstraints.LINE_END;
    xLabel = new JLabel("x:");
    xLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    xLabel.setFont(fFieldBold);
    shapeEditorPanel.add(xLabel, t);

    t.gridx = 1;
    t.gridy = 1;
    xField = new JTextField(3);
    shapeEditorPanel.add(xField, t);

    t.gridy = 0;
    t.gridx = 2;
    yLabel = new JLabel("y:");
    yLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    yLabel.setFont(fFieldBold);
    shapeEditorPanel.add(yLabel, t);

    t.gridx = 2;
    t.gridy = 1;
    yField= new JTextField(3);
    shapeEditorPanel.add(yField, t);

    t.gridx = 1;
    t.gridy = 1;
   // t.anchor = GridBagConstraints.LINE_END;
    wLabel = new JLabel("w:");
    //wLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    wLabel.setFont(fFieldBold);
    shapeEditorPanel.add(wLabel, t);

    t.gridx = 1;
    t.gridy = 1;
    //t.anchor = GridBagConstraints.LINE_START;
    wField = new JTextField(3);
    shapeEditorPanel.add(wField, t);

    t.gridx = 2;
    hLabel = new JLabel("h:");
    hLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    hLabel.setFont(fFieldBold);
    shapeEditorPanel.add(hLabel, t);

    t.gridx = 3;
    hField = new JTextField(3);
    shapeEditorPanel.add(hField, t);

    t.gridx = -1;
    t.gridy = 0;
    tickLabel = new JLabel("tick:");
    hLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    hLabel.setFont(fFieldBold);
    shapeEditorPanel.add(tickLabel, t);

    t.gridx = 0;
    t.gridy = 1;
    t.gridwidth = 4;
    tickField = new JTextField(3);
    shapeEditorPanel.add(tickField, t);

//
//    t.gridx = 0;
//    t.gridy = 6;
//    t.gridwidth = GridBagConstraints.REMAINDER;
//    t.fill =  GridBagConstraints.HORIZONTAL;
//    shapeEditorPanel.add(EditorView.separatorFactory(0), t);
//    t.fill =  GridBagConstraints.NONE;

//
//    t.gridx = 0;
//    t.gridy = 7;
//    t.gridwidth = 1;
//    t.anchor = GridBagConstraints.CENTER;
//    cPrevLabel = new JLabel("Preview:");
//    cPrevLabel.setFont(fFieldBold);
//    shapeEditorPanel.add(cPrevLabel, t);

    t.gridx = 1;
    t.gridheight = 3;
    t.fill =  GridBagConstraints.VERTICAL;
    shapeEditorPanel.add(EditorView.separatorFactory(1), t);
    t.fill =  GridBagConstraints.NONE;

    t.gridx = 2;
    t.gridheight = 1;
    t.anchor = GridBagConstraints.LINE_START;
    rLabel = new JLabel("r:");
    rLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    rLabel.setFont(fFieldBold);
    shapeEditorPanel.add(rLabel, t);

    t.gridx = 3;
    rField = new JTextField(3);
    shapeEditorPanel.add(rField, t);


//    t.gridx = 0;
//    t.gridy = 8;
//    t.gridheight = 2;
//    t.anchor = GridBagConstraints.CENTER;
//    colorPreviewPanel = new JPanel();
//    colorPreviewPanel.setBackground(Color.BLUE);
//    colorPreviewPanel.setPreferredSize(new Dimension(40,40));
//    colorPreviewPanel.setMaximumSize(new Dimension(40,40));
//    shapeEditorPanel.add(colorPreviewPanel, t);

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


    t.gridx = 2;
    t.gridy = 9;
    gLabel = new JLabel("g:");
    gLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    gLabel.setFont(fFieldBold);
    shapeEditorPanel.add(gLabel, t);

    t.gridx = 3;
    gField = new JTextField(3);
    shapeEditorPanel.add(gField, t);


    t.gridx = 0;
    t.gridy = 10;
    t.gridwidth = GridBagConstraints.REMAINDER;
    t.fill =  GridBagConstraints.HORIZONTAL;
    shapeEditorPanel.add(EditorView.separatorFactory(0), t);

    t.gridx = 0;
    t.gridy = 11;
    t.gridwidth = 1;
    addKeyFrame = new JButton("Add Keyframe");
    shapeEditorPanel.add(addKeyFrame, t);

    t.gridx = 1;
    t.gridy = 11;
    t.gridwidth = GridBagConstraints.EAST;
    editKeyframe = new JButton("Edit Keyframe");
    shapeEditorPanel.add(editKeyframe, t);




//    t.gridy = 12;
//    shapeEditorPanel.add(AnimationGUI.separatorFactory(0), t);

    add(shapeEditorPanel);
    // END SHAPEEDITORPANEL

    // END SHAPEEDITOR


    ///////////////////////Ani's work/////////////////////////////////////
    existingShapesDropdown.addActionListener(event -> handleShapeDropdown());
    createShapeButton.addActionListener(event -> handleAddShapeButton());
    removeShapeButton.addActionListener(event -> handleRemoveShapeButton());
    addKeyFrame.addActionListener(event -> handleAddKeyframeButton());
    deleteKeyframe.addActionListener(event -> handleRemoveKeyframeButton());
    editKeyframe.addActionListener(event -> handleEditKeyframeButton());

  }

  private void handleIncreaseSpeedButton() {
    features.increaseSpeed();
    //speedLabel.setText("speed: " + features.getTicksPerMilisecond());

  }

  private void handleDecreaseSpeedButton() {
    features.decreaseSpeed();
    //speedLabel.setText("speed2: " + features.getTicksPerMilisecond());
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
    String[] keyframes = features.showKeyFrames(shapeName).toArray(new String[0]);
    System.out.println("shape's motions: " + keyframes.length);
    for (String keyframe : keyframes) {
      keyframesDropdown.addItem(keyframe);
    }
  }

  private void handleAddShapeButton() {
    String shapeName = newShapeNameField.getText();
    String shapeType = shapeTypeDropdown.getSelectedItem().toString();
    features.addShape(shapeName, shapeType);
    existingShapesDropdown.addItem(shapeName + " " + shapeType);

    newShapeNameField.setText("");
    shapeTypeFlag = false;
    shapeNameFlag = false;
    createShapeButton.setEnabled(shapeNameFlag && shapeTypeFlag);
  }

  private void handleRemoveShapeButton() {
    String shapeName = existingShapesDropdown.getSelectedItem().toString().split(" ")[0];
    features.removeShape(shapeName);
    for (int i = 0; i < existingShapesDropdown.getModel().getSize(); i++) {
      if (existingShapesDropdown.getItemAt(i).equals(existingShapesDropdown.getSelectedItem().toString())) {
        existingShapesDropdown.removeItemAt(i);
      }
    }
    removeShapeFlag = false;
    removeShapeButton.setEnabled(removeShapeFlag);
  }

  private void handleKeyframeDropdown() {
    removeKeyframeFlag = true;
    deleteKeyframe.setEnabled(removeKeyframeFlag);

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
//      allFieldsFilledFlag = false;
//      addKeyframeButton.setEnabled(allFieldsFilledFlag);

      ArrayList<JTextField> allFields = new ArrayList<>(Arrays.asList(tickField, xField,
          yField, wField, hField, rField, gField, bField));
      for(JTextField field : allFields){
        field.setText("");
      }

    }
  }

  private void handleRemoveKeyframeButton() {
    if (ensureDropdownHasSelected(keyframesDropdown) && ensureDropdownHasSelected(existingShapesDropdown)) {
      String shapeName = getShapeNameFromShapeDropdown();
      String keyframeStr = keyframesDropdown.getSelectedItem().toString();
      features.removeKeyFrame(shapeName, keyframeStr);
      for (int i = 0; i < keyframesDropdown.getModel().getSize(); i++) {
        if (keyframesDropdown.getItemAt(i).equals(keyframeStr)) {
          keyframesDropdown.removeItemAt(i);
        }
      }
      removeKeyframeFlag = false;
      deleteKeyframe.setEnabled(removeKeyframeFlag);
    }
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

    JLabel currentTickNum = new JLabel("tickNum");
    currentTickNum.setHorizontalAlignment(SwingConstants.CENTER);
    JProgressBar tickProgress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
    tickProgress.setStringPainted(true);
    tickProgress.setIndeterminate(true);

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


    JPanel tickClock = new JPanel(new GridLayout(2, 1));
    TitledBorder clockBorder = BorderFactory.createTitledBorder("CLOCK");
    clockBorder.setTitleFont(new Font(getFont().toString(), Font.BOLD, 14));
    clockBorder.setTitleColor(new Color(0,65,255));
    clockBorder.setTitleJustification(TitledBorder.CENTER);
    clockBorder.setBorder(new LineBorder(new Color(0,65,255)));
    tickClock.setBackground(bg1);
    tickClock.setBorder(clockBorder);
    tickClock.add(currentTickNum);
    tickClock.add(tickProgress);
    add(tickClock, BorderLayout.LINE_END);

    add(new Box.Filler(new Dimension(0,15), new Dimension(0,15),
        new Dimension(0,15)), BorderLayout.PAGE_END);
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
    String[] fullShapeInfo = existingShapesDropdown.getSelectedItem().toString().split(" ");
    fullShapeInfo = Arrays.copyOf(fullShapeInfo, fullShapeInfo.length - 1);
    String shapeName = features.arrayToString(fullShapeInfo);
    return shapeName;
  }

  private boolean ensureDropdownHasSelected(JComboBox<String> dropdown) {
    return dropdown.getSelectedObjects().length > 0;
  }

  private void ensureTextFieldNotEmpty() {
    System.out.println("here");
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

}
