package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;

public class GameController {
	
    private ArrayList<Road> roads = null;
    private GameView theGameView = null;
    private Road newRoad;

    public GameController(ArrayList<Road> paramRoad, GameView game) {
        roads = paramRoad;
        theGameView = game;
            // TODO Auto-generated constructor stub

        this.theGameView.addPenListener(new PenListener());
        ;
        
        mouseListener mousels = new mouseListener();
        keyboardListener keyls = new keyboardListener();
        this.theGameView.addKeyListener(keyls);
        this.theGameView.addMouseListener(mousels);
        //this.theGameView.addPencilListener(new PencilListener());
        //this.theGameView.addPlayListener(new PlayListener());
        //this.theGameView.addChangeViewListener(new ChangeViewListener());
    }


    class mouseListener extends MouseInputAdapter implements MouseWheelListener{
        boolean mousePressed;
        public void mousePressed(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            roads.add(newRoad);
            roads.get(roads.size() - 1).setX(x);
            roads.get(roads.size() - 1).setY(y);
            
            mousePressed = true;
            
            System.out.println("game.GameController.mouseListener.mousePressed()");
            theGameView.repaintMypanel();
        }
        public void mouseDragged(MouseEvent e)
        {
            int x = e.getX();	
            int y = e.getY();
            mousePressed = true;
            System.out.println("game.GameController.mouseListener.mouseDragged()");
        }

        public void mouseReleased(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            mousePressed = false;
            System.out.println("game.GameController.mouseListener.mouseReleased()");
        }

        public void mouseMoved(MouseEvent e)
        {
        }
        public void mouseClicked(MouseEvent e)
        {
        }

        public void mouseWheelMoved(MouseWheelEvent e)
        {
        }
    }

    class keyboardListener implements KeyListener{

        @Override
        public void keyPressed(KeyEvent e) {

            if(e.isControlDown())
            {
            }
            if(e.getKeyCode() == 127) {   // delete
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
        @Override
        public void keyTyped(KeyEvent arg0) {
        }
        
    }	
	
    class PenListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("game.GameController.PenListener.actionPerformed()");
            newRoad = new Line(0, 0, 0, 0, Color.BLACK, true);
        }
    }

}
