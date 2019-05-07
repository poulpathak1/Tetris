/**
 * Handles events for the Tetris Game.  User events (key strokes) as well as periodic timer
 * events.
 * 
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class EventController extends KeyAdapter implements ActionListener {

	private Game game; // current game: grid and current piece
	public Timer timer;
	
	public boolean timerStatus=false;
	private  double PIECE_MOVE_TIME = 0.8; // wait 0.8 s every time
														// the piece moves down
														// increase to slow it
														// down

	private boolean gameOver;
	private double delay;
	private JButton button;
	private JRadioButton soundButton;
	/**
	 * Creates an EventController to handle key and timer events.
	 * 
	 * @param game
	 *            the game this is controlling
	 * @param gameTimer 
	 */
	public EventController(Game game, JButton b, JRadioButton rdbtn, GameTimer gameTimer) {
		this.game = game;
		gameOver = false;
		button=b;
		soundButton=rdbtn;
		button.addActionListener(new PauseController(button, this, soundButton, gameTimer));
		
		soundButton.addActionListener( new SoundButtonController(soundButton, this));
		
		delay = 1000 * PIECE_MOVE_TIME; // in milliseconds
		timer = new Timer((int) delay, this);
		timer.setCoalesce(true); // if multiple events pending, bunch them to
		// 1 event
		timer.start();
		
		
	
		
	}

	
	
	public void setPieceMoveTime(double a) {
		PIECE_MOVE_TIME=a;
		delay = 1000 * PIECE_MOVE_TIME; // in milliseconds
		timer.setDelay((int) delay);
	}
	/**
	 * Responds to special keys being pressed.
	 * 
	 * Currently just responds to the space key and the q(uit) key
	 */
	public void keyPressed(KeyEvent e) {
		// if 'Q', quit the game
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			timer.stop();
			((JFrame) e.getSource()).dispose();
		}
		if (!gameOver) {
			
			if(timerStatus) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				handleMove(Direction.DOWN);
				//game.rotatePiece();
				break;
            case KeyEvent.VK_LEFT:
            		handleMove(Direction.LEFT);
				break;
            case KeyEvent.VK_RIGHT:
                handleMove(Direction.RIGHT);
				break;
			case KeyEvent.VK_UP:
				game.rotatePiece();
				gameOver=game.isGameOver();
				if(gameOver)
					timer.stop();
				//handleMove(Direction.DOWN);
				break;
			case KeyEvent.VK_SPACE:
				handleHardDrop();
				break;
			case KeyEvent.VK_C:
				holdPiece();
				break;
				
			}
			}
		}
	}
	
	
	
	
	

	private void holdPiece() {
		// TODO Auto-generated method stub
		//game.holdPiece();
		
	}



	private void handleHardDrop() {
		// TODO Auto-generated method stub
		game.hardDrop(Direction.DOWN);
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
		
	}



	/** Updates the game periodically based on a timer event */
	public void actionPerformed(ActionEvent e) {
		handleMove(Direction.DOWN);
	}

	/**
	 * Update the game by moving in the given direction
	 */
	private void handleMove(Direction direction) {
		game.movePiece(direction);
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
	}
}
