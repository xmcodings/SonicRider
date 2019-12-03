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

        ArrayList<Road> roadsToDraw = null;
	JFrame mainframe; 
        JPanel btnPanel;
	viewPanel gamePanel;
        JToggleButton pencilButton;
        JToggleButton penButton;
        JButton playButton;
        JButton viewChangeButton;
        ImageIcon penimg;
        private boolean isPlay;
        private Sonic sonicd;
        
        private ButtonGroup toggleButtons;
	public GameView(ArrayList<Road> road, Sonic s){
            roadsToDraw = road;
            sonicd = s;
            //JFrame.setDefaultLookAndFeelDecorated(true);
            penimg = new ImageIcon("pen.png");
            mainframe = new JFrame();
            btnPanel = new JPanel();
            gamePanel = new viewPanel();
            pencilButton = new JToggleButton("pencil");
            penButton = new JToggleButton("pen");
            playButton = new JButton("play");
            viewChangeButton = new JButton("left");
            toggleButtons = new ButtonGroup();
            /////////////////////
            toggleButtons.add(pencilButton);    
            toggleButtons.add(penButton);
            toggleButtons.add(playButton);
            
            ///////////////////////////////////////////
            mainframe.getContentPane().add(btnPanel, BorderLayout.NORTH);
            mainframe.getContentPane().add(gamePanel, BorderLayout.CENTER);
            mainframe.setSize(1200, 900);
            mainframe.setVisible(true);
            pencilButton.doClick();
            
	}
        
        void drawLayout()
        {
            btnPanel.add(pencilButton, BorderLayout.CENTER);
            btnPanel.add(penButton,BorderLayout.CENTER);
            btnPanel.add(playButton,BorderLayout.CENTER);
            btnPanel.add(viewChangeButton,BorderLayout.CENTER);
            mainframe.setVisible(true);
            
        }
        
        void addPencilListener(ActionListener e)
        {
            pencilButton.addActionListener(e);
        }
        void addPenListener(ActionListener e)
        {
            penButton.addActionListener(e);
        }
        void addPlayListener(ActionListener e)
        {
            playButton.addActionListener(e);
        }
        void addViewChangeListener(ActionListener e)
        {
            viewChangeButton.addActionListener(e);
        }
	/////////////////////////// button
        
        void addMouseListener(MouseAdapter ml)
	{
            gamePanel.addMouseListener(ml);
            gamePanel.addMouseMotionListener(ml);
            gamePanel.addMouseWheelListener(ml);
	}
        void addKeyListener(KeyListener l)
	{
            mainframe.setFocusable(true);
            mainframe.addKeyListener(l);
	}
        void requestFocus()
	{
            mainframe.requestFocusInWindow();
	}
	void repaintMypanel()
	{
            gamePanel.repaint();
	}
        
        
	public class viewPanel extends JPanel{
		public viewPanel()
		{	
                    
		}
		public void paintComponent(Graphics g)
		{
                    super.paintComponent(g);
                    if(!roadsToDraw.isEmpty())
                    {
                        for(Road x : roadsToDraw)
                        {
                            x.Draw(g);
                        }
                    }
                    sonicd.Draw(g);
		}
	}
	
	
}


