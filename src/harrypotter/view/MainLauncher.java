package harrypotter.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainLauncher extends Launcher{
	
	public MainLauncher(){
		super();
		
	}
	
	JPanel btnPanel = new JPanel(new BorderLayout());
    //frame.getContentPane().add(panel,BorderLayout.SOUTH);
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //frame.setSize(300, 300);

	
	public void addButtons(JButton btn){ // Play , Quit
		this.add(btn);
	}

}
