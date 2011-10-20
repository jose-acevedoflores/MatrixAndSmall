package project1.Learning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class sets a frame with a 2x2 grid simulating a matrix so you can input numbers and compute the determinant.
 * This class also keeps track of all inputs and their determinant in the ArrayList instance.
 * Every element of the arrayList saves the four inputs and the determinant in other words, every element is a matrix.
 * @author Jose
 *
 */
public class Matrix2x2 implements KeyListener, ActionListener, MatrixInterface{
	
	private TextField[] field = new TextField[4];
	private JButton compute = new JButton("Compute");
	private JLabel resultLabel = new JLabel();
	private  int checkIfPressed=0;
	private ArrayList<Double[]> history = new ArrayList<Double[]>();
	
	
	/**
	 * Add the text fields to the frame. Also sets the layout so it looks great.
	 * @param frame the frame to add the component.
	 */
	public void addTextFields(JFrame frame)
	{
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(2,2,5,30));//This panel contains the input fields 
		for(int i = 0 ; i< field.length ; i++)
		{
			field[i] = new TextField(10);  	//Initialize a text field with ten columns
			field[i].addKeyListener(this);	//add the KeyListener so when the users hits enter the determinant is computed.
			fieldPanel.add(field[i]); 		//Add the field to the panel.
		}
		
		JPanel pane = new JPanel();		// This panel contains everything. 
		pane.setLayout(new BorderLayout());
		
		pane.add(Box.createRigidArea(new Dimension(50,50)), BorderLayout.NORTH);
		pane.add(Box.createRigidArea(new Dimension(50,50)), BorderLayout.WEST);
		pane.add(fieldPanel, BorderLayout.CENTER);
		pane.add(Box.createRigidArea(new Dimension(50,50)), BorderLayout.EAST);
		
		
		compute.addKeyListener(this); 
		compute.addActionListener(this); 
		compute.setPreferredSize(compute.getMaximumSize()); // So the button doesn't change size when I change it to "Reset"
		JLabel determinant = new JLabel("Determinant: "); 
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom.add(Box.createRigidArea(new Dimension(42,50))); // This is to align the button with the first column of fields. 
		bottom.add(compute);
		bottom.add(determinant);
		bottom.add(resultLabel);
		
		pane.add(bottom, BorderLayout.SOUTH);

		frame.getContentPane().add(pane);
	}

	/**
	 * 
	 * @return the matrix determinant.
	 */
	public double matrixDeterminant()
	{
		double determinantResult= 0 ;
		Double[] forHistory = new Double[5];
		double[] numbersFromFields = new double[4];
		
		for(int i = 0 ; i < field.length; i++ )//Get the numbers from the fields.
		{
			numbersFromFields[i] = Double.parseDouble(field[i].getText());
			forHistory[i] = new Double(numbersFromFields[i] );
		}	
		
		determinantResult = numbersFromFields[0]*numbersFromFields[3];
		determinantResult -= numbersFromFields[1]*numbersFromFields[2];
		
		forHistory[4] = determinantResult;
		history.add(forHistory);
		return determinantResult;
	}
	
	/**
	 * Checks the user input for invalid characters.
	 * @return true if the user did not gave as input an illegal character.
	 */
	private boolean validUserInput()
	{
		char[] validChars = {'.','0','1','2','3','4','5','6','7','8','9'};
		char negSign = '-';
		int flag;
		
		
		for(int i = 0 ; i < field.length; i++ )
		{
			if( field[i].getText().length()==0)//If a field is left empty it's counted as an illegal input.
				return false;
			
			for(int u = 0; u < field[i].getText().length(); u++)
			{
				flag=1;
				
				for(int c = 0 ; c < validChars.length; c++)
				{
					
					if(field[i].getText().charAt(u) == validChars[c])//Checks the valid characters array.
						flag = 0;
					
					else if(field[i].getText().charAt(0) == negSign) // This ensures the negative only appears in the first position.
						flag=0;
					
				}
				if(flag==1) // If no valid char is found flag is kept at zero so an illegal input was found.
					return false;
			}

		}		
		
		return true;
	}
	
	/**
	 * Returns all the inputs and results computed so far.
	 * @return the history of determinants entered.
	 */
	public ArrayList<Double[]> getHistory()
	{
		return history;
	}
	
	/**
	 * Implementation of the interface method KeyPressed.
	 */
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER )
		{
			compute.doClick();
	
		}
		
	}
	
	/**
	 * Handles the mouse button press
	 */
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == compute)
		{
			if(checkIfPressed==0) // If check equals zero we need to compute the determinant.
			{	
				if(!this.validUserInput())
					JOptionPane.showMessageDialog(null, "Illegal Characters", "Whoops", JOptionPane.ERROR_MESSAGE);
				else
				{
					double result = this.matrixDeterminant(); 	//Compute the determinant
					this.resultLabel.setText(String.valueOf(result));//
					compute.setText("Reset");
					checkIfPressed++;
				}
			}
			else // If check equals 1 we need to reset the fields.
			{
				for(int i = 0 ; i < field.length; i++)
				{
					field[i].setText("");
				}
				resultLabel.setText("");
				compute.setText("Compute");
				field[0].requestFocus();
				checkIfPressed--;
			}
		
		}
		
	}


	public void keyReleased(KeyEvent arg0) {
	
		
	}

	
	public void keyTyped(KeyEvent arg0) {

	}

	
	

}
