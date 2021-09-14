package testMultipleFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class pane1 extends JPanel{

	ImageIcon i = new ImageIcon("src/images/ship1.png");
	Image ii = i.getImage();
	public pane1(){
		JLabel l = new JLabel();
		l.setIcon(i);
		l.setVisible(true);
		
		add(l);
		setPreferredSize(new Dimension(500,500));
	}
	
}
