/**
 * Create and control the game Tetris.
 * 
 *
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.net.URL;

import sun.audio.*;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class Tetris extends JPanel {

	private Game game;
	private Boolean isPaused;
	private JButton button;
	private JButton buttonReplay;
	private static JFrame f;
	public JLabel score;
	public int s;
	public boolean gameOver;
	private JLabel timerLabel;
	private JLabel labelLevel;
	private int level=1;
	private EventController ec;
	private double pieceMoveTime=0.7;
	/**
	 * Sets up the parts for the Tetris game, display and user control
	 */
	public Tetris() {
		
		//SoundEffect.init();
		//SoundEffect.gameSound.play();
		
		game = new Game(this);
		isPaused=false;
		s=0;
		gameOver=false;
		
		
		
		
		f=new JFrame("The Tetris Game");
		f.getContentPane().add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(680, 530);
		f.setVisible(true);
		
		
		
	
		setBackground(Color.YELLOW);
		setLayout(null);
		
		
		
		JSlider slider = new JSlider();
		slider.addChangeListener( new SliderListener(slider));
			
		slider.setValue(25);
		slider.setBounds(315, 450, 190, 29);
		add(slider);
		
		slider.setMinimum(0);
		slider.setMaximum(60);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		
		
		
		timerLabel = new JLabel("5 : 00");
		timerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timerLabel.setBounds(332, 167, 161, 40);
		add(timerLabel);
		
		
		GameTimer gameTimer=new GameTimer(game, timerLabel, this);
		
		JButton button = new JButton("START");
		button.setBounds(377, 23, 103, 71);
		add(button);
		
		
		buttonReplay = new JButton("REPLAY");
		buttonReplay.setBounds(377, 83, 103, 71);
		add(buttonReplay);
		
		
		buttonReplay.setEnabled(false);
		
		
		score = new JLabel("0");
		score.setFont(new Font("Chalkduster", Font.BOLD, 50));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(332, 287, 148, 64);
		add(score);
		
		
		
		
		JLabel lblYourScore = new JLabel("Your Score:");
		lblYourScore.setFont(new Font("Chalkduster", Font.BOLD, 30));
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScore.setBounds(300, 237, 246, 51);
		add(lblYourScore);
		
		JRadioButton soundButton =new JRadioButton("SOUND ON");
		soundButton.setBounds(377, 415, 141, 23);
		add(soundButton);
		
	
		
	
		 ec = new EventController(game, button, soundButton, gameTimer);
		
		soundButton.setEnabled(true);
		ec.timer.stop();
		f.addKeyListener(ec);
		soundButton.setSelected(true);
		
		labelLevel = new JLabel("Level 1");
		labelLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelLevel.setHorizontalAlignment(SwingConstants.CENTER);
		labelLevel.setBounds(220, 22, 100, 30);
		add(labelLevel);
		
		
		
		
		
		
	
		
	}
	/**
	 * Updates the display
	 */
	public void update() {
		repaint();	
		
		
	}
	
	
	public void increaseLevel() {
		level++;
		JLabel levelUp = new JLabel("LEVEL UP!!!");
		levelUp.setFont(new Font("Chalkduster", Font.BOLD, 50));
		levelUp.setHorizontalAlignment(SwingConstants.CENTER);
		levelUp.setBounds(100, 237, 346, 151);
		f.getContentPane().add(levelUp);
		
		
		if (pieceMoveTime>0.4) {
			pieceMoveTime-=0.05;
		}
		ec.setPieceMoveTime(pieceMoveTime);
		labelLevel.setText("Level "+level);
	}
	
	
	public void updateScore() {
		s=s+100;
		if (s>=1000) {
			score.setFont(new Font("Chalkduster", Font.BOLD, 40) );
		}
		else if (s>=3000) {
			score.setFont(new Font("Chalkduster", Font.BOLD, 30));
		}
		else if (s>=10000) {
			score.setFont(new Font("Chalkduster", Font.BOLD, 20));
		}
		score.setText(s+"");
	}

	
	
	
	
	
	/**
	 * Paint the current state of the game
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g);
		if (game.isGameOver()) {
			
	
			g.setFont(new Font("Palatino", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER ", 80, 300);
			
			gameOver=true;
			

			
			buttonReplay.setEnabled(true);
			ReplayHandler replayHandler= new ReplayHandler();
			
			buttonReplay.addActionListener(replayHandler);
			
		}
	}

	public static void main(String[] args) {
		
		
		
		Tetris T1=new Tetris();
		
		
		/*
		if (T1.game.isGameOver()) {
			
			
			JButton replayButton = new JButton("REPLAY");
			replayButton.setBounds(457, 48, 67, 29);
			f.getContentPane().add(replayButton);
			replayButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					new Tetris();
				}
				
			});
			
			
			
			//T1.button.setText("REPLAY");
		
		}
			
		*/
		
	}
}
