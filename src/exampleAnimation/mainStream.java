package exampleAnimation;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainStream {

	public static void main(String[] args) {
		JFrame j = new JFrame();
		JPanel p = new gui();
	
		p.setFocusable(true);
		j.setSize(350,350);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);
		j.add(p);
		

	}
	
	
	
	



	
}
