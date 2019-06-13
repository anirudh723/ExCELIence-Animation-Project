package cs3500.animator.view;

/**
 * Represents the functionality of a View.
 */
public interface IView {

  /**
   * Tweens the animation.....
   */
  void tweening();

  /**
   * Renders the animations into a view.
   */
  void render();

  /**
   * Appends the given lines to the Appendable.
   *
   * @param lines the String's to be appended
   * @throws IllegalArgumentException if it cannot append to the Appendable.
   */
  void tryAppend(String ... lines) throws IllegalArgumentException;



}
