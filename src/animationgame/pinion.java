package animationgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import animationgame.animation.condition;

public class pinion {

	private animation anim = new animation(5);
	private frame f;
	protected int x = 1200,y;
	protected boolean visible = true;
	protected int life = 12;
	protected boolean damage = false;
	protected boolean destroy = false;
	private BufferedImage b;
	protected int i = 1;
	
	public pinion(int y){
		this.y = y;
	}
	
	public void drawPinion(Graphics g){
		
		g.drawImage(b,x,y,f);
		
	}
	
	public void move(){
		if(x >= 0){
			x--;
		}else visible = false;
	}
	
	public boolean checkVisibility(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
    public boolean randomizer(){
		
		Random r = new Random();
		int a = r.nextInt(5000);
		if(a <= 10){
			
			return true;
	    }else return false;
		
	}
	
	public void setDamage(boolean damage){
		this.damage = damage;
	}
	
	public boolean getDestroy(){
		return destroy;
	}
	
	public void setImage(BufferedImage b){
		this.b = b;
	}
	
	public BufferedImage getImage(){
		return b;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x + 15, y + 25, 80, 45);
	}
	
	public void render(){
		if(!damage){
			anim.doAnimate(visible, condition.pinion);
			anim.cycle();
		
		}else if(damage && life > 0){
			condition con = condition.pindam;
			anim.doAnimate(visible, con);
			
			anim.cycle();
			
			damage = false;
			life--;
		}else if(destroy && i <= 5){
			condition con = condition.enemyDest;
			anim.doAnimate(visible, con);
			anim.cycle();
			i++;
			if(i == 6)
				destroy = false;
		}else if(life == 0){
			destroy = true;
			life--;
		}
		else visible = false;
		
		setImage(anim.getPlayerImage());
	}
	
	
	
	
}
