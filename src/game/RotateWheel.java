package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class RotateWheel {

   int speed;
   float x_velocity;
   int y_velocity;
   int x=100;
   int y=100;
   
   //통합 시 수정사항 1.diameter 2.초기 큰원 위치 (x,y)
   
   
    public static void main(String[] args) {
        new RotateWheel();
    }

    public RotateWheel() {
       speed = 60; //수정쌈가능
       y_velocity=-5; //중력 10000배ㅑ       
       EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Sonic");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private float degrees = 0;

        public TestPane() {
            Timer timer = new Timer(40, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                   //x
                   y=y-y_velocity;
                    degrees += 20;  //안쪽 원 속력
                    repaint();
                }
            });
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1000, 1000);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            
           
            int r=70;
            int small_r=30;
            g2d.setColor(Color.BLACK);      
            g2d.fillOval(x-r, y-r, 2*r+1, 2*r+1);   //바깥쪽 원 좌표, 나중에 변수로 바꿔야 된다

            g2d.setColor(Color.WHITE);

            
            Point p = getPointOnCircle(degrees, 20);
            g2d.fillOval(x+p.x-small_r, y+p.y-small_r, 2*small_r+1, 2*small_r+1);   //안쪽 원 좌표, 20=작은 원 반지름, 41=반지름*2+1
            g2d.dispose();
            
            

           /* int diameter = Math.min(getWidth(), getHeight());
            int x = ((getWidth() - diameter) / 2);
            int y = ((getHeight() - diameter) / 2);

            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x, y, diameter, diameter);   //바깥쪽 원 좌표, 나중에 변수로 바꿔야 된다

            g2d.setColor(Color.GREEN);
            float innerDiameter = 200;      //안쪽 원 크기

            Point p = getPointOnCircle(degrees, (diameter / 2f) - (innerDiameter / 2));
            g2d.fillOval(x + p.x - (int) (innerDiameter / 2), y + p.y - (int) (innerDiameter / 2), (int) innerDiameter, (int) innerDiameter);   //안쪽 원 좌표

            g2d.dispose();*/
        }

        protected Point getPointOnCircle(float degress, float radius) {
            double rads = Math.toRadians(degress - 90); // 0 becomes the top

            // Calculate the outter point of the line
            int xPosy = Math.round((float) (Math.cos(rads) * radius));
            int yPosy = Math.round((float) (Math.sin(rads) * radius));

            return new Point(xPosy, yPosy);

        }

    }

}