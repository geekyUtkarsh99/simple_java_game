package animationgame;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import animationgame.animation.condition;
import animationgame.enemyBullets.fireRule;

public class frame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//reference variables
	private int pos_x = 0;
	private int pos_y = 0;
	private int delay_animation = 50;
	private boolean doRight = false;
	private boolean doLeft = false;
	private boolean doUp = false;
	private boolean doDown = false;
	private boolean ingame = false; 
	protected int score = 0;
	private boolean msgVisible = false;
	private boolean inMenu = true;
	private boolean doFire = false;
    private boolean visible = true;
    private boolean levelScreen = false;
	private boolean drawBar = true;
    private boolean enemy1 = false;
    private boolean pinion = false;
    private int wave = 1;
	private int enemyCap = 0;
	private boolean planet = false;
	private boolean nullShip = false;
	private boolean nullShipFire = false;
	private boolean updater =false;
	private int arrowX,arrowY;
	private int fpsM = 60;
	private int fps = 1;
	private long bef,af,ref,wait;
	private int avgrate = 1;
	private int width = Toolkit.getDefaultToolkit().getScreenSize().width,
			height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// testing purpose
	JTextField l = new JTextField();
	
	//planet room
	planetRoom pRoom = new planetRoom();
	
	//objects of missile
	private missile m;
	private ArrayList <missile> mil = new ArrayList<missile>();
	
	//objects of enemy
	protected enemy e;
	protected ArrayList<enemy> en = new ArrayList<enemy>();
	
	//object for pinion
	protected pinion p;
	protected ArrayList<pinion> pin = new ArrayList<pinion>();
	
	//animation Object
	animation anim = new animation(5);
	
	// menu object
	menu men = new menu(width,height);

	//level screen object
	levelScreen lvl = new levelScreen();

	//health gauge 
	healthGauge he = new healthGauge();
	private ArrayList<healthGauge> hel = new ArrayList<healthGauge>();
	
	//bullet enemy1
	private ArrayList<enemyBullets> enb = new ArrayList<enemyBullets>();
	private enemyBullets enbul;

	// null ship enemy
	private  nullShip pl;
	private ArrayList<nullShip> pluto = new ArrayList<nullShip>();
	
	//nullship missile
	private nullShipMissile nullMis;
	private ArrayList<nullShipMissile> nullMissile = new ArrayList<nullShipMissile>();	
	
	//level selection
	chapter1 ch ;
	
	
	
	public frame(){
		
		initUi();
		
		
	}
	
	threadHandler t = new threadHandler(delay_animation){
		public void run(){
			while(true){
				condition con = condition.PLAYER;
				anim.doAnimate(visible, con);
				anim.cycle();
			
				graphics();	
				bulletAnimation();
				renderBulls();
				renderNullShip();
				renderPinion();
				try {
					Thread.sleep(t.getSpeed());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	};

	threadHandler t1 = new threadHandler(150){
		
		public void run(){
			while(true){
				if(ingame)
				fire();
				spawner();
				makeNullShip();
				pinionVisible();
				pRoom.render();
				try {
					Thread.sleep(t1.getSpeed());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				
			}
		}
		
	};
	
	threadHandler ship = new threadHandler(3000){
		public void run(){
			while(true){
				for(int i = 0; i < pluto.size(); i++){
					pl = pluto.get(i);
					
					if(pl.getMovable() == false){
	    				
	    				
		    			nullMissile.add(new nullShipMissile(pl.getX(),pl.getY()));
		    				
		    				
		    			}
					
					pl.setMovable(true);
				}
				
				try {
					Thread.sleep(ship.getSpeed());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	};
	
	
	
    public void doProcess(){
    	men.move();
		movement();
		menuRender();
		collisionDetectionM();
		collisionDetectionP();
		Destroy();
		destroyPluto();
		destroyEnemy();
	    repaint();
	}
	
	public void initUi(){
		try{
		setFocusable(true);
		}catch(Exception e){
			System.out.println("focus error");
		}
		setPreferredSize(new Dimension(1200,750));
		setBackground(Color.black);
		addKeyListener(bodyMovement);
		addKeyListener(fireRules);
	    addMouseListener(mouse);
	    this.addMouseMotionListener(m1);
	    l.setColumns(100);
	    l.setVisible(true);
	    add(l);
	 
	  
	}
	
	public void paintComponent(Graphics g){
		
		bef = System.currentTimeMillis();
		try{
		super.paintComponent(g);
		spaceShip(g);
		renderBullet(g);
	    spawn1(g);
	  drawBullets(g);
		drawBar();
		healthbar(g);
		waves(g);
		levelSelect();
		pRoom.draw(g, planet, 10, 75);
			if(msgVisible)
		gameOver(g);
	
		if(inMenu)
			men.menuDesign(g);
		
		if(levelScreen)
			lvl.levelScreenDesign(g);
		
		Toolkit.getDefaultToolkit().sync();
		doProcess();
		af = System.currentTimeMillis();
		wait = af - bef;
		if(wait > 10)
		wait=5;
		
		Thread.sleep(wait);
		
		ref+=wait;
		}catch(Exception e){
			
		}
		fps++;
		
	}
	
	threadHandler fpsCounter = new threadHandler(0){
		public void run(){
			while(true){
			
				System.out.println("fps : " + fps);
				avgrate = (int)ref/fps;
				simulationOptimizer();
				System.out.println("avg rate :" + avgrate);
				ref = 1;
				fps = 1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	};
	
	public void simulationOptimizer(){
		
	}
    
	
	 public void waves(Graphics g){
           //display waves
	    	if(ingame){
	    	g.setFont(new Font("",Font.BOLD,14));
	    	g.setColor(Color.WHITE);
	    	g.drawString("wave:" + wave + " score : " + score, 10, 10);
	    	}
	      
	    }
	 
	enum chapter1{
		l1,l2,l3,l4,l5,l6,l7,l8,l9,l10
	}
	
	public void spaceShip(Graphics g){
		if(ingame){
			Image i = new ImageIcon(getClass().getResource("/images/healthMeter.png")).getImage();
		g.drawImage(anim.getPlayerImage(),pos_x,pos_y,this);
		g.drawImage(i,-35,655,this);
		}
	}
	
	public void drawBar(){
		
		if(ingame && drawBar)	
		if(hel.size() <= 126){
			hel.add(new healthGauge());
		
			
		}else drawBar = false;
		
	}
	
	public void renderBulls(){
		for(int i = 0; i < enb.size(); i++){
			enbul = enb.get(i);
			enbul.render();
		}
		
		for(int l = 0; l < nullMissile.size(); l++){
			nullMis = nullMissile.get(l);
			nullMis.render();
		}
	}
	
     public void drawBullets(Graphics g){
		
		for(int i = 0;i < enb.size(); i++){
			enbul = enb.get(i);
			enbul.draw(g);
			enbul.move();
			
			if(enbul.isVisible() == false)
				enb.remove(i);
			
		}
		
		for(int l = 0; l < nullMissile.size(); l++){
			nullMis = nullMissile.get(l);
			nullMis.draw(g);
			nullMis.move();
			
			if(!nullMis.isVisible())
			     nullMissile.remove(nullMis);
		}
		
	}
	
  
	
	 public void spawn1(Graphics g){
	    	if(enemy1)
	    	for(int i = 0;i < en.size();i++){
	    		//enemy wave
	    		e = en.get(i);
	    		e.drawEnemy(g);
	    		e.doMove();
	    	 
	    	
	    		if(e.randomizer() == true){
	    			enb.add(new enemyBullets(e.getX(),e.getY() + 55,fireRule.n));
	    			
	    		}else e.randomizer();
	    		
	    	 
	    	
	    	}
	    	
	    	if(nullShip){
	    		for(int i = 0; i < pluto.size(); i++){
	    			pl = pluto.get(i);
	    			pl.draw(g);
	    			pl.move();
	    			
	    			
	    			
	    		}
	    	}
	    	
	    	if(pinion){
	    		for(int i = 0; i < pin.size(); i++){
	    			p = pin.get(i);
	    			p.drawPinion(g);
	    			p.move();
	    			
	    			if(p.randomizer()){
	    				enb.add(new enemyBullets(p.getX() + 10,p.getY() + 28,fireRule.mup));
	    				enb.add(new enemyBullets(p.getX() + 10,p.getY() + 28,fireRule.md));
	    				
	    			}else p.randomizer();
	    			
	    			
	    			if(!p.checkVisibility())
	    				pin.remove(p);
	    		}
	    	}
	    	
	    }
	 
	 public void makeNullShip(){
		 if(nullShip && ingame){
			 if(pluto.size() <= enemyCap){
			 Random r = new Random();
			 Random r1= new Random();
			 int a = r.nextInt(1200);
			 int b = r1.nextInt(600);
			 if(a > 200 && a < 900 && b > 10 && b < 600){
				 pluto.add(new nullShip(a,b));
			 
			 }
			 }
		 }
	 }
	 
	
	 public void graphics(){
		 
		 // render process
		 if(ingame && enemy1)
		 for(int i = 0; i < en.size(); i++){
			e = en.get(i);
			e.render();
		 }
	 }
	 
	 public void renderNullShip(){
		 if(ingame && nullShip)
		 for(int i = 0; i < pluto.size(); i++){
			 pl = pluto.get(i);
			 pl.render();
		 }
	 }
	 
	public void enemyDamage(){
		int i = hel.size() - 1;
		if(i < hel.size() && i >= 0){
		
		hel.remove(i);
		i--;
		
		}else{
		dispose();
		msgVisible = true;
		}
	}
	
	public void dispose(){
		en.clear();
		pin.clear();
		mil.clear();
		hel.clear();
		enb.clear();
		nullMissile.clear();
		pluto.clear();
		wave = 1;
		enemyCap = 0;
		ch = null;
		ingame = false;
		enemy1 = false;
		pinion = false;
		levelScreen = false;
		
	}
	
	public void healthbar(Graphics g){
		
		if(ingame){
		int x = 14;
		for(int i = 0; i < hel.size(); i++){
			
			he = hel.get(i);
			he.draw(g);
			he.setX(x);
			he.setColor(Color.green);
			x++;
		}
		updateBar();
		}
		
		
	}
	
	public void updateBar(){
		
		if(hel.size() <= 75 && hel.size() >= 35){
			for(healthGauge health : hel){
				//set color yellow
				he = health;
				he.setColor(Color.YELLOW);
			}
			}else if(hel.size() <= 35){
				for(healthGauge health : hel){
					//set color red
					he = health;
					he.setColor(Color.RED);
				}
			}
		}
	
	
	public void fire(){
		if(doFire && mil.size() <= 50){
				if(ingame)
				 mil.add(new missile(pos_x,pos_y));
				
				
		}
	}
	
	public void renderBullet(Graphics g){
		if(ingame)
		for(int i = 0 ; i < mil.size(); i++){
			m = mil.get(i);
			m.drawMissile(g);
			m.move();
			
		
		}
		
 	}
	
	public void bulletAnimation(){
		//bullet animation 
		for(int i = 0; i < mil.size(); i++){
			m = mil.get(i);
			m.render();
		}
	}
	
	public void Destroy(){
		for(int i = 0; i < mil.size(); i++){
		m = mil.get(i);
			if(m.isVisible() == false){
				mil.remove(m);
	
			}
		
		}
		
		
		
	}
	
	public void spawner(){
	
		if(ingame && enemy1){
		Random r = new Random();
		if(en.size() <= enemyCap)
		en.add(new enemy(r.nextInt(600)));
		
		}
	}
	
	public void pinionVisible(){
		if(ingame && pinion){
			Random r = new Random();
			if(pin.size() <= enemyCap)
				pin.add(new pinion(r.nextInt(600)));
		}
	}
	
	public void renderPinion(){
		if(pinion && ingame)
		for(int i = 0; i < pin.size(); i++){
			p = pin.get(i);
			p.render();
		}
	}
	
	public void destroyPluto(){
		for(int i = 0; i < pluto.size(); i++){
			pl = pluto.get(i);
			if(!pl.getVis()){
				pluto.remove(pl);
			}
		}
		
	}
	
	public void destroyEnemy(){
		for(int i = 0; i < en.size(); i++){
			e = en.get(i);
		
				if(e.checkVisibility() == false)
			      en.remove(e);
				
				
			}
    }
	
	public void collisionDetectionP(){
		
		Rectangle player = new Rectangle(pos_x + 60, pos_y + 39, 110, 80);	
		
		//for enemy1
		for(int i = 0;i < en.size(); i++){
			e = en.get(i);
			Rectangle r = e.getBounds();
			collider col = new collider();
			
			
			if(player.intersects(r)){
            if(col.isPixelCollide(pos_x, pos_y, anim.getPlayerImage(), e.getX(), e.getY(), e.getImage()))
			enemyDamage();
			break;
			}
			
		}
		
		//for enemyBullets
		 for(int l = 0; l < enb.size(); l++){
   		  enbul = enb.get(l);
   		  Rectangle r = enbul.getBounds();
   		  
   		  if(r.intersects(player)){
   			  enb.remove(l);
   			  int damLoop = 0;
   			  while(damLoop <= 3){
   			  enemyDamage();
   			  damLoop++;
   			  }
   		  }
   	  }
		
	  //for pinion
		 for(int i = 0; i < pin.size();i++){
			 p = pin.get(i);
			 Rectangle r = p.getBounds();
			 if(player.intersects(r)){
				 if(new collider().isPixelCollide(pos_x, pos_y, anim.getPlayerImage(), p.getX(), p.getY(), p.getImage())){
					 enemyDamage();
					 break;
				 }
			 }
		 }
		 
	  //for nullShip
		 for(int Null = 0; Null < pluto.size(); Null++){
			 pl = pluto.get(Null);
			 Rectangle nullBounds = pl.getBounds();
			 if(player.intersects(nullBounds)){
				 if(new collider().isPixelCollide(pos_x, pos_y, anim.getPlayerImage(), pl.getX(), pl.getY(), pl.getImage())){
					 enemyDamage();
					 break;
				 }
			 }
		 }
		 
	  //for nullShipBullet
		 for(int nullBulls = 0; nullBulls < nullMissile.size(); nullBulls++){
			 nullMis = nullMissile.get(nullBulls);
			 Rectangle Nulls = nullMis.getBounds();
			 if(player.intersects(Nulls)){
		   		enemyDamage();
					 nullMis.destroy(true);
					 break;
				 
			 }
		 }
		 
		
	}
	
	public void collisionDetectionM(){
		for(int i = 0;i < mil.size(); i++){
			m = mil.get(i);
			Rectangle r = m.getBounds();
			for(int l = 0; l < en.size(); l++){
				e = en.get(l);
				Rectangle r1 = e.getBounds();
				if(r.intersects(r1)){
					
				m.destroyMissile(true);
				e.setDamage(true);
		
			
				}
				if(e.getDestroy()){
					if(e.i >= 5)
						score++;
				}
				
				
			}
			
			for(int l = 0; l < pin.size(); l++){
				p = pin.get(l);
				Rectangle pinion = p.getBounds();
				if(r.intersects(pinion)){
						m.destroyMissile(true);
						p.setDamage(true);
					
					
					}
				if(p.getDestroy()){
					if(p.i >= 5)
					score ++;
				
					}
			}
			
			for(int j = 0; j < pluto.size(); j++){
				pl = pluto.get(j);
				Rectangle plutoBounds = pl.getBounds();
				if(plutoBounds.intersects(r)){
					m.setVisible(false);
					pl.setDamagePoint(1);
					pl.setDamage(true);
					
				}
				if(pl.getDestroy()){
					if(pl.i >= 5)
						score++;
						
					}
					
			}
					
			
		}
	}
	
	
	
	public void gameOver(Graphics g){
		if(msgVisible)
		if(!ingame){
			g.setFont(new Font("Helvetica",Font.BOLD,20));
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER :(\n", 100, 140);
			
			
			g.drawString("SCORE:"+score+"\n", 100, 160);
			g.drawString("PRESS ENTER", 100, 180);
		}
		
	}
	
	
	public void movement(){
		
		if(doRight && pos_x < 1100)
			pos_x+=avgrate;
		else if(doLeft && pos_x > -30)
			pos_x-=avgrate;
		else if(doUp && pos_y > -30)
			pos_y-=avgrate;
		else if(doDown && pos_y < 580)
			pos_y+=avgrate;
			
		
		
	}
	
	//menu functioning
	public void menuRender(){
		if(inMenu){
			Rectangle play = men.getBounds(0);
			Rectangle quit = men.getBounds(5);
			Rectangle cre = men.getBounds(4);
			Rectangle shop = men.getBounds(1);
			Rectangle conf = men.getBounds(2);
			Rectangle abs = men.getBounds(3);
			Rectangle arrow = new Rectangle(arrowX,arrowY,10,10);
			
			if(arrow.intersects(play)){
				men.setImage(1);
				
			}else if(arrow.intersects(quit)){
				men.setImage(6);
			}else if(arrow.intersects(cre)){
				men.setImage(5);
			}else if(arrow.intersects(shop)){
				men.setImage(2);
			}else if(arrow.intersects(conf)){
				men.setImage(3);
			}else if(arrow.intersects(abs)){
				men.setImage(4);
			}else men.setImage(0);
			
		}
		
	}
	
	
	KeyListener bodyMovement = new KeyListener(){

		@Override
		public void keyPressed(KeyEvent e) {
			int a = e.getKeyCode();
			
			if(a == KeyEvent.VK_RIGHT){
				doRight = true;
				doLeft = false;
				doUp = false;
				doDown = false;
			}
			else if(a == KeyEvent.VK_LEFT){
				doRight = false;
				doLeft = true;
				doUp = false;
				doDown = false;
			}
			else if(a == KeyEvent.VK_UP){
				doRight = false;
				doLeft = false;
				doUp = true;
				doDown = false;
			}
			else if(a == KeyEvent.VK_DOWN){
				doRight = false;
				doLeft = false;
				doUp = false;
				doDown = true;
			}else if(a == KeyEvent.VK_ESCAPE)
				System.exit(0);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
            int a = e.getKeyCode();
			
			if(a == KeyEvent.VK_RIGHT){
				doRight = false;
				doLeft = false;
				doUp = false;
				doDown = false;
			}
			else if(a == KeyEvent.VK_LEFT){
				doRight = false;
				doLeft = false;
				doUp = false;
				doDown = false;
			}
			else if(a == KeyEvent.VK_UP){
				doRight = false;
				doLeft = false;
				doUp = false;
				doDown = false;
			}
			else if(a == KeyEvent.VK_DOWN){
				doRight = false;
				doLeft = false;
				doUp = false;
				doDown = false;
			}else if(a == KeyEvent.VK_ESCAPE)
				System.exit(0);
			
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	KeyListener fireRules = new KeyListener(){

		@Override
		public void keyPressed(KeyEvent e) {
			
			int a = e.getKeyCode();
			
			if(a == KeyEvent.VK_SPACE){
			doFire = true;
			}else if(a == KeyEvent.VK_ENTER){
			   if(!ingame && !planet && !levelScreen && !inMenu){
				msgVisible = false;
			    inMenu = true;
			    score = -1;
			    men.resetPosition();
			   }}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
          int a = e.getKeyCode();
          if(a == KeyEvent.VK_SPACE)
        	  doFire = false;
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
			
		}
		
	};
	
	MouseMotionListener m1 = new MouseMotionListener(){

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		int a = 0,b = 0;
		 a = e.getX();
		 b = e.getY();
		
		l.setText(a + "--" + b);
			arrowX = a;
			arrowY = b;
		
		}
		
	};
	
	MouseListener mouse = new MouseListener(){

	
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
	       //get arrow location
			int a = e.getX();
			int b = e.getY();
			//create rectangle
		    Rectangle arrow = new Rectangle(a,b,10,10);
			
			
			if(inMenu){
			
				Rectangle play = men.getBounds(0);
			Rectangle quit = men.getBounds(5);
			if(arrow.intersects(play)){
				inMenu = false;
			    levelScreen = false;
			    ingame = false;
				planet = true;
				
			}else if(arrow.intersects(quit)){
				System.exit(1);
			}
			
			
				
			
			}else if(levelScreen){
				
		
					Rectangle lvl1 = lvl.getBounds(chapter1.l1);
					Rectangle lvl2 = lvl.getBounds(chapter1.l2);
					Rectangle lvl3 = lvl.getBounds(chapter1.l3);
					Rectangle lvl4 = lvl.getBounds(chapter1.l4);
					
					if(arrow.intersects(lvl1)){
						ingame = true;
						drawBar = true;
						levelScreen = false;
						ch = chapter1.l1;
					pos_x = 10;
					pos_y = 10;
					
					}else if(arrow.intersects(lvl2)){
						
						ingame = true;
						drawBar = true;
						levelScreen = false;
						ch = chapter1.l2;
						pos_x = 10;
						pos_y = 10;
						
					}else if(arrow.intersects(lvl3)){
						ingame = true;
						drawBar = true;
						levelScreen = false;
						ch = chapter1.l3;
						pos_x = 10;
						pos_y = 10;
					}else if(arrow.intersects(lvl4)){
						ingame = true;
						drawBar = true;
						levelScreen = false;
						ch = chapter1.l4;
						pos_x = 10;
						pos_y = 10;
					}
						
						
				}else if(planet){
					
					Rectangle plan1 = pRoom.getBounds(1);
					
					//test click
					if(arrow.intersects(plan1)){
						levelScreen = true;
						planet = false;
					}
					
				}
			}
		

		@Override
		public void mouseReleased(MouseEvent arg0) {
		
		}
		
	};
	
	
		//for chapter1 --- planet 1
		public void levelSelect(){
			   if(ch == chapter1.l1){
				  
			   if(wave <=3)
			   if(wave == 1){
				  enemyCap = 3;
				 
				 enemy1 = true;
				  if(score >= 120)
					  wave++;
				  
				  
			   }else if(wave == 2){
				enemy1 = true;
				if(score >= 220)
					wave++;
			   }else if(wave == 3){
				   enemy1 = true;
				   if(score >= 350){
					   
				   msgVisible = true;
				   dispose();
				   }
			   }
			   }else if(ch == chapter1.l2){
				   if(wave <= 4){
					   if(wave == 1){
						   enemyCap = 5;
						  pinion = true;
						   if(score >= 300){
							   wave++;
							pin.clear();   
						   }
					   }else if(wave == 2){
						   enemy1 = true;
						   if(score >= 450){
							   wave++;
							en.clear();   
						   }
					   }else if(wave == 3){
						  pinion = true;
						  enemy1 = true;
						  enemyCap = 2;
						   if(score >= 600){
							   wave++;
							pin.clear();
							en.clear();
						   }
					   }else if(wave == 4){
						   enemy1 = true;
						   pin.clear();
						   if(score >= 900){
							  dispose();
							msgVisible = true;  
						   }
					   }
				   }
			   }else if(ch == chapter1.l3){
				   if(wave <= 3){
					   enemyCap = 4;
					   if(wave == 1){
						   pinion = true;
						   if(score >= 200){
							   pin.clear();
							   wave++;
						   }
					   }else if(wave == 2){
						   pinion = true;
						   if(score >= 450){
							   pin.clear();
							   wave++;
						   }
					   }else if(wave == 3){
						   pinion = true;
						   if(score >= 650){
							   dispose();
							   msgVisible = true;
						   }
					   }
				   }
				   
			   }else if(ch == chapter1.l4){
				   if(wave <= 3){
					   if(wave == 1){
						   enemyCap = 5;
						   pinion = true;
						   if(score >= 450){
							   pin.clear();
							   wave++;
						   }
						   
					   }else if(wave == 2){
						   enemy1 = true;
						   pinion = true;
						   if(score >= 800){
							   pin.clear();
							   en.clear();
							   wave++;
						   }
					   }else if(wave == 3){
						   pin.clear();
						   en.clear();
						   enemyCap = 2;
						   nullShip = true;
						   if(score >= 1000){
							   dispose();
							   msgVisible = true;
						   }
					   }
				   }
			   }
		   }
		
	
	
	
}
