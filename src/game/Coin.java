package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.ArrayList;

public class Coin {
	static int coin_num;
	int x, y, radius;
	Image img;
	boolean isEaten;
	BufferedImage coinImage;
	
    
	public Coin(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		isEaten = false;
		
		//Get Image file
		Toolkit t = Toolkit.getDefaultToolkit();
		this.img = t.getImage("resources/coin.jpg");
		BufferedImageLoader loader = new BufferedImageLoader();
	    coinImage =loader.loadImage("./resources/coin.png");
	    
	}
	
	static boolean coinInit(ArrayList<Coin> c, int num)
	{
		if (num <= 0) return false;
		
		for (int i = 0; i < num; i++)
		{
			//Panel size: 1200x900, Sonic initial position: (100,100)
			int xMin = 200, xMax = 1000;
			int yMin = 200, yMax = 700;
			Random r = new Random();
			int xPos = (int) (xMin + r.nextFloat() * (xMax - xMin));
			int yPos = (int) (yMin + r.nextFloat() * (yMax - yMin));
			c.add(new Coin(xPos, yPos, 25));
			
		}
		return true;
	}
	
	   static int eatAmt(ArrayList<Coin> c)
	   {
	      int num = 0;
	      for (Coin coin : c) if (coin.isEaten) num++;
	      return num;
	   }
	   
	   static boolean allEaten(ArrayList<Coin> c)
	   {
	      if (c == null) return false;
	      if (c.size() == Coin.eatAmt(c)) return true;
	      else return false;
	   }
	
	static void coinRecover(ArrayList<Coin> c)
	{
		for (Coin coin : c) {
			coin.isEaten = false; 
			coin.coinClear();
		}
		System.out.println("Coin Position Recovered");
	}
	
    public void Sound(boolean Loop){

    	Clip clip;
    	AudioInputStream ais;
        
        try {
        	ais = AudioSystem.getAudioInputStream(new File("./resources/ring.wav"));
           clip = AudioSystem.getClip();

           clip.open(ais);
           clip.start();
           if (Loop) clip.loop(-1);
           //Loop 占쏙옙占쏙옙true占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙箕趺占쏙옙占신듸옙求占�.
        //false占쏙옙 占싼뱄옙占쏙옙占쏙옙占쏙옙占신듸옙求占�.
      
           } catch (Exception e) {
        e.printStackTrace();
           }

    }
    
    
	boolean hasIntersected(Sonic s) {
		if (isEaten) return false;
		double distance = Math.sqrt(Math.pow(this.x - Sonic.x, 2) + Math.pow(this.y - Sonic.y, 2));
		if(distance <= this.radius + Sonic.r) 
			{
				System.out.println("coin eat");
				isEaten = true;
				coin_num += 1;
				Sound(false);
				
				return true;
			}
		else return false;
	}
	int getCoin() {
		return coin_num;
	}
	void coinClear() {
		coin_num = 0;
	}
	void coinPlus(int num) {
		coin_num += num;
	}

	
	void Draw(Graphics g) {
		if(!isEaten) {
		Graphics2D g2d = (Graphics2D)g;
		//g2d.drawImage(img,x,y,radius,radius,null);
		g2d.drawImage(coinImage, x,y,null);
		}
	}
	
}