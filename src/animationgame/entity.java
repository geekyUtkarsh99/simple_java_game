package animationgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface entity {

	public int getX();
	public int getY();
	public boolean isVisible();
	public void move();
	public void render();
	public void setImage(BufferedImage i);
	public BufferedImage getImage();
	public void draw(Graphics g);
	public void setVisible(boolean vis);
	public void destroy(boolean dester);
	public Rectangle getBounds();
	public int getDamagePoints();
	public void setDamagePoint(int damagePoints);
	public boolean getDestroy();
}
