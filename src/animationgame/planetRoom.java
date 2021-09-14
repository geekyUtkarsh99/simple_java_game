package animationgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import animationgame.animation.condition;

public class planetRoom {

	private int x,y;
	private frame f;
	private animation anim = new animation(10);
	private boolean visible = true;
	
	
	public planetRoom(){
		
	}
	
	public void draw(Graphics g,boolean visible,int x,int y){
		//define position
		this.x = x;
		this.y = y;
		
		//define visible
		this.visible = visible;
		
		if(visible)
		g.drawImage(anim.getPlayerImage(),x,y,f);
		
		
	}
	
	public Rectangle getBounds(int rect){
		
		switch(rect){
		
		case 1 : return new Rectangle(x + 345,y + 120,475,475);
		
		default : return null;
		
		
		}
	}
	
	public void render(){
		anim.doAnimate(visible, condition.plan1);
		anim.cycle();
	}
	
}
