package cs3500.animator.view;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.Features;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyAnimationModel;

/**
 * Mock class for the Editor View.
 */
public class MockEditorView implements IView {


  private List<Features> listeners;
  private Readable readable;
  private Appendable appendable;

  /**
   * Construct the mock class.
   *
   * @param readable   the readable to read from.
   * @param appendable the appendable to write to.
   */
  public MockEditorView(Readable readable, Appendable appendable) {

    AnimationModel model = AnimationModelImpl.builder().setWidth(200)
            .setHeight(200).setX(50).setY(50).declareShape("R", "rectangle")
            .declareShape("E", "ellipse").build();
    IReadOnlyAnimationModel readModel = new ReadOnlyAnimationModel(model);
    IView view = new VisualView(new StringBuilder(), new InputStreamReader(System.in),
            50, readModel);

    Features feature = new Controller(model, view);
    listeners = new ArrayList<Features>();
    listeners.add(feature);
    this.readable = readable;
    this.appendable = appendable;
  }


  @Override
  public void render() {
    throw new UnsupportedOperationException("this render is not supported for editor view");
  }

  @Override
  public void renderGUIShapes(List<ArrayList<String>> shapesToRender) {
    Scanner scanner = new Scanner(readable);
    try {
      String command = scanner.next();

      for (Features listener : listeners) {
        switch (command) {
          case "start":
            listener.start();
            appendable.append("started");
            break;
          case "pause":
            listener.pause();
            appendable.append("paused");
            break;
          case "resume":
            listener.resume();
            appendable.append("resumed");
            break;
          case "restart":
            listener.restart();
            appendable.append("restarted");
            break;
          case "loop":
            listener.loop();
            appendable.append("looped");
            break;
          case "increaseSpeed":
            listener.increaseSpeed();
            appendable.append("increased");
            break;
          case "decreaseSpeed":
            listener.decreaseSpeed();
            appendable.append("decreased");
            break;
          case "addShape":
            listener.addShape("RShape1", "rectangle");
            appendable.append("addedShape");
            break;
          case "removeShape":
            appendable.append("removedShape");
            listener.removeShape("RShape1 rectangle");
            break;
          case "addKeyframe":
            appendable.append("addedKeyframe");
            listener.addKeyFrame("RShape2", 7, 5, 5, 5,
                5, 5, 5, 5);
            break;
          case "removeKeyframe":
            appendable.append("removedKeyframe");
            listener.removeKeyFrame("RShape1", "5 5 5 5 5 5 5 5");
            break;
          default:
            appendable.append("failed");
        }
      }
    } catch (Exception e) {
      System.out.println("error in test render gui shapes");
    }
  }

  @Override
  public void tryAppend(String... lines) throws IllegalArgumentException {
    //implemented in the real EditorView class - it is not necessary to implement it here.
  }

  @Override
  public ViewType getViewType() {
    return ViewType.EDITOR;
  }

  @Override
  public int getTicksPerSecond() {
    return 50;
  }

  @Override
  public JFrame getFrame() throws UnsupportedOperationException {
    return new JFrame();
  }
}
