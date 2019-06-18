//package cs3500.animator.controller;
//
//import cs3500.animator.view.IView;
//import cucumber.api.java.ca.I;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//
//public class PauseCommand implements ICommand {
//  Timer timer;
//  int tick;
//  int maxTick;
//  int ticksPerMilisecond;
//  IController controller;
//  IView view;
//
//  public PauseCommand(int tick, int maxTick, int ticksPerMillisecond, Timer timer,
//      IController controller, IView view) {
//    this.tick = tick;
//    this.maxTick = maxTick;
//    this.ticksPerMilisecond = ticksPerMillisecond;
//    this.timer = timer;
//    this.controller = controller;
//    this.view = view;
//  }
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    this.timer = new Timer(this.ticksPerMilisecond, new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        if (tick < maxTick) {
//          List<ArrayList<String>> shapesToRender = getShapesAtTick(tick);
//          view.renderGUIShapes(shapesToRender);
//        } else {
//          tick = 1;
//        }
//      }
//    });
//  }
//
//  @Override
//  public int go(int tick) {
//    return 0;
//  }
//}
