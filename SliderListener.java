import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

	
	private JSlider slider;
	public SliderListener(JSlider slider) {
		// TODO Auto-generated constructor stub
		this.slider=slider;
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	
		if (slider.getValue()==0) {
			SoundEffect.volume=SoundEffect.Volume.MUTE;
		}
		if (slider.getValue()==60) {
			SoundEffect.volume=SoundEffect.Volume.HIGH;
		}
		if (slider.getValue()==20) {
			SoundEffect.volume=SoundEffect.Volume.LOW;
		}
		if (slider.getValue()==40) {
			SoundEffect.volume=SoundEffect.Volume.MEDIUM;
		}
			
		
		
	}

}
