import java.io.IOException;

public class Utils {

  public void tryAppend(Appendable ap, String... lines) {
    try {
      for (String s : lines) {
        ap.append(s + " ");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot append to Appendable.");
    }
  }
}
