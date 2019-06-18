package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * Produces a formatted textual description of the animation that is compliant with the SVG file
 * format. Works with a variety of output destinations.
 */
public class SVGView extends AbstractView {
  ViewType type;

  /**
   * Constructs an SVG view.
   *
   * @param ap             the Appendable.
   * @param rd             the Readable.
   * @param ticksPerSecond ticks per second.
   * @param model          the read only version of the model.
   * @throws IllegalArgumentException if Appendable is null.
   * @throws IllegalArgumentException if Readable is null.
   * @throws IllegalArgumentException if the ticks per second is negative.
   * @throws IllegalArgumentException if the dimension is null.
   * @throws IllegalArgumentException if the model is null.
   * @throws IllegalArgumentException the shapes are null.
   */
  public SVGView(Appendable ap, Readable rd, int ticksPerSecond, IReadOnlyAnimationModel model) {
    super(ap, rd, ticksPerSecond, model);
    type = ViewType.SVG;
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
    }

    for (IAnimatableShapeReadOnly shape : this.shapes.values()) {
      writeMotionSVG(shape);
    }
    tryAppend("</svg>");
  }

  private void writeMotionSVG(IAnimatableShapeReadOnly shape) {
    if (shape.getMotions().size() >= 1) {
      for (int i = 0; i < shape.getMotions().size() - 1; i++) {
        IMotion fromMotion = shape.getMotions().get(i);
        IMotion toMotion = shape.getMotions().get(i + 1);

        double durInTicks = toMotion.getTick() - fromMotion.getTick();
        int durInMS = ticksToMiliseconds(durInTicks);

        String fromRGB = formatAsRGB(fromMotion.getColor().getRed(),
                fromMotion.getColor().getGreen(), fromMotion.getColor().getBlue());
        String toRGB = formatAsRGB(toMotion.getColor().getRed(),
                toMotion.getColor().getGreen(), toMotion.getColor().getBlue());

        int fromWidth = (int) fromMotion.getDimension().getWidth();
        int toWidth = (int) toMotion.getDimension().getWidth();

        int fromHeight = (int) fromMotion.getDimension().getHeight();
        int toHeight = (int) toMotion.getDimension().getHeight();

        int fromX = (int) fromMotion.getPosition().getX();
        int toX = (int) toMotion.getPosition().getX();

        int fromY = (int) fromMotion.getPosition().getY();
        int toY = (int) toMotion.getPosition().getY();

        String name = getShapeName(shape);
        if (shape.getType().contains("ellipse")) {
          animate(fromMotion.getTick(), durInMS, "cx", fromX, toX, "freeze", name);
          animate(fromMotion.getTick(), durInMS, "cy", fromY, toY, "freeze", name);
        } else {
          animate(fromMotion.getTick(), durInMS, "x", fromX, toX, "freeze", name);
          animate(fromMotion.getTick(), durInMS, "y", fromY, toY, "freeze", name);
        }
        animate(fromMotion.getTick(), durInMS, "width",
                fromWidth, toWidth, "freeze", name);
        animate(fromMotion.getTick(), durInMS, "height",
                fromHeight, toHeight, "freeze", name);
        animateColor(fromMotion.getTick(), durInMS, fromRGB, toRGB, "freeze", name);
      }
    }
  }


  private void animate(int begin, int dur, String attributeName,
                       int from, int to, String fill, String name) {
    begin = ticksToMiliseconds((double) begin);

    if (dur > 0) {
      tryAppend(" <animate "
              + "xlink:href=\"#" + name + "\" "
              + "attributeType=\"xml\" "
              + "begin=\"" + begin + "ms\" "
              + "dur=\"" + dur + "ms\" "
              + "attributeName=\"" + attributeName + "\" "
              + "from=\"" + from + "\" "
              + "to=\"" + to + "\" "
              + "fill=\"" + fill + "\" /> \n");
    }
  }

  private void animateColor(int begin, int dur, String from, String to, String fill, String name) {
    begin = ticksToMiliseconds((double) begin);

    if (dur != 0) {
      tryAppend(" <animate "
              + "xlink:href=\"#" + name + "\" "
              + "attributeType=\"xml\" "
              + "begin=\"" + begin + "ms\" "
              + "dur=\"" + dur + "ms\" "
              + "attributeName=\"fill\" "
              + "values=\"" + from + ";" + to + "\" "
              + "fill=\"" + fill + "\" /> \n");
    }
  }


  private void writeShapeSVG(String shapeType, String shapeColor, String shapeName,
                             int shapeX, int shapeY, int shapeWidth, int shapeHeight) {
    if (shapeType.equals("ellipse")) {
      tryAppend("<" + shapeType
              + " id=\"" + shapeName
              + "\" cx=\"" + shapeX
              + "\" cy=\"" + shapeY
              + "\" rx=\"" + shapeWidth / 2
              + "\" ry=\"" + shapeHeight / 2
              + "\" fill=\"" + shapeColor
              + "\" visibility=\"visible\" />\n");

    } else {
      tryAppend("<" + shapeType
              + " id=\"" + shapeName
              + "\" x=\"" + shapeX
              + "\" y=\"" + shapeY
              + "\" width=\"" + shapeWidth
              + "\" height=\"" + shapeHeight
              + "\" fill=\"" + shapeColor
              + "\" visibility=\"visible\" />\n");

    }
  }

  private void writeSVGTag() {
    tryAppend("<svg width=\"" + (int) canvas.getWidth() + "\" height= \""
            + (int) canvas.getHeight() + "\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\"  xmlns:xlink=\"http://www.w3.org/1999/xlink\" >\n");
  }

  private String getShapeName(IAnimatableShapeReadOnly target) {
    for (String name : shapes.keySet()) {
      if (target.equals(shapes.get(name))) {
        return name;
      }
    }
    throw new IllegalArgumentException("shape name does not match");
  }

  private String handleType(String type) {
    if (type.contains("rect")) {
      return "rect";
    } else if (type.contains("ellipse")) {
      return "ellipse";
    } else {
      throw new IllegalArgumentException("Illegal shape type.");
    }
  }

  private String formatAsRGB(int r, int g, int b) {
    return "rgb(" + r + "," + g + "," + b + ")";
  }


  /**
   * Given a tick, converts to the time in miliseconds. NOTE: once this works, change the "begin"
   * attribute in the animate and animateColor methods and replace with this function
   */
  private int ticksToMiliseconds(double ticks) {
    double durInSeconds = (ticks) / ((double) ticksPerSecond);
    int durInMS = (int) Math.round(durInSeconds * 1000);
    return durInMS;
  }

  /**
   * Renders the shapes and their motions in svg form and returns a String.
   *
   * @return the output String of the shapes and motions in svg form.
   */
  public String getOutputAsString() {
    render();
    return this.ap.toString();
  }
}
