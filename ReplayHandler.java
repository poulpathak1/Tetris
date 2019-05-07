import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayHandler implements ActionListener {

	public ReplayHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Tetris();
	}

}
