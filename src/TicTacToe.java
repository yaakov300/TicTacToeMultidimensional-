
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



public class TicTacToe {
	public static void main(String[] args)
	{	

		String[] spinnerDimention = new String[98];//inti spinner
		for (int i = 2; i < spinnerDimention.length; i++) {
			spinnerDimention[i] = "" + i;
		}
		JFrame frame = new JFrame("TicTacToe");//create windows game
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(800, 600);
		TicTacToeGUI gui = new TicTacToeGUI();
		/*
		JFrame frameM = new JFrame("");
		String favoritePizza = (String) JOptionPane.showInputDialog(frame, 
				"choose simention?",
				"choose simention",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				spinnerDimention, 
				spinnerDimention[0]);

		 */
		frame.add(gui);//add the gui of the game to windows
		frame.setVisible(true);

	}

}
