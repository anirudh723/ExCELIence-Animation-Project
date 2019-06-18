package cs3500.animator.controller;

import java.awt.event.ActionEvent;

public class RewindCommand implements ICommand {

  public RewindCommand() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Rewind button is clicked");
  }
}
