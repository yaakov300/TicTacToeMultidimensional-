import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.net.ssl.SNIHostName;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.geom.Rectangle2D;


//panel of the game
public class TicTacToeGUI extends JPanel{

	private final int SERIES = 3;//series for tictactoe game
	private TicTacToeGame game;
	Rectangle2D[][] rectangleBoard;
	private int dim = 3;//TODO get diminion from user
	private int width; 
	private boolean win = false;
	private int player = 1;
	private JSpinner jspinnerDim;
	private SpinnerModel spinnerDim = new SpinnerNumberModel(2, 2, 100, 1);

	public TicTacToeGUI()
	{	
		//spiner of dimention
		drawDimentin();
		jspinnerDim = new JSpinner(spinnerDim);
		game = new TicTacToeGame(dim);
		win = false;
		addMouseListener(new Listener());
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(dim != 0)
		{
			drawBoard(g2d);
			if(win)
				gameOver(g2d, player);
		}
	}
	//draw the board 
	private void drawBoard(Graphics2D g2d)
	{
		//init location 
		int YCounter1 = 0;
		int YCounter2 = 0;
		int w = getWidth();
		int h = getHeight();
		int dim;

		//init the board
		int[][] a = game.getBoard();
		rectangleBoard = new Rectangle2D[a.length][a[0].length];
		int x, y;
		//x = y = width;
		y = 100;
		
		
		if(game.SIZE%2 == 0)
		{
			dim = (int)Math.pow(SERIES, ( (int)(game.SIZE/2) ));
			width = (h / (2*dim));

		}
		else{
			dim = (int)Math.pow(SERIES, ( (int)((game.SIZE+1)/2) ));
			width = w / (dim+10);
		}

		for(int i = 0; i < a.length; i++, y += width)
		{	
			if(game.SIZE<SERIES)
				YCounter1 = 0;
			if(YCounter2 == SERIES)
			{
				y += width;
				YCounter2 = 0;
			}
			YCounter2++;

			x = (w/2) - ((a[1].length/2)*width);

			for(int j = 0; j < a[i].length; j++, x += width)
			{	
				if(YCounter1 == SERIES || YCounter1 == 2*SERIES)
				{
					y += width;
				}else if(YCounter1 == 3*SERIES){
					y -= 2*width;
					YCounter1 = 0;
				}
				drawSquare(g2d, a[i][j], x, y, i, j, rectangleBoard);

				YCounter1++;
			}
		}
		g2d.setFont(new Font("arial", Font.BOLD, 30));
		g2d.setColor(Color.GRAY);
		if(player == 1 && !win)
			g2d.drawString("X TURN", width,  50);
		else if(!win)
			g2d.drawString("CIRCLE TURN", width,  50);
	}
	//draw square foe cell
	private void drawSquare(Graphics2D g2d, int player, int x, int y, int i, int j, Rectangle2D[][] rectangleBoard)
	{
		g2d.setColor(Color.GRAY);
		rectangleBoard[i][j] =new Rectangle2D.Double(x+1, y+1, width-2, width-2);
		g2d.fill(rectangleBoard[i][j]);
		//g2d.fillRect(x+1, y+1, width-2, width-2);
		if(player == TicTacToeBoard.X)
		{
			g2d.setColor(Color.BLUE);
			g2d.drawLine(x+2, y+2, x+width-2, y+width-2);
			g2d.drawLine(x+width-2, y+2, x+2, y+width-2);

		}else if(player == TicTacToeBoard.CIRCLE)
		{
			g2d.setColor(Color.RED);
			g2d.drawOval(x+3, y+3, width-6, width-6);
		}
	}
	//the game is finish
	private void gameOver(Graphics2D g2d, int player)
	{
		g2d.setColor(Color.GRAY);
		if(player == 1)
		{
			g2d.drawString("X Win!!!", getWidth() / 2 - 52, 42);
			g2d.setColor(Color.GREEN);
			g2d.drawString("X Win!!!", getWidth() / 2 - 50, 40);
		}
		else{
			g2d.drawString("CIRCLE Win!!!", getWidth() / 2 - 52, 42);
			g2d.setColor(Color.GREEN);
			g2d.drawString("CIRCLE Win!!!", getWidth() / 2 - 50, 40);
		}
	}
	//listener of the board
	private class Listener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			//check the cell
			if(!win){
				int x = e.getX();
				int y = e.getY();
				int[] cell = clickedCell(x, y);
				if(cell != null)
				{	
					win = game.move(cell[1], cell[0],player);
					if(win){

					}
					else if(player == 1){
						player = 2;
					}else{
						player = 1;
					}
					repaint();
				}
			}
		}
		//which cell
		private int[] clickedCell(int x, int y)
		{ 	
			int[][] a = game.getBoard();
			int[] cell = new int[2];
			for (int i = 0; i < rectangleBoard.length; i++) {
				for (int j = 0; j < rectangleBoard[0].length; j++) {
					if(rectangleBoard[i][j].contains(x,y))
					{	
						if(a[i][j] == 1 || a[i][j] == 2)
							return null;
						cell[0] = i;
						cell[1] = j;
						return cell;
					}
				}
			}

			return null;
		}

	}
	//message box for choose dimention
	public void drawDimentin()
	{
		SpinnerNumberModel sModel = new SpinnerNumberModel(2, 2, 30, 1);
		JSpinner spinner = new JSpinner(sModel);
		//JOptionPane.showMessageDialog(null, spinner);

		int option = JOptionPane.showOptionDialog(null, spinner, "choose dimention", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, null, null);
		Integer dimTemp = (Integer)spinner.getValue(); 
		dim = dimTemp.intValue();
	}
}
