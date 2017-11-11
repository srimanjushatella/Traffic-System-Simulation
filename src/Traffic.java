import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Traffic implements ActionListener, Runnable {
	JFrame frame = new JFrame("Traffic System");
	Road road = new Road();
	//south container
	JButton start = new JButton("start");
	JButton stop = new JButton("stop");
	JLabel throughput = new JLabel("Throughput:0");
	Container south = new Container();
	//west container
	JButton car = new JButton("add car");
	JButton truck = new JButton("add truck");
	JButton sports = new JButton("add sports");
	Container west = new Container();
	
	boolean running = false;
	int carCount = 0;
	long startTime = 0;
	
	public Traffic(){
		frame.setSize(1000, 600);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1,3));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(throughput);
		frame.add(south, BorderLayout.SOUTH);
		
		west.setLayout(new GridLayout(3,1));
		west.add(car);
		car.addActionListener(this);
		west.add(truck);
		truck.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		/*Car testCar= new Car(10,20);
		road.addCar(testCar);
		frame.repaint();
		Truck testTruck= new Truck(10,170);
		road.addCar(testTruck);
		frame.repaint();
		Sports testSports= new Sports(10,320);
		road.addCar(testSports);
		frame.repaint();*/
	}

	public static void main(String[] args) {
		new Traffic();

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(start)){
			if(running==false){
			running=true;
			road.resetCarCount();
			startTime = System.currentTimeMillis();
			Thread t = new Thread(this);
			t.start();
		}
		}
		if(event.getSource().equals(stop)){
			running=false;
		}
		if(event.getSource().equals(car)) {
			Car car = new Car(0,20);
			road.addCar(car);
			for(int x=0;x < road.ROAD_WIDTH;x=x + 20){
			for(int y=40;y < 480;y= y + 120){
				car.setX(x);
				car.setY(y);
				if(road.collision(x, y, car) == false) {
					frame.repaint();
					return;
				}
			}
		  }
		}
		if(event.getSource().equals(truck)) {
			Truck truck = new Truck(0,30);
			road.addCar(truck);
			for(int x=0;x < road.ROAD_WIDTH;x=x + 20){
			for(int y=40;y < 480;y= y + 120){
				truck.setX(x);
				truck.setY(y);
				if(road.collision(x, y, truck) == false) {
					frame.repaint();
					return;
				}
			}
		  }
		}
		if(event.getSource().equals(sports)) {
			Sports sports = new Sports(0,30);
			road.addCar(sports);
			for(int x=0;x < road.ROAD_WIDTH;x=x + 20){
			for(int y=40;y < 480;y= y + 120){
				sports.setX(x);
				sports.setY(y);
				if(road.collision(x, y, sports) == false) {
					frame.repaint();
					return;
				}
			}
		  }
		}
	}

	@Override
	public void run() {
		while(running==true){
			road.step();
			carCount = road.getCarCount();
			double throughputCalc = carCount / (double) (System.currentTimeMillis() - startTime);
			throughput.setText("Throughput:"+throughputCalc);
			frame.repaint();
			try{
				Thread.sleep(100);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		
	}

}
