package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Sonic {
    
    
    
    int speed;
    float x_velocity;
    int y_velocity;
    int x=100;
    int y=100;
    int r=70;
    int small_r=30;
    private float degrees = 0;
    private boolean isPlay;
    
    public Sonic()
    {
        isPlay = false;
        speed = 60; //수정쌈가능
        y_velocity=-5; //중력 10000배ㅑ 
        //theGameView = gameview;
    }
    
    
    void Draw(Graphics g)
    {
        if(GameMain.isPlay)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.BLACK);      
            g2d.fillOval(x-r, y-r, 2*r+1, 2*r+1);   //바깥쪽 원 좌표, 나중에 변수로 바꿔야 된다
            System.out.println("game.Sonic.Draw()");
            g2d.setColor(Color.WHITE);
            Point p = getPointOnCircle(degrees, 20);
            g2d.fillOval(x+p.x-small_r, y+p.y-small_r, 2*small_r+1, 2*small_r+1);   //안쪽 원 좌표, 20=작은 원 반지름, 41=반지름*2+1
            update();
            g2d.dispose();
        }
    }
    
    void update()
    {
        y=y-y_velocity;
        degrees += 20;  //안쪽 원 속력
    }
    
    Point getPointOnCircle(float degress, float radius) {
        double rads = Math.toRadians(degress - 90); // 0 becomes the top

        // Calculate the outter point of the line
        int xPosy = Math.round((float) (Math.cos(rads) * radius));
        int yPosy = Math.round((float) (Math.sin(rads) * radius));
        return new Point(xPosy, yPosy);
        
    }
    
    
}
