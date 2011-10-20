package project1.Learning;

import java.util.ArrayList;

import javax.swing.JFrame;

public interface MatrixInterface {

	void addTextFields(JFrame frame);
	
	double matrixDeterminant();
	
	ArrayList<Double[]> getHistory();
	
	
}
