package cs3500.animator.view;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * Tests the Mock Editor View class to ensure that the action listeners work.
 */
public class MockEditorViewTest {
  Appendable appendable;
  IView view;

  /**
   * Initialize the appendable.
   */
  public void init() {
    appendable = new StringBuilder();
  }

  /**
   * Test starting the animation.
   */
  @Test
  public void renderGUIShapes1() {
    init();
    view = new MockEditorView(new StringReader("start"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("started", appendable.toString());
  }

  /**
   * Test pausing the animation.
   */
  @Test
  public void renderGUIShapes2() {
    init();
    view = new MockEditorView(new StringReader("pause"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("paused", appendable.toString());
  }

  /**
   * Test resuming the animation.
   */
  @Test
  public void renderGUIShapes3() {
    init();
    view = new MockEditorView(new StringReader("resume"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("resumed", appendable.toString());
  }

  /**
   * Test restarting the animation.
   */
  @Test
  public void renderGUIShapes4() {
    init();
    view = new MockEditorView(new StringReader("restart"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("restarted", appendable.toString());
  }

  /**
   * Test looping the animation.
   */
  @Test
  public void renderGUIShapes5() {
    init();
    view = new MockEditorView(new StringReader("loop"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("looped", appendable.toString());
  }

  /**
   * Test increasing the speed.
   */
  @Test
  public void renderGUIShapes6() {
    init();
    view = new MockEditorView(new StringReader("increaseSpeed"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("increased", appendable.toString());
  }

  /**
   * Test decreasing the speed.
   */
  @Test
  public void renderGUIShapes7() {
    init();
    view = new MockEditorView(new StringReader("decreaseSpeed"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("decreased", appendable.toString());
  }

  /**
   * Test adding a shape.
   */
  @Test
  public void renderGUIShapes8() {
    init();
    view = new MockEditorView(new StringReader("addShape"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("addedShape", appendable.toString());
  }

  /**
   * Test adding a keyframe.
   */
  @Test
  public void renderGUIShapes10() {
    init();
    view = new MockEditorView(new StringReader("addKeyframe"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("addedKeyframe", appendable.toString());
  }

  /**
   * Test removing a keyframe.
   */
  @Test
  public void renderGUIShapes11() {
    init();
    view = new MockEditorView(new StringReader("removeKeyframe"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("removedKeyframe", appendable.toString());
  }

  /**
   * Test removing a shape.
   */
  @Test
  public void renderGUIShapes9() {
    init();
    view = new MockEditorView(new StringReader("removeShape"), appendable);
    view.renderGUIShapes(new ArrayList<ArrayList<String>>());
    assertEquals("removedShape", appendable.toString());
  }

}