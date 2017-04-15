package harrypotter.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import harrypotter.view.MainLauncher;
import harrypotter.view.PreGameLauncher;

public class LauncherController implements ActionListener {
	private MainLauncher mainLauncher;
	private PreGameLauncher preGameLauncher;
	
	public LauncherController(){
		// Initialize MainLauncher
		mainLauncher = new MainLauncher();
		// Play Button
		JButton playbtn = new JButton();
		playbtn.setText("Play");
		playbtn.addActionListener(this);
		playbtn.setPreferredSize(new Dimension(150,75));
		playbtn.setBackground(Color.GRAY);
		// Quit Button
		JButton quitbtn = new JButton();
		quitbtn.setText("Quit");
		quitbtn.addActionListener(this);
		quitbtn.setPreferredSize(new Dimension(150,75));
		quitbtn.setBackground(Color.DARK_GRAY);
		mainLauncher.addButtons(playbtn);
		//mainLauncher.addButtons(quitbtn);
		mainLauncher.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton btn  = (JButton) e.getSource();
		String btnID = btn.getText();
		switch(btnID){
		case "Quit" : mainLauncher.dispatchEvent(
				new WindowEvent(
						mainLauncher, WindowEvent.WINDOW_CLOSING)); break;
		case "Play" : preGameLauncher = new PreGameLauncher();
					  preGameLauncher.setVisible(true); 
					  mainLauncher.dispose(); break; 				
		
		}
	}
	public static void main(String [] args){
		new LauncherController();
	}

}
