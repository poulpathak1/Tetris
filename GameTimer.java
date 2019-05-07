import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class GameTimer{
	
	private int secondsPassed=0;
	private int displaySecond=0;
	private int currMin=4;
	private Game game;
	private Timer timer;
	private TimerTask task;
	private Tetris tetris;
	private JLabel timerLabel;
	private double i=1.0;
	public GameTimer(Game game, JLabel timerLabel, Tetris tetris) {
		this.game=game;
		this.timerLabel=timerLabel;
		 timer= new Timer();
		 this.tetris=tetris;
		 
		 task = new TimerTask() {
		 
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				secondsPassed++;
				if (displaySecond>59) {
					displaySecond=0;
					currMin--;
				}
				displaySecond++;
				
				if (currMin==0 && displaySecond>30) {
				timerLabel.setForeground(Color.red);
				}
				else {
					timerLabel.setForeground(Color.black);
				}
				
					if (displaySecond>50) {
						timerLabel.setText("0"+currMin +" : "+ "0"+(60-displaySecond)+"");
					}
					
					
					else {
					timerLabel.setText("0"+currMin +" : "+(60-displaySecond)+"");
					}
				
				
				/*if (secondsPassed>60*5-1) {
					//while(!game.isGameOver()) {
						
					}*/
				
				if ( Integer.parseInt(tetris.score.getText())>=i*2000) {
					tetris.increaseLevel();		
					timer=new Timer();
					secondsPassed=0;
					displaySecond=0;
					currMin=4;
					i=i+1.25;
				}
					else if(secondsPassed>60*5-1) {
					game.setIsOver(true);
					timer.cancel();
					}
					
					//}
				}
			
			
		};
		
		
		
	}
	
	
	public void start() {
		//timer.schedule(task, 1000);
		timer.scheduleAtFixedRate(task, 1000, 1000);
		
	}
	
	public void pause() {
		timer.cancel();
		
		
	}
	
	public void resume() {
		timer.schedule(task, 1000);
	}

	public int getSecondsPassed(){
		return secondsPassed;
	}
	
	
	
	
	
}