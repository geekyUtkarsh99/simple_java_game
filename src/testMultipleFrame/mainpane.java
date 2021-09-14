package testMultipleFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class mainpane extends JPanel{
	
	private pane1 p = new pane1();
	private pane2 q = new pane2();
	
	public mainpane(){
		add(p);
		add(q);
		p.setVisible(true);
		q.setVisible(false);
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int a = e.getKeyCode();
				if(a == KeyEvent.VK_ENTER){
				
					p.setVisible(false);
					q.setVisible(true);
				}
			
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	

}
