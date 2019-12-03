package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Road {

	int x1, y1;
	int x2, y2;	
	int lineNumber; // for freeline
	int backupx;
        int backupy;
        int lineWeight = 3;
        
	void setX(int x){
            x1 = x;
            backupx = x1;
	}
	void setY(int y){
            y1 = y;
            backupy = y1;
	}
        
	public Road(int x1, int y1, int x2, int y2, Color coli, boolean fill) {
            this.x2 = x2;
            this.y2 = y2;
            this.x1 = x1;
            this.x2 = y1;
	}
	
	abstract void Draw(Graphics g);
	
	
}

class Line extends Road{

	Color colorli;
	
	public Line(int x1, int y1, int x2, int y2, Color coli, boolean fill) {
		super(x1, y1, x2, y2, coli, fill);
		// TODO Auto-generated constructor stub
	}

	@Override
	void Draw(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(colorli);
            g2d.setStroke(new BasicStroke(lineWeight));
            g2d.drawLine(x1, y1, x2, y2);
	}
	
        void updateSize()
        {
            
            
        }
        
}



