package cs3500.animator.controller;

import java.io.IOException;

/**
 * Mock Controller class that is used to test the action listeners of the Controller.
 */
public class MockController implements Features {
  private final Appendable appendable;

  /**
   * Contstructs the mock controller with the given appendable.
   *
   * @param appendable the appendable to append to.
   */
  public MockController(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void start() {
    try {
      appendable.append("started timer");
    } catch (IOException e) {
      System.out.println("failed to start timer");
    }

  }

  @Override
  public void pause() {
    try {
      appendable.append("paused timer");
    } catch (IOException e) {
      System.out.println("failed to pause timer");
    }

  }

  @Override
  public void resume() {
    try {
      appendable.append("resumed timer");
    } catch (IOException e) {
      System.out.println("failed to resume timer");
    }

  }

  @Override
  public void restart() {
    try {
      appendable.append("restarted timer");
    } catch (IOException e) {
      System.out.println("failed to restart timer");
    }
  }

  @Override
  public void loop() {
    try {
      appendable.append("toggled loop for  timer");
    } catch (IOException e) {
      System.out.println("failed to toggle loop for timer");
    }
  }

  @Override
  public void increaseSpeed() {
    try {
      appendable.append("increased tick rate");
    } catch (IOException e) {
      System.out.println("failed to increase tick rate");
    }
  }

  @Override
  public void decreaseSpeed() {
    try {
      appendable.append("decreased tick rate");
    } catch (IOException e) {
      System.out.println("failed to decrease tick rate");
    }
  }

  @Override
  public void addShape(String name, String type) {
    try {
      appendable.append("added a shape in editor: " + name + " " + type);
    } catch (IOException e) {
      System.out.println("failed to add a shape in editor");
    }
  }

  @Override
  public void removeShape(String shapeInfo) {
    try {
      appendable.append("removed a shape in editor: " + shapeInfo);
    } catch (IOException e) {
      System.out.println("failed to remove a shape in editor");
    }
  }

  @Override
  public void addKeyFrame(String shapeId, int tick, int posX, int posY, int width, int height,
                          int red, int green, int blue) {
    try {
      appendable.append("added a keyframe in editor: " + shapeId + " " + tick + " " + posX + " "
              + posY + " " + width + " " + height + " " + red + " " + green + " " + blue);
    } catch (IOException e) {
      System.out.println("failed to add a keyframe in editor");
    }
  }

  @Override
  public void removeKeyFrame(String shapeId, String keyFrameInfo) {
    try {
      appendable.append("removed a keyframe in editor: " + shapeId + " " + keyFrameInfo);
    } catch (IOException e) {
      System.out.println("failed to remove a keyframe in editor");
    }
  }

}
