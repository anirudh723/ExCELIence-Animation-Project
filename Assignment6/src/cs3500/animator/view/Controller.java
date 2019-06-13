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
        List<ArrayList<String>>shapesToRender = getShapesAtTick(tick++);
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
    ArrayList<String> data;
// todo have a case if there is only one motion in list

    for (IAnimatableShapeReadOnly shape : model.getShapeMap().values()) {
      for (int i = 0; i < shape.getMotions().size() - 1; i++) {
        IMotion fromMotion = (shape).getMotions().get(i);
        IMotion toMotion = (shape).getMotions().get(i + 1);
        double part1 = ((double) (fromMotion.getTick() - tick)) / (toMotion.getTick() - fromMotion.getTick());
        double part2 = ((double) (tick - fromMotion.getTick())) / (toMotion.getTick() - fromMotion.getTick());

        data = new ArrayList<>();

        int newX = (int) ((shape.getPosition().getX() * part1) + (toMotion.getPosition().getX() * part2));
        int newY = (int) ((shape.getPosition().getY() * part1) + (toMotion.getPosition().getY() * part2));
        data.add(Integer.toString(newX));
        data.add(Integer.toString(newY));

        int newWidth = (int) (shape.getDimension().getWidth() * part1 + toMotion.getDimension().getWidth() * part2);
        int newHeight = (int) (shape.getDimension().getHeight() * part1 + toMotion.getDimension().getHeight() * part2);
        data.add(Integer.toString(newWidth));
        data.add(Integer.toString(newHeight));


        int newRed = (int) (shape.getColor().getRed() * part1 + toMotion.getColor().getRed() * part2);
        if (colorOutOfRange(newRed)) {
          newRed = toMotion.getColor().getRed();
        }
        int newGreen = (int) (shape.getColor().getGreen() * part1 + toMotion.getColor().getGreen() * part2);
        if (colorOutOfRange(newGreen)) {
          newGreen = toMotion.getColor().getGreen();
        }
        int newBlue = (int) (shape.getColor().getBlue() * part1 + toMotion.getColor().getBlue() * part2);
        if (colorOutOfRange(newBlue)) {
          newBlue = toMotion.getColor().getBlue();
        }
        data.add(Integer.toString(newRed));
        data.add(Integer.toString(newGreen));
        data.add(Integer.toString(newBlue));

        if (shape.getType().contains("rect")) {
          data.add(shape.getType());
          shapesAtTick.add(data);
        } else if (shape.getType().contains("ellipse")) {
          data.add(shape.getType());
          shapesAtTick.add(data);
        }
      }
    }
    return shapesAtTick;
  }


  private boolean colorOutOfRange(int color) {
    if (color > 255 || color < 0) {
      return true;
    }
    return false;
  }
}
