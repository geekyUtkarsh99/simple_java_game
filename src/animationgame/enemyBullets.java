package animationgame;

import java.awt.Graphics;
import java.awt.Rectangle;

import animationgame.animation.condition;

public class enemyBullets {

	//reference variables
	private int x,y;
	private boolean destroy = false;
	private boolean visible = true;
	private frame f;
	private fireRule rule = null;
	
	//animation object
	private animation anim = new animation(5);
	
	public enemyBullets(int x,int y,fireRule rule){
		this.x = x;
		this.y = y;
		this.rule = rule;
	}
	
	public void draw(Graphics g){
		
		g.drawImage(anim.getPlayerImage(),x,y,f);
		
	}
	
	enum fireRule{
		n,mup,md
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void move(){
		
		if(x >=0){
		if(rule == fireRule.n)	
		x-=3;
		else if(rule == fireRule.md){
			x-=3;
			y++;
		}else if(rule == fireRule.mup){
			y--;
			x-=3;
		}
		
		
		}
		else visible  = false;
		
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,10,10);
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void render(){
		
		if(!destroy){
			anim.doAnimate(visible, condition.enemAttack);
			anim.cycle();
		}
			
		
	}
	
}
