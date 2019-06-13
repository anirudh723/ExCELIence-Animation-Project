package cs3500.animator.view;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;

public class SVGView extends AbstractView {

  SVGView(Appendable ap, Readable rd, int ticksPerSecond, Dimension canvas,
          LinkedHashMap<String, IAnimatableShapeReadOnly> shapes, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, canvas, shapes, model);
  }

  @Override
  public void render() {
    writeSVGTag();

    for (IAnimatableShapeReadOnly shape : this.shapes.values()) {
      String shapeType = handleType(shape.getType());
      String shapeColor = formatAsRGB(shape.getColor().getRed(),
              shape.getColor().getGreen(), shape.getColor().getBlue());
      String shapeName = getShapeName(shape);
      int shapeWidth = (int) shape.getDimension().getWidth();
      int shapeHeight = (int) shape.getDimension().getHeight();
      int shapeX = (int) shape.getPosition().getX();
      int shapeY = (int) shape.getPosition().getY();

      writeShapeSVG(shapeType, shapeColor, shapeName, shapeX, shapeY, shapeWidth, shapeHeight);

      writeMotionSVG(shape);

      tryAppend("  </" + shapeType + ">\n");
    }
    tryAppend("</svg>");
  }


  private void writeMotionSVG(IAnimatableShapeReadOnly shape) {
    for (int i = 0; i < shape.getMotions().size() - 1; i++) {

      IMotion fromMotion = shape.getMotions().get(i);
      IMotion toMotion = shape.getMotions().get(i + 1);

      int durInTicks = toMotion.getTick() - fromMotion.getTick();
      int durInSeconds = durInTicks * ticksPerSecond;

      String fromRGB = formatAsRGB(fromMotion.getColor().getRed(),
              fromMotion.getColor().getGreen(), fromMotion.getColor().getBlue());
      String toRGB = formatAsRGB(toMotion.getColor().getRed(),
              toMotion.getColor().getGreen(), toMotion.getColor().getBlue());

      int fromWidth = (int) fromMotion.getDimension().getWidth();
      int toWidth = (int) toMotion.getDimension().getWidth();

      int fromHeight = (int) fromMotion.getDimension().getHeight();
      int toHeight = (int) toMotion.getDimension().getHeight();

      int fromX = (int) fromMotion.getPosition().getX();
      int toX = (int) toMotion.getPosition().getY();

      int fromY = (int) fromMotion.getPosition().getX();
      int toY = (int) toMotion.getPosition().getY();


      //animate motion
      if (shape.getType() == "ellipse") {
        animate(fromMotion.getTick(), durInSeconds, "cx", fromX, toX, "freeze");
        animate(fromMotion.getTick(), durInSeconds, "cy", fromY, toY, "freeze");
      } else {
        animate(fromMotion.getTick(), durInSeconds, "x", fromX, toX, "freeze");
        animate(fromMotion.getTick(), durInSeconds, "y", fromY, toY, "freeze");
      }

      //animate transform
      animate(fromMotion.getTick(), durInSeconds, "width", fromWidth, toWidth, "freeze");
      animate(fromMotion.getTick(), durInSeconds, "height", fromHeight, toHeight, "freeze");

      //animate color
      animateColor(fromMotion.getTick(), durInSeconds, fromRGB, toRGB, "freeze");

    }
  }


  private void animate(int begin, int dur, String attributeName,
                       int from, int to, String fill) {
    if (dur != 0) {
      tryAppend(" <animate attributeType=\"xml\" begin=\""
              + begin + "s\" dur=\""
              + dur + "s\" attributeName=\""
              + attributeName + "\" from=\""
              + from + "\" to=\""
              + to + "\" fill=\""
              + fill + "\" /> \n");
    }
  }

  private void animateColor(int begin, int dur,
                            String from, String to, String fill) {
    if (dur != 0) {
      tryAppend(" <animate "
              + "attributeType=\"xml\" "
              + "begin=\"" + begin + "s\" "
              + "dur=\"" + dur + "s\" "
              + "attributeName=\"fill\" "
              + "values=\"" + from + ";" + to + "\" "
              + "fill=\"" + fill + "\" /> \n");
    }

  }


  private void writeShapeSVG(String shapeType, String shapeColor, String shapeName,
                             int shapeX, int shapeY, int shapeWidth, int shapeHeight) {
    if (shapeType == "ellipse") {
      tryAppend("<" + shapeType
              + " id=\"" + shapeName
              + "\" cx=\"" + shapeX
              + "\" cy=\"" + shapeY
              + "\" rx=\"" + shapeWidth / 2
              + "\" ry=\"" + shapeHeight / 2
              + "\" fill=\"" + shapeColor
              + "\" visibility=\"visible\" >\n");
    } else {
      tryAppend("<" + shapeType
              + " id=\"" + shapeName
              + "\" x=\"" + shapeX
              + "\" y=\"" + shapeY
              + "\" width=\"" + shapeWidth
              + "\" height=\"" + shapeHeight
              + "\" fill=\"" + shapeColor
              + "\" visibility=\"visible\" >\n");
    }
  }


  private void writeSVGTag() {
    tryAppend("<svg width=\"" + (int) canvas.getWidth() + "\" height= \""
            + (int) canvas.getHeight() + "\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\" >\n");
  }

  private String getShapeName(IAnimatableShapeReadOnly target) {
    for (String name : shapes.keySet()) {
      if (target == shapes.get(name)) {
        return name;
      }
    }
    return "";
  }

  private String handleType(String type) {
    if (type.contains("rect")) {
      return "rect";
    } else if (type.contains("ellipse")) {
      return "ellipse";
    } else throw new IllegalArgumentException("Illegal shape type.");
  }


  private String formatAsRGB(int r, int g, int b) {
    return "rgb(" + r + "," + g + "," + b + ")";
  }


  /**
   * Given a tick, converts to the time in miliseconds. NOTE: once this works, change the "begin"
   * attribute in the animate and animateColor methods and replace with this function
   */
  private int ticksToMiliseconds(int ticks) {
    return 0;//todo figure this out
  }

  public String getOutputAsString() {
    render();
    return in.toString();
  }
}
