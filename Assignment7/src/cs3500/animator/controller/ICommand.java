package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface ICommand extends ActionListener {

  @Override
  void actionPerformed(ActionEvent e);
}
