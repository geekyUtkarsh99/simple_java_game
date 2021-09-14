package animationgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animationgame.animation.condition;

public class nullShipMissile implements entity{

	private int x,y;
	private boolean vis = true;
	private animation anim = new animation(5);
	private boolean dester =false;
	private BufferedImage b;
	private int i = 1;
	private frame f;
	
	public nullShipMissile(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
			return x;
	}

	@Override
	public int getY() {
	
		return y;
	}

	@Override
	public boolean isVisible() {
		return vis;
	}

	@Override
	public void move() {
		if(vis){
			if(x > 0){
				x-=2;
			}else vis = false;
		}
		
	}

	@Override
	public void render() {
		if(!dester){
			anim.doAnimate(vis, condition.nullMissile);
			anim.cycle();
		}else if(dester && i <= 5){
			
			i++;
		}else vis = false;
		
		
		setImage(anim.getPlayerImage());
	}

	@Override
	public void setImage(BufferedImage i) {
		
		this.b = i;
		
	}

	@Override
	public BufferedImage getImage() {
	
		return b;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(b,x,y,f);
		
	}

	@Override
	public void setVisible(boolean vis) {
	  this.vis = vis;
	}

	@Override
	public void destroy(boolean dester) {
	
		this.dester = dester;
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x + 40, y + 30, 50, 50);
	}

	@Override
	public int getDamagePoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDamagePoint(int damagePoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getDestroy() {
		// TODO Auto-generated method stub
		return dester;
	}

}
