package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Motion;

import cs3500.animator.view.AnimationControls;
import cs3500.animator.view.EditorView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Timer;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewType;

/**
 * Controls the Animation program, creating the a  model, view and timer.
 */
public class Controller implements IController, Features {

  private Timer timer;
  private AnimationModel model;
  private int tick;
  private IView view;
  private int maxTick;
  private int ticksPerMillisecond;
  boolean loopOn = true;
  boolean running = false;

  /**
   * Constructs controller from the given model and view.
   *
   * @param model model being controlled.
   * @param view  view being controlled.
   */
  public Controller(AnimationModel model, IView view) {
    this.view = view;
    this.tick = 0;
    this.model = model;
    this.maxTick = 0;
    ticksPerMillisecond = 1000 / view.getTicksPerSecond();
    for (IAnimatableShapeReadOnly shape : model.getShapeMap().values()) {
      if (shape.getMotions().size() > maxTick) {
        maxTick = shape.getMotions().get(shape.getMotions().size() - 1).getTick();
      }
    }
    timer = new Timer(ticksPerMillisecond, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (tick < maxTick) {
          List<ArrayList<String>> shapesToRender = getShapesAtTick(tick++);
          view.renderGUIShapes(shapesToRender);
        } else if (loopOn && tick >= maxTick) {
          tick = 1;
        }
      }
    });
  }


  @Override
  public void run() {
    if (view.getViewType() == ViewType.VISUAL) {
      timer.start();
    }
    if (view.getViewType() == ViewType.SVG) {
      view.render();
    }
    if (view.getViewType() == ViewType.TEXT) {
      view.render();
    }
    if (view.getViewType() == ViewType.EDITOR) {
      // timer.start();
    }
  }

  @Override
  public List<ArrayList<String>> getShapesAtTick(int tick) {
    List<ArrayList<String>> shapesAtTick = new ArrayList<>();
    ArrayList<String> data;
    for (IAnimatableShapeReadOnly shape : model.getShapeMap().values()) {
      if (shape.getMotions().size() != 0) {
        if (shape.getMotions().size() == 1 && tick < shape.getMotions().get(0).getTick()) {
        } else if (shape.getMotions().size() == 1 && tick >= shape.getMotions().get(0).getTick()){
          IMotion motion = shape.getMotions().get(0);
          data = formatData(motion.getPosition().getX(), motion.getPosition().getY(),
              motion.getDimension().getWidth(), motion.getDimension().getHeight(),
              motion.getColor().getRed(), motion.getColor().getGreen(),
              motion.getColor().getBlue());
          data.add(shape.getType());
          shapesAtTick.add(data);
        }else {
          int index = relativeTickInMotions(shape.getMotions(), tick);
          if (index != -1) {
            if (index == shape.getMotions().size() - 1) {
              IMotion motion = shape.getMotions().get(shape.getMotions().size() - 1);
              data = formatData(motion.getPosition().getX(), motion.getPosition().getY(),
                  motion.getDimension().getWidth(), motion.getDimension().getHeight(),
                  motion.getColor().getRed(), motion.getColor().getGreen(),
                  motion.getColor().getBlue());
              data.add(shape.getType());
              shapesAtTick.add(data);
            } else {
              data = tween(shape.getMotions().get(index), shape.getMotions().get(index + 1), tick);
              data.add(shape.getType());
              shapesAtTick.add(data);
            }
          }
        }
      }
    }
    return shapesAtTick;
  }

  @Override
  public int getTick() {
    return this.tick;
  }


  private ArrayList<String> tween(IMotion from, IMotion to, int tick) {
    double p1 = ((double) (to.getTick() - tick)) / ((double) (to.getTick() - from.getTick()));
    double p2 = ((double) (tick - from.getTick())) / ((double) (to.getTick() - from.getTick()));

    double newX = calcNew(from.getPosition().getX(), to.getPosition().getX(), p1, p2);
    double newY = calcNew(from.getPosition().getY(), to.getPosition().getY(), p1, p2);
    double newW = calcNew(from.getDimension().getWidth(), to.getDimension().getWidth(), p1, p2);
    double newH = calcNew(from.getDimension().getHeight(), to.getDimension().getHeight(), p1, p2);
    int newR = (int) (calcNew(from.getColor().getRed(), to.getColor().getRed(), p1, p2));
    int newG = (int) (calcNew(from.getColor().getGreen(), to.getColor().getGreen(), p1, p2));
    int newB = (int) (calcNew(from.getColor().getBlue(), to.getColor().getBlue(), p1, p2));

    return formatData(newX, newY, newW, newH, newR, newG, newB);
  }

  private double calcNew(double from, double to, double frac1, double frac2) {
    return ((from * frac1) + (to * frac2));
  }

  private ArrayList<String> formatData(double x, double y, double width, double height,
      int red, int green, int blue) {
    return new ArrayList(Arrays.asList(Double.toString(x),
        Double.toString(y), Double.toString(width), Double.toString(height),
        Integer.toString(red), Integer.toString(green), Integer.toString(blue)));
  }

  private int relativeTickInMotions(List<IMotion> motions, int tick) {
    if (tick < motions.get(0).getTick()) {
      return -1;
    }
    for (int i = 0; i < motions.size() - 1; i++) {
      if (motions.get(i).getTick() <= tick && motions.get(i + 1).getTick() > tick) {
        return i;
      }
    }
    return motions.size() - 1;
  }


  @Override
  public void start() {
    timer.start();
  }

  @Override
  public void pause() {
    running = false;
    timer.stop();
  }

  @Override
  public void resume() {
    running = true;
    timer.start();
  }

  @Override
  public void restart() {
    tick = 0;
  }

  @Override
  public void loop() {
    if (loopOn) {
      loopOn = false;
    } else {
      loopOn = true;
    }
  }


  @Override
  public void increaseSpeed() {
    if (ticksPerMillisecond - 5 > 0) {
      timer.stop();
      ticksPerMillisecond = ticksPerMillisecond - 5;
      timer.setDelay(ticksPerMillisecond);
    }
    timer.start();
  }

  @Override
  public void decreaseSpeed() {
    timer.stop();
    ticksPerMillisecond = ticksPerMillisecond + 5;
    timer.setDelay(ticksPerMillisecond);
    timer.start();
  }

  @Override
  public void addShape(String name, String type) {
    model.addShape(name, type);
  }

  @Override
  public void removeShape(String shapeInfo) {
    String[] shapeStuff = shapeInfo.split(" ");
    model.removeShape(shapeStuff[0]);
  }

  @Override
  public void addKeyFrame(String shapeId, int tick, int posX, int posY, int width, int height,
      int red, int green, int blue) {
    IMotion motion = new Motion(tick, new Point(posX, posY), new Dimension(width, height),
        new Color(red, green, blue));
    model.addMotion(shapeId, motion);
  }

  @Override
  public ArrayList<String> showKeyFrames(String shapeName) {
    IAnimatableShapeReadOnly shape = model.getShapeMap().get(shapeName);
    return this.getkeyFrameInfo(shape);
  }

  @Override
  public void removeKeyFrame(String shapeName, String keyFrameInfo) {
    String[] keyFrameStuff = keyFrameInfo.split(" ");
    String keyFrameTick = keyFrameStuff[0];
    for (IMotion motion : model.getShapeMap().get(shapeName).getMotions()) {
      if (Integer.toString(motion.getTick()).equals(keyFrameTick)) {
        model.removeMotion(shapeName, motion);
        return;
      }
    }
  }
//

  private ArrayList<String> getkeyFrameInfo(IAnimatableShapeReadOnly shape) {
    ArrayList<String> keyFramesInfo = new ArrayList<>();
    if (shape.getMotions().size() > 0) {
      for (IMotion motion : shape.getMotions()) {
        keyFramesInfo.add(motion.writeMotion());
      }
    }
    return keyFramesInfo;
  }

  @Override
  public String arrayToString(String[] shapeNameList) {
    String newStr ="";
    for (String str : shapeNameList) {
      newStr += str + " ";
    }
    return newStr.substring(0, newStr.length() - 1);
  }

  @Override
  public int getTicksPerMilisecond() {
    return this.ticksPerMillisecond;
  }
}