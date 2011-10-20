package project1.Learning;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class explores the concept of the mouseListener. If the mouse enters a label, button or a text area the appropriate action is
 * triggered.
 * @author Jose
 *
 */
public class EnterAreaMouseListener implements MouseListener {

	static JButton b1 = new JButton("Here");
	static JButton b2 = new JButton("Over Here");
	static JTextArea txtArea = new JTextArea(10,10);
	static JLabel label = new JLabel("A-a_a");
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("Area");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		
		JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // This is the main panel. It's used to accommodate the components in the y direction
		
		JPanel buttonsPanel = new JPanel(new FlowLayout()); // This panel accommodates the buttons and label from left to right.
		
		buttonsPanel.add(b1);
		buttonsPanel.add(Box.createRigidArea(new Dimension(50,50))); // Empty area to separate the two components
		buttonsPanel.add(b2);
		buttonsPanel.add(Box.createRigidArea(new Dimension(50,50))); 
		buttonsPanel.add(label);
		
		panel.add(buttonsPanel);
		panel.add(Box.createRigidArea(new Dimension(50,50))); //Empty area to separate the text area from the buttons 
		panel.add(scroll);
		
		frame.add(panel);
		b1.addMouseListener(new EnterAreaMouseListener());
		b2.addMouseListener(new EnterAreaMouseListener());
		txtArea.addMouseListener(new EnterAreaMouseListener());
		label.addMouseListener(new EnterAreaMouseListener());
		frame.setVisible(true);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	/**
	 * If we pass through one of the components in this method something is added to the text Area.
	 */
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource()== b1)
		{
			txtArea.append("Here\n");
		}
		else if(arg0.getSource()== b2)
		{
			txtArea.append("Over Here\n");
		}
		
		else if(arg0.getSource()== txtArea)
		{
			txtArea.append("In text area\n");
		}
		
		else if(arg0.getSource()== label)
		{
			txtArea.append("In label whoop\n");
		}
		
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource()== txtArea)
		{
			txtArea.append("Out text area\n");
		}
		
	}

	
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
