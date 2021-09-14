package animationgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class CollisionUtil {

	private Image i;
	
	public CollisionUtil(){
	
	}
	
	public boolean checkCollission(BufferedImage b1,BufferedImage b2,Rectangle rect1,Rectangle rect2,int x1,int x2,int y1,int y2){
		
		
		
		HashSet<String> pixel1 = new HashSet<String>();
		HashSet<String> pixel2 = new HashSet<String>();
		if(rect1.intersects(rect2)){
		for(int i = 0; i < b1.getWidth(); i++){
			for(int j = 0; j < b1.getHeight(); j++){
				if(((b1.getRGB(i,j) >> 24) & 0xff) != 0)
				
				
					
					pixel1.add(i +"" + j);
				
			}
		}
	
		for(int i = 0; i < b2.getHeight(); i++){
			for(int j = 0; j < b2.getWidth(); j++){
				if(((b2.getRGB(j, i) >> 24) & 0xff) != 0)
				pixel1.add(j+""+(-i));
				
			}
		}
		
		pixel1.retainAll(pixel2);
		if(pixel1.size() > 0)
			return true;
		else return false;
		
		}else return false;
	}
		
	
	
}
