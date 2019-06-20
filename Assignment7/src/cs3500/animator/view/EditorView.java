package cs3500.animator.view;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import com.intellij.ui.components.JBScrollPane;
import cs3500.animator.controller.Features;
import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import sun.plugin.javascript.JSClassLoader;

public class EditorView extends AbstractView {
  private JFrame delegate;
  private IView visualView;
  IReadOnlyAnimationModel model;
  private Features features;
  private DrawingPanel visualPanel;
  JPanel animationControls;
  JTabbedPane editorTabs;
  JScrollPane scrollPane;

  public EditorView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model,
      IView visualView, Features features) {
    super(ap, rd, ticksPerSecond, model);
    this.visualView = visualView;
    this.features = features;
    this.model = model;
    delegate = this.visualView.getFrame();
    this.visualPanel = this.visualView.getPanel();
    scrollPane = new JBScrollPane(this.visualPanel);
    scrollPane.setPreferredSize(new Dimension(500, 300));
    initGUI();
  }

  private void initGUI() {
    delegate.setTitle("ExCELlence Animator");
//    delegate.setMinimumSize(new Dimension(1500, 1000));
//    delegate.setPreferredSize(new Dimension(1500, 1000));

    animationControls = new AnimationControls(this.features);
    //JScrollPane animationPanel = new AnimationPanel();
    editorTabs = new EditorTabs(this.features, this.model);

    this.delegate.add(scrollPane, BorderLayout.EAST);
    this.delegate.add(animationControls, BorderLayout.NORTH);
    this.delegate.add(editorTabs, BorderLayout.WEST);


    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);
    delegate.setJMenuBar(menuBar);

    this.delegate.pack();
    this.delegate.setVisible(true);
  }

  public static JSeparator separatorFactory(int orientation) {
    JSeparator result = new JSeparator(orientation);
    result.setBackground(Color.gray);
    return result;
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


  private ArrayList<String> getkeyFrameInfo(IAnimatableShapeReadOnly shape) {
    ArrayList<String> keyFramesInfo = new ArrayList<>();
    for (IMotion motion : shape.getMotions()) {
      keyFramesInfo.add(motion.writeMotion());
    }
    return keyFramesInfo;
  }
}
