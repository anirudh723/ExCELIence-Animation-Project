package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Motion;

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
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewType;

/**
 * Controls the Animation program, creating the a  model, view and timer. Can determine what type of
 * animation is produced, depending on which type of view is passed to this Controller.
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
   * @throws IllegalArgumentException if  the given model is null.
   * @throws IllegalArgumentException if the given view is null.
   */
  public Controller(AnimationModel model, IView view) {
    if (view == null) {
      throw new IllegalArgumentException("Given view is null.");
    }
    this.view = view;
    this.tick = 0;
    if (model == null) {
      throw new IllegalArgumentException("Given model is null.");
    }
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

  /**
   * Runs the appropriate view based on the {@link ViewType} of the IView given to the Controller.
   */
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
  }

  /**
   * Gets information about each shape at the given tick. Uses tweening to calculate the values of a
   * shape's x, y, width, height, and color between keyframes.
   *
   * @param tick the tick to get the shapes at.
   * @return a list that represent all shape's x, y, width, height, and color at the given tick.
   */
  @Override
  public List<ArrayList<String>> getShapesAtTick(int tick) {
    List<ArrayList<String>> shapesAtTick = new ArrayList<>();
    ArrayList<String> data;
    for (IAnimatableShapeReadOnly shape : model.getShapeMap().values()) {
      if (shape.getMotions().size() != 0) {
        if (shape.getMotions().size() == 1 && tick < shape.getMotions().get(0).getTick()) {
          System.out.println("Desired tick is before the shape's only motion starts.");
        } else if (shape.getMotions().size() == 1 && tick >= shape.getMotions().get(0).getTick()) {
          IMotion motion = shape.getMotions().get(0);
          data = formatData(motion.getPosition().getX(), motion.getPosition().getY(),
                  motion.getDimension().getWidth(), motion.getDimension().getHeight(),
                  motion.getColor().getRed(), motion.getColor().getGreen(),
                  motion.getColor().getBlue());
          data.add(shape.getType());
          shapesAtTick.add(data);
        } else {
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

  /**
   * Gets the current tick of the animation as it's running.
   *
   * @return the current tick.
   */
  @Override
  public int getTick() {
    return this.tick;
  }

  /**
   * Use tweening to find the state of a shape between the two given motions at the given tick. The
   * state's shape includes its x, y, width, height, and color values.
   *
   * @param from the motion to start tweening from.
   * @param to   the motion to end tweening at.
   * @param tick the tick at which to find the state of the shape's values.
   * @return a list of the shape's x, y, width, height, and color values at the given tick.
   */
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

  /**
   * Given the values for the two fractions of the tweening linear interpolation formula
   * 𝑓(𝑡)=𝑎(𝑡𝑏−𝑡/𝑡𝑏−𝑡𝑎)+𝑏(𝑡−𝑡𝑎/𝑡𝑏−𝑡𝑎), and the values for 𝑎 and 𝑏, calculates
   * the value of 𝑓(𝑡).
   *
   * @param from  the 'from' value.
   * @param to    the 'to' value.
   * @param frac1 the value of the first time interval calculation.
   * @param frac2 the value of the second time interval calculation.
   * @return the result of the formula, 𝑓(𝑡), which is a value between 'from' and 'to' that
   *              corresponds to the tick 𝑡 in the formula.
   */
  private double calcNew(double from, double to, double frac1, double frac2) {
    return ((from * frac1) + (to * frac2));
  }

  /**
   * Given various numbers, formats them into a list of strings.
   *
   * @param x      the shape's x position.
   * @param y      the shape's x position.
   * @param width  the shape's width.
   * @param height the shape's height.
   * @param red    the shape's red value.
   * @param green  the shape's green value.
   * @param blue   the shape's blue value.
   */
  private ArrayList<String> formatData(double x, double y, double width, double height,
                                       int red, int green, int blue) {
    return new ArrayList(Arrays.asList(Double.toString(x),
            Double.toString(y), Double.toString(width), Double.toString(height),
            Integer.toString(red), Integer.toString(green), Integer.toString(blue)));
  }

  /**
   * Given a list of motions and a tick, finds the index of the motion that starts directly before
   * or at the tick.
   *
   * @param motions the list of motions to look through.
   * @param tick    the tick to find the motion before it.
   * @return the index of the motion that is directly before or at the given tick.
   */
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

  /**
   * Starts the animation.
   */
  @Override
  public void start() {
    timer.start();
  }

  /**
   * Pauses the animation.
   */
  @Override
  public void pause() {
    running = false;
    timer.stop();
  }

  /**
   * Resumes the animation after it has been paused.
   */
  @Override
  public void resume() {
    running = true;
    timer.start();
  }

  /**
   * Resets the animation back to it's starting state.
   */
  @Override
  public void restart() {
    tick = 0;
  }

  /**
   * Toggles whether or not the animation loops.
   */
  @Override
  public void loop() {
    if (loopOn) {
      loopOn = false;
    } else {
      loopOn = true;
    }
  }

  /**
   * Increases the speed of the animation.
   */
  @Override
  public void increaseSpeed() {
    if (ticksPerMillisecond - 5 > 0) {
      timer.stop();
      ticksPerMillisecond = ticksPerMillisecond - 5;
      timer.setDelay(ticksPerMillisecond);
    }
    timer.start();
  }

  /**
   * Decreases the speed of the animation.
   */
  @Override
  public void decreaseSpeed() {
    timer.stop();
    ticksPerMillisecond = ticksPerMillisecond + 5;
    timer.setDelay(ticksPerMillisecond);
    timer.start();
  }

  /**
   * Adds a shape to the animation.
   *
   * @param name the name of the shape to add.
   * @param type the type of the shape to add.
   */
  @Override
  public void addShape(String name, String type) {
    model.addShape(name, type);
  }

  /**
   * Removes a shape from the animation.
   *
   * @param shapeInfo a string representing the shape's name and type, formatted as "name type"
   */
  @Override
  public void removeShape(String shapeInfo) {
    String[] shapeStuff = shapeInfo.split(" ");
    model.removeShape(shapeStuff[0]);
  }

  /**
   * Adds a keyframe to the animation.
   *
   * @param shapeId the name of the shape to add the keyframe to.
   * @param tick    the tick of the new keyframe.
   * @param posX    the x position of the new keyframe.
   * @param posY    the y position of the new keyframe.
   * @param width   the width of the new keyframe.
   * @param height  the height of the new keyframe.
   * @param red     the red value of the new keyframe.
   * @param green   the green value of the new keyframe.
   * @param blue    the blue value of the new keyframe.
   */
  @Override
  public void addKeyFrame(String shapeId, int tick, int posX, int posY, int width, int height,
                          int red, int green, int blue) {
    IMotion motion = new Motion(tick, new Point(posX, posY), new Dimension(width, height),
            new Color(red, green, blue));
    model.addMotion(shapeId, motion);
  }

  /**
   * Removes a keyframe from this animation.
   *
   * @param shapeName    the name of the shape to remove the keyframe from.
   * @param keyFrameInfo a string of the keyframe's x, y width, height, r, g, and b values.
   */
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

}