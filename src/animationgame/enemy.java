package animationgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import animationgame.animation.condition;

public class enemy {

	protected int x = 1200;
	protected int y;
	protected boolean visible = true;
	private boolean damage = false;
	private boolean destroy = false;
	private int life = 15;
	private frame f;
	private animation anim = new animation(5);
	protected int i = 1;
	private BufferedImage b;
	
	// enemy bullets
	private enemyBullets enbul;
	private ArrayList<enemyBullets> enb = new ArrayList<enemyBullets>();
	
	public enemy(int d){
		
		this.y = d;
		
	}
	
	public void doMove(){
		if(!damage)
		if(x > 0){
			
			x--;
		}	
		else visible = false;;
		
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
	
	public void setDamage(boolean damage){
		this.damage = damage;
	}
	

	public void drawEnemy(Graphics g){
	g.drawImage(b,x,y,f);
	

	}
	
	public boolean randomizer(){
		
		Random r = new Random();
		int a = r.nextInt(5000);
		if(a <= 10){
			
			return true;
	    }else return false;
		
	}

	public void setImage(BufferedImage b){
		this.b = b;
	}
	
	public BufferedImage getImage(){
		
		
		return b;
	}
	
	
	
    public Rectangle getBounds(){
    	return new Rectangle(x,y + 58,90,30);
    }
    
    public boolean getDestroy(){
    	return destroy;
    }
   
    
    public void render(){
    	
		
			if(!damage){
				
				condition con = condition.ENEMY;
			anim.doAnimate(visible, con);
			
			anim.cycle();
			}else if(damage && life > 0){
				condition con = condition.enemydamage;
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
