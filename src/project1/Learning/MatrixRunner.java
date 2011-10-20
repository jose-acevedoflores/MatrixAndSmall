package project1.Learning;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.*;

/**
 * This class is designed to run a matrix 2x2 or 3x3 program (uncomment the one you want ).
 * It computes the determinant of the 
 * @author Jose
 *
 */
public class MatrixRunner implements ActionListener{

	 private static MatrixInterface m;
		
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Matrix Determinant");
		frame.setSize(new Dimension(400,300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		MenuBar menu = new MenuBar();
		Menu file = new Menu("File");
		
		MenuItem history = new MenuItem("History");
		history.addActionListener(new MatrixRunner());
		
		file.add(history);	 // Add history to the file menu
		menu.add(file); 	//Add file to the menu bar
			
		frame.setMenuBar(menu);
		
		String option = JOptionPane.showInputDialog("Type\n1) Matrix 2x2\n2) Matrix 3x3");
		if(option== null)
			System.exit(0);
		if(option.equals("1"))
		{
			m = new Matrix2x2();
			m.addTextFields(frame);
		}
		else if(option.equals("2"))
		{
			m = new Matrix3x3();
			m.addTextFields(frame);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Something went wrong");
			System.exit(-1);
		}
	
		
		frame.setVisible(true);
		
	}
	
	/**
	 * Here if the history menu item is clicked it will display the list of previous determinants
	 * calculated so far. 
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("History"))
		{
			if(m.getHistory().size() == 0) //If the array list is empty then no determinants have been calculated so far.
			{
				JOptionPane.showMessageDialog(null, "No history", "Notification", JOptionPane.OK_CANCEL_OPTION);
			}
			
			else
			{
				JFrame smallFrame = new JFrame("Previous determinants");
				JPanel resultHistoryPanel = new JPanel();
				resultHistoryPanel.setLayout(new BoxLayout(resultHistoryPanel, BoxLayout.Y_AXIS));
				for(int i = 0 ; i < m.getHistory().size() ; i++ )
				{																	
					int determinantPosition = m.getHistory().get(i).length-1;
					String determinantToAdd = String.valueOf(m.getHistory().get(i)[determinantPosition]);
					resultHistoryPanel.add(new JLabel(determinantToAdd));
				}

				smallFrame.add(resultHistoryPanel);
				smallFrame.setMinimumSize(new Dimension(300,0));
				smallFrame.pack();
				smallFrame.setVisible(true);
			}
				
		}
		
	}

}
