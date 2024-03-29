package cs3500.animator;

import cs3500.animator.model.AnimationModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;


/**
 * Class housing the entry point for the Animation program.
 */
public class Excellence {

  /**
   * Entry point for the Animation program.
   *
   * @param args command line arguments used to configure animation.
   */
  public static void main(String[] args) {

    // HashMap to accumulate inputs
    HashMap<String, String> config = new HashMap<>(4);
    // ArrayList to more easily identify valid prefixes
    ArrayList<String> validPrefix = new ArrayList<>(
            Arrays.asList("-in", "-view", "-out", "-speed"));

    // Parse command line, extracting specified inputs
    for (int i = 0; i < args.length - 1; i += 2) {
      String curString = args[i];
      String nextString = args[i + 1];

      // catches command line invalid prefixes and inputs, and ordering
      if (!validPrefix.contains(curString)) {
        JOptionPane.showMessageDialog(new JFrame(), curString + " is an invalid prefix!",
                "Invalid inputs!", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      } else if (config.containsKey(curString)) {
        JOptionPane.showMessageDialog(new JFrame(), curString + " was already set!",
                "Invalid inputs!", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      } else if (validPrefix.contains(nextString)) {
        JOptionPane.showMessageDialog(new JFrame(), curString + " cannot be immediately"
                        + " followed by prefix " + nextString + "!", "Invalid inputs!",
                JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      } else {
        config.put(curString, nextString);
      }
    }

    // Validate command line input values, ...
    if (!config.containsKey("-in")) {
      JOptionPane.showMessageDialog(new JFrame(), "-in is required!",
              "Invalid inputs!", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    } else if (!config.containsKey("-view")) {
      JOptionPane.showMessageDialog(new JFrame(), "-view is required!",
              "Invalid inputs!", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    } else {

      // ... instantiate AnimationModel, ReadOnlyAnimationModel and Controller; runs animation
      try {

        Readable input = new FileReader(config.get("-in"));
        AnimationModel model = AnimationReader.parseFile(input,
                new AnimationModelImpl.Builder());
        IReadOnlyAnimationModel readModel = new ReadOnlyAnimationModel(model);

        int ticksPerSec = Integer.parseInt(config.getOrDefault("-speed", "1"));
        String filePath = config.get("-out");
        if (filePath == null) {
          // Runs the controller, printing the result
          IView view = new ViewFactory().create(System.out, input, config.get("-view"),
                  ticksPerSec, model);
          IController controller = new Controller(model, view);
          controller.run();
        } else {
          // Creates an appendable FileWriter, runs the controller, closes the FileWriter,
          // writing the result to the target output file
          FileWriter output = new FileWriter(config.get("-out"), true);
          IView view = new ViewFactory().create(output, input, config.get("-view"),
                  ticksPerSec, model);
          IController controller = new Controller(model, view);
          controller.run();
          output.close();
        }

        // catches non-int speed, invalid input and output files, and any possible
        // AnimationModel, ReadOnlyAnimationModel, IView, Controller initialization or run errors
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(new JFrame(), "-speed value must be an int!",
                "Initialization error!", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(new JFrame(), "File error: " + e.getMessage(),
                "IOException!", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!",
                JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      }
    }
  }
}