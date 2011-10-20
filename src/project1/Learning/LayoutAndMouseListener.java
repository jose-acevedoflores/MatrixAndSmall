package project1.Learning;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 * Using the GridBagLayout and the KeyListener. In this program I was experimenting with the gridbag layout
 * but I found it too difficult. But in here I also started experimenting with the MouseListener and ActionListener interfaces. 
 * @author Jose
 *
 */
public class LayoutAndMouseListener implements MouseListener, ActionListener {
	
	private static JButton button;
	private static JButton button2;
	private static JButton newWindow;
	private static MenuItem item1 = new MenuItem("This is a test >");
	private static JTextField txtField = new JTextField(10);
	 	
	/**
	 * Adds the buttons and text field to the given frame.
	 * @param fr the frame to add the components to.
	 */
	    public static void addComponentsToFrame(JFrame fr) {
	   
	    	
	    JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout()); 
		GridBagConstraints c = new GridBagConstraints();
		 // This object determines the properties of the component
		// (how it should look like and where it will appear) that is going to be added.
		// That's why the add ,method always takes an object say button or empty area as well as this variable. 

		
		c.fill = GridBagConstraints.HORIZONTAL;// All components should be resized horizontally.
		c.gridx = 0; // Since the grid is 3x3 this element goes in the upper left corner.
		c.gridy = 0;
		pane.add(Box.createRigidArea(new Dimension(30,0)),c ); // This object is an empty area. 
		
		c.gridx = 1;//Locates this element at the center-top of the frame.
		c.gridy = 0;
		pane.add(txtField, c);
		
		c.gridx = 2;//Locates this element at the right-top corner of the frame.
		c.gridy = 0;
		pane.add(Box.createRigidArea(new Dimension(30,0)),c );
		
		button = new JButton("Test");
		button.addMouseListener(new LayoutAndMouseListener());
		button.setPreferredSize(new Dimension(40,40));
		c.weighty = 1;//This means the element as more "weight" when the component resizes in the y direction. 
		c.weightx = 0.4;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(button, c);
		
		c.gridx = 1;
		c.gridy = 1;
		pane.add(Box.createRigidArea(new Dimension(30,0)),c );
		
		button2 = new JButton("Append :)");
		button2.addMouseListener(new LayoutAndMouseListener());
		button2.setPreferredSize(new Dimension(20,30));
		c.weighty = 1;
		c.weightx = 0.4;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(button2, c);
		
		newWindow = new JButton("Window");
		newWindow.addMouseListener(new LayoutAndMouseListener());
		newWindow.setPreferredSize(new Dimension(20,30));
		c.weighty = 1;
		c.weightx = 0.4;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(newWindow, c);
		
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		fr.add(pane);
	    }

	 
	    public static void main(String[] args) {
	    		JFrame frame = new JFrame("GridBagLayoutDemo");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(new Dimension(400,200));
		        
		        MenuBar menu = new MenuBar(); // Create the menu bar
		    	Menu file = new Menu("File");
		        file.add(new MenuItem("PaPoi"));
		        file.add(item1); //Add the item to the menu
		        item1.addActionListener(new LayoutAndMouseListener()); // Added the listener to the menu item
		        menu.add(file);
		        frame.setMenuBar(menu);
		        
		        
		        addComponentsToFrame(frame);
		        
		        frame.setVisible(true);
	    }



		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getComponent().equals(button)) // If "Test" is pressed
			{
				JOptionPane.showMessageDialog(null, "SIIIIII");
			}
			
			else if(arg0.getComponent().equals(button2)) //"Append :)" button pressed
			{
				String msg = txtField.getText();
				msg+=":)";
				JOptionPane.showMessageDialog(null, msg);
				txtField.setText(msg);
			}
			
			else if(arg0.getComponent().equals(newWindow)) // "New Window" 
			{
				JFrame f = new JFrame("Test");
				f.setSize(new Dimension(200,100));
			//	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
			
		}


	
		public void mouseEntered(MouseEvent arg0) {
	
		}


		
		public void mouseExited(MouseEvent arg0) {
			
			
		}


		
		public void mousePressed(MouseEvent arg0) {
			
			
		}


	
		public void mouseReleased(MouseEvent arg0) {
			
			
		}

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(item1))
			{
				JOptionPane.showMessageDialog(null,"The test is working");
			}
			
		}
}


