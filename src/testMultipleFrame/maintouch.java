package testMultipleFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class maintouch {

	public static void main(String args[]){
		JFrame j = new JFrame();
		JPanel p = new mainpane();
		j.add(p);
		p.setFocusable(true);
		p.setVisible(true);
		j.setSize(500,500);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		
	}
	
}
