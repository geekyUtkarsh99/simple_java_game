package animationgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import animationgame.animation.condition;


public class menu extends displayWindow{
 
	protected Image i;
	private Image[] Buttons = new Image[10];
	private Image[] glow = new Image[10];
	private Image play;
	private Image shop;
	private Image conf;
	private Image abs;
	private Image cre;
	private Image ex;
	private Image he;
	private Image fac;
	private Image ins;
	private Image tw;
	private frame f;
	protected Image l;
	private int width,height;
	private BufferedImage b;
	private int finalx = 32,finaly = 435;
	private int[] inx = {-100,-200,-300,-400,-500,-600};
	
	
	public menu(int width,int height){
		this.width = width;
		this.height = height;
		
		loadPics();
	}
	
	public void menuDesign(Graphics g){
		
		menuSetup(g);
		
		
	}

    public void loadPics(){
    	
    	
    //background
    	 ImageIcon im1 = new ImageIcon(getClass().getResource("/images/mainBase.png"));
    	 i = im1.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT);
     	 
    //buttons
    	 Buttons[0] = new ImageIcon(getClass().getResource("/images/play.png")).getImage();
    	 Buttons[1] = new ImageIcon(getClass().getResource("/images/shop.png")).getImage();
    	 Buttons[2] = new ImageIcon(getClass().getResource("/images/configure.png")).getImage();
    	 Buttons[3] = new ImageIcon(getClass().getResource("/images/aboutus.png")).getImage();
    	 Buttons[4] = new ImageIcon(getClass().getResource("/images/credit.png")).getImage();
    	 Buttons[5] = new ImageIcon(getClass().getResource("/images/Exit.png")).getImage();
    	 Buttons[6] = new ImageIcon(getClass().getResource("/images/help.png")).getImage();
    	 Buttons[7] = new ImageIcon(getClass().getResource("/images/Facebook.png")).getImage();
    	 Buttons[8] = new ImageIcon(getClass().getResource("/images/instagram.png")).getImage();
    	 Buttons[9] = new ImageIcon(getClass().getResource("/images/twitter.png")).getImage();
     	
    	 //glow
    	 glow[0] = new ImageIcon(getClass().getResource("/images/playglow.png")).getImage();
    	 glow[1] = new ImageIcon(getClass().getResource("/images/shopglow.png")).getImage();
    	 glow[2] = new ImageIcon(getClass().getResource("/images/configuration.png")).getImage();
    	 glow[3] = new ImageIcon(getClass().getResource("/images/aboutusglow.png")).getImage();
    	 glow[4] = new ImageIcon(getClass().getResource("/images/creditglow.png")).getImage();
    	 glow[5] = new ImageIcon(getClass().getResource("/images/exitglow.png")).getImage();
    	 glow[6] = new ImageIcon(getClass().getResource("/images/helpglow.png")).getImage();
    	 glow[7] = new ImageIcon(getClass().getResource("/images/facebookglow.png")).getImage();
    	 glow[8] = new ImageIcon(getClass().getResource("/images/instagramglow.png")).getImage();
    	 glow[9] = new ImageIcon(getClass().getResource("/images/twitterglow.png")).getImage();
    	 
    	 //by default
    	 play = Buttons[0];
    	 shop = Buttons[1];
    	 conf = Buttons[2];
    	 abs = Buttons[3];
    	 cre = Buttons[4];
    	 ex = Buttons[5];
    	 he = Buttons[6];
    	 fac = Buttons[7];
    	 ins = Buttons[8];
    	 tw = Buttons[9];
    	 
    	 
    }
	
	public void menuSetup(Graphics g){
		
		
		
	    //background
		g.drawImage(i,10,10,f);
		
		//buttons 
	    g.drawImage(play,inx[0],435,f);
		g.drawImage(shop,inx[1],490 - 5,f);
		g.drawImage(conf,inx[2],540 - 5,f);
		g.drawImage(abs,inx[3],590 - 5,f);
		g.drawImage(cre,inx[4],640 - 5,f);
		g.drawImage(ex,inx[5],690 - 5,f);
		
		
		
	}
	
	public void move(){
		
		if(inx[0] < finalx){
			inx[0]+=9;
		}else inx[0] = finalx;
		
		if(inx[1] < finalx)
			inx[1]+=9;
		else inx[1] = finalx;
		
		if(inx[2] < finalx)
			inx[2]+=9;
		else inx[2] = finalx;
		
		if(inx[3] < finalx)
			inx[3]+=9;
		else inx[3] = finalx;
		
		if(inx[4] < finalx)
			inx[4]+=9;
		else inx[4] = finalx;
		
		if(inx[5] < finalx)
			inx[5]+=9;
		else inx[5] = finalx;
		
		
	}
	
	public void resetPosition(){
		inx[0] = -100;
		inx[1] = -200;
		inx[2] = -300;
		inx[3] = -400;
		inx[4] = -500;
		inx[5] = -600;
	
	}
	
	public Rectangle getBounds(int a){
		switch(a){
		case 0 : return new Rectangle(32, 435, 250, 45);
		
		case 1 : return new Rectangle(32,490,250,45);
		
		case 2 : return new Rectangle(32,540,250,45);
		
        case 3 : return new Rectangle(32,590,250,45);
		
		case 4 : return new Rectangle(32,640,250,45);
		
		case 5 : return new Rectangle(32,690,250,45);
		
		default : return null;
		}
	}
	
	public void setImage(int u){
		
	if(u == 0){
		 play = Buttons[0];
    	 shop = Buttons[1];
    	 conf = Buttons[2];
    	 abs = Buttons[3];
    	 cre = Buttons[4];
    	 ex = Buttons[5];
    	 he = Buttons[6];
    	 fac = Buttons[7];
    	 ins = Buttons[8];
    	 tw = Buttons[9];
	}
	
		if(u == 1)
		play = glow[0];
	else play = Buttons[0];
		
		if(u == 2)
		shop = glow[1];
	else shop = Buttons[1];
		
		if(u == 3)
		conf = glow[2];
	else conf = Buttons[2];
		
		if(u == 4)
		abs = glow[3];
	else abs = Buttons[3];
		
		if(u == 5)
		cre = glow[4];
	else cre = Buttons[4];
		
		if(u == 6)
		ex = glow[5];
	else ex = Buttons[5];
		
		
		if(u == 7)
		he = glow[6];
	else he = Buttons[6];
		
		
		if(u == 8)
		fac = glow[7];
	else fac = Buttons[7];
		
		
		if(u == 9)
		ins = glow[8];
	else ins = Buttons[8];
		
		
		if(u == 10)
		tw = glow[9];
		else tw = Buttons[9];
		
		
	}





}

