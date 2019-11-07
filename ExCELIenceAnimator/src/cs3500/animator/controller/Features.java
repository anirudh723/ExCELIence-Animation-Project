package cs3500.animator.controller;

/**
 * Interface that holds the functionality of the playback and editing controls that are displayed in
 * the window of the {@link cs3500.animator.view.EditorView} animation.
 */
public interface Features {

  /**
   * Starts the animation.
   */
  void start();

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Resumes the animation after it has been paused.
   */
  void resume();

  /**
   * Resets the animation back to it's starting state.
   */
  void restart();

  /**
   * Toggles whether or not the animation loops.
   */
  void loop();

  /**
   * Increases the speed of the animation.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the animation.
   */
  void decreaseSpeed();

  /**
   * Adds a shape to the animation.
   *
   * @param name the name of the shape to add.
   * @param type the type of the shape to add.
   */
  void addShape(String name, String type);

  /**
   * Removes a shape from the animation.
   * @param shapeInfo a string representing the shape's name and type, formatted as "name type"
   */
  void removeShape(String shapeInfo);

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
  void addKeyFrame(String shapeId, int tick, int posX, int posY, int width, int height,
                   int red, int green, int blue);

  /**
   * Removes a keyframe from this animation.
   *
   * @param shapeName      the name of the shape to remove the keyframe from.
   * @param keyFrameInfo a string of the keyframe's x, y width, height, r, g, and b values.
   */
  void removeKeyFrame(String shapeName, String keyFrameInfo);
}