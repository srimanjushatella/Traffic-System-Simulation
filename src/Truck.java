import java.awt.Color;
import java.awt.Graphics;

public class Truck extends Vehicle{
	public Truck(int newx, int newy){
		super(newx, newy);
		width=150;
		height=40;
		speed=8;
	}
	
	public void paintMe(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

}
