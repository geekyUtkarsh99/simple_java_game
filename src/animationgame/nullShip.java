package animationgame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import animationgame.animation.condition;

public class nullShip {

	private int x,y;
	private boolean vis = true;
	private location loc = null;
	private int max_x,max_y;
	private boolean doMove = true;
	private frame f;
	private boolean damage = false;
	private int life = 25;
	private boolean destroy = false;
	protected int i = 1;
	private int damagePoints = 0;
	private BufferedImage b;
	private animation anim = new animation(5);
	
	
	public nullShip(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		g.drawImage(b,x,y,f);
		
	}
	
	public void setImage(BufferedImage i){
		this.b = i;
	}
	
	public BufferedImage getImage(){
		return b;
	}
	
	enum location{
		up,down,left,right,upl,upr,downl,downr
	}
	
	public location getLocation(){
		return loc;
	}
	
	public void setMovable(boolean doMove){
		this.doMove = doMove;
	}
	
	public void setDamagePoint(int damagePoints){
		this.damagePoints = damagePoints;
	}
	
	public void setDamage(boolean damage){
		this.damage = damage;
	}
	
	public boolean getDestroy(){
		return destroy;
	}
	
	public boolean getMovable(){
		return doMove;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	//moving function 
	public void move(){
		if(doMove)
		if(max_x >200 && max_x < 1000 && max_y > 10 && max_y < 600 ){
			
		if(vis){
			
			
		if(loc == location.up){
			
			y-=4;
			if(y <= max_y){
				loc = null;
			doMove =false;
			
			}
		}else if(loc == location.down){
			y+=4;
			if(y >= max_y){
				loc = null;
			doMove = false;
			
			
			}
		}else if(loc == location.left){
			x-=4;
			if(x <= max_x){
			loc = null;
			doMove = false;
			
			
			}
			
		}else if(loc == location.downl){
			x-=4;
			y-=4;
			if(x <= max_x){
				loc = null;
				doMove = false;
				
				
				}
		}else if(loc == location.downr){
			x+=4;
			y+=4;
			if(x >= max_x){
				loc = null;
				doMove = false;
				
				
			}
		}else if(loc == location.upl){
			x-=4;
			y-=4;
			if(x<=max_x){
				loc = null;
				doMove = false;
				
				
			}
		}else if(loc == location.upr){
			x+=4;
			y-=4;
			if(x >= max_x){
				loc = null;
				doMove = false;
				
				
			}
		}else if(loc == location.right){
			x+=4;
			if(x >= max_x){
			loc = null;
			doMove = false;
			
			
			}
		}else randomizer();
		}
		}else{
			max_x = 300;
			max_y = 300;
		}
	}
	
    public void setVis(boolean vis){
    	this.vis = vis;
    }
    
    public boolean getVis(){
    	return vis;
    }
	
    public void randomizer(){
    	
    	Random r = new Random();
    	int a = r.nextInt(8);
    	switch(a){
    	case 1 : loc = location.up;
    	max_x = x;
    	max_y = y - 150;
    	break;
    	
    	case 2 : loc = location.down;
    	max_x = x;
    	max_y = y + 150;
    	break;
    	
    	case 3 : loc = location.left;
    	max_x = x - 150;
    	max_y = y;
    	break;
    	
    	case 4 : loc = location.right;
    	max_x = x + 150;
    	max_y = y;
    	break;
    	
    	case 5 : loc = location.downl;
    	max_x = x - 150;
    	max_y = y + 150;
    	break;
    	
    	case 6 : loc = location.downr;
    	max_x = x + 150;
    	max_y = y + 150;
    	break;
    	case 7 : loc = location.upl;
    	max_x = x - 150;
    	max_y = y - 150;
    	break;
    	
    	case 8 : loc = location.upr;
    	max_x = x + 150;
    	max_y = y - 150;
    	break;
    	
    	}
    	
    }
    
    public Rectangle getBounds(){
    	return new Rectangle(x + 30, y + 45, 130, 50);
    }
    
    public void render(){
    	if(!damage){
    		if(doMove){
			anim.doAnimate(vis, condition.nullship);
			anim.cycle();
    		}else {anim.doAnimate(vis, condition.nullShipfire);
    			anim.cycle();
    		}
		}else if(damage && life > 0){
			condition con = condition.nullDam;
			anim.doAnimate(vis, con);
			
			anim.cycle();
			
			damage = false;
			life-=damagePoints;
			
			//fix breaking point 
			if(life < 0)
			  life = 0;
			
		}else if(destroy && i <= 5){
			condition con = condition.enemyDest;
			anim.doAnimate(vis, con);
			anim.cycle();
			i++;
			if(i == 6)
				destroy = false;
				
			
		}else if(life == 0){
			destroy = true;
			life--;
		}
		else vis = false;
    	
    	
    	setImage(anim.getPlayerImage());
    }
    
}
