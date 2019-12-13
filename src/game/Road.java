package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Road {

	int x1, y1;
	int x2, y2;	
	int lineNumber; // for freeline
	int backupx;
	int backupy;
    int lineWeight = 3;
    Color lineColor;
    BufferedImage redArrow;
    //static double redTotalline;
    //double redline;
    
    
	void setX(int x){
            x1 = x;
            backupx = x1;
	}
	void setY(int y){
            y1 = y;
            backupy = y1;
	}
        
	public Road(int x1, int y1, int x2, int y2, Color linecolor) {
            this.x2 = x2;
            this.y2 = y2;
            this.x1 = x1;
            this.y1 = y1;
            this.lineColor = linecolor;
            
            BufferedImageLoader loader = new BufferedImageLoader();
            redArrow = loader.loadImage("./resources/redArrow.png");
	}
	
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public Color getColor()
	{
		return lineColor;
	}
	
	abstract double getLength();
	abstract double getTotalLength();
	abstract void resetLength();
	
	abstract void Draw(Graphics g);
	abstract void updateSize(int x, int y);
	public abstract boolean hasIntersected(Sonic sonic);
	
	
}
class Line extends Road {
	static double totalLength;
	double thisLength;
	
	public Line(int x1, int y1, int x2, int y2, Color linecolor) {
		super(x1, y1, x2, y2, linecolor);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	void Draw(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(lineColor);
            g2d.setStroke(new BasicStroke(lineWeight));
            //lineColor = Color.red;
            
            if(lineColor == Color.WHITE) {
                g2d.drawLine(x1, y1, x2, y2);
             }
             if(lineColor == Color.RED) {
                g2d.drawLine(x1, y1, x2, y2);
                double theta;
                if(x2 - x1 == 0)
                {
                	theta = 0;
                }
                else {
                	theta = Math.atan((y2-y1)/(x2-x1));
                }
                if(x2>x1) {
                   for(int temp =x1; temp<x2 ;temp= temp +40 ){
                      g2d.translate((int)temp, (int)(y1 + (y2-y1)*(temp-x1)/(x2-x1)));
                      g2d.rotate(theta+Math.PI);
                      g2d.drawImage(redArrow, -10,-10 , null);
                      g2d.rotate(-theta+Math.PI);
                      g2d.translate(-(int)temp,-(int)(y1 + (y2-y1)*(temp-x1)/(x2-x1)));
                   }
                }
                else{
                   for(int temp =x2; temp<x1 ;temp= temp +40 ){
                      g2d.translate((int)temp, (int)(y1 + (y2-y1)*(temp-x1)/(x2-x1)));
                      g2d.rotate(theta);
                      g2d.drawImage(redArrow, -10,-10 , null);
                      g2d.rotate(-theta);
                      g2d.translate(-(int)temp,-(int)(y1 + (y2-y1)*(temp-x1)/(x2-x1)));
                   }
                }   
             }
             if(lineColor == Color.GREEN) {
                 g2d.drawLine(x1, y1, x2, y2);
              }
	}
	
	void updateSize(int x, int y)
	{
	    x2 = x;
	    y2 = y;
	}
    
	public boolean hasIntersected(Sonic sonic) {

    	float tangent;
    	float c=(float) Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
    	float cos=(x2-x1)/c;
    	if(x2 - x1 != 0)
    	{
    		tangent = Math.abs((y2-y1)/(x2-x1));
    	}
    	else {
    		tangent = 0;
    	}
    	if(tangent>0) {
    		cos=-(x2-x1)/c;
    	}
    	float sin=(y2-y1)/c;
    	//cossin x �ӵ�
    	//sinsin y �ӵ�

		if((Math.abs((y2-y1)*(sonic.x-x1)-(x2-x1)*(sonic.y-y1))/Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))< sonic.r + 0.1)
			&& (sonic.x <= Math.max(x1, x2) && sonic.x >= Math.min(x1, x2))) {

			if(lineColor == Color.WHITE) {
				/*
    			sonic.x_accel=(sonic.gravity*sin*cos);
    			System.out.println(sonic.x_velocity);
    			if(y1<y2) {
    				sonic.y_velocity=(float) 2;
    				System.out.println(sonic.x_velocity);
    			}
    			else {
    				sonic.y_velocity=(float) 2;
    				System.out.println(sonic.x_velocity);
    			}
    			sonic.redAcc = 0.05f;
    			*/
			//	if((sonic.x <= Math.max(x1, x2)-sonic.r/2 && sonic.x >= Math.min(x1, x2)+sonic.r/2)) {
		            float Accel = (float)Math.sqrt(Math.pow(sonic.x_accel,2) + Math.pow(sonic.y_accel,2)); 
		            if(y1 >y2) {
		               int temp = x1;
		               x1 = x2;
		               x2 =temp;
		               temp = y1;
		               y1 = y2;
		               y2 = temp;
		            }
		            tangent = Math.abs((y2-y1)/(x2-x1));
		            sonic.speed += tangent;
		            sonic.x_velocity = (float) -((x2 - x1)/Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))*4)*2 ;
		               sonic.y_velocity = (float) -((y2 - y1)/Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))*4)*2 ;
		               sonic.y -= sonic.r/4;
		               sonic.y_velocity -= 2*sonic.gravity;
				}
			//}
			else if(lineColor == Color.RED) {
				sonic.x_velocity = (float) -((x2 - x1)/Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))*4)*2 * sonic.redAcc;
	            sonic.y_velocity = (float) -((y2 - y1)/Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))*4)*2 * sonic.redAcc;
	            sonic.y_velocity -= sonic.gravity;
	            sonic.redAcc = sonic.redAcc* 1.0005f;
			}
			else { // green
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	
	double getLength()
	{
		double x_delta = Math.abs(x1 - x2);
		double y_delta = Math.abs(y1 - y2);
		if(lineColor == Color.RED)
		{
			//redline = Math.hypot(x_delta, y_delta);
		}
		thisLength = Math.hypot(x_delta, y_delta);
		return thisLength;
	}

	
	double getTotalLength()
	{
		totalLength = getLength() + totalLength;
		
		return totalLength;
	}
	void resetLength() {
		totalLength = 0;
	}
        
	
}

class Curve extends Road{

	ArrayList<Line> lines = new ArrayList<Line>();
	ArrayList<Integer> xpoints = new ArrayList<>();
	ArrayList<Integer> ypoints = new ArrayList<>();
	int beforex, beforey;
	Color curveColor;
	static double totalLength;
	double thisLength;
	
	public Curve(int x1, int y1, int x2, int y2, Color curvecolor) {
		super(x1, y1, x2, y2, curvecolor);
		// TODO Auto-generated constructor stub
		lineColor = curvecolor;
		curveColor = curvecolor;
	}

	@Override
	void Draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(lineColor);
		if(!lines.isEmpty())
		{
			for(Line x : lines)
			{
				x.Draw(g);
			}
		}
	}

	@Override
	void updateSize(int x, int y) {
		
		
		
		beforex = x1;
		beforey = y1;
		
		xpoints.add(beforex); //x1
		ypoints.add(beforey); //y1
		
		Line l = new Line(beforex, beforey, x, y, curveColor);
		
		
		lines.add(l);
		lines.get(lines.size()-1).lineNumber = lines.size();
		
		
		
		x1 = x;
		y1 = y;
		
		
	}
	
	public boolean hasIntersected(Sonic sonic) {
		// TODO Auto-generated method stub
		for( Line l : lines)
		{
			l.hasIntersected(sonic);
		}
		return false;
	}
	
	
	
	
	double getTotalLength() {
		// TODO Auto-generated method stub
		for(Line l : lines)
		{
			//totalLength= totalLength + l.getLength() + redline;
		}
		return totalLength;
	}

	@Override
	double getLength() {
		
		for(Line l : lines)
		{
			thisLength = thisLength + l.getLength();
		}
		
		return thisLength;
	}
	
	 void resetLength() {
		totalLength = 0;
	}
	
}



