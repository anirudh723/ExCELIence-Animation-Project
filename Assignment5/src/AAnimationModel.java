import java.util.ArrayList;
import java.util.List;

public abstract class AAnimationModel implements IAnimationModel{
  List<IShape> shapes;
  Appendable ap;
  Utils utils = new Utils();

  public AAnimationModel(List<IShape> shapes) {
    if (shapes == null) {
      throw new IllegalArgumentException("List of Shapes must be non-null.");
    }
    this.shapes = new ArrayList<IShape>();
  }

  @Override
  public void getDescription() {
    for(IShape shape : shapes){
      utils.tryAppend(this.ap, shape.writeShape()+ "\n");
    }
  }
}


