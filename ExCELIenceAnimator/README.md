Built a user-interactive animator that graphically rendered 2D shapes to create interesting designs.
My main goal was to create a separation between how an algorithm or phenomenon is described 
and how it is drawn on a screen. I used the MVC architecture to design the structure of the animator.

Model - Represents the state of the program internally, and enforces the rules.
View - Displays the interface to the user.
Controller - Takes input from the user and decides what to do. The Controller
		bridges the model and view.

The MVC structure is essential here because it prevents coupling between the different components of the structure.
The user can never change the internal state of the model, which is very important. The model should also
not control the view. This prevents having to change the model repeatedly when a UI change needs to be made.
The Controller is what takes information regarding the state of the program from the Model and then sends the 
necessary information to the View for it to be rendered using the GUI (with Java Swing). 

Testing: Using Mock designs was crucial in the testing aspect. Making Mock Controllers and Mock Views allowed me to ensure 
that the correct buttons were being hit, the action listeners were properly working, etc. Testing exceptions was also important
to make sure if a user did something unexpected, then the program can handle it. The ability to imitate objects was very helpful.