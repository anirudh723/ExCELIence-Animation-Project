import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public abstract class AMotion implements IMotion {
  protected int startTick;
  protected int endTick;
  protected Point2D startPos;
  protected Color startColor;
  protected int startWidth;
  protected int startHeight;
  protected Point2D endPos;
  protected Color endColor;
  protected int endWidth;
  protected int endHeight;

  protected Appendable ap;
  protected Utils utils;

  public AMotion(int startTick, int endTick, Point2D startPos, Color startColor, int startWidth,
                 int startHeight, Point2D endPos, Color endColor, int endWidth, int endHeight,
                 Appendable ap, Utils utils) {
    if(startTick > endTick){
      throw new IllegalArgumentException("start tick can't be greater than end tick");
    }
    if(startTick < 0 || endTick < 0){
      throw new IllegalArgumentException("start tick and end tick can't be negative");
    }
    this.startTick = startTick;
    this.endTick = endTick;
    this.startPos = startPos;

    if(startWidth < 0 || endWidth < 0 || startHeight < 0 || endHeight < 0){
      throw new IllegalArgumentException("height and width must be positive");
    }
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.endPos = endPos;
    this.startColor = startColor;
    this.endColor = endColor;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.ap = ap;
    this.utils = new Utils();
  }

  public void writeMotion(){
    utils.tryAppend(this.ap, "" + this.startTick + this.startPos.getX() +
            this.startPos.getY() + this.startWidth + this.startHeight +
            this.startColor.getRed() + this.startColor.getGreen() + this.startColor.getBlue()
            +  "\t" + this.endTick + this.endPos.getX() +
            this.endPos.getY() + this.endWidth + this.endHeight +
            this.endColor.getRed() + this.endColor.getGreen() + this.endColor.getBlue());
  }

}
