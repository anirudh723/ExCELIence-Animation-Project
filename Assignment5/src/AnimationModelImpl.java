import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents an Implementation of an Animation Model.
 */
public class AnimationModelImpl extends AbstractAnimationModel {

  /**
   * Constructs an implementation of an Animation Model.
   *
   * @param shapes the map of shapes of the model.
   * @throws IllegalArgumentException if the LinkedHashMap of shapes is null.
   */
  public AnimationModelImpl(LinkedHashMap<Integer, IAnimatableShape> shapes) {
    super(shapes);
  }

  @Override
  public List<IShape> getAllShapesAtTick(int tick) {
    return null;
  }

}
