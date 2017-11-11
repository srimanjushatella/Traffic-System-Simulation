import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Car extends Vehicle{
	
	Image myImage;
	
	public Car(int newx, int newy){
		super(newx, newy);
		width=120;
		height=40;
		speed=5;
		try{
			myImage = ImageIO.read(new File("car1.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void paintMe(Graphics g){
		/*g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);*/
		g.drawImage(myImage, x, y, null);
	}

}
