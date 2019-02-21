Description of changes:

-Created AbstractMarbleSolitaireMarble class which abstracts many of the methods for the three different board models, and all board types extend the abstract class

-Removed a large number of methods from each of the extended classes (MarbleSolitaireModelImpl, EuropeanSolitaireModelImpl, TriangleSolitaireModelImpl). for European and English classes, removed all public methods and included only select helpers in the concrete classes. For Triangle class, removed most public methods except getGameState and isGameOver which required certain custom functionality to represent the board. 

-As far as actual functionality, did not have to make any changes to classes from older assignments. However I did refactor code slightly to avoid duplication (having all MarbleSolitaireModel constructors call their respective 3-argument constructor with default vals, abstracting logic to handle broader range of cases, etc.)

