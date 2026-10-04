# Tic-Tac-Toe

A console Tic-Tac-Toe game in Java. Play against a friend, or against a computer opponent that never loses.

```
 X | O | 3
---+---+---
 4 | X | 6
---+---+---
 7 | 8 | O
Player X, choose a cell (1-9):
```

## Running

Requires Java 17 or newer. No build tool needed.

```bash
javac -d out src/tictactoe/*.java
java -cp out tictactoe.TicTacToe
```

## How it works

- **`Board`** stores the 9 cells and checks the 8 winning lines (3 rows, 3 columns, 2 diagonals).
- **`ComputerPlayer`** uses the **minimax** algorithm. It tries every possible continuation of the game, assuming both sides play their best, and picks the move with the best guaranteed outcome. Scores take search depth into account, so the computer prefers faster wins and slower losses.
- **`TicTacToe`** runs the game loop and validates input. It rejects non-numbers, out-of-range cells and cells that are already taken.

Tic-Tac-Toe is small enough (fewer than 9! = 362,880 move sequences) for minimax to search the whole game tree on every move. Playing every possible human strategy against the computer gives 569 distinct games: the computer wins 386, draws 183 and loses none.
