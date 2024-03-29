Anirudh Singh, Holly Christensen, Brian Fogarty

CS3500 ExCELIence Animation README file:

Assignment 7:

No changes in model.

We added a new View (Editor View), which took in the Visual View as a field, because this new view would build on top of it. It also took
in a read only version of the model so in our action listeners/helpers, we could get the map of shapes for example. It inherits the renderGUIShapes
method that was in the interface, and just calls the Visual view's renderGUIShapes. 
We then made a few classes as part of our GUI to handle different components:
  - Animation Controls handles the controls at the top of the GUI (start pause resume button etc). 
  - Editor Tabs is just for looks.
  - Shape Editor Tabs handles the controls of adding/removing/editing keyframes, shapes. 
  
The only change in our Visual View was we adjusted the size of the frame.

The biggest addition in this assignment other than the new view, was our Features interface. This had all the methods,
 such as start, pause, loop, increaseSpeed, etc. Our controller implemented that interface. Our Editor took in the features interface
 and so for our buttons for starting pausing etc we called the methods on the features. This went to the Controller, since it implements
 Features. This allowed us to be in the Controller and have access to the Timer, model, view, whatever we needed in order to reflect the 
 actions. 
 
 
 We added the new view to our View Factory class. With our factory, we did not even need to add anything to our Main in order to make it work
 for our new view. 


----------------------------------------------------------


Our Motion class is represented by a single tick, position, dimension, color, instead of a start and end for each.

We structured it this way because it reduces duplication of fields and code overall. This is also very beneficial

because it prevents the animation from having any overlaps or gaps, because there is no concept of start and end.

It's more of a Motion having a checkpoint: tick is when the Motion needs to start, and the next Motion in the list

will start when it's tick has begun.



We have an Abstract Animation Model, which generalizes all types of Animation models.

Used a LinkedHashMap, that associates an Animatable Shape with a ShapeID. This can help to inquire about a certain Animatable Shape.



We have an Abstract Shape, which consists of general characterists of a Shape.



We have an Animatable Shape, which consists of an Abstract Shape along with a List of Motions, since it's Animatable.



Invariants/Assumptions:

Tick cannot be negative

Width/Height cannot be negative

Positions cannot have negative coordinates

Shapes, Motions, Maps of Shapes, List of Motions cannot be null.

An animation's motions will never have overlaps or gaps (from our Motion implementation)

Our list of motions will be in order with regards to it's tick

Cannot add a shape if it already exists

Cannot add a motion to a shape that has a motion at the same tick

Cannot remove a shape if it already exist

Cannot remove a motion of a shape if it does not exist



We created a builder class for our AnimationModel so that information can be read from an input file and create a Model that way.



Our Textual View simply formats and outputs our model's data. The SVG view formats and translates the data from the model so that it can be rendered as an SVG. It outputs the data to an Appendable so that the file can be used as an SVG.



Our Visual view uses a Java Swing to render the visual animation.



We created a controller whose purpose is to keep track of the animation's ticks while running the VisualView.



In the controller, we included a method getAllShapesAtTick, which returns an arraylist of lists of data about each shape at the given tick, includig x, y, width, height, color, and type. The controller uses tweening to determine the exact state of each shape at each tick.

The controller interacts with the view to render the data collected from getAllShapesAtTick as shapes on a JFrame window.



We implemented Main so that a user can use command line inputs to specify which type of IView they would to use, as well as the input file, output file, and speed of the animation.



The model that the View and Controller use is a Read Only version of the model. We chose to do this so that the view and controller could not mutate the model's data in any way, and there would be no leaks of information. All of the shapes and information passed to the view and controller from the model is immutable.