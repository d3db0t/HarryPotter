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
		dragonintrobtn.setBounds(20,40, dragonintroimg.getIconWidth(), dragonintroimg.getIconHeight());
		dragonintrobtn.setBorder(BorderFactory.createEmptyBorder());
		dragonintrobtn.setContentAreaFilled(false);
		// NewGameButton
		ImageIcon newgameimg = (new ImageIcon("NewGameButton.png"));
		JButton newgamebtn = new JButton(newgameimg);
		newgamebtn.setBounds(900,450, newgameimg.getIconWidth(), newgameimg.getIconHeight());
		newgamebtn.setBorder(BorderFactory.createEmptyBorder());
		newgamebtn.setContentAreaFilled(false);
		newgamebtn.setName("NewGameButton");
		newgamebtn.addActionListener(this);
		btns.add(dragonintrobtn);
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
		ImageIcon hufflepuffimg = (new ImageIcon("Hufflepuff.jpg"));
		JButton hufflepuffbtn = new JButton(hufflepuffimg);
		hufflepuffbtn.setBounds(20,200, hufflepuffimg.getIconWidth(), hufflepuffimg.getIconHeight());
		hufflepuffbtn.setBorder(BorderFactory.createEmptyBorder());
		hufflepuffbtn.setContentAreaFilled(false);
		hufflepuffbtn.setName("HufflepuffButton");
		hufflepuffbtn.addActionListener(this);
		preGameLauncher.addButtons(hufflepuffbtn);
		// CreateButtonGlow Image
		ImageIcon createbuttonglowimg = (new ImageIcon("CreateButtonGlow.png"));
		// CreateButton Button
		ImageIcon createbuttonimg = (new ImageIcon("CreateButton.png"));
		JButton createbtn = new JButton(createbuttonimg);
		createbtn.setBounds(990,550, createbuttonimg.getIconWidth(), createbuttonimg.getIconHeight());
		createbtn.setBorder(BorderFactory.createEmptyBorder());
		createbtn.setContentAreaFilled(false);
		createbtn.setName("CreateButton");
		createbtn.addActionListener(this);
		preGameLauncher.addButtons(createbtn);
		createbtn.setRolloverIcon(createbuttonglowimg);
		preGameLauncher.setVisible(true); 
		launcher.addPanel(preGameLauncher);
	}
	public static void main(String [] args) throws IOException{
		new LauncherController();
	}

}
