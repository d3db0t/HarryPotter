package harrypotter.view;

import javax.swing.JFrame;

public abstract class Launcher extends JFrame{
	public Launcher(){
		setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
