// Mohit Nakrani
// TicTacToe AI that will play the best move and will never lose the game
// May. 1 2014

public class TTT_MyAI implements TicTacToe {
	public int[] board;
	private final int CANT_MOVE = 0, LOSE_MOVE = 1, DRAW_MOVE = 2, WIN_MOVE  = 3;

	// Initialize game / board
	public void game() {
		board = new int[9];
	}

	// My move: loc is the location of the opponent's move, if loc is 0, my move is the first move of the game
	// Return the location of my move
	public int move(int loc) {
		if (loc == 0) return (board[0] = 1);
		--loc;
		board[loc] = -1;
		board[loc = findBest(board, 1)] = 1;
		return loc + 1;
	}

	// Return the best move location for a player on the board
	private int findBest(int[] board, int player) {
		int i = 0, loc = 0, max = 0;
		int[] points = new int[9];

	    for (i = 0; i < 9; ++i) {
			if (board[i] == 0) {
				int tmp = getPoints(i, board, player);
				if (tmp > max) {
					loc = i;
					max = tmp;
				}
			}
		}
		return loc;
	}

	// Return the chance of winning the game with the player moving at location on the board
	private int getPoints(int loc, int[] board, int player) {
		int i = 0, res = 0, curr = player;
		int[] tmpBoard = new int[9];
		for (i = 0; i < 9; ++i) tmpBoard[i] = board[i];
		board = tmpBoard;
		
		board[loc] = curr;
		res = getStatus(board, curr);
		if (res == WIN_MOVE) return WIN_MOVE;

		while (res == CANT_MOVE) {
			curr = -curr;
			board[findBest(board, curr)] = curr;
			res = getStatus(board, curr);
			if (res == WIN_MOVE) return (curr == player) ? WIN_MOVE : LOSE_MOVE;
		}
		return DRAW_MOVE;
	}

	// Return the status of the game, check if won / lost / drawn / nothing yet
	public int getStatus(int[] board, int player) {
		boolean win = board[0] == player && board[1] == player && board[2] == player ||
			board[3] == player && board[4] == player && board[5] == player ||
			board[6] == player && board[7] == player && board[8] == player ||
			board[0] == player && board[3] == player && board[6] == player ||
			board[1] == player && board[4] == player && board[7] == player ||
			board[2] == player && board[5] == player && board[8] == player ||
			board[0] == player && board[4] == player && board[8] == player ||
			board[2] == player && board[4] == player && board[6] == player;
		
		if (win) return WIN_MOVE;
		int c = 0;
		for (int i : board) if (i == 0) ++c;
		return (c == 0) ? DRAW_MOVE : CANT_MOVE;
	}
}
