package tictactoe;

/** A 3x3 board. Cells are numbered 1-9, left to right, top to bottom. */
public class Board {

    public static final char EMPTY = ' ';

    private static final int[][] LINES = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
        {0, 4, 8}, {2, 4, 6}             // diagonals
    };

    private final char[] cells = new char[9];

    public Board() {
        java.util.Arrays.fill(cells, EMPTY);
    }

    public boolean isFree(int cell) {
        return cell >= 1 && cell <= 9 && cells[cell - 1] == EMPTY;
    }

    public void place(int cell, char mark) {
        if (!isFree(cell)) {
            throw new IllegalArgumentException("Cell " + cell + " is not available");
        }
        cells[cell - 1] = mark;
    }

    public void clear(int cell) {
        cells[cell - 1] = EMPTY;
    }

    /** Returns 'X' or 'O' if that player has three in a row, otherwise EMPTY. */
    public char winner() {
        for (int[] line : LINES) {
            char first = cells[line[0]];
            if (first != EMPTY && first == cells[line[1]] && first == cells[line[2]]) {
                return first;
            }
        }
        return EMPTY;
    }

    public boolean isFull() {
        for (char c : cells) {
            if (c == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return winner() != EMPTY || isFull();
    }

    /** Draws the board, showing the cell number in each free cell. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            sb.append(' ');
            for (int col = 0; col < 3; col++) {
                int i = row * 3 + col;
                sb.append(cells[i] == EMPTY ? (char) ('1' + i) : cells[i]);
                if (col < 2) {
                    sb.append(" | ");
                }
            }
            sb.append('\n');
            if (row < 2) {
                sb.append("---+---+---\n");
            }
        }
        return sb.toString();
    }
}
