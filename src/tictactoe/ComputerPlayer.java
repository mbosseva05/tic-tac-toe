package tictactoe;

/** Picks moves with minimax, so it never loses. */
public class ComputerPlayer {

    private final char me;
    private final char opponent;

    public ComputerPlayer(char me) {
        this.me = me;
        this.opponent = me == 'X' ? 'O' : 'X';
    }

    public int chooseMove(Board board) {
        int bestCell = -1;
        int bestScore = Integer.MIN_VALUE;
        for (int cell = 1; cell <= 9; cell++) {
            if (board.isFree(cell)) {
                board.place(cell, me);
                int score = minimax(board, false, 1);
                board.clear(cell);
                if (score > bestScore) {
                    bestScore = score;
                    bestCell = cell;
                }
            }
        }
        return bestCell;
    }

    /**
     * Scores the board from the computer's point of view: positive if it wins, negative if it loses,
     * 0 for a draw. Subtracting the depth prefers faster wins and slower losses.
     */
    private int minimax(Board board, boolean myTurn, int depth) {
        char winner = board.winner();
        if (winner == me) {
            return 10 - depth;
        }
        if (winner == opponent) {
            return depth - 10;
        }
        if (board.isFull()) {
            return 0;
        }

        int best = myTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int cell = 1; cell <= 9; cell++) {
            if (board.isFree(cell)) {
                board.place(cell, myTurn ? me : opponent);
                int score = minimax(board, !myTurn, depth + 1);
                board.clear(cell);
                best = myTurn ? Math.max(best, score) : Math.min(best, score);
            }
        }
        return best;
    }
}
