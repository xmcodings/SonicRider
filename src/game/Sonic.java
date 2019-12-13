package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.sql.XAConnection;

public class Sonic {
    
    
    
    float speed;
    float x_velocity;
    float y_velocity;
    float x_accel = 0;
    float gravity=-1;
    float y_accel = gravity;
    float redAcc;

    static float x=100;
    static float y=100;
    static float r=30;
    float small_r=10;
    
    
    private float degrees = 0;
    
    private boolean isPlay;
    
    // image
    
    
    BufferedImage sonicImage;
    
    public Sonic()
    {
        isPlay = false;
        speed = 10; //
        x_velocity = 0; 
        y_velocity= 0;
        x_accel = 0;
        gravity=-0.5f;
        y_accel = gravity;
        redAcc = 1.1f;
      
        BufferedImageLoader loader = new BufferedImageLoader();
    	sonicImage = loader.loadImage("./resources/sonicImage.png");
    	        
    }
    
    
    void Draw(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D)g;
        if(GameMain.isPlay)
        {
            
            g2d.setColor(Color.BLACK);      
            //g2d.fillOval((int)(x-r), (int)(y-r), (int)(2*r+1), (int)(2*r+1));   //
            //System.out.println("game.Sonic.Draw()");
            g2d.setColor(Color.WHITE);
            Point p = getPointOnCircle(degrees, 20);
            //g2d.fillOval((int)(x+p.x-small_r), (int)(y+p.y-small_r), (int)(2*small_r+1), (int)(2*small_r+1));   //
            
            //g2d.dispose();
            
			/////////////////////////////////////////////////////// Draw Sonic Image
			g2d.translate((int)x,(int)y);
			g2d.rotate(degrees*10);
			g2d.drawImage(sonicImage, -(int)r,-(int)r, null );
			g2d.rotate(-degrees*10);
			g2d.translate(-(int)(x),-(int)(y));
			///////////////////////////////////////////////////////
            
			
			update();
			
        }
        else {
        	g2d.translate((int)x,(int)y);
        	g2d.drawImage(sonicImage, -(int)r,-(int)r, null );
        	g2d.translate(-(int)(x),-(int)(y));
        }
        // no2 -> image rotation
    }
    
    void update()
    {
    	x_velocity = x_velocity + x_accel;
    	y_velocity = y_velocity + y_accel;
    	x=x-x_velocity;
        y=y-y_velocity;
        degrees += speed;
        speed = -x_velocity;
        setposition();
    }
    
    boolean setposition()
    {
    	if(x>1200) {
    		x_velocity=-x_velocity;
    	}
    	else if(x<0) {
    		x_velocity=-x_velocity;
    	}
    	if(y<0) {
    		y_velocity=-y_velocity;
    	}
    	else if(y>900){
        	System.out.println("Game Over~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        	
    		return false;
    	}
    	return true;
    }
    
    Point getPointOnCircle(float degress, float radius) {
        double rads = Math.toRadians(degress - 90); // 0 becomes the top
        // Calculate the outer point of the line
        int xPosy = Math.round((float) (Math.cos(rads) * radius));
        int yPosy = Math.round((float) (Math.sin(rads) * radius));
        return new Point(xPosy, yPosy);        
    }

    void resetSonic()
    {
		x = 100;
		y = 100;
		speed = 20;
		x_velocity = 0;
		y_velocity = 0;
		x_accel = 0;
		redAcc=1.1f;
		y_accel = gravity;
		
    }
    


}
