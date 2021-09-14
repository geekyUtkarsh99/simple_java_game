package animationgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class animation {

	
	private frame f;
	private int animate = 0;
	private int frames = 0;
	private BufferedImage refer ;
	private Image[] player = new Image[5];
	private Image[] missile = new Image[5];
	private Image[] mdest = new Image[5];
	private Image[] enemy1 = new Image[5];
	private Image[] enemy1dest = new Image[5];
	private Image[] pinion = new Image[5];
	private Image[] enbul1 = new Image[5];
	private Image[] plan1 = new Image[10];
	private Image[] pluto = new Image[5];
	private Image[] nullShip = new Image[5];
	private Image[] nullMissile = new Image[5];
	
	
	private BufferedImage buff,b1;
	private Image[] damaged = new Image[10];
	
	public animation(int frames){
		this.frames = frames;
		loadPics();
	}
	
	public void loadPics(){
		//player images
		ImageIcon i1 = new ImageIcon(getClass().getResource("/images/ship1.png"));
		player[0] = i1.getImage();
		ImageIcon i2 = new ImageIcon(getClass().getResource("/images/ship2.png"));
		player[1] = i2.getImage();
		ImageIcon i3 = new ImageIcon(getClass().getResource("/images/ship3.png"));
		player[2] = i3.getImage();
		ImageIcon i4 = new ImageIcon(getClass().getResource("/images/ship4.png"));
		player[3] = i4.getImage();
		ImageIcon i5 = new ImageIcon(getClass().getResource("/images/ship5.png"));
		player[4] = i5.getImage();
		
	
		//missile images
		ImageIcon missile1 = new ImageIcon(getClass().getResource("/images/bullet1.png"));
		missile[0] = missile1.getImage();
		ImageIcon missile2 = new ImageIcon(getClass().getResource("/images/bullet2.png"));
		missile[1] = missile2.getImage();
		ImageIcon missile3 = new ImageIcon(getClass().getResource("/images/bullet3.png"));
		missile[2] = missile3.getImage();
		ImageIcon missile4 = new ImageIcon(getClass().getResource("/images/bullet4.png"));
		missile[3] = missile4.getImage();
		ImageIcon missile5 = new ImageIcon(getClass().getResource("/images/bullet5.png"));
		missile[4] = missile5.getImage();
	
		//missile destroy image
		ImageIcon mdest1 = new ImageIcon(getClass().getResource("/images/bdestroy3.png"));
		mdest[0] = mdest1.getImage();
		ImageIcon mdest2 = new ImageIcon(getClass().getResource("/images/bdestroy4.png"));
		mdest[1] = mdest2.getImage();
		ImageIcon mdest3 = new ImageIcon(getClass().getResource("/images/bdestroy1.png"));
		mdest[2] = mdest3.getImage();
		ImageIcon mdest4 = new ImageIcon(getClass().getResource("/images/bdestroy5.png"));
		mdest[3] = mdest4.getImage();
		ImageIcon mdest5 = new ImageIcon(getClass().getResource("/images/bdestroy2.png"));
		mdest[4] = mdest5.getImage();
		
		//enemy images
		ImageIcon en1 = new ImageIcon(getClass().getResource("/images/framevd1(pixel).png"));
		enemy1[0] = en1.getImage();
		ImageIcon en2 = new ImageIcon(getClass().getResource("/images/framevd2(pixel).png"));
		enemy1[1] = en2.getImage();
		ImageIcon en3 = new ImageIcon(getClass().getResource("/images/framevd3(pixel).png"));
		enemy1[2] = en3.getImage();
		ImageIcon en4 = new ImageIcon(getClass().getResource("/images/framevd4(pixel).png"));
		enemy1[3] = en4.getImage();
		ImageIcon en5 = new ImageIcon(getClass().getResource("/images/framevd5(pixel).png"));
		enemy1[4] = en5.getImage();
	
	 
		//enemy damage
		ImageIcon damage = new ImageIcon(getClass().getResource("/images/damageEnemy.png"));
		damaged[0] = damage.getImage();
	
		//destroy enemy1
		ImageIcon en1d1 = new ImageIcon(getClass().getResource("/images/damage1.png"));
		enemy1dest[0] = en1d1.getImage();
		ImageIcon en1d2 = new ImageIcon(getClass().getResource("/images/damage2.png"));
		enemy1dest[1] = en1d2.getImage();
		ImageIcon en1d3 = new ImageIcon(getClass().getResource("/images/damage3.png"));
		enemy1dest[2] = en1d3.getImage();
		ImageIcon en1d4 = new ImageIcon(getClass().getResource("/images/damage4.png"));
		enemy1dest[3] = en1d4.getImage();
		ImageIcon en1d5 = new ImageIcon(getClass().getResource("/images/damage5.png"));
		enemy1dest[4] = en1d5.getImage();
		
		
		// pinion images
		pinion[0] = new ImageIcon(getClass().getResource("/images/pinonp1.png")).getImage();
		pinion[1] = new ImageIcon(getClass().getResource("/images/pinonp2.png")).getImage();
		pinion[2] = new ImageIcon(getClass().getResource("/images/pinonp3.png")).getImage();
		pinion[3] = new ImageIcon(getClass().getResource("/images/pinonp4.png")).getImage();
		pinion[4] = new ImageIcon(getClass().getResource("/images/pinonp5.png")).getImage();
		
		//pinion damage
		damaged[1] = new ImageIcon(getClass().getResource("/images/piniondamage.png")).getImage();
		
		//enemy bullets
		enbul1[0] = new ImageIcon(getClass().getResource("/images/enbul1.png")).getImage();
		enbul1[1] = new ImageIcon(getClass().getResource("/images/enbul2.png")).getImage();
		enbul1[2] = new ImageIcon(getClass().getResource("/images/enbul3.png")).getImage();
		enbul1[3] = new ImageIcon(getClass().getResource("/images/enbul4.png")).getImage();
		enbul1[4] = new ImageIcon(getClass().getResource("/images/enbul5.png")).getImage();
		
		//planet 1
		plan1[0] = new ImageIcon(getClass().getResource("/images/plan1.png")).getImage();
		plan1[1] = new ImageIcon(getClass().getResource("/images/plan2.png")).getImage();
		plan1[2] = new ImageIcon(getClass().getResource("/images/plan3.png")).getImage();
		plan1[3] = new ImageIcon(getClass().getResource("/images/plan4.png")).getImage();
		plan1[4] = new ImageIcon(getClass().getResource("/images/plan5.png")).getImage();
		plan1[5] = new ImageIcon(getClass().getResource("/images/plan6.png")).getImage();
		plan1[6] = new ImageIcon(getClass().getResource("/images/plan7.png")).getImage();
		plan1[7] = new ImageIcon(getClass().getResource("/images/plan8.png")).getImage();
		plan1[8] = new ImageIcon(getClass().getResource("/images/plan9.png")).getImage();
		plan1[9] = new ImageIcon(getClass().getResource("/images/plan10.png")).getImage();
		
		//null ship
		pluto[0] = new ImageIcon(getClass().getResource("/images/pluto1.png")).getImage();
		pluto[1] = new ImageIcon(getClass().getResource("/images/pluto2.png")).getImage();
		pluto[2] = new ImageIcon(getClass().getResource("/images/pluto3.png")).getImage();
		pluto[3] = new ImageIcon(getClass().getResource("/images/pluto4.png")).getImage();
		pluto[4] = new ImageIcon(getClass().getResource("/images/pluto5.png")).getImage();
		nullShip[0] = new ImageIcon(getClass().getResource("/images/Nullship1.png")).getImage();
		nullShip[1] = new ImageIcon(getClass().getResource("/images/Nullship2.png")).getImage();
		nullShip[2] = new ImageIcon(getClass().getResource("/images/Nullship1.png")).getImage();
		nullShip[3] = new ImageIcon(getClass().getResource("/images/Nullship2.png")).getImage();
		nullShip[4] = new ImageIcon(getClass().getResource("/images/Nullship1.png")).getImage();
		damaged[2] = new ImageIcon(getClass().getResource("/images/plutoDamage.png")).getImage();
		
		//nullship missile
		nullMissile[0] = new ImageIcon(getClass().getResource("/images/Bull1.png")).getImage();
		nullMissile[1] = new ImageIcon(getClass().getResource("/images/Bull2.png")).getImage();
		nullMissile[2] = new ImageIcon(getClass().getResource("/images/Bull3.png")).getImage();
		nullMissile[3] = new ImageIcon(getClass().getResource("/images/Bull4.png")).getImage();
		nullMissile[4] = new ImageIcon(getClass().getResource("/images/Bull5.png")).getImage();
		
		
		
	}
	
	enum condition{
		PLAYER,MISSILE,ENEMY,SHOOTING,MISSILEDESTOYED,enemydamage,enemyDest,pinion,enemAttack,
		plan1,nullship,pindam,nullShipfire,nullMissile,nullDam
	}
	
	public BufferedImage setBuffer(Image i){
		buff = new BufferedImage(i.getWidth(null),i.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(i,0,0,null);
		return buff;
	}
	
	public void doAnimate(Boolean visible,condition con){
		
	if(con == condition.PLAYER){
	
	    setPlayerImage(setBuffer(player[animate]));
		
	}else if(con == condition.MISSILE){
		 buff = new BufferedImage(missile[animate].getWidth(null),missile[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(missile[animate],0,0,null);
		
		setPlayerImage(buff);
	}else if(con == condition.MISSILEDESTOYED){
		 buff = new BufferedImage(mdest[animate].getWidth(null),mdest[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(mdest[animate],0,0,null);
		
       setPlayerImage(buff);
   
	}else if(con == condition.ENEMY){
		 buff = new BufferedImage(enemy1[animate].getWidth(null),enemy1[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(enemy1[animate],0,0,null);
		  setPlayerImage(buff);
		  
	}else if(con == condition.enemydamage){
		 buff = new BufferedImage(damaged[0].getWidth(null),damaged[0].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(damaged[0],0,0,null);
		
		setPlayerImage(buff);
	}else if(con == condition.enemyDest){
		 buff = new BufferedImage(enemy1dest[animate].getWidth(null),enemy1dest[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(enemy1dest[animate],0,0,null);
		
		 setPlayerImage(buff);
	}else if(con == condition.pinion){
		 buff = new BufferedImage(pinion[animate].getWidth(null),pinion[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(pinion[animate],0,0,null);
		
		setPlayerImage(buff);
			
	}else if(con == condition.enemAttack){
		 buff = new BufferedImage(enbul1[animate].getWidth(null),enbul1[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(enbul1[animate],0,0,null);
		
		setPlayerImage(buff);
			
	}else if(con == condition.plan1){
		 buff = new BufferedImage(plan1[animate].getWidth(null),plan1[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(plan1[animate],0,0,null);
		
			setPlayerImage(buff);
			
	}else if(con == condition.nullship){
		 buff = new BufferedImage(pluto[animate].getWidth(null),pluto[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.createGraphics();
		g.drawImage(pluto[animate],0,0,null);
		
			 setPlayerImage(buff);
			
	}else if(con == condition.pindam){
		 buff = new BufferedImage(damaged[1].getWidth(null),damaged[1].getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = buff.createGraphics();
			g.drawImage(damaged[1],0,0,null);
			
			setPlayerImage(buff);
	}else if(con == condition.nullShipfire){
		 buff = new BufferedImage(nullShip[animate].getWidth(null),nullShip[animate].getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = buff.createGraphics();
			g.drawImage(nullShip[animate],0,0,null);
			
				 setPlayerImage(buff);
	}else if(con == condition.nullMissile){
		setPlayerImage(setBuffer(nullMissile[animate]));
	}else if(con == condition.nullDam){
		setPlayerImage(setBuffer(damaged[2]));
	}

	}
	
	
	public void cycle(){
		
		for(int i = 0;i <=4;){
			animate++;
			if(animate == frames)
				animate = 0;
			break;
		}
		
		
	}
	
	public BufferedImage getPlayerImage(){
		return refer;
	}
	
	public void setPlayerImage(BufferedImage i){
		refer = i;
	}
	
	
}
