package project1.Learning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
 * This class sets a frame with a 3x3 grid simulating a matrix so you can input numbers and compute the determinant.
 * This class also keeps track of all inputs and their determinant in the ArrayList instance.
 * Every element of the arrayList saves the nine inputs and the determinant in other words, every element is a matrix.
 * @author Jose
 *
 */
public class Matrix3x3 implements KeyListener, ActionListener,MatrixInterface{


	private TextField[] field = new TextField[9];
	private JButton compute = new JButton("Compute");
	private JLabel resultLabel = new JLabel();
	private  int checkIfPressed=0;
	private ArrayList<Double[]> history = new ArrayList<Double[]>();
	
	/**
	 * Builds the GUI for the given frame. This method organizes the components with three 
	 * different layouts: BorderLAyout for the bottom part ( the "Compute" button, the "Determinant"
	 * label, and the "Result" label  ).
	 * The text fields are added to the fieldPanel component using GridLayout
	 * Everything else is added using BorderLayout. 
	 * The "Box.createRigidArea(Dimension)" creates empty spaces to make everything look pretty.
	 * @param frame the frame to add the text fields, and buttons to.
	 */
	public void addTextFields(JFrame frame)
	{
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(3,3,5,30));//This panel contains the input fields 
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
	
	

	/*
	 * 0 1 2 | 0 1
	 * 3 4 5 | 3 4
	 * 6 7 8 | 6 7
	 */

	/**
	 * Computes the matrix determinant.
	 * @return the matrix determinant.
	 */
	public double matrixDeterminant()
	{
		double determinantResult = 0;
		double[] numbersFromFields = new double[9]; // This array is for easy computing of the determinant
	
		Double[] forHistory = new Double[10]; 		// This array is to add it to the history array list. Since array list cannot be invoked on double 
													// it has to be created as Double.
		
		for(int i = 0 ; i < field.length; i++ )//Get the numbers from the fields.
		{
			numbersFromFields[i] = Double.parseDouble(field[i].getText());
			forHistory[i] = new Double(numbersFromFields[i] );
		}		
		
		
		//Formula for adding the determinant of a 3x3 matrix
		determinantResult += numbersFromFields[0]*numbersFromFields[4]*numbersFromFields[8];
		determinantResult += numbersFromFields[1]*numbersFromFields[5]*numbersFromFields[6];
		determinantResult += numbersFromFields[2]*numbersFromFields[3]*numbersFromFields[7];
	
		determinantResult -= numbersFromFields[6]*numbersFromFields[4]*numbersFromFields[2];
		determinantResult -= numbersFromFields[7]*numbersFromFields[5]*numbersFromFields[0];
		determinantResult -= numbersFromFields[8]*numbersFromFields[3]*numbersFromFields[1];

		forHistory[9] = determinantResult; // Save the result of this matrix in the end of the array
		history.add(forHistory);
		return determinantResult;
	}
	
	
	/**
	 * Checks the user input for invalid characters.
	 * @return true the user did not gave as input an illegal character.
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
				flag=0;
				
				for(int c = 0 ; c < validChars.length; c++)
				{
					
					if(field[i].getText().charAt(u) == validChars[c])//Checks the valid characters array.
						flag = 1;
					
					else if(field[i].getText().charAt(0) == negSign) // This ensures the negative only appears in the first position.
						flag=1;
					
				}
				if(flag==0) // If no valid char is found flag is kept at zero so an illegal input was found.
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
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER )
		{
			compute.doClick();
		
		}
	}
	
	public void actionPerformed(ActionEvent t)
	{
		if(t.getSource() == compute)
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
