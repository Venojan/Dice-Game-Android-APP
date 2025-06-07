# 🎲 Dice-Game-Android-APP

A simple Android **Dice Game** application built using **Kotlin** and **Jetpack Compose**.

## 📱 Features

✅ Main Menu  
✅ About Screen with student information  
✅ Dice Game Screen  
✅ Target score of 101 to win  
✅ Roll and reroll mechanics  
✅ Computer AI opponent with basic strategy  
✅ Win/Lose/Tie handling with AlertDialog  
✅ Dynamic background images  

---

## 🚀 How to Play

- **New Game**: Starts a new game where both the human player and the computer compete to reach a score of **101 or more**.
- On each turn:
    - Click **Throw** to roll 5 dice.
    - You can reroll your dice up to **3 times** (first throw + 2 rerolls max).
    - Once you are satisfied, click **Score** to add your dice total to your score.
    - The computer will automatically reroll its dice and score.
- The first player to reach or exceed **101 points** wins.
- If there is a tie, an automatic tie-breaker round occurs.

---

## 🎮 Game Screens

### Main Menu
- Button to start a new game.
- Button to view the about screen.

### Game Screen
- Displays target score.
- Displays current player and computer scores.
- Dice images for both players.
- Buttons to throw/reroll dice and score.
- Win/Lose/Tie alert when game ends.

### About Screen
- Displays student details and academic honesty statement in a dialog.

---

## 🛠️ Tech Stack

- **Language**: Kotlin  
- **UI Framework**: Jetpack Compose  
- **IDE**: Android Studio  

---

## 📂 Project Structure

```
MainActivity.kt
└── DiceGame() - Main composable that controls screen navigation
    ├── GameMenu() - Menu screen
    ├── AboutScreen() - About screen with AlertDialog
    ├── GameScreen() - Main gameplay screen
        ├── DiceRow() - Displays a row of dice images
        ├── DiceImage() - Helper function to map dice number to drawable resource
```

---

## 🎓 Author Info

- **Student ID**: 20230341  
- **Name**: Venojan Shanmugarajah  

✍️ _I confirm that I understand what plagiarism is and have read and understood the section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged._  

---

## 📜 License

This project is created for educational and academic purposes.

---

## 🙏 Acknowledgements

- Android Studio
- Jetpack Compose documentation
- Open source dice images used as drawable resources
