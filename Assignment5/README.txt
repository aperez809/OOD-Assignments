Changelist from Assignment 5 to Assignment 6

Actions:
Removed Action Subclasses ChangeColor, ChangeDims, and Move to handle all actions on a
shape in one Action class with Parameters that account for the shape's initial and final
dimensions, color, and location.
Added IAction interface to declare all methods defined in Action class.

Oval / Ellipse:
Renamed Oval Class to Ellipse to match shape description in given text files.

Shape:
Added checkOverlap, addAction, getActions, getShapeName, removeAction methods to Shape,
declared in Shape interface and implemented in AbstractShape / child classes.

METHODS TO ADD FOR ASSIGNMENT 7
- setTick that can replay animations
    - will also need to add a button to the Swing panel that can trigger this method

-