package cs3500.animator.controller;

import java.util.ArrayList;

public interface Features {

  void start();

  void pause();

  void resume();

  void restart();

  void loop();

  void increaseSpeed();

  void decreaseSpeed();

  void addShape(String name, String type);

  void removeShape(String shapeInfo);

  void addKeyFrame(String shapeId, int tick, int posX, int posY, int width, int height,
      int red, int green, int blue);

  ArrayList<String> getKeyframes(String shapeInfo);

  void removeKeyFrame(String shapeId, String keyFrameInfo);

  void editKeyFrame(String keyFrameInfo);

  int getCurrentTick();

   String arrayToString(String[] list);

   int getTicksPerMilisecond();

}
