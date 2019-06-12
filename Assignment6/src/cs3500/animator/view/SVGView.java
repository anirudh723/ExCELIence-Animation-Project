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
    for(IAnimatableShapeReadOnly shape : this.shapes.values()){
      String type = handleType(shape.getType());
      tryAppend("<"+type+" x=\""+(int)shape.getPosition().getX()+"\" y=\""
              +(int)shape.getPosition().getY()+"\" width=\""+(int)shape.getDimension().getWidth()
              +"\" height=\""+(int)shape.getDimension().getHeight()+"\">\n");
      writeMotionSVG(shape);
      tryAppend("  </rect>");
    }
  }


  private void writeMotionSVG(IAnimatableShapeReadOnly shape){
    for(int i=0; i < shape.getMotions().size()-1; i++){
      IMotion fromMotion =shape.getMotions().get(i);
      IMotion toMotion =shape.getMotions().get(i+ 1);

      int ticks = fromMotion.getTick() -  toMotion.getTick();
      int tps = ticksToSeconds(ticks);

      String fromRGB = formatAsRGB(fromMotion.getColor().getRed(),
              fromMotion.getColor().getGreen(), fromMotion.getColor().getBlue() );
      String toRGB = formatAsRGB(toMotion.getColor().getRed(),
              toMotion.getColor().getGreen(), toMotion.getColor().getBlue() );

      int fromWidth = (int)fromMotion.getDimension().getWidth();
      int toWidth = (int)toMotion.getDimension().getWidth();

      int fromHeight = (int)fromMotion.getDimension().getHeight();
      int toHeight = (int)toMotion.getDimension().getHeight();


      tryAppend(
              "<path d=\"M "
              +(int)fromMotion.getPosition().getX()+","
              +(int)fromMotion.getPosition().getY() +
              " L " +(int)toMotion.getPosition().getX()+","
              +(int)toMotion.getPosition().getY()+"\"" +
               "id=\"motionPath\"/>");
      tryAppend("<animateMotion dur=\""+tps+"s\" reapeatCount=\"indefinite\"/>"+
              "<mpath xlink:href=\"#motionPath\"/> </animateMotion>");

      tryAppend("<animateColor attributeName=\"fill\" attributeType=\"XML\"\n" +
              "        from=\""+fromRGB+"\" to=\""+toRGB+"\" dur=\""+tps+"\" repeatCount=\"indefinite\"/>");

      tryAppend("<animateTransform attributeName=\"width\"\n" +
              "attributeType=\"XML\"\n" +
              "type=\"scale\"\n" +
              "from=\""+fromWidth+"\"\n" +
              "to=\""+toWidth+"\"\n" +
              "dur=\""+tps+"\"\n" +
              "repeatCount=\"indefinite\"/>");
      tryAppend("<animateTransform attributeName=\"height\"\n" +
              "attributeType=\"XML\"\n" +
              "type=\"scale\"\n" +
              "from=\""+fromHeight+"\"\n" +
              "to=\""+toHeight+"\"\n" +
              "dur=\""+tps+"\"\n" +
              "repeatCount=\"indefinite\"/>");
    }
  }

  private String handleType(String type){
    if(type.contains("rect")){
      return "rect";
    } else if(type.contains("ellipse") ){
      return "ellipse";
    }
    else throw new IllegalArgumentException("Illegal shape type.");
  }


  private String formatAsRGB(int r, int g, int b){
    return "rgb("+r+", "+g+", "+b+")";
  }


  private int ticksToSeconds(int ticks){
    return ticks * ticksPerSecond;
  }

  public String getOutputAsString(){
    render();
    return in.toString();
  }
}
