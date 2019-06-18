package cs3500.animator.controller;

import java.awt.event.ActionEvent;

public class StartCommand implements ICommand {
  int tick;

  public StartCommand() {
    //this.tick = tick;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    tick = 1;
    System.out.println("Start button is clicked");
  }
}
