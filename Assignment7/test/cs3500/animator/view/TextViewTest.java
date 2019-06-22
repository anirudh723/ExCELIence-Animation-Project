package cs3500.animator.view;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.util.AnimationReader;

/**
 * Represents the test for the textual view.
 */
public class TextViewTest {

  private FileWriter closedWriter;
  private FileWriter openWriter;
  private FileReader emptyCanvas;
  private FileReader emptyShapes;
  private FileReader givenReadable;

  /**
   * Intialize the text files to write to and read from for the tests.
   */
  private void setFiles() {
    try {
      closedWriter = new FileWriter("test2.txt", true);
      closedWriter.close();
      openWriter = new FileWriter("test2.txt", true);
      emptyCanvas = new FileReader("TTemptyCanvas.txt");
      emptyShapes = new FileReader("TTemptyShapes.txt");
      givenReadable = new FileReader("givenFile.txt");
    } catch (IOException e) {
      System.out.println("setFiles error!");
    }
  }

  /**
   * Reads from a file.
   *
   * @param encoding the encoding.
   * @return The file as a string.
   */
  private static String readFile(Charset encoding) {
    String result = "";
    try {
      byte[] encoded = Files.readAllBytes(Paths.get("givenFile.txt"));
      result = new String(encoded, encoding);
    } catch (IOException e) {
      System.out.println("read file error!");
    }
    return result;
  }


  /**
   * Ensures TextView constructor throws errors for all possible illegal arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewConstructorExceptions() {
    setFiles();
    /*
    ILLEGAL ARGUMENTS:
    1. null Appendable ap
    2. null Readable rd
    3. negative int ticksPerSecond
    4. null Dimension dimension
    5. negative Dimension dimension.width()
    6. negative Dimension dimension.height()
    7. null IReadOnlyModel model
    */
    IReadOnlyAnimationModel givenModel
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.givenReadable,
        new AnimationModelImpl.Builder()));
    setFiles();
    IView nullAp = new TextView(null, givenReadable, 1, givenModel);
    IView nullRd = new TextView(openWriter, null, 1, givenModel);
    IView negativeTick = new TextView(openWriter, givenReadable, -10, givenModel);
    IView nullDimension = new TextView(openWriter, givenReadable, 1, givenModel);
    IView negativeWidth = new TextView(openWriter, givenReadable, 1, givenModel);
    IView negativeHeight = new TextView(openWriter, givenReadable, 1, givenModel);
    IView nullModel = new TextView(openWriter, givenReadable, 1, null);
  }

  /**
   * Ensures TextView tryAppend(...) throws error when FileWriter is closed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAppend() {
    setFiles();
    IReadOnlyAnimationModel givenModel
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.givenReadable,
        new AnimationModelImpl.Builder()));
    setFiles();
    IView appendError = new TextView(closedWriter, givenReadable, 1, givenModel);
    appendError.tryAppend("this", "throws", "an", "error");
  }

  /**
   * Ensures TextView render() functionality for all edge cases.
   */
  @Test
  public void testRender() {
    setFiles();

    StringBuilder emptyCanvasOut = new StringBuilder();
    IReadOnlyAnimationModel emptyCanvas
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.emptyCanvas,
        new AnimationModelImpl.Builder()));
    IView emptyRender = new TextView(emptyCanvasOut, this.emptyCanvas,
        1, emptyCanvas);
    emptyRender.render();
    Assert.assertEquals("canvas 200 70 360 360", emptyCanvasOut.toString());

    StringBuilder emptyShapesOut = new StringBuilder();
    IReadOnlyAnimationModel emptyShapes
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.emptyShapes,
        new AnimationModelImpl.Builder()));
    IView emptyShapesRender = new TextView(emptyShapesOut, this.emptyShapes, 1,
        emptyShapes);
    emptyShapesRender.render();
    Assert.assertEquals("canvas 200 70 360 360\n"
        + "shape R rectangle\n" + "shape C ellipse", emptyShapesOut.toString());

    StringBuilder givenOut = new StringBuilder();
    IReadOnlyAnimationModel givenModel
        = new ReadOnlyAnimationModel(AnimationReader.parseFile(this.givenReadable,
        new AnimationModelImpl.Builder()));
    IView givenView = new TextView(givenOut, this.givenReadable, 1, givenModel);
    givenView.render();
    Assert.assertEquals(readFile(
        Charset.defaultCharset()), givenOut.toString());
  }
}