package animationgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class displayWindow {

	private static JFrame j;
	//size variables
	protected static int height;
	protected static int width;
	
	
    public static void main(String[] args) {
		    j = new JFrame();
		   
			   JPanel f = new frame();		 
				  
					j.add(f);
				  j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
		f.setFocusable(true);
		f.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		j.setUndecorated(true);
		  j.pack();
		  j.setVisible(true);

	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}

}
