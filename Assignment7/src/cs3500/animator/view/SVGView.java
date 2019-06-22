package cs3500.animator.view;

import cs3500.animator.model.IAnimatableShapeReadOnly;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * Produces a formatted textual description of the animation that is compliant with the SVG file
 * format. Works with a variety of output destinations.
 */
public class SVGView extends AbstractView {

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
  }

  /**
   * Renders the animations into a view. This is only supported by views that do not have visual
   * elements and do not show the animation in a window.
   *
   * @throws UnsupportedOperationException for views that render the shapes in a window with Java
   *                                       Swing, and therefore require a list of information about
   *                                       the shapes at every interval between keyframes.
   */
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

  /**
   * Append animate tags for all Motions for the given shape to this view's appendable. Represent
   * the given shape's Motions as SVG animations using the animate tag. Appends the SVG code to this
   * Appendable.
   *
   * @param shape the shape for which each Motion will be written.
   */
  private void writeMotionSVG(IAnimatableShapeReadOnly shape) {
    if (shape.getMotions().size() >= 1) {
      for (int i = 0; i < shape.getMotions().size() - 1; i++) {
        IMotion fromMotion = shape.getMotions().get(i);
        IMotion toMotion = shape.getMotions().get(i + 1);

        double durInTicks = toMotion.getTick() - fromMotion.getTick();
        int durInMS = ticksToMiliseconds(durInTicks);

        //convert r, g, and b values into an rgb(r, g, b) format for starting and ending color
        String fromRGB = formatAsRGB(fromMotion.getColor().getRed(),
                fromMotion.getColor().getGreen(), fromMotion.getColor().getBlue());
        String toRGB = formatAsRGB(toMotion.getColor().getRed(),
                toMotion.getColor().getGreen(), toMotion.getColor().getBlue());

        //declare starting and ending values for width, height, x and y
        int fromWidth = (int) fromMotion.getDimension().getWidth();
        int toWidth = (int) toMotion.getDimension().getWidth();

        int fromHeight = (int) fromMotion.getDimension().getHeight();
        int toHeight = (int) toMotion.getDimension().getHeight();

        int fromX = (int) fromMotion.getPosition().getX();
        int toX = (int) toMotion.getPosition().getX();

        int fromY = (int) fromMotion.getPosition().getY();
        int toY = (int) toMotion.getPosition().getY();

        //determine how to write the animate tag for the shape based on its type
        //animating shpae's x, y, width, and height
        String name = getShapeName(shape);
        String widthAttribute = "width";
        String heightAttribute = "height";
        String xAttribute = "x";
        String yAttribute = "y";
        if (shape.getType().contains("ellipse")) {
          widthAttribute = "rx";
          heightAttribute = "ry";
          xAttribute = "cx";
          yAttribute = "cy";
        }

        animate(fromMotion.getTick(), durInMS, xAttribute, fromX, toX, "freeze", name);
        animate(fromMotion.getTick(), durInMS, yAttribute, fromY, toY, "freeze", name);
        animate(fromMotion.getTick(), durInMS, widthAttribute,
                fromWidth, toWidth, "freeze", name);
        animate(fromMotion.getTick(), durInMS, heightAttribute,
                fromHeight, toHeight, "freeze", name);

        animateColor(fromMotion.getTick(), durInMS, fromRGB, toRGB, "freeze", name);
      }
    }
  }

  /**
   * Append the given information into an animate tag in SVG format to this view's appendable.
   * Animates one attribute of the shape, such as x, y, width, or height.
   *
   * @param begin         the time to start the animation, represented as a tick.
   * @param dur           the duration of the animation in miliseconds.
   * @param attributeName the name of the attribute to change, e.g. width, height, x, or y.
   * @param from          the beginning state of the shape's attribute.
   * @param to            the end state of the shape's attribute.
   * @param fill          whether to freeze the state of the shape after this animation or to set
   *                      the shape back to its original position.
   * @param name          the id of the shape to animate.
   */
  private void animate(int begin, int dur, String attributeName,
                       int from, int to, String fill, String name) {
    begin = ticksToMiliseconds((double) begin);

    if (attributeName.equals("rx") || attributeName.equals("ry")) {
      from = from / 2;
      to = to / 2;
    }

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

  /**
   * Append  SVG tags to Animate the color of a shape using the animate tag in SVG format to this
   * view's appendable.
   *
   * @param begin the time to start the animation, represented as a tick.
   * @param dur   the duration of the animation in miliseconds.
   * @param from  the beginning state of the shape's color, represented as a string in rgb format.
   * @param to    the ending state of the shape's color, represented as a string in rgb format.
   * @param fill  whether to freeze the state of the shape after this animation or to set the shape
   *              back to its original position.
   * @param name  the id of the shape to animate.
   */
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

  /**
   * Append a rect or ellipse tag for the shape, depending on its type, to this view's Appendable.
   * Includes information about the shape's size, position, color, and id.
   *
   * @param shapeType   the type of shape, rectangle or ellipse.
   * @param shapeColor  the initial color of the shape, represented as a string in rgb format.
   * @param shapeName   the unique id of the shape.
   * @param shapeX      the shape's x position.
   * @param shapeY      the shape's y position.
   * @param shapeWidth  the shape's width.
   * @param shapeHeight the shape's height.
   */
  private void writeShapeSVG(String shapeType, String shapeColor, String shapeName,
                             int shapeX, int shapeY, int shapeWidth, int shapeHeight) {
    if (shapeType.equals("ellipse")) {
      shapeWidth = shapeWidth / 2;
      shapeHeight = shapeHeight / 2;
    }

    tryAppend("<" + shapeType
            + " id=\"" + shapeName
            + "\" x=\"" + shapeX
            + "\" y=\"" + shapeY
            + "\" width=\"" + shapeWidth
            + "\" height=\"" + shapeHeight
            + "\" fill=\"" + shapeColor
            + "\" visibility=\"visible\" />\n");
  }


  /**
   * Appends the opening SVG tag to this view's Appendable.
   */
  private void writeSVGTag() {
    tryAppend("<svg width=\"" + (int) canvas.getWidth() + "\" height= \""
            + (int) canvas.getHeight() + "\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\""
            + "  xmlns:xlink=\"http://www.w3.org/1999/xlink\" >\n");
  }

  /**
   * Get the name of the given shape.
   *
   * @param target the shape to get the name of.
   * @return the name of the shape.
   */
  private String getShapeName(IAnimatableShapeReadOnly target) {
    for (String name : shapes.keySet()) {
      if (target.equals(shapes.get(name))) {
        return name;
      }
    }
    throw new IllegalArgumentException("shape name does not match");
  }

  /**
   * Determine if the given shape type is a valid type that can be rendered in SVG format.
   *
   * @param type the type of the shape.
   * @return the correct type of the shape.
   */
  private String handleType(String type) {
    if (type.contains("rect")) {
      return "rect";
    } else if (type.contains("ellipse")) {
      return "ellipse";
    } else {
      throw new IllegalArgumentException("Illegal shape type.");
    }
  }

  /**
   * Format the given numbers in rgb format rgb(r, g, b).
   *
   * @param r the red value of the color.
   * @param g the green value of the color.
   * @param b the blue value of the color.
   * @return the color as an rgb string.
   */
  private String formatAsRGB(int r, int g, int b) {
    return "rgb(" + r + "," + g + "," + b + ")";
  }


  /**
   * Given a tick, converts to the time in miliseconds.
   *
   * @param ticks the ticks to convert.
   * @return the ticks converted to miliseconds.
   */
  private int ticksToMiliseconds(double ticks) {
    double durInSeconds = (ticks) / ((double) ticksPerSecond);
    int durInMS = (int) Math.round(durInSeconds * 1000);
    return durInMS;
  }

  /**
   * Renders the shapes and their motions in SVG form and returns a String.
   *
   * @return the output String of the shapes and motions in svg form.
   */
  public String getOutputAsString() {
    render();
    return this.ap.toString();
  }

  /**
   * Gets the {@link ViewType} of the view. Can be TEXT, SVG, VISUAL, or EDIT
   *
   * @return the view type of the view.
   */
  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }
}
