package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.util.AnimationReader;
import java.awt.Dimension;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

public class VisualViewTest {
  private static FileReader generateFile() {
    try {
      return new FileReader("C:\\Users\\Anirudh\\IdeaProjects\\Assignments(Github)\\CS3500TeamAssignments\\ExCELIence-Animation-Project\\Assignment6\\src\\givenFile.rtf");
    } catch (IOException e) {
      System.out.print("oops");
    }
    return null;
  }

  private static String readTgtFileAsString() {
    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Anirudh\\IdeaProjects\\Assignments(Github)\\CS3500TeamAssignments\\ExCELIence-Animation-Project\\Assignment6\\src\\tgtFile.rtf")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return text;
  }

  private FileReader file = generateFile();
  private AnimationModel model1 = AnimationReader.parseFile(file, new AnimationModelImpl.Builder());
  IReadOnlyAnimationModel readOnlyAnimationModel = new ReadOnlyAnimationModel(model1);
  IView view = new VisualView(new Dimension(500, 500));
  private IController model1_viewer = new Controller(readOnlyAnimationModel, view);

}
