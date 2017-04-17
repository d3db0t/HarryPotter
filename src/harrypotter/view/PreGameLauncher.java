package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PreGameLauncher extends Launcher{
	
	public PreGameLauncher(){
		super();
		getContentPane().setBackground(Color.BLACK);
	}
	
	JPanel btnSouthPanel = new JPanel(new BorderLayout());
	
	public void addButtons(JButton btn){
		btnSouthPanel.add(btn);
	}

}
