package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;

public class GameController {
	
	ArrayList<Road> roads = new ArrayList<Road>();
	
	
	public GameController() {
	
		
		// TODO Auto-generated constructor stub
	}
	
	
	class mouseListener extends MouseInputAdapter implements MouseWheelListener{
		boolean mousePressed;
		public void mousePressed(MouseEvent e)
		{
			int x = e.getX();
			int y = e.getY();
            mousePressed = true;
            
            
		}

		public void mouseDragged(MouseEvent e)
		{
			int x = e.getX();	
            int y = e.getY();
            mousePressed = true;
		}
		
		public void mouseReleased(MouseEvent e)
		{
			int x = e.getX();
            int y = e.getY();
            mousePressed = false;
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
				// TODO Auto-generated method stub
				
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
	
	

}
