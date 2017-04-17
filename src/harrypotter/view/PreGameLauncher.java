package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PreGameLauncher extends Launcher{
	JPanel btnSouthPanel;
	public PreGameLauncher(){
		super();
		getContentPane().setBackground(Color.BLACK);
		//btnSouthPanel = new JPanel(new BorderLayout());
		//add(btnSouthPanel);
	}
	
	
	
	public void addButtons(JButton btn){
		this.add(btn);
	}

}
