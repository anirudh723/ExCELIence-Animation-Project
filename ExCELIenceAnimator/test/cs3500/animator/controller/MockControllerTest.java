package cs3500.animator.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the Mock Controller class, to ensure that the controller's action listeners work.
 */
public class MockControllerTest {

  private Appendable appendable;
  private Features testController;

  /**
   * Initialize the views, model, mock controller, and appendable.
   */
  @Before
  public void initMockController() {
    appendable = new StringBuilder();
    testController = new MockController(appendable);
  }

  /**
   * Test the controller's start.
   */
  @Test
  public void testEditorStart() {
    testController.start();
    assertEquals("started timer", appendable.toString());
  }

  /**
   * Test the controller's pause.
   */
  @Test
  public void testEditorPause() {
    testController.pause();
    assertEquals("paused timer", appendable.toString());
  }


  /**
   * Test the controller's resume.
   */
  @Test
  public void testEditorResume() {
    testController.resume();
    assertEquals("resumed timer", appendable.toString());
  }

  /**
   * Test the controller's restart.
   */
  @Test
  public void testEditorRestart() {
    testController.restart();
    assertEquals("restarted timer", appendable.toString());
  }

  /**
   * Test the controller's loop.
   */
  @Test
  public void testEditorLoop() {
    testController.loop();
    assertEquals("toggled loop for  timer", appendable.toString());
  }

  /**
   * Test the controller's increase speed.
   */
  @Test
  public void testEditorIncreaseSpeed() {
    testController.increaseSpeed();
    assertEquals("increased tick rate", appendable.toString());
  }

  /**
   * Test the controller's decrease speed.
   */
  @Test
  public void testEditorDecreaseSpeed() {
    testController.decreaseSpeed();
    assertEquals("decreased tick rate", appendable.toString());
  }

  /**
   * Test the controller's add shape.
   */
  @Test
  public void addShape() {
    testController.addShape("very cool shape", "ellipse");
    assertEquals("added a shape in editor: very cool shape ellipse",
            appendable.toString());
  }

  /**
   * Test the controller's remove shape.
   */
  @Test
  public void removeShape() {
    testController.removeShape("very cool shape ellipse");
    assertEquals("removed a shape in editor: very cool shape ellipse",
            appendable.toString());
  }

  /**
   * Test the controller's add keyframe.
   */
  @Test
  public void addKeyFrame() {
    testController.addKeyFrame("very cool shape", 10, 10, 10,
            10, 10, 10, 10, 10);
    assertEquals("added a keyframe in editor: very cool shape 10 10 10 10 10 10 10 10",
            appendable.toString());
  }

  /**
   * Test the controller's remove keyframe.
   */
  @Test
  public void removeKeyFrame() {
    testController.removeKeyFrame("very cool shape",
            "10 10 10 10 10 10 10 10");
    assertEquals("removed a keyframe in editor: very cool shape 10 10 10 10 10 10 10 10",
            appendable.toString());
  }
}