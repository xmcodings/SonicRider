package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

//import java.awt.*;

public class GameView {
	ArrayList<Road> roadsToDraw = null;
	ArrayList<Coin> coinsToGet = null;
	ArrayList<Obstacle> obsToDodge = null;
	JFrame mainframe;
	JPanel btnPanel;
	viewPanel gamePanel;

	JToggleButton pencilButton;

	JToggleButton ErasesonicButton;
	JToggleButton RestartButton;
	//JToggleButton penButton;
	JToggleButton NewSonicButton;

	JButton SelectButton;
	JButton startButton;
	JButton terminateButton;
	JButton playButton;
	JButton viewChangeButton;
	ImageIcon playimg;
	ImageIcon startimg;
	ImageIcon terminateimg;
	private Sonic sonicd;
	private ButtonGroup toggleButtons;
	private JToggleButton blackBtn;
	private JToggleButton redBtn;
	private JToggleButton greenBtn;
	BufferedImage background;

	/// score
	JPanel scorePanel;
	JLabel scoreLabel;
	JLabel scoreImage;
	
	private String whitePercent;
	private String redPercent;
	

	public GameView(ArrayList<Road> road, ArrayList<Coin> coin, ArrayList<Obstacle> obs, Sonic s) {
		roadsToDraw = road;
		coinsToGet = coin;
		obsToDodge = obs;
		sonicd = s;
		
		whitePercent = "100";
		redPercent = "100";
		
		// sangil = new Coin(20, 80, 400);
		if (!Coin.coinInit(coinsToGet, 3))
			System.out.println("Coin Position Initialization Error");
		if (!Obstacle.obsInit(obsToDodge, coinsToGet, 1))
			System.out.println("Obstacle Position Initialization Error");
		
		playimg = new ImageIcon("resources/play.png");
		startimg = new ImageIcon("resources/startButton.png");
		terminateimg = new ImageIcon("resources/xbutton.png");
		mainframe = new JFrame();
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnPanel = new JPanel();
		gamePanel = new viewPanel();
		btnPanel.setBackground(Color.BLACK);

		pencilButton = new JToggleButton(new ImageIcon("resources/pen.png"));
		pencilButton.setContentAreaFilled(false);
		pencilButton.setBorderPainted(false);
		pencilButton.setOpaque(false);

		ErasesonicButton = new JToggleButton(new ImageIcon("resources/reset.png"));
		ErasesonicButton.setContentAreaFilled(false);
		ErasesonicButton.setBorderPainted(false);
		ErasesonicButton.setOpaque(false);

		RestartButton = new JToggleButton(new ImageIcon("resources/restart.png"));
		RestartButton.setContentAreaFilled(false);
		RestartButton.setBorderPainted(false);
		RestartButton.setOpaque(false);

		NewSonicButton = new JToggleButton(new ImageIcon("resources/sonicimage.png"));
		NewSonicButton.setContentAreaFilled(false);
		NewSonicButton.setBorderPainted(false);
		NewSonicButton.setOpaque(false);

		/*
		penButton = new JToggleButton(new ImageIcon("resources/line.png"));
		penButton.setContentAreaFilled(false);
		penButton.setBorderPainted(false);
		penButton.setOpaque(false);
*/
		SelectButton  = new JButton(new ImageIcon("resources/charChange.png"));
		SelectButton.setContentAreaFilled(false);
		SelectButton.setBorderPainted(false);
		SelectButton.setOpaque(false);
		
		playButton = new JButton(playimg);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setOpaque(false);

		startButton = new JButton(startimg);
		startButton.setSize(200, 150);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setOpaque(false);

		terminateButton = new JButton(terminateimg);
		terminateButton.setSize(200, 150);
		terminateButton.setContentAreaFilled(false);
		terminateButton.setBorderPainted(false);
		terminateButton.setOpaque(false);

		blackBtn = new JToggleButton(whitePercent,new ImageIcon("resources/wsmall.png"));
		blackBtn.setVerticalTextPosition(SwingConstants.CENTER);
		blackBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		blackBtn.setContentAreaFilled(false);
		blackBtn.setBorderPainted(false);
		blackBtn.setOpaque(false);

		redBtn = new JToggleButton(redPercent, new ImageIcon("resources/rsmall.png"));
		redBtn.setVerticalTextPosition(SwingConstants.CENTER);
		redBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		redBtn.setContentAreaFilled(false);
		redBtn.setBorderPainted(false);
		redBtn.setOpaque(false);

		greenBtn = new JToggleButton(new ImageIcon("resources/gsmall.png"));
		greenBtn.setContentAreaFilled(false);
		greenBtn.setBorderPainted(false);
		greenBtn.setOpaque(false);

		viewChangeButton = new JButton("left");
		toggleButtons = new ButtonGroup();
		/////////////////////
		toggleButtons.add(pencilButton);
		//toggleButtons.add(penButton);
		toggleButtons.add(playButton);

		///////////////////////////////////////////
		/// score
		//////////////
		scorePanel = new JPanel();
		scorePanel.setBorder(new LineBorder(Color.black, 3));
		scoreLabel = new JLabel();
		scoreLabel.setText(":  0");
		scoreImage = new JLabel();
		scoreImage.setIcon(new ImageIcon("resources/coin.png"));

		mainframe.getContentPane().add(gamePanel, BorderLayout.CENTER);
		mainframe.getContentPane().add(btnPanel, BorderLayout.NORTH);
		mainframe.setSize(1200, 900);
		mainframe.setVisible(true);

	}

	void whiteSetText(int percent)
	{
		whitePercent = Integer.toString(percent);
		blackBtn.setText(whitePercent);
	}
	
	void redSetText(int percent)
	{
		redPercent = Integer.toString(percent);
		redBtn.setText(redPercent);
	}
	
	
	void drawbackground() {
		if (GameController.intro) {
			BufferedImageLoader loader = new BufferedImageLoader();
			background = loader.loadImage("./resources/intro.jpg");
		} else if (GameController.mode == 0) {
			BufferedImageLoader loader = new BufferedImageLoader();
			background = loader.loadImage("./resources/gameover.png");
		} // intro true이면 배경 그림 변경
		else if (GameController.mode == 1) {
			BufferedImageLoader loader = new BufferedImageLoader();
			background = loader.loadImage("./resources/background1.jpg");
		} else if (GameController.mode == 2) {
			BufferedImageLoader loader = new BufferedImageLoader();
			background = loader.loadImage("./resources/background.jpg");
		}
	}

	void drawLayout() {
		if (GameController.intro) {
			btnPanel.add(startButton, BorderLayout.CENTER);
			btnPanel.add(terminateButton, BorderLayout.CENTER);

		} else if (GameController.mode == 0) {
			
			
			
		}
		// intro true면 플레이용 화면 안나옴
		else {
			btnPanel.add(SelectButton, BorderLayout.CENTER);
			
			btnPanel.add(pencilButton, BorderLayout.CENTER);
			//btnPanel.add(penButton, BorderLayout.CENTER);
			btnPanel.add(playButton, BorderLayout.CENTER);
			btnPanel.add(NewSonicButton, BorderLayout.CENTER);
			btnPanel.add(ErasesonicButton, BorderLayout.CENTER);
			btnPanel.add(RestartButton, BorderLayout.CENTER);

			btnPanel.add(blackBtn, BorderLayout.CENTER);
			btnPanel.add(redBtn, BorderLayout.CENTER);
			btnPanel.add(greenBtn, BorderLayout.CENTER);

			///////////////////////////////////////////
			/// score
			//////////////
			btnPanel.add(scorePanel, BorderLayout.EAST);
			scorePanel.add(scoreImage, BorderLayout.EAST);
			scorePanel.add(scoreLabel, BorderLayout.EAST);

			// btnPanel.add(viewChangeButton,BorderLayout.CENTER);
			mainframe.setVisible(true);
			pencilButton.doClick();
			blackBtn.doClick();
		}
	}

	void addSelectButtonListener(ActionListener e) {
		SelectButton.addActionListener(e);
	}

	void addStartListener(ActionListener e) {
		startButton.addActionListener(e);
	}

	void addNewSonicListener(ActionListener e) {
		NewSonicButton.addActionListener(e);
	}

	void addTerminateListener(ActionListener e) {
		terminateButton.addActionListener(e);
	}

	void addErasesonicListener(ActionListener e)

	{
		ErasesonicButton.addActionListener(e);
	}

	void addRestartListener(ActionListener e) {
		RestartButton.addActionListener(e);
	}

	void addPencilListener(ActionListener e) {
		pencilButton.addActionListener(e);
	}
/*
	void addPenListener(ActionListener e) {
		penButton.addActionListener(e);
	}
*/
	void addPlayListener(ActionListener e) {
		playButton.addActionListener(e);
	}

	void addViewChangeListener(ActionListener e) {
		viewChangeButton.addActionListener(e);
	}

	void addBlackListener(ActionListener e) {
		blackBtn.addActionListener(e);
	}

	void addRedListener(ActionListener e) {
		redBtn.addActionListener(e);
	}

	void addGreenListener(ActionListener e) {
		greenBtn.addActionListener(e);
	}

	/////////////////////////// button
	void addMouseListener(MouseAdapter ml) {
		gamePanel.addMouseListener(ml);
		gamePanel.addMouseMotionListener(ml);
		gamePanel.addMouseWheelListener(ml);
	}


	void requestFocus() {
		mainframe.requestFocusInWindow();
	}

	void repaintMypanel() {
		gamePanel.repaint();
	}

	void changePlayIcon() {
		if (GameMain.isPlay) {
			playButton.setIcon(new ImageIcon("resources/stop.png"));
		} else {
			playButton.setIcon(playimg);
		}
	}

	void setScore(int coin) {
		scoreLabel.setText(":  " + coin);
	}

	void closeWindow() {
		System.exit(0);
	}

	public class viewPanel extends JPanel {
		public viewPanel() {
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			
			drawbackground();
			g.drawImage(background, 0, 0, null);
			if (!roadsToDraw.isEmpty()) {
				for (Road x : roadsToDraw) {
					x.Draw(g);
				}
			}
			if(!GameController.intro && !(GameController.mode == 0))
			{
				sonicd.Draw(g);	
			}
			
			// sangil.Draw(g);
			// intro가 참이면 코인 표시 안함
			if (!GameController.intro) {
				if (GameController.mode != 0) {
					if (!coinsToGet.isEmpty()) {
						for (Coin x : coinsToGet) {
							x.Draw(g);
						}
					}

					if (!obsToDodge.isEmpty()) {
						for (Obstacle o : obsToDodge) {
							o.Draw(g);
						}
					}
				}
			}
		}
	}

}
