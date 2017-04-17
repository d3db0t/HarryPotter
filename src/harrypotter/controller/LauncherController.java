package harrypotter.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import harrypotter.view.MainLauncher;
import harrypotter.view.PreGameLauncher;

public class LauncherController implements ActionListener {
	private MainLauncher mainLauncher;
	private PreGameLauncher preGameLauncher;
	
	public LauncherController() throws IOException{
		// Initialize MainLauncher
		mainLauncher = new MainLauncher();
		// Play Button
		//JButton playbtn = new JButton();
		//playbtn.setText("Play");
		//playbtn.addActionListener(this);
		//playbtn.setPreferredSize(new Dimension(150,75));
		//playbtn.setBackground(Color.GRAY);
		// Quit Button
		//JButton quitbtn = new JButton();
		//quitbtn.setText("Quit");
		//quitbtn.addActionListener(this);
		//quitbtn.setPreferredSize(new Dimension(150,75));
		//quitbtn.setBackground(Color.DARK_GRAY);
		ArrayList <JButton>btns = new ArrayList<JButton>();
		// NewGameButton
		ImageIcon newgameimg = (new ImageIcon("NewGameButton.png"));
		JButton newgamebtn = new JButton(newgameimg);
		newgamebtn.setBounds(20,200, newgameimg.getIconWidth(), newgameimg.getIconHeight());
		newgamebtn.setBorder(BorderFactory.createEmptyBorder());
		newgamebtn.setContentAreaFilled(false);
		newgamebtn.setName("NewGameButton");
		newgamebtn.addActionListener(this);
		//mainLauncher.addB(newgamebtn);
		//btns.add(playbtn);
		//btns.add(quitbtn);
		btns.add(newgamebtn);
		mainLauncher.addButtons(btns);
		mainLauncher.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton btn  = (JButton) e.getSource();
		String btnID = btn.getName();
		switch(btnID){
		case "Quit" : mainLauncher.dispatchEvent(
				new WindowEvent(
						mainLauncher, WindowEvent.WINDOW_CLOSING)); break;
		case "NewGameButton" : startPreGameLauncher();
							   break;
		
		}
	}
	
	public void startPreGameLauncher(){
		preGameLauncher = new PreGameLauncher();
		ImageIcon hufflepuffimg = (new ImageIcon("Hufflepuff.jpg"));
		JButton hufflepuffbtn = new JButton(hufflepuffimg);
		hufflepuffbtn.setBounds(20,200, hufflepuffimg.getIconWidth(), hufflepuffimg.getIconHeight());
		hufflepuffbtn.setBorder(BorderFactory.createEmptyBorder());
		hufflepuffbtn.setContentAreaFilled(false);
		hufflepuffbtn.setName("NewGameButton");
		hufflepuffbtn.addActionListener(this);
		preGameLauncher.addButtons(hufflepuffbtn);
		preGameLauncher.setVisible(true); 
		mainLauncher.dispose();
	}
	public static void main(String [] args) throws IOException{
		new LauncherController();
	}

}
