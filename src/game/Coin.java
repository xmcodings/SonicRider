package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Coin {
	int x, y, radius;
	Image img;
	
	public Coin(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		//Get Image file
		Toolkit t = Toolkit.getDefaultToolkit();
		this.img = t.getImage("resources/coin.jpg");
	}
	
	/*
	boolean getIntersected(Sonic s) {
		int distance = Math.sqrt(Math.pow(this.x+s.getX(),2)+Math.pow(this.y+s.getY(), 2));
		if(distance <= this.radius + s.getRadius()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	void Draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(img,x,y,20,20,GameView.getPanel());
	}
	
	*/
}