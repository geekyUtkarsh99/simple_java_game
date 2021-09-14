package animationgame;

public class threadHandler extends Thread implements threadsup{

	
	private int speed;
	
	
	public threadHandler(int speed) {
		super();
		this.speed = speed;
		start();
		
	}
	
	
	
	public int getSpeed(){
		return speed;
	}
	

	
}
