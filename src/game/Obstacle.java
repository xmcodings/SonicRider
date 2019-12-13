package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * 
 */
public class Obstacle 
{
   private int x, y;
   private int radius;
   boolean isTouch;
   BufferedImage obsImage;
   
   public Obstacle(int x, int y, int r)
   {
      this.x = x;
      this.y = y;
      this.radius = r;
      isTouch = false;
      
      BufferedImageLoader loader = new BufferedImageLoader();
       obsImage =loader.loadImage("./resources/bitcoin.png");
   }
   
   public void Sound(boolean Loop) {

		AudioInputStream ais;

		try {
			ais = AudioSystem.getAudioInputStream(new File("./resources/fail.wav"));
			Clip clip = AudioSystem.getClip();
			if (Loop) {
			clip.open(ais);
			clip.start();
			}
			else {
				
				clip.stop();
			    clip.setFramePosition(0);				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

   static boolean obsInit(ArrayList<Obstacle> o, ArrayList<Coin> c, int numPerCoin)
   {
      if (numPerCoin <= 0) return false;

      for (Coin coin : c)
      {
         int xMin = 0, xMax = 0, yMin = 0, yMax = 0; 
         int xPos, yPos;
         
         for (int i = 0; i < numPerCoin; i++)
         {
            Random r = new Random();
            
            xMin = coin.x - 100; xMax = coin.x + 100;
            yMin = coin.y - 100; yMax = coin.y + 100;
            do
            {
            xPos = r.nextInt(xMax - xMin + 1) + xMin; 
            yPos = r.nextInt(yMax - yMin + 1) + yMin;
            } while (((int) (Math.sqrt(Math.pow(xPos - coin.x, 2)
                  + Math.pow(yPos - coin.y, 2))) < 30));
            o.add(new Obstacle(xPos, yPos, 10));
         }
      }
      
      return true;
   }
   
   boolean hasIntersected(Sonic s)
   {
      if(isTouch) return false;
      double dist = Math.sqrt(Math.pow(this.x - Sonic.x, 2) + Math.pow(this.y - Sonic.y, 2));
      if (dist <= this.radius + Sonic.r)
      {
         Sound(true);
         isTouch = true;
         return true;
      }
      else return false;
      
      //sound
   }
   
   void Draw(Graphics g) 
   {
      {
      Graphics2D g2d = (Graphics2D)g;
      g2d.drawImage(obsImage, x, y, null);
      }
   }
   
   static void obsRecover(ArrayList<Obstacle> o)
   {
      for (Obstacle obs : o) obs.isTouch = false;
    
   }
   
}

