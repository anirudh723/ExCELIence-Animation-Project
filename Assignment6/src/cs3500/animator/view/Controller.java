package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;


public class Controller implements IController {

  private Timer timer;
  private IReadOnlyAnimationModel model;
  private int tick;
  private IView view;


  public Controller(IReadOnlyAnimationModel model, IView view) {
    this.view = view;
    this.tick = 1;
    this.model = model;
    timer = new Timer(500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        List<ArrayList<String>> shapesToRender = getShapesAtTick(tick++);
        view.renderGUIShapes(shapesToRender);
        System.out.println(tick);
      }
    });
  }

  @Override
  public void run() {
    timer.start();

  }

  @Override
  public List<ArrayList<String>> getShapesAtTick(int tick) {
    List<ArrayList<String>> shapesAtTick = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
// todo have a case if there is only one motion in list

    for (IAnimatableShapeReadOnly shape : model.getShapeMap().values()) {
      if (shape.getMotions().size() != 0) {
        if (shape.getMotions().size() == 1) {
          IMotion motion = shape.getMotions().get(0);
          data.add(Integer.toString((int) motion.getPosition().getX()));
          data.add(Integer.toString((int) motion.getPosition().getY()));
          data.add(Integer.toString((int) motion.getDimension().getWidth()));
          data.add(Integer.toString((int) motion.getDimension().getHeight()));
          data.add(Integer.toString(motion.getColor().getRed()));
          data.add(Integer.toString(motion.getColor().getGreen()));
          data.add(Integer.toString(motion.getColor().getBlue()));
          data.add(shape.getType());
        }
        else {
          int index = relativeTickInMotions(shape.getMotions(), tick);
          if (index == shape.getMotions().size() - 1) {
            IMotion motion = shape.getMotions().get(shape.getMotions().size() - 1);
            data.add(Integer.toString((int) motion.getPosition().getX()));
            data.add(Integer.toString((int) motion.getPosition().getY()));
            data.add(Integer.toString((int) motion.getDimension().getWidth()));
            data.add(Integer.toString((int) motion.getDimension().getHeight()));
            data.add(Integer.toString(motion.getColor().getRed()));
            data.add(Integer.toString(motion.getColor().getGreen()));
            data.add(Integer.toString(motion.getColor().getBlue()));
            data.add(shape.getType());
          }
          else {
            data = tween(shape.getMotions().get(index), shape.getMotions().get(index + 1), tick);
            data.add(shape.getType());
          }
        }
      }
    }
    System.out.println("size : " + shapesAtTick.size());
    return shapesAtTick;
  }

  private ArrayList<String> tween(IMotion from, IMotion to, int tick) {
    ArrayList<String> data = new ArrayList<>();
    double part1 = (double) ((to.getTick() - tick) / (to.getTick() - from.getTick()));
    double part2 = (double) ((tick - from.getTick()) / (to.getTick() - from.getTick()));
    int newX = (int) ((from.getPosition().getX() * part1) + (to.getPosition().getX() * part2));
    int newY = (int) ((from.getPosition().getY() * part1) + (to.getPosition().getY() * part2));
    int newWidth = (int) (from.getDimension().getWidth() * part1
        + to.getDimension().getWidth() * part2);
    int newHeight = (int) (from.getDimension().getHeight() * part1
        + to.getDimension().getHeight() * part2);
    int newRed = (int) (from.getColor().getRed() * part1 + to.getColor().getRed() * part2);
//    if (colorOutOfRange(newRed)) {
//      newRed = to.getColor().getRed();
//    }
    int newGreen = (int) (from.getColor().getGreen() * part1 + to.getColor().getGreen() * part2);
//    if (colorOutOfRange(newGreen)) {
//      newGreen = to.getColor().getGreen();
//    }
    int newBlue = (int) (from.getColor().getBlue() * part1 + to.getColor().getBlue() * part2);
//    if (colorOutOfRange(newBlue)) {
//      newBlue = to.getColor().getBlue();
//    }
    data.add(Integer.toString(newX));
    data.add(Integer.toString(newY));
    data.add(Integer.toString(newWidth));
    data.add(Integer.toString(newHeight));
    data.add(Integer.toString(newRed));
    data.add(Integer.toString(newGreen));
    data.add(Integer.toString(newBlue));
    return data;
  }


  private int relativeTickInMotions(List<IMotion> motions, int tick) {
    for (int i = 0; i < motions.size() - 1; i++) {
      if (motions.get(i).getTick() <= tick && motions.get(i + 1).getTick() > tick) {
        return i;
      }
    }
    return motions.size() - 1;
  }

  private boolean colorOutOfRange(int color) {
    if (color > 255 || color < 0) {
      return true;
    }
    return false;
  }
}
