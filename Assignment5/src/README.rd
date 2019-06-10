Anirudh Singh, Holly Christensen
CS3500 Assignment 5 README file:

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



We included a method getAllShapesAtTick, which takes in a specific tick and returns a List of Shapes. We thought
this may be helpful later when we do the View. It's important to be able to get the state of the shapes at any time of the game.
We have not implemented it yet.