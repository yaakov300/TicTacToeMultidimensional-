
public class TicTacToeGame {
	private TicTacToeBoard board;
	public final int SIZE;

	public TicTacToeGame(int dim)
	{
		SIZE = dim;
		board = new TicTacToeBoard(dim);
	}
	
	public boolean move(int row, int col, int player)
    {
        return board.isSeries(row, col, player);
    }
	
	public int[][] getBoard()
    {
        return board.getBoard();
    }
}
