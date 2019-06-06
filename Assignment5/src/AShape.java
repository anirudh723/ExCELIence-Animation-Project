import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class AShape implements IShape {
  protected int width;
  protected int height;
  protected Color color;
  protected Point2D position;
  protected Appendable ap;
  protected Utils utils;

  static List<AMotion> motions;

  public AShape(int width, int height, Color color, Point2D position, List<AMotion> motions) {
    utils = new Utils();
    if (width < 0) {
      throw new IllegalArgumentException("width must be positive");
    }
    this.width = width;
    if (height < 0) {
      throw new IllegalArgumentException("height must be positive");
    }
    this.height = height;
    this.color = color;
    if (position.getX() < 0 || position.getY() < 0) {
      throw new IllegalArgumentException("Position coordinates must be positive");
    }
    this.position = position;

    if (!ensureContinuity(motions)) {
      throw new IllegalArgumentException("Not continuous");
    } else {
      this.motions = motions;
    }
  }

   protected boolean ensureContinuity(List<AMotion> motions) {
     for (int i = 0; i < motions.size() - 2; i++) {
       if (motions.get(i).endTick != motions.get(i + 1).startTick) {
         return false;
       }
     }
     return true;
   }

  @Override
  public String writeShape() {
    for (AMotion motion : motions) {
      this.utils.tryAppend(this.ap, motion.toString(), "\n");
    }
    return ap.toString();
  }

  @Override
  public void applyMotions() {
    //applies all motions for this shape
    for (AMotion motion : motions) {
      this.apply(motion);
    }
  }

  protected void apply(AMotion motion) {
    //mutates this shape based on the given motion
//    loop through from start to end tick       return true;
    int moveDifferenceX = (int) (Math.abs(motion.endPos.getX() - motion.startPos.getX()));
    int moveDifferenceY = (int) Math.abs(motion.endPos.getY() - motion.startPos.getY());
    int widthDifference = motion.endWidth - motion.startWidth;
    int heightDifference = motion.endHeight - motion.startHeight;
    int redDifference = motion.endColor.getRed() - motion.startColor.getRed();
    int greenDifference = motion.endColor.getGreen() - motion.startColor.getGreen();
    int blueDifference = motion.endColor.getBlue() - motion.startColor.getBlue();
    int tickDifference = motion.endTick - motion.startTick;

    for(int i = 0; i < tickDifference; i++){
      if (moveDifferenceX > 0 || moveDifferenceY > 0) {
        double incremenentX = moveDifferenceX / tickDifference;
        double incremenentY = moveDifferenceY / tickDifference;
        this.position = new Point((int) (this.position.getX() + incremenentX),
                (int) (this.position.getY() + incremenentY));
      }

      if (widthDifference > 0 || heightDifference > 0) {
        double incremenentWidth = widthDifference / tickDifference;
        double incremenentHeight = heightDifference / tickDifference;
        this.width += incremenentWidth;
        this.height += incremenentHeight;
      }

      if (redDifference > 0 || greenDifference > 0 || blueDifference > 0) {
        double incremenentRed = redDifference / tickDifference;
        double incremenentGreen = greenDifference / tickDifference;
        double incremenentBlue = blueDifference / tickDifference;
        this.color = new Color((int) (this.color.getRed() + incremenentRed),
                (int) (this.color.getGreen() + incremenentGreen),
                (int) (this.color.getBlue() + incremenentBlue));
      }
    }
  }
}