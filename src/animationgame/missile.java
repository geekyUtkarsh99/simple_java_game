package animationgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animationgame.animation.condition;

public class missile {

	protected int x,y;
	protected boolean visible = true;
	private boolean dester = false;
	protected int i = 0;
	private frame f;
	private animation anim = new animation(5);
	private BufferedImage b;
	
	
	public missile(int x,int y){
		
		this.x = x;
		this.y = y;
	
	}
	
	
	
	public void render(){
		

		if(!dester){
		condition con = condition.MISSILE;
		anim.doAnimate(visible,con);
		anim.cycle();
		}else if(dester && i <= 5){
			condition con = condition.MISSILEDESTOYED;
			anim.doAnimate(visible,con);
			anim.cycle();
			i++;
			
		}else visible = false;
		
		setImage(anim.getPlayerImage());
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
		
	}
	
	public void destroyMissile(boolean dester){
		this.dester = dester;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void move(){
		if(!dester)
		if(x <= 1200)
			x+=9;	
		else visible = false;
	}
	
	public void setImage(BufferedImage b){
		this.b = b;
	}
	
	public BufferedImage getimage(){
		return b;
	}
	
	public void drawMissile(Graphics g){
		
		g.drawImage(b,getX() + 160,getY() + 70,f);
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX() + 160,getY() + 70,10,10);
	}

	










}







