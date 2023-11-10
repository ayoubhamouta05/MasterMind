# MasterMind
## How to Play
The game provides you with an opportunity to guess a secret color combination.
Select colors for each of the five positions.
Click the "Check Color" button to see how you did.
Red color means a color is in the right place.
White color means a color is correct but in the wrong place.
No color means the color is not part of the solution.
You win if you correctly guess the combination or lose after 10 tries.

## Difficulty Levels
Easy: 5 chances, correct arrangement is crucial, no duplicates allowed.
Medium: 8 chances, correct arrangement is crucial, duplicates allowed.
Hard: 15 chances, arrangement is not crucial, no duplicates allowed.
Expert: 20 chances, arrangement is not crucial, duplicates allowed.

## Documentation
- **PlayActivity:** The main activity that manages the game.
- **CheckColorsAdapter and TryColorsAdapter:** RecyclerView adapters for displaying check and try colors.
- **CheckColorsData and TryColorsData:** Data classes to hold information for RecyclerView items.
- **fillTheTable:** Function to generate a random color combination.
- **showBottomSheet:** Function to show the color selection bottom sheet.
- **setupBottomSheetClickListener:** Function to set up click listeners for the bottom sheet.
- **setUserTrying:** Function to check user-selected colors and display results.
- **removeDuplicatedColors:** Function to handle duplicate colors in the result.
- **showWinDialog and showFailureDialog:** Functions to display game result dialogs.
- **showRestartDialog:** Function to show a dialog for restarting the game.

### Main Activity
**PlayActivity:** Manages the game and initializes gameplay based on selected difficulty levels.
### Adapters
- **CheckColorsAdapter and TryColorsAdapter:** RecyclerView adapters for displaying check and try colors.
### Functions
- **fillTheTable:** Generate a random color combination based on the selected difficulty level.

- **showBottomSheet:** Display a bottom sheet for color selection.

- **setUserTrying:** Check user-selected colors and display results on the game board.

- **removeDuplicatedColors:** Handle duplicate colors in the result to provide accurate feedback.

- **showWinDialog and showFailureDialog:** Display dialogs indicating whether the player has won or lost, including the number of attempts.

- **showRestartDialog:** Show a dialog allowing the player to restart the game.

- **play:** Main gameplay function that orchestrates the game flow and user interactions.


- **Color Selection Functions:** selectColor, getItemSelected, and setupBottomSheetClickListener: Map color selections to actions.

- **Game Management Functions:** playAgain, setAllItemsEmpty, showBottomSheet, and controlRVs: Manage game state and UI interactions.

- **Validation Functions:** checkIfColorsChosenAreNotEmpty and checkDuplicatedColorsAreNotMoreThanTwo: Validate user inputs.

### Randomization and Logic Functions
- **fillTheTableWithoutRep and fillTheTableWith2Rep:** Generate a random color combination with or without duplicates.
### Duplicate Check and Sorting Function
- **removeDuplicatedCheckColors:** Determine the place and existence of colors, considering duplicates and sorting based on the difficulty level

## Screenshots

<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/29dbd5d4-ceb1-43e1-9b5b-abfecaf50f59" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/353b16d9-10b8-43b7-9a6d-f013ba35da55" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/51274408-acfe-488c-9cae-5b59b044a710" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/fe8e0705-8c33-40fb-b9c9-5790f903b38f" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/5b4c3c76-0dfd-4b3f-91bc-a88497e3e69a" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/a56feaf6-2867-43af-a800-0c766061553c" width="300" />




