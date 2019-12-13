package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javax.swing.event.MouseInputAdapter;

public class GameController {

	public static boolean intro = true;
	public static int mode = 1;
	public static boolean sonicselect = false;

	private Clip clip;
	private ArrayList<Road> roads = null;
	private ArrayList<Coin> coinsToGet = null;
	private ArrayList<Obstacle> obsToDodge = null;
	private GameView theGameView = null;
	private Road newRoad;
	private Sonic sonicd;
	// private boolean isPlay;
	private Timer timer;
	BufferedImageLoader loader;
	private Image cursorImage;
	private Color lineColor;
	private int clearNum = 0; // the number a player has cleared the stage; used for coinInit()
	private int clearCoin = 0; // total number of coins obtained until last stage
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Cursor c;

	private int redMax;
	private int whiteMax;	
	public GameController(ArrayList<Road> paramRoad, GameView game, Sonic s, ArrayList<Coin> coins,
			ArrayList<Obstacle> obs) {
		roads = paramRoad;
		theGameView = game;
		obsToDodge = obs;
		sonicd = s;
		coinsToGet = coins;
		redMax = 2000;
		whiteMax = 4000;
		
		// TODO Auto-generated constructor stub
		this.theGameView.addStartListener(new StartListener());
		this.theGameView.addTerminateListener(new TerminateListener());
		//this.theGameView.addPenListener(new PenListener());
		this.theGameView.addPencilListener(new PencilListener());
		this.theGameView.addPlayListener(new PlayListener());
		this.theGameView.addNewSonicListener(new NewSonicListener());
		this.theGameView.addErasesonicListener(new ErasesonicListener());
		this.theGameView.addRestartListener(new RestartListener());
		this.theGameView.addBlackListener(new BlackListener());
		this.theGameView.addRedListener(new RedListener());
		this.theGameView.addGreenListener(new GreenListener());
		this.theGameView.addSelectButtonListener(new SelectButtonListener());

		mouseListener mousels = new mouseListener();
		this.theGameView.addMouseListener(mousels);
		// this.theGameView.addPencilListener(new PencilListener());
		// this.theGameView.addPlayListener(new PlayListener());
		// this.theGameView.addChangeViewListener(new ChangeViewListener());

		timer = new Timer(30, new TimerThread());
		//////////////// timer
	}

	//////////////////////////////// inner class ///////////////////////////////

	class mouseListener extends MouseInputAdapter implements MouseWheelListener {
		boolean mousePressed;
		double whitelength;
		double redlength;
		
		public void mousePressed(MouseEvent e) {
			if (!GameMain.isPlay && !intro) {
				int x = e.getX();
				int y = e.getY();
				roads.add(newRoad);
				roads.get(roads.size() - 1).setX(x);
				roads.get(roads.size() - 1).setY(y);

				mousePressed = true;

				System.out.println("game.GameController.mouseListener.mousePressed()");
				theGameView.repaintMypanel();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (!GameMain.isPlay && !intro) {
				int x = e.getX();
				int y = e.getY();
				mousePressed = true;
				System.out.println("game.GameController.mouseListener.mouseDragged()");
				double lineLength = 0;
				double curvelength = 0;
				
				boolean drawswitch = true;
				/*
				if(roads.get(roads.size() - 1) instanceof Line) {
					Line l = (Line) roads.get(roads.size() - 1);
					
					if(roads.get(roads.size() - 1).getColor() == Color.WHITE)
					{
						whitelength = l.totalLength + l.getLength();
						theGameView.whiteSetText((int)(((double)(whiteMax - whitelength) *100 /(double) whiteMax)));						
					}
					if(roads.get(roads.size() - 1).getColor() == Color.RED)
					{
						redlength = l.redline + l.getLength();
						theGameView.redSetText((int)(((double)(redMax - redlength) *100 /(double) redMax)));						
					}
					
				}
				else {
					Curve c = (Curve) roads.get(roads.size() - 1);
					redlength = c.totalLength + c.getLength();
					theGameView.redSetText((int)(((double)(redMax - redlength)  *100/ (double)redMax)));
				}
				
				if(whiteMax - whitelength < 0 || redMax - redlength < 0)
				{
					drawswitch = false;
				}
				*/
				/*
				if(drawswitch)
				{
					roads.get(roads.size() - 1).updateSize(x, y);
				}
				*/
				roads.get(roads.size() - 1).updateSize(x, y);
				System.out.println(whitelength);
				
				
				theGameView.repaintMypanel();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (!GameMain.isPlay) {
				int x = e.getX();
				int y = e.getY();
				newRoad = new Line(0, 0, 0, 0, lineColor);
				mousePressed = false;
				//System.out.println("game.GameController.mouseListener.mouseReleased()");
				System.out.println(roads.get(roads.size() - 1).getTotalLength());
				double lineLength = 0;
				double curvelength = 0;
				/*
				if(roads.get(roads.size() - 1) instanceof Line)
				{
					lineLength = roads.get(roads.size() - 1).getTotalLength();
					if(roads.get(roads.size() - 1).getColor() == Color.WHITE)
					{
						theGameView.whiteSetText((int)(((double)(whiteMax - lineLength) *100 /(double) whiteMax)));						
					}
					if(roads.get(roads.size() - 1).getColor() == Color.RED)
					{
						theGameView.redSetText((int)(((double)(redMax - lineLength) *100 /(double) redMax)));						
					}
					
				}
				else {
					curvelength = roads.get(roads.size() - 1).getTotalLength();
					theGameView.redSetText((int)(((double)(redMax - curvelength)  *100/ (double)redMax)));
				}
				*/
				System.out.println(curvelength);
				System.out.println(redMax);
			}
		}

		public void mouseMoved(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			if (!GameMain.isPlay) {
				System.out.println("mouseenter");
			}
		}

		public void mouseWheelMoved(MouseWheelEvent e) {
		}

	}

	class ErasesonicListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			
			sonicd.resetSonic();
			try {
				clip.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			
			if (GameMain.isPlay = true) {
				GameMain.isPlay = false;
				Coin.coinRecover(coinsToGet);
				Coin.coin_num = clearCoin;
				theGameView.setScore(Coin.coin_num);
				Obstacle.obsRecover(obsToDodge);
			}
			for (Road r : roads)
			{
				r.resetLength();
			}
			roads.clear();
			theGameView.redSetText(100);
			theGameView.whiteSetText(100);
			theGameView.changePlayIcon();
			theGameView.repaintMypanel();
		}
	}

	class RestartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				clip.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			sonicd.resetSonic();
			
			if (GameMain.isPlay = true) {
				GameMain.isPlay = false;
				coinsToGet.clear();
				clearNum = 0;
				// make
				Coin.coinInit(coinsToGet, 3 + clearNum);
				for (Coin c : coinsToGet) {
					c.coinClear();
				}
				theGameView.setScore(coinsToGet.get(0).getCoin());
				obsToDodge.clear();
				Obstacle.obsInit(obsToDodge, coinsToGet, 1);
			}
			for (Road r : roads)
			{
				r.resetLength();
			}
			roads.clear();
			theGameView.redSetText(100);
			theGameView.whiteSetText(100);
			theGameView.changePlayIcon();
			mode = 1;
			theGameView.repaintMypanel();
				
		}
	}

	class NewSonicListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			newSonic();
		}
	}

	class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			intro = false;
			theGameView.drawbackground();
			theGameView.drawLayout();
			theGameView.repaintMypanel();
			theGameView.startButton.setVisible(intro);
			theGameView.terminateButton.setVisible(intro);
		}
	}

	class TerminateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			theGameView.closeWindow();
		}
	}

	class PenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			newRoad = new Line(0, 0, 0, 0, lineColor);

		}
	}

	class PencilListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newRoad = new Curve(0, 0, 0, 0, Color.RED);
		}
	}

	class BlackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			lineColor = Color.WHITE;
			newRoad = new Line(0, 0, 0, 0, lineColor);
		}
	}

	class RedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			lineColor = Color.RED;
			newRoad = new Line(0, 0, 0, 0, lineColor);
		}
	}

	class SelectButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (coinsToGet.get(0).getCoin() < 10) {
				return;
			}
			coinsToGet.get(0).coinPlus(-10);
			theGameView.setScore(coinsToGet.get(0).getCoin());
			File f = new File("\newimage.png");

			File fileToSave = null;
			// parent component of the dialog
			JFrame parentFrame = new JFrame();

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("pick a picture!");

			int userSelection = fileChooser.showSaveDialog(null);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				sonicselect = true;
				if (f.exists()) {
					f.delete();
					
				}
				fileToSave = fileChooser.getSelectedFile();
				System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				File fileToMove = new File("\newimage.png");
				
				boolean isMoved = fileToSave.renameTo(fileToMove);
			}

//			File file = new File("c:/text.txt");
			BufferedImageLoader loader = new BufferedImageLoader();
			
			sonicd.sonicImage = loader.loadImage("/newimage.png");

		}

		class PenListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("game.GameController.PenListener.actionPerformed()");
				newRoad = new Line(0, 0, 0, 0, lineColor);
		
			}
		}

		class PencilListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newRoad = new Curve(0, 0, 0, 0, Color.RED);
			}
		}

		class BlackListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lineColor = Color.WHITE;
				newRoad = new Curve(0, 0, 0, 0, lineColor);
			}
		}

		class RedListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lineColor = Color.RED;
				newRoad = new Curve(0, 0, 0, 0, lineColor);
			}
		}

		class GreenListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lineColor = Color.GREEN;
				newRoad = new Curve(0, 0, 0, 0, lineColor);
			}
		}

		class PlayListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameMain.togglePlay();
				theGameView.changePlayIcon();

				if (GameMain.isPlay) {
					timer.start();
					Sound(true);
					System.out.println("START");
				} else {
					timer.stop();
					clip.stop();
					System.out.println("STOP");
				}
			}

		}

		public void Sound(boolean Loop) {

			AudioInputStream ais;

			try {
				ais = AudioSystem.getAudioInputStream(new File("./resources/background .wav"));
				clip = AudioSystem.getClip();
				if (Loop) {
					clip.open(ais);
					clip.start();
				} else {
					
					clip.stop();
					clip.setFramePosition(0);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	class GreenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			lineColor = Color.GREEN;
			newRoad = new Curve(0, 0, 0, 0, lineColor);
		}
	}

	class PlayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameMain.togglePlay();
			theGameView.changePlayIcon();
			
			if (GameMain.isPlay) {
				timer.start();
				Sound(true);
				System.out.println("START");
			} else {
				timer.stop();
				clip.stop();
				System.out.println("STOP");
			}
		}

	}

	public void Sound(boolean Loop) {

		AudioInputStream ais;

		try {
			ais = AudioSystem.getAudioInputStream(new File("./resources/background .wav"));
			clip = AudioSystem.getClip();
			if (Loop) {
				clip.open(ais);
				clip.start();
			} else {
				clip.stop();
				clip.setFramePosition(0);
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void newSonic()
	{
		Coin.coinRecover(coinsToGet);
		Obstacle.obsRecover(obsToDodge);
		Coin.coin_num = clearCoin;
		theGameView.setScore(Coin.coin_num);
		sonicd.resetSonic();
		try {
			clip.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (GameMain.isPlay = true) {
			GameMain.isPlay = false;
		}
		theGameView.changePlayIcon();
		theGameView.repaintMypanel();
	}
	
	
	class TimerThread implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (!GameMain.isPlay) {
				
				timer.stop();
			}

			if (!sonicd.setposition()) {
				
				newSonic();
				
			}

			for (Road r : roads) {
				if (r.hasIntersected(sonicd)) {
					
				}
			}

			for (Coin c : coinsToGet) {

				if (c.hasIntersected(sonicd)) {
					
					if (Coin.allEaten(coinsToGet) && GameMain.isPlay) {
						obsToDodge.clear();
						coinsToGet.clear();

						GameMain.isPlay = false;
						roads.clear();
						theGameView.changePlayIcon();
						theGameView.repaintMypanel();
						sonicd.resetSonic();
						clip.close();
						clearNum++;
						clearCoin += 2 + clearNum;
						Coin.coinInit(coinsToGet, 3 + clearNum);
						Obstacle.obsInit(obsToDodge, coinsToGet, 1);
						break;
					}
				}
			}
			theGameView.setScore(coinsToGet.get(0).getCoin());

			for (Obstacle o : obsToDodge) {

				if (o.hasIntersected(sonicd) && GameMain.isPlay) {
					System.out.println("obstacle");

					coinsToGet.clear();
					clearNum = 0;
					clearCoin = 0;
					Coin.coin_num = 0;
					theGameView.setScore(0);
					Coin.coinInit(coinsToGet, 3);
					obsToDodge.clear();
					Obstacle.obsInit(obsToDodge, coinsToGet, 1);
					GameMain.isPlay = false;
					roads.clear();
					theGameView.changePlayIcon();
					theGameView.repaintMypanel();
					sonicd.resetSonic();
					clip.close();
					mode = 0;
					break;
					
				}
			}
			theGameView.repaintMypanel();
		}
	}

}
