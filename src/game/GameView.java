package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class GameView {

	JFrame mainframe = new JFrame();
	viewPanel gamePanel = new viewPanel();
	
	public GameView(){
		
		
		mainframe.getContentPane().add(gamePanel, BorderLayout.CENTER);
		mainframe.setSize(1200, 900);
		mainframe.setVisible(true);
	}
	
	public class viewPanel extends JPanel{
		public viewPanel()
		{	
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//if(!mylist.isEmpty())
			//{
				//for( x : mylist)
				//{
					//x.Draw(g);
				//}
			//}		
		}
	}
	
	
}


