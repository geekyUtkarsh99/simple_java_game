package animationgame;

import java.awt.Color;
import java.awt.Graphics;

public class healthGauge {

	
	protected int x = 14,y = 700;
	protected boolean visible = true;
	protected Color color = null;
	
	public void draw(Graphics g){
		
		g.setColor(color);
		g.fillOval(x, y, 20, 26);
		
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void isvisible(boolean visible){
		this.visible = visible;
	}
 
	
	
}
