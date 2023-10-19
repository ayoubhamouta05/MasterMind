# MasterMind
## How to Play
The game provides you with an opportunity to guess a secret color combination.
Select colors for each of the five positions.
Click the "Check Color" button to see how you did.
Red color means a color is in the right place.
White color means a color is correct but in the wrong place.
No color means the color is not part of the solution.
You win if you correctly guess the combination or lose after 10 tries.
## Documentation
Code Overview
PlayActivity: The main activity that manages the game.
CheckColorsAdapter and TryColorsAdapter: RecyclerView adapters for displaying check and try colors.
CheckColorsData and TryColorsData: Data classes to hold information for RecyclerView items.
fillTheTable: Function to generate a random color combination.
showBottomSheet: Function to show the color selection bottom sheet.
setupBottomSheetClickListener: Function to set up click listeners for the bottom sheet.
setUserTrying: Function to check user-selected colors and display results.
removeDuplicatedColors: Function to handle duplicate colors in the result.
showWinDialog and showFailureDialog: Functions to display game result dialogs.
showRestartDialog: Function to show a dialog for restarting the game.

<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/af75b961-6863-47b1-9c79-6ee0a5e2f043" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/44d0b4d6-cf0e-46ff-b14f-c4707a527b1f" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/c611a58d-180f-4cef-8775-19f65cdc853c" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/24bacbd8-5060-4682-b85b-507b62949f56" width="300" />
<img src="https://github.com/ayoubhamouta05/MasterMind/assets/103429679/bf43f02a-ba6f-475d-982a-31f7d65d0ee5" width="300" />

