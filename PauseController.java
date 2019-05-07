import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class PauseController implements ActionListener {

	private Boolean isPaused;
	public Boolean isStarted=false;
	private JButton button;
	private EventController ec;
	private JRadioButton SoundButton;
	private GameTimer gameTimer;
	//private Game game;
	public PauseController(JButton b, EventController e, JRadioButton r, GameTimer gameTimer) {
		// TODO Auto-generated constructor stub
		button =b;
		ec=e;
		SoundButton=r;
		//game=g;
		this.gameTimer=gameTimer;
		
		button.addKeyListener(ec);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		
		if(!isStarted) {
			isStarted=true;
			button.setText("||");
			isPaused=false;
			SoundButton.setEnabled(false);
			ec.timer.start();
			gameTimer.start();
			ec.timerStatus=true;
		}
		
		
		else {
		if (!isPaused ) {
			button.setText("|>");
			isPaused=true;
			
			ec.timer.stop();
			//gameTimer.pause();
			SoundButton.setEnabled(true);
			ec.timerStatus=false;
			
			}
			
		else if(isPaused){
			button.setText("||");
			isPaused=false;
			ec.timer.start();
			//gameTimer.resume();
			
			SoundButton.setEnabled(false);
			ec.timerStatus=true;
			
		}
		
		
		
			//button.addKeyListener(ec);
			
		
		}
		
	}

}
