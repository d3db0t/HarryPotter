package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import harrypotter.view.Launcher;
import harrypotter.view.MainLauncher;
import harrypotter.view.PreGameLauncher;

public class LauncherController implements ActionListener {
	private Launcher launcher;
	private MainLauncher mainLauncher;
	private PreGameLauncher preGameLauncher;
	
	public LauncherController() throws IOException{
		// Initialize MainLauncher
		mainLauncher = new MainLauncher();
		ArrayList <JButton>btns = new ArrayList<JButton>();
		// DragonInto gif
		ImageIcon dragonintroimg = (new ImageIcon("DragonIntro.gif"));
		JButton dragonintrobtn = new JButton(dragonintroimg);
		dragonintrobtn.setBounds(300,10, dragonintroimg.getIconWidth(), dragonintroimg.getIconHeight());
		dragonintrobtn.setBorder(BorderFactory.createEmptyBorder());
		dragonintrobtn.setContentAreaFilled(false);
		// HarryPotter Logo
		ImageIcon harrypotterlogoimg = (new ImageIcon("HPlogo.png"));
		JButton harrypotterlogobtn = new JButton(harrypotterlogoimg);
		harrypotterlogobtn.setBounds(20,40, harrypotterlogoimg.getIconWidth(), harrypotterlogoimg.getIconHeight());
		harrypotterlogobtn.setBorder(BorderFactory.createEmptyBorder());
		harrypotterlogobtn.setContentAreaFilled(false);
		// NewGameButton
		ImageIcon newgameimg = (new ImageIcon("NewGameButton.png"));
		JButton newgamebtn = new JButton(newgameimg);
		newgamebtn.setBounds(900,450, newgameimg.getIconWidth(), newgameimg.getIconHeight());
		newgamebtn.setBorder(BorderFactory.createEmptyBorder());
		newgamebtn.setContentAreaFilled(false);
		newgamebtn.setName("NewGameButton");
		newgamebtn.addActionListener(this);
		btns.add(dragonintrobtn);
		btns.add(harrypotterlogobtn);
		btns.add(newgamebtn);
		mainLauncher.addButtons(btns);
		mainLauncher.setVisible(true);
		launcher = new Launcher();
		launcher.addPanel(mainLauncher);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton btn  = (JButton) e.getSource();
		String btnID = btn.getName();
		switch(btnID){
		case "NewGameButton" : startPreGameLauncher();
							   break;
		
		}
	}
	
	public void startPreGameLauncher(){
		mainLauncher.removeAll();
		mainLauncher.revalidate();
		preGameLauncher = new PreGameLauncher();
		// RavenClaw Image Button
		JButton ravenclawbtn = new JButton();
		ravenclawbtn.setName("RavenClawButton");
		ravenclawbtn.addActionListener(this);
		preGameLauncher.addButtons(ravenclawbtn);
		// Hufflepuff Image Button
		JButton hufflepuffbtn = new JButton();
		hufflepuffbtn.setName("HufflepuffButton");
		hufflepuffbtn.addActionListener(this);
		preGameLauncher.addButtons(hufflepuffbtn);
		// Gryffindor Image Button
		JButton gryffindorbtn = new JButton();
		gryffindorbtn.setName("GryffindorButton");
		gryffindorbtn.addActionListener(this);
		preGameLauncher.addButtons(gryffindorbtn);
		// Slytherin Image Button
		JButton slytherinbtn = new JButton();
		slytherinbtn.setName("SlytherinButton");
		slytherinbtn.addActionListener(this);
		preGameLauncher.addButtons(slytherinbtn);
		// CreateButtonGlow Image
		// CreateButton Button
		JButton createbtn = new JButton();
		createbtn.setName("CreateButton");
		createbtn.addActionListener(this);
		preGameLauncher.addButtons(createbtn);
		//Make pre game launcher visible and adding it to frame
		preGameLauncher.setVisible(true); 
		launcher.addPanel(preGameLauncher);
	}
	public static void main(String [] args) throws IOException{
		new LauncherController();
	}

}
