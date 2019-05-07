import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class SoundButtonController implements ActionListener {

	private JRadioButton soundButton;
	private EventController ec;
	
	public SoundButtonController(JRadioButton a, EventController e) {
		// TODO Auto-generated constructor stub
		
		soundButton=a;
		soundButton.addKeyListener(ec);
	}

	
	
		public void actionPerformed(ActionEvent e) {
			
			
			if (!soundButton.isSelected()) {
			soundButton.setSelected(false);
			SoundEffect.gameSound.stop();
			
			}
			else {
				soundButton.setSelected(true);
				SoundEffect.gameSound.play();

			}
		}
	
}
