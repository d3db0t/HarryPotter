package harrypotter.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Launcher extends JFrame{
	public Launcher(){
		setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void addPanel(JPanel p){
		this.add(p);
	}

}
