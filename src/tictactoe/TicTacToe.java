package tictactoe;

import java.util.Scanner;

/** Console Tic-Tac-Toe: two players, or one player against the computer. */
public class TicTacToe {

    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new TicTacToe().run();
    }

    private void run() {
        System.out.println("Tic-Tac-Toe");
        System.out.println("1) Two players");
        System.out.println("2) Play against the computer");
        int mode = readNumber("Choose a mode: ", 1, 2);

        ComputerPlayer computer = mode == 2 ? new ComputerPlayer('O') : null;
        Board board = new Board();
        char current = 'X';

        while (!board.isGameOver()) {
            System.out.println();
            System.out.print(board);

            int cell;
            if (computer != null && current == 'O') {
                cell = computer.chooseMove(board);
                System.out.println("Computer plays " + cell);
            } else {
                cell = readMove(board, current);
            }
            board.place(cell, current);
            current = current == 'X' ? 'O' : 'X';
        }

        System.out.println();
        System.out.print(board);
        char winner = board.winner();
        System.out.println(winner == Board.EMPTY ? "It's a draw!" : winner + " wins!");
    }

    private int readMove(Board board, char player) {
        while (true) {
            int cell = readNumber("Player " + player + ", choose a cell (1-9): ", 1, 9);
            if (board.isFree(cell)) {
                return cell;
            }
            System.out.println("That cell is taken. Try another one.");
        }
    }

    private int readNumber(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!in.hasNextLine()) {
                System.out.println();
                System.exit(0);
            }
            String line = in.nextLine().trim();
            try {
                int n = Integer.parseInt(line);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException ignored) {
                // fall through to the error message
            }
            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }
}
