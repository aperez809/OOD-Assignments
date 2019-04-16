# nguyenmayeux animation program

The program is structured as a Model-View-Controller application.

The model represents a the data within an animation, which we structured as series of shapes, each of which contains a series of changes that it would need to make unto itself when called upon a time tick. This model allows the ability to add new shape, get a shape by name, and get all the shapes in the model. 

It can advance itself using the tick() method, making the shapes reaches the next time frame.

The view represents a series of outputs that we can represents the animation in. As of now, we have a textual view, which would render the animation as a series text containing data of a shape at a frame from start to end; a SVG view, which will render the animation into SVG-style code; and a visual view, which will render the animation into a Java Swing JPanel. Each of this view has the option to write to a file or to the console its output.

The controller is the mediator between the model and the view. It prompts the model to advance, and inform the view to render. This controller structure avoid passing the whole model to the view itself, making it that the model is untouched by the view, and that multiple view could be representing the same model through the same controller behaviours.

The controller now regulates the ability to loop the animation on editor view, it will keep playing the model until paused. The model is modified to no longer remove the transition once it was performed, and instead maintain a index number to keep track of progress, in order to keep the animation loop-able.

The editor view is a JFrame that contains the visual view's JPanel within it. the editor view provides a playback panel at the bottom, with start/pause, restart, loop, and set speed. Each of these button triggers an event in the controller to respectively modifiy the model, thus modifying the view. The left and right panels are for adding/removing shapes and keyframes, each of which button will open a popup window for the user to input in the parameters for the shapes and keyframe to add/remove. The popup windows upon hitting OK, will inform the controller to modify the model accordingly.