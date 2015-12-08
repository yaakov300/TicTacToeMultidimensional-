
public class TicTacToeBoard {

	private final int NUM_DIMENSIONS, N = 3;
	public static final int EMPTY = 0, X = 1, CIRCLE = 2;
	

	private  int[][] board; 
	//the board of the game
	public TicTacToeBoard(int n)
	{
		NUM_DIMENSIONS = n;
		int colDim = (int)Math.pow(N, (NUM_DIMENSIONS / 2));
		int rowDim = (int)Math.pow(N, ((NUM_DIMENSIONS % 2) + (NUM_DIMENSIONS / 2)));
		board = new int[colDim][rowDim];
		for (int i = 0; i < colDim; i++)
		{
			for (int j = 0; j < rowDim; j++)   
			{
				board[i][j] = EMPTY;
			}
		}
	}
	//return the board 
	public int[][] getBoard()
	{
		return board;
	}  
	//check if there is series 
	public boolean isSeries(int x, int y, int player)
	{
			board[y][x] = player;
			return isSeries(x, y, player, NUM_DIMENSIONS, 0, 0, 0, 0);
	}
	//recursive function of is sersi 
	public boolean isSeries(int x, int y, int player, int dim,
			int index1X, int index1Y, int index2X, int index2Y)
	{

		boolean numDimensionsIsOddNumber;
		int locaiton;
		int jump;
		//check dimention
		if (dim == NUM_DIMENSIONS && (dim % 2) !=0 )
		{
			dim = dim + 1;
			jump = (int)Math.pow(N, (dim/2 - 1));
			numDimensionsIsOddNumber = true;
		}
		else
		{
			jump = (int)Math.pow(N, (dim/2 - 1));
			numDimensionsIsOddNumber = false;
		}
		//check location for dimention 
		locaiton  = locaitonForXY(x, y, dim);
		
		if(dim == 2)
		{
			if(isTrinity(player, locaiton, index1X, index1Y,
					index2X, index2Y))
				return true;
			else
				return false;
		}

		//location is 1
		if(locaiton == 1)
		{
			if(isSeries(x, y, player, dim - 2, index1X, index1Y, index2X, index2Y)||//(1,1,1)
					isSeries(x, y, player, dim - 2, index1X + jump, index1Y, index2X + 2*jump, index2Y))//(1,2,3)
				return true;
			else if(!numDimensionsIsOddNumber)
				if(isSeries(x, y, player, dim - 2, index1X + jump, index1Y + jump, index2X + 2*jump, index2Y  + 2*jump)||//(1,5,9)
						isSeries(x, y, player, dim - 2, index1X, index1Y + jump, index2X, index2Y + 2*jump))//(1,4,7))
					return true;
		}
		//location is 2
		else if(locaiton == 2)
		{
			if(isSeries(x, y, player, dim - 2, index1X + jump, index1Y, index2X + jump, index2Y)||//(2,2,2)
					isSeries(x, y, player, dim - 2, index1X, index1Y, index2X + 2*jump, index2Y))//(1,2,3)
				return true;
			else if(!numDimensionsIsOddNumber)
				if(isSeries(x, y, player, dim - 2, index1X + jump, index1Y + jump, index2X + jump, index2Y + 2*jump))//(2,5,8)
					return true;
		}
		//location is 3
		else if(locaiton == 3)
		{
			if(isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y, index2X + 2*jump, index2Y)||//(3,3,3)
					isSeries(x, y, player, dim - 2, index1X, index1Y, index2X + jump, index2Y))//(1,2,3)
				return true;
			else if(!numDimensionsIsOddNumber)
				if(isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y + jump, index2X + 2*jump, index2Y + 2*jump)||//(3,6,9)
						isSeries(x, y, player, dim - 2, index1X + jump, index1Y + jump, index2X, index2Y  + 2*jump))//(3,5,7)
					return true;
		}
		//location is 4
		else if(locaiton == 4 && !numDimensionsIsOddNumber)
		{
			if(isSeries(x, y, player, dim - 2, index1X, index1Y + jump, index2X, index2Y + jump)||//(4,4,4)
					isSeries(x, y, player, dim - 2, index1X + jump, index1Y + jump, index2X + 2*jump, index2Y + jump)||//(4,5,6)
					isSeries(x, y, player, dim - 2, index1X, index1Y, index2X, index2Y + 2*jump))//(1,4,7)
				return true;
		}
		//location is 5
		else if(locaiton == 5 && !numDimensionsIsOddNumber)
		{
			if(isSeries(x, y, player, dim - 2, index1X + jump, index1Y + jump, index2X + jump, index2Y + jump)||//(5,5,5)
					isSeries(x, y, player, dim - 2, index1X, index1Y + jump, index2X + 2*jump, index2Y + jump)||//(4,5,6)
					isSeries(x, y, player, dim - 2, index1X + jump, index1Y, index2X + jump, index2Y + 2*jump)||//(2,5,8)
					isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y, index2X, index2Y + 2*jump)||//(3,5,7)
					isSeries(x, y, player, dim - 2, index1X, index1Y, index2X + 2*jump, index2Y + 2*jump))//(1,5,9)
				return true;
		}
		//location is 6
		else if(locaiton == 6 && !numDimensionsIsOddNumber)
		{
			if(isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y + jump, index2X + 2*jump, index2Y + jump)||//(6,6,6)
					isSeries(x, y, player, dim - 2, index1X, index1Y + jump, index2X + jump, index2Y + jump)||//(4,5,6)
					isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y, index2X + 2*jump, index2Y + 2*jump))//(3,6,9)
				return true;
		}
		//location is 7
		else if(locaiton == 7 && !numDimensionsIsOddNumber)
		{
			if(isSeries(x, y, player, dim - 2, index1X, index1Y + 2*jump, index2X, index2Y + 2*jump)||//(7,7,7)
					isSeries(x, y, player, dim - 2, index1X + jump, index1Y + 2*jump, index2X  + 2*jump, index2Y + 2*jump)||//(7,8,9)
					isSeries(x, y, player, dim - 2, index1X, index1Y, index2X, index2Y + jump)||//(1,4,7)
					isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y, index2X + jump, index2Y + jump))//(3,5,7)
				return true;
		}
		//location is 8
		else if(locaiton == 8 && !numDimensionsIsOddNumber)
		{
			if(isSeries(x, y, player, dim - 2, index1X + jump, index1Y + 2*jump, index2X + jump, index2Y + 2*jump)||//(8,8,8)
					isSeries(x, y, player, dim - 2, index1X, index1Y + 2*jump, index2X + 2*jump, index2Y + 2*jump)||//(7,8,9)
					isSeries(x, y, player, dim - 2, index1X + jump, index1Y, index2X + jump, index2Y + jump))//(2,5,8)
				return true;
		}
		//location is 9
		else if(locaiton == 9 && !numDimensionsIsOddNumber)
		{
			if(isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y + 2*jump, index2X + 2*jump, index2Y + 2*jump)||//(9,9,9)
					isSeries(x, y, player, dim - 2, index1X, index1Y + 2*jump, index2X + jump, index2Y + 2*jump)||//(7,8,9)
					isSeries(x, y, player, dim - 2, index1X + 2*jump, index1Y, index2X + 2*jump, index2Y + jump)||//(3,6,9)
					isSeries(x, y, player, dim - 2, index1X, index1Y, index2X + jump, index2Y + jump))//(1,5,9)
				return true;
		}

		return false;
	}

	//check if the trinity
	public boolean isTrinity(int player, int thisLocation, int index1X, int index1Y, int index2X, int index2Y)
	{

		if(thisLocation == 1)
		{
			if((board[index1Y][index1X] == player && board[index2Y][index2X] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (1,1,1) and not same Square
			{
				return true;
			}
			else if(board[index1Y][index1X + 1] == player && board[index2Y][index2X + 2] == player)// (1,2,3)
			{
				return true;
			}
			else if(board[index1Y + 1][index1X] == player && board[index2Y + 2][index2X] == player)// (1,4,7)
			{
				return true;
			}
			else if(board[index1Y + 1][index1X + 1] == player && board[index2Y + 2][index2X + 2] == player)// (1,5,9)
			{
				return true;
			}
		}
		else if(thisLocation == 2)
		{

			if((board[index1Y][index1X + 1] == player && board[index2Y][index2X + 1] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (2,2,2) and not same Square
			{
				return true;
			}
			else if(board[index1Y][index1X] == player && board[index2Y][index2X + 2] == player)// (1,2,3)
			{
				return true;
			}
			else if(board[index1Y + 1][index1X + 1] == player && board[index2Y + 2][index2X + 1] == player)// (2,5,8)
			{
				return true;
			}
		}
		else if(thisLocation == 3)
		{
			if((board[index1Y][index1X + 2] == player && board[index2Y][index2X + 2] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (3,3,3) and not same Square
			{
				return true;
			}
			else if(board[index1Y][index1X] == player && board[index2Y][index2X + 1] == player)// (1,2,3)
			{
				return true;
			}
			else if(board[index1Y + 1][index1X + 2] == player && board[index2Y+2][index2X + 2] == player)// (3,6,9)
			{
				return true;
			}
			else if(board[index1Y + 1][index1X + 1] == player && board[index2Y + 2][index2X] == player)// (3,5,7)
			{
				return true;
			}
		}
		else if(thisLocation == 4)
		{
			if((board[index1Y + 1][index1X] == player && board[index2Y + 1][index2X] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (4,4,4) and not same Square
			{
				return true;
			}
			else if(board[index1Y + 1][index1X + 1] == player && board[index2Y + 1][index2X + 2] == player)// (4,5,6)
			{
				return true;
			}
			else if(board[index1Y][index1X] == player && board[index2Y + 2][index2X] == player)// (1,4,7)
			{
				return true;
			}
		}
		else if(thisLocation == 5)
		{
			if((board[index1Y + 1][index1X + 1] == player && board[index2Y + 1][index2X + 1] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (5,5,5) and not same Square
			{
				return true;
			}
			else if(board[index1Y + 1][index1X] == player && board[index2Y + 1][index2X + 2] == player)// (4,5,6)
			{
				return true;
			}
			else if(board[index1Y][index1X + 1] == player && board[index2Y + 2][index2X + 1] == player)// (2,5,8)
			{
				return true;
			}
			if(board[index1Y][index1X] == player && board[index2Y + 2][index2X + 2] == player)// (1,5,9)
			{
				return true;
			}
			else if(board[index1Y][index1X + 2] == player && board[index2Y + 2][index2X] == player)// (3,5,7)
			{
				return true;
			}
		}
		else if(thisLocation == 6)
		{ 
			if((board[index1Y + 1][index1X + 2] == player && board[index2Y + 1][index2X + 2] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (6,6,6) and not same Square
			{
				return true;
			}
			else if(board[index1Y + 1][index1X] == player && board[index2Y + 1][index2X + 1] == player)// (4,5,6)
			{
				return true;
			}
			else if(board[index1Y][index1X + 2] == player && board[index2Y + 2][index2X + 2] == player)// (3,6,9)
			{ 
				return true;
			}
		}
		else if(thisLocation == 7)
		{
			if((board[index1Y + 2][index1X] == player && board[index2Y + 2][index2X] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (7,7,7) and not same Square
			{
				return true;
			}
			else if(board[index1Y + 2][index1X + 1] == player && board[index2Y + 2][index2X + 2] == player)// (7,8,9)
			{
				return true;
			}
			else if(board[index1Y][index1X] == player && board[index2Y + 1][index2X] == player)// (1,4,7)
			{
				return true;
			}
			else if(board[index1Y][index1X + 2] == player && board[index2Y + 1][index2X + 1] == player)// (3,5,7)
			{
				return true;
			}
		}
		else if(thisLocation == 8)
		{
			if((board[index1Y + 2][index1X + 1] == player && board[index2Y + 2][index2X + 1] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (8,8,8) and not same Square
			{
				return true;
			}
			else if(board[index1Y + 2][index1X] == player && board[index2Y + 2][index2X + 2] == player)// (7,8,9)
			{
				return true;
			}
			else if(board[index1Y][index1X + 1] == player && board[index2Y + 1][index2X + 1] == player)// (2,5,8)
			{ 
				return true;
			}
		}
		else if(thisLocation == 9)
		{
			if((board[index1Y + 2][index1X + 2] == player && board[index2Y + 2][index2X + 2] == player)
					&& ((index1X != index2X) || (index1Y != index2Y)))// (9,9,9) and not same Square
			{
				return true;
			}
			else if(board[index1Y + 2][index1X] == player && board[index2Y + 2][index2X + 1] == player)// (7,8,9)
			{
				return true;
			}
			else if(board[index1Y][index1X + 2] == player && board[index2Y + 1][index2X + 2] == player)// (3,6,9)
			{
				return true;
			}
			else if(board[index1Y][index1X] == player && board[index2Y + 1][index2X + 1] == player)// (1,5,9)
			{
				return true;
			}
		}


		return false;  
	}


	//check location for x y ant return 9-1 number
	public int locaitonForXY(int x, int y, int dim)
	{ 
		int jump = (int)Math.pow(N, (dim/2));
		int modulo = jump;
		jump = jump/3;
		/*while(x>=modulo) {
			x = x % 3; 
		}
		while(y>=modulo) {
			y = y % 3; 
		}*/
		x = x % modulo;
		y = y % modulo;
		
		if( (x >= (0*jump) && x < 1*jump) && (y >= (0*jump) && y < 1*jump) )
		{
			return 1;
		}
		else if( (x >= (1*jump) && x < 2*jump) && (y >= (0*jump) && y < 1*jump) )
		{
			return 2;
		}
		else if( (x >= (2*jump) && x < 3*jump) && (y >= (0*jump) && y < 1*jump) )
		{
			return 3;
		}
		else if( (x >= (0*jump) && x < 1*jump) && (y >= (1*jump) && y < 2*jump) )
		{
			return 4;
		}
		else if( (x >= (1*jump) && x < 2*jump) && (y >= (1*jump) && y < 2*jump) )
		{
			return 5;
		}
		else if( (x >= (2*jump) && x < 3*jump) && (y >= (1*jump) && y < 2*jump) )
		{
			return 6;
		}if( (x >= (0*jump) && x < 1*jump) && (y >= (2*jump) && y < 3*jump) )
		{
			return 7;
		}
		else if( (x >= (1*jump) && x < 2*jump) && (y >= (2*jump) && y < 3*jump) )
		{
			return 8;
		}
		else if( (x >= (2*jump) && x < 3*jump) && (y >= (2*jump) && y < 3*jump) )
		{
			return 9;
		}
		return 0;
	}

}


