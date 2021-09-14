package animationgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import animationgame.frame.chapter1;

public class levelScreen {
	
	private Image[] i = new Image[20];
	private int rectX = 45;
	private int rectY = 40;
	private int x = 15,y = 15;
	
	private frame f;
	
	public levelScreen(){
		loadPics();
	}
	
	public void loadPics(){
	
		i[0] = new ImageIcon(getClass().getResource("/images/lvlButton1.1.png")).getImage();
		i[1] = new ImageIcon(getClass().getResource("/images/lvlButton1.2.png")).getImage();
		i[2] = new ImageIcon(getClass().getResource("/images/lvlButton1.3.png")).getImage();
		i[3] = new ImageIcon(getClass().getResource("/images/lvlButton1.4.png")).getImage();
		i[4] = new ImageIcon(getClass().getResource("/images/lvlButton1.5.png")).getImage();
		
	}
	
	public Rectangle getBounds(chapter1 ch){
		if(ch == chapter1.l1)
		return new Rectangle(rectX,rectY,100,100);
		else if(ch == chapter1.l2)
	    return new  Rectangle(rectX + 120,rectY,100,100);
		else if(ch == chapter1.l3)
			return new Rectangle(rectX + 120*2,rectY,100,100);
		else if(ch == chapter1.l4)
			return new Rectangle(rectX + 120*3,rectY,100,100);
		else return null;
	}
	
	public void levelScreenDesign(Graphics g){
		
		g.drawImage(i[0],x,y,f);
		g.drawImage(i[1],x + 120,y,f);
		g.drawImage(i[2],x + 120*2,y,f);
		g.drawImage(i[3],x + 120*3,y,f);
		g.drawImage(i[4],x + 120*4,y,f);
		g.drawImage(i[5],x + 120*5,y,f);

		
	}

}
